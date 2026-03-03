# INTERFACE IN JAVA -- GUIDA COMPLETA E PROFONDA

Questa guida è pensata per prepararti al 100% sulle interface in Java.
Contiene teoria, meccanica interna, esempi progressivi e casi complessi.

------------------------------------------------------------------------

# 1️⃣ Cos'è davvero una Interface?

Un'interfaccia è un **tipo astratto puro**.

Caratteristiche fondamentali:

-   È un reference type
-   Non può avere costruttori
-   Non può essere istanziata
-   Definisce un insieme di metodi pubblici astratti (di default)
-   Rappresenta un contratto

Un'interfaccia definisce **COSA deve essere fatto**, non COME.

------------------------------------------------------------------------

# 2️⃣ Forma Base

``` java
public interface Printer {
    void print(String text);
}
```

Il metodo è implicitamente:

-   public
-   abstract

Scrivere:

``` java
public abstract void print(String text);
```

è ridondante.

------------------------------------------------------------------------

# 3️⃣ Cosa succede quando usi implements?

Quando scrivi:

``` java
public class LaserPrinter implements Printer
```

Il compilatore verifica:

-   Che tutti i metodi siano implementati
-   Che siano pubblici
-   Che la firma sia identica

Se manca un metodo → errore di compilazione.

Esempio:

``` java
public class LaserPrinter implements Printer {

    @Override
    public void print(String text) {
        System.out.println("Printing: " + text);
    }
}
```

------------------------------------------------------------------------

# 4️⃣ Polimorfismo Dinamico

``` java
public interface Notification {
    void send(String message);
}
```

Implementazioni:

``` java
public class EmailNotification implements Notification {

    @Override
    public void send(String message) {
        System.out.println("Email: " + message);
    }
}
```

``` java
public class SMSNotification implements Notification {

    @Override
    public void send(String message) {
        System.out.println("SMS: " + message);
    }
}
```

Uso:

``` java
public class NotificationService {

    public void process(Notification notification) {
        notification.send("Ordine completato");
    }
}
```

Main:

``` java
NotificationService service = new NotificationService();

Notification email = new EmailNotification();
Notification sms = new SMSNotification();

service.process(email);
service.process(sms);
```

Qui il tipo è Notification, ma l'implementazione è scelta a runtime.

------------------------------------------------------------------------

# 5️⃣ Interface vs Abstract Class

## Abstract Class

``` java
public abstract class Animal {

    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    public void sleep() {
        System.out.println("Sleeping...");
    }

    public abstract void makeSound();
}
```

Ha: - Stato - Costruttore - Metodi concreti

## Interface

``` java
public interface Animal {
    void makeSound();
}
```

Non può avere costruttori né stato (solo costanti).

------------------------------------------------------------------------

# 6️⃣ Cosa può contenere una Interface (Java moderno)

## Metodi astratti

``` java
void run();
```

## Metodi default

``` java
default void stop() {
    System.out.println("Stopped");
}
```

## Metodi static

``` java
static void info() {
    System.out.println("Utility method");
}
```

## Costanti

``` java
int MAX_SPEED = 120;  // public static final automatico
```

------------------------------------------------------------------------

# 7️⃣ Multiple Implementation

``` java
public interface Flyable {
    void fly();
}

public interface Swimmable {
    void swim();
}
```

Classe:

``` java
public class Duck implements Flyable, Swimmable {

    @Override
    public void fly() {
        System.out.println("Flying");
    }

    @Override
    public void swim() {
        System.out.println("Swimming");
    }
}
```

Una classe può implementare più interfacce. Non può estendere più
classi.

------------------------------------------------------------------------

# 8️⃣ Sistema Pagamenti Reale

``` java
public interface PaymentMethod {
    boolean authorize(double amount);
    void capture(double amount);
    void refund(double amount);
}
```

Implementazione:

``` java
public class CreditCardPayment implements PaymentMethod {

    @Override
    public boolean authorize(double amount) {
        return amount < 5000;
    }

    @Override
    public void capture(double amount) {
        System.out.println("Pagamento carta completato: " + amount);
    }

    @Override
    public void refund(double amount) {
        System.out.println("Rimborso carta: " + amount);
    }
}
```

Servizio:

``` java
public class CheckoutService {

    public void checkout(PaymentMethod method, double amount) {

        if (method.authorize(amount)) {
            method.capture(amount);
        } else {
            System.out.println("Pagamento non autorizzato");
        }
    }
}
```

------------------------------------------------------------------------

# 9️⃣ Sistema Plugin (Architettura Reale)

``` java
public interface Plugin {
    void initialize();
    void execute();
    void shutdown();
}
```

Plugin:

``` java
public class LoggingPlugin implements Plugin {

    @Override
    public void initialize() {
        System.out.println("Logging plugin ready");
    }

    @Override
    public void execute() {
        System.out.println("Logging data...");
    }

    @Override
    public void shutdown() {
        System.out.println("Logging plugin stopped");
    }
}
```

Manager:

``` java
import java.util.ArrayList;
import java.util.List;

public class PluginManager {

    private List<Plugin> plugins = new ArrayList<>();

    public void register(Plugin plugin) {
        plugins.add(plugin);
    }

    public void startAll() {
        for (Plugin p : plugins) {
            p.initialize();
            p.execute();
        }
    }

    public void stopAll() {
        for (Plugin p : plugins) {
            p.shutdown();
        }
    }
}
```

------------------------------------------------------------------------

# 🔟 Strategy Pattern (Esempio Avanzato)

``` java
public interface SortingStrategy {
    void sort(int[] array);
}
```

Implementazioni:

``` java
public class BubbleSort implements SortingStrategy {

    @Override
    public void sort(int[] array) {
        System.out.println("Sorting with BubbleSort");
    }
}
```

``` java
public class QuickSort implements SortingStrategy {

    @Override
    public void sort(int[] array) {
        System.out.println("Sorting with QuickSort");
    }
}
```

Classe che usa la strategia:

``` java
public class Sorter {

    private SortingStrategy strategy;

    public Sorter(SortingStrategy strategy) {
        this.strategy = strategy;
    }

    public void sortArray(int[] array) {
        strategy.sort(array);
    }
}
```

Uso:

``` java
int[] data = {5, 3, 8};

Sorter sorter = new Sorter(new QuickSort());
sorter.sortArray(data);
```

Qui puoi cambiare algoritmo a runtime.

------------------------------------------------------------------------

# 🔥 Conclusione Finale

Interface serve a:

-   Separare il COSA dal COME
-   Permettere polimorfismo dinamico
-   Rendere il codice estendibile
-   Ridurre accoppiamento
-   Applicare design pattern professionali

Se padroneggi questo documento, sei pronto a usare le interface in modo
serio e professionale.
