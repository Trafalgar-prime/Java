Le `VIEW` in MySQL sono delle **tabelle virtuali**.

Non salvano realmente i dati come una tabella normale.

Salvano invece:
- una query SQL
- che viene eseguita ogni volta che usi la view

---

# Idea mentale

Una `VIEW` è come:

```text id="vz42r4"
SELECT salvata con un nome
```

---

# Esempio reale

Hai questa query:

```sql id="8d54wq"
select d.nome, d.cognome, u.nome_ufficio
from dipendenti d
inner join uffici u
	on d.id_ufficio = u.id_ufficio;
```

Funziona.

Ma magari la usi continuamente.

Allora puoi trasformarla in una VIEW.

---

# Creazione VIEW

```sql id="7g5g8r"
create view vista_dipendenti_uffici as
select d.nome, d.cognome, u.nome_ufficio
from dipendenti d
inner join uffici u
	on d.id_ufficio = u.id_ufficio;
```

---

# Ora la usi come una tabella

```sql id="u9t69q"
select * from vista_dipendenti_uffici;
```

---

# Risultato

| nome | cognome | nome_ufficio |
|---|---|---|
| Paolo | Rossi | Amministrazione |
| Luca | Verdi | Marketing |

---

# Importantissimo

La view NON contiene realmente i dati.

Contiene la query:

```sql id="75igkr"
select ...
from ...
join ...
```

Quando fai:

```sql id="6ch8tp"
select * from vista_dipendenti_uffici;
```

MySQL esegue internamente la query originale.

---

# Perché usare le VIEW?

## 1. Semplificare query complesse

Invece di scrivere:

```sql id="m6zx2y"
select ...
inner join ...
inner join ...
where ...
group by ...
```

ogni volta…

usi:

```sql id="e6efh4"
select * from mia_view;
```

---

# 2. Rendere il codice più leggibile

Meglio:

```sql id="u4r8s2"
select * from dipendenti_attivi;
```

che:

```sql id="s3yd55"
select * from dipendenti
where stato = 'ATTIVO'
and data_licenziamento is null;
```

---

# 3. Sicurezza

Puoi mostrare solo alcuni dati.

Esempio:

Tabella vera:

| id | nome | stipendio |
|---|---|---|

View pubblica:

| nome |
|---|

Così nascondi informazioni sensibili.

---

# Esempio utile

## Tabella completa

```sql id="c53kbf"
create table dipendenti (
	id int,
	nome varchar(100),
	stipendio decimal(10,2)
);
```

---

# View senza stipendio

```sql id="o3txrd"
create view dipendenti_pubblici as
select nome
from dipendenti;
```

---

# Uso

```sql id="l3rzfp"
select * from dipendenti_pubblici;
```

---

# Modificare una VIEW

```sql id="w5f3j5"
create or replace view dipendenti_pubblici as
select nome, stipendio
from dipendenti;
```

---

# Eliminare una VIEW

```sql id="d8lzqs"
drop view dipendenti_pubblici;
```

---

# Differenza tra VIEW e TABLE

| TABLE | VIEW |
|---|---|
| contiene dati veri | contiene query |
| occupa spazio | quasi no |
| dati fisici | dati virtuali |
| insert diretto | spesso no |

---

# Le VIEW si aggiornano automaticamente?

Sì.

Se cambia la tabella reale:

```sql id="pl5mfi"
insert into dipendenti values (1, 'Paolo', 1500);
```

la view mostra subito il nuovo dato.

---

# VIEW aggiornabili

Alcune view permettono:

```sql id="zt4v9o"
insert
update
delete
```

altre no.

Dipende dalla complessità della query.

---

# Esempio NON aggiornabile

```sql id="k0n4i8"
create view prova as
select d.nome, u.nome_ufficio
from dipendenti d
inner join uffici u
	on d.id_ufficio = u.id_ufficio;
```

Qui MySQL spesso NON permette update diretti.

Perché ci sono più tabelle.

---

# Esempio aggiornabile

```sql id="i7p2pf"
create view dip_view as
select nome, cognome
from dipendenti;
```

Questa invece spesso sì.

---

# Regola mentale importante

## TABLE

```text id="k4o7d4"
Salva dati
```

---

## VIEW

```text id="idyl3m"
Salva query
```

---

# Esempio reale professionale

Molte aziende usano view per:
- report
- dashboard
- statistiche
- permessi
- semplificazione query
- API backend

---

# Riassunto finale

Una VIEW:
- è una tabella virtuale
- salva una query
- semplifica query complesse
- migliora leggibilità e sicurezza
- si usa come una tabella normale
- non salva realmente i dati
- si aggiorna automaticamente quando cambiano i dati reali
