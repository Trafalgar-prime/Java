# Lettura file in Java con `readLine()` – Guida completa (BufferedReader)

Questa guida spiega **in profondità** come leggere un file di testo in Java usando `readLine()`, cioè il metodo tipico di `BufferedReader`.

Contenuti:
- Cos’è `readLine()` e **a quale classe appartiene**
- Lettura riga-per-riga con `BufferedReader`
- Perché serve il buffer (performance)
- Varianti: `Files.newBufferedReader(...)` (NIO moderno)
- Errori comuni (working directory, `null`, newline)
- Confronto rapido con `Scanner` e `Files.lines()`

---

## 1) `readLine()` non è di `FileReader`: è di `BufferedReader`

Molti confondono questa cosa.

- `FileReader` **legge caratteri**
- `BufferedReader` **aggiunge buffering** e mette a disposizione metodi comodi, tra cui:

✅ `readLine()`

**Quindi**: se stai usando `readLine()`, stai usando `BufferedReader` (direttamente o indirettamente).

---

## 2) Perché `BufferedReader` è “meglio” di leggere carattere per carattere

Leggere dal disco è “costoso” (lento).  
Se tu chiami `read()` una volta per ogni carattere, fai tantissime operazioni I/O.

`BufferedReader` fa una cosa intelligente:
- legge un blocco di dati (buffer) in RAM
- poi ti serve i caratteri/righe da lì

Risultato: **molto più veloce** su file medi/grandi.

---

## 3) Esempio principale: `BufferedReader` + `FileReader` + `readLine()`

### Codice completo (standard)

```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadWithBufferedReader {

    public static void main(String[] args) {

        try (BufferedReader reader =
                     new BufferedReader(new FileReader("file.txt"))) {

            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### Spiegazione riga per riga

```java
import java.io.BufferedReader;
```
Importi la classe che **contiene `readLine()`**.

```java
import java.io.FileReader;
```
Importi un reader di base che apre un file testo come stream di caratteri.

```java
import java.io.IOException;
```
Importi `IOException` perché qualsiasi operazione di I/O può fallire (file non esiste, permessi, problemi disco, ecc.).

---

```java
public class ReadWithBufferedReader {
```
Definisci la classe.

```java
public static void main(String[] args) {
```
Entry point dell’applicazione.

---

```java
try (BufferedReader reader =
             new BufferedReader(new FileReader("file.txt"))) {
```

Qui succedono 3 cose:

1) `new FileReader("file.txt")`  
   - prova ad aprire `file.txt`  
   - prepara uno stream di **caratteri**

2) `new BufferedReader(...)`  
   - “avvolge” il FileReader  
   - aggiunge il **buffer**  
   - abilita metodi comodi (tra cui `readLine()`)

3) `try ( ... )` = **try-with-resources**  
   - garantisce che `reader.close()` venga chiamato automaticamente  
   - quindi il file viene chiuso anche in caso di errore

---

```java
String line;
```
Dichiari una variabile che conterrà la riga corrente del file.

---

```java
while ((line = reader.readLine()) != null) {
```

Questa è la riga più importante.

- `reader.readLine()` legge **una riga** e la ritorna come `String`
- quando arriva a fine file, ritorna **`null`**
- quindi la condizione `!= null` significa “continua finché ci sono righe”

🔎 Nota:
- una riga vuota nel file è una `String` valida (es. `""`)
- `null` arriva solo a **fine file**

---

```java
System.out.println(line);
```
Stampa la riga letta.

---

```java
} catch (IOException e) {
    e.printStackTrace();
}
```

Se:
- il file non esiste
- non hai permessi
- c’è un errore di lettura

Java entra nel `catch` e stampa lo stack trace.

---

## 4) Approfondimento: cosa considera “riga” `readLine()`?

`readLine()` considera “fine riga” quando incontra:
- `\n` (newline, tipico Linux/macOS)
- `\r\n` (Windows)

`readLine()` **non include** il carattere di newline nella stringa ritornata.

Esempio:
- nel file hai: `CIAO\n`
- `readLine()` ritorna `"CIAO"` (senza `\n`)

---

## 5) Variante moderna consigliata: `Files.newBufferedReader(Path)`

Questa è la versione più moderna usando NIO (`java.nio.file`).

### Codice

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadModern {

    public static void main(String[] args) {

        try (BufferedReader reader =
                     Files.newBufferedReader(Path.of("file.txt"))) {

            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### Differenza rispetto a FileReader

- `Path.of("file.txt")` crea un oggetto `Path` (percorso moderno)
- `Files.newBufferedReader(...)` crea direttamente un `BufferedReader`

✅ Vantaggi:
- API più moderna
- più flessibile per percorsi e charset (si può specificare)

---

## 6) Errori comuni (che capitano sempre)

### 6.1 “Non trova file.txt” ma tu lo vedi nel progetto
Motivo tipico: **working directory diversa**.

- In un IDE, il programma non “parte” necessariamente dalla cartella dove tu vedi il file.
- Soluzione pratica: usa un path assoluto o verifica la working directory.

Esempio (Windows):
```java
Path path = Path.of("C:\\Users\\...\\file.txt");
```

Esempio (Linux/macOS):
```java
Path path = Path.of("/home/.../file.txt");
```

---

### 6.2 Confondere `null` con riga vuota
- riga vuota: `""`
- fine file: `null`

Quindi questa condizione è corretta:
```java
while ((line = reader.readLine()) != null)
```

---

### 6.3 Dimenticare di chiudere il reader
Se non usi try-with-resources, devi fare `reader.close()` manualmente.  
Meglio **sempre** try-with-resources.

---

## 7) Confronto rapido: `BufferedReader.readLine()` vs `Scanner` vs `Files.lines()`

### BufferedReader + readLine()
✅ molto usato, veloce, controllo “manuale” (while classico)  
Ottimo se vuoi gestire la lettura passo passo.

### Scanner
✅ facile, ma più lento; ottimo per parsing semplice (numeri, token)  
Non ideale per file grandi.

### Files.lines()
✅ moderno, streaming, ottimo per file grandi e pipeline (Stream API)  
Stile più “funzionale”.

---

## 8) Mini-esercizi (per fissare)

1) Modifica l’esempio e stampa solo le righe che contengono la parola `"error"`.  
2) Conta quante righe totali ha il file.  
3) Leggi il file e costruisci una `List<String>` con tutte le righe (qui stai replicando `readAllLines`).

---

## 9) Riassunto “da esame”

- `readLine()` è di **BufferedReader**
- Legge una riga alla volta e ritorna `null` a fine file
- `BufferedReader` è veloce perché usa un buffer
- Preferisci try-with-resources
- Variante moderna: `Files.newBufferedReader(Path)`
