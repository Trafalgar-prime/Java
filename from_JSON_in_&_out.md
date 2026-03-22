# from JSON in & out

## JSON in Java: come scrivere un file JSON e come estrarre poi le informazioni

In Java, per lavorare bene con i file JSON, hai due strade:

1. **Scrivere JSON a mano**
2. **Usare una libreria dedicata**

La strada corretta, pratica e professionale è la seconda: usare una libreria come **Jackson**.

Scrivere JSON a mano è possibile, ma è scomodo, fragile e pieno di errori possibili, soprattutto quando i dati diventano più complessi o iniziano a comparire liste, oggetti annidati o valori mancanti.

---

## Perché usare Jackson

Jackson è una delle librerie più usate in Java per:

- convertire oggetti Java in JSON
- leggere JSON e trasformarlo in oggetti Java
- leggere liste di oggetti
- scrivere file JSON in modo ordinato e pulito

In pratica fa da ponte tra il mondo Java e il mondo JSON.

---

## Dipendenza Maven

Se usi **Maven**, aggiungi questa dipendenza nel file `pom.xml`:

```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.17.0</version>
</dependency>
```

### Spiegazione
- `<dependency>`: indica a Maven che vuoi aggiungere una libreria esterna.
- `<groupId>`: identifica il gruppo che pubblica la libreria.
- `<artifactId>`: identifica la libreria specifica.
- `<version>`: indica quale versione usare.

---

# 1. Creare una classe Java da salvare in JSON

Per esempio, immaginiamo di voler salvare uno studente in un file JSON.

## Codice

```java
public class Studente {
    public String nome;
    public int eta;

    // costruttore vuoto (necessario per Jackson)
    public Studente() {}

    public Studente(String nome, int eta) {
        this.nome = nome;
        this.eta = eta;
    }
}
```

---

## Spiegazione riga per riga

```java
public class Studente {
```
- `public`: la classe è accessibile anche da altre classi.
- `class`: stai dichiarando una classe.
- `Studente`: nome della classe.
- `{`: inizio del corpo della classe.

```java
    public String nome;
```
- `public`: il campo è accessibile direttamente.
- `String`: il tipo del dato è una stringa.
- `nome`: nome dell'attributo.

```java
    public int eta;
```
- `public`: campo accessibile direttamente.
- `int`: numero intero.
- `eta`: attributo che rappresenta l'età.

```java
    public Studente() {}
```
- Questo è il **costruttore vuoto**.
- `public`: visibile da altre classi.
- `Studente()`: costruttore con lo stesso nome della classe e senza parametri.
- `{}`: corpo vuoto.
- Jackson spesso ha bisogno di questo costruttore per creare l'oggetto quando legge il JSON.

```java
    public Studente(String nome, int eta) {
```
- Costruttore con parametri.
- Riceve due valori: `nome` e `eta`.

```java
        this.nome = nome;
```
- `this.nome`: indica l'attributo dell'oggetto.
- `nome`: è il parametro ricevuto dal costruttore.
- Questa riga assegna il parametro all'attributo.

```java
        this.eta = eta;
```
- Stesso discorso: assegna il parametro `eta` all'attributo dell'oggetto.

```java
    }
}
```
- Fine del costruttore.
- Fine della classe.

---

# 2. Scrivere un oggetto Java dentro un file JSON

Una volta creata la classe, puoi generare un oggetto e salvarlo in un file JSON.

## Codice completo

```java
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        Studente s = new Studente("Lorenzo", 23);

        mapper.writeValue(new File("studente.json"), s);

        System.out.println("File JSON creato!");
    }
}
```

---

## Spiegazione riga per riga

```java
import com.fasterxml.jackson.databind.ObjectMapper;
```
- `import`: serve a usare classi che si trovano in altri package.
- `ObjectMapper`: è la classe principale di Jackson che converte oggetti Java in JSON e JSON in oggetti Java.

```java
import java.io.File;
```
- Importa la classe `File`, che serve per rappresentare un file nel filesystem.

```java
public class Main {
```
- Dichiara la classe principale del programma.

```java
    public static void main(String[] args) throws Exception {
```
- `public`: metodo accessibile.
- `static`: può essere eseguito senza creare un oggetto della classe `Main`.
- `void`: non restituisce nulla.
- `main`: punto di ingresso del programma.
- `String[] args`: array di argomenti passati da linea di comando.
- `throws Exception`: indica che il metodo può lanciare eccezioni senza gestirle direttamente con `try-catch`.

```java
        ObjectMapper mapper = new ObjectMapper();
```
- Crea un oggetto `ObjectMapper`.
- Questo oggetto farà il lavoro di scrittura e lettura del JSON.

```java
        Studente s = new Studente("Lorenzo", 23);
```
- Crea un oggetto `Studente`.
- `"Lorenzo"` viene assegnato al campo `nome`.
- `23` viene assegnato al campo `eta`.

```java
        mapper.writeValue(new File("studente.json"), s);
```
- `mapper.writeValue(...)`: metodo che scrive un oggetto Java in formato JSON.
- `new File("studente.json")`: crea il riferimento al file dove scrivere.
- `s`: è l'oggetto da convertire in JSON e salvare nel file.

```java
        System.out.println("File JSON creato!");
```
- Stampa un messaggio sulla console per confermare che il file è stato creato.

```java
    }
}
```
- Fine del metodo `main`.
- Fine della classe `Main`.

---

## JSON generato

Il file `studente.json` conterrà:

```json
{
  "nome": "Lorenzo",
  "eta": 23
}
```

---

## Spiegazione del JSON

```json
{
```
- Inizio dell'oggetto JSON.

```json
  "nome": "Lorenzo",
```
- `"nome"` è la chiave.
- `"Lorenzo"` è il valore associato.

```json
  "eta": 23
```
- `"eta"` è la chiave.
- `23` è il valore numerico.

```json
}
```
- Fine dell'oggetto JSON.

---

# 3. Leggere un file JSON e trasformarlo in un oggetto Java

Dopo aver scritto il file, puoi anche leggerlo.

## Codice completo

```java
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        Studente s = mapper.readValue(new File("studente.json"), Studente.class);

        System.out.println("Nome: " + s.nome);
        System.out.println("Età: " + s.eta);
    }
}
```

---

## Spiegazione riga per riga

```java
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
```
- Gli import sono gli stessi di prima.
- `ObjectMapper` serve per leggere il JSON.
- `File` serve per puntare al file da leggere.

```java
public class Main {
```
- Dichiara la classe principale.

```java
    public static void main(String[] args) throws Exception {
```
- Metodo principale del programma.

```java
        ObjectMapper mapper = new ObjectMapper();
```
- Crea l'oggetto Jackson che si occuperà della conversione.

```java
        Studente s = mapper.readValue(new File("studente.json"), Studente.class);
```
- `readValue(...)`: legge un JSON e lo converte in oggetto Java.
- `new File("studente.json")`: indica il file da leggere.
- `Studente.class`: indica a Jackson che il contenuto deve essere convertito in un oggetto della classe `Studente`.
- Il risultato viene salvato nella variabile `s`.

```java
        System.out.println("Nome: " + s.nome);
```
- Stampa il valore del campo `nome` letto dal JSON.

```java
        System.out.println("Età: " + s.eta);
```
- Stampa il valore del campo `eta` letto dal JSON.

```java
    }
}
```
- Fine del metodo e della classe.

---

# 4. Scrivere una lista di oggetti in JSON

Spesso non devi salvare un solo oggetto, ma una lista di oggetti.

## Codice

```java
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        List<Studente> lista = new ArrayList<>();
        lista.add(new Studente("Lorenzo", 23));
        lista.add(new Studente("Marco", 25));

        mapper.writeValue(new File("studenti.json"), lista);

        System.out.println("Lista JSON creata!");
    }
}
```

---

## Spiegazione riga per riga

```java
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
```
- `ObjectMapper`: per JSON.
- `File`: per il file.
- `ArrayList`: implementazione concreta di una lista dinamica.
- `List`: interfaccia generale per gestire liste.

```java
public class Main {
    public static void main(String[] args) throws Exception {
```
- Struttura standard del programma.

```java
        ObjectMapper mapper = new ObjectMapper();
```
- Crea il convertitore JSON.

```java
        List<Studente> lista = new ArrayList<>();
```
- Dichiara una lista di oggetti `Studente`.
- `new ArrayList<>()` crea concretamente la lista vuota.

```java
        lista.add(new Studente("Lorenzo", 23));
```
- Crea un nuovo studente e lo aggiunge alla lista.

```java
        lista.add(new Studente("Marco", 25));
```
- Aggiunge un secondo studente alla lista.

```java
        mapper.writeValue(new File("studenti.json"), lista);
```
- Scrive l'intera lista in formato JSON dentro il file `studenti.json`.

```java
        System.out.println("Lista JSON creata!");
```
- Messaggio di conferma.

```java
    }
}
```
- Fine del programma.

---

## JSON generato

```json
[
  {
    "nome": "Lorenzo",
    "eta": 23
  },
  {
    "nome": "Marco",
    "eta": 25
  }
]
```

---

## Spiegazione del JSON con array

```json
[
```
- Inizio di un array JSON.

```json
  {
    "nome": "Lorenzo",
    "eta": 23
  },
```
- Primo oggetto della lista.

```json
  {
    "nome": "Marco",
    "eta": 25
  }
```
- Secondo oggetto della lista.

```json
]
```
- Fine dell'array JSON.

---

# 5. Leggere una lista di oggetti da un file JSON

Qui c'è il passaggio che spesso crea confusione.

Quando leggi una lista, non basta usare `List.class`, perché Java perde il tipo generico. Devi usare `TypeReference`.

## Codice

```java
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        List<Studente> lista = mapper.readValue(
            new File("studenti.json"),
            new TypeReference<List<Studente>>() {}
        );

        for (Studente s : lista) {
            System.out.println(s.nome);
            System.out.println(s.eta);
        }
    }
}
```

---

## Spiegazione riga per riga

```java
import com.fasterxml.jackson.core.type.TypeReference;
```
- Importa `TypeReference`, che serve a dire a Jackson che deve leggere una lista di un tipo preciso.

```java
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.List;
```
- Import normali per JSON, file e liste.

```java
public class Main {
    public static void main(String[] args) throws Exception {
```
- Struttura del programma.

```java
        ObjectMapper mapper = new ObjectMapper();
```
- Crea il mapper JSON.

```java
        List<Studente> lista = mapper.readValue(
```
- Inizia la lettura del file JSON in una lista di studenti.

```java
            new File("studenti.json"),
```
- Indica il file da leggere.

```java
            new TypeReference<List<Studente>>() {}
```
- Dice a Jackson: “leggi questo file come una lista di oggetti `Studente`”.
- Le parentesi graffe finali `{}` servono a creare una sottoclasse anonima di `TypeReference`.

```java
        );
```
- Fine del metodo `readValue`.

```java
        for (Studente s : lista) {
```
- Ciclo `for-each`: scorre ogni studente presente nella lista.

```java
            System.out.println(s.nome);
```
- Stampa il nome dello studente corrente.

```java
            System.out.println(s.eta);
```
- Stampa l'età dello studente corrente.

```java
        }
    }
}
```
- Fine del ciclo, del metodo e della classe.

---

# 6. Errori classici da evitare

## 1. Mancanza del costruttore vuoto
Se la classe non ha un costruttore vuoto, Jackson può non riuscire a creare l'oggetto quando legge il JSON.

### Esempio problematico

```java
public class Studente {
    public String nome;
    public int eta;

    public Studente(String nome, int eta) {
        this.nome = nome;
        this.eta = eta;
    }
}
```

### Perché può creare problemi
Quando Jackson legge il JSON, spesso prova prima a creare un oggetto vuoto e poi a riempire i campi. Se non trova il costruttore vuoto, può andare in errore.

---

## 2. Nomi dei campi diversi da quelli del JSON

Se nel JSON c'è:

```json
{
  "nome": "Lorenzo"
}
```

ma nella classe Java hai:

```java
public String nomeStudente;
```

Jackson non capisce automaticamente che `nome` del JSON deve andare in `nomeStudente`, a meno che tu non usi annotazioni specifiche.

---

## 3. Percorso file sbagliato

Se scrivi:

```java
new File("studente.json")
```

il file viene cercato o creato nella **working directory** del progetto, non per forza dentro `src`.

Quindi devi sempre sapere da dove viene eseguito il programma.

---

## 4. Confondere JSON con una semplice stringa

JSON non è “solo testo a caso”.
È testo strutturato con regole precise:

- oggetti tra `{ }`
- array tra `[ ]`
- chiavi tra virgolette
- stringhe tra virgolette
- numeri senza virgolette

---

# 7. Perché questa cosa è importante davvero

Sapere usare JSON in Java ti serve in un sacco di casi reali:

- salvare utenti
- leggere configurazioni
- esportare dati
- importare dati
- parlare con API REST
- memorizzare risultati o impostazioni di un'app

Per esempio:
- login di utenti
- elenco esami
- lista studenti
- configurazioni di un'app desktop
- dati scambiati tra frontend e backend

---

# 8. Differenza pratica tra scrivere e leggere JSON

## Scrivere JSON
Parti da un oggetto Java e lo trasformi in file JSON.

**Direzione:**
Java → JSON

Esempio:
```java
Studente s = new Studente("Lorenzo", 23);
mapper.writeValue(new File("studente.json"), s);
```

## Leggere JSON
Parti da un file JSON e lo trasformi in oggetto Java.

**Direzione:**
JSON → Java

Esempio:
```java
Studente s = mapper.readValue(new File("studente.json"), Studente.class);
```

---

# 9. Versione minima da ricordare a memoria

Se vuoi ricordarti l'essenziale:

## Scrivere un oggetto
```java
ObjectMapper mapper = new ObjectMapper();
mapper.writeValue(new File("studente.json"), oggetto);
```

## Leggere un oggetto
```java
ObjectMapper mapper = new ObjectMapper();
Studente s = mapper.readValue(new File("studente.json"), Studente.class);
```

## Scrivere una lista
```java
mapper.writeValue(new File("studenti.json"), lista);
```

## Leggere una lista
```java
List<Studente> lista = mapper.readValue(
    new File("studenti.json"),
    new TypeReference<List<Studente>>() {}
);
```

---

# 10. Consiglio finale importante

Non ti basta saper copiare il codice.

Devi capire bene queste 4 idee:

1. **Oggetto Java**  
   È la struttura dati nel tuo programma.

2. **JSON**  
   È il formato testuale con cui salvi o scambi dati.

3. **Serializzazione**  
   Trasformare un oggetto Java in JSON.

4. **Deserializzazione**  
   Trasformare JSON in un oggetto Java.

Quando capisci questo, hai davvero capito il meccanismo.

---

# 11. Esempio completo finale

Qui sotto ti lascio un esempio completo, ordinato e pronto da studiare.

## Classe `Studente.java`

```java
public class Studente {
    public String nome;
    public int eta;

    public Studente() {}

    public Studente(String nome, int eta) {
        this.nome = nome;
        this.eta = eta;
    }
}
```

### Spiegazione
- definisce il modello dati
- contiene i campi da salvare nel JSON
- ha il costruttore vuoto per Jackson
- ha il costruttore comodo per creare oggetti rapidamente

---

## Classe `Main.java`

```java
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        List<Studente> lista = new ArrayList<>();
        lista.add(new Studente("Lorenzo", 23));
        lista.add(new Studente("Marco", 25));

        mapper.writeValue(new File("studenti.json"), lista);

        List<Studente> listaLetta = mapper.readValue(
            new File("studenti.json"),
            new TypeReference<List<Studente>>() {}
        );

        for (Studente s : listaLetta) {
            System.out.println("Nome: " + s.nome + " - Età: " + s.eta);
        }
    }
}
```

---

## Spiegazione riga per riga del flusso logico

```java
ObjectMapper mapper = new ObjectMapper();
```
- crea il convertitore JSON

```java
List<Studente> lista = new ArrayList<>();
```
- crea la lista vuota di studenti

```java
lista.add(new Studente("Lorenzo", 23));
lista.add(new Studente("Marco", 25));
```
- aggiunge due oggetti alla lista

```java
mapper.writeValue(new File("studenti.json"), lista);
```
- salva la lista dentro il file JSON

```java
List<Studente> listaLetta = mapper.readValue(
    new File("studenti.json"),
    new TypeReference<List<Studente>>() {}
);
```
- legge il file JSON e lo riconverte in una lista Java

```java
for (Studente s : listaLetta) {
```
- scorre tutti gli elementi letti

```java
    System.out.println("Nome: " + s.nome + " - Età: " + s.eta);
```
- stampa nome ed età di ogni studente

---

# 12. Conclusione

Quello che devi portarti via è questo:

- con `writeValue()` **scrivi** JSON
- con `readValue()` **leggi** JSON
- con `ObjectMapper` fai entrambe le cose
- con `TypeReference<List<T>>` leggi correttamente le liste
- con una classe Java ben fatta puoi convertire dati avanti e indietro senza problemi

Questo è il modo standard, corretto e professionale di lavorare con JSON in Java.
