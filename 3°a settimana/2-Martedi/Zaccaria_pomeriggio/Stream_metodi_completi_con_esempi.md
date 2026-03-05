
# Stream in Java – Operazioni Intermedie e Terminali (Guida Approfondita con Esempi)

---

# 1) Operazioni Intermedie (Intermediate)

Le operazioni intermedie restituiscono una nuova Stream e sono *lazy*.
Vengono eseguite realmente solo quando si chiama una operazione terminale.

---

## filter(Predicate<? super T>)

Fa:
Scorre gli elementi della stream e mantiene solo quelli che soddisfano una condizione booleana.

Uso:
stream.filter(x -> x > 5)

Esempio:
```java
List<Integer> numbers = List.of(1,2,3,6,7);

numbers.stream()
       .filter(n -> n > 5)
       .forEach(System.out::println);
```

---

## map(Function<? super T, ? extends R>)

Fa:
Trasforma ogni elemento della stream in un nuovo valore.

Uso:
stream.map(x -> x * 2)

Esempio:
```java
List<Integer> numbers = List.of(1,2,3);

numbers.stream()
       .map(n -> n * 2)
       .forEach(System.out::println);
```

---

## mapToInt(ToIntFunction<? super T>)

Fa:
Converte una Stream<T> in IntStream per operazioni numeriche efficienti.

Esempio:
```java
List<String> words = List.of("Java","Stream","API");

int totalLength = words.stream()
        .mapToInt(String::length)
        .sum();

System.out.println(totalLength);
```

---

## mapToLong(ToLongFunction<? super T>)

Fa:
Converte gli elementi della stream in long.

Esempio:
```java
List<Integer> numbers = List.of(1,2,3);

long sum = numbers.stream()
        .mapToLong(n -> n)
        .sum();

System.out.println(sum);
```

---

## mapToDouble(ToDoubleFunction<? super T>)

Fa:
Converte gli elementi in double.

Esempio:
```java
List<Integer> numbers = List.of(1,2,3);

double avg = numbers.stream()
        .mapToDouble(n -> n)
        .average()
        .orElse(0);

System.out.println(avg);
```

---

## flatMap(Function<? super T, Stream<R>>)

Fa:
Trasforma ogni elemento in una stream e poi appiattisce il risultato.

Esempio:
```java
List<List<Integer>> list = List.of(
        List.of(1,2),
        List.of(3,4)
);

list.stream()
    .flatMap(l -> l.stream())
    .forEach(System.out::println);
```

---

## distinct()

Fa:
Rimuove i duplicati dalla stream.

Esempio:
```java
List<Integer> numbers = List.of(1,1,2,2,3);

numbers.stream()
       .distinct()
       .forEach(System.out::println);
```

---

## sorted()

Fa:
Ordina gli elementi secondo l'ordine naturale.

Esempio:
```java
List<Integer> numbers = List.of(5,2,9,1);

numbers.stream()
       .sorted()
       .forEach(System.out::println);
```

---

## sorted(Comparator)

Fa:
Ordina secondo una regola personalizzata.

Esempio:
```java
List<String> names = List.of("Marco","Anna","Luca");

names.stream()
     .sorted((a,b) -> b.compareTo(a))
     .forEach(System.out::println);
```

---

## peek(Consumer)

Fa:
Permette di osservare gli elementi mentre passano nella pipeline.

Esempio:
```java
List<Integer> numbers = List.of(1,2,3);

numbers.stream()
       .peek(n -> System.out.println("Elemento: " + n))
       .map(n -> n * 2)
       .forEach(System.out::println);
```

---

## limit(long)

Fa:
Prende solo i primi N elementi della stream.

Esempio:
```java
java.util.stream.Stream.iterate(1, n -> n + 1)
      .limit(5)
      .forEach(System.out::println);
```

---

## skip(long)

Fa:
Salta i primi N elementi.

Esempio:
```java
List<Integer> numbers = List.of(1,2,3,4,5);

numbers.stream()
       .skip(2)
       .forEach(System.out::println);
```

---

## takeWhile(Predicate)

Fa:
Prende elementi finché la condizione è vera.

Esempio:
```java
List<Integer> numbers = List.of(1,2,3,10,4);

numbers.stream()
       .takeWhile(n -> n < 5)
       .forEach(System.out::println);
```

---

## dropWhile(Predicate)

Fa:
Scarta elementi iniziali finché la condizione è vera.

Esempio:
```java
List<Integer> numbers = List.of(1,2,3,10,4);

numbers.stream()
       .dropWhile(n -> n < 5)
       .forEach(System.out::println);
```

---

## unordered()

Fa:
Rimuove il vincolo di ordine per possibili ottimizzazioni.

Esempio:
```java
List<Integer> numbers = List.of(1,2,3,4);

numbers.stream()
       .unordered()
       .parallel()
       .forEach(System.out::println);
```

---

# 2) Operazioni Terminali

## forEach(Consumer)

Fa:
Esegue un'azione per ogni elemento.

Esempio:
```java
List<String> names = List.of("Anna","Luca","Marco");

names.stream()
     .forEach(System.out::println);
```

---

## forEachOrdered(Consumer)

Fa:
Come forEach ma mantiene l'ordine.

Esempio:
```java
List<Integer> numbers = List.of(1,2,3,4);

numbers.parallelStream()
       .forEachOrdered(System.out::println);
```

---

## toArray()

Fa:
Converte la stream in array.

Esempio:
```java
Object[] arr = List.of(1,2,3)
        .stream()
        .toArray();
```

---

## toArray(IntFunction)

Fa:
Converte la stream in array tipizzato.

Esempio:
```java
String[] arr = List.of("A","B","C")
        .stream()
        .toArray(String[]::new);
```

---

## collect(Collector)

Fa:
Raccoglie i risultati in una collezione.

Esempio:
```java
List<Integer> result =
        List.of(1,2,3).stream()
        .collect(java.util.stream.Collectors.toList());
```

---

## reduce(BinaryOperator)

Fa:
Riduce tutti gli elementi a uno solo.

Esempio:
```java
int sum = List.of(1,2,3,4)
        .stream()
        .reduce(0, Integer::sum);

System.out.println(sum);
```

---

## min(Comparator)

Fa:
Trova l'elemento minimo.

Esempio:
```java
int min = List.of(3,1,4)
        .stream()
        .min(Integer::compare)
        .orElse(0);
```

---

## max(Comparator)

Fa:
Trova l'elemento massimo.

Esempio:
```java
int max = List.of(3,1,4)
        .stream()
        .max(Integer::compare)
        .orElse(0);
```

---

## count()

Fa:
Conta il numero di elementi.

Esempio:
```java
long count = List.of(1,2,3,4)
        .stream()
        .count();
```

---

## anyMatch(Predicate)

Fa:
Verifica se almeno un elemento soddisfa la condizione.

Esempio:
```java
boolean result = List.of(1,2,3)
        .stream()
        .anyMatch(n -> n > 2);
```

---

## allMatch(Predicate)

Fa:
Verifica se tutti gli elementi soddisfano la condizione.

Esempio:
```java
boolean result = List.of(2,4,6)
        .stream()
        .allMatch(n -> n % 2 == 0);
```

---

## noneMatch(Predicate)

Fa:
Verifica che nessun elemento soddisfi la condizione.

Esempio:
```java
boolean result = List.of(1,3,5)
        .stream()
        .noneMatch(n -> n % 2 == 0);
```

---

## findFirst()

Fa:
Restituisce il primo elemento della stream.

Esempio:
```java
int first = List.of(10,20,30)
        .stream()
        .findFirst()
        .orElse(0);
```

---

## findAny()

Fa:
Restituisce un elemento qualsiasi della stream.

Esempio:
```java
int any = List.of(10,20,30)
        .stream()
        .findAny()
        .orElse(0);
```

---

# 3) Primitive Stream Terminali

## sum()

Fa:
Somma tutti gli elementi.

Esempio:
```java
int sum = java.util.stream.IntStream.of(1,2,3)
        .sum();
```

---

## average()

Fa:
Calcola la media.

Esempio:
```java
double avg = java.util.stream.IntStream.of(1,2,3)
        .average()
        .orElse(0);
```

---

## summaryStatistics()

Fa:
Restituisce statistiche complete.

Esempio:
```java
java.util.IntSummaryStatistics stats =
        java.util.stream.IntStream.of(1,2,3,4)
        .summaryStatistics();

System.out.println(stats.getAverage());
```
