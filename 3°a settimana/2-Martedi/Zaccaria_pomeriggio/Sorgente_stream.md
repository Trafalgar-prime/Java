# Sorgente delle Stream in Java

Quando si parla di **Stream in Java**, la prima cosa da capire bene è **da dove arrivano i dati**.  
Una **Stream non contiene dati**, ma **li riceve da una sorgente (source)** e li elabora.

Quindi il concetto è:

```java
SORGENTE → STREAM → OPERAZIONI → RISULTATO
```

Esempio semplice:

```java
List<Integer> numeri = List.of(1,2,3,4,5);

numeri.stream()
      .filter(n -> n % 2 == 0)
      .forEach(System.out::println);
```

Qui:

- **Sorgente** → `List`
- **Stream** → `numeri.stream()`
- **Operazioni** → `filter`
- **Terminale** → `forEach`

---

## 1️⃣ Che cos'è una sorgente di Stream

Una **sorgente** è **qualsiasi struttura dati o generatore di dati da cui Java può creare una Stream**.

Le sorgenti principali sono:

1. **Collezioni**
2. **Array**
3. **File**
4. **Stringhe**
5. **Generatori di valori**
6. **Stream statiche (`Stream.of`, `Stream.generate`, ecc.)**
7. **Random / numeri**
8. **Reader e file di testo**
9. **Stream primitive (`IntStream`, `LongStream`, `DoubleStream`)**

Vediamole **una per una**, perché è qui che molti studenti fanno confusione.

---

## 2️⃣ Stream da collezioni (la sorgente più usata)

Qualsiasi **Collection** può generare una stream.

Esempi:

- `List`
- `Set`
- `Queue`
- `TreeSet`
- `HashSet`

### Esempio

```java
List<String> nomi = List.of("Luca", "Marco", "Anna");

nomi.stream()
    .forEach(System.out::println);
```

Oppure:

```java
Set<Integer> numeri = Set.of(1,2,3,4);

numeri.stream()
      .forEach(System.out::println);
```

Metodo usato:

```java
collection.stream()
```

Oppure **parallel stream**:

```java
collection.parallelStream()
```

---

## 3️⃣ Stream da array

Gli **array non hanno il metodo `stream()`**, quindi si usa `Arrays.stream()`.

### Esempio

```java
int[] numeri = {1,2,3,4,5};

Arrays.stream(numeri)
      .forEach(System.out::println);
```

Oppure:

```java
String[] nomi = {"Luca","Anna","Marco"};

Arrays.stream(nomi)
      .forEach(System.out::println);
```

---

## 4️⃣ Stream da file (molto importante)

Java permette di creare stream **leggendo file riga per riga**.

Metodo principale:

```java
Files.lines()
```

### Esempio

File `dati.txt`

```text
Luca
Anna
Marco
```

Codice:

```java
import java.nio.file.Files;
import java.nio.file.Path;

Files.lines(Path.of("dati.txt"))
     .forEach(System.out::println);
```

Qui la stream contiene **le righe del file**.

Tipo:

```java
Stream<String>
```

---

## 5️⃣ Stream da Stringhe

Si può creare una stream **dei caratteri di una stringa**.

### Esempio

```java
String parola = "JAVA";

parola.chars()
      .forEach(System.out::println);
```

Output:

```text
74
65
86
65
```

Perché restituisce i **codici ASCII**.

Oppure convertendo:

```java
parola.chars()
      .mapToObj(c -> (char)c)
      .forEach(System.out::println);
```

Output:

```text
J
A
V
A
```

---

## 6️⃣ Stream da valori diretti

Possiamo creare una stream **scrivendo direttamente i valori**.

Metodo:

```java
Stream.of()
```

### Esempio

```java
Stream.of(1,2,3,4,5)
      .forEach(System.out::println);
```

Oppure con stringhe:

```java
Stream.of("Luca","Anna","Marco")
      .forEach(System.out::println);
```

---

## 7️⃣ Stream infinite (generatori)

Java può generare **stream infinite**.

Due metodi fondamentali:

```java
Stream.generate()
Stream.iterate()
```

### `generate()`

Genera valori usando una funzione.

Esempio:

```java
Stream.generate(Math::random)
      .limit(5)
      .forEach(System.out::println);
```

Output:

```text
0.34
0.72
0.19
0.88
0.55
```

### `iterate()`

Genera una sequenza.

```java
Stream.iterate(0, n -> n + 2)
      .limit(5)
      .forEach(System.out::println);
```

Output:

```text
0
2
4
6
8
```

---

## 8️⃣ Stream di numeri (primitive streams)

Java ha stream ottimizzate per numeri.

Tipi:

```java
IntStream
LongStream
DoubleStream
```

### Esempio

```java
IntStream.range(1, 6)
         .forEach(System.out::println);
```

Output:

```text
1
2
3
4
5
```

Oppure:

```java
IntStream.rangeClosed(1,5)
```

---

## 9️⃣ Stream da Random

Si possono generare stream di numeri casuali.

```java
new Random().ints()
            .limit(5)
            .forEach(System.out::println);
```

---

## 🔟 Stream da Reader / BufferedReader

Se stai leggendo file manualmente.

```java
BufferedReader reader = new BufferedReader(new FileReader("file.txt"));

reader.lines()
      .forEach(System.out::println);
```

---

## 11️⃣ Stream da Map

Le **Map** non hanno direttamente `stream()`, ma le loro viste sì.

Possibili sorgenti:

```java
map.keySet()
map.values()
map.entrySet()
```

### Esempio

```java
Map<String,Integer> mappa = Map.of(
        "Luca",20,
        "Anna",25
);

mappa.entrySet()
     .stream()
     .forEach(System.out::println);
```

---

## 📊 Riepilogo delle sorgenti principali

| Sorgente | Metodo |
|---|---|
| Collection | `collection.stream()` |
| Array | `Arrays.stream()` |
| Valori | `Stream.of()` |
| File | `Files.lines()` |
| BufferedReader | `reader.lines()` |
| String | `string.chars()` |
| Generatori | `Stream.generate()` |
| Sequenze | `Stream.iterate()` |
| Numeri | `IntStream.range()` |
| Random | `Random.ints()` |
| Map | `map.entrySet().stream()` |

---

## ⚠️ Concetto fondamentale

Una **stream può essere usata una sola volta**.

Questo genera errore:

```java
Stream<Integer> s = List.of(1,2,3).stream();

s.forEach(System.out::println);

s.forEach(System.out::println); // ERRORE
```

Perché la stream è **consumata**.

---

## 💡 Regola mentale utile

Quando usi le stream pensa sempre così:

```java
1. sorgente dei dati
2. creazione stream
3. operazioni intermedie
4. operazione terminale
```

Esempio completo:

```java
Files.lines(Path.of("dati.txt"))  // sorgente
     .filter(s -> s.length() > 3) // intermedia
     .map(String::toUpperCase)    // intermedia
     .forEach(System.out::println); // terminale
```
