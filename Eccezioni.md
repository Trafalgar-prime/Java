# Eccezioni in Java

Certo. In Java le eccezioni servono a gestire situazioni anomale che possono accadere durante l’esecuzione del programma.

Esempi:

```java
int risultato = 10 / 0;
```

Qui Java genera un errore perché non puoi dividere per zero.

Oppure:

```java
int numero = Integer.parseInt("ciao");
```

Qui Java prova a trasformare `"ciao"` in numero, ma non può farlo.

## 1. Che cos’è un’eccezione?

Un’eccezione è un oggetto che rappresenta un problema avvenuto durante l’esecuzione.

Tutte le eccezioni derivano da questa gerarchia:

```text
Throwable
├── Error
└── Exception
    ├── RuntimeException
    └── altre eccezioni controllate
```

La parte importante è questa:

```java
Exception
```

Dentro `Exception` troviamo due grandi famiglie:

## 2. Checked Exception e Unchecked Exception

### Checked Exception

Sono eccezioni che Java ti obbliga a gestire.

Esempio:

```java
FileReader file = new FileReader("test.txt");
```

Questo può generare:

```java
FileNotFoundException
```

Java ti dice:

Questo file potrebbe non esistere. Devi gestire il problema.

Quindi sei obbligato a usare:

```java
try-catch
```

oppure:

```java
throws
```

Esempi di checked exception:

```java
IOException
FileNotFoundException
SQLException
ClassNotFoundException
```

### Unchecked Exception

Sono eccezioni che Java non ti obbliga a gestire.

Esempio:

```java
int x = 10 / 0;
```

Genera:

```java
ArithmeticException
```

Esempi di unchecked exception:

```java
ArithmeticException
NullPointerException
ArrayIndexOutOfBoundsException
NumberFormatException
IllegalArgumentException
```

Queste derivano da:

```java
RuntimeException
```

## 3. Metodo 1: usare try-catch

Il modo più classico è:

```java
try {
    // codice che potrebbe generare errore
} catch (TipoEccezione e) {
    // codice da eseguire se avviene l'errore
}
```

Esempio:

```java
public class Main {
    public static void main(String[] args) {

        try {
            int risultato = 10 / 0;
            System.out.println(risultato);
        } catch (ArithmeticException e) {
            System.out.println("Errore: divisione per zero");
        }

        System.out.println("Il programma continua");
    }
}
```

Cosa succede?

`try`

contiene il codice rischioso.

```java
int risultato = 10 / 0;
```

genera un’eccezione.

Java interrompe il blocco `try` e passa subito al `catch`.

```java
catch (ArithmeticException e)
```

cattura l’errore.

Il programma non si blocca.

## 4. L’oggetto e

Quando scrivi:

```java
catch (ArithmeticException e)
```

`e` è l’oggetto eccezione.

Puoi usarlo così:

```java
System.out.println(e.getMessage());
```

Esempio:

```java
try {
    int risultato = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println(e.getMessage());
}
```

Output:

```text
/ by zero
```

Puoi anche stampare tutta la traccia dell’errore:

```java
e.printStackTrace();
```

Esempio:

```java
try {
    int risultato = 10 / 0;
} catch (ArithmeticException e) {
    e.printStackTrace();
}
```

Questo mostra dove è avvenuto l’errore.

## 5. Metodo 2: più catch

Puoi gestire errori diversi in modi diversi.

```java
public class Main {
    public static void main(String[] args) {

        try {
            String testo = "ciao";
            int numero = Integer.parseInt(testo);

            int risultato = 10 / numero;

            System.out.println(risultato);

        } catch (NumberFormatException e) {
            System.out.println("Errore: la stringa non è un numero");

        } catch (ArithmeticException e) {
            System.out.println("Errore: divisione per zero");
        }
    }
}
```

Qui possono succedere due errori:

```java
Integer.parseInt(testo)
```

può generare:

```java
NumberFormatException
```

mentre:

```java
10 / numero
```

può generare:

```java
ArithmeticException
```

Java esegue solo il primo `catch` compatibile.

## 6. Attenzione all’ordine dei catch

Questo è sbagliato:

```java
try {
    int x = 10 / 0;
} catch (Exception e) {
    System.out.println("Errore generico");
} catch (ArithmeticException e) {
    System.out.println("Divisione per zero");
}
```

Perché?

Perché `Exception` è più generale di `ArithmeticException`.

Java dice:

Se catturi già tutte le eccezioni con `Exception`, il `catch` specifico dopo non verrà mai raggiunto.

Corretto:

```java
try {
    int x = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Divisione per zero");
} catch (Exception e) {
    System.out.println("Errore generico");
}
```

Prima le eccezioni specifiche, poi quelle generiche.

## 7. Metodo 3: multi-catch

Se vuoi gestire più eccezioni nello stesso modo:

```java
try {
    String testo = "ciao";
    int numero = Integer.parseInt(testo);
    int risultato = 10 / numero;
} catch (NumberFormatException | ArithmeticException e) {
    System.out.println("Errore nei dati numerici");
}
```

Qui il `catch` cattura sia:

```java
NumberFormatException
```

sia:

```java
ArithmeticException
```

È utile quando vuoi fare la stessa cosa per più errori.

## 8. Metodo 4: finally

Il blocco `finally` viene eseguito sempre.

```java
try {
    int risultato = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Errore: divisione per zero");
} finally {
    System.out.println("Questo viene eseguito sempre");
}
```

Output:

```text
Errore: divisione per zero
Questo viene eseguito sempre
```

Serve soprattutto per chiudere risorse:

```java
Scanner scanner = null;

try {
    scanner = new Scanner(System.in);
    int numero = scanner.nextInt();
} catch (Exception e) {
    System.out.println("Errore");
} finally {
    if (scanner != null) {
        scanner.close();
    }
}
```

Il `finally` viene eseguito anche se non c’è errore.

## 9. Metodo 5: try-catch-finally completo

```java
try {
    int x = 10 / 2;
    System.out.println(x);
} catch (ArithmeticException e) {
    System.out.println("Errore matematico");
} finally {
    System.out.println("Fine controllo");
}
```

Output:

```text
5
Fine controllo
```

Qui non c’è errore, quindi il `catch` non viene eseguito, ma il `finally` sì.

## 10. Metodo 6: throw

`throw` serve a lanciare manualmente un’eccezione.

Esempio:

```java
public class Main {
    public static void main(String[] args) {

        int eta = 15;

        if (eta < 18) {
            throw new IllegalArgumentException("Devi essere maggiorenne");
        }

        System.out.println("Accesso consentito");
    }
}
```

Qui sei tu che decidi di generare un errore.

```java
throw new IllegalArgumentException("Devi essere maggiorenne");
```

significa:

Creo e lancio un’eccezione perché il valore non è valido.

`IllegalArgumentException` si usa quando un argomento passato non è accettabile.

## 11. throw dentro un metodo

Esempio più corretto:

```java
public class Main {

    public static void controllaEta(int eta) {
        if (eta < 18) {
            throw new IllegalArgumentException("Età non valida: devi essere maggiorenne");
        }

        System.out.println("Età valida");
    }

    public static void main(String[] args) {
        controllaEta(15);
    }
}
```

Il metodo:

```java
controllaEta
```

controlla il valore.

Se il valore non va bene, lancia un’eccezione.

## 12. Metodo 7: throws

`throws` non gestisce l’eccezione.

`throws` dice:

Questo metodo può generare questa eccezione. Chi lo chiama dovrà gestirla.

Esempio:

```java
import java.io.FileReader;
import java.io.FileNotFoundException;

public class Main {

    public static void leggiFile() throws FileNotFoundException {
        FileReader file = new FileReader("test.txt");
    }

    public static void main(String[] args) {
        try {
            leggiFile();
        } catch (FileNotFoundException e) {
            System.out.println("File non trovato");
        }
    }
}
```

Qui:

```java
public static void leggiFile() throws FileNotFoundException
```

significa:

Il metodo `leggiFile` potrebbe generare `FileNotFoundException`.

Il metodo non la gestisce direttamente.

La gestione avviene nel `main`.

## 13. Differenza tra throw e throws

Questa è importantissima.

### throw

Lancia davvero un’eccezione.

```java
throw new IllegalArgumentException("Errore");
```

È un’azione.

### throws

Dichiara che un metodo può lanciare un’eccezione.

```java
public void leggiFile() throws IOException
```

È una dichiarazione.

Tabella:

| Keyword | Cosa fa | Dove si usa |
|---|---|---|
| throw | lancia un’eccezione | dentro il metodo |
| throws | dichiara un’eccezione possibile | nella firma del metodo |

## 14. Esempio insieme: throw + throws

```java
public class Main {

    public static void controllaVoto(int voto) throws Exception {
        if (voto < 0 || voto > 10) {
            throw new Exception("Voto non valido");
        }

        System.out.println("Voto valido");
    }

    public static void main(String[] args) {
        try {
            controllaVoto(15);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
```

Qui:

```java
throws Exception
```

dice che il metodo può generare un’eccezione.

```java
throw new Exception(...)
```

lancia davvero l’eccezione.

## 15. Metodo 8: creare eccezioni personalizzate

Puoi creare le tue eccezioni.

Esempio:

```java
class EtaNonValidaException extends Exception {

    public EtaNonValidaException(String messaggio) {
        super(messaggio);
    }
}
```

Poi la usi così:

```java
public class Main {

    public static void controllaEta(int eta) throws EtaNonValidaException {
        if (eta < 18) {
            throw new EtaNonValidaException("Età non valida: devi essere maggiorenne");
        }

        System.out.println("Accesso consentito");
    }

    public static void main(String[] args) {
        try {
            controllaEta(16);
        } catch (EtaNonValidaException e) {
            System.out.println(e.getMessage());
        }
    }
}
```

Spiegazione:

```java
class EtaNonValidaException extends Exception
```

crea una nuova eccezione controllata.

```java
super(messaggio);
```

passa il messaggio alla classe madre `Exception`.

## 16. Eccezione personalizzata unchecked

Se vuoi creare un’eccezione non obbligatoria da gestire:

```java
class EtaNonValidaException extends RuntimeException {

    public EtaNonValidaException(String messaggio) {
        super(messaggio);
    }
}
```

Uso:

```java
public class Main {

    public static void controllaEta(int eta) {
        if (eta < 18) {
            throw new EtaNonValidaException("Età non valida");
        }

        System.out.println("Accesso consentito");
    }

    public static void main(String[] args) {
        controllaEta(15);
    }
}
```

Qui non sei obbligato a mettere `throws` o `try-catch`.

## 17. Quando usare Exception e quando RuntimeException

Usa `Exception` quando:

il problema è esterno al programma e il chiamante dovrebbe gestirlo.

Esempi:

- file non trovato
- database non raggiungibile
- connessione interrotta
- input/output fallito

Usa `RuntimeException` quando:

il problema dipende da un errore logico del programmatore o da un valore non valido.

Esempi:

- parametro sbagliato
- indice fuori limite
- oggetto null
- argomento non valido
- stato non valido

## 18. Metodo 9: try-with-resources

È un modo moderno per chiudere automaticamente risorse.

Esempio:

```java
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try (FileReader file = new FileReader("test.txt")) {
            int carattere = file.read();
            System.out.println(carattere);

        } catch (IOException e) {
            System.out.println("Errore nella lettura del file");
        }
    }
}
```

Questa parte:

```java
try (FileReader file = new FileReader("test.txt"))
```

significa:

Apro una risorsa e Java la chiuderà automaticamente alla fine.

Non serve scrivere:

```java
file.close();
```

È molto usato con:

```java
FileReader
BufferedReader
Scanner
Connection
PreparedStatement
ResultSet
```

## 19. Metodo 10: rilanciare un’eccezione

Puoi catturare un’eccezione e poi rilanciarla.

```java
public static void metodo() throws Exception {
    try {
        int x = 10 / 0;
    } catch (ArithmeticException e) {
        System.out.println("Errore intercettato, ma lo rilancio");
        throw e;
    }
}
```

Significa:

Ho visto l’errore, magari faccio un log, ma poi lo mando a chi ha chiamato il metodo.

## 20. Metodo 11: incapsulare un’eccezione dentro un’altra

Esempio:

```java
public static void metodo() throws Exception {
    try {
        int x = Integer.parseInt("ciao");
    } catch (NumberFormatException e) {
        throw new Exception("Errore durante la conversione del numero", e);
    }
}
```

Questa parte:

```java
throw new Exception("Errore durante la conversione del numero", e);
```

crea una nuova eccezione, ma conserva quella originale.

È utile perché mantieni la causa reale.

## 21. Metodo 12: gestione generica con Exception

Puoi fare:

```java
try {
    int x = Integer.parseInt("ciao");
} catch (Exception e) {
    System.out.println("Errore generico");
}
```

Funziona, ma non è sempre consigliato.

Perché?

Perché cattura tutto e perdi precisione.

Meglio:

```java
try {
    int x = Integer.parseInt("ciao");
} catch (NumberFormatException e) {
    System.out.println("Il testo non è un numero");
}
```

Usa `Exception` solo quando vuoi davvero catturare molti tipi di errore.

## 22. Metodo 13: validazione con eccezioni

Esempio pratico:

```java
public class Main {

    public static void registraUtente(String nome, int eta) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Il nome non può essere vuoto");
        }

        if (eta < 0) {
            throw new IllegalArgumentException("L'età non può essere negativa");
        }

        System.out.println("Utente registrato");
    }

    public static void main(String[] args) {
        registraUtente("", 20);
    }
}
```

Qui usiamo le eccezioni per bloccare dati non validi.

## 23. Eccezioni molto comuni

### NullPointerException

Succede quando usi un oggetto `null`.

```java
String nome = null;
System.out.println(nome.length());
```

Errore perché `nome` non punta a nessun oggetto.

### ArrayIndexOutOfBoundsException

Succede quando esci dai limiti di un array.

```java
int[] numeri = {1, 2, 3};
System.out.println(numeri[5]);
```

L’indice `5` non esiste.

### NumberFormatException

Succede quando converti male una stringa.

```java
int n = Integer.parseInt("ciao");
```

### ArithmeticException

Esempio classico:

```java
int x = 10 / 0;
```

### IllegalArgumentException

Quando un argomento non è valido.

```java
public static void setEta(int eta) {
    if (eta < 0) {
        throw new IllegalArgumentException("Età negativa");
    }
}
```

### IllegalStateException

Quando l’oggetto è in uno stato non valido.

```java
if (!connessioneAperta) {
    throw new IllegalStateException("La connessione non è aperta");
}
```

## 24. Schema mentale definitivo

Quando scrivi codice Java, ragiona così:

### Caso 1: voglio provare un codice rischioso

Uso:

```java
try-catch
```

### Caso 2: voglio eseguire codice sempre alla fine

Uso:

```java
finally
```

### Caso 3: voglio chiudere automaticamente una risorsa

Uso:

```java
try-with-resources
```

### Caso 4: voglio generare io un errore

Uso:

```java
throw
```

### Caso 5: voglio dire che il metodo può generare errore

Uso:

```java
throws
```

### Caso 6: voglio creare un errore mio

Creo una classe:

```java
class MiaException extends Exception
```

oppure:

```java
class MiaException extends RuntimeException
```

## 25. Esempio finale completo

```java
class SaldoInsufficienteException extends Exception {

    public SaldoInsufficienteException(String messaggio) {
        super(messaggio);
    }
}

class ContoBancario {

    private double saldo;

    public ContoBancario(double saldo) {
        if (saldo < 0) {
            throw new IllegalArgumentException("Il saldo iniziale non può essere negativo");
        }

        this.saldo = saldo;
    }

    public void preleva(double importo) throws SaldoInsufficienteException {
        if (importo <= 0) {
            throw new IllegalArgumentException("L'importo deve essere positivo");
        }

        if (importo > saldo) {
            throw new SaldoInsufficienteException("Saldo insufficiente");
        }

        saldo -= importo;
        System.out.println("Prelievo effettuato. Saldo rimasto: " + saldo);
    }
}

public class Main {

    public static void main(String[] args) {

        try {
            ContoBancario conto = new ContoBancario(100);
            conto.preleva(150);

        } catch (SaldoInsufficienteException e) {
            System.out.println("Errore bancario: " + e.getMessage());

        } catch (IllegalArgumentException e) {
            System.out.println("Dato non valido: " + e.getMessage());

        } catch (Exception e) {
            System.out.println("Errore generico: " + e.getMessage());

        } finally {
            System.out.println("Operazione terminata");
        }
    }
}
```

Qui hai quasi tutto:

```java
extends Exception
```

per creare un’eccezione personalizzata controllata.

```java
throw new IllegalArgumentException(...)
```

per lanciare un errore su dati sbagliati.

```java
throws SaldoInsufficienteException
```

per dichiarare che il metodo `preleva` può generare quell’eccezione.

```java
try
```

per provare il codice.

```java
catch
```

per gestire gli errori.

```java
finally
```

per eseguire codice finale sempre.

## Riassunto secco

### try

prova a eseguire codice rischioso.

### catch

cattura l’errore.

### finally

viene eseguito sempre.

### throw

lancia manualmente un’eccezione.

### throws

dichiara che un metodo può lanciare un’eccezione.

### Exception

è controllata, quindi spesso Java obbliga a gestirla.

### RuntimeException

non è controllata, quindi Java non obbliga a gestirla.

### extends Exception

crea una checked exception personalizzata.

### extends RuntimeException

crea una unchecked exception personalizzata.
