# COSA È UNA FUNZIONE SQL

Una funzione è un blocco di codice salvato nel database che:

- riceve parametri
- esegue operazioni
- restituisce un risultato

È molto simile a una funzione in Java.

---

# STRUTTURA BASE

```sql
create or replace function nome_funzione(parametri)
returns tipo_ritorno
as $$
begin

    -- codice

end;
$$ language plpgsql;
```

---

# ESEMPIO PIÙ SEMPLICE

Somma di due numeri:

```sql
create or replace function somma(a int, b int)
returns int
as $$
begin
    return a + b;
end;
$$ language plpgsql;
```

Uso:

```sql
select somma(10,5);
```

Risultato:

```text
15
```

---

# SPIEGAZIONE COMPLETA

## create or replace function

Crea la funzione.

Se esiste già → la sostituisce.

---

## somma(a int, b int)

Parametri in ingresso.

Come in Java:

```java
somma(int a, int b)
```

---

## returns int

Specifica cosa restituisce.

Può essere:

- int
- numeric
- varchar
- boolean
- date
- table
- record
- void

---

## as $$

Delimita il corpo della funzione.

Tutto il codice è scritto tra:

```sql
$$
...
$$
```

---

## begin / end

Inizio e fine del blocco logico.

Come:

```java
{
}
```

---

## return

Restituisce il valore finale.

---

# FUNZIONI VOID

Funzioni che non restituiscono nulla.

```sql
create or replace function test()
returns void
as $$
begin
    raise notice 'ciao';
end;
$$ language plpgsql;
```

Uso:

```sql
select test();
```

---

# RAISE NOTICE

Serve per stampare messaggi.

```sql
raise notice 'Messaggio';
```

Con variabili:

```sql
raise notice 'Valore: %', variabile;
```

---

# VARIABILI

Si dichiarano con `declare`.

```sql
create or replace function esempio()
returns int
as $$
declare
    numero int := 10;
begin
    return numero;
end;
$$ language plpgsql;
```

---

# OPERATORE :=

Assegnazione.

Come:

```java
=
```

in Java.

---

# IF

```sql
if stipendio > 1500 then
    raise notice 'stipendio alto';
end if;
```

---

# IF ELSE

```sql
if stipendio > 1500 then
    raise notice 'alto';
else
    raise notice 'basso';
end if;
```

---

# ELSIF

```sql
if voto >= 90 then
    raise notice 'A';
elsif voto >= 70 then
    raise notice 'B';
else
    raise notice 'C';
end if;
```

---

# LOOP

## LOOP infinito

```sql
loop

end loop;
```

---

# WHILE

```sql
while x < 10 loop
    x := x + 1;
end loop;
```

---

# FOR

```sql
for i in 1..10 loop
    raise notice '%', i;
end loop;
```

---

# QUERY DENTRO FUNZIONI

Puoi usare:

- SELECT
- INSERT
- UPDATE
- DELETE

---

# SELECT INTO

Prende valori dal database e li salva in variabili.

## Esempio

```sql
create or replace function trova_nome(id_input int)
returns varchar
as $$
declare
    nome_trovato varchar;
begin

    select nome
    into nome_trovato
    from employee
    where id = id_input;

    return nome_trovato;

end;
$$ language plpgsql;
```

---

# DIFFERENZA TRA SELECT NORMALE E SELECT INTO

## SELECT normale

Mostra dati.

```sql
select * from employee;
```

---

## SELECT INTO

Salva dati in una variabile.

```sql
select nome into variabile;
```

---

# FUNZIONI CHE RESTITUISCONO TABELLE

Importantissime.

---

# RETURNS TABLE

```sql
returns table(
    id int,
    nome varchar
)
```

---

# ESEMPIO COMPLETO

```sql
create or replace function lista_employee()
returns table(
    id int,
    fullname varchar
)
as $$
begin

    return query
    select e.id, e.fullname
    from employee e;

end;
$$ language plpgsql;
```

Uso:

```sql
select * from lista_employee();
```

---

# RETURN QUERY

Serve per restituire una SELECT.

FUNZIONA SOLO CON QUERY CHE PRODUCONO RIGHE.

Corretto:

```sql
return query
select * from employee;
```

Sbagliato:

```sql
return query
create table test(...);
```

Perché `CREATE TABLE` non restituisce tuple.

---

# DIFFERENZA TRA RETURN E RETURN QUERY

## RETURN

Restituisce un singolo valore.

```sql
return numero;
```

---

## RETURN QUERY

Restituisce più righe.

```sql
return query
select * from employee;
```

---

# PERFORM

Esegue query senza usare il risultato.

```sql
perform funzione_test();
```

Oppure:

```sql
perform * from employee;
```

---

# INSERT DENTRO FUNZIONI

```sql
create or replace function add_employee(
    p_nome varchar,
    p_stipendio numeric
)
returns void
as $$
begin

    insert into employee(nome, stipendio)
    values(p_nome, p_stipendio);

end;
$$ language plpgsql;
```

---

# UPDATE DENTRO FUNZIONI

```sql
update employee
set stipendio = 2000
where id = 1;
```

---

# DELETE DENTRO FUNZIONI

```sql
delete from employee
where id = 1;
```

---

# FUNZIONI CON RECORD

Quando non conosci la struttura.

```sql
declare
    rec record;
```

---

# CICLO SU RECORD

```sql
for rec in
    select * from employee
loop

    raise notice '%', rec.fullname;

end loop;
```

---

# ECCEZIONI

## EXCEPTION

```sql
exception
    when others then
        raise notice 'errore';
```

---

# ESEMPIO COMPLETO

```sql
create or replace function divisione(a numeric, b numeric)
returns numeric
as $$
begin

    return a / b;

exception
    when division_by_zero then
        raise notice 'divisione per zero';
        return 0;
end;
$$ language plpgsql;
```

---

# FUNZIONI SQL VS PROCEDURE

## FUNCTION

- restituisce valori
- usabile dentro SELECT

```sql
select funzione();
```

---

## PROCEDURE

- non restituisce valori direttamente
- si usa con CALL
- utile per operazioni grandi

```sql
call procedura();
```

---

# DIFFERENZA TRA VIEW E FUNCTION

## VIEW

È una query salvata.

```sql
create view lista as
select * from employee;
```

Non accetta parametri.

---

## FUNCTION

Può accettare parametri.

```sql
select * from lista_employee(1500);
```

Molto più dinamica.

---

# FUNZIONI AGGREGATE

Funzioni già presenti in SQL.

---

# COUNT

```sql
select count(*) from employee;
```

---

# SUM

```sql
select sum(stipendio) from employee;
```

---

# AVG

```sql
select avg(stipendio) from employee;
```

---

# MAX / MIN

```sql
select max(stipendio) from employee;
```

---

# FUNZIONI STRINGA

## upper

```sql
select upper(nome);
```

---

## lower

```sql
select lower(nome);
```

---

## concat

```sql
select concat(nome,' ',cognome);
```

---

## length

```sql
select length(nome);
```

---

# FUNZIONI DATA

## now()

```sql
select now();
```

---

## current_date

```sql
select current_date;
```

---

## age()

```sql
select age(current_date, data_nascita);
```

---

# FUNZIONI NUMERICHE

## round

```sql
select round(10.567,2);
```

---

## ceil

```sql
select ceil(10.1);
```

---

## floor

```sql
select floor(10.9);
```

---

# OVERLOADING

Puoi avere più funzioni con lo stesso nome.

```sql
somma(int,int)
somma(numeric,numeric)
```

---

# DROP FUNCTION

Elimina funzione.

```sql
drop function somma;
```

Con parametri:

```sql
drop function somma(int,int);
```

---

# COME PENSARE LE FUNZIONI

Le funzioni SQL sono:

- mini programmi
- eseguiti direttamente nel database
- molto veloci
- utili per business logic
- riutilizzabili ovunque

---

# QUANDO USARLE

Usale quando:

- ripeti la stessa query
- hai logica complessa
- vuoi filtrare dati dinamicamente
- vuoi centralizzare regole
- vuoi evitare codice duplicato

---

# QUANDO EVITARLE

Evita funzioni enormi con:

- migliaia di righe
- troppa logica applicativa
- UI/business troppo complessa

Quella parte spesso è meglio nel backend Java/Python.

---

# ESEMPIO FINALE REALE

```sql
create or replace function find_employee(
    stipendio_min numeric
)
returns table(
    id int,
    fullname varchar,
    stipendio numeric
)
as $$
begin

    return query
    select 
        e.id,
        e.fullname,
        c.stipendio
    from employee e
        join contratto c 
            on c.id_contratto = e.id_contratto
    where c.stipendio > stipendio_min;

end;
$$ language plpgsql;
```

Uso:

```sql
select * from find_employee(1500);
```

Questa è una delle funzioni più tipiche nei database reali.
