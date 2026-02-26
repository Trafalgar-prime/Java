# Array di oggetti in Java (es. `Dog[]`)

Quando **crei una classe** in Java (es. `Dog`), puoi anche creare **array di oggetti** di quella classe.  
Il punto chiave da capire è questo:

> Un array come `Dog[]` contiene **riferimenti a oggetti `Dog`**, non gli oggetti già “costruiti”.

Quindi, quando fai `new Dog[3]`, stai creando **3 “slot”** (celle) che possono puntare a `Dog`, ma **inizialmente sono `null`**.

---

## 1) Definire la classe `Dog`

Ecco la versione corretta e completa (nota: **in Java non si scrive `class Dog()`**, ma **`class Dog`**):

```java
public class Dog {
    String name;
    int eta;
}
```

- `name` e `eta` sono **campi** (attributi) dell’oggetto.
- Se non assegni valori, Java mette valori di default:
  - `name` → `null`
  - `eta` → `0`

---

## 2) Creare un array di cani

```java
Dog[] dogs = new Dog[3];
```

Questo **NON crea 3 cani**.

Crea:
- 1 array di lunghezza 3
- con 3 celle:
  - `dogs[0] == null`
  - `dogs[1] == null`
  - `dogs[2] == null`

### Visualizzazione mentale

| Indice | Contenuto |
|-------:|-----------|
| 0 | `null` |
| 1 | `null` |
| 2 | `null` |

---

## 3) Perché serve `new Dog()` per ogni elemento?

Perché ogni `dogs[i]` è un riferimento, e finché non lo “riempi” con un oggetto, resta `null`.

Se provi a fare:

```java
dogs[1].name = "Fred";
```

prima di creare l’oggetto, ottieni:

**NullPointerException**  
perché stai dicendo a Java di accedere a `.name` su un oggetto che non esiste (`null`).

---

## 4) Creare l’istanza e poi usare i campi

```java
dogs[1] = new Dog();      // creo l'oggetto e lo metto nella cella 1
dogs[1].name = "Fred";    // ora posso usare i campi
dogs[1].eta = 5;
```

Ora l’array contiene:

| Indice | Contenuto |
|-------:|-----------|
| 0 | `null` |
| 1 | riferimento → oggetto `Dog{name="Fred", eta=5}` |
| 2 | `null` |

---

## 5) Esempio completo (stampa e controllo `null`)

```java
public class Main {
    public static void main(String[] args) {
        Dog[] dogs = new Dog[3];

        dogs[1] = new Dog();
        dogs[1].name = "Fred";
        dogs[1].eta = 5;

        // Attenzione: dogs[0] e dogs[2] sono ancora null!
        for (int i = 0; i < dogs.length; i++) {
            if (dogs[i] != null) {
                System.out.println("dogs[" + i + "] = " + dogs[i].name + ", eta=" + dogs[i].eta);
            } else {
                System.out.println("dogs[" + i + "] = null (nessun cane creato)");
            }
        }
    }
}
```

---

## 6) Aggiungere un costruttore (modo più “pulito”)

Invece di creare l’oggetto vuoto e poi assegnare i campi uno per uno, puoi fare un **costruttore**:

```java
public class Dog {
    String name;
    int eta;

    public Dog(String name, int eta) {
        this.name = name;
        this.eta = eta;
    }
}
```

E nel `main`:

```java
Dog[] dogs = new Dog[3];
dogs[0] = new Dog("Fred", 5);
dogs[1] = new Dog("Luna", 2);
dogs[2] = new Dog("Rocky", 7);
```

Questo è più compatto, e riduce errori.

---

## 7) Inizializzare tutti i cani con un ciclo

Se vuoi creare un array di 3 cani “vuoti” (tutti già istanziati):

```java
Dog[] dogs = new Dog[3];

for (int i = 0; i < dogs.length; i++) {
    dogs[i] = new Dog("SenzaNome", 0);
}
```

Ora **nessuna cella è null**.

---

## 8) Differenza fondamentale: array di primitivi vs array di oggetti

### Array di primitivi
```java
int[] a = new int[3];
```

Qui `a` contiene **valori** (0,0,0) perché `int` è primitivo.

### Array di oggetti
```java
Dog[] dogs = new Dog[3];
```

Qui `dogs` contiene **riferimenti** (`null, null, null`) finché non fai `new Dog()`.

---

## 9) Alternative più flessibili: `ArrayList<Dog>`

Un array ha dimensione fissa. Se vuoi aggiungere cani senza decidere prima “quanti”, usa una lista:

```java
import java.util.ArrayList;

ArrayList<Dog> dogs = new ArrayList<>();

dogs.add(new Dog("Fred", 5));
dogs.add(new Dog("Luna", 2));
```

- Cresce automaticamente
- È comoda per inserimenti/rimozioni

---

## 10) Buone pratiche (importanti)

- **Evita campi pubblici** in progetti seri: usa `private` + getter/setter (quando serve).
- Aggiungi `toString()` per stampare bene un oggetto.
- Controlla `null` se l’array non è completamente inizializzato.

Esempio con `toString()`:

```java
public class Dog {
    String name;
    int eta;

    public Dog(String name, int eta) {
        this.name = name;
        this.eta = eta;
    }

    @Override
    public String toString() {
        return "Dog{name='" + name + "', eta=" + eta + "}";
    }
}
```

Poi:

```java
System.out.println(dogs[0]); // stampa Dog{name='Fred', eta=5}
```

---

## Mini-riassunto (da ricordare)

- `new Dog[3]` crea **solo l’array**, non i cani.
- Ogni `dogs[i]` è `null` finché non fai `dogs[i] = new Dog(...)`.
- Dopo aver creato l’oggetto, puoi usare `dogs[i].name`, `dogs[i].eta`, ecc.
- Se vuoi struttura più flessibile, usa `ArrayList<Dog>`.

---
