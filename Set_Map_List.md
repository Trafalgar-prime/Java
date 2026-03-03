# Differenza tra Set, Map e List in Java

## 1) LIST → Collezione ordinata con duplicati

Una **List**: - mantiene l'ordine di inserimento - permette elementi
duplicati - si accede con un indice (0,1,2,...)

### Quando usarla?

Quando l'ordine è importante. Esempio: classifica, sequenza di mosse,
elenco studenti.

### Implementazioni principali

-   ArrayList
-   LinkedList

### Esempio con ArrayList

``` java
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<String> nomi = new ArrayList<>();

        nomi.add("Marco");
        nomi.add("Luca");
        nomi.add("Marco");  // duplicato permesso

        System.out.println(nomi);

        System.out.println(nomi.get(0)); // accesso per indice
    }
}
```

Output:

    [Marco, Luca, Marco]
    Marco

### Dichiarazione

``` java
List<String> lista = new ArrayList<>();
```

-   List è l'interfaccia
-   ArrayList è l'implementazione concreta

------------------------------------------------------------------------

## 2) SET → Collezione senza duplicati

Un **Set**: - NON permette duplicati - non garantisce sempre l'ordine
(dipende dall'implementazione) - non ha indice

### Quando usarlo?

Quando vuoi solo elementi unici. Esempio: email, numeri estratti, codici
fiscali.

### Implementazioni principali

-   HashSet → veloce, nessun ordine garantito
-   LinkedHashSet → mantiene ordine inserimento
-   TreeSet → ordinato automaticamente

### Esempio con HashSet

``` java
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Set<String> nomi = new HashSet<>();

        nomi.add("Marco");
        nomi.add("Luca");
        nomi.add("Marco"); // ignorato

        System.out.println(nomi);
    }
}
```

Output possibile:

    [Marco, Luca]

### Dichiarazione

``` java
Set<String> insieme = new HashSet<>();
```

-   Set è l'interfaccia
-   HashSet è l'implementazione

------------------------------------------------------------------------

## 3) MAP → Struttura chiave → valore

La **Map** associa una chiave a un valore.

Esempio:

    "Marco" → 25
    "Luca" → 30

### Quando usarla?

Quando vuoi associare qualcosa a qualcos'altro. Esempi: - nome → età -
ID → oggetto - username → password

### Implementazioni principali

-   HashMap
-   LinkedHashMap
-   TreeMap

### Esempio con HashMap

``` java
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String, Integer> eta = new HashMap<>();

        eta.put("Marco", 25);
        eta.put("Luca", 30);

        System.out.println(eta.get("Marco")); // 25
    }
}
```

### Dichiarazione

``` java
Map<String, Integer> mappa = new HashMap<>();
```

-   Map è l'interfaccia
-   HashMap è l'implementazione

------------------------------------------------------------------------

## Differenza Riassunta

  Struttura   Duplicati      Ordine    Accesso
  ----------- -------------- --------- ---------------
  List        sì             sì        indice
  Set         no             dipende   nessun indice
  Map         chiave unica   dipende   per chiave

------------------------------------------------------------------------

## Esempio pratico (torneo)

-   List → elenco squadre in ordine di classifica
-   Set → squadre qualificate (senza doppioni)
-   Map → squadra → punteggio

``` java
List<String> classifica = new ArrayList<>();
Set<String> qualificate = new HashSet<>();
Map<String, Integer> punteggi = new HashMap<>();

classifica.add("Lazio");
classifica.add("Roma");

qualificate.add("Lazio");
qualificate.add("Lazio"); // ignorato

punteggi.put("Lazio", 30);
punteggi.put("Roma", 25);
```
