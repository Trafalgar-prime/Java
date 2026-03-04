# Collectors in Java -- Guida Completa e Approfondita

------------------------------------------------------------------------

# Collectors: cosa sono

collect(...) è l'operazione terminale che materializza una Stream in un
risultato finale.

Un Collector definisce: 1) che contenitore creare, 2) come accumulare
gli elementi, 3) come combinarli (utile in parallel), 4) quale risultato
finale restituire.

------------------------------------------------------------------------

# 1) Collectors base di collezione

## toList()

Cosa fa: Raccoglie tutti gli elementi in una List. È il collector più
comune quando vuoi salvare il risultato della pipeline.

Uso:

``` java
List<String> out = stream.collect(Collectors.toList());
```

Note: - Il tipo concreto non è garantito (spesso ArrayList). - Se vuoi
una lista specifica usa toCollection(...).

------------------------------------------------------------------------

## toUnmodifiableList() (Java 10+)

Cosa fa: Crea una lista immutabile (non modificabile).

Uso:

``` java
List<String> out = stream.collect(Collectors.toUnmodifiableList());
```

------------------------------------------------------------------------

## toSet()

Cosa fa: Raccoglie in un Set eliminando duplicati secondo
equals()/hashCode().

Uso:

``` java
Set<String> s = stream.collect(Collectors.toSet());
```

Note: - Non garantisce tipo né ordine.

------------------------------------------------------------------------

## toUnmodifiableSet() (Java 10+)

Cosa fa: Crea un Set immutabile.

Uso:

``` java
Set<String> s = stream.collect(Collectors.toUnmodifiableSet());
```

------------------------------------------------------------------------

## toCollection(Supplier`<C>`{=html})

Cosa fa: Permette di scegliere esplicitamente il tipo di collezione
finale.

Uso:

``` java
LinkedList<String> l = stream.collect(Collectors.toCollection(LinkedList::new));
TreeSet<String> t = stream.collect(Collectors.toCollection(TreeSet::new));
```

------------------------------------------------------------------------

# 2) Collectors per Map

## toMap(keyMapper, valueMapper)

Cosa fa: Costruisce una Map\<K,V\> trasformando ogni elemento in chiave
e valore.

Uso:

``` java
Map<Integer, String> map = books.stream()
    .collect(Collectors.toMap(Book::getId, Book::getTitle));
```

Nota: - Se esistono chiavi duplicate → IllegalStateException.

------------------------------------------------------------------------

## toMap(keyMapper, valueMapper, mergeFunction)

Cosa fa: Gestisce chiavi duplicate specificando come unire i valori.

Uso:

``` java
Map<Integer, String> map = stream.collect(Collectors.toMap(
    x -> x.getId(),
    x -> x.getName(),
    (v1, v2) -> v1
));
```

Esempio somma:

``` java
Map<String, Integer> map = stream.collect(Collectors.toMap(
    x -> x.getKey(),
    x -> x.getAmount(),
    Integer::sum
));
```

------------------------------------------------------------------------

## toMap(keyMapper, valueMapper, mergeFunction, mapSupplier)

Cosa fa: Oltre al merge, consente di scegliere l'implementazione della
Map.

Uso:

``` java
Map<Integer, String> map = stream.collect(Collectors.toMap(
    x -> x.getId(),
    x -> x.getName(),
    (a,b) -> a,
    TreeMap::new
));
```

------------------------------------------------------------------------

## toUnmodifiableMap() (Java 10+)

Cosa fa: Crea una Map immutabile.

Uso:

``` java
Map<Integer, String> map =
    stream.collect(Collectors.toUnmodifiableMap(Book::getId, Book::getTitle));
```

------------------------------------------------------------------------

# 3) Collectors per Stringhe

## joining()

Cosa fa: Concatena elementi in un'unica stringa.

Uso:

``` java
String s = stream.collect(Collectors.joining());
```

------------------------------------------------------------------------

## joining(delimiter)

Cosa fa: Concatena con separatore.

Uso:

``` java
String s = names.stream().collect(Collectors.joining(", "));
```

------------------------------------------------------------------------

## joining(delimiter, prefix, suffix)

Cosa fa: Concatena con separatore, prefisso e suffisso.

Uso:

``` java
String s = names.stream().collect(Collectors.joining(", ", "[", "]"));
```

------------------------------------------------------------------------

# 4) Raggruppamento e Partizione

## groupingBy(classifier)

Cosa fa: Raggruppa elementi per chiave → Map\<K, List`<T>`{=html}\>.

Uso:

``` java
Map<String, List<Book>> byAuthor =
    books.stream().collect(Collectors.groupingBy(Book::getAuthor));
```

------------------------------------------------------------------------

## groupingBy(classifier, downstream)

Cosa fa: Raggruppa applicando un collector interno.

Uso:

``` java
Map<String, Long> countByAuthor =
    books.stream().collect(Collectors.groupingBy(
        Book::getAuthor,
        Collectors.counting()
    ));
```

------------------------------------------------------------------------

## groupingBy(classifier, mapFactory, downstream)

Cosa fa: Permette di scegliere tipo Map.

Uso:

``` java
Map<String, Long> m = books.stream().collect(
    Collectors.groupingBy(
        Book::getAuthor,
        TreeMap::new,
        Collectors.counting()
    )
);
```

------------------------------------------------------------------------

## partitioningBy(predicate)

Cosa fa: Divide in due gruppi (true/false).

Uso:

``` java
Map<Boolean, List<Book>> part =
    books.stream().collect(Collectors.partitioningBy(b -> b.getPrice() > 20));
```

------------------------------------------------------------------------

## partitioningBy(predicate, downstream)

Cosa fa: Partiziona applicando un collector interno.

Uso:

``` java
Map<Boolean, Long> counts =
    books.stream().collect(Collectors.partitioningBy(
        b -> b.getYear() >= 2000,
        Collectors.counting()
    ));
```

------------------------------------------------------------------------

# 5) Collectors numerici

## counting()

Cosa fa: Conta elementi.

Uso:

``` java
long c = stream.collect(Collectors.counting());
```

------------------------------------------------------------------------

## summingInt / summingLong / summingDouble

Cosa fa: Somma un campo numerico.

Uso:

``` java
int total = students.stream()
    .collect(Collectors.summingInt(Student::getVoto));
```

------------------------------------------------------------------------

## averagingInt / averagingLong / averagingDouble

Cosa fa: Calcola media di un campo.

Uso:

``` java
double avg = students.stream()
    .collect(Collectors.averagingInt(Student::getVoto));
```

------------------------------------------------------------------------

## summarizingInt / summarizingLong / summarizingDouble

Cosa fa: Restituisce statistiche complete (count, sum, min, max,
average).

Uso:

``` java
IntSummaryStatistics stats = students.stream()
    .collect(Collectors.summarizingInt(Student::getVoto));
```

------------------------------------------------------------------------

# 6) Min e Max come Collector

## minBy(Comparator)

Cosa fa: Trova minimo (Optional).

Uso:

``` java
Optional<Book> cheapest =
    books.stream().collect(Collectors.minBy(
        Comparator.comparingDouble(Book::getPrice)
    ));
```

------------------------------------------------------------------------

## maxBy(Comparator)

Cosa fa: Trova massimo (Optional).

Uso:

``` java
Optional<Book> mostExpensive =
    books.stream().collect(Collectors.maxBy(
        Comparator.comparingDouble(Book::getPrice)
    ));
```

------------------------------------------------------------------------

# 7) Collectors trasformazione avanzata

## mapping(mapper, downstream)

Cosa fa: Mappa elementi prima di applicare downstream.

Uso:

``` java
Map<String, List<String>> namesByCity =
    users.stream().collect(Collectors.groupingBy(
        User::getCity,
        Collectors.mapping(User::getName, Collectors.toList())
    ));
```

------------------------------------------------------------------------

## collectingAndThen(downstream, finisher)

Cosa fa: Esegue un collect e poi applica trasformazione finale.

Uso:

``` java
List<String> out = stream.collect(Collectors.collectingAndThen(
    Collectors.toList(),
    List::copyOf
));
```

------------------------------------------------------------------------

# 8) Collectors avanzati

## reducing(...)

Cosa fa: Riduce in forma collector (utile in grouping).

Uso:

``` java
int sum = nums.stream()
    .collect(Collectors.reducing(0, x -> x, Integer::sum));
```

------------------------------------------------------------------------

## filtering(predicate, downstream) (Java 9+)

Cosa fa: Filtra dentro grouping o partition.

Uso:

``` java
Map<String, List<Book>> recentByAuthor =
    books.stream().collect(Collectors.groupingBy(
        Book::getAuthor,
        Collectors.filtering(
            b -> b.getYear() >= 2000,
            Collectors.toList()
        )
    ));
```

------------------------------------------------------------------------

## flatMapping(mapper, downstream) (Java 9+)

Cosa fa: Appiattisce stream interne dentro grouping.

Uso:

``` java
Map<String, Set<String>> tagsByAuthor =
    books.stream().collect(Collectors.groupingBy(
        Book::getAuthor,
        Collectors.flatMapping(
            b -> b.getTags().stream(),
            Collectors.toSet()
        )
    ));
```

------------------------------------------------------------------------

## teeing(c1, c2, merger) (Java 12+)

Cosa fa: Esegue due collector in parallelo e unisce i risultati.

Uso:

``` java
var result = nums.stream().collect(Collectors.teeing(
    Collectors.minBy(Integer::compareTo),
    Collectors.maxBy(Integer::compareTo),
    (min, max) -> Map.of("min", min, "max", max)
));
```

------------------------------------------------------------------------

# 9) Esempio Completo

``` java
import java.util.*;
import java.util.stream.*;

class Book {
    private final String author;
    private final String title;
    private final int year;
    private final double price;

    public Book(String author, String title, int year, double price) {
        this.author = author;
        this.title = title;
        this.year = year;
        this.price = price;
    }

    public String getAuthor() { return author; }
    public String getTitle() { return title; }
    public int getYear() { return year; }
    public double getPrice() { return price; }
}

public class CollectorsEsempio {
    public static void main(String[] args) {
        List<Book> books = List.of(
            new Book("A", "X", 1999, 10.0),
            new Book("A", "Y", 2005, 20.0),
            new Book("B", "Z", 2010, 30.0)
        );

        Map<String, Long> countByAuthor =
            books.stream().collect(Collectors.groupingBy(
                Book::getAuthor,
                Collectors.counting()
            ));

        Map<String, List<String>> titlesByAuthor =
            books.stream().collect(Collectors.groupingBy(
                Book::getAuthor,
                Collectors.mapping(Book::getTitle, Collectors.toList())
            ));

        Map<String, Double> sumPriceByAuthor =
            books.stream().collect(Collectors.groupingBy(
                Book::getAuthor,
                Collectors.summingDouble(Book::getPrice)
            ));

        System.out.println(countByAuthor);
        System.out.println(titlesByAuthor);
        System.out.println(sumPriceByAuthor);
    }
}
```
