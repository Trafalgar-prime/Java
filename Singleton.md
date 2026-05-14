# Singleton in Java

Il Singleton è un design pattern: serve a fare in modo che di una classe esista un solo oggetto in tutto il programma, e che tutti usino sempre quello stesso oggetto. È usato quando vuoi una sola istanza condivisa, per esempio per configurazioni, logger, gestione impostazioni, o oggetti centralizzati.

## 1. Idea semplice

Normalmente puoi fare:

```java
Persona p1 = new Persona();
Persona p2 = new Persona();
Persona p3 = new Persona();
```

Qui hai creato tre oggetti diversi.

Con il Singleton invece vuoi impedire questo:

```java
Database db1 = Database.getInstance();
Database db2 = Database.getInstance();
Database db3 = Database.getInstance();
```

Anche se lo chiami tre volte, ottieni sempre lo stesso oggetto.

## 2. Struttura base di un Singleton

Un Singleton di solito ha tre elementi:

```java
public class Singleton {

    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }

        return instance;
    }
}
```

Adesso lo analizziamo bene.

## 3. Il costruttore privato

```java
private Singleton() {
}
```

Questa è la parte più importante.

Normalmente un costruttore pubblico permette di fare:

```java
Singleton s = new Singleton();
```

Ma se il costruttore è private, questa cosa dall’esterno non si può fare.

Quindi questo codice dà errore:

```java
Singleton s = new Singleton();
```

Perché?

Perché il costruttore è privato.

Quindi nessuna classe esterna può creare nuovi oggetti Singleton.

## 4. La variabile statica

```java
private static Singleton instance;
```

Questa variabile serve a conservare l’unico oggetto della classe.

È static, quindi appartiene alla classe, non al singolo oggetto.

Significa che puoi usarla senza creare un oggetto prima.

## 5. Il metodo getInstance()

```java
public static Singleton getInstance() {
    if (instance == null) {
        instance = new Singleton();
    }

    return instance;
}
```

Questo metodo è il punto di accesso al Singleton.

Funziona così:

Prima volta:

```java
instance == null
```

quindi Java crea l’oggetto:

```java
instance = new Singleton();
```

Seconda volta:

```java
instance != null
```

quindi Java non crea nulla e restituisce lo stesso oggetto già esistente.

## 6. Esempio completo

```java
class Database {

    private static Database instance;

    private Database() {
        System.out.println("Database creato");
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }

        return instance;
    }

    public void connetti() {
        System.out.println("Connessione al database...");
    }
}

public class Main {

    public static void main(String[] args) {

        Database db1 = Database.getInstance();
        Database db2 = Database.getInstance();

        db1.connetti();
        db2.connetti();

        System.out.println(db1 == db2);
    }
}
```

Output:

```text
Database creato
Connessione al database...
Connessione al database...
true
```

Attenzione a questa riga:

```java
System.out.println(db1 == db2);
```

Stampa:

```text
true
```

Perché db1 e db2 puntano allo stesso oggetto.

## 7. Perché stampa “Database creato” una volta sola?

Perché la prima volta:

```java
Database db1 = Database.getInstance();
```

instance è null, quindi viene creato l’oggetto.

La seconda volta:

```java
Database db2 = Database.getInstance();
```

instance non è più null, quindi Java restituisce l’oggetto già creato.

## 8. Versione “lazy”

Quella appena vista è una versione lazy.

Lazy significa:

creo l’oggetto solo quando mi serve davvero.

```java
public static Database getInstance() {
    if (instance == null) {
        instance = new Database();
    }

    return instance;
}
```

L’oggetto non nasce subito all’avvio del programma.

Nasce solo quando chiami:

```java
Database.getInstance();
```

## 9. Problema nei programmi con più thread

La versione semplice va bene per studiare, ma ha un problema.

Se hai più thread, due thread potrebbero entrare insieme qui:

```java
if (instance == null) {
    instance = new Database();
}
```

E potrebbero creare due oggetti.

Questo rompe il Singleton.

Per questo nei programmi multi-thread bisogna fare attenzione. Fonti Java spiegano che la lazy initialization nei Singleton richiede attenzione in contesti multi-thread, perché due thread potrebbero provare a creare l’istanza nello stesso momento.

## 10. Versione synchronized

Una soluzione semplice è usare synchronized.

```java
class Database {

    private static Database instance;

    private Database() {
    }

    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }

        return instance;
    }
}
```

Questa riga:

```java
public static synchronized Database getInstance()
```

significa che un solo thread alla volta può entrare nel metodo.

Quindi impedisci che due thread creino due oggetti insieme.

Svantaggio: può essere meno efficiente, perché sincronizza ogni chiamata, anche quando l’oggetto è già stato creato.

## 11. Versione eager

Eager significa:

creo subito l’oggetto quando la classe viene caricata.

```java
class Database {

    private static final Database INSTANCE = new Database();

    private Database() {
    }

    public static Database getInstance() {
        return INSTANCE;
    }
}
```

Qui l’oggetto viene creato subito:

```java
private static final Database INSTANCE = new Database();
```

Il metodo:

```java
public static Database getInstance() {
    return INSTANCE;
}
```

restituisce sempre quello stesso oggetto.

Questa versione è semplice e sicura, ma crea l’oggetto anche se magari non lo userai mai.

## 12. Versione consigliata: holder statico

Una versione molto usata e pulita è questa:

```java
class Database {

    private Database() {
    }

    private static class Holder {
        private static final Database INSTANCE = new Database();
    }

    public static Database getInstance() {
        return Holder.INSTANCE;
    }
}
```

Questa tecnica si chiama spesso Initialization-on-demand holder idiom. È una forma di Singleton lazy e thread-safe: l’istanza viene creata solo quando viene richiesto getInstance(), sfruttando il caricamento delle classi interne da parte della JVM.

Spiegazione:

```java
private static class Holder
```

è una classe interna.

Dentro ha:

```java
private static final Database INSTANCE = new Database();
```

L’istanza viene creata solo quando chiami:

```java
Holder.INSTANCE
```

cioè dentro:

```java
getInstance()
```

È una delle versioni migliori da ricordare.

## 13. Versione con enum

In Java puoi fare anche un Singleton con enum.

```java
enum Database {
    INSTANCE;

    public void connetti() {
        System.out.println("Connessione al database...");
    }
}
```

Uso:

```java
public class Main {

    public static void main(String[] args) {

        Database db = Database.INSTANCE;

        db.connetti();
    }
}
```

Questa versione è molto robusta.

Il Singleton con enum è spesso consigliato perché Java garantisce una sola istanza dell’enum e protegge meglio da problemi come serializzazione e reflection.

## 14. Quando usare Singleton?

Usalo quando ha senso avere un solo oggetto condiviso.

Esempi:

- configurazione dell’applicazione
- logger
- gestore impostazioni
- cache centrale
- gestore connessioni

Esempio concettuale:

```java
Configurazione config = Configurazione.getInstance();
```

Tutti leggono la stessa configurazione.

## 15. Quando NON usarlo?

Non usare Singleton ovunque.

È comodo, ma può diventare pericoloso perché crea uno stato globale nascosto.

Problemi possibili:

- rende i test più difficili
- crea dipendenze nascoste
- può rendere il codice meno flessibile
- può essere abusato al posto di una buona progettazione

Quindi il Singleton va usato con criterio.

## 16. Schema mentale definitivo

Un Singleton ha:

```text
private constructor
```

per impedire new.

```text
private static instance
```

per conservare l’unico oggetto.

```text
public static getInstance()
```

per ottenere sempre lo stesso oggetto.

Schema:

```java
class NomeClasse {

    private static NomeClasse instance;

    private NomeClasse() {
    }

    public static NomeClasse getInstance() {
        if (instance == null) {
            instance = new NomeClasse();
        }

        return instance;
    }
}
```

## 17. Riassunto secco

Il Singleton serve quando vuoi:

- una sola istanza di una classe
- accessibile da più punti del programma
- senza creare nuovi oggetti ogni volta

La logica è:

```java
private NomeClasse() {}
```

blocca la creazione dall’esterno.

```java
private static NomeClasse instance;
```

salva l’unico oggetto.

```java
public static NomeClasse getInstance()
```

restituisce sempre quell’oggetto.

La versione più semplice è utile per capire.

La versione con Holder o enum è migliore nei casi reali.
