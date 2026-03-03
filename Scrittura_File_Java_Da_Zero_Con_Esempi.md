# Scrittura su file in Java — Guida completa “da zero”
*(con esempi completi + spiegazione riga per riga, codice formattato bene)*

Questa guida spiega **tutto il flusso reale** della scrittura su file in Java:

- cosa significa “aprire un file” in scrittura
- dove viene creato un file (working directory / `Path`)
- come creare cartelle e file
- overwrite vs append vs create-new
- testo vs binario
- encoding (UTF-8)
- metodi moderni (NIO) e metodi classici (`java.io`)
- **esempio completo per ogni metodo**, con **spiegazione riga per riga**

> Nota: in molti esempi scrivo dentro `output/`. Se non esiste, la creo con `Files.createDirectories(...)`.

---

## 0) “Aprire un file” in scrittura: cosa significa davvero

In Java, “aprire un file per scrivere” significa:

1. scegliere **dove** scrivere (percorso `Path` o `File`)
2. scegliere **cosa** scrivere (testo o binario)
3. scegliere **modalità** (overwrite / append / create-new)
4. creare un **canale di uscita** (`Writer` o `OutputStream`)
5. scrivere
6. **chiudere** (quasi sempre con *try-with-resources*)

Non “apri” manualmente: **crei un Writer/Stream** e quello apre/crea il file.

---

## 1) Dove viene creato il file?

Se usi un percorso relativo tipo `"file.txt"`, Java lo crea nella **working directory** del programma.

- Terminale: la cartella da cui lanci `java ...`
- IDE: dipende dalla configurazione Run (spesso è la cartella del progetto o `build/` / `target/`)

Per evitare dubbi, usa `Path` e/o stampa `toAbsolutePath()`.

---

## 2) Creare cartelle e file (NIO)

### 2.1 Creare directory (cartelle)

📦 Import tipici:
```java
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
```

✅ **Esempio completo**

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

- `Path dir = Path.of("output");`  
  Crea un percorso (relativo alla working directory).
- `Files.createDirectories(dir);`  
  Crea la cartella se non esiste (se esiste, non dà errore).
- `dir.toAbsolutePath()`  
  Ti fa capire *dove* stai scrivendo davvero.
- `catch (IOException e)`  
  Gestisce errori di permessi o I/O.

---

### 2.2 Creare un file vuoto

`Files.createFile(path)` crea il file **solo se non esiste** (se esiste → eccezione).

✅ **Esempio completo**

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
  Percorso portabile (`output/vuoto.txt`).
- `Files.createDirectories(file.getParent());`  
  Crea `output/` se manca.
- `Files.createFile(file);`  
  Crea il file vuoto. Se esiste già, lancia `FileAlreadyExistsException` (sottoclasse di `IOException`).

> Nota: spesso non serve creare a mano il file se poi scrivi con `CREATE`/`CREATE_NEW`.

---

## 3) Overwrite vs Append vs Create-New (fondamentale)

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

Molti problemi con accenti e simboli derivano dall’encoding di default del sistema.

📦 Import:
```java
import java.nio.charset.StandardCharsets;
```

Nei progetti moderni: **UTF-8** quasi sempre.

---

# PARTE A — Metodi moderni consigliati (NIO)

## A1) `Files.writeString()` (Java 11+) — testo semplice

### Quando usarlo
Hai una `String` finale e vuoi scriverla in modo pulito.

---

### A1.1 Esempio completo: create + overwrite

📦 Import:
```java
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
```

✅ **Codice**

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

- `Path file = Path.of("output", "...");` → scegli il percorso.
- `Files.createDirectories(file.getParent());` → crea la cartella se manca.
- `Files.writeString(..., UTF_8, CREATE, TRUNCATE_EXISTING)` →  
  crea il file se manca, e se esiste lo svuota e riscrive tutto.

---

### A1.2 Esempio completo: create + append

✅ **Codice**

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

- `CREATE` → crea il file se non esiste.
- `APPEND` → aggiunge in fondo senza cancellare nulla.

---

## A2) `Files.write()` — scrivere una `List<String>` (righe)

### Quando usarlo
Hai righe già separate e vuoi scrivere un file “a righe”.

---

### A2.1 Esempio completo: overwrite

📦 Import:
```java
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.util.List;
```

✅ **Codice**

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

- `List.of(...)` → crea una lista immutabile di righe.
- `Files.write(file, lines, UTF_8)` → scrive le righe (di default sovrascrive).

---

### A2.2 Esempio completo: append righe con opzioni

📦 Import:
```java
import java.nio.file.StandardOpenOption;
```

✅ **Codice**

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

- `CREATE` + `APPEND` → crea se manca, poi aggiunge in fondo.

---

## A3) `Files.newBufferedWriter()` — controllo massimo (testo grande / generato in loop)

### Quando usarlo
Stai generando un file lungo riga per riga e vuoi:
- buffer
- newline corretto (`newLine()`)
- encoding controllato (UTF-8)

✅ **Codice**

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

- `Files.newBufferedWriter(file, UTF_8)` → apre un `BufferedWriter` moderno in UTF-8.
- `try (BufferedWriter writer = ...)` → chiusura automatica + flush automatico.
- `writer.newLine()` → newline corretto per OS.

---

# PARTE B — Metodi classici (`java.io`)

## B1) `FileWriter` — testo (classico)

### Quando usarlo
Esercizi o programmi piccoli, dove l’encoding di default non ti crea problemi.

---

### B1.1 Esempio completo: overwrite

✅ **Codice**

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

- `new FileWriter("...")` → apre/crea in modalità overwrite.
- `writer.write(...)` → scrive testo.
- try-with-resources → chiude e flush automatico.

---

### B1.2 Esempio completo: append

✅ **Codice**

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

- `true` nel costruttore → abilita l’append.

---

## B2) `BufferedWriter` (classico) — buffering su `FileWriter`

### Quando usarlo
Scrivi tante volte e vuoi performance migliori rispetto a FileWriter “nudo”.

✅ **Codice**

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

- `new BufferedWriter(new FileWriter(...))` → aggiunge buffering.
- `newLine()` → newline portabile.

---

## B3) `PrintWriter` — comodo (println/printf)

### Quando usarlo
Log e report formattati.

✅ **Codice**

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

- `println` → scrive e va a capo.
- `printf` → formattazione; `%n` è newline portabile.

---

# PARTE C — Scrittura binaria (byte)

## C1) `FileOutputStream` — byte (binari)

### Quando usarlo
Immagini, pdf, zip, qualunque file non-testuale.

✅ **Codice**

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

- `byte[] data = {...}` → dati grezzi.
- `new FileOutputStream(...)` → apre/crea file binario (overwrite default).
- `fos.write(data)` → scrive bytes.

---

### C1.2 Esempio completo: append binario

✅ **Codice**

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

- `true` → append di bytes in fondo.

---

## C2) `BufferedOutputStream` — byte con buffer

### Quando usarlo
Molti byte / file grandi → buffering = più performance.

✅ **Codice**

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
            // bos.flush(); // opzionale: la close fa flush automaticamente

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

#### Spiegazione riga per riga

- `getBytes()` → converte stringa in bytes (encoding di default).
- `BufferedOutputStream` → accumula e scrive a blocchi.

---

# PARTE D — Checklist finale (da esame / progetto)

## Regole d’oro
- Testo moderno → **NIO** (`Files.writeString`, `Files.write`, `Files.newBufferedWriter`)
- Specifica UTF-8 quando serve (`StandardCharsets.UTF_8`)
- Sempre **try-with-resources**
- Sii esplicito sulla modalità:
  - overwrite (truncate)
  - append
  - create-new

## Scelta pratica rapidissima
- String → `Files.writeString()`
- List<String> → `Files.write(...)`
- File generato in loop → `Files.newBufferedWriter()`
- Log/report formattati → `PrintWriter`
- Binari → `FileOutputStream` / `BufferedOutputStream`

## Perché chiudere è fondamentale
Senza `close()` / `flush()` rischi:
- dati non scritti (buffer non svuotato)
- file bloccato (Windows)
- risorse sprecate

Try-with-resources risolve.

---

Fine guida.
