# Parole Chiave SQL per Database Relazionali

Quando si lavora con i database relazionali, il linguaggio principale è **SQL** (*Structured Query Language*).

SQL è composto da moltissime parole chiave (*keywords*) che servono per:

- creare database e tabelle
- modificare strutture
- inserire dati
- interrogare dati
- eliminare dati
- gestire relazioni
- controllare utenti e permessi
- usare funzioni avanzate

Di seguito trovi le parole chiave principali organizzate per categorie.

---

# 1. Parole chiave per interrogare i dati (QUERY)

Sono le più usate.

| Keyword | Significato |
|---|---|
| `SELECT` | seleziona dati |
| `FROM` | indica la tabella |
| `WHERE` | condizione |
| `AND` | entrambe vere |
| `OR` | almeno una vera |
| `NOT` | negazione |
| `IN` | appartiene a lista |
| `BETWEEN` | intervallo |
| `LIKE` | ricerca pattern |
| `IS NULL` | valore nullo |
| `IS NOT NULL` | non nullo |
| `DISTINCT` | elimina duplicati |
| `ORDER BY` | ordina risultati |
| `GROUP BY` | raggruppa |
| `HAVING` | condizione sui gruppi |
| `LIMIT` | limita risultati |
| `OFFSET` | salta righe |
| `AS` | alias |
| `TOP` | limita risultati, usato soprattutto in SQL Server |

---

## Esempio

```sql
SELECT nome, cognome
FROM studenti
WHERE voto > 24
ORDER BY cognome;
```

---

# 2. JOIN: relazioni tra tabelle

Le `JOIN` servono a unire tabelle relazionate tra loro.

| Keyword | Significato |
|---|---|
| `JOIN` | unisce tabelle |
| `INNER JOIN` | mostra solo i record che hanno corrispondenza in entrambe le tabelle |
| `LEFT JOIN` | mostra tutti i record della tabella di sinistra e quelli corrispondenti della destra |
| `RIGHT JOIN` | mostra tutti i record della tabella di destra e quelli corrispondenti della sinistra |
| `FULL JOIN` | mostra tutti i record di entrambe le tabelle |
| `ON` | indica la condizione della join |
| `UNION` | unisce risultati eliminando duplicati |
| `UNION ALL` | unisce risultati senza eliminare duplicati |

---

## Esempio

```sql
SELECT studenti.nome, esami.voto
FROM studenti
INNER JOIN esami
ON studenti.id = esami.id_studente;
```

---

# 3. Manipolazione dati (DML)

Le istruzioni DML servono per modificare i dati presenti nelle tabelle.

DML significa **Data Manipulation Language**.

| Keyword | Significato |
|---|---|
| `INSERT INTO` | inserisce nuovi dati |
| `VALUES` | indica i valori da inserire |
| `UPDATE` | aggiorna dati esistenti |
| `SET` | assegna nuovi valori |
| `DELETE` | elimina righe |
| `TRUNCATE` | svuota una tabella |

---

## INSERT

```sql
INSERT INTO studenti(nome, cognome)
VALUES ('Luca', 'Rossi');
```

---

## UPDATE

```sql
UPDATE studenti
SET nome = 'Marco'
WHERE id = 1;
```

---

## DELETE

```sql
DELETE FROM studenti
WHERE id = 1;
```

---

# 4. Creazione strutture database (DDL)

Le istruzioni DDL servono per creare o modificare la struttura del database.

DDL significa **Data Definition Language**.

| Keyword | Significato |
|---|---|
| `CREATE` | crea |
| `DATABASE` | database |
| `TABLE` | tabella |
| `ALTER` | modifica |
| `DROP` | elimina definitivamente |
| `RENAME` | rinomina |
| `ADD` | aggiunge |
| `COLUMN` | colonna |
| `MODIFY` | modifica una colonna |

---

## CREATE TABLE

```sql
CREATE TABLE studenti (
    id INT,
    nome VARCHAR(50)
);
```

---

## ALTER TABLE

```sql
ALTER TABLE studenti
ADD email VARCHAR(100);
```

---

# 5. Vincoli (CONSTRAINTS)

I vincoli definiscono regole sui dati.

Servono a proteggere la correttezza del database.

| Keyword | Significato |
|---|---|
| `PRIMARY KEY` | chiave primaria |
| `FOREIGN KEY` | chiave esterna |
| `REFERENCES` | riferimento a un'altra tabella |
| `UNIQUE` | valore unico |
| `NOT NULL` | valore obbligatorio |
| `CHECK` | controllo su una condizione |
| `DEFAULT` | valore predefinito |
| `AUTO_INCREMENT` | incremento automatico, usato in MySQL |
| `IDENTITY` | auto incremento, usato soprattutto in SQL Server |

---

## Esempio

```sql
CREATE TABLE studenti (
    id INT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
);
```

---

# 6. Funzioni aggregate

Le funzioni aggregate servono per fare calcoli su più righe.

| Funzione | Significato |
|---|---|
| `COUNT()` | conta |
| `SUM()` | somma |
| `AVG()` | calcola la media |
| `MIN()` | trova il valore minimo |
| `MAX()` | trova il valore massimo |

---

## Esempio

```sql
SELECT AVG(voto)
FROM esami;
```

---

# 7. Funzioni stringa

Le funzioni stringa servono per lavorare sui testi.

| Funzione | Significato |
|---|---|
| `UPPER()` | converte in maiuscolo |
| `LOWER()` | converte in minuscolo |
| `LENGTH()` | restituisce la lunghezza |
| `SUBSTRING()` | estrae una parte di stringa |
| `TRIM()` | rimuove spazi iniziali e finali |
| `REPLACE()` | sostituisce testo |

---

# 8. Funzioni data e ora

Le funzioni data e ora servono per lavorare con date, orari e timestamp.

| Funzione | Significato |
|---|---|
| `NOW()` | data e ora attuale |
| `CURDATE()` | data attuale |
| `CURTIME()` | ora attuale |
| `YEAR()` | estrae l'anno |
| `MONTH()` | estrae il mese |
| `DAY()` | estrae il giorno |

---

# 9. Transazioni

Le transazioni sono fondamentali nei database professionali.

Servono a rendere sicure operazioni composte da più passaggi.

| Keyword | Significato |
|---|---|
| `COMMIT` | conferma e salva le modifiche |
| `ROLLBACK` | annulla le modifiche |
| `SAVEPOINT` | crea un punto di ritorno |
| `TRANSACTION` | indica una transazione |

---

## Esempio

```sql
START TRANSACTION;

UPDATE conti
SET saldo = saldo - 100
WHERE id = 1;

COMMIT;
```

---

# 10. Indici

Gli indici servono a velocizzare le query.

Sono molto utili quando una tabella contiene molti dati.

| Keyword | Significato |
|---|---|
| `INDEX` | indice |
| `CREATE INDEX` | crea un indice |
| `DROP INDEX` | elimina un indice |

---

# 11. VIEW

Una `VIEW` è una tabella virtuale.

Non contiene direttamente i dati, ma mostra il risultato di una query salvata.

| Keyword | Significato |
|---|---|
| `VIEW` | vista |
| `CREATE VIEW` | crea una vista |

---

## Esempio

```sql
CREATE VIEW studenti_promossi AS
SELECT *
FROM studenti
WHERE voto >= 18;
```

---

# 12. Stored Procedure e funzioni

Le stored procedure e le funzioni servono per salvare logica SQL dentro il database.

| Keyword | Significato |
|---|---|
| `PROCEDURE` | procedura |
| `FUNCTION` | funzione |
| `BEGIN` | inizio blocco |
| `END` | fine blocco |
| `DECLARE` | dichiara una variabile |
| `RETURN` | restituisce un valore |

---

# 13. Trigger

Un trigger è un'azione automatica che viene eseguita dal database quando accade un evento.

Ad esempio:

- prima di un inserimento
- dopo un aggiornamento
- prima di una cancellazione

| Keyword | Significato |
|---|---|
| `TRIGGER` | trigger |
| `BEFORE` | prima |
| `AFTER` | dopo |
| `FOR EACH ROW` | per ogni riga |

---

# 14. Sicurezza e utenti

Queste parole chiave servono per gestire utenti e permessi.

| Keyword | Significato |
|---|---|
| `GRANT` | concede permessi |
| `REVOKE` | rimuove permessi |
| `USER` | utente |
| `PASSWORD` | password |

---

# 15. Tipi di dato più usati

I tipi di dato stabiliscono che tipo di informazione può essere salvata in una colonna.

| Tipo | Significato |
|---|---|
| `INT` | numero intero |
| `BIGINT` | numero intero grande |
| `FLOAT` | numero decimale |
| `DOUBLE` | numero decimale più preciso |
| `CHAR` | stringa a lunghezza fissa |
| `VARCHAR` | stringa a lunghezza variabile |
| `TEXT` | testo lungo |
| `DATE` | data |
| `TIME` | ora |
| `DATETIME` | data più ora |
| `BOOLEAN` | vero/falso |
| `BLOB` | dati binari |

---

# 16. Query avanzate

Queste parole chiave sono usate per query più complesse.

| Keyword | Significato |
|---|---|
| `EXISTS` | verifica se esiste almeno un risultato |
| `ANY` | almeno uno |
| `ALL` | tutti |
| `CASE` | struttura condizionale simile a if/switch |
| `WITH` | crea una CTE, cioè una query temporanea |
| `CAST` | converte un tipo di dato |
| `COALESCE` | restituisce il primo valore non nullo |

---

## CASE

```sql
SELECT nome,
CASE
    WHEN voto >= 18 THEN 'Promosso'
    ELSE 'Bocciato'
END
FROM studenti;
```

---

# 17. Gerarchia concettuale importante

SQL si divide in macro-categorie.

| Categoria | Scopo |
|---|---|
| DDL | gestisce la struttura del database |
| DML | manipola i dati |
| DQL | interroga i dati |
| DCL | gestisce sicurezza e permessi |
| TCL | gestisce le transazioni |

---

# 18. Database relazionali famosi

Alcuni database relazionali molto usati sono:

- MySQL
- PostgreSQL
- Oracle Database
- MariaDB
- SQLite
- Microsoft SQL Server

---

# Conclusione

Queste non sono tutte le keyword esistenti in assoluto, perché ogni database aggiunge estensioni proprie.

Però queste sono le parole chiave principali del coding SQL professionale.

Se impari bene queste categorie, sei già in grado di capire e scrivere gran parte delle query usate nei database relazionali.
