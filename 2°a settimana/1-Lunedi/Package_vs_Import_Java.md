# Java: differenza tra `package` e `import` (con esempi concreti)

Questa guida chiarisce **in modo approfondito** la differenza tra:

- `package` → **dove “vive” la tua classe** (namespace/cartella logica)
- `import` → **come “usi” classi che vivono in altri package**

> **Regola d’oro:** `package` e `import` **non sono alternative**.  
> `package` definisce l’identità della classe, `import` rende più comodo riferirsi ad altre classi.

---

## 1) Che cos’è un `package`?

Un `package` è un **nome gerarchico** (es. `com.example.fruits`) che:

1. **Organizza** le classi in gruppi (come cartelle logiche)
2. **Evita conflitti** di nomi (due classi possono chiamarsi uguale ma stare in package diversi)
3. Definisce il **Fully Qualified Name** (FQN), cioè il nome completo della classe

Esempio:

```java
package com.example.fruits;

public class Apple {}
```

Qui la classe si chiama (FQN):

```text
com.example.fruits.Apple
```

### 1.1) Regola pratica: `package` ↔ struttura delle cartelle

Se hai:

```java
package com.example.fruits;
```

allora il file **deve** stare (in un progetto standard) in una struttura tipo:

```text
src/
  com/
    example/
      fruits/
        Apple.java
```

Se il file non è in quella cartella, in genere avrai errori di compilazione o IntelliJ non riuscirà a risolvere correttamente la classe.

---

## 2) Che cos’è un `import`?

`import` serve a **usare classi (o membri statici)** definite in **altri package** senza scrivere ogni volta il nome completo.

Esempio:

```java
import java.util.Scanner;
```

Questo ti permette di scrivere `Scanner` invece di:

```java
java.util.Scanner
```

---

## 3) Esempio base: `package` e `import` insieme

### Struttura del progetto

```text
src/
  com/
    example/
      fruits/
        Main.java
        Apple.java
      tools/
        RoundStuffCalc.java
```

### `Apple.java`

```java
package com.example.fruits;

public class Apple {
    public int volume = 1;

    public void grow(int amount) {
        volume += amount;
    }
}
```

### `RoundStuffCalc.java`

```java
package com.example.tools;

public class RoundStuffCalc {
    public static int roundToInt(double x) {
        return (int) Math.round(x);
    }
}
```

### `Main.java` (usa *Apple* e *RoundStuffCalc*)

```java
package com.example.fruits;

// Apple è nello stesso package -> NON serve import
import com.example.tools.RoundStuffCalc; // package diverso -> serve import
import java.util.Scanner;                // libreria standard -> serve import

public class Main {
    public static void main(String[] args) {

        Apple apple = new Apple(); // stesso package: com.example.fruits
        apple.grow(10);
        System.out.println("Volume apple = " + apple.volume);

        int r = RoundStuffCalc.roundToInt(3.6);
        System.out.println("Round = " + r);

        Scanner sc = new Scanner(System.in);
        System.out.print("Inserisci un numero: ");
        double x = sc.nextDouble();
        System.out.println("Hai inserito: " + x);
    }
}
```

✅ Nota: in questo esempio **usi sia `package` (per definire dove sta Main)** sia `import` (per usare classi esterne).

---

## 4) Perché a volte “vedo solo `package` e non `import`”?

Perché **`import` serve solo se usi classi fuori dal package corrente**.

Esempio: se `Main` e `Apple` sono nello stesso package `com.example.fruits`, allora:

```java
package com.example.fruits;

public class Main {
    public static void main(String[] args) {
        Apple a = new Apple(); // stesso package -> niente import
    }
}
```

Se invece `Apple` fosse in `com.example.other`, allora:

```java
package com.example.fruits;

import com.example.other.Apple;

public class Main {
    public static void main(String[] args) {
        Apple a = new Apple();
    }
}
```

---

## 5) “Default package” (senza `package`): perché è sconsigliato

Se non scrivi `package ...;`, la classe è nel **default package**.

Esempio:

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
    }
}
```

Funziona per progetti piccoli, ma è **sconsigliato** perché:

- rende difficile organizzare il codice
- crea conflitti di nomi
- molti tool/framework (e IDE) lavorano peggio con il default package
- non puoi importare classi dal default package in un package “normale”

✅ Consiglio: **usa sempre i package** nei progetti reali.

---

## 6) “import non necessario”: puoi usare il nome completo (FQN)

Se vuoi, puoi evitare `import` e scrivere sempre il nome completo:

```java
package com.example.fruits;

public class Main {
    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);
    }
}
```

È corretto, ma **poco leggibile**.

---

## 7) `import` con wildcard `*` (e perché usarlo con cautela)

Puoi importare tutte le classi di un package:

```java
import java.util.*;
```

Pro:
- meno righe

Contro:
- meno chiaro
- può creare ambiguità se due package hanno classi con lo stesso nome

✅ Suggerimento pratico: in genere è meglio importare classi specifiche:

```java
import java.util.Scanner;
import java.util.ArrayList;
```

---

## 8) Conflitto di nomi: stesso nome classe in due package diversi

Esempio classico: `java.util.Date` e `java.sql.Date`.

Se importi entrambe:

```java
import java.util.Date;
import java.sql.Date; // conflitto: due Date
```

Poi scrivi:

```java
Date d = new Date(); // ERRORE: ambiguità
```

Soluzione: importi una sola e l’altra la scrivi col nome completo:

```java
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Date a = new Date();                 // java.util.Date
        java.sql.Date b = new java.sql.Date(0);
    }
}
```

---

## 9) `static import` (importare membri statici)

Serve a importare **metodi/variabili statiche** senza scrivere il nome classe.

Esempio:

```java
import static java.lang.Math.sqrt;
import static java.lang.Math.PI;

public class Main {
    public static void main(String[] args) {
        System.out.println(sqrt(16)); // invece di Math.sqrt(16)
        System.out.println(PI);       // invece di Math.PI
    }
}
```

### Quando usarlo
- quando davvero migliora la leggibilità (es. costanti o funzioni matematiche)
- evita di abusarne: può rendere difficile capire “da dove arriva” un metodo

---

## 10) Ordine e regole sintattiche (IMPORTANTE)

L’ordine nel file Java è:

1. `package ...;` (se presente) **deve essere la prima riga non-commento**
2. `import ...;` (opzionali)
3. definizione di classi/interfacce

Esempio corretto:

```java
package com.example.fruits;

import java.util.Scanner;
import com.example.tools.RoundStuffCalc;

public class Main { ... }
```

Esempio **non valido**:

```java
import java.util.Scanner;
package com.example.fruits; // ERRORE: package dopo import
```

---

## 11) Collegamento con IntelliJ: perché “non posso fare Run”

IntelliJ riesce a fare Run quando:

- il file sta sotto una cartella **Sources Root** (`src` marcata correttamente)
- esiste una classe con `public static void main(String[] args)`
- la classe indicata nella Run Configuration è corretta (FQN corretto)

### Firma corretta del main

Questa è quella “standard” e riconosciuta:

```java
public static void main(String[] args) { }
```

Se scrivi solo:

```java
static void main() { }
```

IntelliJ di solito **non lo riconosce come entry point** (e in generale non è l’entry point standard della JVM).

---

## 12) Mini-esercizi (con soluzione)

### Esercizio A
Hai:

```text
src/com/example/fruits/Main.java
src/com/example/tools/RoundStuffCalc.java
```

Domanda: in `Main.java` cosa devi scrivere?

✅ Soluzione:

```java
package com.example.fruits;

import com.example.tools.RoundStuffCalc;

public class Main {
    public static void main(String[] args) {
        int x = RoundStuffCalc.roundToInt(2.4);
        System.out.println(x);
    }
}
```

---

### Esercizio B
Due classi nello stesso package:

```text
src/com/example/fruits/Main.java
src/com/example/fruits/Apple.java
```

Domanda: serve import per Apple?

✅ Soluzione: **no**, perché è nello stesso package.

---

## 13) Riassunto ultra-chiaro

- **`package`**: definisce *dove vive* la classe e qual è il suo nome completo (FQN).
- **`import`**: rende comodo *usare* classi (o membri statici) di altri package.
- Puoi avere **entrambi** nello stesso file (normalissimo).
- Se tutto è nello stesso package, spesso **non ti serve import**.
- Evita il **default package** nei progetti seri.

---

## 14) Cheat-sheet finale

### Ho scritto `package com.example.fruits;` → dove deve stare il file?
```text
src/com/example/fruits/NomeClasse.java
```

### Quando devo usare `import`?
- quando usi classi fuori dal package corrente
- quando usi classi della libreria standard (es. `java.util.Scanner`)

### Posso non usare `import`?
Sì, scrivendo il nome completo:
```java
java.util.Scanner sc = new java.util.Scanner(System.in);
```

---

Se vuoi, posso aggiungere anche una sezione “**errori tipici e come risolverli**” (ClassNotFoundException, package mismatch, classpath, ecc.) con esempi reali da IntelliJ/terminal.
