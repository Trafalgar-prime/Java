Gli `INDEX` in MySQL servono a **velocizzare le ricerche sui dati**.

Sono uno dei concetti più importanti nei database grandi.

---

# Idea mentale

Un `INDEX` è come l’indice di un libro.

---

# Senza indice

Se cerchi:

```text id="zqndca"
Capitolo: "Reti Neurali"
```

senza indice:
- devi sfogliare tutte le pagine

---

# Con indice

Vai direttamente alla pagina giusta.

---

# Nei database è uguale

Immagina una tabella con:

```text id="ymt4fk"
10 milioni di dipendenti
```

e fai:

```sql id="fqjplw"
select *
from dipendenti
where cognome = 'Rossi';
```

---

# Senza INDEX

MySQL controlla:
- riga 1
- riga 2
- riga 3
- riga 4
- …

Questo si chiama:

```text id="h0zv8e"
FULL TABLE SCAN
```

cioè scansione completa della tabella.

È lenta.

---

# Con INDEX

MySQL usa una struttura speciale:
- simile ad un albero (B-Tree)
- ordinata
- ottimizzata per ricerca veloce

e trova subito i record.

---

# A cosa serve quindi?

## Velocizzare:

- `WHERE`
- `JOIN`
- `ORDER BY`
- `GROUP BY`
- ricerche
- filtri

---

# Esempio

## Tabella

```sql id="s4bmcc"
create table dipendenti (
	id int primary key auto_increment,
	nome varchar(100),
	cognome varchar(100)
);
```

---

# Query lenta

```sql id="g9vl0w"
select *
from dipendenti
where cognome = 'Rossi';
```

---

# Creazione INDEX

```sql id="pqyn5d"
create index idx_cognome
on dipendenti(cognome);
```

---

# Significato

```text id="8dkvv0"
Crea un indice sulla colonna cognome
```

---

# Dopo l’index

Questa query:

```sql id="ngjn7n"
select *
from dipendenti
where cognome = 'Rossi';
```

diventa molto più veloce.

---

# Come vedere gli index

```sql id="sgnh5w"
show index from dipendenti;
```

---

# Eliminare index

```sql id="zbl4nk"
drop index idx_cognome
on dipendenti;
```

---

# PRIMARY KEY crea automaticamente un INDEX

Quando fai:

```sql id="k3x6kp"
primary key(id)
```

MySQL crea automaticamente un indice.

Quindi:

```sql id="wckaxq"
id
```

è già velocissimo da cercare.

---

# Anche UNIQUE crea un INDEX

```sql id="5lg0kv"
telefono varchar(10) unique
```

crea automaticamente un indice.

---

# Perché gli index sono così importanti?

Perché i database reali hanno:
- milioni
- miliardi
di righe.

Senza index:
- le query diventano lentissime.

---

# Come funziona internamente?

MySQL usa spesso:

```text id="3yzyuh"
B-TREE
```

che è una struttura ad albero ordinata.

---

# Idea semplificata

Invece di:

```text id="z0vcvq"
1 -> 2 -> 3 -> 4 -> 5 -> ...
```

fa:

```text id="hm2m7t"
          50
       /      \
     20        80
    /  \      /  \
  10   30   70   90
```

Quindi trova i dati in pochissimi passaggi.

---

# Dove conviene mettere INDEX?

## Ottimo per:

```sql id="wdb13y"
where cognome = ?
```

```sql id="u0v6tb"
join on id_cliente
```

```sql id="i6f6jm"
order by cognome
```

---

# NON conviene su tutto

Gli index:
- occupano spazio
- rallentano INSERT/UPDATE/DELETE

Perché ogni volta MySQL deve aggiornare anche l’indice.

---

# Esempio

Hai:

```text id="9p6scc"
100 milioni di righe
```

e metti:
- 25 index

Ogni insert diventa pesante.

---

# Regola importante

Gli index:
- velocizzano lettura
- rallentano scrittura

---

# INDEX composti

Puoi fare index su più colonne.

---

# Esempio

```sql id="pk3p7t"
create index idx_nome_cognome
on dipendenti(nome, cognome);
```

---

# Utile per query tipo:

```sql id="3u4d7s"
select *
from dipendenti
where nome = 'Paolo'
and cognome = 'Rossi';
```

---

# Attenzione importante

Questo index:

```sql id="o54w3g"
(nome, cognome)
```

funziona bene per:

```sql id="3tpr97"
where nome = ...
```

oppure:

```sql id="r6s11g"
where nome = ...
and cognome = ...
```

MA NON bene per:

```sql id="s41t6f"
where cognome = ...
```

Perché conta l’ordine.

---

# Come controllare se un index viene usato

Usi:

```sql id="lbj1ae"
explain
```

---

# Esempio

```sql id="6z9bh1"
explain
select *
from dipendenti
where cognome = 'Rossi';
```

MySQL ti mostra:
- se usa index
- quale index usa
- quante righe legge

---

# FULL TABLE SCAN vs INDEX

## Senza index

```text id="b1k4bz"
legge tutta la tabella
```

---

## Con index

```text id="00gbx7"
legge solo i dati necessari
```

---

# Tipi di INDEX principali

## 1. PRIMARY INDEX

Creato dalla primary key.

---

## 2. UNIQUE INDEX

Creato da UNIQUE.

---

## 3. INDEX normale

```sql id="9a1l4v"
create index ...
```

---

## 4. COMPOSITE INDEX

Index su più colonne.

---

# Riassunto mentale finale

## INDEX

```text id="x4gxpd"
Velocizza la ricerca dei dati
```

---

# Ma allora perché non mettere index ovunque?

Perché:
- occupano RAM e disco
- rallentano insert/update/delete
- hanno costo di manutenzione

---

# Regola pratica professionale

Metti index:
- sulle colonne usate spesso nei WHERE
- sulle foreign key
- sulle colonne usate nelle JOIN
- sulle colonne usate in ORDER BY
- sulle colonne usate spesso nelle ricerche

---

# Esempio reale professionale

Quasi sempre trovi index su:

```text id="s0s2os"
id_cliente
email
username
codice_fiscale
p_iva
foreign key
timestamp
```
