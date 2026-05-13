# Enum in Java

Certo. In Java le classi enum servono a rappresentare un insieme fisso e limitato di valori possibili.

Per esempio, pensa ai giorni della settimana:

```text
LUNEDI
MARTEDI
MERCOLEDI
GIOVEDI
VENERDI
SABATO
DOMENICA
```

Oppure ai livelli di difficoltà:

```text
FACILE
MEDIO
DIFFICILE
```

Oppure agli stati di un ordine:

```text
IN_ATTESA
SPEDITO
CONSEGNATO
ANNULLATO
```

In tutti questi casi i valori possibili sono pochi, conosciuti e non devono cambiare durante l’esecuzione.

Per questo Java ci mette a disposizione gli enum.

## 1. Che cos’è un enum?

Un enum è un tipo speciale di classe che contiene un insieme fisso di costanti.

Esempio:

```java
enum Giorno {
    LUNEDI,
    MARTEDI,
    MERCOLEDI,
    GIOVEDI,
    VENERDI,
    SABATO,
    DOMENICA
}
```

Qui abbiamo creato un nuovo tipo chiamato:

```text
Giorno
```

Questo tipo può assumere solo questi valori:

```java
Giorno.LUNEDI
Giorno.MARTEDI
Giorno.MERCOLEDI
Giorno.GIOVEDI
Giorno.VENERDI
Giorno.SABATO
Giorno.DOMENICA
```

Non può assumere valori inventati.

## 2. Perché usare enum?

Senza enum potresti scrivere:

```java
String giorno = "Lunedi";
```

Il problema è che potresti sbagliare:

```java
String giorno = "Lunedì";
String giorno2 = "lunedi";
String giorno3 = "LUNEDDI";
```

Java non ti blocca, perché sono tutte stringhe.

Con enum invece:

```java
Giorno giorno = Giorno.LUNEDI;
```

Qui Java controlla il valore.

Se provi a scrivere:

```java
Giorno giorno = Giorno.LUNEDDI;
```

Java ti dà errore, perché LUNEDDI non esiste.

Quindi l’enum serve a evitare errori stupidi ma pericolosi.

## 3. Primo esempio completo

```java
enum Giorno {
    LUNEDI,
    MARTEDI,
    MERCOLEDI,
    GIOVEDI,
    VENERDI,
    SABATO,
    DOMENICA
}

public class Main {

    public static void main(String[] args) {

        Giorno oggi = Giorno.MERCOLEDI;

        System.out.println(oggi);
    }
}
```

Output:

```text
MERCOLEDI
```

## 4. Spiegazione riga per riga

```java
enum Giorno
```

crea un nuovo tipo enum chiamato Giorno.

```java
{
    LUNEDI,
    MARTEDI,
    MERCOLEDI,
    GIOVEDI,
    VENERDI,
    SABATO,
    DOMENICA
}
```

questi sono gli unici valori possibili.

```java
Giorno oggi = Giorno.MERCOLEDI;
```

crea una variabile chiamata oggi di tipo Giorno.

Il valore assegnato è:

```java
Giorno.MERCOLEDI
```

cioè la costante MERCOLEDI appartenente all’enum Giorno.

## 5. Enum dentro una classe

Puoi dichiarare un enum anche dentro una classe.

```java
public class Main {

    enum Livello {
        BASSO,
        MEDIO,
        ALTO
    }

    public static void main(String[] args) {

        Livello livello = Livello.ALTO;

        System.out.println(livello);
    }
}
```

Qui Livello esiste dentro Main.

È utile se quell’enum serve solo in quella classe.

## 6. Enum in un file separato

Puoi anche mettere l’enum in un file suo.

File:

```text
Giorno.java
```

contenuto:

```java
public enum Giorno {
    LUNEDI,
    MARTEDI,
    MERCOLEDI,
    GIOVEDI,
    VENERDI,
    SABATO,
    DOMENICA
}
```

Poi in Main.java:

```java
public class Main {

    public static void main(String[] args) {

        Giorno giorno = Giorno.LUNEDI;

        System.out.println(giorno);
    }
}
```

Attenzione: se l’enum è public, il file deve chiamarsi come l’enum.

Quindi:

```java
public enum Giorno
```

deve stare nel file:

```text
Giorno.java
```

## 7. Convenzione dei nomi

Di solito i valori degli enum si scrivono in maiuscolo:

```java
enum StatoOrdine {
    IN_ATTESA,
    SPEDITO,
    CONSEGNATO,
    ANNULLATO
}
```

Questo perché sono costanti.

È simile a quando scrivi:

```java
public static final int MAX = 100;
```

## 8. Usare enum con if

Esempio:

```java
enum Semaforo {
    ROSSO,
    GIALLO,
    VERDE
}

public class Main {

    public static void main(String[] args) {

        Semaforo colore = Semaforo.ROSSO;

        if (colore == Semaforo.ROSSO) {
            System.out.println("Fermati");
        } else if (colore == Semaforo.GIALLO) {
            System.out.println("Rallenta");
        } else if (colore == Semaforo.VERDE) {
            System.out.println("Puoi passare");
        }
    }
}
```

Con gli enum puoi confrontare usando:

```java
==
```

Questo è corretto.

Perché?

Perché ogni valore enum è un oggetto unico e fisso.

Quindi puoi fare:

```java
colore == Semaforo.ROSSO
```

## 9. Usare enum con switch

Gli enum funzionano benissimo con switch.

```java
enum Semaforo {
    ROSSO,
    GIALLO,
    VERDE
}

public class Main {

    public static void main(String[] args) {

        Semaforo colore = Semaforo.VERDE;

        switch (colore) {
            case ROSSO:
                System.out.println("Fermati");
                break;

            case GIALLO:
                System.out.println("Rallenta");
                break;

            case VERDE:
                System.out.println("Puoi passare");
                break;
        }
    }
}
```

Nota importante: dentro lo switch scrivi:

```java
case ROSSO:
```

non:

```java
case Semaforo.ROSSO:
```

Perché Java sa già che la variabile colore è di tipo Semaforo.

## 10. Switch moderno con enum

Nelle versioni moderne di Java puoi scrivere anche così:

```java
enum Semaforo {
    ROSSO,
    GIALLO,
    VERDE
}

public class Main {

    public static void main(String[] args) {

        Semaforo colore = Semaforo.VERDE;

        String messaggio = switch (colore) {
            case ROSSO -> "Fermati";
            case GIALLO -> "Rallenta";
            case VERDE -> "Puoi passare";
        };

        System.out.println(messaggio);
    }
}
```

Qui lo switch restituisce direttamente un valore.

Questa parte:

```java
case ROSSO -> "Fermati";
```

significa:

Se il colore è ROSSO, il risultato dello switch è "Fermati".

## 11. Metodo values()

Ogni enum ha automaticamente il metodo:

```java
values()
```

Questo metodo restituisce tutti i valori dell’enum.

Esempio:

```java
enum Giorno {
    LUNEDI,
    MARTEDI,
    MERCOLEDI,
    GIOVEDI,
    VENERDI,
    SABATO,
    DOMENICA
}

public class Main {

    public static void main(String[] args) {

        for (Giorno g : Giorno.values()) {
            System.out.println(g);
        }
    }
}
```

Output:

```text
LUNEDI
MARTEDI
MERCOLEDI
GIOVEDI
VENERDI
SABATO
DOMENICA
```

Spiegazione:

```java
Giorno.values()
```

restituisce un array con tutti i valori:

```java
Giorno[]
```

Quindi il ciclo:

```java
for (Giorno g : Giorno.values())
```

scorre tutti i giorni uno alla volta.

## 12. Metodo valueOf()

Ogni enum ha anche:

```java
valueOf()
```

Serve a convertire una stringa in enum.

Esempio:

```java
enum Giorno {
    LUNEDI,
    MARTEDI,
    MERCOLEDI
}

public class Main {

    public static void main(String[] args) {

        Giorno giorno = Giorno.valueOf("LUNEDI");

        System.out.println(giorno);
    }
}
```

Output:

```text
LUNEDI
```

Attenzione: la stringa deve essere identica al nome dell’enum.

Questo funziona:

```java
Giorno.valueOf("LUNEDI");
```

Questo no:

```java
Giorno.valueOf("lunedi");
```

Questo no:

```java
Giorno.valueOf("Lunedì");
```

Se la stringa non corrisponde, Java lancia:

```java
IllegalArgumentException
```

Esempio:

```java
Giorno giorno = Giorno.valueOf("DOMENICA");
```

se DOMENICA non esiste nell’enum, il programma genera errore.

## 13. Metodo name()

Ogni valore enum ha il metodo:

```java
name()
```

Serve a ottenere il nome esatto della costante.

Esempio:

```java
enum Giorno {
    LUNEDI,
    MARTEDI,
    MERCOLEDI
}

public class Main {

    public static void main(String[] args) {

        Giorno giorno = Giorno.MARTEDI;

        System.out.println(giorno.name());
    }
}
```

Output:

```text
MARTEDI
```

name() restituisce una stringa.

Quindi:

```java
giorno.name()
```

restituisce:

```text
"MARTEDI"
```

## 14. Metodo ordinal()

Ogni enum ha anche:

```java
ordinal()
```

Serve a ottenere la posizione numerica del valore nell’enum.

Esempio:

```java
enum Giorno {
    LUNEDI,
    MARTEDI,
    MERCOLEDI
}

public class Main {

    public static void main(String[] args) {

        System.out.println(Giorno.LUNEDI.ordinal());
        System.out.println(Giorno.MARTEDI.ordinal());
        System.out.println(Giorno.MERCOLEDI.ordinal());
    }
}
```

Output:

```text
0
1
2
```

Attenzione: parte da zero.

```text
LUNEDI -> 0
MARTEDI -> 1
MERCOLEDI -> 2
```

Però devi stare attento: ordinal() non va usato troppo nella logica del programma.

Perché?

Se cambi l’ordine degli enum, cambia anche il valore numerico.

Esempio:

```java
enum Giorno {
    DOMENICA,
    LUNEDI,
    MARTEDI
}
```

Adesso LUNEDI.ordinal() non è più 0, ma 1.

Quindi ordinal() è utile per stampare o controllare, ma non conviene basare la logica importante su quello.

## 15. Enum con attributi

Un enum può avere anche variabili interne.

Esempio:

```java
enum Livello {
    BASSO(1),
    MEDIO(2),
    ALTO(3);

    private int valore;

    Livello(int valore) {
        this.valore = valore;
    }

    public int getValore() {
        return valore;
    }
}
```

Uso:

```java
public class Main {

    public static void main(String[] args) {

        Livello livello = Livello.ALTO;

        System.out.println(livello.getValore());
    }
}
```

Output:

```text
3
```

## 16. Spiegazione dell’enum con attributi

Questa parte:

```java
BASSO(1),
MEDIO(2),
ALTO(3);
```

significa che ogni costante enum ha un valore associato.

Quindi:

BASSO

ha valore:

```text
1
```

MEDIO

ha valore:

```text
2
```

ALTO

ha valore:

```text
3
```

Attenzione al punto e virgola:

```java
ALTO(3);
```

Quando un enum contiene attributi, costruttori o metodi, dopo l’ultima costante devi mettere:

```java
;
```

## 17. Costruttore negli enum

Questo è il costruttore:

```java
Livello(int valore) {
    this.valore = valore;
}
```

Nota importante: il costruttore di un enum non può essere public.

Infatti scriviamo:

```java
Livello(int valore)
```

non:

```java
public Livello(int valore)
```

Perché?

Perché non puoi creare nuovi enum con new.

Questo è sbagliato:

```java
Livello l = new Livello(1);
```

Gli unici oggetti possibili sono quelli dichiarati all’inizio:

```text
BASSO
MEDIO
ALTO
```

## 18. Enum con String associata

Esempio:

```java
enum Ruolo {
    ADMIN("Amministratore"),
    USER("Utente normale"),
    GUEST("Ospite");

    private String descrizione;

    Ruolo(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }
}
```

Uso:

```java
public class Main {

    public static void main(String[] args) {

        Ruolo ruolo = Ruolo.ADMIN;

        System.out.println(ruolo);
        System.out.println(ruolo.getDescrizione());
    }
}
```

Output:

```text
ADMIN
Amministratore
```

## 19. Enum con più attributi

Puoi mettere più dati dentro ogni costante.

```java
enum StatoOrdine {
    IN_ATTESA("In attesa", false),
    SPEDITO("Spedito", false),
    CONSEGNATO("Consegnato", true),
    ANNULLATO("Annullato", true);

    private String descrizione;
    private boolean finale;

    StatoOrdine(String descrizione, boolean finale) {
        this.descrizione = descrizione;
        this.finale = finale;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public boolean isFinale() {
        return finale;
    }
}
```

Uso:

```java
public class Main {

    public static void main(String[] args) {

        StatoOrdine stato = StatoOrdine.CONSEGNATO;

        System.out.println(stato.getDescrizione());
        System.out.println(stato.isFinale());
    }
}
```

Output:

```text
Consegnato
true
```

## 20. Enum con metodi

Gli enum possono avere metodi normali.

Esempio:

```java
enum Giorno {
    LUNEDI,
    MARTEDI,
    MERCOLEDI,
    GIOVEDI,
    VENERDI,
    SABATO,
    DOMENICA;

    public boolean isWeekend() {
        return this == SABATO || this == DOMENICA;
    }
}
```

Uso:

```java
public class Main {

    public static void main(String[] args) {

        Giorno giorno = Giorno.SABATO;

        if (giorno.isWeekend()) {
            System.out.println("È weekend");
        } else {
            System.out.println("È giorno lavorativo");
        }
    }
}
```

Output:

```text
È weekend
```

## 21. Enum con metodi diversi per ogni costante

Questa è una parte più avanzata.

Ogni costante può avere un comportamento diverso.

Esempio:

```java
enum Operazione {
    SOMMA {
        public int esegui(int a, int b) {
            return a + b;
        }
    },
    SOTTRAZIONE {
        public int esegui(int a, int b) {
            return a - b;
        }
    },
    MOLTIPLICAZIONE {
        public int esegui(int a, int b) {
            return a * b;
        }
    };

    public abstract int esegui(int a, int b);
}
```

Uso:

```java
public class Main {

    public static void main(String[] args) {

        Operazione op = Operazione.SOMMA;

        int risultato = op.esegui(10, 5);

        System.out.println(risultato);
    }
}
```

Output:

```text
15
```

Spiegazione:

```java
public abstract int esegui(int a, int b);
```

obbliga ogni costante enum a implementare il metodo esegui.

Quindi:

SOMMA

fa una cosa.

SOTTRAZIONE

fa un’altra cosa.

MOLTIPLICAZIONE

fa un’altra cosa ancora.

## 22. Enum e classi

Un enum è molto più potente di una semplice lista di valori.

Infatti un enum può avere:

- attributi
- costruttori
- metodi
- metodi astratti
- metodi diversi per ogni costante
- implementare interfacce

Però non può estendere un’altra classe, perché ogni enum estende già implicitamente:

```java
java.lang.Enum
```

Quindi non puoi fare:

```java
enum Giorno extends Qualcosa
```

però puoi fare:

```java
enum Giorno implements MiaInterfaccia
```

## 23. Enum che implementa un’interfaccia

Esempio:

```java
interface Descrivibile {
    String descrizione();
}

enum Ruolo implements Descrivibile {
    ADMIN,
    USER,
    GUEST;

    public String descrizione() {
        return "Ruolo: " + this.name();
    }
}
```

Uso:

```java
public class Main {

    public static void main(String[] args) {

        Ruolo ruolo = Ruolo.ADMIN;

        System.out.println(ruolo.descrizione());
    }
}
```

Output:

```text
Ruolo: ADMIN
```

## 24. Enum nei parametri dei metodi

Gli enum sono molto utili nei metodi.

Esempio sbagliato con String:

```java
public static void impostaLivello(String livello) {
    if (livello.equals("BASSO")) {
        System.out.println("Livello basso");
    }
}
```

Problema: potresti passare qualsiasi stringa.

```java
impostaLivello("CIAO");
impostaLivello("basso");
impostaLivello("BASO");
```

Meglio con enum:

```java
enum Livello {
    BASSO,
    MEDIO,
    ALTO
}

public class Main {

    public static void impostaLivello(Livello livello) {
        if (livello == Livello.BASSO) {
            System.out.println("Livello basso");
        } else if (livello == Livello.MEDIO) {
            System.out.println("Livello medio");
        } else if (livello == Livello.ALTO) {
            System.out.println("Livello alto");
        }
    }

    public static void main(String[] args) {
        impostaLivello(Livello.ALTO);
    }
}
```

Ora il metodo accetta solo:

```java
Livello.BASSO
Livello.MEDIO
Livello.ALTO
```

## 25. Enum come attributo di una classe

Esempio:

```java
enum StatoStudente {
    ATTIVO,
    SOSPESO,
    LAUREATO
}

class Studente {

    private String nome;
    private StatoStudente stato;

    public Studente(String nome, StatoStudente stato) {
        this.nome = nome;
        this.stato = stato;
    }

    public void stampaInfo() {
        System.out.println(nome + " - " + stato);
    }
}
```

Uso:

```java
public class Main {

    public static void main(String[] args) {

        Studente s = new Studente("Luca", StatoStudente.ATTIVO);

        s.stampaInfo();
    }
}
```

Output:

```text
Luca - ATTIVO
```

Questo è molto realistico: uno studente può essere solo in certi stati.

## 26. Enum e package

Un enum può stare in un package come una normale classe.

Esempio:

```java
package com.example.model;

public enum StatoOrdine {
    IN_ATTESA,
    SPEDITO,
    CONSEGNATO,
    ANNULLATO
}
```

Poi lo importi:

```java
import com.example.model.StatoOrdine;
```

E lo usi:

```java
StatoOrdine stato = StatoOrdine.SPEDITO;
```

## 27. Enum e null

Un enum può anche essere null, perché è un tipo riferimento.

Esempio:

```java
Giorno giorno = null;
```

Attenzione:

```java
giorno.name();
```

genera:

```java
NullPointerException
```

Per evitare problemi:

```java
if (giorno != null) {
    System.out.println(giorno.name());
}
```

Oppure, quando confronti:

```java
if (giorno == Giorno.LUNEDI) {
    System.out.println("Lunedì");
}
```

Questo confronto non genera errore anche se giorno è null.

Invece questo può generare errore:

```java
if (giorno.equals(Giorno.LUNEDI)) {
    System.out.println("Lunedì");
}
```

Perché se giorno è null, chiamare .equals() provoca NullPointerException.

Quindi con enum spesso è meglio usare:

```java
==
```

## 28. Enum e valueOf con eccezioni

Come detto, valueOf() può generare errore.

Esempio:

```java
enum Giorno {
    LUNEDI,
    MARTEDI
}

public class Main {

    public static void main(String[] args) {

        Giorno giorno = Giorno.valueOf("DOMENICA");

        System.out.println(giorno);
    }
}
```

Qui Java genera:

```java
IllegalArgumentException
```

Per gestirlo:

```java
public class Main {

    public static void main(String[] args) {

        try {
            Giorno giorno = Giorno.valueOf("DOMENICA");
            System.out.println(giorno);

        } catch (IllegalArgumentException e) {
            System.out.println("Valore non valido");
        }
    }
}
```

## 29. Metodo sicuro per convertire String in enum

Puoi creare un metodo tuo:

```java
enum Giorno {
    LUNEDI,
    MARTEDI,
    MERCOLEDI;

    public static Giorno fromString(String testo) {
        for (Giorno g : Giorno.values()) {
            if (g.name().equalsIgnoreCase(testo)) {
                return g;
            }
        }

        throw new IllegalArgumentException("Giorno non valido: " + testo);
    }
}
```

Uso:

```java
public class Main {

    public static void main(String[] args) {

        Giorno giorno = Giorno.fromString("lunedi");

        System.out.println(giorno);
    }
}
```

Output:

```text
LUNEDI
```

Qui accetta anche:

```text
"lunedi"
"LUNEDI"
"LuNeDi"
```

perché usiamo:

```java
equalsIgnoreCase()
```

## 30. Quando usare un enum?

Usa un enum quando hai un insieme chiuso di valori.

Esempi perfetti:

```java
enum GiornoSettimana
enum StatoOrdine
enum LivelloAccesso
enum RuoloUtente
enum TipoPagamento
enum StatoPrenotazione
enum Sesso
enum Direzione
enum ColoreSemaforo
enum Difficolta
```

## 31. Quando NON usare un enum?

Non usare enum quando i valori sono variabili o arrivano da fuori e possono cambiare spesso.

Esempio: lista dei prodotti di un negozio.

Sbagliato:

```java
enum Prodotto {
    PANE,
    LATTE,
    PASTA
}
```

Perché i prodotti possono aumentare, diminuire, cambiare nome, essere caricati da un database.

Meglio una classe:

```java
class Prodotto {
    private String nome;
    private double prezzo;
}
```

Usa enum solo quando i valori sono davvero fissi.

## 32. Esempio pratico completo: ordine online

```java
enum StatoOrdine {
    IN_ATTESA("Ordine ricevuto", false),
    IN_PREPARAZIONE("Ordine in preparazione", false),
    SPEDITO("Ordine spedito", false),
    CONSEGNATO("Ordine consegnato", true),
    ANNULLATO("Ordine annullato", true);

    private String descrizione;
    private boolean concluso;

    StatoOrdine(String descrizione, boolean concluso) {
        this.descrizione = descrizione;
        this.concluso = concluso;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public boolean isConcluso() {
        return concluso;
    }
}

class Ordine {

    private int id;
    private StatoOrdine stato;

    public Ordine(int id) {
        this.id = id;
        this.stato = StatoOrdine.IN_ATTESA;
    }

    public void cambiaStato(StatoOrdine nuovoStato) {
        if (stato.isConcluso()) {
            throw new IllegalStateException("Ordine già concluso");
        }

        this.stato = nuovoStato;
    }

    public void stampa() {
        System.out.println("Ordine #" + id);
        System.out.println("Stato: " + stato);
        System.out.println("Descrizione: " + stato.getDescrizione());
        System.out.println("Concluso: " + stato.isConcluso());
    }
}

public class Main {

    public static void main(String[] args) {

        Ordine ordine = new Ordine(1001);

        ordine.stampa();

        ordine.cambiaStato(StatoOrdine.SPEDITO);

        ordine.stampa();
    }
}
```

Output possibile:

```text
Ordine #1001
Stato: IN_ATTESA
Descrizione: Ordine ricevuto
Concluso: false

Ordine #1001
Stato: SPEDITO
Descrizione: Ordine spedito
Concluso: false
```

Questo esempio fa vedere bene perché gli enum sono utili:

- lo stato dell’ordine è limitato
- non puoi mettere uno stato inventato
- ogni stato ha una descrizione
- ogni stato sa se è concluso oppure no
- la classe Ordine usa l’enum come attributo

## 33. Riassunto secco

Un enum serve per rappresentare valori fissi.

Esempio:

```java
enum Giorno {
    LUNEDI,
    MARTEDI,
    MERCOLEDI
}
```

Si usa così:

```java
Giorno giorno = Giorno.LUNEDI;
```

Puoi confrontare con:

```java
==
```

Puoi usarlo nello switch.

Puoi ottenere tutti i valori con:

```java
Giorno.values()
```

Puoi convertire da stringa con:

```java
Giorno.valueOf("LUNEDI")
```

Puoi ottenere il nome con:

```java
giorno.name()
```

Puoi ottenere la posizione con:

```java
giorno.ordinal()
```

Puoi aggiungere attributi:

```java
BASSO(1),
MEDIO(2),
ALTO(3);
```

Puoi aggiungere metodi:

```java
public boolean isWeekend()
```

Puoi creare enum molto simili a classi vere.

## 34. Regola pratica da ricordare

Usa enum quando vuoi dire:

Questa variabile può avere solo uno tra questi valori precisi.

Esempio:

```java
StatoOrdine stato = StatoOrdine.SPEDITO;
```

Meglio di:

```java
String stato = "spedito";
```

Perché con String puoi sbagliare.

Con enum, Java ti protegge.
