# Classi Abstract in Java

## 🧱 Cos'è una classe `abstract`?

Una classe `abstract` è una classe che:

-   ❌ Non può essere istanziata
-   ✅ Può contenere metodi senza implementazione
-   ✅ Serve come modello per le sottoclassi

Esempio base:

``` java
abstract class Animal {

    public abstract void makeNoise();  // metodo astratto

    public void breathe() {            // metodo normale
        System.out.println("Respira");
    }
}
```

------------------------------------------------------------------------

## 🚫 Perché non può essere istanziata?

Questo è illegale:

``` java
Animal a = new Animal(); // ERRORE
```

Perché una classe astratta è incompleta.

È come dire: "Esiste il concetto di Animale, ma non esiste un animale
generico concreto."

------------------------------------------------------------------------

## 🔹 Metodo astratto

Un metodo astratto:

-   Non ha corpo
-   Ha solo la firma
-   Deve essere implementato nelle sottoclassi

``` java
public abstract void makeNoise();
```

Notare: - niente `{ }` - niente implementazione

------------------------------------------------------------------------

## 🔹 Sottoclasse concreta

``` java
class Dog extends Animal {

    @Override
    public void makeNoise() {
        System.out.println("Bau!");
    }
}
```

Ora funziona:

``` java
Dog d = new Dog();
d.makeNoise(); // Bau!
```

------------------------------------------------------------------------

## 🔥 Punto fondamentale: obbligo di implementazione

Se una classe estende una classe astratta, deve:

-   Implementare tutti i metodi astratti
-   Oppure diventare anch'essa abstract

Esempio errore:

``` java
class Cat extends Animal {
    // ERRORE se non implementa makeNoise()
}
```

Soluzione alternativa:

``` java
abstract class Cat extends Animal {
}
```

------------------------------------------------------------------------

## 🎯 Perché esistono?

Per imporre un contratto.

Esempio reale:

Tutti gli animali fanno rumore, ma ognuno in modo diverso.

Quindi il padre dice: "Chiunque sia un Animal deve sapere fare
makeNoise()"

Ma non dice come.

------------------------------------------------------------------------

## 🧠 Polimorfismo con classi abstract

``` java
Animal a1 = new Dog();
Animal a2 = new Cat();

a1.makeNoise();
a2.makeNoise();
```

Qui Java decide a runtime quale metodo eseguire.

Questo è polimorfismo dinamico.

------------------------------------------------------------------------

## 🔍 Classe abstract può avere:

✔ Metodi normali\
✔ Metodi abstract\
✔ Costruttore\
✔ Variabili\
✔ Metodi static

Esempio completo:

``` java
abstract class Vehicle {

    protected int speed;

    public Vehicle(int speed) {
        this.speed = speed;
    }

    public void stop() {
        System.out.println("Veicolo fermo");
    }

    public abstract void move();
}
```

------------------------------------------------------------------------

## ⚠ Differenza tra `abstract class` e `interface`

  Abstract class                   Interface
  -------------------------------- --------------------------
  Può avere variabili di istanza   No (solo costanti)
  Può avere costruttore            No
  Una sola eredità                 Implementazioni multiple
  Può avere metodi concreti        Sì

------------------------------------------------------------------------

## 🎯 Quando si usa davvero?

Usi una classe abstract quando:

-   Vuoi codice condiviso
-   Vuoi comportamento base
-   Vuoi forzare certe implementazioni

Esempio reale da progetto serio:

``` java
abstract class Shape {
    public abstract double area();
}
```

``` java
class Circle extends Shape {
    private double radius;

    public Circle(double r) {
        this.radius = r;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}
```

------------------------------------------------------------------------

## 🔥 Errore comune degli studenti

Pensano che abstract significhi:

"classe inutile"

No.

Significa:

"classe concettuale che definisce un modello"

------------------------------------------------------------------------

## 🎓 Riassunto chiaro

Una classe abstract:

-   Non può essere istanziata
-   Può avere metodi astratti
-   Serve come base per altre classi
-   È uno strumento per il polimorfismo
