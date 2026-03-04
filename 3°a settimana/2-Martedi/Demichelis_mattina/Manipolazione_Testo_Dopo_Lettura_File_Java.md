# Manipolazione del Testo dopo la Lettura di un File in Java

Questa guida raccoglie in modo strutturato tutti i principali metodi e
strumenti che puoi utilizzare **dopo aver letto un file in Java**,
quando hai a disposizione:

-   una `String`
-   una `List<String>` (righe del file)
-   uno `Stream<String>`

L'obiettivo è capire **cosa puoi fare teoricamente**, quali import
servono e quando usare ogni metodo.

------------------------------------------------------------------------

# Import comuni

## Lettura file (NIO moderno)

import java.nio.file.Files; import java.nio.file.Path; import
java.io.IOException;

## Liste

import java.util.List; import java.util.ArrayList; import
java.util.Collections;

## Stream

import java.util.stream.Stream; import java.util.stream.Collectors;

## Regex avanzate

import java.util.regex.Pattern; import java.util.regex.Matcher;

## Charset (se necessario)

import java.nio.charset.StandardCharsets;

------------------------------------------------------------------------

# 1) Metodi principali di String (java.lang -- nessun import necessario)

## Sostituzioni e trasformazioni

### replace(old, new)

Sostituisce tutte le occorrenze letterali di una sottostringa o
carattere. Non usa regex.

### replaceAll(regex, replacement)

Sostituisce usando espressioni regolari (regex). Potentissimo per
trasformazioni complesse. Attenzione ai caratteri speciali come . \* + ?
( ) che devono essere escapati.

### replaceFirst(regex, replacement)

Come replaceAll ma solo per la prima occorrenza.

### toLowerCase() / toUpperCase()

Normalizza il testo per confronti case-insensitive.

### strip() / trim()

Rimuove spazi all'inizio e alla fine. strip() è più moderno e gestisce
meglio Unicode.

### stripLeading() / stripTrailing()

Rimuove spazi solo a sinistra o solo a destra.

### repeat(n)

Ripete la stringa n volte.

------------------------------------------------------------------------

## Controlli e ricerche

### contains(substring)

Verifica se la stringa contiene una sottostringa.

### startsWith(prefix) / endsWith(suffix)

Verifica inizio o fine stringa.

### indexOf(x) / lastIndexOf(x)

Restituisce la posizione della prima o ultima occorrenza.

### isEmpty()

True se lunghezza = 0.

### isBlank()

True se vuota o composta solo da spazi.

### length()

Restituisce la lunghezza in char UTF-16.

------------------------------------------------------------------------

## Estrazione

### substring(begin) / substring(begin, end)

Estrae una porzione della stringa.

### split(regex)

Divide la stringa in un array usando una regex come separatore.

### lines() (Java 11+)

Restituisce uno Stream`<String>`{=html} dalle righe di una stringa
multilinea.

------------------------------------------------------------------------

## Confronti

### equals(other)

Confronto esatto.

### equalsIgnoreCase(other)

Confronto ignorando maiuscole/minuscole.

### compareTo(other)

Confronto lessicografico (ordinamento).

### matches(regex)

Verifica se l'intera stringa rispetta una regex.

------------------------------------------------------------------------

# 2) Regex avanzate (Pattern e Matcher)

Richiedono: import java.util.regex.Pattern; import
java.util.regex.Matcher;

### Pattern.compile(regex)

Compila una regex (utile se riutilizzata molte volte).

### matcher = pattern.matcher(text)

Crea un matcher associato al testo.

### matcher.find()

Trova occorrenze successive.

### matcher.group()

Restituisce il testo matchato o gruppi specifici.

### matcher.replaceAll(repl)

Sostituzione avanzata con regex.

Quando usarle: - Parsing strutturato - Estrazione dati - Validazioni
complesse

------------------------------------------------------------------------

# 3) Operazioni su List`<String>`{=html}

Se hai letto il file come lista di righe:

import java.util.List; import java.util.Collections;

### size()

Numero di righe.

### get(i)

Accede alla riga i-esima.

### Collections.sort(list)

Ordina alfabeticamente.

### removeIf(predicate)

Rimuove elementi che soddisfano una condizione.

### subList(from, to)

Restituisce una porzione della lista.

### String.join(delimiter, list)

Unisce tutte le righe in una stringa unica.

------------------------------------------------------------------------

# 4) Operazioni su Stream`<String>`{=html}

import java.util.stream.Stream; import java.util.stream.Collectors;

## Trasformazioni

### filter(predicate)

Mantiene solo elementi che soddisfano una condizione.

### map(function)

Trasforma ogni elemento.

### flatMap(function)

Un elemento produce più elementi.

### distinct()

Rimuove duplicati.

### sorted()

Ordina gli elementi.

------------------------------------------------------------------------

## Aggregazioni

### count()

Conta gli elementi.

### collect(Collectors.toList())

Converte lo stream in lista.

### collect(Collectors.joining(delimiter))

Unisce gli elementi in una stringa.

### anyMatch / allMatch / noneMatch

Verifica condizioni globali.

### findFirst()

Restituisce il primo elemento che soddisfa una condizione.

------------------------------------------------------------------------

# 5) Parsing numerico

Nessun import necessario (java.lang).

### Integer.parseInt(text)

String → int (può lanciare NumberFormatException).

### Double.parseDouble(text)

String → double.

### Boolean.parseBoolean(text)

String → boolean.

------------------------------------------------------------------------

# 6) Costruzione e formattazione testo

### StringBuilder

Costruzione efficiente di testo in loop.

### String.format(...)

Formattazione stile printf.

------------------------------------------------------------------------

# 7) Normalizzazioni tipiche dopo lettura file

-   Rimozione righe vuote → isBlank()
-   Rimozione spazi multipli → replaceAll("\\s+", " ")
-   Case insensitive → toLowerCase()
-   Rimozione commenti → indexOf + substring
-   Validazione formato → matches(regex)

------------------------------------------------------------------------

# 8) Scrittura dopo trasformazione

Se hai una String finale: Files.writeString(path, content)

Se hai una List`<String>`{=html}: Files.write(path, lines)

------------------------------------------------------------------------

# Nota importante su replaceAll

replaceAll usa regex.

Caratteri speciali: . \* + ? ( ) \[ \] { } \^ \$ \|

Se vuoi sostituire un punto letterale: "\\."

Se vuoi sostituzione semplice senza regex, usa replace().

------------------------------------------------------------------------

# Conclusione

Dopo aver letto un file, il lavoro reale è:

1.  Normalizzare
2.  Pulire
3.  Estrarre
4.  Trasformare
5.  Validare
6.  Riscrivere

Le classi fondamentali che userai sempre sono:

-   String
-   List
-   Stream
-   Pattern / Matcher
-   Files

Questa è la cassetta degli attrezzi completa per la manipolazione del
testo in Java.
