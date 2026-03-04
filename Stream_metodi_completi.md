# Stream in Java -- Operazioni Intermedie e Terminali (Guida Approfondita)

------------------------------------------------------------------------

# 1) Operazioni Intermedie (Intermediate)

Le operazioni intermedie restituiscono una nuova Stream e sono *lazy*.
Vengono eseguite realmente solo quando si chiama una operazione
terminale.

## filter(Predicate\<? super T\>)

Fa: Scorre gli elementi e mantiene solo quelli che soddisfano una
condizione booleana. È la base per selezionare dati senza usare cicli
for con if interni. Uso: stream.filter(x -\> condizione) Opzioni/Note: -
Le Predicate possono essere combinate con and(), or(), negate(). -
Evitare null nella pipeline.

## map(Function\<? super T, ? extends R\>)

Fa: Trasforma ogni elemento in un altro valore, anche di tipo diverso.
Uso: stream.map(x -\> trasformazione) Opzioni/Note: - Spesso più
leggibile con method reference: map(Book::getTitle).

## mapToInt(ToIntFunction\<? super T\>)

Fa: Converte una Stream`<T>`{=html} in IntStream per operazioni
numeriche efficienti. Uso: stream.mapToInt(obj -\> obj.getYear())
Opzioni: - Esistono anche mapToLong e mapToDouble.

## mapToLong(ToLongFunction\<? super T\>)

Fa: Converte in LongStream per calcoli su long. Uso: stream.mapToLong(o
-\> o.getId())

## mapToDouble(ToDoubleFunction\<? super T\>)

Fa: Converte in DoubleStream per calcoli su double. Uso:
stream.mapToDouble(o -\> o.getPrice())

## flatMap(Function\<? super T, ? extends Stream\<? extends R\>\>)

Fa: Appiattisce una struttura annidata (lista di liste → lista unica).
Uso: stream.flatMap(x -\> x.getList().stream()) Note: - Evitare null.

## flatMapToInt / flatMapToLong / flatMapToDouble

Fa: Versioni per primitive stream. Uso: stream.flatMapToInt(x -\>
Arrays.stream(x.getArray()))

## distinct()

Fa: Elimina duplicati usando equals() e hashCode(). Uso:
stream.distinct() Note: - equals/hashCode devono essere corretti.

## sorted()

Fa: Ordina secondo ordine naturale (Comparable). Uso: stream.sorted()

## sorted(Comparator)

Fa: Ordina secondo regola personalizzata. Uso:
stream.sorted(Comparator.comparing(Book::getYear)) Note: - Possibile
usare reversed() e thenComparing().

## peek(Consumer)

Fa: Permette di osservare gli elementi per debug senza modificarli. Uso:
stream.peek(System.out::println) Note: - Non usarlo per logica di
business.

## limit(long)

Fa: Prende solo i primi N elementi. Uso: stream.limit(10)

## skip(long)

Fa: Salta i primi N elementi. Uso: stream.skip(5)

## takeWhile(Predicate) (Java 9+)

Fa: Prende elementi finché la condizione è vera. Uso: stream.takeWhile(x
-\> x \< 10)

## dropWhile(Predicate) (Java 9+)

Fa: Scarta elementi iniziali finché la condizione è vera. Uso:
stream.dropWhile(x -\> x \< 10)

## unordered()

Fa: Rimuove vincolo di ordine per possibili ottimizzazioni. Uso:
stream.unordered()

------------------------------------------------------------------------

# 2) Operazioni Terminali (Terminal)

Le operazioni terminali consumano la Stream e producono un risultato
finale.

## forEach(Consumer)

Fa: Esegue un'azione su ogni elemento (side-effect). Uso:
stream.forEach(System.out::println) Note: - In parallel non garantisce
ordine.

## forEachOrdered(Consumer)

Fa: Come forEach ma rispetta l'ordine. Uso:
stream.forEachOrdered(System.out::println)

## toArray()

Fa: Converte in Object\[\]. Uso: Object\[\] arr = stream.toArray()

## toArray(IntFunction)

Fa: Converte in array tipizzato. Uso: String\[\] arr =
stream.toArray(String\[\]::new)

## collect(Collector)

Fa: Raccoglie risultati in una struttura finale (List, Set, Map, ecc.).
Uso: stream.collect(Collectors.toList())

## collect(Supplier, BiConsumer, BiConsumer)

Fa: Versione manuale di collect. Uso: stream.collect(ArrayList::new,
List::add, List::addAll)

## reduce(BinaryOperator)

Fa: Combina tutti gli elementi in uno solo (Optional). Uso:
stream.reduce((a,b) -\> a+b)

## reduce(identity, BinaryOperator)

Fa: Riduce con valore iniziale. Uso: stream.reduce(0, Integer::sum)

## reduce(identity, accumulator, combiner)

Fa: Riduzione avanzata per parallel. Uso: stream.reduce(id, accumulator,
combiner)

## min(Comparator)

Fa: Trova minimo secondo comparator (Optional). Uso:
stream.min(Comparator.naturalOrder())

## max(Comparator)

Fa: Trova massimo secondo comparator. Uso:
stream.max(Comparator.comparing(Book::getPrice))

## count()

Fa: Conta elementi dopo pipeline. Uso: long c = stream.count()

## anyMatch(Predicate)

Fa: Verifica se almeno uno soddisfa condizione (short-circuit). Uso:
stream.anyMatch(x -\> x \> 10)

## allMatch(Predicate)

Fa: Verifica se tutti soddisfano condizione. Uso: stream.allMatch(x -\>
x != null)

## noneMatch(Predicate)

Fa: Verifica che nessuno soddisfi condizione. Uso: stream.noneMatch(x
-\> x \< 0)

## findFirst()

Fa: Restituisce primo elemento (Optional). Uso: stream.findFirst()

## findAny()

Fa: Restituisce un elemento qualsiasi (più efficiente in parallel). Uso:
stream.findAny()

## iterator()

Fa: Restituisce Iterator consumando la Stream. Uso: Iterator`<T>`{=html}
it = stream.iterator()

## spliterator()

Fa: Restituisce Spliterator (supporto parallel). Uso:
Spliterator`<T>`{=html} sp = stream.spliterator()

------------------------------------------------------------------------

# 3) Terminali Primitive Streams

## sum()

Fa: Somma valori primitivi senza boxing. Uso: IntStream.of(1,2,3).sum()

## average()

Fa: Calcola media (OptionalDouble). Uso: IntStream.of(1,2,3).average()

## summaryStatistics()

Fa: Restituisce count, sum, min, max, average in un unico oggetto. Uso:
IntStream.of(1,2,3).summaryStatistics()

------------------------------------------------------------------------
