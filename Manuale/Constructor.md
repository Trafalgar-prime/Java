# Costruttori in Java

## 🔹 COSA È UN COSTRUTTORE?

Un **costruttore** è un metodo speciale che viene eseguito **automaticamente quando crei un oggetto con `new`**.

Serve a:
- inizializzare le variabili dell’oggetto
- preparare lo stato iniziale
- garantire che l’oggetto nasca “valido”

---

## 🔹 ESEMPIO BASE

```java
class Dog {
    String name;
    int age;

    Dog() {
        System.out.println("Sto creando un cane!");
    }
}
```

Uso nel `main`:

```java
Dog d1 = new Dog();
```

Quando scrivi `new Dog()`:
1. Java alloca memoria
2. Esegue il costruttore `Dog()`
3. Restituisce l’oggetto

---

## 🔹 CARATTERISTICHE IMPORTANTI

Un costruttore:

- Ha **lo stesso nome della classe**
- NON ha tipo di ritorno (neanche `void`)
- Può avere parametri
- Può essere sovraccaricato (overloading)

---

## 🔹 COSTRUTTORE CON PARAMETRI

Questa è la forma più importante nella pratica.

```java
class Dog {
    String name;
    int age;

    Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

Uso:

```java
Dog d1 = new Dog("Fido", 3);
```

---

### 🔹 Perché si usa `this`?

`this` significa:  
👉 “l’oggetto corrente”

Qui:

```java
this.name = name;
```

- `this.name` → variabile dell’oggetto
- `name` → parametro del costruttore

Senza `this`, Java non capirebbe quale dei due stai usando.

---

## 🔹 COSTRUTTORE DI DEFAULT

Se NON scrivi nessun costruttore, Java crea automaticamente:

```java
Dog() { }
```

⚠️ Ma attenzione:  
Se scrivi anche solo un costruttore con parametri, quello vuoto NON viene più creato automaticamente.

Esempio:

```java
Dog(String name) { ... }
```

Ora `new Dog()` NON funziona più.

---

## 🔹 COSTRUTTORI MULTIPLI (OVERLOADING)

Puoi avere più costruttori:

```java
class Dog {
    String name;
    int age;

    Dog() {
        name = "Sconosciuto";
        age = 0;
    }

    Dog(String name) {
        this.name = name;
        age = 0;
    }

    Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

Java sceglie il costruttore in base ai parametri passati.

---

## 🔹 CHIAMARE UN COSTRUTTORE DA UN ALTRO (`this()`)

Puoi riutilizzare codice:

```java
Dog(String name) {
    this(name, 0);
}
```

⚠️ `this()` deve essere SEMPRE la prima riga del costruttore.

---

## 🔹 COSTRUTTORE E EREDITARIETÀ (`super()`)

Se hai una classe padre:

```java
class Animal {
    String name;

    Animal(String name) {
        this.name = name;
    }
}
```

Classe figlia:

```java
class Dog extends Animal {

    Dog(String name) {
        super(name);
    }
}
```

`super()` chiama il costruttore della classe padre.

⚠️ Anche `super()` deve essere la prima riga.

Se il padre NON ha costruttore vuoto, sei obbligato a chiamare `super()`.

---

## 🔹 DIFFERENZA TRA COSTRUTTORE E METODO

Costruttore:

```java
Dog() { }
```

Metodo:

```java
void Dog() { }   // Questo NON è un costruttore
```

Se metti `void`, diventa un metodo normale.

---

## 🔹 QUANDO USARE UN COSTRUTTORE?

Sempre quando:

- vuoi obbligare a inizializzare variabili
- vuoi evitare oggetti incompleti
- vuoi controllare come nasce l’oggetto

Esempio corretto (validazione):

```java
class Student {
    private String name;

    Student(String name) {
        if(name == null) {
            throw new IllegalArgumentException("Nome obbligatorio");
        }
        this.name = name;
    }
}
```

Questo impedisce oggetti invalidi.

---

## 🔹 COSTRUTTORE PRIVATO (concetto avanzato)

Serve per:
- Singleton
- classi utility
- impedire istanziazione

```java
class Utility {
    private Utility() {}
}
```

Ora nessuno può fare:

```java
new Utility();
```

---

## 🔹 IN MEMORIA COSA SUCCEDE?

Quando fai:

```java
Dog d = new Dog("Fido", 3);
```

1. Stack → variabile `d`
2. Heap → oggetto creato
3. Costruttore inizializza l’oggetto
4. `d` punta all’oggetto in heap

---

## 🔹 ERRORE COMUNE

Scrivere:

```java
public void Dog() {
}
```

Questo NON è un costruttore.

Oppure dimenticare di inizializzare campi importanti.

---

## 🔹 RIASSUNTO FINALE

Il costruttore:

- crea lo stato iniziale dell’oggetto
- ha lo stesso nome della classe
- non ha tipo di ritorno
- può avere parametri
- può essere sovraccaricato
- può chiamare `this()` o `super()`
