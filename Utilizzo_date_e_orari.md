# Utilizzo date e orari in Java

In Java la gestione delle date è un argomento enorme, perché esistono:

- classi vecchie (`Date`, `Calendar`)
- classi moderne (`LocalDate`, `LocalTime`, `LocalDateTime`)
- fusi orari
- parsing
- formattazione
- confronti
- operazioni matematiche sulle date

Oggi in Java si usa quasi sempre il package:

```java
java.time
```

introdotto da Java 8.

È molto migliore rispetto alle vecchie classi.

---

## 1. Le classi principali delle date in Java

Le più importanti sono:

| Classe | Serve per |
|---|---|
| `LocalDate` | solo data |
| `LocalTime` | solo orario |
| `LocalDateTime` | data + orario |
| `ZonedDateTime` | data + orario + fuso |
| `Period` | differenza tra date |
| `Duration` | differenza tra orari |
| `DateTimeFormatter` | formattare date |

Package:

```java
import java.time.*;
```

---

## 2. LocalDate → solo data

Rappresenta:

- giorno
- mese
- anno

senza orario.

Esempio:

```java
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        LocalDate oggi = LocalDate.now();

        System.out.println(oggi);
    }
}
```

Output esempio:

```text
2026-05-13
```

---

## 3. Creare una data manualmente

```java
LocalDate data = LocalDate.of(2025, 12, 25);

System.out.println(data);
```

Output:

```text
2025-12-25
```

---

## 4. Prendere giorno, mese e anno

```java
LocalDate oggi = LocalDate.now();

System.out.println(oggi.getDayOfMonth());
System.out.println(oggi.getMonth());
System.out.println(oggi.getYear());
```

Esempio output:

```text
13
MAY
2026
```

---

## 5. LocalTime → solo orario

Gestisce:

```text
ora:minuti:secondi
```

Esempio:

```java
import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {

        LocalTime ora = LocalTime.now();

        System.out.println(ora);
    }
}
```

Output:

```text
15:32:10.123
```

---

## 6. Creare un orario manualmente

```java
LocalTime ora = LocalTime.of(14, 30);

System.out.println(ora);
```

Output:

```text
14:30
```

---

## 7. LocalDateTime → data + orario

È la classe più usata.

Esempio:

```java
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        LocalDateTime adesso = LocalDateTime.now();

        System.out.println(adesso);
    }
}
```

Output:

```text
2026-05-13T15:35:20.123
```

---

## 8. Creare data e ora manualmente

```java
LocalDateTime dataOra = LocalDateTime.of(2025, 12, 25, 14, 30);

System.out.println(dataOra);
```

Output:

```text
2025-12-25T14:30
```

---

## 9. Modificare date

Le classi `java.time` sono **IMMUTABILI**.

Significa:

non modificano l’oggetto originale.

Esempio:

```java
LocalDate oggi = LocalDate.now();

LocalDate domani = oggi.plusDays(1);

System.out.println(oggi);
System.out.println(domani);
```

---

## 10. Operazioni sulle date

### Aggiungere giorni

`plusDays()`

Esempio:

```java
LocalDate oggi = LocalDate.now();

LocalDate futuro = oggi.plusDays(10);

System.out.println(futuro);
```

### Togliere giorni

`minusDays()`

```java
LocalDate passato = oggi.minusDays(5);
```

### Aggiungere mesi

```java
plusMonths(2)
```

### Aggiungere anni

```java
plusYears(1)
```

---

## 11. Confrontare date

### isBefore()

```java
LocalDate d1 = LocalDate.of(2025, 1, 1);
LocalDate d2 = LocalDate.of(2026, 1, 1);

System.out.println(d1.isBefore(d2));
```

Output:

```text
true
```

### isAfter()

```java
System.out.println(d2.isAfter(d1));
```

### equals()

```java
System.out.println(d1.equals(d2));
```

---

## 12. Differenza tra date

### Period

Serve per date.

```java
import java.time.LocalDate;
import java.time.Period;

public class Main {

    public static void main(String[] args) {

        LocalDate nascita = LocalDate.of(2000, 5, 10);
        LocalDate oggi = LocalDate.now();

        Period differenza = Period.between(nascita, oggi);

        System.out.println(differenza.getYears());
    }
}
```

Output:

```text
26
```

---

## 13. Duration

Serve per orari.

```java
import java.time.Duration;
import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {

        LocalTime inizio = LocalTime.of(10, 0);
        LocalTime fine = LocalTime.of(12, 30);

        Duration durata = Duration.between(inizio, fine);

        System.out.println(durata.toMinutes());
    }
}
```

Output:

```text
150
```

---

## 14. Formattare le date

Per cambiare il formato si usa:

```java
DateTimeFormatter
```

Esempio:

```java
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {

        LocalDate oggi = LocalDate.now();

        DateTimeFormatter formato =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String dataFormattata = oggi.format(formato);

        System.out.println(dataFormattata);
    }
}
```

Output:

```text
13/05/2026
```

---

## 15. Simboli del formatter

| Simbolo | Significato |
|---|---|
| `dd` | giorno |
| `MM` | mese |
| `yyyy` | anno |
| `HH` | ore |
| `mm` | minuti |
| `ss` | secondi |

Esempio:

```java
DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
```

Output:

```text
13-05-2026 15:50:10
```

---

## 16. Convertire stringa → data

Si usa:

```java
parse()
```

Esempio:

```java
LocalDate data =
        LocalDate.parse("2025-12-25");

System.out.println(data);
```

---

## 17. Parsing personalizzato

```java
DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("dd/MM/yyyy");

LocalDate data =
        LocalDate.parse("25/12/2025", formatter);

System.out.println(data);
```

---

## 18. Giorno della settimana

```java
LocalDate oggi = LocalDate.now();

System.out.println(oggi.getDayOfWeek());
```

Output:

```text
WEDNESDAY
```

---

## 19. Numero di giorni del mese

```java
LocalDate oggi = LocalDate.now();

System.out.println(oggi.lengthOfMonth());
```

Esempio:

```text
31
```

---

## 20. Verificare anno bisestile

```java
LocalDate oggi = LocalDate.now();

System.out.println(oggi.isLeapYear());
```

---

## 21. ZonedDateTime → fusi orari

Serve per lavorare con i time zone.

```java
import java.time.ZonedDateTime;
import java.time.ZoneId;

public class Main {

    public static void main(String[] args) {

        ZonedDateTime roma =
                ZonedDateTime.now(ZoneId.of("Europe/Rome"));

        ZonedDateTime tokyo =
                ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));

        System.out.println(roma);
        System.out.println(tokyo);
    }
}
```

---

## 22. Vecchie classi Date e Calendar

Prima di Java 8 si usavano:

```java
Date
Calendar
SimpleDateFormat
```

Esempio vecchio stile:

```java
Date data = new Date();

System.out.println(data);
```

Oggi si preferisce:

```java
LocalDateTime
```

perché è molto più pulito e sicuro.

---

## 23. Esempio pratico completo

```java
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {

        LocalDate nascita =
                LocalDate.of(2000, 5, 10);

        LocalDate oggi =
                LocalDate.now();

        Period eta =
                Period.between(nascita, oggi);

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Data nascita: "
                + nascita.format(formatter));

        System.out.println("Oggi: "
                + oggi.format(formatter));

        System.out.println("Età: "
                + eta.getYears());
    }
}
```

---

## 24. Schema mentale definitivo

| Caso | Classe da usare |
|---|---|
| Solo data | `LocalDate` |
| Solo ora | `LocalTime` |
| Data + ora | `LocalDateTime` |
| Fuso orario | `ZonedDateTime` |
| Differenza tra date | `Period` |
| Differenza tra orari | `Duration` |
| Formattare date | `DateTimeFormatter` |

---

## 25. Le funzioni più importanti da ricordare

| Metodo | Serve per |
|---|---|
| `now()` | data/ora corrente |
| `of()` | creare data manuale |
| `parse()` | convertire stringa |
| `plusDays()` | aggiungere giorni |
| `minusDays()` | togliere giorni |
| `isBefore()` | confronto |
| `isAfter()` | confronto |
| `equals()` | uguaglianza |
| `format()` | formattazione |

---

## 26. Consiglio importante

Nel Java moderno usa **SEMPRE**:

```java
java.time
```

Evita quando possibile:

```java
Date
Calendar
```

perché le API vecchie sono più complicate, meno sicure e più confusionarie.
