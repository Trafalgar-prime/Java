# Metodo format() in Java (usato con i Wrapper)

Il **`format()`** non è un metodo tipico dei wrapper come `parseInt()` o
`valueOf()`.\
In realtà appartiene principalmente a:

-   `String.format()`
-   `System.out.printf()`
-   `Formatter`

Ma viene usato **continuamente insieme ai wrapper** perché serve a
**formattare numeri (`Integer`, `Double`, ecc.) in stringhe leggibili**.

Quindi è molto importante quando lavori con **numeri wrapper**.

------------------------------------------------------------------------

# Metodo `format()`

## Cosa fa

`format()` serve a **creare stringhe formattate**, cioè stringhe dove
puoi inserire valori dentro un modello (template).

Funziona in modo molto simile a **printf in C**.

Permette di:

-   controllare **numero di decimali**
-   controllare **spazi e allineamenti**
-   formattare **numeri, stringhe, date**
-   creare **output leggibili**

------------------------------------------------------------------------

# Sintassi

``` java
String risultato = String.format("formato", valori);
```

Esempio:

``` java
int numero = 10;

String s = String.format("Il numero è %d", numero);

System.out.println(s);
```

Output:

    Il numero è 10

------------------------------------------------------------------------

# I principali simboli di format

  simbolo   tipo
  --------- -----------------
  `%d`      intero
  `%f`      numero decimale
  `%s`      stringa
  `%c`      carattere
  `%b`      boolean
  `%x`      esadecimale

------------------------------------------------------------------------

# Esempio con wrapper `Integer`

``` java
Integer numero = 25;

String risultato = String.format("Numero: %d", numero);

System.out.println(risultato);
```

Output

    Numero: 25

------------------------------------------------------------------------

# Esempio con `Double`

``` java
Double prezzo = 12.5678;

String s = String.format("Prezzo: %.2f", prezzo);

System.out.println(s);
```

Output

    Prezzo: 12.57

### Spiegazione

    %.2f

significa:

-   `%` → formato
-   `.2` → 2 cifre decimali
-   `f` → floating point

------------------------------------------------------------------------

# Controllo della larghezza

Puoi decidere **quanti spazi deve occupare il numero**.

``` java
int n = 5;

String s = String.format("Numero: %5d", n);

System.out.println(s);
```

Output

    Numero:     5

(4 spazi + 5)

------------------------------------------------------------------------

# Allineamento a sinistra

``` java
String s = String.format("%-5d", 5);
```

Output

    5

il numero resta a sinistra.

------------------------------------------------------------------------

# Riempimento con zeri

``` java
String s = String.format("%05d", 42);

System.out.println(s);
```

Output

    00042

Molto usato per:

-   codici
-   ID
-   numeri di fattura

------------------------------------------------------------------------

# Esempio con più variabili

``` java
String nome = "Luca";
int eta = 25;
double altezza = 1.82;

String s = String.format(
        "Nome: %s | Età: %d | Altezza: %.2f",
        nome,
        eta,
        altezza
);

System.out.println(s);
```

Output

    Nome: Luca | Età: 25 | Altezza: 1.82

------------------------------------------------------------------------

# `System.out.printf()`

È identico a `format()`, ma stampa direttamente.

``` java
System.out.printf("Numero: %d", 10);
```

equivalente a

``` java
System.out.println(String.format("Numero: %d", 10));
```

------------------------------------------------------------------------

# Esempio completo con wrapper

``` java
public class Main {

    public static void main(String[] args) {

        Integer quantità = 7;
        Double prezzo = 12.3456;

        String output = String.format(
                "Quantità: %d | Prezzo: %.2f €",
                quantità,
                prezzo
        );

        System.out.println(output);
    }
}
```

Output

    Quantità: 7 | Prezzo: 12.35 €

------------------------------------------------------------------------

# Riassunto

`format()` serve per:

-   creare **stringhe formattate**
-   controllare **decimali**
-   controllare **spazi**
-   controllare **allineamento**
-   formattare **numeri wrapper**

Molto usato con:

    Integer
    Double
    Float
    Long
