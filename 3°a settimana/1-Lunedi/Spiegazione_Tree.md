# Spiegazione completa dei Tree in Java

## Cos'è un Tree?

Un **Tree (albero)** è una struttura dati gerarchica composta da:

-   nodi (nodes)
-   collegamenti padre → figlio
-   una radice (root)

Esempio:

            10
           /  \
          5    20

-   10 è la root
-   5 e 20 sono figli
-   5 e 20 sono foglie (non hanno figli)

------------------------------------------------------------------------

# Tree Binario

Un **Binary Tree** è un albero in cui ogni nodo ha al massimo 2 figli:

-   figlio sinistro
-   figlio destro

------------------------------------------------------------------------

# Binary Search Tree (BST)

Un **Binary Search Tree** rispetta questa regola:

    Tutto a sinistra < nodo
    Tutto a destra > nodo

Esempio:

            10
           /  \
          5    20
         / \     \
        3   7     30

Ricerca del 7:

1.  Parti da 10 → 7 \< 10 → vai a sinistra
2.  Arrivi a 5 → 7 \> 5 → vai a destra
3.  Trovi 7

------------------------------------------------------------------------

# Complessità

Se l'albero è bilanciato:

    ricerca = O(log n)

Se è sbilanciato:

    ricerca = O(n)

------------------------------------------------------------------------

# TreeSet in Java

``` java
import java.util.TreeSet;
import java.util.Set;

Set<Integer> numeri = new TreeSet<>();

numeri.add(10);
numeri.add(5);
numeri.add(20);

System.out.println(numeri);
```

Output:

    [5, 10, 20]

TreeSet mantiene l'ordine automaticamente.

TreeSet usa internamente un **Red-Black Tree** (albero auto-bilanciato).

Garantisce complessità O(log n).

------------------------------------------------------------------------

# TreeMap in Java

``` java
import java.util.TreeMap;
import java.util.Map;

Map<String, Integer> map = new TreeMap<>();

map.put("Marco", 25);
map.put("Luca", 30);
map.put("Andrea", 20);

System.out.println(map);
```

Output ordinato per chiave:

    {Andrea=20, Luca=30, Marco=25}

------------------------------------------------------------------------

# Differenza Hash vs Tree

  Struttura   Ordinata   Complessità   Usa hash
  ----------- ---------- ------------- ----------
  HashMap     No         O(1) medio    Sì
  TreeMap     Sì         O(log n)      No
  HashSet     No         O(1) medio    Sì
  TreeSet     Sì         O(log n)      No

------------------------------------------------------------------------

# Quando usare Tree

Usa Tree quando:

-   vuoi dati sempre ordinati
-   vuoi elemento più piccolo
-   vuoi elemento più grande
-   vuoi range tra valori

Esempio:

``` java
TreeSet<Integer> set = new TreeSet<>();

set.add(10);
set.add(5);
set.add(20);

System.out.println(set.first());  // 5
System.out.println(set.last());   // 20
```

------------------------------------------------------------------------

# Struttura interna (concetto avanzato)

Un **Red-Black Tree**:

-   è un Binary Search Tree
-   ha regole di colore (rosso/nero)
-   garantisce altezza limitata
-   evita sbilanciamento

Questo impedisce casi peggiori O(n).

------------------------------------------------------------------------

# Confronto mentale

HashMap → cassetti numerati (veloce ma disordinato)

TreeMap → libreria ordinata alfabeticamente

------------------------------------------------------------------------

# Riassunto finale

Tree è una struttura:

-   gerarchica
-   basata su confronti (\< \>)
-   ordinata
-   complessità O(log n)

Hash è:

-   basato su hashCode()
-   non ordinato
-   complessità O(1) medio
