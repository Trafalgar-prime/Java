# Scrittura su File in Java -- Guida Completa con Spiegazioni ed Esempi

Questa guida contiene TUTTE le spiegazioni e gli esempi relativi ai
metodi di scrittura su file in Java.

------------------------------------------------------------------------

# 1️⃣ FileWriter (scrittura testo -- metodo classico)

## Import

import java.io.FileWriter; import java.io.IOException;

## Cos'è

FileWriter è uno stream di caratteri. Scrive testo in un file.

Lavora a livello di char, non di byte.

## Comportamento

-   Sovrascrive il file per default
-   Usa encoding di default del sistema
-   Può lavorare in modalità append

## Esempio -- Sovrascrittura

try (FileWriter writer = new FileWriter("file.txt")) {
writer.write("Ciao mondo"); } catch (IOException e) {
e.printStackTrace(); }

Apre il file, cancella il contenuto precedente e scrive il testo.

## Esempio -- Append

try (FileWriter writer = new FileWriter("file.txt", true)) {
writer.write("Nuova riga`\n`{=tex}"); }

Il parametro true abilita l'append.

------------------------------------------------------------------------

# 2️⃣ BufferedWriter (scrittura testo con buffer)

## Import

import java.io.BufferedWriter; import java.io.FileWriter; import
java.io.IOException;

## Cos'è

Writer con buffer. Accumula testo in RAM e scrive a blocchi.

## Esempio

try (BufferedWriter writer = new BufferedWriter(new
FileWriter("file.txt"))) {

    writer.write("Prima riga");
    writer.newLine();
    writer.write("Seconda riga");

} catch (IOException e) { e.printStackTrace(); }

## Concetti chiave

-   newLine() inserisce newline corretto per OS
-   flush() forza scrittura immediata
-   Ottimo per molte scritture consecutive

------------------------------------------------------------------------

# 3️⃣ PrintWriter (scrittura comoda e formattata)

## Import

import java.io.PrintWriter;

## Cos'è

Writer ad alto livello.

Permette: - print() - println() - printf()

## Esempio

try (PrintWriter writer = new PrintWriter("file.txt")) {

    writer.println("Riga 1");
    writer.printf("Numero: %d%n", 10);

}

## Differenza rispetto a BufferedWriter

-   Più comodo
-   Gestione errori meno rigorosa
-   Ottimo per formattazione

------------------------------------------------------------------------

# 4️⃣ FileOutputStream (scrittura byte)

## Import

import java.io.FileOutputStream; import java.io.IOException;

## Cos'è

Stream di byte.

Usato per: - immagini - PDF - zip - file binari

## Esempio

try (FileOutputStream fos = new FileOutputStream("file.bin")) {

    byte[] data = {65, 66, 67};
    fos.write(data);

}

Scrive byte grezzi.

## Differenza chiave

Writer → caratteri (testo) OutputStream → byte (binario)

------------------------------------------------------------------------

# 5️⃣ BufferedOutputStream

## Import

import java.io.BufferedOutputStream; import java.io.FileOutputStream;

## Cos'è

Versione bufferizzata di FileOutputStream.

## Esempio

try (BufferedOutputStream bos = new BufferedOutputStream(new
FileOutputStream("file.bin"))) {

    bos.write("Testo".getBytes());

}

Usato per file grandi o molte scritture.

------------------------------------------------------------------------

# 6️⃣ Files.write() (API moderna NIO)

## Import

import java.nio.file.Files; import java.nio.file.Path; import
java.util.List;

## Cos'è

Metodo statico moderno.

Può scrivere: - byte\[\] - List`<String>`{=html}

## Esempio

Files.write(Path.of("file.txt"), List.of("Riga 1", "Riga 2"));

Sovrascrive per default.

------------------------------------------------------------------------

# 7️⃣ Files.write con opzioni

## Import

import java.nio.file.StandardOpenOption;

## Esempio -- Append

Files.write(Path.of("file.txt"), List.of("Nuova Riga"),
StandardOpenOption.CREATE, StandardOpenOption.APPEND);

## Opzioni importanti

-   CREATE
-   APPEND
-   TRUNCATE_EXISTING
-   CREATE_NEW

------------------------------------------------------------------------

# 8️⃣ Files.writeString() (Java 11+)

Metodo più semplice per scrivere testo oggi.

## Esempio

Files.writeString(Path.of("file.txt"), "Contenuto completo");

------------------------------------------------------------------------

# 9️⃣ Files.newBufferedWriter()

Equivalente moderno di new BufferedWriter(new FileWriter(...))

## Esempio

try (var writer = Files.newBufferedWriter(Path.of("file.txt"))) {

    writer.write("Testo moderno");
    writer.newLine();

}

------------------------------------------------------------------------

# 🔟 ObjectOutputStream (serializzazione oggetti)

## Import

import java.io.ObjectOutputStream; import java.io.FileOutputStream;
import java.io.Serializable;

## Esempio

class Person implements Serializable { String name; Person(String name)
{ this.name = name; } }

try (ObjectOutputStream oos = new ObjectOutputStream(new
FileOutputStream("obj.dat"))) {

    oos.writeObject(new Person("Luca"));

}

## Cos'è

Scrive oggetti Java in formato binario. Non leggibile dall'uomo.

------------------------------------------------------------------------

# 1️⃣1️⃣ DataOutputStream

## Import

import java.io.DataOutputStream; import java.io.FileOutputStream;

## Esempio

try (DataOutputStream dos = new DataOutputStream(new
FileOutputStream("data.bin"))) {

    dos.writeInt(10);
    dos.writeDouble(3.14);

}

## Cos'è

Scrive tipi primitivi in formato binario strutturato.

------------------------------------------------------------------------

# Overwrite vs Append

FileWriter → Overwrite FileOutputStream → Overwrite Files.write →
Overwrite APPEND → Aggiunge

------------------------------------------------------------------------

# Scelta pratica

Testo semplice moderno → Files.writeString() Molte righe →
Files.write(List`<String>`{=html}) Scrittura controllata →
Files.newBufferedWriter() Log formattati → PrintWriter File binari →
FileOutputStream Oggetti Java → ObjectOutputStream

------------------------------------------------------------------------

# Best Practice

-   Usa sempre try-with-resources
-   Specifica UTF-8 quando serve
-   Non ignorare IOException
-   Preferisci NIO nei progetti moderni
