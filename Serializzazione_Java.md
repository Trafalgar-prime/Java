# Serializzazione in Java

La **serializzazione** in Java è il processo con cui **un oggetto viene trasformato in una sequenza di byte**, in modo che possa essere:

- salvato su **file**
- inviato tramite **rete**
- memorizzato in **database**
- trasferito tra **processi**

In parole semplici:

> **Serializzare = trasformare un oggetto Java in byte per poterlo salvare o trasmettere.**

---

# 1. Concetto base

Immagina di avere un oggetto:

```java
Persona p = new Persona("Marco", 30);
```

Questo oggetto esiste **solo nella memoria della JVM**.

Se il programma termina:

```text
memoria cancellata → oggetto perso
```

La **serializzazione** permette di fare:

```text
Oggetto Java → sequenza di byte → file
```

Schema:

```text
Oggetto Java
     ↓
Serializzazione
     ↓
Byte
     ↓
File / rete / database
```

---

# 2. L’operazione inversa: deserializzazione

Il processo opposto è la **deserializzazione**.

```text
byte → oggetto Java
```

Schema:

```text
File / rete
     ↓
Byte
     ↓
Deserializzazione
     ↓
Oggetto Java
```

---

# 3. Come funziona in Java

Java usa queste classi:

| Classe | Funzione |
|------|------|
| `ObjectOutputStream` | serializza oggetti |
| `ObjectInputStream` | deserializza oggetti |

---

# 4. La classe deve implementare Serializable

Per serializzare un oggetto, la classe deve implementare:

```java
Serializable
```

Esempio:

```java
import java.io.Serializable;

public class Persona implements Serializable {

    String nome;
    int eta;

    public Persona(String nome, int eta){
        this.nome = nome;
        this.eta = eta;
    }
}
```

`Serializable` è una **marker interface**.

Significa:

```text
non ha metodi
serve solo a dire alla JVM che l'oggetto è serializzabile
```

---

# 5. Esempio di serializzazione

Scriviamo un oggetto su file.

```java
import java.io.*;

class Persona implements Serializable {

    String nome;
    int eta;

    Persona(String nome, int eta){
        this.nome = nome;
        this.eta = eta;
    }
}

public class Main {

    public static void main(String[] args) throws Exception {

        Persona p = new Persona("Marco", 30);

        ObjectOutputStream oos =
            new ObjectOutputStream(
                new FileOutputStream("persona.dat"));

        oos.writeObject(p);

        oos.close();
    }
}
```

Cosa succede:

```text
oggetto Persona
     ↓
convertito in byte
     ↓
salvato nel file persona.dat
```

---

# 6. Esempio di deserializzazione

Leggiamo l’oggetto dal file.

```java
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {

        ObjectInputStream ois =
            new ObjectInputStream(
                new FileInputStream("persona.dat"));

        Persona p = (Persona) ois.readObject();

        System.out.println(p.nome);

        ois.close();
    }
}
```

Output:

```text
Marco
```

---

# 7. Cosa viene serializzato

Durante la serializzazione vengono salvati:

- i **campi dell’oggetto**
- lo **stato dell’oggetto**

Esempio:

```java
class Persona implements Serializable {

    String nome;
    int eta;
}
```

Vengono salvati:

```text
nome
eta
```

---

# 8. Campi che NON vengono serializzati

Non vengono serializzati:

### 1️⃣ campi `static`

```java
static int contatore;
```

perché appartengono **alla classe**, non all’oggetto.

---

### 2️⃣ campi `transient`

```java
transient String password;
```

Significa:

```text
non serializzare questo campo
```

Esempio:

```java
class User implements Serializable {

    String username;

    transient String password;
}
```

La password **non verrà salvata**.

---

# 9. serialVersionUID

Ogni classe serializzabile dovrebbe avere:

```java
private static final long serialVersionUID = 1L;
```

Serve per verificare la **compatibilità tra versioni della classe**.

Esempio:

```java
class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    String nome;
}
```

Se la classe cambia struttura, Java può generare errori durante la deserializzazione.

---

# 10. Perché si usa la serializzazione

È usata molto in:

### 1️⃣ salvataggio oggetti

```text
file .dat
file .ser
```

---

### 2️⃣ comunicazione rete

Client → Server

---

### 3️⃣ RMI (Remote Method Invocation)

---

### 4️⃣ caching

---

### 5️⃣ sessioni web

Molti server salvano oggetti serializzati.

---

# 11. Esempio reale

Supponiamo di salvare utenti registrati.

```text
Utente
nome
email
password
```

Serializzi l’oggetto:

```text
Utente → byte → file utenti.dat
```

Quando il programma riparte:

```text
file utenti.dat → oggetti Utente
```

---

# 12. Schema completo

```text
SERIALIZZAZIONE

Oggetto Java
     ↓
ObjectOutputStream
     ↓
byte
     ↓
file / rete
```

```text
DESERIALIZZAZIONE

file / rete
     ↓
byte
     ↓
ObjectInputStream
     ↓
oggetto Java
```

---

# 13. Riassunto super sintetico

**Serializzazione**

```text
Oggetto → byte
```

**Deserializzazione**

```text
byte → oggetto
```

Classi principali:

```text
Serializable
ObjectOutputStream
ObjectInputStream
```

---

💡 Ti dico anche una cosa importante per gli esami Java:

Ci sono **3 domande classiche che i professori fanno sempre sulla serializzazione**:

1️⃣ perché serve `Serializable`  
2️⃣ cosa fa `transient`  
3️⃣ cos’è `serialVersionUID`

---

# 14. Esempio completo di serializzazione e deserializzazione

Qui sotto trovi un esempio completo con:

- classe `Persona` serializzabile
- salvataggio dell’oggetto su file
- lettura dello stesso oggetto dal file
- stampa dei dati deserializzati

```java
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    String nome;
    int eta;
    transient String password;

    public Persona(String nome, int eta, String password) {
        this.nome = nome;
        this.eta = eta;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Persona{nome='" + nome + "', eta=" + eta + ", password='" + password + "'}";
    }
}

public class Main {

    public static void main(String[] args) {

        Persona p1 = new Persona("Marco", 30, "segreta123");

        // SERIALIZZAZIONE
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("persona.dat"))) {
            oos.writeObject(p1);
            System.out.println("Oggetto serializzato correttamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // DESERIALIZZAZIONE
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("persona.dat"))) {
            Persona p2 = (Persona) ois.readObject();
            System.out.println("Oggetto deserializzato:");
            System.out.println(p2);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```

### Cosa noterai nell’output

La `password` è dichiarata `transient`, quindi **non viene serializzata**.

Perciò dopo la deserializzazione otterrai qualcosa di simile:

```text
Oggetto serializzato correttamente.
Oggetto deserializzato:
Persona{nome='Marco', eta=30, password='null'}
```

Questo succede perché il campo `transient` non viene salvato nel file.
