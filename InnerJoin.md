L’`INNER JOIN` serve per **unire i dati di due o più tabelle** usando una colonna in comune.

È uno dei concetti più importanti dei database relazionali.

---

# Concetto base

Hai queste tabelle:

## Tabella `dipendenti`

| id_dipendente | nome  | cognome |
|---|---|---|
| 1 | Paolo | Rossi |
| 2 | Luca | Verdi |

---

## Tabella `rapporto_cliente`

| id_rapporto | descrizione | id_dipendente |
|---|---|---|
| 1 | Assistenza | 1 |
| 2 | Vendita | 2 |

---

La colonna:

```sql
id_dipendente
```

in `rapporto_cliente` è una **foreign key** che punta a:

```sql
dipendenti.id_dipendente
```

Quindi le due tabelle sono collegate.

---

# Problema senza JOIN

Se fai:

```sql
select * from rapporto_cliente;
```

ottieni:

| id_rapporto | descrizione | id_dipendente |
|---|---|---|
| 1 | Assistenza | 1 |
| 2 | Vendita | 2 |

Ma non sai chi è il dipendente.

Per avere il nome devi collegare le tabelle.

---

# INNER JOIN

```sql
select *
from rapporto_cliente
inner join dipendenti
on rapporto_cliente.id_dipendente = dipendenti.id_dipendente;
```

---

# Come si legge

## 1. FROM

```sql
from rapporto_cliente
```

Parto dalla tabella `rapporto_cliente`.

---

## 2. INNER JOIN

```sql
inner join dipendenti
```

Voglio unire la tabella `dipendenti`.

---

## 3. ON

```sql
on rapporto_cliente.id_dipendente = dipendenti.id_dipendente
```

Qui dici:

> “Collega le righe dove gli ID coincidono”.

---

# Risultato

| id_rapporto | descrizione | id_dipendente | nome | cognome |
|---|---|---|---|---|
| 1 | Assistenza | 1 | Paolo | Rossi |
| 2 | Vendita | 2 | Luca | Verdi |

Ora hai i dati combinati.

---

# Perché si chiama INNER?

Perché prende solo le righe che hanno corrispondenza in entrambe le tabelle.

Esempio:

## dipendenti

| id_dipendente | nome |
|---|---|
| 1 | Paolo |
| 2 | Luca |
| 3 | Marco |

---

## rapporto_cliente

| id_rapporto | id_dipendente |
|---|---|
| 1 | 1 |
| 2 | 2 |

Marco (`id=3`) non ha rapporti.

Con `INNER JOIN`, Marco NON compare.

---

# Visualmente

`INNER JOIN` prende l’intersezione:

```text
TABELLA A      TABELLA B
     \          /
      \        /
       \______/
      SOLO MATCH
```

---

# Versione professionale

Di solito si usano alias:

```sql
select d.nome, d.cognome, r.descrizione
from rapporto_cliente r
inner join dipendenti d
	on r.id_dipendente = d.id_dipendente;
```

---

# Alias

Qui:

```sql
rapporto_cliente r
```

significa:

```sql
r = rapporto_cliente
```

e:

```sql
dipendenti d
```

significa:

```sql
d = dipendenti
```

Così scrivi:

```sql
d.nome
```

invece di:

```sql
dipendenti.nome
```

---

# JOIN su più tabelle

Puoi concatenare join.

Esempio:

```sql
select 
	d.nome,
	c.denominazione,
	r.descrizione
from rapporto_cliente r
inner join dipendenti d
	on r.id_dipendente = d.id_dipendente
inner join clienti c
	on r.id_cliente = c.id_cliente;
```

---

# Risultato finale

| nome | denominazione | descrizione |
|---|---|---|
| Paolo | Amazon | Assistenza |
| Luca | Google | Vendita |

---

# Regola mentale fondamentale

## FOREIGN KEY = collegamento

Quando vedi:

```sql
foreign key (id_cliente) references clienti(id_cliente)
```

quasi sicuramente farai una JOIN con quella tabella.

---

# Errori comuni

## 1. Dimenticare ON

SBAGLIATO:

```sql
inner join dipendenti;
```

Serve la condizione.

---

## 2. Collegare colonne sbagliate

SBAGLIATO:

```sql
on rapporto_cliente.id_rapporto = dipendenti.id_dipendente
```

Devi collegare chiavi coerenti.

---

## 3. Ambiguità colonne

Se entrambe le tabelle hanno `id`:

```sql
select id
```

MySQL non sa quale.

Serve:

```sql
select dipendenti.id_dipendente
```

oppure alias:

```sql
select d.id_dipendente
```

---

# Riassunto rapido

```sql
select colonne
from tabella1
inner join tabella2
	on tabella1.colonna = tabella2.colonna;
```

L’`INNER JOIN`:
- unisce tabelle
- usa colonne in comune
- restituisce solo le righe che combaciano
- sfrutta foreign key e primary key
