# Override vs Overload in Java

## 🔁 @Override --- COSA FA DAVVERO

Nel tuo esempio:

``` java
@Override
public void roam() {
    super.roam();
}
```

### Cosa significa?

Significa che questa classe sta ridefinendo (riscrivendo) un metodo
`roam()` che esiste già nella classe padre.

------------------------------------------------------------------------

## 🔹 1️⃣ Cos'è l'Override?

L'override è quando:

> Una sottoclasse fornisce una nuova implementazione di un metodo già
> definito nella superclasse.

Esempio semplice:

``` java
class Animal {
    public void roam() {
        System.out.println("L'animale si muove");
    }
}
```

``` java
class Dog extends Animal {

    @Override
    public void roam() {
        System.out.println("Il cane corre");
    }
}
```

Ora:

``` java
Dog d = new Dog();
d.roam();
```

Output:

    Il cane corre

Perché? Perché il metodo del figlio sovrascrive quello del padre.

------------------------------------------------------------------------

## 🔹 2️⃣ A cosa serve @Override?

Tecnicamente non è obbligatorio, ma è IMPORTANTISSIMO.

Serve a dire al compilatore:

> "Sto volontariamente sovrascrivendo un metodo della superclasse."

Se sbagli nome o firma del metodo, Java ti segnala errore.

Esempio errore:

``` java
@Override
public void roamm() {   // errore di battitura
}
```

Il compilatore dirà: \> Method does not override method from superclass

Senza `@Override`, invece, il codice compila e crea un metodo nuovo
(errore silenzioso).

Quindi: 👉 `@Override` ti protegge dagli errori.

------------------------------------------------------------------------

## 🔹 3️⃣ Cosa fa super.roam();?

Questa riga:

``` java
super.roam();
```

Chiama il metodo della classe padre.

Quindi nel tuo codice:

``` java
@Override
public void roam() {
    super.roam();
}
```

Stai facendo questo:

1.  Override del metodo
2.  Ma dentro richiami esattamente il comportamento originale

👉 Di fatto non stai cambiando nulla.

È utile quando vuoi:

-   Eseguire comportamento del padre
-   Aggiungere qualcosa dopo

Esempio:

``` java
@Override
public void roam() {
    super.roam();
    System.out.println("in modo più veloce");
}
```

------------------------------------------------------------------------

# 🔀 OVERLOAD --- completamente diverso

## 🔹 Cos'è l'Overload?

L'overload è quando:

> Hai più metodi con lo stesso nome ma parametri diversi nella stessa
> classe.

Esempio:

``` java
class MathUtils {

    public int sum(int a, int b) {
        return a + b;
    }

    public double sum(double a, double b) {
        return a + b;
    }

    public int sum(int a, int b, int c) {
        return a + b + c;
    }
}
```

Qui: - Stesso nome - Parametri diversi - Stessa classe

👉 Questo è overload.

------------------------------------------------------------------------

# ⚠️ DIFFERENZA CHIAVE (fondamentale)

  Override                            Overload
  ----------------------------------- -------------------------------
  Avviene tra classe padre e figlio   Avviene nella stessa classe
  Stessa firma                        Parametri diversi
  Cambia comportamento ereditato      Offre più versioni del metodo
  Richiede ereditarietà               Non richiede ereditarietà

------------------------------------------------------------------------

# 🔬 Differenza tecnica precisa

## Override richiede:

-   Stesso nome
-   Stessi parametri
-   Stesso tipo di ritorno (o covariante)
-   Non può essere `private`
-   Non può essere `static`

## Overload richiede:

-   Stesso nome
-   Parametri diversi (numero o tipo)

⚠️ Il tipo di ritorno da solo NON basta per fare overload:

Questo è errore:

``` java
public int sum(int a, int b) {}
public double sum(int a, int b) {} // ERRORE
```

Perché i parametri sono identici.

------------------------------------------------------------------------

# 🧠 Concetto mentale corretto

-   Override = "Rimpiazzo comportamento"
-   Overload = "Offro più varianti"

------------------------------------------------------------------------

# 🎯 Quando si usano davvero?

## Override → Polimorfismo

Esempio reale:

``` java
Animal a = new Dog();
a.roam();
```

Viene chiamato il metodo del `Dog`.

Questo è polimorfismo dinamico.

------------------------------------------------------------------------

## Overload → Comodità API

Esempio classico: `System.out.println()` ha decine di overload:

``` java
println(int)
println(double)
println(String)
println(boolean)
```

------------------------------------------------------------------------

# 🔥 Punto importante da sviluppatore serio

Se stai lavorando su progetti grandi:

-   Override è per comportamento dinamico
-   Overload è per usabilità

Non sono intercambiabili.
