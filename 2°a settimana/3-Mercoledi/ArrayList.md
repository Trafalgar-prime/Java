
# ArrayList in Java — Guida Completa

---

# 1️⃣ Cos’è un ArrayList?

`ArrayList` è una **classe della Java Collections Framework** che rappresenta un **array dinamico**.

👉 Significa che:
- Si comporta come un array
- Ma **non ha dimensione fissa**
- Cresce automaticamente quando aggiungi elementi

Fa parte del package:

```java
import java.util.ArrayList;
```

---

# 2️⃣ Perché esiste se già esistono gli array?

Un array normale:

```java
int[] numeri = new int[3];
```

Ha **dimensione fissa**.

Se vuoi aggiungere un quarto elemento?  
Devi creare un nuovo array e copiare tutto.

Con `ArrayList` invece:

```java
ArrayList<Integer> numeri = new ArrayList<>();
numeri.add(10);
numeri.add(20);
numeri.add(30);
numeri.add(40);
```

Lui si espande da solo.

---

# 3️⃣ Sintassi Base

## Dichiarazione

```java
ArrayList<Tipo> nome = new ArrayList<>();
```

Esempio:

```java
ArrayList<String> nomi = new ArrayList<>();
ArrayList<Integer> numeri = new ArrayList<>();
ArrayList<Double> voti = new ArrayList<>();
```

---

# 4️⃣ ⚠️ Importante: Wrapper Classes

`ArrayList` NON accetta tipi primitivi.

❌ Questo NON funziona:

```java
ArrayList<int> numeri = new ArrayList<>();  // ERRORE
```

Devi usare le **Wrapper Classes**:

| Primitivo | Wrapper |
|-----------|----------|
| int       | Integer |
| double    | Double  |
| char      | Character |
| boolean   | Boolean |

Quindi:

```java
ArrayList<Integer> numeri = new ArrayList<>();
```

Java usa **autoboxing** automaticamente.

---

# 5️⃣ Metodi fondamentali

## ➤ add()

Aggiunge un elemento

```java
nomi.add("Lorenzo");
```

Puoi anche specificare la posizione:

```java
nomi.add(0, "Marco");
```

---

## ➤ get()

Recupera un elemento

```java
String nome = nomi.get(0);
```

---

## ➤ set()

Modifica un elemento

```java
nomi.set(0, "Giovanni");
```

---

## ➤ remove()

Per indice:

```java
nomi.remove(0);
```

Per valore:

```java
nomi.remove("Giovanni");
```

---

## ➤ size()

Restituisce la dimensione

```java
int lunghezza = nomi.size();
```

⚠️ Ricorda:  
Array → `.length`  
ArrayList → `.size()`

---

## ➤ contains()

```java
if(nomi.contains("Lorenzo")) {
    System.out.println("Presente");
}
```

---

## ➤ clear()

```java
nomi.clear();
```

Svuota tutto.

---

# 6️⃣ Iterare un ArrayList

## For classico

```java
for(int i = 0; i < nomi.size(); i++) {
    System.out.println(nomi.get(i));
}
```

---

## For-each (più pulito)

```java
for(String nome : nomi) {
    System.out.println(nome);
}
```

---

# 7️⃣ Come funziona internamente?

Un `ArrayList` internamente usa un **array normale**.

Quando si riempie:

1. Crea un nuovo array più grande
2. Copia tutti gli elementi
3. Sostituisce il vecchio

Complessità:

- Accesso → O(1)
- Aggiunta in fondo → quasi O(1)
- Inserimento in mezzo → O(n)
- Rimozione in mezzo → O(n)

Se lavori con molti inserimenti centrali → meglio `LinkedList`.

---

# 8️⃣ Differenza tra Array e ArrayList

| Array | ArrayList |
|-------|----------|
| Dimensione fissa | Dinamico |
| Può contenere primitivi | Solo oggetti |
| Più veloce | Leggermente più lento |
| Non ha metodi | Ha molti metodi |

---

# 9️⃣ ArrayList di Oggetti

```java
class Dog {
    String name;
    int eta;
}

ArrayList<Dog> cani = new ArrayList<>();

Dog d1 = new Dog();
d1.name = "Fido";
d1.eta = 3;

cani.add(d1);
```

Accesso:

```java
System.out.println(cani.get(0).name);
```

---

# 🔟 ArrayList vs LinkedList

Entrambe implementano `List`.

```java
List<String> lista = new ArrayList<>();
```

Buona pratica: dichiarare con l'interfaccia `List`.

Perché?
→ Polimorfismo  
→ Puoi cambiare implementazione facilmente

---

# 1️⃣1️⃣ Costruttori

Puoi specificare capacità iniziale:

```java
ArrayList<String> nomi = new ArrayList<>(100);
```

Utile se sai che inserirai molti elementi → meno riallocazioni.

---

# 1️⃣2️⃣ Ordinare un ArrayList

```java
import java.util.Collections;

Collections.sort(nomi);
```

---

# 1️⃣3️⃣ Conversione Array ↔ ArrayList

## Array → ArrayList

```java
import java.util.Arrays;

String[] array = {"A", "B", "C"};
ArrayList<String> lista = new ArrayList<>(Arrays.asList(array));
```

---

## ArrayList → Array

```java
String[] array = lista.toArray(new String[0]);
```

---

# 1️⃣4️⃣ Errori comuni

❌ Dimenticare import  
❌ Usare tipo primitivo  
❌ Confondere `.length` con `.size()`  
❌ Accedere fuori indice → `IndexOutOfBoundsException`

---

# 1️⃣5️⃣ Quando usare ArrayList?

Usalo quando:

- Non sai la dimensione iniziale
- Devi aggiungere dinamicamente
- Devi usare metodi comodi (contains, remove, ecc.)

Non usarlo quando:

- Serve altissima performance
- Hai dimensione fissa
- Devi fare molte operazioni centrali (usa LinkedList)

---

# 1️⃣6️⃣ Esempio completo

```java
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<String> nomi = new ArrayList<>();

        nomi.add("Lorenzo");
        nomi.add("Marco");
        nomi.add("Giulia");

        for(String nome : nomi) {
            System.out.println(nome);
        }

        nomi.remove("Marco");

        System.out.println("Dimensione: " + nomi.size());
    }
}
```

---

# 🎯 Conclusione

Un buon programmatore:

- Sa quando usare array
- Sa quando usare ArrayList
- Capisce la complessità temporale
- Dichiara sempre con `List<>`
