Dopo l’`INNER JOIN`, i join più importanti sono:

- `LEFT JOIN`
- `RIGHT JOIN`
- `FULL JOIN`

La differenza principale è:

> cosa succede alle righe che NON trovano corrispondenza.

---

# 1. LEFT JOIN

Il `LEFT JOIN` prende:
- TUTTE le righe della tabella sinistra
- solo le righe compatibili della tabella destra

Se non trova corrispondenza:
- mette `NULL`

---

# Sintassi

```sql
select *
from tabella1
left join tabella2
	on condizione;
```

---

# Esempio

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

Marco non ha rapporti.

---

# Query

```sql
select d.nome, r.id_rapporto
from dipendenti d
left join rapporto_cliente r
	on d.id_dipendente = r.id_dipendente;
```

---

# Risultato

| nome | id_rapporto |
|---|---|
| Paolo | 1 |
| Luca | 2 |
| Marco | NULL |

---

# Perché compare Marco?

Perché il `LEFT JOIN`:
- mantiene TUTTA la tabella sinistra (`dipendenti`)
- anche se non trova match

---

# Visualmente

```text
TABELLA SINISTRA + MATCH
```

oppure:

```text
 _________
|         \__
| LEFT       |
|_________/  |
```

---

# Quando si usa?

Quando vuoi:
- vedere anche i record senza associazioni
- trovare elementi “orfani”

---

# Esempio reale

## Trovare dipendenti senza clienti

```sql
select d.nome
from dipendenti d
left join rapporto_cliente r
	on d.id_dipendente = r.id_dipendente
where r.id_rapporto is null;
```

---

# Come funziona?

Il `LEFT JOIN` mette `NULL` dove non trova match.

Quindi:

```sql
where r.id_rapporto is null
```

significa:

> “dammi quelli senza collegamenti”.

---

# 2. RIGHT JOIN

È il contrario del `LEFT JOIN`.

Prende:
- tutte le righe della tabella destra
- solo i match della sinistra

---

# Sintassi

```sql
select *
from tabella1
right join tabella2
	on condizione;
```

---

# Esempio

```sql
select d.nome, r.id_rapporto
from rapporto_cliente r
right join dipendenti d
	on r.id_dipendente = d.id_dipendente;
```

---

# Risultato

| nome | id_rapporto |
|---|---|
| Paolo | 1 |
| Luca | 2 |
| Marco | NULL |

---

# Importante

Questo:

```sql
A LEFT JOIN B
```

è equivalente a:

```sql
B RIGHT JOIN A
```

Cambiano solo posizione e leggibilità.

---

# Nella pratica

Il `RIGHT JOIN` si usa poco.

Quasi tutti preferiscono:
- usare sempre `LEFT JOIN`
- invertire le tabelle se necessario

Perché è più leggibile.

---

# 3. FULL JOIN

Il `FULL JOIN` prende:
- tutte le righe della sinistra
- tutte le righe della destra
- matchando dove possibile

---

# Visualmente

```text
TUTTA TABELLA A + TUTTA TABELLA B
```

---

# Esempio concettuale

## dipendenti

| id | nome |
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
| 3 | 99 |

---

# FULL JOIN

```sql
select *
from dipendenti d
full join rapporto_cliente r
	on d.id_dipendente = r.id_dipendente;
```

---

# Risultato

| nome | id_rapporto |
|---|---|
| Paolo | 1 |
| Luca | 2 |
| Marco | NULL |
| NULL | 3 |

---

# Problema importante

## MySQL NON supporta FULL JOIN

Se usi MySQL:

```sql
full join
```

darà errore.

---

# Come simulare FULL JOIN in MySQL

Si usa:

```sql
LEFT JOIN
UNION
RIGHT JOIN
```

---

# Esempio

```sql
select d.nome, r.id_rapporto
from dipendenti d
left join rapporto_cliente r
	on d.id_dipendente = r.id_dipendente

union

select d.nome, r.id_rapporto
from dipendenti d
right join rapporto_cliente r
	on d.id_dipendente = r.id_dipendente;
```

---

# Differenza fondamentale

| JOIN | Tiene righe senza match? |
|---|---|
| INNER JOIN | NO |
| LEFT JOIN | solo sinistra |
| RIGHT JOIN | solo destra |
| FULL JOIN | entrambe |

---

# Riassunto mentale

## INNER JOIN

```text
Solo corrispondenze
```

---

## LEFT JOIN

```text
Tutta la sinistra + match
```

---

## RIGHT JOIN

```text
Tutta la destra + match
```

---

## FULL JOIN

```text
Tutto
```

---

# Regola pratica importante

## INNER JOIN

Usalo quando:
- vuoi solo dati collegati

---

## LEFT JOIN

Usalo quando:
- vuoi vedere anche elementi senza collegamenti

È il join più usato dopo INNER.

---

## RIGHT JOIN

Usato raramente.

---

## FULL JOIN

Molto utile teoricamente, ma MySQL non lo implementa direttamente.
