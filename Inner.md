# Inner class in Java

In italiano possiamo dire:

una inner class è una classe interna, cioè una classe definita all’interno di un’altra classe.

Esempio base:

class Esterna {

    class Interna {

    }
}

Qui hai:

Esterna

classe esterna.

Interna

classe interna.

1. Perché esistono le inner class?

Le inner class servono quando una classe ha senso solo dentro un’altra classe.

Esempio concettuale:

class Automobile {

    class Motore {

    }
}

Il Motore può essere visto come una parte interna dell’Automobile.

Oppure:

class Casa {

    class Stanza {

    }
}

La Stanza è collegata logicamente alla Casa.

2. Primo esempio semplice
class Esterna {

    class Interna {

        public void saluta() {
            System.out.println("Ciao dalla classe interna");
        }
    }
}

Per usare la classe interna:

public class Main {

    public static void main(String[] args) {

        Esterna esterna = new Esterna();

        Esterna.Interna interna = esterna.new Interna();

        interna.saluta();
    }
}

Output:

Ciao dalla classe interna
3. Spiegazione della creazione

Questa riga:

Esterna esterna = new Esterna();

crea prima l’oggetto della classe esterna.

Poi:

Esterna.Interna interna = esterna.new Interna();

crea l’oggetto della classe interna.

Attenzione: per creare una inner class non static, ti serve prima un oggetto della classe esterna.

Non puoi fare direttamente:

Esterna.Interna interna = new Esterna.Interna();

perché Interna dipende da un oggetto Esterna.

4. Inner class non static

Questa è la vera inner class classica:

class Persona {

    private String nome = "Luca";

    class Indirizzo {

        public void stampaNomePersona() {
            System.out.println(nome);
        }
    }
}

Uso:

public class Main {

    public static void main(String[] args) {

        Persona persona = new Persona();

        Persona.Indirizzo indirizzo = persona.new Indirizzo();

        indirizzo.stampaNomePersona();
    }
}

Output:

Luca

La cosa importante è questa:

System.out.println(nome);

La classe interna può accedere direttamente ai campi della classe esterna, anche se sono private.

5. Perché la inner class può accedere ai private?

Esempio:

class Auto {

    private String modello = "Fiat Panda";

    class Motore {

        public void stampaModelloAuto() {
            System.out.println(modello);
        }
    }
}

Anche se modello è private, la classe interna Motore può leggerlo.

Questo è uno dei motivi principali per usare le inner class: sono molto legate alla classe esterna.

6. Esempio pratico con veicolo

Nel tuo progetto potresti immaginare:

class Veicoli {

    private String marca;
    private String modello;

    class SchedaTecnica {

        public void stampaScheda() {
            System.out.println("Marca: " + marca);
            System.out.println("Modello: " + modello);
        }
    }

    public Veicoli(String marca, String modello) {
        this.marca = marca;
        this.modello = modello;
    }
}

Uso:

public class Main {

    public static void main(String[] args) {

        Veicoli v = new Veicoli("Fiat", "500");

        Veicoli.SchedaTecnica scheda = v.new SchedaTecnica();

        scheda.stampaScheda();
    }
}

Output:

Marca: Fiat
Modello: 500

Qui SchedaTecnica ha senso dentro Veicoli, perché descrive proprio quel veicolo.

7. Tipi di classi interne in Java

In Java ci sono vari tipi:

1. Inner class normale
2. Static nested class
3. Local inner class
4. Anonymous inner class

Le vediamo una per una.

8. Inner class normale

È una classe non static dentro un’altra classe.

class Esterna {

    class Interna {

    }
}

Caratteristiche:

dipende da un oggetto della classe esterna
può accedere ai campi private della classe esterna
si crea con esterna.new Interna()

Esempio:

class Computer {

    private String marca = "HP";

    class Processore {

        public void info() {
            System.out.println("Processore del computer " + marca);
        }
    }
}

Uso:

Computer computer = new Computer();

Computer.Processore processore = computer.new Processore();

processore.info();
9. Static nested class

Questa non è una vera inner class nel senso stretto, ma una classe annidata statica.

class Esterna {

    static class InternaStatica {

    }
}

Esempio:

class Computer {

    static class Usb {

        public void collega() {
            System.out.println("USB collegata");
        }
    }
}

Uso:

public class Main {

    public static void main(String[] args) {

        Computer.Usb usb = new Computer.Usb();

        usb.collega();
    }
}

Output:

USB collegata

Qui non serve creare prima un oggetto Computer.

Questo funziona:

Computer.Usb usb = new Computer.Usb();

Perché Usb è static.

10. Differenza tra inner class normale e static nested class
Inner class normale
class Esterna {
    class Interna {
    }
}

Si crea così:

Esterna esterna = new Esterna();
Esterna.Interna interna = esterna.new Interna();

Dipende da un oggetto della classe esterna.

Static nested class
class Esterna {
    static class Interna {
    }
}

Si crea così:

Esterna.Interna interna = new Esterna.Interna();

Non dipende da un oggetto della classe esterna.

11. Accesso ai campi della classe esterna
Inner class normale
class Esterna {

    private int numero = 10;

    class Interna {

        public void stampa() {
            System.out.println(numero);
        }
    }
}

Funziona.

Static nested class
class Esterna {

    private int numero = 10;

    static class Interna {

        public void stampa() {
            System.out.println(numero);
        }
    }
}

Questo non funziona.

Perché?

Perché Interna è static, quindi non appartiene a un oggetto specifico di Esterna.

Per accedere a numero, dovrebbe ricevere un oggetto Esterna.

Corretto:

class Esterna {

    private int numero = 10;

    static class Interna {

        public void stampa(Esterna esterna) {
            System.out.println(esterna.numero);
        }
    }
}

Uso:

Esterna esterna = new Esterna();

Esterna.Interna interna = new Esterna.Interna();

interna.stampa(esterna);
12. Local inner class

Una local inner class è una classe dichiarata dentro un metodo.

Esempio:

class Esterna {

    public void metodo() {

        class InternaLocale {

            public void saluta() {
                System.out.println("Ciao dalla classe locale");
            }
        }

        InternaLocale interna = new InternaLocale();

        interna.saluta();
    }
}

Uso:

public class Main {

    public static void main(String[] args) {

        Esterna e = new Esterna();

        e.metodo();
    }
}

Output:

Ciao dalla classe locale
13. Quando usare una local inner class?

La usi quando una classe ti serve solo dentro un metodo.

Esempio:

public void esegui() {

    class Validatore {

        public boolean isValido(String testo) {
            return testo != null && !testo.isBlank();
        }
    }

    Validatore validatore = new Validatore();

    System.out.println(validatore.isValido("ciao"));
}

La classe Validatore non esiste fuori dal metodo esegui.

Quindi non puoi fare:

Validatore v = new Validatore();

da un altro metodo.

14. Anonymous inner class

Questa è molto importante.

Una anonymous inner class è una classe interna senza nome, creata al momento.

Esempio con interfaccia:

interface Saluto {
    void saluta();
}

Uso con classe anonima:

public class Main {

    public static void main(String[] args) {

        Saluto s = new Saluto() {
            @Override
            public void saluta() {
                System.out.println("Ciao dalla classe anonima");
            }
        };

        s.saluta();
    }
}

Output:

Ciao dalla classe anonima

Questa parte:

new Saluto() {
    @Override
    public void saluta() {
        System.out.println("Ciao dalla classe anonima");
    }
};

crea una classe senza nome che implementa Saluto.

15. Perché si chiama anonima?

Perché non scrivi:

class MiaClasse implements Saluto {
}

Invece crei direttamente l’oggetto:

Saluto s = new Saluto() {
    @Override
    public void saluta() {
        System.out.println("Ciao");
    }
};

La classe esiste, ma non ha un nome scritto da te.

16. Anonymous inner class con classe astratta

Esempio:

abstract class Processo {

    public abstract void execute();
}

Uso:

public class Main {

    public static void main(String[] args) {

        Processo p = new Processo() {
            @Override
            public void execute() {
                System.out.println("Esecuzione processo");
            }
        };

        p.execute();
    }
}

Qui stai creando al volo una sottoclasse anonima di Processo.

17. Collegamento con il tuo progetto

Tu avevi una mappa tipo:

Map<String, GeneralProcess> pr = Map.ofEntries(
    Map.entry("list", new ListManager()),
    Map.entry("stream", new StreamManager())
);

Potresti usare una anonymous inner class così:

Map<String, GeneralProcess> pr = Map.ofEntries(
    Map.entry("list", new GeneralProcess() {
        @Override
        public void execute() {
            System.out.println("Eseguo list");
        }
    })
);

Qui non hai creato una classe ListManager.

Hai creato direttamente un oggetto anonimo che implementa GeneralProcess.

18. Anonymous inner class con eventi Swing

Le classi anonime sono molto usate con Swing.

Esempio:

JButton button = new JButton("Clicca");

button.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Hai cliccato il bottone");
    }
});

Qui:

new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Hai cliccato il bottone");
    }
}

è una anonymous inner class.

Oggi spesso si usa la lambda:

button.addActionListener(e -> {
    System.out.println("Hai cliccato il bottone");
});

Però la classe anonima ti fa capire cosa succede davvero.

19. Inner class e accesso alle variabili locali

Una inner class locale o anonima può usare variabili locali del metodo solo se sono final o effectively final.

Esempio:

public void metodo() {

    String messaggio = "Ciao";

    class Interna {

        public void stampa() {
            System.out.println(messaggio);
        }
    }
}

Funziona perché messaggio non viene modificato.

Questo invece non va:

public void metodo() {

    String messaggio = "Ciao";

    messaggio = "Ciao modificato";

    class Interna {

        public void stampa() {
            System.out.println(messaggio);
        }
    }
}

Perché messaggio non è più effectively final.

20. Esempio completo con tutte le inner class
class Esterna {

    private String nome = "Classe Esterna";

    class InnerNormale {

        public void stampa() {
            System.out.println("Inner normale: " + nome);
        }
    }

    static class StaticNested {

        public void stampa() {
            System.out.println("Static nested class");
        }
    }

    public void metodoConClasseLocale() {

        class LocalInner {

            public void stampa() {
                System.out.println("Local inner class");
            }
        }

        LocalInner local = new LocalInner();

        local.stampa();
    }

    public void metodoConClasseAnonima() {

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous inner class");
            }
        };

        r.run();
    }
}

Uso:

public class Main {

    public static void main(String[] args) {

        Esterna esterna = new Esterna();

        Esterna.InnerNormale inner = esterna.new InnerNormale();
        inner.stampa();

        Esterna.StaticNested nested = new Esterna.StaticNested();
        nested.stampa();

        esterna.metodoConClasseLocale();

        esterna.metodoConClasseAnonima();
    }
}

Output:

Inner normale: Classe Esterna
Static nested class
Local inner class
Anonymous inner class
21. Quando usare le inner class?

Usale quando una classe:

ha senso solo dentro un’altra classe
è molto collegata alla classe esterna
deve accedere ai dati private della classe esterna
serve solo per organizzare meglio il codice
serve solo dentro un metodo
serve per implementare al volo un’interfaccia o classe astratta
22. Quando NON usarle?

Non abusarne.

Se una classe diventa grande, complessa e usata in più parti del programma, meglio metterla in un file separato.

Esempio sbagliato:

class Veicoli {

    class Macchina {
    }

    class Moto {
    }

    class Bici {
    }
}

Nel tuo progetto questo non conviene, perché Macchina, Moto e Bici sono entità importanti.

Meglio tenerle separate:

Veicoli.java
Macchina.java
Moto.java
Bici.java
23. Tabella riassuntiva
Tipo	Dove si dichiara	Ha nome?	Dipende da oggetto esterno?
Inner class normale	dentro una classe	sì	sì
Static nested class	dentro una classe con static	sì	no
Local inner class	dentro un metodo	sì	sì, nel contesto del metodo
Anonymous inner class	dentro un’espressione	no	sì, nel contesto in cui nasce
24. Riassunto secco

Una inner class è una classe dentro un’altra classe.

Esempio:

class Esterna {
    class Interna {
    }
}

Per creare una inner class normale:

Esterna e = new Esterna();
Esterna.Interna i = e.new Interna();

Una static nested class si crea così:

Esterna.Interna i = new Esterna.Interna();

Una local inner class sta dentro un metodo.

Una anonymous inner class non ha nome:

Runnable r = new Runnable() {
    @Override
    public void run() {
        System.out.println("Ciao");
    }
};

La regola pratica è:

usa una inner class quando quella classe serve solo dentro o insieme alla classe esterna.