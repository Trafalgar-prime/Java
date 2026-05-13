# Collections in Java: lista e utilizzi

Certo. Qui parliamo delle Collections di Java, cioè le strutture dati già pronte che Java ti mette a disposizione per salvare gruppi di oggetti.

Il package principale è:

```java
java.util
```

Esempio di import generale:

```java
import java.util.*;
```

## 1. Schema generale delle Collections

In Java hai principalmente queste grandi famiglie:

```text
Collection
├── List
│   ├── ArrayList
│   ├── LinkedList
│   ├── Vector
│   └── Stack
│
├── Set
│   ├── HashSet
│   ├── LinkedHashSet
│   └── TreeSet
│
└── Queue
    ├── LinkedList
    ├── PriorityQueue
    ├── ArrayDeque
    └── Deque

Map
├── HashMap
├── LinkedHashMap
├── TreeMap
├── Hashtable
└── ConcurrentHashMap
```

Attenzione: Map non estende direttamente Collection, però fa comunque parte del Java Collections Framework.

## 2. List

Una List è una collezione ordinata.

Significa che gli elementi hanno una posizione:

```text
indice 0
indice 1
indice 2
indice 3
```

Una List può contenere duplicati.

Esempio:

```java
List<String> nomi = new ArrayList<>();

nomi.add("Luca");
nomi.add("Marco");
nomi.add("Luca");
```

Qui "Luca" compare due volte, ed è permesso.

## 3. ArrayList

ArrayList è probabilmente la lista più usata.

Internamente funziona come un array dinamico.

Esempio:

```java
ArrayList<String> nomi = new ArrayList<>();

nomi.add("Luca");
nomi.add("Marco");
nomi.add("Anna");

System.out.println(nomi.get(0));
```

Output:

```text
Luca
```

### Come funziona

ArrayList salva gli elementi in ordine.

Puoi accedere velocemente agli elementi tramite indice:

```java
nomi.get(0);
nomi.get(1);
nomi.get(2);
```

### Quando usarla

Usa ArrayList quando:

- vuoi una lista normale;
- vuoi accedere spesso agli elementi con get(indice);
- aggiungi elementi soprattutto alla fine;
- vuoi una struttura semplice e veloce.

### Caratteristiche

| Caratteristica | ArrayList |
|---|---|
| Mantiene ordine | sì |
| Accetta duplicati | sì |
| Accesso per indice | veloce |
| Inserimento in mezzo | meno veloce |
| Rimozione in mezzo | meno veloce |

## 4. LinkedList

LinkedList è una lista collegata.

Ogni elemento conosce l’elemento precedente e quello successivo.

Schema semplificato:

```text
[Luca] <-> [Marco] <-> [Anna]
```

Esempio:

```java
LinkedList<String> nomi = new LinkedList<>();

nomi.add("Luca");
nomi.add("Marco");
nomi.add("Anna");

nomi.addFirst("Primo");
nomi.addLast("Ultimo");

System.out.println(nomi);
```

### Come funziona

A differenza di ArrayList, LinkedList non è basata su un array interno.

È basata su nodi collegati tra loro.

Ogni nodo contiene:

- valore
- riferimento al nodo precedente
- riferimento al nodo successivo

### Quando usarla

Usa LinkedList quando:

- devi aggiungere spesso all’inizio;
- devi rimuovere spesso all’inizio;
- vuoi usarla anche come coda;
- vuoi usare metodi come addFirst(), addLast(), removeFirst().

### Caratteristiche

| Caratteristica | LinkedList |
|---|---|
| Mantiene ordine | sì |
| Accetta duplicati | sì |
| Accesso per indice | meno veloce |
| Inserimento all’inizio | veloce |
| Rimozione all’inizio | veloce |
| Può fare da Queue/Deque | sì |

## 5. Differenza tra ArrayList e LinkedList

### ArrayList

```java
List<String> lista = new ArrayList<>();
```

Meglio quando vuoi leggere spesso tramite indice.

```java
lista.get(500);
```

è veloce.

### LinkedList

```java
List<String> lista = new LinkedList<>();
```

Meglio quando vuoi aggiungere o togliere spesso all’inizio o alla fine.

```java
lista.addFirst("Luca");
lista.removeFirst();
```

In pratica:

```text
ArrayList → scelta standard
LinkedList → utile per code, stack, inserimenti/rimozioni ai bordi
```

## 6. Vector

Vector è una lista simile ad ArrayList, ma più vecchia.

Esempio:

```java
Vector<String> nomi = new Vector<>();

nomi.add("Luca");
nomi.add("Marco");
```

### Come funziona

È simile ad ArrayList, ma i suoi metodi sono sincronizzati.

Significa che è pensata per essere più sicura in contesti con più thread.

Però oggi si usa poco.

### Quando usarla

Quasi mai nei progetti moderni.

Di solito si preferisce:

```java
ArrayList
```

oppure strutture concorrenti più moderne.

## 7. Stack

Stack rappresenta una pila.

Funziona con logica:

```text
LIFO
Last In, First Out
```

Cioè:

l’ultimo elemento inserito è il primo che esce.

Esempio:

```java
Stack<String> pila = new Stack<>();

pila.push("A");
pila.push("B");
pila.push("C");

System.out.println(pila.pop());
```

Output:

```text
C
```

### Metodi principali

```java
push()
pop()
peek()
```

push() inserisce.

pop() rimuove e restituisce l’ultimo elemento.

peek() legge l’ultimo elemento senza rimuoverlo.

### Quando usarlo

Stack esiste, ma oggi spesso si preferisce usare:

```java
ArrayDeque
```

come stack moderno.

## 8. Set

Un Set è una collezione che non accetta duplicati.

Esempio:

```java
Set<String> nomi = new HashSet<>();

nomi.add("Luca");
nomi.add("Marco");
nomi.add("Luca");

System.out.println(nomi);
```

"Luca" viene salvato una sola volta.

### Caratteristiche generali

| Caratteristica | Set |
|---|---|
| Accetta duplicati | no |
| Accesso per indice | no |
| Utile per eliminare duplicati | sì |

## 9. HashSet

HashSet è il set più usato.

Esempio:

```java
HashSet<String> nomi = new HashSet<>();

nomi.add("Luca");
nomi.add("Marco");
nomi.add("Anna");
nomi.add("Luca");

System.out.println(nomi);
```

### Come funziona

HashSet usa internamente una tabella hash.

Non mantiene necessariamente l’ordine di inserimento.

Quindi potresti inserire:

```text
Luca
Marco
Anna
```

ma stamparli in un ordine diverso.

### Quando usarlo

Usa HashSet quando:

- vuoi evitare duplicati;
- non ti interessa l’ordine;
- vuoi controlli veloci con contains().

Esempio:

```java
if (nomi.contains("Luca")) {
    System.out.println("Luca presente");
}
```

## 10. LinkedHashSet

LinkedHashSet è simile ad HashSet, ma mantiene l’ordine di inserimento.

Esempio:

```java
LinkedHashSet<String> nomi = new LinkedHashSet<>();

nomi.add("Luca");
nomi.add("Marco");
nomi.add("Anna");
nomi.add("Luca");

System.out.println(nomi);
```

Output:

```text
[Luca, Marco, Anna]
```

Il secondo "Luca" non viene aggiunto.

### Quando usarlo

Usa LinkedHashSet quando:

- non vuoi duplicati;
- vuoi mantenere l’ordine di inserimento.

## 11. TreeSet

TreeSet è un set ordinato.

Esempio:

```java
TreeSet<Integer> numeri = new TreeSet<>();

numeri.add(30);
numeri.add(10);
numeri.add(20);

System.out.println(numeri);
```

Output:

```text
[10, 20, 30]
```

### Come funziona

TreeSet ordina automaticamente gli elementi.

Con i numeri ordina in modo crescente.

Con le stringhe ordina alfabeticamente.

Esempio:

```java
TreeSet<String> nomi = new TreeSet<>();

nomi.add("Marco");
nomi.add("Anna");
nomi.add("Luca");

System.out.println(nomi);
```

Output:

```text
[Anna, Luca, Marco]
```

### Quando usarlo

Usa TreeSet quando:

- vuoi evitare duplicati;
- vuoi elementi ordinati automaticamente.

## 12. Differenza tra HashSet, LinkedHashSet e TreeSet

| Tipo | Duplicati | Ordine |
|---|---|---|
| HashSet | no | non garantito |
| LinkedHashSet | no | ordine di inserimento |
| TreeSet | no | ordine naturale/crescente |

## 13. Queue

Una Queue è una coda.

Di solito funziona con logica:

```text
FIFO
First In, First Out
```

Cioè:

il primo elemento inserito è il primo che esce.

Esempio reale:

```text
fila alla posta
```

Chi arriva prima viene servito prima.

Esempio Java:

```java
Queue<String> coda = new LinkedList<>();

coda.add("Luca");
coda.add("Marco");
coda.add("Anna");

System.out.println(coda.poll());
```

Output:

```text
Luca
```

### Metodi principali

```java
add()
offer()
poll()
peek()
```

add() aggiunge.

offer() aggiunge, spesso preferito nelle code.

poll() rimuove e restituisce il primo elemento.

peek() legge il primo elemento senza rimuoverlo.

## 14. LinkedList come Queue

LinkedList può essere usata anche come coda.

```java
Queue<String> coda = new LinkedList<>();

coda.offer("Luca");
coda.offer("Marco");

System.out.println(coda.poll());
```

Output:

```text
Luca
```

Qui LinkedList non la stiamo usando come semplice lista, ma come Queue.

## 15. PriorityQueue

PriorityQueue è una coda con priorità.

Non estrae per forza il primo inserito.

Estrae l’elemento con priorità più alta.

Per i numeri, di default, estrae il più piccolo.

Esempio:

```java
PriorityQueue<Integer> numeri = new PriorityQueue<>();

numeri.offer(30);
numeri.offer(10);
numeri.offer(20);

System.out.println(numeri.poll());
```

Output:

```text
10
```

### Quando usarla

Usa PriorityQueue quando:

- devi gestire priorità;
- vuoi sempre estrarre l’elemento minimo o massimo;
- stai facendo algoritmi tipo Dijkstra, A*, scheduling, gestione priorità.

## 16. Deque

Deque significa:

```text
Double Ended Queue
```

cioè coda a doppia estremità.

Puoi aggiungere e togliere sia davanti sia dietro.

Esempio:

```java
Deque<String> deque = new ArrayDeque<>();

deque.addFirst("Luca");
deque.addLast("Marco");

System.out.println(deque.removeFirst());
System.out.println(deque.removeLast());
```

Output:

```text
Luca
Marco
```

### Quando usarla

Usa Deque quando vuoi:

- una coda doppia;
- una struttura flessibile;
- una pila moderna;
- aggiungere/togliere da entrambe le estremità.

## 17. ArrayDeque

ArrayDeque è una delle strutture più utili ma spesso sottovalutate.

Può funzionare sia da coda sia da stack.

Esempio come coda:

```java
ArrayDeque<String> coda = new ArrayDeque<>();

coda.offer("Luca");
coda.offer("Marco");

System.out.println(coda.poll());
```

Output:

```text
Luca
```

Esempio come stack:

```java
ArrayDeque<String> stack = new ArrayDeque<>();

stack.push("A");
stack.push("B");
stack.push("C");

System.out.println(stack.pop());
```

Output:

```text
C
```

### Quando usarla

Usa ArrayDeque quando:

- vuoi una coda efficiente;
- vuoi una pila moderna;
- vuoi evitare Stack, che è vecchio.

## 18. Map

Una Map salva coppie:

```text
chiave -> valore
```

Esempio:

```text
"nome" -> "Luca"
"eta" -> 25
"citta" -> "Roma"
```

In Java:

```java
Map<String, Integer> voti = new HashMap<>();

voti.put("Luca", 28);
voti.put("Marco", 30);
voti.put("Anna", 26);
```

Qui:

```text
"Luca"
```

è la chiave.

```text
28
```

è il valore.

## 19. HashMap

HashMap è la mappa più usata.

Esempio:

```java
HashMap<String, Integer> voti = new HashMap<>();

voti.put("Luca", 28);
voti.put("Marco", 30);
voti.put("Anna", 26);

System.out.println(voti.get("Marco"));
```

Output:

```text
30
```

### Come funziona

HashMap usa una tabella hash.

Le chiavi sono uniche.

Questo significa che non puoi avere due volte la stessa chiave.

Esempio:

```java
voti.put("Luca", 28);
voti.put("Luca", 30);
```

Il secondo valore sovrascrive il primo.

Quindi "Luca" avrà valore 30.

### Quando usarla

Usa HashMap quando:

- vuoi associare una chiave a un valore;
- vuoi cercare velocemente tramite chiave;
- non ti interessa l’ordine.

## 20. LinkedHashMap

LinkedHashMap è come HashMap, ma mantiene l’ordine di inserimento.

Esempio:

```java
LinkedHashMap<String, Integer> voti = new LinkedHashMap<>();

voti.put("Luca", 28);
voti.put("Marco", 30);
voti.put("Anna", 26);

System.out.println(voti);
```

Output:

```text
{Luca=28, Marco=30, Anna=26}
```

### Quando usarla

Usa LinkedHashMap quando:

- vuoi chiave-valore;
- vuoi mantenere l’ordine di inserimento.

## 21. TreeMap

TreeMap ordina automaticamente le chiavi.

Esempio:

```java
TreeMap<String, Integer> voti = new TreeMap<>();

voti.put("Marco", 30);
voti.put("Anna", 26);
voti.put("Luca", 28);

System.out.println(voti);
```

Output:

```text
{Anna=26, Luca=28, Marco=30}
```

Le chiavi vengono ordinate alfabeticamente.

Con numeri:

```java
TreeMap<Integer, String> studenti = new TreeMap<>();

studenti.put(3, "Marco");
studenti.put(1, "Anna");
studenti.put(2, "Luca");

System.out.println(studenti);
```

Output:

```text
{1=Anna, 2=Luca, 3=Marco}
```

### Quando usarla

Usa TreeMap quando:

- vuoi una mappa;
- vuoi le chiavi ordinate automaticamente.

## 22. Hashtable

Hashtable è una mappa vecchia, simile a HashMap.

Esempio:

```java
Hashtable<String, Integer> voti = new Hashtable<>();

voti.put("Luca", 28);
voti.put("Marco", 30);
```

### Differenza con HashMap

Hashtable è sincronizzata, quindi più pensata per vecchi contesti multi-thread.

Oggi si usa poco.

Di solito si preferisce:

```java
HashMap
```

oppure:

```java
ConcurrentHashMap
```

## 23. ConcurrentHashMap

ConcurrentHashMap è una mappa pensata per programmi con più thread.

Esempio:

```java
ConcurrentHashMap<String, Integer> voti = new ConcurrentHashMap<>();

voti.put("Luca", 28);
voti.put("Marco", 30);
```

Serve quando più thread leggono e scrivono sulla stessa mappa.

Per usarla:

```java
import java.util.concurrent.ConcurrentHashMap;
```

### Quando usarla

Usa ConcurrentHashMap quando:

- lavori con thread;
- più parti del programma modificano la mappa contemporaneamente;
- vuoi più sicurezza rispetto a HashMap.

## 24. Differenza tra HashMap, LinkedHashMap e TreeMap

| Tipo | Chiavi duplicate | Ordine |
|---|---|---|
| HashMap | no | non garantito |
| LinkedHashMap | no | ordine di inserimento |
| TreeMap | no | chiavi ordinate |

## 25. Metodi principali delle List

```java
add()
get()
set()
remove()
size()
contains()
isEmpty()
clear()
```

Esempio:

```java
ArrayList<String> nomi = new ArrayList<>();

nomi.add("Luca");
nomi.add("Marco");

System.out.println(nomi.get(0));

nomi.set(0, "Anna");

nomi.remove("Marco");

System.out.println(nomi.size());
```

## 26. Metodi principali dei Set

```java
add()
remove()
contains()
size()
isEmpty()
clear()
```

Esempio:

```java
HashSet<String> nomi = new HashSet<>();

nomi.add("Luca");
nomi.add("Luca");
nomi.add("Marco");

System.out.println(nomi.size());
```

Output:

```text
2
```

## 27. Metodi principali delle Map

```java
put()
get()
remove()
containsKey()
containsValue()
keySet()
values()
entrySet()
size()
clear()
```

Esempio:

```java
HashMap<String, Integer> voti = new HashMap<>();

voti.put("Luca", 28);
voti.put("Marco", 30);

System.out.println(voti.get("Luca"));

System.out.println(voti.containsKey("Marco"));

for (String nome : voti.keySet()) {
    System.out.println(nome);
}

for (Integer voto : voti.values()) {
    System.out.println(voto);
}

for (Map.Entry<String, Integer> entry : voti.entrySet()) {
    System.out.println(entry.getKey() + " -> " + entry.getValue());
}
```

## 28. Quale collection scegliere?

### Voglio una lista normale

Usa:

```java
ArrayList
```

### Voglio una lista e devo aggiungere spesso all’inizio

Usa:

```java
LinkedList
```

### Voglio eliminare duplicati

Usa:

```java
HashSet
```

### Voglio eliminare duplicati ma mantenere ordine

Usa:

```java
LinkedHashSet
```

### Voglio eliminare duplicati e ordinare automaticamente

Usa:

```java
TreeSet
```

### Voglio coppie chiave-valore

Usa:

```java
HashMap
```

### Voglio coppie chiave-valore ordinate per inserimento

Usa:

```java
LinkedHashMap
```

### Voglio coppie chiave-valore ordinate per chiave

Usa:

```java
TreeMap
```

### Voglio una coda normale

Usa:

```java
Queue<String> coda = new LinkedList<>();
```

oppure:

```java
ArrayDeque
```

### Voglio una pila moderna

Usa:

```java
ArrayDeque
```

## 29. Tabella riassuntiva completa

| Tipo | Duplicati | Ordine | Uso principale |
|---|---|---|---|
| ArrayList | sì | inserimento | lista standard |
| LinkedList | sì | inserimento | lista/coda con inserimenti ai bordi |
| Vector | sì | inserimento | vecchia lista sincronizzata |
| Stack | sì | LIFO | pila vecchio stile |
| HashSet | no | non garantito | eliminare duplicati |
| LinkedHashSet | no | inserimento | no duplicati + ordine |
| TreeSet | no | ordinato | no duplicati + ordinamento |
| PriorityQueue | sì | priorità | estrarre elemento prioritario |
| ArrayDeque | sì | dipende dall’uso | coda o stack moderna |
| HashMap | chiavi no, valori sì | non garantito | chiave-valore |
| LinkedHashMap | chiavi no, valori sì | inserimento | chiave-valore ordinato per inserimento |
| TreeMap | chiavi no, valori sì | chiavi ordinate | chiave-valore ordinato |
| Hashtable | chiavi no, valori sì | non garantito | vecchia mappa sincronizzata |
| ConcurrentHashMap | chiavi no, valori sì | non garantito | mappa per più thread |

## 30. Esempio finale con quasi tutte

```java
import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<String> lista = new ArrayList<>();
        lista.add("Luca");
        lista.add("Marco");
        lista.add("Luca");

        Set<String> set = new HashSet<>();
        set.add("Luca");
        set.add("Marco");
        set.add("Luca");

        Map<String, Integer> mappa = new HashMap<>();
        mappa.put("Luca", 28);
        mappa.put("Marco", 30);

        Queue<String> coda = new LinkedList<>();
        coda.offer("Primo");
        coda.offer("Secondo");

        Deque<String> pila = new ArrayDeque<>();
        pila.push("A");
        pila.push("B");

        System.out.println("Lista: " + lista);
        System.out.println("Set: " + set);
        System.out.println("Mappa: " + mappa);
        System.out.println("Coda poll: " + coda.poll());
        System.out.println("Pila pop: " + pila.pop());
    }
}
```

## Riassunto secco

Usa:

```java
ArrayList
```

per liste normali.

```java
LinkedList
```

per liste collegate, code e inserimenti/rimozioni ai bordi.

```java
HashSet
```

per valori unici senza ordine.

```java
LinkedHashSet
```

per valori unici con ordine di inserimento.

```java
TreeSet
```

per valori unici ordinati.

```java
HashMap
```

per chiave-valore senza ordine.

```java
LinkedHashMap
```

per chiave-valore con ordine di inserimento.

```java
TreeMap
```

per chiave-valore ordinato per chiave.

```java
ArrayDeque
```

per coda o stack moderni.

```java
PriorityQueue
```

per code con priorità.
