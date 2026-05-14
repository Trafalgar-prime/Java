# Parametro generico `<T>` in Java

In Java il parametro generico T serve a dire:

questa classe, interfaccia o metodo lavora con un tipo che deciderò dopo.

La T non è una parola magica: è solo un nome convenzionale. Di solito significa Type.

Esempio base:

```java
class Box<T> {

    private T valore;

    public void setValore(T valore) {
        this.valore = valore;
    }

    public T getValore() {
        return valore;
    }
}
```

Qui T è un tipo generico.

Significa:

Io non so ancora se dentro Box ci sarà una String, un Integer, una Persona, un Double, ecc. Lo deciderà chi userà la classe.

## 1. Esempio senza generics

Immagina questa classe:

```java
class BoxString {

    private String valore;

    public void setValore(String valore) {
        this.valore = valore;
    }

    public String getValore() {
        return valore;
    }
}
```

Questa classe funziona solo con String.

Uso:

```java
BoxString box = new BoxString();

box.setValore("Ciao");

String testo = box.getValore();
```

Ma se vuoi una scatola per numeri?

Dovresti creare un’altra classe:

```java
class BoxInteger {

    private Integer valore;

    public void setValore(Integer valore) {
        this.valore = valore;
    }

    public Integer getValore() {
        return valore;
    }
}
```

Quindi avresti tante classi quasi uguali.

Il generico T serve proprio a evitare questo.

## 2. Esempio con T

```java
class Box<T> {

    private T valore;

    public void setValore(T valore) {
        this.valore = valore;
    }

    public T getValore() {
        return valore;
    }
}
```

Ora puoi usare la stessa classe con tipi diversi.

Con String:

```java
Box<String> boxTesto = new Box<>();

boxTesto.setValore("Ciao");

String testo = boxTesto.getValore();

System.out.println(testo);
```

Con Integer:

```java
Box<Integer> boxNumero = new Box<>();

boxNumero.setValore(25);

Integer numero = boxNumero.getValore();

System.out.println(numero);
```

Con una tua classe:

```java
class Persona {
    String nome;

    Persona(String nome) {
        this.nome = nome;
    }
}
```

Uso:

```java
Box<Persona> boxPersona = new Box<>();

boxPersona.setValore(new Persona("Luca"));

Persona p = boxPersona.getValore();
```

## 3. Cosa significa davvero Box<T>?

Questa riga:

```java
class Box<T>
```

significa:

Creo una classe chiamata Box, ma il tipo interno non lo fisso subito.

Quando poi scrivi:

```java
Box<String> box = new Box<>();
```

Java sostituisce mentalmente T con String.

Quindi:

```java
private T valore;
```

diventa come se fosse:

```java
private String valore;
```

e:

```java
public T getValore()
```

diventa come se fosse:

```java
public String getValore()
```

Quando scrivi:

```java
Box<Integer> box = new Box<>();
```

Java interpreta T come Integer.

## 4. Perché non usare Object?

Prima dei generics si usava spesso Object.

Esempio:

```java
class Box {

    private Object valore;

    public void setValore(Object valore) {
        this.valore = valore;
    }

    public Object getValore() {
        return valore;
    }
}
```

Sembra flessibile, perché Object può contenere tutto.

Uso:

```java
Box box = new Box();

box.setValore("Ciao");
```

Il problema arriva quando riprendi il valore:

```java
String testo = box.getValore();
```

Questo non funziona, perché getValore() restituisce Object.

Devi fare cast:

```java
String testo = (String) box.getValore();
```

Il cast è pericoloso.

Esempio:

```java
Box box = new Box();

box.setValore(100);

String testo = (String) box.getValore();
```

Questo compila, ma durante l’esecuzione genera errore:

```java
ClassCastException
```

Con T, Java controlla il tipo prima.

```java
Box<String> box = new Box<>();

box.setValore("Ciao");

String testo = box.getValore();
```

Qui non serve cast.

E se provi a fare:

```java
box.setValore(100);
```

Java ti dà errore subito, perché quel Box accetta solo String.

## 5. T nelle Collection

Tu usi già i generics quando scrivi:

```java
ArrayList<String> nomi = new ArrayList<>();
```

Qui String è il tipo generico.

La classe ArrayList internamente è dichiarata più o meno così:

```java
class ArrayList<E> {
    // ...
}
```

E significa Element, cioè tipo degli elementi.

Quando scrivi:

```java
ArrayList<String>
```

stai dicendo:

questa lista contiene solo String.

Quando scrivi:

```java
ArrayList<Integer>
```

stai dicendo:

questa lista contiene solo Integer.

Esempio:

```java
ArrayList<String> nomi = new ArrayList<>();

nomi.add("Luca");
nomi.add("Marco");
```

Questo è sbagliato:

```java
nomi.add(25);
```

perché 25 è un Integer, non una String.

## 6. T nei metodi

Puoi usare T anche nei metodi, non solo nelle classi.

Esempio:

```java
public class Utility {

    public static <T> void stampa(T valore) {
        System.out.println(valore);
    }
}
```

Uso:

```java
Utility.stampa("Ciao");
Utility.stampa(10);
Utility.stampa(3.14);
```

Qui il metodo accetta qualsiasi tipo.

Questa parte:

```java
<T>
```

prima del tipo di ritorno serve a dire:

questo metodo usa un parametro generico chiamato T.

La struttura è:

```java
public static <T> void stampa(T valore)
```

Dove:

```java
<T>
```

dichiara il generico.

```java
void
```

è il tipo di ritorno.

```java
stampa
```

è il nome del metodo.

```java
T valore
```

è il parametro del metodo.

## 7. Metodo generico che restituisce T

Esempio:

```java
public class Utility {

    public static <T> T ritorna(T valore) {
        return valore;
    }
}
```

Uso:

```java
String testo = Utility.ritorna("Ciao");

Integer numero = Utility.ritorna(10);
```

Quando passi "Ciao", Java capisce che T è String.

Quando passi 10, Java capisce che T è Integer.

## 8. Due parametri generici

Puoi avere più tipi generici.

Esempio:

```java
class Coppia<K, V> {

    private K chiave;
    private V valore;

    public Coppia(K chiave, V valore) {
        this.chiave = chiave;
        this.valore = valore;
    }

    public K getChiave() {
        return chiave;
    }

    public V getValore() {
        return valore;
    }
}
```

Uso:

```java
Coppia<String, Integer> voto = new Coppia<>("Luca", 28);

String nome = voto.getChiave();
Integer numero = voto.getValore();
```

Qui:

K

rappresenta il tipo della chiave.

V

rappresenta il tipo del valore.

Infatti nelle mappe trovi spesso:

```java
Map<K, V>
```

cioè:

```text
Map<Chiave, Valore>
```

Esempio reale:

```java
Map<String, Integer> voti = new HashMap<>();
```

Qui:

String

è il tipo della chiave.

Integer

è il tipo del valore.

## 9. Nomi comuni dei generics

Puoi chiamare il generico come vuoi:

```java
class Box<Pippo> {
}
```

Funziona.

Però per convenzione si usano lettere standard:

| Lettera | Significato comune |
|---|---|
| T | Type |
| E | Element |
| K | Key |
| V | Value |
| N | Number |
| R | Return |
| S, U | secondo/terzo tipo generico |

Esempi:

```java
List<E>
Map<K, V>
Box<T>
```

## 10. Generics solo con classi, non con primitivi

Questo è sbagliato:

```java
Box<int> box = new Box<>();
```

Perché i generics non lavorano con tipi primitivi.

Devi usare le wrapper class:

| Primitivo | Wrapper |
|---|---|
| int | Integer |
| double | Double |
| boolean | Boolean |
| char | Character |
| long | Long |
| float | Float |

Corretto:

```java
Box<Integer> box = new Box<>();
```

## 11. T con vincolo: extends

A volte vuoi dire:

T può essere qualsiasi tipo, ma deve essere figlio di una certa classe.

Esempio:

```java
class BoxNumero<T extends Number> {

    private T valore;

    public BoxNumero(T valore) {
        this.valore = valore;
    }

    public double doppio() {
        return valore.doubleValue() * 2;
    }
}
```

Uso:

```java
BoxNumero<Integer> b1 = new BoxNumero<>(10);
BoxNumero<Double> b2 = new BoxNumero<>(3.5);
```

Questo funziona perché Integer e Double estendono Number.

Questo invece no:

```java
BoxNumero<String> b3 = new BoxNumero<>("ciao");
```

Perché String non estende Number.

Questa riga:

```java
<T extends Number>
```

significa:

T deve essere Number o una sua sottoclasse.

## 12. Perché usare extends?

Perché così puoi usare i metodi della classe padre.

Nel caso di Number, puoi usare:

```java
doubleValue()
intValue()
longValue()
```

Esempio:

```java
public double doppio() {
    return valore.doubleValue() * 2;
}
```

Se scrivessi solo:

```java
class BoxNumero<T>
```

Java non saprebbe che T è un numero.

Quindi questo darebbe errore:

```java
valore.doubleValue()
```

Con:

```java
<T extends Number>
```

Java sa che T è almeno un Number.

## 13. T con interfacce

Anche con le interfacce si usa extends.

Esempio:

```java
interface Stampabile {
    void stampa();
}

class Stampatore<T extends Stampabile> {

    public void stampaOggetto(T oggetto) {
        oggetto.stampa();
    }
}
```

Anche se Stampabile è un’interfaccia, si scrive comunque:

```java
T extends Stampabile
```

non:

```java
T implements Stampabile
```

Nei generics si usa sempre extends.

## 14. T con più vincoli

Puoi dire che T deve rispettare più vincoli.

Esempio:

```java
class Gestore<T extends Number & Comparable<T>> {
}
```

Significa:

T deve essere un Number e deve anche essere Comparable.

Regola importante:

se c’è una classe, deve stare per prima.

Corretto:

```java
<T extends Number & Comparable<T>>
```

Sbagliato:

```java
<T extends Comparable<T> & Number>
```

## 15. Wildcard ?

Oltre a T, Java usa anche il simbolo:

```java
?
```

Significa:

tipo sconosciuto.

Esempio:

```java
public static void stampaLista(List<?> lista) {
    for (Object elemento : lista) {
        System.out.println(elemento);
    }
}
```

Questo metodo accetta:

```java
List<String>
List<Integer>
List<Double>
List<Persona>
```

Uso:

```java
stampaLista(List.of("A", "B"));
stampaLista(List.of(1, 2, 3));
```

? si usa quando non ti interessa sapere il tipo preciso.

## 16. Differenza tra T e ?

Questa è importante.

### Con T

Usi T quando vuoi mantenere un collegamento tra tipi.

Esempio:

```java
public static <T> T primoElemento(List<T> lista) {
    return lista.get(0);
}
```

Se passi una List<String>, torna una String.

Se passi una List<Integer>, torna un Integer.

### Con ?

Usi ? quando vuoi solo leggere genericamente.

```java
public static void stampaLista(List<?> lista) {
    for (Object elemento : lista) {
        System.out.println(elemento);
    }
}
```

Qui non ti interessa restituire lo stesso tipo.

## 17. Wildcard con extends

```java
List<? extends Number>
```

significa:

lista di un tipo sconosciuto che estende Number.

Accetta:

```java
List<Integer>
List<Double>
List<Float>
```

Esempio:

```java
public static double somma(List<? extends Number> numeri) {
    double totale = 0;

    for (Number n : numeri) {
        totale += n.doubleValue();
    }

    return totale;
}
```

Uso:

```java
List<Integer> interi = List.of(1, 2, 3);
List<Double> decimali = List.of(1.5, 2.5);

System.out.println(somma(interi));
System.out.println(somma(decimali));
```

## 18. Wildcard con super

```java
List<? super Integer>
```

significa:

lista di un tipo sconosciuto che è Integer oppure padre di Integer.

Accetta:

```java
List<Integer>
List<Number>
List<Object>
```

Serve soprattutto quando vuoi inserire elementi.

Esempio:

```java
public static void aggiungiNumeri(List<? super Integer> lista) {
    lista.add(10);
    lista.add(20);
}
```

## 19. Regola PECS

Regola importante:

```text
PECS = Producer Extends, Consumer Super
```

Cioè:

### Se leggi dati da una lista

Usa:

```java
? extends Tipo
```

Esempio:

```java
List<? extends Number>
```

Perché la lista produce numeri per te.

### Se scrivi dati dentro una lista

Usa:

```java
? super Tipo
```

Esempio:

```java
List<? super Integer>
```

Perché la lista consuma/interiorizza Integer.

## 20. Esempio pratico con extends e super

```java
import java.util.*;

public class Main {

    public static double somma(List<? extends Number> lista) {
        double totale = 0;

        for (Number n : lista) {
            totale += n.doubleValue();
        }

        return totale;
    }

    public static void aggiungi(List<? super Integer> lista) {
        lista.add(10);
        lista.add(20);
    }

    public static void main(String[] args) {

        List<Integer> interi = new ArrayList<>();
        interi.add(1);
        interi.add(2);

        System.out.println(somma(interi));

        List<Number> numeri = new ArrayList<>();
        aggiungi(numeri);

        System.out.println(numeri);
    }
}
```

## 21. Erasure: cosa succede davvero a runtime

Java usa i generics soprattutto a compile-time.

Significa:

Java controlla i tipi mentre compili, ma a runtime molte informazioni generiche vengono cancellate.

Questo meccanismo si chiama:

```text
type erasure
```

Esempio:

```java
List<String> lista1 = new ArrayList<>();
List<Integer> lista2 = new ArrayList<>();
```

A runtime entrambe sono semplicemente:

```java
ArrayList
```

Quindi non puoi fare:

```java
if (lista1 instanceof List<String>) {
}
```

Questo non è permesso.

## 22. Non puoi creare array generici direttamente

Questo è sbagliato:

```java
T[] array = new T[10];
```

Java non lo permette direttamente per via della type erasure.

Spesso si usa una lista:

```java
List<T> lista = new ArrayList<>();
```

oppure soluzioni più avanzate.

## 23. Esempio completo: Repository generico

Questo è un esempio molto realistico.

```java
import java.util.*;

class Repository<T> {

    private List<T> elementi = new ArrayList<>();

    public void aggiungi(T elemento) {
        elementi.add(elemento);
    }

    public T trovaPrimo() {
        if (elementi.isEmpty()) {
            return null;
        }

        return elementi.get(0);
    }

    public List<T> trovaTutti() {
        return elementi;
    }
}
```

Uso con String:

```java
Repository<String> repoNomi = new Repository<>();

repoNomi.aggiungi("Luca");
repoNomi.aggiungi("Marco");

String primoNome = repoNomi.trovaPrimo();

System.out.println(primoNome);
```

Uso con Persona:

```java
class Persona {

    private String nome;

    public Persona(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}

Repository<Persona> repoPersone = new Repository<>();

repoPersone.aggiungi(new Persona("Anna"));

Persona primaPersona = repoPersone.trovaPrimo();

System.out.println(primaPersona.getNome());
```

## 24. Esempio con interfaccia generica

```java
interface Converter<T, R> {
    R converti(T input);
}
```

Qui:

T

è il tipo in ingresso.

R

è il tipo in uscita.

Implementazione:

```java
class StringToIntegerConverter implements Converter<String, Integer> {

    public Integer converti(String input) {
        return Integer.parseInt(input);
    }
}
```

Uso:

```java
Converter<String, Integer> converter = new StringToIntegerConverter();

Integer numero = converter.converti("123");

System.out.println(numero);
```

## 25. Riassunto secco

T è un parametro generico.

Serve a scrivere codice riutilizzabile per tipi diversi.

Esempio:

```java
class Box<T> {
    private T valore;
}
```

Uso:

```java
Box<String> box1 = new Box<>();
Box<Integer> box2 = new Box<>();
```

T viene deciso quando usi la classe.

I generics evitano cast pericolosi.

Permettono controlli di tipo a compile-time.

Puoi usare più generici:

```java
<K, V>
```

come nelle mappe.

Puoi mettere vincoli:

```java
<T extends Number>
```

Puoi usare wildcard:

```java
?
? extends Number
? super Integer
```

La regola pratica è:

```text
T → quando vuoi lavorare con un tipo preciso ma generico
? → quando accetti un tipo sconosciuto
extends → quando devi leggere da un tipo più specifico
super → quando devi inserire dentro una struttura
```

In parole semplici:

```java
T
```

significa:

non so ancora che tipo sarà, ma quando lo userai Java lo controllerà per te.
