# Exception in Java – Codici + Spiegazione riga per riga

Questo file contiene **tutti i codici** visti nella chat sulle *Exception* in Java, e per ciascuno trovi una spiegazione **chiara e completa**, riga per riga (o blocco per blocco quando ha più senso).

---

## 0) Concetto base: cos’è un’Exception

Un’**exception** è un oggetto che rappresenta un errore (o una condizione anomala) che interrompe il flusso “normale” del programma.

Quando un’exception “parte” (si dice *viene lanciata / thrown*), Java:

1. interrompe l’esecuzione del blocco corrente
2. cerca un `catch` compatibile
3. se lo trova, entra nel `catch`
4. poi esegue il `finally` (se presente)
5. se non trova un `catch`, il programma termina e stampa lo **stack trace**

---

## 1) Divisione per zero (ArithmeticException)

### Codice

```java
int a = 10;
int b = 0;
int c = a / b;  // ArithmeticException
```

### Spiegazione riga per riga

- `int a = 10;`  
  Dichiari una variabile intera `a` e le assegni il valore 10.

- `int b = 0;`  
  Dichiari una variabile intera `b` e le assegni 0.

- `int c = a / b;`  
  Provi a calcolare `a / b`, cioè `10 / 0`.  
  In matematica e in Java **non è permesso dividere per zero** sugli interi, quindi la JVM lancia una **ArithmeticException** (una *RuntimeException*).

- `// ArithmeticException`  
  Commento: ti sta dicendo che su quella riga avverrà l’eccezione.

**Nota importante:** l’exception si verifica **prima** che `c` possa ricevere un valore. Quindi il programma “salta” fuori da quella riga e cerca un `catch`.

---

## 2) Struttura try / catch / finally

### Struttura base

```java
try {
    // codice che può generare errore
} catch (ExceptionTipo e) {
    // gestione errore
} finally {
    // eseguito sempre
}
```

### Spiegazione (concetti chiave)

- `try { ... }`  
  Dentro `try` metti il codice che **potrebbe** generare un’eccezione.

- `catch (ExceptionTipo e) { ... }`  
  Se nel `try` succede un’eccezione compatibile con `ExceptionTipo`, Java entra qui.  
  `e` è la variabile che contiene l’oggetto eccezione (con messaggio, tipo, stack trace, ecc.).

- `finally { ... }`  
  Questo blocco viene eseguito **sempre**:  
  - sia se il `try` va bene  
  - sia se scatta un’eccezione (e viene catturata)  
  - sia se scatta un’eccezione e viene rilanciata (con alcune eccezioni rare tipo `System.exit`, crash JVM, ecc.)

Il `finally` si usa spesso per **chiudere risorse** (file, connessioni, ecc.) o per fare cleanup.

---

## 3) Array fuori limite (ArrayIndexOutOfBoundsException) + finally

### Codice

```java
try {
    int[] numeri = new int[3];
    System.out.println(numeri[5]);
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("Indice fuori limite!");
} finally {
    System.out.println("Blocco terminato.");
}
```

### Spiegazione riga per riga

- `try {`  
  Inizia il blocco “a rischio”.

- `int[] numeri = new int[3];`  
  Crea un array di `int` lungo **3**.  
  Gli indici validi sono: `0, 1, 2`.  
  (Perché un array di lunghezza N ha indici da 0 a N-1.)

- `System.out.println(numeri[5]);`  
  Provi a leggere l’elemento in posizione **5**, ma non esiste (l’array arriva a 2).  
  Quindi Java lancia `ArrayIndexOutOfBoundsException`.

- `} catch (ArrayIndexOutOfBoundsException e) {`  
  Stai dicendo: “Se l’errore è *indice fuori limite*, gestiscilo qui”.

- `System.out.println("Indice fuori limite!");`  
  Messaggio personalizzato per l’utente.

- `} finally {`  
  Entra nel `finally` **sempre**, anche se c’è stata eccezione.

- `System.out.println("Blocco terminato.");`  
  Stampa che il blocco è finito.  
  In output, vedrai prima “Indice fuori limite!”, poi “Blocco terminato.”

---

## 4) Più catch: ordine corretto (specifico → generico)

### Codice corretto

```java
try {
    int x = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Divisione per zero!");
} catch (Exception e) {
    System.out.println("Errore generico");
}
```

### Spiegazione riga per riga

- `try { int x = 10 / 0; }`  
  Nel try fai una divisione per zero → scatta `ArithmeticException`.

- `catch (ArithmeticException e) { ... }`  
  Questo è il catch **più specifico** per la divisione per zero sugli interi.  
  Siccome l’eccezione è proprio `ArithmeticException`, Java entra qui.

- `catch (Exception e) { ... }`  
  Questo è più generico: `Exception` “copre” tante eccezioni.  
  Serve come “paracadute” per errori che non hai gestito prima.

### Perché l’ordine è fondamentale?

Perché `Exception` è “padre” di molte eccezioni (tra cui `ArithmeticException`).  
Se mettessi `catch (Exception e)` prima, cattureresti tutto lì e il catch specifico non verrebbe mai raggiunto.

---

## 5) Più catch: ordine sbagliato (errore “unreachable catch”)

### Codice sbagliato

```java
try {
    int x = 10 / 0;
} catch (Exception e) {
    System.out.println("Errore generico");
} catch (ArithmeticException e) {
    System.out.println("Divisione per zero!");
}
```

### Spiegazione del perché è sbagliato

- Quando scrivi `catch (Exception e)` stai già dicendo a Java:
  “Cattura QUALSIASI eccezione che è una `Exception` (incluse le sue sottoclassi)”.

- `ArithmeticException` **è una sottoclasse** di `Exception`.

Quindi, nel momento in cui c’è una `ArithmeticException`, Java entrerebbe **sempre** nel primo `catch (Exception e)`.  
Il secondo catch sarebbe **impossibile da raggiungere** → Java segnala errore in compilazione (*unreachable catch block*).

---

## 6) Problema reale del tuo progetto: hai chiamato la classe `Exception`

Tu avevi questo file:

```java
package com.example.exception;

import java.io.IOException;

public class Exception {
    public static void main(String[] args) {
        // ...
    }
}
```

### Perché è un problema?

Perché Java ha già una classe fondamentale chiamata `java.lang.Exception`.  
Se tu crei una classe con lo stesso nome `Exception` **nel tuo package**, quando scrivi:

```java
catch (Exception e)
```

Java “vede” prima `com.example.exception.Exception` (la tua) e **non** `java.lang.Exception`.

### E perché compare l’errore:
> com.example.exception.Exception cannot be converted to java.lang.Throwable

Un `catch(...)` può accettare **solo tipi che estendono `Throwable`**.

- `java.lang.Exception` estende `Throwable` ✅
- La tua `com.example.exception.Exception` **NON** estende `Throwable` ❌

Quindi il compilatore ti ferma.

### Soluzione corretta

1) **Rinomina la classe** (migliore soluzione)  
Esempio: `ExceptionsDemo` o `Main`.

2) In emergenza, puoi scrivere esplicitamente:

```java
catch (java.lang.Exception e)
```

ma è meglio rinominare la tua classe.

---

## 7) Altro problema nel tuo codice: nel primo try non stavi dividendo

Tu avevi:

```java
int var_dividendo = 50;
int var_divisor = 0;

try {
    var_dividendo = var_divisor + var_dividendo;
}
catch (ArithmeticException e) {
    System.out.println(e.getMessage());
}
```

### Perché NON scatta ArithmeticException?

Perché fai una **somma**, non una divisione.

- `var_divisor + var_dividendo` = `0 + 50` = `50`  
  Nessuna eccezione.

### Se vuoi testare davvero ArithmeticException devi fare:

```java
int risultato = var_dividendo / var_divisor; // 50 / 0 -> ArithmeticException
```

---

## 8) Versione corretta e pulita del tuo file (con commenti utili)

```java
package com.example.exception;

public class ExceptionsDemo {
    public static void main(String[] args) {

        int var_dividendo = 50;
        int var_divisor = 0;

        // 1) TEST: divisione per zero -> ArithmeticException
        try {
            int risultato = var_dividendo / var_divisor;
            System.out.println(risultato); // non verrà eseguito se scatta eccezione
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage()); // messaggio dell'eccezione (spesso "/ by zero")
        }

        // 2) TEST: indice array fuori limite
        try {
            int[] numeri = new int[3];              // indici validi 0..2
            System.out.println(numeri[5]);          // indice 5 -> eccezione
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Indice fuori limite!");
        } finally {
            System.out.println("Blocco terminato.");
        }

        // 3) TEST: multi-catch in ordine corretto
        try {
            int x = 10 / 0;                         // eccezione qui
            System.out.println(x);
        } catch (ArithmeticException e) {           // specifica
            System.out.println("Divisione per zero!");
        } catch (Exception e) {                     // generica
            System.out.println("Errore generico");
        }
    }
}
```

### Spiegazione dei punti chiave

- **Nome classe**: `ExceptionsDemo` (non “Exception”) per evitare conflitti.
- **Divisione per zero**: ora è davvero una divisione, quindi l’eccezione scatta.
- **Catch order**: `ArithmeticException` prima, poi `Exception`.
- **Finally**: viene eseguito sempre.

---

## 9) throw: lanciare manualmente un’eccezione

### Codice

```java
public void controllaEta(int eta) {
    if (eta < 18) {
        throw new IllegalArgumentException("Sei minorenne");
    }
}
```

### Spiegazione riga per riga

- `public void controllaEta(int eta) {`  
  Metodo che riceve un’età.

- `if (eta < 18) {`  
  Condizione: se età minore di 18…

- `throw new IllegalArgumentException("Sei minorenne");`  
  Crei un oggetto eccezione e lo **lanci**.  
  Da quel punto in poi, il metodo **si interrompe** e torna al chiamante cercando un `catch` adatto.

- `}` / `}`  
  Fine blocco `if` e fine metodo.

**Quando si usa:** per validare parametri o stati invalidi.

---

## 10) throws: dichiarare che il metodo può lanciare (Checked Exception)

### Codice

```java
import java.io.IOException;

public void leggiFile() throws IOException {
    // codice che può generare IOException
}
```

### Spiegazione riga per riga

- `import java.io.IOException;`  
  Importi la classe `IOException`.

- `public void leggiFile() throws IOException {`  
  Stai dicendo: “Questo metodo **può** lanciare IOException e NON la gestisco qui dentro”.

Con le *checked exception* Java ti obbliga:  
- o le gestisci con `try/catch`
- o le dichiari con `throws`

---

## 11) Eccezione personalizzata (custom exception)

### Classe eccezione

```java
public class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}
```

### Spiegazione riga per riga

- `public class InvalidAgeException extends Exception {`  
  Stai creando una nuova eccezione che è una vera exception perché **estende** `java.lang.Exception`.

- `public InvalidAgeException(String message) {`  
  Costruttore che riceve un messaggio.

- `super(message);`  
  Passi il messaggio al costruttore della classe padre (`Exception`) così `getMessage()` funzionerà.

- `}` / `}`  
  Fine costruttore e fine classe.

### Uso della custom exception

```java
public void controllaEta(int eta) throws InvalidAgeException {
    if (eta < 18) {
        throw new InvalidAgeException("Età non valida");
    }
}
```

- `throws InvalidAgeException`  
  Il metodo dichiara che può lanciare la tua exception.

- `throw new InvalidAgeException(...)`  
  La lanci quando la condizione è vera.

---

## 12) Best practice fondamentale: NON “mangiare” le exception

### Codice sbagliato (da evitare)

```java
catch (Exception e) {
}
```

### Perché è sbagliato?

Perché stai nascondendo l’errore.  
Se qualcosa va male, tu non lo sai, e il programma continua in uno stato potenzialmente incoerente.

### Minimo indispensabile

```java
catch (Exception e) {
    e.printStackTrace();
}
```

`printStackTrace()` stampa lo stack trace: ti dice **dove** e **perché** è successo l’errore.

---

## 13) Stack trace: cos’è e come leggerlo

Esempio tipico:

```
Exception in thread "main" java.lang.ArithmeticException: / by zero
    at Main.main(Main.java:10)
```

- `java.lang.ArithmeticException: / by zero`  
  Tipo e messaggio.

- `at Main.main(Main.java:10)`  
  Punto preciso: file `Main.java`, riga 10, dentro `main`.

Lo stack trace è il tuo “GPS” per trovare l’errore.

---

## 14) Riassunto “da esame”

- **Metti nel `try`** il codice che può fallire.
- **Catch specifici prima**, catch generici dopo.
- **Non chiamare classi** con nomi come `Exception`, `String`, `List`, ecc.
- Usa `finally` (o meglio `try-with-resources`) per chiudere risorse.
- Non “mangiare” le exception: almeno logga o stampa lo stack trace.


# 15) Esempi completi con `throw` (come richiesto)

Qui trovi **due esempi**:  
1) `throw` con una **RuntimeException** (non serve `throws`)  
2) `throw` con una **Checked Exception personalizzata** (serve `throws` + `try/catch`)

---

## 15.1) `throw` con RuntimeException (IllegalArgumentException)

### Obiettivo
Bloccare un prelievo se l’importo è maggiore del saldo.

### Codice

```java
public class BankAccount {

    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public void withdraw(double amount) {

        if (amount > balance) {
            throw new IllegalArgumentException("Saldo insufficiente!");
        }

        balance -= amount;
        System.out.println("Prelievo effettuato. Nuovo saldo: " + balance);
    }

    public static void main(String[] args) {

        BankAccount account = new BankAccount(100);

        account.withdraw(50);   // ok
        account.withdraw(200);  // qui scatta l'eccezione
    }
}
```

### Spiegazione (riga per riga / blocco per blocco)

- `public class BankAccount { ... }`  
  Definisci una classe “conto bancario”.

- `private double balance;`  
  Attributo privato: il saldo non deve essere modificato direttamente dall’esterno.

- `public BankAccount(double balance) { this.balance = balance; }`  
  Costruttore: inizializza il saldo quando crei l’oggetto.

- `public void withdraw(double amount) { ... }`  
  Metodo per prelevare un importo `amount`.

- `if (amount > balance) { ... }`  
  Controllo: se prelevi più del saldo, il prelievo non deve andare avanti.

- `throw new IllegalArgumentException("Saldo insufficiente!");`  
  **Punto chiave**:
  1. crei un oggetto eccezione (`new IllegalArgumentException(...)`)
  2. lo **lanci** (`throw`)
  3. il metodo si interrompe subito (non esegue le righe sotto)
  4. se non c’è un `try/catch` che la intercetta, il programma termina e stampa lo stack trace

- `balance -= amount;`  
  Questa riga viene eseguita solo se **non** è stato fatto `throw`.

- `account.withdraw(50);`  
  50 ≤ 100 quindi non scatta l’eccezione, saldo diventa 50.

- `account.withdraw(200);`  
  200 > 50 quindi scatta `IllegalArgumentException`.

**Perché non serve `throws`?**  
Perché `IllegalArgumentException` è una **RuntimeException**: Java non ti obbliga a dichiararla o catturarla.

---

## 15.2) `throw` con Checked Exception personalizzata

### Obiettivo
Stessa logica del prelievo, ma usando una **checked exception** creata da te.  
Questo ti obbliga a gestirla (con `try/catch`) o dichiararla (`throws`).

---

### 15.2.1) Classe della custom exception

```java
public class InsufficientFundsException extends Exception {

    public InsufficientFundsException(String message) {
        super(message);
    }
}
```

#### Spiegazione

- `extends Exception`  
  Rendendola figlia di `Exception` (e non di `RuntimeException`), la tua eccezione diventa **checked**.

- `public InsufficientFundsException(String message) { super(message); }`  
  Salvi il messaggio dentro la classe padre, così `getMessage()` funziona.

---

### 15.2.2) Uso della custom exception nel conto

```java
public class BankAccount {

    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public void withdraw(double amount) throws InsufficientFundsException {

        if (amount > balance) {
            throw new InsufficientFundsException("Saldo insufficiente!");
        }

        balance -= amount;
        System.out.println("Prelievo effettuato. Nuovo saldo: " + balance);
    }

    public static void main(String[] args) {

        BankAccount account = new BankAccount(100);

        try {
            account.withdraw(200);
        } catch (InsufficientFundsException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }
}
```

#### Spiegazione (punto per punto)

- `public void withdraw(double amount) throws InsufficientFundsException`  
  Qui dichiari: “Questo metodo **può lanciare** `InsufficientFundsException`”.  
  È obbligatorio perché è **checked**.

- `if (amount > balance) { throw new InsufficientFundsException(...); }`  
  Se la condizione è vera, lanci la tua eccezione personalizzata.

- Nel `main`, quando chiami `withdraw`, devi gestire l’eccezione:

  - `try { account.withdraw(200); }`  
    Provi a fare l’operazione che può fallire.

  - `catch (InsufficientFundsException e) { ... }`  
    Se l’eccezione viene lanciata, entri nel catch e gestisci l’errore.

---

## 15.3) Differenza fondamentale: RuntimeException vs Checked Exception

| Tipo | Esempio | Serve `throws`? | Serve `try/catch`? | Quando usarla |
|------|---------|------------------|--------------------|---------------|
| RuntimeException (Unchecked) | `IllegalArgumentException` | No | No (ma puoi) | errori di programmazione / input non valido |
| Checked Exception | `IOException`, `InsufficientFundsException` | Sì | Sì (o rilanci) | errori “gestibili” che vuoi forzare a gestire |

---

## 15.4) Cosa devi ricordare di `throw`

- `throw` **interrompe** subito il metodo nel punto in cui viene eseguito.
- `throw` lancia un **oggetto** che deve essere `Throwable` (o una sua sottoclasse).
- Se non c’è un `catch` compatibile “più in alto”, il programma termina e stampa lo stack trace.
