# Builder Pattern in Java

Certo. Le classi Builder create all’interno delle classi sono un modo molto usato in Java per costruire oggetti complessi in modo più ordinato, leggibile e sicuro.

Di solito si parla di Builder Pattern.

L’idea è questa:

invece di creare un oggetto con un costruttore lunghissimo pieno di parametri, creo una classe interna Builder che mi permette di impostare i valori uno alla volta e poi costruire l’oggetto finale.

1. Il problema: costruttori troppo lunghi

Nel tuo progetto, per esempio, una Macchina può avere tanti dati:

new Macchina(
    null,
    typeVeicolo.MACCHINA,
    4,
    typeFuel.BENZINA,
    categoriaVeicolo.BERLINA,
    "Bianco",
    "Fiat",
    2025,
    "500",
    1200,
    4
);

Questo funziona, ma è scomodo.

Il problema è che devi ricordarti l’ordine esatto dei parametri:

id
tipoVeicolo
numeroRuote
tipoFuel
categoria
colore
marca
annoProduzione
modello
cc
porte

Se sbagli ordine, il codice può diventare pericoloso.

Esempio:

new Macchina(null, typeVeicolo.MACCHINA, 4, typeFuel.BENZINA,
        categoriaVeicolo.BERLINA, "Fiat", "Bianco", 2025, "500", 1200, 4);

Qui magari hai invertito:

"Fiat"

e:

"Bianco"

Il programma compila, perché sono entrambe String, ma logicamente è sbagliato.

2. Soluzione: Builder

Con il Builder puoi scrivere così:

Macchina macchina = new Macchina.Builder()
        .numeroRuote(4)
        .tipoAlimentazione(typeFuel.BENZINA)
        .categoria(categoriaVeicolo.BERLINA)
        .colore("Bianco")
        .marca("Fiat")
        .annoProduzione(2025)
        .modello("500")
        .cc(1200)
        .porte(4)
        .build();

Questo codice è molto più leggibile.

Si capisce subito quale valore stai impostando.

3. Com’è fatta una classe con Builder interno

Esempio completo semplice:

public class Persona {

    private String nome;
    private String cognome;
    private int eta;

    private Persona(Builder builder) {
        this.nome = builder.nome;
        this.cognome = builder.cognome;
        this.eta = builder.eta;
    }

    public static class Builder {

        private String nome;
        private String cognome;
        private int eta;

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder cognome(String cognome) {
            this.cognome = cognome;
            return this;
        }

        public Builder eta(int eta) {
            this.eta = eta;
            return this;
        }

        public Persona build() {
            return new Persona(this);
        }
    }
}

Uso:

Persona p = new Persona.Builder()
        .nome("Luca")
        .cognome("Rossi")
        .eta(25)
        .build();
4. Spiegazione pezzo per pezzo

Questa è la classe esterna:

public class Persona {

È la classe dell’oggetto finale che vogliamo creare.

Dentro ha gli attributi:

private String nome;
private String cognome;
private int eta;

Poi ha un costruttore privato:

private Persona(Builder builder) {
    this.nome = builder.nome;
    this.cognome = builder.cognome;
    this.eta = builder.eta;
}

Questo costruttore riceve un oggetto Builder e copia i valori dal builder alla persona.

5. Perché il costruttore è private?

Perché vogliamo obbligare chi usa la classe a creare l’oggetto tramite il Builder.

Quindi non vogliamo fare:

Persona p = new Persona(...);

Vogliamo fare:

Persona p = new Persona.Builder()
        .nome("Luca")
        .eta(25)
        .build();

Per questo il costruttore può essere:

private
6. La classe interna Builder

Dentro Persona trovi:

public static class Builder {

Questa è una classe interna statica.

È static perché non deve dipendere da un oggetto Persona già esistente.

Infatti il suo scopo è proprio creare una Persona.

Quindi si usa così:

new Persona.Builder()

non così:

Persona persona = new Persona();
Persona.Builder builder = persona.new Builder();

Il Builder deve poter nascere prima dell’oggetto finale.

7. Perché il Builder ha gli stessi campi?

Dentro il Builder trovi:

private String nome;
private String cognome;
private int eta;

Sono gli stessi dati che poi finiranno dentro Persona.

Il Builder li conserva temporaneamente finché non chiami:

build()
8. I metodi del Builder

Esempio:

public Builder nome(String nome) {
    this.nome = nome;
    return this;
}

Questo metodo fa due cose:

salva il valore dentro il Builder;
restituisce il Builder stesso.

Questa parte:

this.nome = nome;

salva il nome.

Questa parte:

return this;

permette di concatenare i metodi.

Infatti puoi fare:

new Persona.Builder()
        .nome("Luca")
        .cognome("Rossi")
        .eta(25)
        .build();

Ogni metodo ritorna lo stesso builder, così puoi continuare la catena.

9. Il metodo build()

Il metodo finale è:

public Persona build() {
    return new Persona(this);
}

Questo crea davvero l’oggetto Persona.

Prima di build(), hai solo un Builder.

Dopo build(), hai l’oggetto finale.

10. Esempio con il tuo progetto: Veicoli

Immaginiamo una classe Macchina.

Senza Builder:

Macchina m = new Macchina(
        null,
        typeVeicolo.MACCHINA,
        4,
        typeFuel.BENZINA,
        categoriaVeicolo.BERLINA,
        "Bianco",
        "Fiat",
        2025,
        "500",
        1200,
        4
);

Con Builder:

Macchina m = new Macchina.Builder()
        .numeroRuote(4)
        .tipoAlimentazione(typeFuel.BENZINA)
        .categoria(categoriaVeicolo.BERLINA)
        .colore("Bianco")
        .marca("Fiat")
        .annoProduzione(2025)
        .modello("500")
        .cc(1200)
        .porte(4)
        .build();

Questo è molto più chiaro.

11. Esempio completo Macchina con Builder
public class Macchina extends Veicoli {

    private int cc;
    private int porte;

    private Macchina(Builder builder) {
        this.setTipoVeicolo(typeVeicolo.MACCHINA);
        this.setNumeroRuote(builder.numeroRuote);
        this.setTipoAlimentazione(builder.tipoAlimentazione);
        this.setCategoria(builder.categoria);
        this.setColore(builder.colore);
        this.setMarca(builder.marca);
        this.setAnnoProduzione(builder.annoProduzione);
        this.setModello(builder.modello);

        this.cc = builder.cc;
        this.porte = builder.porte;
    }

    public static class Builder {

        private int numeroRuote;
        private typeFuel tipoAlimentazione;
        private categoriaVeicolo categoria;
        private String colore;
        private String marca;
        private int annoProduzione;
        private String modello;

        private int cc;
        private int porte;

        public Builder numeroRuote(int numeroRuote) {
            this.numeroRuote = numeroRuote;
            return this;
        }

        public Builder tipoAlimentazione(typeFuel tipoAlimentazione) {
            this.tipoAlimentazione = tipoAlimentazione;
            return this;
        }

        public Builder categoria(categoriaVeicolo categoria) {
            this.categoria = categoria;
            return this;
        }

        public Builder colore(String colore) {
            this.colore = colore;
            return this;
        }

        public Builder marca(String marca) {
            this.marca = marca;
            return this;
        }

        public Builder annoProduzione(int annoProduzione) {
            this.annoProduzione = annoProduzione;
            return this;
        }

        public Builder modello(String modello) {
            this.modello = modello;
            return this;
        }

        public Builder cc(int cc) {
            this.cc = cc;
            return this;
        }

        public Builder porte(int porte) {
            this.porte = porte;
            return this;
        }

        public Macchina build() {
            return new Macchina(this);
        }
    }
}

Uso:

Macchina macchina = new Macchina.Builder()
        .numeroRuote(4)
        .tipoAlimentazione(typeFuel.BENZINA)
        .categoria(categoriaVeicolo.SUV)
        .colore("Bianco")
        .marca("Fiat")
        .annoProduzione(2025)
        .modello("500")
        .cc(1200)
        .porte(4)
        .build();
12. Perché Builder è dentro Macchina?

Perché quel builder serve solo a costruire una Macchina.

Quindi ha senso tenerlo dentro la classe Macchina.

Così il nome è chiaro:

Macchina.Builder

cioè:

il Builder della classe Macchina.

13. Perché Builder è static?

Questa è una cosa molto importante.

Il Builder deve essere static perché deve esistere prima dell’oggetto Macchina.

Se scrivessi:

public class Builder {
}

senza static, per creare il builder dovresti già avere una Macchina.

Ma questo non ha senso, perché il builder serve proprio a creare la macchina.

Per questo si scrive:

public static class Builder

Così puoi fare:

new Macchina.Builder()
14. Builder con valori obbligatori

A volte alcuni valori devono essere obbligatori.

Per esempio, per una macchina vuoi obbligare:

marca
modello
annoProduzione

Puoi metterli nel costruttore del Builder:

public static class Builder {

    private String marca;
    private String modello;
    private int annoProduzione;

    public Builder(String marca, String modello, int annoProduzione) {
        this.marca = marca;
        this.modello = modello;
        this.annoProduzione = annoProduzione;
    }
}

Uso:

Macchina macchina = new Macchina.Builder("Fiat", "500", 2025)
        .numeroRuote(4)
        .tipoAlimentazione(typeFuel.BENZINA)
        .categoria(categoriaVeicolo.BERLINA)
        .cc(1200)
        .porte(4)
        .build();

Così non puoi dimenticare marca, modello e anno.

15. Builder con controlli nel build()

Puoi mettere controlli prima di creare l’oggetto finale.

Esempio:

public Macchina build() throws Exception {

    if (numeroRuote <= 0) {
        throw new Exception("Numero ruote non valido");
    }

    if (marca == null || marca.isBlank()) {
        throw new Exception("Marca mancante");
    }

    if (modello == null || modello.isBlank()) {
        throw new Exception("Modello mancante");
    }

    if (annoProduzione < 2000 || annoProduzione > 2026) {
        throw new Exception("Anno produzione non valido");
    }

    return new Macchina(this);
}

Questo è molto utile perché il Builder non solo costruisce l’oggetto, ma lo costruisce già validato.

16. Esempio con controlli completi
public Macchina build() throws Exception {

    if (numeroRuote != 4) {
        throw new Exception("Una macchina deve avere 4 ruote");
    }

    if (tipoAlimentazione == null) {
        throw new Exception("Tipo alimentazione mancante");
    }

    if (categoria == null) {
        throw new Exception("Categoria mancante");
    }

    if (marca == null || marca.isBlank()) {
        throw new Exception("Marca mancante");
    }

    if (modello == null || modello.isBlank()) {
        throw new Exception("Modello mancante");
    }

    if (annoProduzione < 2000 || annoProduzione > 2026) {
        throw new Exception("Anno produzione non valido");
    }

    if (cc < 50 || cc > 12000) {
        throw new Exception("Cilindrata non valida");
    }

    if (porte < 2 || porte > 7) {
        throw new Exception("Numero porte non valido");
    }

    return new Macchina(this);
}

Uso:

Macchina macchina = new Macchina.Builder()
        .numeroRuote(4)
        .tipoAlimentazione(typeFuel.BENZINA)
        .categoria(categoriaVeicolo.SUV)
        .colore("Bianco")
        .marca("Fiat")
        .annoProduzione(2025)
        .modello("500")
        .cc(1200)
        .porte(4)
        .build();
17. Builder e ereditarietà

Nel tuo progetto hai:

Veicoli
├── Macchina
├── Moto
└── Bici

Qui il Builder diventa un po’ più complesso, perché una parte dei dati è comune:

numeroRuote
tipoAlimentazione
categoria
colore
marca
annoProduzione
modello

e una parte è specifica:

Per Macchina:

cc
porte
targa

Per Moto:

cc
targa

Per Bici:

marce
freno
sospensione
pieghevole

Puoi fare due strade.

18. Strada 1: un Builder separato per ogni classe figlia

Questa è la più semplice da capire.

Fai:

Macchina.Builder
Moto.Builder
Bici.Builder

Ogni classe ha il proprio builder.

Esempio:

Macchina macchina = new Macchina.Builder()
        .numeroRuote(4)
        .tipoAlimentazione(typeFuel.BENZINA)
        .categoria(categoriaVeicolo.BERLINA)
        .marca("Fiat")
        .modello("500")
        .annoProduzione(2025)
        .cc(1200)
        .porte(4)
        .build();
Moto moto = new Moto.Builder()
        .numeroRuote(2)
        .tipoAlimentazione(typeFuel.BENZINA)
        .categoria(categoriaVeicolo.STRADALE)
        .marca("Yamaha")
        .modello("R1")
        .annoProduzione(2025)
        .cc(900)
        .build();
Bici bici = new Bici.Builder()
        .numeroRuote(2)
        .tipoAlimentazione(typeFuel.NESSUNA)
        .categoria(categoriaVeicolo.CORSA)
        .marca("Bianchi")
        .modello("Grizl 5")
        .annoProduzione(2025)
        .marce(10)
        .sospensione(typeSospensione.ARIA)
        .pieghevole(false)
        .build();

Questa è la strada che ti consiglio all’inizio.

19. Strada 2: Builder generico nella classe padre

Puoi anche creare un Builder astratto dentro Veicoli, ma è più avanzato.

Tipo:

public abstract class Veicoli {

    protected int numeroRuote;
    protected typeFuel tipoAlimentazione;
    protected categoriaVeicolo categoria;
    protected String colore;
    protected String marca;
    protected int annoProduzione;
    protected String modello;

    protected Veicoli(Builder<?> builder) {
        this.numeroRuote = builder.numeroRuote;
        this.tipoAlimentazione = builder.tipoAlimentazione;
        this.categoria = builder.categoria;
        this.colore = builder.colore;
        this.marca = builder.marca;
        this.annoProduzione = builder.annoProduzione;
        this.modello = builder.modello;
    }

    public abstract static class Builder<T extends Builder<T>> {

        private int numeroRuote;
        private typeFuel tipoAlimentazione;
        private categoriaVeicolo categoria;
        private String colore;
        private String marca;
        private int annoProduzione;
        private String modello;

        public T numeroRuote(int numeroRuote) {
            this.numeroRuote = numeroRuote;
            return self();
        }

        public T tipoAlimentazione(typeFuel tipoAlimentazione) {
            this.tipoAlimentazione = tipoAlimentazione;
            return self();
        }

        public T categoria(categoriaVeicolo categoria) {
            this.categoria = categoria;
            return self();
        }

        public T colore(String colore) {
            this.colore = colore;
            return self();
        }

        public T marca(String marca) {
            this.marca = marca;
            return self();
        }

        public T annoProduzione(int annoProduzione) {
            this.annoProduzione = annoProduzione;
            return self();
        }

        public T modello(String modello) {
            this.modello = modello;
            return self();
        }

        protected abstract T self();

        public abstract Veicoli build();
    }
}

Poi Macchina estende questo builder:

public class Macchina extends Veicoli {

    private int cc;
    private int porte;

    private Macchina(Builder builder) {
        super(builder);
        this.cc = builder.cc;
        this.porte = builder.porte;
        this.setTipoVeicolo(typeVeicolo.MACCHINA);
    }

    public static class Builder extends Veicoli.Builder<Builder> {

        private int cc;
        private int porte;

        public Builder cc(int cc) {
            this.cc = cc;
            return this;
        }

        public Builder porte(int porte) {
            this.porte = porte;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public Macchina build() {
            return new Macchina(this);
        }
    }
}

Uso:

Macchina macchina = new Macchina.Builder()
        .numeroRuote(4)
        .tipoAlimentazione(typeFuel.BENZINA)
        .categoria(categoriaVeicolo.BERLINA)
        .colore("Bianco")
        .marca("Fiat")
        .annoProduzione(2025)
        .modello("500")
        .cc(1200)
        .porte(4)
        .build();

Questa soluzione è potente, ma è più difficile.

Per adesso ti consiglio la strada 1: un Builder per ogni classe figlia.

20. Perché nei Builder si usa return this

Questa è una cosa fondamentale.

Se scrivi:

public Builder marca(String marca) {
    this.marca = marca;
    return this;
}

puoi concatenare:

new Macchina.Builder()
        .marca("Fiat")
        .modello("500")
        .annoProduzione(2025)
        .build();

Se invece il metodo fosse void:

public void marca(String marca) {
    this.marca = marca;
}

dovresti scrivere:

Macchina.Builder builder = new Macchina.Builder();

builder.marca("Fiat");
builder.modello("500");
builder.annoProduzione(2025);

Macchina macchina = builder.build();

Funziona, ma è meno elegante.

21. Builder e oggetti immutabili

Spesso il Builder si usa con oggetti immutabili.

Un oggetto immutabile è un oggetto che, una volta creato, non cambia più.

Esempio:

public class Persona {

    private final String nome;
    private final String cognome;
    private final int eta;

    private Persona(Builder builder) {
        this.nome = builder.nome;
        this.cognome = builder.cognome;
        this.eta = builder.eta;
    }
}

I campi sono:

final

Quindi dopo il costruttore non possono più cambiare.

In quel caso potresti anche non mettere i setter.

22. Builder vs setter

Con i setter fai:

Macchina m = new Macchina();

m.setMarca("Fiat");
m.setModello("500");
m.setAnnoProduzione(2025);
m.setCc(1200);
m.setPorte(4);

Con Builder fai:

Macchina m = new Macchina.Builder()
        .marca("Fiat")
        .modello("500")
        .annoProduzione(2025)
        .cc(1200)
        .porte(4)
        .build();

Differenza:

Setter	Builder
modifichi l’oggetto dopo averlo creato	prepari i dati e poi crei l’oggetto
oggetto può rimanere incompleto	puoi validare tutto in build()
utile per oggetti semplici	utile per oggetti complessi
più diretto	più ordinato
23. Quando conviene usare il Builder

Conviene usarlo quando:

hai tanti parametri nel costruttore
molti parametri sono opzionali
hai tanti campi dello stesso tipo
vuoi evitare costruttori lunghissimi
vuoi validare l’oggetto prima di crearlo
vuoi rendere il codice più leggibile

Nel tuo progetto veicoli ha senso, perché Macchina, Moto e Bici hanno tanti campi.

24. Quando non conviene

Non conviene se la classe è semplice.

Esempio:

class Punto {
    private int x;
    private int y;
}

Qui un builder sarebbe esagerato.

Puoi fare semplicemente:

new Punto(10, 20);

oppure setter.

25. Riassunto secco

Una classe Builder dentro una classe serve a costruire oggetti complessi in modo ordinato.

Struttura tipica:

public class NomeClasse {

    private NomeClasse(Builder builder) {
        // copia dati dal builder
    }

    public static class Builder {

        public Builder campo1(String campo1) {
            this.campo1 = campo1;
            return this;
        }

        public NomeClasse build() {
            return new NomeClasse(this);
        }
    }
}

Uso:

NomeClasse obj = new NomeClasse.Builder()
        .campo1("valore")
        .campo2("valore")
        .build();

La classe Builder è quasi sempre:

static

perché deve creare l’oggetto finale, non dipendere da un oggetto già esistente.

Il metodo:

build()

è quello che crea davvero l’oggetto.

Il:

return this;

serve per concatenare i metodi.

Nel tuo progetto, la soluzione più semplice è creare:

Macchina.Builder
Moto.Builder
Bici.Builder

uno per ogni classe concreta.