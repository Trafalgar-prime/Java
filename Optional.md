# Optional in Java

Certo. In Java la classe `Optional` serve a gestire meglio i casi in cui un valore potrebbe esserci oppure potrebbe non esserci.

In parole semplici:

`Optional` è una scatola che può contenere un valore oppure può essere vuota.

Serve soprattutto per evitare errori come:

```text
NullPointerException
```

cioè errori causati da oggetti `null`.

## 1. Il problema: null

In Java spesso un metodo può restituire `null`.

Esempio:

```java
public Veicoli trova(int id) {

    for (Veicoli v : lista) {
        if (v.getId() == id) {
            return v;
        }
    }

    return null;
}
```

Questo metodo significa:

se trovo il veicolo lo restituisco, altrimenti restituisco `null`.

Il problema è che chi usa il metodo potrebbe fare:

```java
Veicoli v = trova(10);

System.out.println(v.getMarca());
```

Se `trova(10)` restituisce `null`, questa riga:

```java
v.getMarca()
```

genera:

```text
NullPointerException
```

Perché stai chiamando un metodo su un oggetto che non esiste.

## 2. Cos’è Optional

`Optional` risolve questo problema rendendo esplicito che un valore potrebbe mancare.

Invece di scrivere:

```java
public Veicoli trova(int id)
```

puoi scrivere:

```java
public Optional<Veicoli> trova(int id)
```

Questo significa:

questo metodo potrebbe restituire un veicolo, oppure potrebbe non trovare nulla.

## 3. Import di Optional

Per usarlo devi importare:

```java
import java.util.Optional;
```

## 4. Creare un Optional con valore

Puoi creare un `Optional` pieno con:

```java
Optional<String> nome = Optional.of("Luca");
```

Qui `nome` contiene `"Luca"`.

## 5. Creare un Optional vuoto

Puoi creare un `Optional` vuoto con:

```java
Optional<String> nome = Optional.empty();
```

Qui non c’è nessun valore.

È come dire:

non ho trovato niente.

## 6. Optional.of()

```java
Optional<String> nome = Optional.of("Luca");
```

`of()` si usa quando sei sicuro che il valore non è `null`.

Attenzione:

```java
String testo = null;

Optional<String> opt = Optional.of(testo);
```

Questo genera subito:

```text
NullPointerException
```

Perché `Optional.of()` non accetta `null`.

## 7. Optional.ofNullable()

Se un valore potrebbe essere `null`, devi usare:

```java
Optional<String> opt = Optional.ofNullable(testo);
```

Esempio:

```java
String testo = null;

Optional<String> opt = Optional.ofNullable(testo);
```

Qui non esplode.

Semplicemente crea un `Optional` vuoto.

Quindi:

```java
Optional.ofNullable(null)
```

equivale a:

```java
Optional.empty()
```

## 8. Differenza tra of() e ofNullable()

| Metodo | Quando usarlo |
|---|---|
| `Optional.of(valore)` | quando sei sicuro che valore non sia `null` |
| `Optional.ofNullable(valore)` | quando valore potrebbe essere `null` |
| `Optional.empty()` | quando vuoi creare un `Optional` vuoto |

Esempio:

```java
Optional<String> a = Optional.of("ciao");
Optional<String> b = Optional.ofNullable(null);
Optional<String> c = Optional.empty();
```

## 9. Controllare se il valore esiste: isPresent()

Puoi controllare se dentro l’`Optional` c’è un valore:

```java
Optional<String> nome = Optional.of("Luca");

if (nome.isPresent()) {
    System.out.println(nome.get());
}
```

Output:

```text
Luca
```

Questa parte:

```java
nome.isPresent()
```

significa:

dentro l’`Optional` c’è un valore?

## 10. Prendere il valore: get()

```java
nome.get()
```

estrae il valore contenuto nell’`Optional`.

Però attenzione: usare `get()` senza controllare prima è pericoloso.

Esempio sbagliato:

```java
Optional<String> nome = Optional.empty();

System.out.println(nome.get());
```

Questo genera errore:

```text
NoSuchElementException
```

Perché stai cercando di prendere un valore che non esiste.

Quindi, se usi `get()`, prima devi controllare:

```java
if (nome.isPresent()) {
    System.out.println(nome.get());
}
```

## 11. isEmpty()

Da Java 11 puoi usare anche:

```java
isEmpty()
```

Esempio:

```java
Optional<String> nome = Optional.empty();

if (nome.isEmpty()) {
    System.out.println("Nessun valore presente");
}
```

È il contrario di `isPresent()`.

## 12. ifPresent()

Metodo più elegante:

```java
Optional<String> nome = Optional.of("Luca");

nome.ifPresent(n -> System.out.println(n));
```

Output:

```text
Luca
```

Significa:

se il valore esiste, allora esegui questa azione.

Esempio più leggibile:

```java
nome.ifPresent(n -> {
    System.out.println("Nome trovato: " + n);
});
```

Se l’`Optional` è vuoto, non succede nulla.

## 13. ifPresentOrElse()

Da Java 9 puoi fare:

```java
Optional<String> nome = Optional.empty();

nome.ifPresentOrElse(
        n -> System.out.println("Nome trovato: " + n),
        () -> System.out.println("Nome non trovato")
);
```

Se il valore esiste, esegue la prima parte.

Se il valore non esiste, esegue la seconda.

Esempio con valore presente:

```java
Optional<String> nome = Optional.of("Luca");

nome.ifPresentOrElse(
        n -> System.out.println("Nome trovato: " + n),
        () -> System.out.println("Nome non trovato")
);
```

Output:

```text
Nome trovato: Luca
```

Esempio con valore assente:

```java
Optional<String> nome = Optional.empty();

nome.ifPresentOrElse(
        n -> System.out.println("Nome trovato: " + n),
        () -> System.out.println("Nome non trovato")
);
```

Output:

```text
Nome non trovato
```

## 14. orElse()

`orElse()` serve a dare un valore di riserva.

Esempio:

```java
Optional<String> nome = Optional.empty();

String risultato = nome.orElse("Nome sconosciuto");

System.out.println(risultato);
```

Output:

```text
Nome sconosciuto
```

Se invece l’`Optional` contiene un valore:

```java
Optional<String> nome = Optional.of("Luca");

String risultato = nome.orElse("Nome sconosciuto");

System.out.println(risultato);
```

Output:

```text
Luca
```

Quindi:

```java
orElse(...)
```

significa:

dammi il valore se c’è, altrimenti usa questo valore alternativo.

## 15. orElseGet()

`orElseGet()` è simile a `orElse()`, ma il valore alternativo viene calcolato solo se serve.

Esempio:

```java
Optional<String> nome = Optional.empty();

String risultato = nome.orElseGet(() -> "Nome generato");

System.out.println(risultato);
```

Output:

```text
Nome generato
```

La differenza è sottile ma importante.

Con `orElse()`:

```java
String risultato = nome.orElse(creaNome());
```

`creaNome()` viene chiamato sempre, anche se `nome` contiene già un valore.

Con `orElseGet()`:

```java
String risultato = nome.orElseGet(() -> creaNome());
```

`creaNome()` viene chiamato solo se l’`Optional` è vuoto.

## 16. Esempio differenza orElse() e orElseGet()

```java
public static String creaNome() {
    System.out.println("Creo nome alternativo");
    return "Nome alternativo";
}

public static void main(String[] args) {

    Optional<String> nome = Optional.of("Luca");

    String risultato = nome.orElse(creaNome());

    System.out.println(risultato);
}
```

Output:

```text
Creo nome alternativo
Luca
```

Anche se il valore è `"Luca"`, `creaNome()` viene comunque eseguito.

Con `orElseGet()`:

```java
Optional<String> nome = Optional.of("Luca");

String risultato = nome.orElseGet(() -> creaNome());

System.out.println(risultato);
```

Output:

```text
Luca
```

Qui `creaNome()` non viene chiamato.

Regola pratica:

se il valore alternativo è semplice, usa `orElse()`;
se il valore alternativo richiede calcoli, usa `orElseGet()`.

## 17. orElseThrow()

`orElseThrow()` serve a lanciare un’eccezione se il valore non c’è.

Esempio:

```java
Optional<String> nome = Optional.empty();

String risultato = nome.orElseThrow(() ->
        new IllegalArgumentException("Nome non trovato")
);
```

Qui, siccome `nome` è vuoto, viene lanciata l’eccezione.

Se invece:

```java
Optional<String> nome = Optional.of("Luca");

String risultato = nome.orElseThrow(() ->
        new IllegalArgumentException("Nome non trovato")
);

System.out.println(risultato);
```

Output:

```text
Luca
```

Questa è molto utile nei metodi `trova`.

## 18. Esempio nel tuo progetto: trova

Prima magari avevi:

```java
public Veicoli trova(int id) {

    for (Veicoli v : SingleTon.getInstance().getAllVehicles()) {
        if (v.getId() == id) {
            return v;
        }
    }

    return null;
}
```

Con `Optional` puoi scrivere:

```java
public Optional<Veicoli> trova(int id) {

    for (Veicoli v : SingleTon.getInstance().getAllVehicles()) {
        if (v.getId() == id) {
            return Optional.of(v);
        }
    }

    return Optional.empty();
}
```

Adesso chi chiama il metodo è costretto a ragionare sul fatto che il veicolo potrebbe non esistere.

Uso:

```java
Optional<Veicoli> risultato = trova(10);

if (risultato.isPresent()) {
    Veicoli v = risultato.get();
    System.out.println(v);
} else {
    System.out.println("Veicolo non trovato");
}
```

Oppure meglio:

```java
trova(10).ifPresentOrElse(
        v -> System.out.println(v),
        () -> System.out.println("Veicolo non trovato")
);
```

## 19. Esempio con orElseThrow() nel tuo progetto

Se vuoi che il programma lanci errore quando il veicolo non esiste:

```java
public Veicoli trovaObbligatorio(int id) throws Exception {

    return trova(id).orElseThrow(() ->
            new Exception("Nessun veicolo trovato con id: " + id)
    );
}
```

Dove `trova(id)` ritorna:

```java
Optional<Veicoli>
```

Metodo completo:

```java
public Optional<Veicoli> trova(int id) {

    for (Veicoli v : SingleTon.getInstance().getAllVehicles()) {
        if (v.getId() == id) {
            return Optional.of(v);
        }
    }

    return Optional.empty();
}

public Veicoli trovaObbligatorio(int id) throws Exception {

    return trova(id).orElseThrow(() ->
            new Exception("Nessun veicolo trovato con id: " + id)
    );
}
```

## 20. Optional con Stream

`Optional` viene usato spesso con gli stream.

Esempio:

```java
Optional<Veicoli> veicolo = SingleTon.getInstance()
        .getAllVehicles()
        .stream()
        .filter(v -> v.getId() == id)
        .findFirst();
```

Qui:

```java
findFirst()
```

restituisce un:

```java
Optional<Veicoli>
```

Perché lo stream potrebbe trovare un veicolo oppure no.

Metodo completo:

```java
public Optional<Veicoli> trova(int id) {

    return SingleTon.getInstance()
            .getAllVehicles()
            .stream()
            .filter(v -> v.getId() == id)
            .findFirst();
}
```

Questa è una versione molto pulita.

## 21. Optional con filtro per tipo veicolo

Esempio:

```java
public Optional<Veicoli> trovaMacchina(int id) {

    return SingleTon.getInstance()
            .getAllVehicles()
            .stream()
            .filter(v -> v.getId() == id)
            .filter(v -> v.getTipoVeicolo() == typeVeicolo.MACCHINA)
            .findFirst();
}
```

Qui trova il veicolo solo se:

```java
v.getId() == id
```

e se:

```java
v.getTipoVeicolo() == typeVeicolo.MACCHINA
```

## 22. Optional e map()

`map()` serve a trasformare il valore dentro l’`Optional`.

Esempio:

```java
Optional<String> nome = Optional.of("luca");

Optional<String> nomeMaiuscolo = nome.map(n -> n.toUpperCase());

System.out.println(nomeMaiuscolo.get());
```

Output:

```text
LUCA
```

Se l’`Optional` è vuoto, `map()` non fa nulla e resta vuoto.

Esempio:

```java
Optional<String> nome = Optional.empty();

Optional<String> nomeMaiuscolo = nome.map(n -> n.toUpperCase());

System.out.println(nomeMaiuscolo);
```

Output:

```text
Optional.empty
```

## 23. Optional e filter()

`filter()` serve a mantenere il valore solo se rispetta una condizione.

Esempio:

```java
Optional<String> nome = Optional.of("Luca");

Optional<String> risultato = nome.filter(n -> n.length() > 3);

System.out.println(risultato);
```

Output:

```text
Optional[Luca]
```

Se la condizione è falsa:

```java
Optional<String> nome = Optional.of("Luca");

Optional<String> risultato = nome.filter(n -> n.length() > 10);

System.out.println(risultato);
```

Output:

```text
Optional.empty
```

## 24. Optional e flatMap()

`flatMap()` si usa quando la trasformazione restituisce già un `Optional`.

Esempio più avanzato:

```java
class Persona {

    private String nome;
    private Indirizzo indirizzo;

    public Optional<Indirizzo> getIndirizzo() {
        return Optional.ofNullable(indirizzo);
    }
}

class Indirizzo {

    private String citta;

    public Optional<String> getCitta() {
        return Optional.ofNullable(citta);
    }
}
```

Uso:

```java
Optional<Persona> persona = Optional.of(new Persona());

Optional<String> citta = persona
        .flatMap(p -> p.getIndirizzo())
        .flatMap(i -> i.getCitta());
```

Se `persona`, `indirizzo` o `città` mancano, il risultato sarà `Optional.empty()`.

Senza `flatMap`, avresti `Optional` dentro `Optional`:

```java
Optional<Optional<Indirizzo>>
```

che è scomodo.

## 25. Optional in input ai metodi: attenzione

Di solito è meglio non usare `Optional` come parametro.

Meglio evitare:

```java
public void stampa(Optional<String> nome) {
}
```

Meglio:

```java
public void stampa(String nome) {
}
```

e dentro, se serve:

```java
Optional.ofNullable(nome)
```

`Optional` è pensato soprattutto come valore di ritorno, non come parametro.

## 26. Optional come campo di una classe: attenzione

Di solito è meglio non fare:

```java
class Persona {
    private Optional<String> nome;
}
```

Meglio:

```java
class Persona {
    private String nome;
}
```

e se vuoi un getter sicuro:

```java
public Optional<String> getNome() {
    return Optional.ofNullable(nome);
}
```

Questo è più pulito.

## 27. Quando usare Optional

Usa `Optional` quando un metodo può non trovare un risultato.

Esempi buoni:

```java
Optional<Veicoli> trova(int id)
Optional<Utente> cercaUtente(String email)
Optional<Ordine> trovaOrdine(int numero)
Optional<String> getDescrizione()
```

Serve quando vuoi dire chiaramente:

questo valore potrebbe non esserci.

## 28. Quando NON usare Optional

Non usarlo per tutto.

Non serve per:

```java
String nome = "Luca";
int numero = 10;
List<String> lista = new ArrayList<>();
```

Non serve se sei sicuro che il valore esiste sempre.

Non usarlo solo per evitare qualsiasi `null` ovunque. Usalo soprattutto nei metodi di ricerca.

## 29. Esempio completo nel tuo stile

```java
import java.util.Optional;

public class VeicoliService {

    public Optional<Veicoli> trova(int id) {

        return SingleTon.getInstance()
                .getAllVehicles()
                .stream()
                .filter(v -> v.getId() == id)
                .findFirst();
    }

    public void stampaVeicolo(int id) {

        trova(id).ifPresentOrElse(
                v -> System.out.println(v),
                () -> System.out.println("Veicolo non trovato con id: " + id)
        );
    }

    public Veicoli trovaOppureErrore(int id) throws Exception {

        return trova(id).orElseThrow(() ->
                new Exception("Nessun veicolo trovato con id: " + id)
        );
    }
}
```

Uso:

```java
VeicoliService service = new VeicoliService();

service.stampaVeicolo(10);
```

Se il veicolo esiste, lo stampa.

Se non esiste, stampa:

```text
Veicolo non trovato con id: 10
```

## 30. Riassunto secco

`Optional<T>` significa:

forse ho un valore di tipo `T`, forse no.

Creare `Optional`:

```java
Optional.of(valore)
Optional.ofNullable(valore)
Optional.empty()
```

Controllare:

```java
isPresent()
isEmpty()
```

Usare il valore:

```java
ifPresent()
ifPresentOrElse()
orElse()
orElseGet()
orElseThrow()
```

Trasformare:

```java
map()
flatMap()
filter()
```

Nel tuo progetto, invece di:

```java
return null;
```

puoi fare:

```java
return Optional.empty();
```

e invece di:

```java
return veicolo;
```

puoi fare:

```java
return Optional.of(veicolo);
```

La regola pratica è:

usa `Optional` soprattutto nei metodi che cercano qualcosa e potrebbero non trovarla.
