
# Byte negativi in Java (Guida Completa e Riassuntiva)

## 1️⃣ Cos'è un `byte` in Java

In Java:

```java
byte x;
```

- Occupa **8 bit**
- È **signed** (con segno)
- Intervallo valori:  
  **-128 → 127**

---

## 2️⃣ Perché esistono byte negativi?

Java usa la rappresentazione in **Complemento a 2** per i numeri con segno.

Questo sistema permette di rappresentare numeri negativi usando i bit.

---

## 3️⃣ Rappresentazione binaria

| Decimale | Binario (8 bit) |
|----------|----------------|
| 127      | 01111111 |
| 0        | 00000000 |
| -1       | 11111111 |
| -2       | 11111110 |
| -128     | 10000000 |

📌 Regola importante:  
Se il **primo bit (MSB)** è `1` → il numero è negativo.

---

## 4️⃣ Come leggere un byte negativo (Complemento a 2)

Esempio:

```
11111110
```

### Passaggi:

1. Inverti i bit:
```
00000001
```

2. Aggiungi 1:
```
00000010
```

3. Converti in decimale:
```
2
```

4. Metti il segno meno:

Risultato: **-2**

---

## 5️⃣ Esempio pratico in Java

```java
byte b = -2;
System.out.println(b);
```

Output:
```
-2
```

---

## 6️⃣ Come vedere i bit reali di un byte

⚠️ Attenzione: quando stampi un byte, Java lo promuove a `int` (32 bit).

Per vedere solo gli 8 bit reali:

```java
byte b = -1;
System.out.println(Integer.toBinaryString(b & 0xFF));
```

### Perché `& 0xFF`?

- `0xFF` = `11111111`
- Serve per eliminare l’estensione del segno
- Ti mostra solo gli 8 bit originali

Senza `& 0xFF`, vedresti 32 bit.

---

## 7️⃣ Concetti chiave da ricordare

- `byte` = 8 bit signed
- Primo bit = segno
- Java usa **Complemento a 2**
- Per vedere i bit reali → `b & 0xFF`
- Intervallo: `-128 → 127`

---

## 8️⃣ Formula mentale veloce

Se il primo bit è 1:

1. Inverti tutti i bit  
2. Aggiungi 1  
3. Metti il segno meno  

---

## 9️⃣ Esempio esercizio

Converti:

```
11101011
```

Procedimento:

1. Inverti → `00010100`
2. +1 → `00010101`
3. Decimale → 21

Risultato: **-21**

---

## 🔟 Conclusione

Capire il complemento a 2 è fondamentale per:

- Gestire overflow
- Lavorare con reti e protocolli
- Manipolare bit
- Comprendere conversioni tra tipi numerici

Se padroneggi questo concetto, hai fatto un passo avanti serio nella comprensione della rappresentazione dei dati.
