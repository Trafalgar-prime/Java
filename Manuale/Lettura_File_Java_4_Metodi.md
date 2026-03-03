# Lettura file in Java – 4 metodi (Scanner, FileReader, FileInputStream, Files.lines)

Obiettivo: capire **quando usare cosa**, e cosa succede **riga per riga**.

I 4 metodi che approfondiamo:

1. `Scanner` (semplice, comodo)
2. `FileReader` (caratteri, low-level)
3. `FileInputStream` (byte, binario / low-level)
4. `Files.lines()` (moderno, streaming con `Stream<String>`)

> Nota: negli esempi userò un file `"file.txt"` nella **working directory** di esecuzione.  
> In IDE (NetBeans/IntelliJ), la working directory dipende dalla configurazione di Run.

---

## 1) Metodo 1 – `Scanner` (lettura riga per riga “facile”)

### Quando usarlo (pro/contro)
✅ **Pro**
- Facilissimo da usare (ottimo per iniziare)
- Ha metodi pronti per leggere `int`, `double`, ecc. (`nextInt()`, `nextDouble()`, …)

⚠️ **Contro**
- Più lento di `BufferedReader`/`Files.lines()` per file grandi (parsing e overhead)
- Se ti serve performance o file enormi, preferisci altri metodi

### Codice completo

```java
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadWithScanner {

    public static void main(String[] args) {

        try {
            File file = new File("file.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```

### Spiegazione riga per riga

- `import java.io.File;`  
  Importi `File`, che rappresenta un riferimento a un file (percorso + nome).

- `import java.io.FileNotFoundException;`  
  Importi l’eccezione lanciata quando provi ad aprire un file inesistente/non accessibile.

- `import java.util.Scanner;`  
  Importi `Scanner`, lettore alto livello (comodo per testo).

- `public class ReadWithScanner {`  
  Definisci la classe.

- `public static void main(String[] args) {`  
  Entry point.

- `try {`  
  Apri un blocco protetto: l’apertura del file può fallire.

- `File file = new File("file.txt");`  
  Crei un oggetto `File` che *punta* a `file.txt`.  
  Qui **non** leggi ancora il contenuto: descrivi solo il percorso.

- `Scanner scanner = new Scanner(file);`  
  Qui apri davvero il file e colleghi lo scanner.  
  Se il file non esiste → `FileNotFoundException`.

- `while (scanner.hasNextLine()) {`  
  Finché c’è un’altra riga disponibile…

- `String line = scanner.nextLine();`  
  Leggi la riga successiva.

- `System.out.println(line);`  
  Stampa la riga.

- `scanner.close();`  
  Chiudi la risorsa. È fondamentale (rilascia handle file).

- `} catch (FileNotFoundException e) {`  
  Se il file non si apre, gestisci qui.

- `e.printStackTrace();`  
  Stampa stack trace per debugging.

---

### Variante migliore: try-with-resources (consigliata)

```java
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadWithScannerTWR {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("file.txt"))) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```

Qui la `close()` viene chiamata automaticamente.

---

## 2) Metodo 2 – `FileReader` (caratteri, lettura “bassa”)

### Quando usarlo (pro/contro)
✅ **Pro**
- È un `Reader`: lavora a livello di **caratteri** (testo)
- Utile per capire la base dei reader

⚠️ **Contro**
- Leggere un carattere alla volta è spesso inefficiente su file grandi
- In pratica, spesso lo usi con `BufferedReader` per avere buffering

### Codice: lettura carattere per carattere

```java
import java.io.FileReader;
import java.io.IOException;

public class ReadCharByChar {

    public static void main(String[] args) {

        try (FileReader reader = new FileReader("file.txt")) {

            int character;

            while ((character = reader.read()) != -1) {
                System.out.print((char) character);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### Spiegazione riga per riga

- `import java.io.FileReader;`  
  Importi il reader che legge **caratteri**.

- `import java.io.IOException;`  
  Importi l’eccezione generale di I/O.

- `try (FileReader reader = new FileReader("file.txt")) {`  
  Apri il file in lettura come stream di caratteri.  
  Try-with-resources: chiusura automatica.

- `int character;`  
  Variabile `int` perché `read()` ritorna un int e usa `-1` per “fine file”.

- `while ((character = reader.read()) != -1) {`  
  Legge 1 carattere alla volta:  
  - assegna il valore a `character`  
  - se è `-1`, fine file → esci.

- `System.out.print((char) character);`  
  Cast a `char` e stampa (senza andare a capo).

- `} catch (IOException e) { ... }`  
  Gestione errori di I/O.

---

### Nota su encoding (importante)
`FileReader` usa l’encoding di default del sistema.  
Se vuoi forzare UTF‑8, in genere usi `InputStreamReader` con charset (best practice avanzata).

---

## 3) Metodo 3 – `FileInputStream` (byte, binario)

### Quando usarlo (pro/contro)
✅ **Pro**
- Legge **byte**: perfetto per file binari (immagini, pdf, zip)
- È un mattoncino base per I/O in Java

⚠️ **Contro**
- Per testo non è comodo: devi gestire encoding e conversione in caratteri
- Leggere byte per byte è lento senza buffering

### Codice: lettura byte per byte (stampando come char solo per demo)

```java
import java.io.FileInputStream;
import java.io.IOException;

public class ReadBytes {

    public static void main(String[] args) {

        try (FileInputStream fis = new FileInputStream("file.txt")) {

            int byteData;

            while ((byteData = fis.read()) != -1) {
                System.out.print((char) byteData);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### Spiegazione riga per riga

- `import java.io.FileInputStream;`  
  Importi lo stream che legge **byte**.

- `try (FileInputStream fis = new FileInputStream("file.txt")) {`  
  Apri il file come stream di byte.  
  Try-with-resources: chiusura automatica.

- `int byteData;`  
  `read()` ritorna `int`, con `-1` = fine file.

- `while ((byteData = fis.read()) != -1) {`  
  Legge 1 byte alla volta.

- `System.out.print((char) byteData);`  
  Cast a char solo per visualizzare in console se il file è testo semplice.  
  ⚠️ Per binari NON ha senso.

---

### Perché è “il metodo giusto” per binari?
Perché immagini/pdf/zip non sono testo: sono sequenze di byte.  
Per questo `FileInputStream` è il modo naturale di leggerli.

---

## 4) Metodo 4 – `Files.lines()` (moderno, streaming righe)

### Quando usarlo (pro/contro)
✅ **Pro**
- Legge righe in streaming (non carica tutto in RAM)
- Moderno e pulito
- Ottimo per file grandi e pipeline (Stream API)

⚠️ **Contro**
- Devi chiudere lo stream (try-with-resources)
- Serve un minimo di confidenza con Stream

### Codice completo

```java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class ReadWithStream {

    public static void main(String[] args) {

        try (Stream<String> lines = Files.lines(Path.of("file.txt"))) {
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### Spiegazione riga per riga

- `import java.nio.file.Files;` / `import java.nio.file.Path;`  
  Importi NIO: API moderna per file.

- `import java.util.stream.Stream;`  
  Importi `Stream`, un flusso di dati.

- `try (Stream<String> lines = Files.lines(Path.of("file.txt"))) {`  
  - `Path.of("file.txt")` crea un Path  
  - `Files.lines(...)` apre il file e produce uno stream di righe (`String`)  
  - try-with-resources chiude tutto automaticamente

- `lines.forEach(System.out::println);`  
  Per ogni riga, chiama `println(riga)`.

- `catch (IOException e)`  
  Se fallisce la lettura, finisci qui.

---

### Variante pratica: contare righe

```java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CountLines {
    public static void main(String[] args) {
        try {
            long count = Files.lines(Path.of("file.txt")).count();
            System.out.println("Numero righe: " + count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

---

## Confronto finale: scelta rapida

| Metodo | Legge | Livello | Ideale per | Nota |
|-------|------|---------|------------|------|
| `Scanner` | testo (righe/token) | alto | esercizi, parsing facile | comodo ma più lento |
| `FileReader` | caratteri | basso | capire reader, testo semplice | spesso meglio buffered |
| `FileInputStream` | byte | basso | file binari | per testo serve charset |
| `Files.lines()` | righe (Stream) | moderno | file grandi | streaming + try-with-resources |

---

## Mini-checklist (da ricordare sempre)

- Apri file? **Chiudi risorse** → preferisci **try-with-resources**
- File enorme? meglio `Files.lines()` (o `BufferedReader`)
- File binario? `FileInputStream`
- Non fare `catch` vuoti: almeno `e.printStackTrace()`
