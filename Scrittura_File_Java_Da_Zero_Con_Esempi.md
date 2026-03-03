# Scrittura su file in Java – Guida completa “da zero” (con esempi completi + spiegazione riga per riga)

Questa guida mette insieme **tutte le informazioni** discusse in chat su:
- cosa significa “aprire un file” in scrittura in Java
- dove viene creato un file (working directory / path)
- come creare cartelle e file
- overwrite vs append vs create-new
- testo vs binario
- encoding (UTF-8)
- **tutti i metodi principali di scrittura** (classici + moderni NIO)
- **un esempio completo per ogni metodo**, con **spiegazione riga per riga**

> Nota importante: negli esempi uso `Path` e scrivo dentro una cartella `output/`.  
> Se la cartella non esiste, la creo con `Files.createDirectories(...)`.

---

## 0) “Aprire un file” in scrittura: cosa significa davvero

In Java, “aprire un file per scrivere” significa:
1. scegliere **dove** scrivere (percorso `Path` o `File`)
2. scegliere **cosa** scrivere (testo o binario)
3. scegliere **modalità** (overwrite / append / create-new)
4. creare un **canale di uscita** (`Writer` o `OutputStream`)
5. scrivere
6. **chiudere** (quasi sempre con `try-with-resources`)

In pratica: non “apri” manualmente come in C: **crei un Writer/Stream** e quello apre/crea il file.

---

## 1) Dove viene creato il file?

Se usi un percorso relativo tipo `"file.txt"`, Java lo crea nella **working directory** del programma.

- Terminale: la cartella da cui lanci `java ...`
- IDE: dipende dalla configurazione Run (molto comune confondersi)

Per evitare dubbi: usa `Path` e cartelle dedicate (es. `output/`), o un path assoluto.

---

## 2) Creare cartelle e file (NIO)

### 2.1 Creare directory (cartelle)

**Idea:** se vuoi scrivere in `output/risultati.txt` devi essere sicuro che `output/` esista.

📦 Import tipici:
```java
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
```

✅ Esempio completo

```java
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class CreateDirectoriesExample {
    public static void main(String[] args) {
        try {
            Path dir = Path.of("output");
            Files.createDirectories(dir);
            System.out.println("Cartella pronta: " + dir.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

#### Spiegazione riga per riga

- `import java.nio.file.Files;`  
  Importi la classe utility NIO per operazioni su file/cartelle.

- `import java.nio.file.Path;`  
  Importi `Path`, rappresentazione moderna del percorso.

- `import java.io.IOException;`  
  Importi l’eccezione checked tipica dell’I/O.

- `public class CreateDirectoriesExample { ... }`  
  Classe di esempio.

- `public static void main(String[] args) { ... }`  
  Entry point.

- `Path dir = Path.of("output");`  
  Crei un percorso relativo alla working directory.

- `Files.createDirectories(dir);`  
  Crea la cartella **se non esiste**, e non dà errore se già esiste.

- `dir.toAbsolutePath()`  
  Ti stampa dove è davvero la cartella sul disco (utile per capire la working directory).

- `catch (IOException e)`  
  Gestisce problemi di permessi o I/O.

---

### 2.2 Creare un file vuoto

- `Files.createFile(path)` crea il file **solo se non esiste** (se esiste → eccezione).

✅ Esempio completo

```java
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class CreateFileExample {
    public static void main(String[] args) {
        try {
            Path file = Path.of("output", "vuoto.txt");
            Files.createDirectories(file.getParent());
            Files.createFile(file);
            System.out.println("Creato file: " + file.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

#### Spiegazione riga per riga

- `Path file = Path.of("output", "vuoto.txt");`  
  Percorso `output/vuoto.txt` (portabile).

- `file.getParent()`  
  Ritorna la cartella padre (`output`).

- `Files.createDirectories(file.getParent());`  
  Crea la cartella se manca.

- `Files.createFile(file);`  
  Crea il file vuoto. Se esiste già → eccezione.

**Nota pratica:** spesso non serve chiamare `createFile` se poi scrivi con opzioni `CREATE`/`CREATE_NEW`.

---

## 3) Overwrite vs Append vs Create-New (la parte più importante)

Con NIO usi `StandardOpenOption`.

📦 Import:
```java
import java.nio.file.StandardOpenOption;
```

Opzioni principali:
- `CREATE` → crea il file se non esiste
- `APPEND` → aggiunge in fondo
- `TRUNCATE_EXISTING` → svuota il file se esiste (overwrite “pulito”)
- `CREATE_NEW` → crea solo se non esiste (se esiste → errore)

---

## 4) Testo vs Binario

### Testo (caratteri)
Usi `Writer` (o NIO che internamente crea Writer):
- `FileWriter`, `BufferedWriter`, `PrintWriter`
- `Files.writeString`, `Files.write`, `Files.newBufferedWriter`

### Binario (byte)
Usi `OutputStream`:
- `FileOutputStream`, `BufferedOutputStream`
- (più avanzati: `DataOutputStream`, `ObjectOutputStream`)

---

## 5) Encoding (UTF-8): non lasciarlo al caso

Molti problemi “misteriosi” con accenti, simboli e testi tra Windows/Linux derivano dall’encoding.

📦 Import:
```java
import java.nio.charset.StandardCharsets;
```

Con NIO puoi specificare esplicitamente `StandardCharsets.UTF_8` (consigliato).

---

# PARTE A — Metodi moderni consigliati (NIO)

## A1) `Files.writeString()` (Java 11+) – testo semplice

### Quando usarlo
- Hai una **String** finale e vuoi scriverla in modo semplice e moderno.

### Esempio completo: create + overwrite (svuota e riscrive)

```java
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class WriteStringOverwrite {
    public static void main(String[] args) {
        Path file = Path.of("output", "writeString_overwrite.txt");
        String content = "Prima riga\nSeconda riga\n";

        try {
            Files.createDirectories(file.getParent());

            Files.writeString(
                file,
                content,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
            );

            System.out.println("Scritto: " + file.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

#### Spiegazione riga per riga

- `Path file = Path.of("output", "writeString_overwrite.txt");`  
  Scegli dove scrivere.

- `String content = "Prima riga\nSeconda riga\n";`  
  Il contenuto testuale da scrivere.

- `Files.createDirectories(file.getParent());`  
  Crea `output/` se manca.

- `Files.writeString(..., UTF_8, CREATE, TRUNCATE_EXISTING)`  
  Scrive la stringa:
  - `CREATE`: crea il file se non esiste
  - `TRUNCATE_EXISTING`: se esiste, lo svuota prima (overwrite)
  - `UTF_8`: encoding stabile

---

### Esempio completo: create + append (aggiunge in fondo)

```java
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class WriteStringAppend {
    public static void main(String[] args) {
        Path file = Path.of("output", "writeString_append.txt");
        String line = "Aggiunta in append\n";

        try {
            Files.createDirectories(file.getParent());

            Files.writeString(
                file,
                line,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
            );

            System.out.println("Append fatto: " + file.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

#### Spiegazione riga per riga

- `APPEND` aggiunge il testo in fondo.  
- `CREATE` fa sì che il file venga creato se ancora non esiste.

---

## A2) `Files.write()` – scrivere una `List<String>` (righe)

### Quando usarlo
- Hai già le righe separate (lista) e vuoi scriverle come file di testo.

### Esempio completo: write righe (overwrite)

```java
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.util.List;

public class WriteLinesOverwrite {
    public static void main(String[] args) {
        Path file = Path.of("output", "write_lines.txt");
        List<String> lines = List.of("Riga 1", "Riga 2", "Riga 3");

        try {
            Files.createDirectories(file.getParent());
            Files.write(file, lines, StandardCharsets.UTF_8);
            System.out.println("Righe scritte: " + file.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

#### Spiegazione riga per riga

- `List<String> lines = List.of(...)`  
  La lista di righe (ogni elemento è una riga).

- `Files.write(file, lines, UTF_8)`  
  Scrive le righe in UTF-8.  
  Di default sovrascrive (comportamento tipico per la maggior parte dei casi).

---

### Esempio completo: append righe con opzioni

```java
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.util.List;

public class WriteLinesAppend {
    public static void main(String[] args) {
        Path file = Path.of("output", "write_lines_append.txt");
        List<String> newLines = List.of("Nuova riga A", "Nuova riga B");

        try {
            Files.createDirectories(file.getParent());

            Files.write(
                file,
                newLines,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
            );

            System.out.println("Righe in append: " + file.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

#### Spiegazione riga per riga

- `Files.write(..., CREATE, APPEND)`  
  Appende le righe in fondo; crea il file se manca.

---

## A3) `Files.newBufferedWriter()` – controllo massimo (testo grande / generato in loop)

### Quando usarlo
- Stai generando un file grande (report, CSV, log) riga per riga.
- Vuoi buffer + API moderna + controllo.

### Esempio completo

```java
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.io.BufferedWriter;
import java.io.IOException;

public class NewBufferedWriterExample {
    public static void main(String[] args) {
        Path file = Path.of("output", "newBufferedWriter.txt");

        try {
            Files.createDirectories(file.getParent());

            try (BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
                writer.write("Riga 1");
                writer.newLine();
                writer.write("Riga 2");
                writer.newLine();
                writer.write("Riga 3");
            }

            System.out.println("Creato con BufferedWriter: " + file.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

#### Spiegazione riga per riga

- `Files.newBufferedWriter(file, UTF_8)`  
  Apre (o crea) un buffered writer in UTF-8.

- `try (BufferedWriter writer = ...) { ... }`  
  Try-with-resources: chiude e fa flush automaticamente.

- `writer.newLine()`  
  Scrive il newline corretto per il sistema.

---

# PARTE B — Metodi classici (java.io)

## B1) `FileWriter` – testo (classico)

### Quando usarlo
- Esercizi / programmi semplici
- Non ti interessa controllare esplicitamente l’encoding

### Esempio completo: overwrite

```java
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterOverwrite {
    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("filewriter_overwrite.txt")) {
            writer.write("Sovrascrivo il file con FileWriter\n");
            writer.write("Seconda riga\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

#### Spiegazione riga per riga

- `new FileWriter("...")`  
  Apre/crea il file in modalità overwrite (default).

- `writer.write(...)`  
  Scrive testo.

- `try-with-resources`  
  Chiude automaticamente.

---

### Esempio completo: append

```java
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterAppend {
    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("filewriter_append.txt", true)) {
            writer.write("Questa riga va in append\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

#### Spiegazione riga per riga

- Il secondo parametro `true` abilita l’append.

---

## B2) `BufferedWriter` (classico) – buffering su FileWriter

### Quando usarlo
- Stai facendo molte scritture consecutive e vuoi performance migliori.

### Esempio completo

```java
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedWriterExample {
    public static void main(String[] args) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("bufferedwriter.txt"))) {
            writer.write("Riga 1");
            writer.newLine();
            writer.write("Riga 2");
            writer.newLine();
            writer.write("Riga 3");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

#### Spiegazione riga per riga

- `new BufferedWriter(new FileWriter(...))`  
  Avvolge il FileWriter con un buffer.

- `newLine()`  
  Newline corretto OS.

---

## B3) `PrintWriter` – scrittura comoda (println/printf)

### Quando usarlo
- Log, report, output formattato
- Vuoi `println` e `printf` come in console

### Esempio completo

```java
import java.io.PrintWriter;
import java.io.IOException;

public class PrintWriterExample {
    public static void main(String[] args) {
        try (PrintWriter writer = new PrintWriter("printwriter.txt")) {
            writer.println("Riga 1 con println");
            writer.println("Riga 2 con println");
            writer.printf("Numero formattato: %d%n", 42);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

#### Spiegazione riga per riga

- `println` aggiunge newline automaticamente.
- `printf` permette formattazione (`%d`, `%f`, ecc.) e `%n` è il newline portabile.

---

# PARTE C — Scrittura binaria (byte)

## C1) `FileOutputStream` – byte (binari)

### Quando usarlo
- File binari: immagini, pdf, zip
- Scrittura di bytes grezzi

### Esempio completo

```java
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamExample {
    public static void main(String[] args) {
        byte[] data = {65, 66, 67, 10}; // A B C newline (10 = '\n')

        try (FileOutputStream fos = new FileOutputStream("outputstream.bin")) {
            fos.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

#### Spiegazione riga per riga

- `byte[] data = { ... }`  
  Dati binari. Qui 65/66/67 sono valori ASCII per A/B/C.

- `new FileOutputStream("...")`  
  Apre/crea file binario (overwrite default).

- `fos.write(data)`  
  Scrive bytes.

---

### Esempio completo: append binario (solo per completezza)

```java
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamAppend {
    public static void main(String[] args) {
        byte[] data = {68, 69, 70, 10}; // D E F newline

        try (FileOutputStream fos = new FileOutputStream("outputstream_append.bin", true)) {
            fos.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

#### Spiegazione riga per riga

- Il `true` abilita l’append (aggiunge bytes in fondo).

---

## C2) `BufferedOutputStream` – byte con buffer

### Quando usarlo
- Scrivi tanti byte o file grandi e vuoi performance migliori.

### Esempio completo

```java
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedOutputStreamExample {
    public static void main(String[] args) {
        byte[] data = "Testo in bytes\n".getBytes();

        try (BufferedOutputStream bos =
                 new BufferedOutputStream(new FileOutputStream("buffered_output.bin"))) {

            bos.write(data);
            // bos.flush(); // opzionale: try-with-resources flush/close automaticamente

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

#### Spiegazione riga per riga

- `getBytes()` converte stringa → bytes (encoding di default; per controllo serio usare charset).
- `BufferedOutputStream` bufferizza la scrittura.

---

# PARTE D — (Extra) Checklist “da esame / da progetto”

## Regole d’oro
- Se scrivi testo in progetti moderni: **preferisci NIO**
  - `Files.writeString`
  - `Files.write`
  - `Files.newBufferedWriter`
- Specifica UTF-8 quando ha senso (`StandardCharsets.UTF_8`)
- Usa sempre **try-with-resources**
- Chiarisci sempre la modalità:
  - overwrite (truncate)
  - append
  - create-new

---

## Scelta pratica (rapidissima)
- Testo semplice (String) → `Files.writeString()`
- Molte righe già in lista → `Files.write(List<String>)`
- File generato in loop → `Files.newBufferedWriter()`
- Log/report formattati → `PrintWriter`
- Binari → `FileOutputStream` / `BufferedOutputStream`

---

## Perché chiudere è fondamentale
Senza chiusura (o flush) rischi:
- dati non scritti (buffer non svuotato)
- file “bloccato” (Windows)
- risorse (handle) sprecate

Try-with-resources risolve tutto automaticamente.

---

Fine guida.
