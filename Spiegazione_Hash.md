# Spiegazione completa di Hash in Java

## Cos'è un Hash?

Un **hash** è una funzione che trasforma un oggetto in un numero intero.

    oggetto → numero intero

In Java questo numero viene generato dal metodo:

``` java
hashCode()
```

### Esempio

``` java
String nome = "Marco";
System.out.println(nome.hashCode());
```

Output (esempio):

    74113942

Quel numero è l'hash.

------------------------------------------------------------------------

## A cosa serve l'hash?

Serve per: - trovare velocemente un oggetto - salvarlo in una struttura
dati - evitare ricerche lente

Invece di scorrere tutto (O(n)), si usa l'hash per andare direttamente
nella posizione giusta.

------------------------------------------------------------------------

# Hash e Set

## HashSet

``` java
Set<String> set = new HashSet<>();
```

Quando fai:

``` java
set.add("Marco");
```

Succede questo:

1.  Java calcola `"Marco".hashCode()`
2.  Usa quel numero per decidere in quale "bucket" metterlo
3.  Se nello stesso bucket c'è già qualcosa, controlla con `equals()`

### Perché Set non permette duplicati?

Perché:

-   stesso oggetto → stesso hash
-   stesso hash + equals() true → duplicato

E quindi lo ignora.

------------------------------------------------------------------------

# Hash e Map

## HashMap

``` java
Map<String, Integer> map = new HashMap<>();
```

Quando fai:

``` java
map.put("Marco", 25);
```

Succede:

1.  Java calcola hash della chiave `"Marco"`
2.  Trova il bucket
3.  Se la chiave esiste già (equals), sovrascrive
4.  Se non esiste, inserisce

⚠️ L'hash riguarda solo la chiave, NON il valore.

------------------------------------------------------------------------

# Hash e List

## ArrayList NON usa hash

``` java
List<String> list = new ArrayList<>();
```

ArrayList funziona come un array dinamico.

Quando fai:

``` java
list.contains("Marco");
```

Java scorre tutta la lista:

    confronto 1
    confronto 2
    confronto 3
    ...

Costo: O(n)

La List: - mantiene ordine - permette duplicati - si accede per indice

Non ha bisogno di hash.

------------------------------------------------------------------------

# Differenza strutturale

  Struttura   Usa Hash?   Per cosa?
  ----------- ----------- ------------------------------
  ArrayList   No          accesso per indice
  HashSet     Sì          trovare ed evitare duplicati
  HashMap     Sì          trovare chiavi velocemente

------------------------------------------------------------------------

# Come funziona internamente

Immagina una tabella:

    Indice: 0
    Indice: 1
    Indice: 2
    Indice: 3
    Indice: 4

L'hash decide in quale indice salvare l'oggetto.

Esempio:

    hash("Marco") % 5 = 2

Va nella posizione 2.

Se due oggetti danno lo stesso indice → collisione.

Java allora: - li mette in una lista interna - controlla con equals()

------------------------------------------------------------------------

# Perché equals() è importantissimo

Se crei una classe tua:

``` java
class Person {
    String name;
}
```

E la metti in un HashSet:

``` java
Set<Person> people = new HashSet<>();
```

Se non override: - hashCode() - equals()

Il Set NON funziona correttamente.

------------------------------------------------------------------------

# Esempio corretto

``` java
class Person {
    String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Person)) return false;
        Person other = (Person) obj;
        return this.name.equals(other.name);
    }
}
```

Ora il Set capisce se due persone sono uguali.

------------------------------------------------------------------------

# Perché hash è veloce?

Senza hash:

    ricerca = O(n)

Con hash:

    ricerca = O(1) medio

Se hai 1.000.000 elementi:

ArrayList: → potrebbe fare 1.000.000 confronti

HashMap: → quasi diretto

------------------------------------------------------------------------

# Quando scegliere Hash

Usa:

-   HashSet → quando ti serve unicità e velocità
-   HashMap → quando vuoi associazione chiave-valore veloce

Non usare hash quando: - l'ordine è fondamentale (usa LinkedHashMap o
TreeMap) - vuoi indicizzazione per posizione (usa List)

------------------------------------------------------------------------

# Conclusione

Hash serve a:

> Trasformare un oggetto in un numero per trovare velocemente la sua
> posizione in memoria.
