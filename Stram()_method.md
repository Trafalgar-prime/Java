# Stream in Java — guida completa (Java 8+)

Le **Stream** in Java sono uno degli strumenti più potenti introdotti da **Java 8** per lavorare con collezioni in modo **dichiarativo, funzionale e leggibile**.

> Nota importante: una Stream **non è una struttura dati** e **non memorizza** elementi.  
> Lavora sugli elementi di una sorgente (List, Set, array, file, range…) applicando operazioni di trasformazione e aggregazione.

---

## 1) Cos’è una Stream

Una **Stream** è una *sequenza* di elementi su cui esegui operazioni come:
- filtrare (`filter`)
- trasformare (`map`)
- ordinare (`sorted`)
- ridurre a un valore (`reduce`)
- collezionare in una lista/set/map (`collect`)
- trovare elementi (`findFirst`, `findAny`)
- verificare condizioni (`anyMatch`, `allMatch`, `noneMatch`)

Esempio base:

```java
import java.util.List;

public class EsempioBase {
    public static void main(String[] args) {
        List<String> nomi = List.of("Luca", "Anna", "Marco");

        nomi.stream()
            .filter(n -> n.length() > 4)
            .forEach(System.out::println);
    }
}
```

---

## 2) Struttura di una Stream: Sorgente → Intermedie → Terminale

Una pipeline Stream ha sempre questo schema:

```
SORGENTE → OPERAZIONI INTERMEDIE → OPERAZIONE TERMINALE
```

### 2.1 Sorgente (da dove arrivano i dati)

Esempi di sorgenti comuni:
- `List`, `Set`, `Collection` → `collection.stream()`
- Array → `Arrays.stream(array)`
- Map → `map.entrySet().stream()`, `map.keySet().stream()`, `map.values().stream()`
- Range numerici → `IntStream.range(...)`, `LongStream.range(...)`
- File → `Files.lines(path)` (stream di righe)

Esempi:

```java
import java.util.*;
import java.util.stream.*;
import java.nio.file.*;

public class SorgentiStream {
    public static void main(String[] args) throws Exception {
        List<Integer> list = List.of(1,2,3);
        list.stream();

        int[] arr = {1,2,3};
        Arrays.stream(arr);

        Map<String, Integer> map = Map.of("a", 1, "b", 2);
        map.entrySet().stream();

        IntStream.range(0, 10);

        // Files.lines(Path.of("file.txt")); // richiede gestione eccezioni e file esistente
    }
}
```

---

## 3) Operazioni intermedie (Intermediate Operations)

Le operazioni intermedie:
- **trasformano** la Stream
- **restituiscono sempre** una nuova Stream
- sono **lazy**: non vengono eseguite davvero finché non chiami una terminale

Esempi tipici:
- `filter()`
- `map()`
- `sorted()`
- `distinct()`
- `limit()`
- `skip()`

---

## 4) Operazioni terminali (Terminal Operations)

Le operazioni terminali:
- **consumano** la Stream
- producono un risultato finale (o un effetto, come stampare)
- dopo una terminale, **quella stream non si può ri-usare**

Esempi tipici:
- `forEach()`
- `collect()`
- `count()`
- `reduce()`
- `findFirst()`, `findAny()`
- `anyMatch()`, `allMatch()`, `noneMatch()`

---

## 5) Esempio concreto: senza Stream vs con Stream

Dati:

```java
import java.util.List;

List<Integer> numeri = List.of(1,2,3,4,5,6,7,8,9,10);
```

### 5.1 Senza Stream (imperativo)

```java
import java.util.*;

public class SenzaStream {
    public static void main(String[] args) {
        List<Integer> numeri = List.of(1,2,3,4,5,6,7,8,9,10);

        List<Integer> pari = new ArrayList<>();
        for (int n : numeri) {
            if (n % 2 == 0) {
                pari.add(n);
            }
        }

        System.out.println(pari);
    }
}
```

### 5.2 Con Stream (dichiarativo)

```java
import java.util.*;
import java.util.stream.Collectors;

public class ConStream {
    public static void main(String[] args) {
        List<Integer> numeri = List.of(1,2,3,4,5,6,7,8,9,10);

        List<Integer> pari = numeri.stream()
                                  .filter(n -> n % 2 == 0)
                                  .collect(Collectors.toList());

        System.out.println(pari);
    }
}
```

---

## 6) Le operazioni più importanti (con esempi)

### 6.1 `filter(Predicate)`

Filtra elementi in base a una condizione.

```java
List<Integer> maggioriDi5 = numeri.stream()
    .filter(n -> n > 5)
    .collect(Collectors.toList());
```

---

### 6.2 `map(Function)`

Trasforma ogni elemento in un altro valore.

```java
List<Integer> doppi = numeri.stream()
    .map(n -> n * 2)
    .collect(Collectors.toList());
```

---

### 6.3 `sorted()` e `sorted(Comparator)`

Ordina gli elementi.

```java
List<Integer> ordinati = numeri.stream()
    .sorted()
    .collect(Collectors.toList());
```

Ordine decrescente con comparator:

```java
List<Integer> decrescente = numeri.stream()
    .sorted((a, b) -> Integer.compare(b, a))
    .collect(Collectors.toList());
```

---

### 6.4 `distinct()`

Rimuove duplicati (basato su `equals()` e `hashCode()`).

```java
List<Integer> senzaDuplicati = List.of(1,1,2,2,3,3).stream()
    .distinct()
    .collect(Collectors.toList());
```

---

### 6.5 `limit(n)` e `skip(n)`

```java
List<Integer> primi3 = numeri.stream()
    .limit(3)
    .collect(Collectors.toList());

List<Integer> dopo2 = numeri.stream()
    .skip(2)
    .collect(Collectors.toList());
```

---

### 6.6 `collect(...)`

Serve per convertire la Stream in una collezione o struttura finale.

```java
import java.util.*;
import java.util.stream.Collectors;

List<Integer> lista = numeri.stream().collect(Collectors.toList());
Set<Integer> set = numeri.stream().collect(Collectors.toSet());
```

---

### 6.7 `count()`

Conta gli elementi nella stream.

```java
long totale = numeri.stream().count();
```

---

### 6.8 `reduce(...)`

Riduce tutti gli elementi a uno solo (somma, prodotto, max, min, ecc.).

Somma:

```java
int somma = numeri.stream()
    .reduce(0, (a, b) -> a + b);
```

Prodotto:

```java
int prodotto = numeri.stream()
    .reduce(1, (a, b) -> a * b);
```

Max (con Optional):

```java
import java.util.Optional;

Optional<Integer> max = numeri.stream()
    .reduce(Integer::max);
```

---

## 7) Stream su oggetti (caso reale OOP)

Esempio classe:

```java
public class Student {
    private final String name;
    private final int voto;

    public Student(String name, int voto) {
        this.name = name;
        this.voto = voto;
    }

    public String getName() { return name; }
    public int getVoto() { return voto; }

    @Override
    public String toString() {
        return name + " (" + voto + ")";
    }
}
```

Uso:

```java
import java.util.*;
import java.util.stream.Collectors;

public class StreamOggetti {
    public static void main(String[] args) {
        List<Student> students = List.of(
            new Student("Luca", 7),
            new Student("Anna", 5),
            new Student("Marco", 9)
        );

        // solo promossi
        List<Student> promossi = students.stream()
            .filter(s -> s.getVoto() >= 6)
            .collect(Collectors.toList());

        // solo nomi
        List<String> nomi = students.stream()
            .map(Student::getName)
            .collect(Collectors.toList());

        System.out.println("Promossi: " + promossi);
        System.out.println("Nomi: " + nomi);
    }
}
```

---

## 8) Stream su Map

Esempio: filtrare entry in base al valore.

```java
import java.util.*;

public class StreamMap {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 5);
        map.put("B", 12);
        map.put("C", 20);

        map.entrySet().stream()
            .filter(e -> e.getValue() > 10)
            .forEach(e -> System.out.println(e.getKey() + " -> " + e.getValue()));
    }
}
```

---

## 9) `parallelStream()`

Puoi provare a parallelizzare:

```java
numeri.parallelStream()
      .map(n -> n * 2)
      .count();
```

⚠️ Attenzione:
- non sempre è più veloce (dipende da CPU, dimensione, costo operazioni)
- attenzione a side effects e operazioni non thread-safe
- `forEach` su parallel può non rispettare l’ordine

---

## 10) Vantaggi reali

- codice più leggibile (se non esageri)
- meno boilerplate (meno for/if ripetuti)
- stile moderno funzionale
- facile combinare pipeline di trasformazioni
- possibile parallelizzazione

---

## 11) Quando NON usarle

- quando la logica diventa troppo contorta e meno leggibile
- quando ti serve debug “step-by-step” molto semplice
- quando devi modificare strutture esterne in modo complicato (side effects)

> Regola pratica: **una Stream brutta è peggio di un `for` fatto bene**.

---

## 12) `forEach` Stream vs `for-each` classico

```java
// Stream
numeri.stream().forEach(System.out::println);
```

```java
// for classico
for (int n : numeri) {
    System.out.println(n);
}
```

- Il `for` classico è **imperativo** (come faccio a farlo).
- La Stream è **dichiarativa** (cosa voglio ottenere).

---

## 13) Concetto fondamentale

Le Stream ti fanno ragionare così:

**Non**: “Come faccio a ciclare?”  
**Ma**: “Cosa voglio ottenere?”

È un cambio mentale che ti porta a scrivere codice più pulito e “alto livello”.
