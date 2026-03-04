# Wrapper Classes e Metodi in Java

## Spiegazione dei Wrapper

In **Java**, i **wrapper classes** sono classi che "avvolgono" i **tipi
primitivi** in oggetti.\
Servono perché molte parti di Java lavorano **solo con oggetti**, non
con tipi primitivi (per esempio **Collections, Generics, Streams**).

Esempio:\
- `int` è un tipo primitivo\
- `Integer` è il **wrapper** di `int`

Questo permette di trattare un numero come un **oggetto con metodi**.

------------------------------------------------------------------------

# Lista completa dei Wrapper di Java

  Tipo primitivo   Wrapper class
  ---------------- ---------------
  `byte`           `Byte`
  `short`          `Short`
  `int`            `Integer`
  `long`           `Long`
  `float`          `Float`
  `double`         `Double`
  `char`           `Character`
  `boolean`        `Boolean`

Questi wrapper si trovano nel package:

``` java
java.lang
```

quindi **non serve import**.

------------------------------------------------------------------------

# Esempio semplice

``` java
int x = 10;          // tipo primitivo
Integer y = 10;      // wrapper
```

oppure

``` java
Integer y = Integer.valueOf(10);
```

------------------------------------------------------------------------

# Perché esistono i wrapper?

I tipi primitivi:

    int, double, boolean, char...

non sono oggetti.

Ma molte strutture di Java **richiedono oggetti**.

Esempio con **ArrayList**:

❌ NON funziona

``` java
ArrayList<int> lista = new ArrayList<>();
```

✔ Funziona

``` java
ArrayList<Integer> lista = new ArrayList<>();
```

------------------------------------------------------------------------

# Autoboxing e Unboxing

Java converte automaticamente tra **primitivi e wrapper**.

### Autoboxing

primitivo → oggetto

``` java
int x = 5;
Integer y = x;
```

Java fa automaticamente:

    Integer y = Integer.valueOf(x);

------------------------------------------------------------------------

### Unboxing

oggetto → primitivo

``` java
Integer y = 5;
int x = y;
```

Java fa:

    int x = y.intValue();

------------------------------------------------------------------------

# Metodi utili dei wrapper

I wrapper hanno **molti metodi statici utili**.

### Convertire String → numero

``` java
int x = Integer.parseInt("123");
double y = Double.parseDouble("10.5");
```

------------------------------------------------------------------------

### Convertire numero → String

``` java
String s = Integer.toString(10);
```

------------------------------------------------------------------------

### Confrontare numeri

``` java
Integer a = 10;
Integer b = 20;

System.out.println(a.compareTo(b));
```

------------------------------------------------------------------------

### Costanti utili

``` java
System.out.println(Integer.MAX_VALUE);
System.out.println(Integer.MIN_VALUE);
```

------------------------------------------------------------------------

# Esempio completo

``` java
import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<Integer> numeri = new ArrayList<>();

        numeri.add(10);
        numeri.add(20);
        numeri.add(30);

        for(Integer n : numeri){
            System.out.println(n);
        }

        int x = Integer.parseInt("50");
        System.out.println(x);
    }
}
```

------------------------------------------------------------------------

# Metodi dei Wrapper (spiegati)

## 1) valueOf(...)

**Fa:** converte un valore primitivo o una stringa in un oggetto
wrapper.\
Spesso usa una **cache interna** per evitare di creare nuovi oggetti
quando non serve.

``` java
Integer a = Integer.valueOf(10);
Double d = Double.valueOf("3.14");
Boolean b = Boolean.valueOf("true");
```

------------------------------------------------------------------------

## 2) parseXxx(String)

**Fa:** legge una stringa e restituisce il **valore primitivo**
corrispondente.\
È molto usato quando si leggono dati da file, input utente o API.

``` java
int x = Integer.parseInt("123");
long l = Long.parseLong("999999");
double y = Double.parseDouble("10.5");
```

------------------------------------------------------------------------

## 3) xxxValue()

**Fa:** prende l'oggetto wrapper e restituisce il **valore primitivo
contenuto**.

``` java
Integer n = 42;
int a = n.intValue();

Double z = 2.5;
float f = z.floatValue();
```

------------------------------------------------------------------------

## 4) toString()

**Fa:** converte il valore dell'oggetto wrapper in una stringa.

``` java
Integer n = 7;
String s = n.toString();
```

------------------------------------------------------------------------

## 5) toString(primitive)

**Fa:** metodo statico che converte direttamente un valore primitivo in
stringa.

``` java
String s1 = Integer.toString(99);
String s2 = Double.toString(3.14);
```

------------------------------------------------------------------------

## 6) equals(Object)

**Fa:** confronta **il contenuto** di due oggetti wrapper e non il
riferimento in memoria.

``` java
Integer a = 1000;
Integer b = 1000;

System.out.println(a.equals(b)); // true
System.out.println(a == b);      // false
```

------------------------------------------------------------------------

## 7) compareTo(T)

**Fa:** confronta due oggetti wrapper e restituisce:

-   numero negativo se minore
-   0 se uguali
-   numero positivo se maggiore

``` java
Integer a = 10, b = 20;
System.out.println(a.compareTo(b));
```

------------------------------------------------------------------------

## 8) compare(x,y)

**Fa:** confronto statico tra due valori primitivi senza creare oggetti.

``` java
System.out.println(Integer.compare(5, 2));
System.out.println(Double.compare(2.0, 2.0));
```

------------------------------------------------------------------------

## 9) hashCode()

**Fa:** restituisce un numero hash che rappresenta il valore
dell'oggetto.\
Viene usato da strutture come **HashMap** e **HashSet**.

``` java
Integer x = 42;
System.out.println(x.hashCode());
```

------------------------------------------------------------------------

## 10) hashCode(primitive)

**Fa:** genera direttamente l'hash di un valore primitivo.

``` java
int h = Integer.hashCode(42);
int hd = Double.hashCode(3.14);
```

------------------------------------------------------------------------

## 11) min(a,b)

**Fa:** restituisce il valore minimo tra due numeri.

``` java
int m = Integer.min(10, 3);
double md = Double.min(2.5, 9.0);
```

------------------------------------------------------------------------

## 12) max(a,b)

**Fa:** restituisce il valore massimo tra due numeri.

``` java
int M = Integer.max(10, 3);
```

------------------------------------------------------------------------

## 13) sum(a,b)

**Fa:** calcola la somma tra due valori numerici.

``` java
int s = Integer.sum(10, 5);
long sl = Long.sum(7L, 2L);
```

------------------------------------------------------------------------

## 14) decode(String)

**Fa:** converte una stringa in numero riconoscendo automaticamente la
base numerica (decimale, esadecimale, ottale).

``` java
Integer a = Integer.decode("10");
Integer b = Integer.decode("0x10");
Integer c = Integer.decode("010");
```

------------------------------------------------------------------------

## 15) toHexString / toOctalString / toBinaryString

**Fa:** converte un numero intero in una rappresentazione testuale in
base 16, 8 o 2.

``` java
System.out.println(Integer.toHexString(255));
System.out.println(Integer.toBinaryString(10));
System.out.println(Integer.toOctalString(9));
```

------------------------------------------------------------------------

## 16) getInteger(String key)

**Fa:** legge una **System Property** e la converte in Integer.

``` java
System.setProperty("threads", "8");
Integer t = Integer.getInteger("threads");
```

------------------------------------------------------------------------

## 17) isNaN()

**Fa:** controlla se un valore floating point è **NaN (Not a Number)**.

``` java
Double x = 0.0 / 0.0;
System.out.println(x.isNaN());
```

------------------------------------------------------------------------

## 18) isInfinite()

**Fa:** controlla se un numero floating point è infinito.

``` java
Double x = 1.0 / 0.0;
System.out.println(x.isInfinite());
```

------------------------------------------------------------------------

## 19) isFinite(double)

**Fa:** controlla se un numero è finito (non infinito e non NaN).

``` java
System.out.println(Double.isFinite(10.0));
System.out.println(Double.isFinite(1.0/0.0));
```

------------------------------------------------------------------------

## 20) Character.isDigit()

**Fa:** verifica se un carattere rappresenta una cifra numerica.

``` java
System.out.println(Character.isDigit('7'));
System.out.println(Character.isDigit('a'));
```

------------------------------------------------------------------------

## 21) Character.isLetter()

**Fa:** verifica se un carattere è una lettera.

``` java
System.out.println(Character.isLetter('A'));
System.out.println(Character.isLetter('9'));
```

------------------------------------------------------------------------

## 22) Character.isWhitespace()

**Fa:** verifica se un carattere è uno spazio, tab o newline.

``` java
System.out.println(Character.isWhitespace(' '));
System.out.println(Character.isWhitespace('\n'));
```

------------------------------------------------------------------------

## 23) Character.toUpperCase / toLowerCase

**Fa:** converte un carattere rispettivamente in maiuscolo o minuscolo.

``` java
System.out.println(Character.toUpperCase('a'));
System.out.println(Character.toLowerCase('Z'));
```

------------------------------------------------------------------------

## 24) Character.forDigit()

**Fa:** converte un numero in un carattere nella base specificata.

``` java
System.out.println(Character.forDigit(10, 16));
System.out.println(Character.forDigit(15, 16));
```

------------------------------------------------------------------------

## 25) Boolean.compare

**Fa:** confronta due valori booleani.

``` java
System.out.println(Boolean.compare(false, true));
```

------------------------------------------------------------------------

## 26) Boolean.logicalAnd / logicalOr / logicalXor

**Fa:** esegue operazioni logiche tra due boolean.

``` java
System.out.println(Boolean.logicalAnd(true, false));
System.out.println(Boolean.logicalOr(true, false));
System.out.println(Boolean.logicalXor(true, true));
```

------------------------------------------------------------------------

# Esempio completo finale

\`\`\`java import java.util.\*;

public class Main { public static void main(String\[\] args) {

        int x = Integer.parseInt("10");
        Integer y = Integer.valueOf("20");

        int m = Integer.min(x, y);
        int M = Integer.max(x, y);
        int s = Integer.sum(x, y);

        System.out.println("min=" + m + " max=" + M + " sum=" + s);

        System.out.println("hex(sum)=" + Integer.toHexString(s));
        System.out.println("bin(sum)=" + Integer.toBinaryString(s));

        Integer a = 1000, b = 1000;
        System.out.println("equals: " + a.equals(b));
        System.out.println("==     : " + (a == b));

        char c = '7';
        System.out.println("isDigit? " + Character.isDigit(c));
        System.out.println("upper(a)=" + Character.toUpperCase('a'));

        Double nan = 0.0 / 0.0;
        Double inf = 1.0 / 0.0;
        System.out.println("nan isNaN=" + nan.isNaN());
        System.out.println("inf isInfinite=" + inf.isInfinite());
    }

}
