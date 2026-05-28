# Connessione Database SQL in Java

Una connessione tra Java e un database SQL è, in pratica, un “canale di comunicazione” che permette alla tua applicazione di:

- inviare query SQL al database
- ricevere risultati
- modificare dati
- creare tabelle
- gestire transazioni

Il tutto avviene tramite JDBC.

---

# Cos’è JDBC

JDBC significa:

```text
Java Database Connectivity
```

È l’API standard di Java per collegarsi ai database relazionali.

JDBC fa da ponte tra:

| Java | Database |
|---|---|
| oggetti/classes | tabelle SQL |

---

# Architettura generale

Il flusso reale è questo:

```text
Java Application
       ↓
JDBC API
       ↓
JDBC Driver
       ↓
Database SQL
```

---

# 1. Applicazione Java

Il tuo programma Java vuole comunicare con il database.

Esempio:

```java
System.out.println("voglio leggere utenti");
```

Ma Java da solo non sa parlare direttamente con MySQL o PostgreSQL.

Serve un interprete.

---

# 2. JDBC API

Java usa interfacce standard come:

```java
Connection
Statement
PreparedStatement
ResultSet
```

Queste classi definiscono COME parlare con un database.

Ma non sanno ancora parlare con un database specifico.

---

# 3. JDBC Driver

Qui entra il driver.

Esempio MySQL:

```text
com.mysql.cj.jdbc.Driver
```

Esempio PostgreSQL:

```text
org.postgresql.Driver
```

Il driver traduce:

```text
richieste Java → linguaggio del database
```

È praticamente un traduttore.

---

# 4. Database

Il database riceve:

```sql
SELECT * FROM utenti;
```

esegue la query e restituisce i dati.

---

# Come nasce una connessione

Quando fai:

```java
Connection con =
DriverManager.getConnection(url, user, password);
```

succede questo.

---

# STEP 1 — DriverManager

`DriverManager` cerca un driver compatibile con l’URL.

Esempio:

```java
jdbc:mysql://localhost:3306/db_academy
```

vede:

```text
mysql
```

e usa il driver MySQL.

---

# STEP 2 — Apertura socket TCP/IP

Java apre una connessione di rete verso:

```text
localhost
porta 3306
```

che è il server MySQL.

Questa è una vera connessione di rete.

Anche se il database è sul tuo PC.

---

# STEP 3 — Autenticazione

Java invia:

```text
username
password
```

al database.

Esempio:

```properties
user=root
password=1234
```

Il database controlla:

- utente valido?
- password corretta?
- permessi presenti?

---

# STEP 4 — Sessione SQL

Se tutto va bene:

il database crea una sessione.

Ora Java può:

- fare query
- fare insert
- fare update
- fare delete
- creare tabelle

---

# Oggetti principali JDBC

---

# 1. Connection

Rappresenta il collegamento aperto col database.

```java
Connection con
```

È il canale principale.

---

# 2. Statement

Serve per inviare SQL.

```java
Statement stmt = con.createStatement();
```

---

# 3. PreparedStatement

Versione più sicura e professionale.

Esempio:

```java
PreparedStatement ps =
con.prepareStatement(
    "select * from utenti where id = ?"
);
```

---

# Perché PreparedStatement è importante

Evita SQL Injection.

Esempio:

```java
ps.setInt(1, 5);
```

Il database distingue:

- dati
- codice SQL

---

# 4. ResultSet

Contiene il risultato della query.

```java
ResultSet rs = stmt.executeQuery("select * from utenti");
```

---

# Come funziona ResultSet

Il database restituisce una tabella.

`ResultSet` è un cursore che scorre le righe.

```java
while(rs.next()) {
    System.out.println(rs.getString("nome"));
}
```

---

# Esempio completo

```java
import java.sql.*;

public class Main {

    public static void main(String[] args) {

        String url =
        "jdbc:mysql://localhost:3306/db_academy";

        String user = "root";

        String password = "1234";

        try {

            Connection con =
            DriverManager.getConnection(
                url,
                user,
                password
            );

            Statement stmt =
            con.createStatement();

            ResultSet rs =
            stmt.executeQuery(
                "select * from utenti"
            );

            while(rs.next()) {

                System.out.println(
                    rs.getInt("id")
                );

                System.out.println(
                    rs.getString("nome")
                );
            }

            rs.close();
            stmt.close();
            con.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
```

---

# Cosa succede dietro le quinte

Quando esegui:

```java
stmt.executeQuery(...)
```

JDBC:

1. converte il comando in protocollo MySQL
2. invia il pacchetto via rete
3. MySQL esegue la query
4. MySQL restituisce i risultati
5. JDBC trasforma il risultato in `ResultSet`

---

# Tipi di query

## SELECT

Restituisce dati.

```java
executeQuery()
```

---

## INSERT UPDATE DELETE

Modificano dati.

```java
executeUpdate()
```

Restituisce:

```text
numero righe modificate
```

---

# Transazioni

Il database lavora spesso con transazioni.

```java
con.setAutoCommit(false);
```

---

# Commit

Conferma modifiche.

```java
con.commit();
```

---

# Rollback

Annulla modifiche.

```java
con.rollback();
```

---

# Esempio transazione

```java
try {

    con.setAutoCommit(false);

    // operazioni SQL

    con.commit();

} catch(Exception e) {

    con.rollback();
}
```

---

# Perché chiudere la connessione

Le connessioni consumano:

- memoria
- socket di rete
- thread
- risorse database

Quindi:

```java
con.close();
```

è fondamentale.

---

# Problemi comuni

## 1. Driver mancante

Errore:

```text
No suitable driver
```

---

## 2. Database spento

Errore:

```text
Connection refused
```

---

## 3. Password errata

Errore:

```text
Access denied
```

---

## 4. URL sbagliato

Errore:

```text
Unknown database
```

---

# URL JDBC

## MySQL

```text
jdbc:mysql://localhost:3306/db
```

---

## PostgreSQL

```text
jdbc:postgresql://localhost:5432/db
```

---

# Pool di connessioni

Nelle applicazioni grandi non si apre una connessione ogni volta.

Si usa un:

```text
Connection Pool
```

che mantiene connessioni già aperte.

Framework come Spring Boot usano:

- HikariCP
- C3P0
- DBCP

---

# JDBC vs ORM

| JDBC | ORM |
|---|---|
| basso livello | alto livello |
| SQL manuale | oggetti |
| più controllo | più automazione |
| più complesso | più rapido |

---

# Riassunto finale

Una connessione SQL in Java funziona così:

```text
Java
 ↓
JDBC
 ↓
Driver SQL
 ↓
Connessione TCP/IP
 ↓
Database
```

e permette alla tua applicazione di:

- inviare query
- leggere dati
- modificare tabelle
- gestire transazioni
- controllare il database da codice Java.
