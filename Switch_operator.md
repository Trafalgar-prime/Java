# Switch in Java - Guida Completa

Questa guida spiega in modo completo la `switch` in Java: - Switch
classica con `:` - Switch moderna con `->` - Switch expression - Uso di
`default` - Uso di `yield` - Tutti i modelli possibili

------------------------------------------------------------------------

## 1) Switch classica (statement) con `:`

### Caso base con break

``` java
int day = 3;

switch (day) {
    case 1:
        System.out.println("Lunedì");
        break;
    case 2:
        System.out.println("Martedì");
        break;
    case 3:
        System.out.println("Mercoledì");
        break;
    default:
        System.out.println("Giorno non valido");
}
```

Punti chiave: - `case X:` apre un ramo - senza `break`, Java continua
nei case successivi (fall-through)

------------------------------------------------------------------------

### Fall-through (senza break)

``` java
int x = 2;

switch (x) {
    case 1:
        System.out.println("Uno");
    case 2:
        System.out.println("Due");
    case 3:
        System.out.println("Tre");
}
```

Output: - stampa "Due" - poi continua e stampa "Tre"

Motivo: non c'è `break`.

------------------------------------------------------------------------

### Fall-through usato correttamente

``` java
int month = 4;

switch (month) {
    case 12:
    case 1:
    case 2:
        System.out.println("Inverno");
        break;
    case 3:
    case 4:
    case 5:
        System.out.println("Primavera");
        break;
    default:
        System.out.println("Mese non valido");
}
```

Più valori portano allo stesso blocco.

------------------------------------------------------------------------

## 2) Switch moderna con `->` (statement)

Nessun fall-through.

``` java
int day = 3;

switch (day) {
    case 1 -> System.out.println("Lunedì");
    case 2 -> System.out.println("Martedì");
    case 3 -> System.out.println("Mercoledì");
    default -> System.out.println("Giorno non valido");
}
```

------------------------------------------------------------------------

### Più valori nello stesso case

``` java
int month = 2;

switch (month) {
    case 12, 1, 2 -> System.out.println("Inverno");
    case 3, 4, 5 -> System.out.println("Primavera");
    case 6, 7, 8 -> System.out.println("Estate");
    case 9, 10, 11 -> System.out.println("Autunno");
    default -> System.out.println("Mese non valido");
}
```

------------------------------------------------------------------------

### Blocco con più istruzioni

``` java
int n = 1;

switch (n) {
    case 1 -> {
        System.out.println("Caso 1");
        System.out.println("Altra riga");
    }
    case 2 -> System.out.println("Caso 2");
    default -> System.out.println("Altro");
}
```

------------------------------------------------------------------------

## 3) Switch expression (produce un valore)

``` java
int day = 2;

String name = switch (day) {
    case 1 -> "Lunedì";
    case 2 -> "Martedì";
    case 3 -> "Mercoledì";
    default -> "Non valido";
};

System.out.println(name);
```

Qui `default` è necessario se non copri tutti i casi.

------------------------------------------------------------------------

### Switch expression con yield

``` java
int score = 87;

String grade = switch (score / 10) {
    case 10, 9 -> "A";
    case 8 -> {
        System.out.println("Buon punteggio!");
        yield "B";
    }
    case 7 -> "C";
    case 6 -> "D";
    default -> "F";
};

System.out.println(grade);
```

Regola: - `yield` restituisce un valore alla switch expression - si usa
solo dentro una switch expression - serve quando usi un blocco `{}`

------------------------------------------------------------------------

### Differenza tra yield e return

-   `yield` restituisce valore alla switch
-   `return` esce dal metodo

------------------------------------------------------------------------

## 4) Uso di default

### Switch statement

Non è obbligatorio.

``` java
int x = 99;

switch (x) {
    case 1 -> System.out.println("Uno");
    case 2 -> System.out.println("Due");
}
```

Se nessun case corrisponde, non succede nulla.

------------------------------------------------------------------------

### Switch expression

Deve sempre produrre un valore.

Con enum completo puoi ometterlo:

``` java
enum Day { MON, TUE, WED }

Day d = Day.MON;

String s = switch (d) {
    case MON -> "Lunedì";
    case TUE -> "Martedì";
    case WED -> "Mercoledì";
};
```

------------------------------------------------------------------------

### Default con eccezione (buona pratica)

``` java
String s = switch (d) {
    case MON -> "Lunedì";
    case TUE -> "Martedì";
    case WED -> "Mercoledì";
    default -> throw new IllegalStateException("Valore inatteso: " + d);
};
```

------------------------------------------------------------------------

## 5) Tipi supportati

La switch può usare: - byte - short - char - int - wrapper (Integer,
Short, ecc.) - String - enum

Esempio con String:

``` java
String cmd = "start";

switch (cmd) {
    case "start" -> System.out.println("Avvio");
    case "stop" -> System.out.println("Stop");
    default -> System.out.println("Comando sconosciuto");
}
```

------------------------------------------------------------------------

## 6) Template riassuntivi

### Classico con break

``` java
switch (x) {
    case A:
        ...
        break;
    default:
        ...
}
```

### Classico con fall-through

``` java
switch (x) {
    case A:
    case B:
        ...
        break;
}
```

### Moderno -\>

``` java
switch (x) {
    case A -> ...
    default -> ...
}
```

### Switch expression

``` java
T out = switch (x) {
    case A -> valueA;
    default -> valueDefault;
};
```

### Switch expression con yield

``` java
T out = switch (x) {
    case A -> {
        ...
        yield valueA;
    }
    default -> valueDefault;
};
```

------------------------------------------------------------------------

# Fine Guida
