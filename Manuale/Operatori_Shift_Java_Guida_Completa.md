
# Operatori di Shift in Java: `<<` e `>>` (Guida Completa)

## 1️⃣ Cosa sono gli operatori di shift?

Gli operatori:

- `<<`  → Left Shift (shift a sinistra)
- `>>`  → Right Shift (shift a destra con segno)
- `>>>` → Right Shift senza segno (unsigned)

Servono per spostare i bit di un numero verso sinistra o verso destra.

---

# 2️⃣ Operatore `<<` (Left Shift)

### Sintassi:

```java
numero << n
```

Sposta tutti i bit verso sinistra di `n` posizioni.

Ogni shift a sinistra equivale a moltiplicare per 2.

---

## Esempio:

```java
int x = 5;          // 00000101
int y = x << 1;     // 00001010
System.out.println(y);
```

Risultato:

```
10
```

Perché:
5 × 2 = 10

---

## Altro esempio

```java
int x = 3;          // 00000011
int y = x << 2;     // 00001100
```

Risultato:

```
12
```

Perché:
3 × 2² = 12

---

# 3️⃣ Operatore `>>` (Right Shift con segno)

### Sintassi:

```java
numero >> n
```

Sposta i bit verso destra mantenendo il bit di segno.

Ogni shift a destra equivale a dividere per 2 (arrotondando verso il basso).

---

## Esempio positivo

```java
int x = 8;          // 00001000
int y = x >> 1;     // 00000100
System.out.println(y);
```

Risultato:

```
4
```

---

## Esempio con numero negativo

```java
int x = -8;
int y = x >> 1;
System.out.println(y);
```

Risultato:

```
-4
```

Perché il bit di segno viene mantenuto.

---

# 4️⃣ Differenza tra `>>` e `>>>`

| Operatore | Mantiene segno? | Riempie con |
|------------|-----------------|-------------|
| `>>`       | Sì              | Bit di segno |
| `>>>`      | No              | Zeri |

---

## Esempio:

```java
int x = -1;

System.out.println(x >> 1);
System.out.println(x >>> 1);
```

Risultato:

```
-1
2147483647
```

Perché `>>>` riempie con zeri e non mantiene il segno.

---

# 5️⃣ Esempio pratico con byte

```java
byte b = 4;          // 00000100
byte result = (byte)(b << 1);
System.out.println(result);
```

Output:

```
8
```

⚠️ Nota: con `byte` serve spesso il cast perché Java promuove a `int`.

---

# 6️⃣ Quando usare gli shift?

Gli operatori di shift sono usati per:

- Moltiplicazioni e divisioni veloci per potenze di 2
- Manipolazione bit
- Protocolli di rete
- Compressione dati
- Embedded systems
- Performance-critical code

---

# 7️⃣ Riassunto mentale veloce

- `x << n`  → moltiplica per 2ⁿ
- `x >> n`  → divide per 2ⁿ mantenendo il segno
- `x >>> n` → divide per 2ⁿ ignorando il segno

---

# 8️⃣ Esercizio

Calcola:

```
6 << 2
16 >> 3
-8 >> 2
```

Soluzioni:

```
6 << 2  = 24
16 >> 3 = 2
-8 >> 2 = -2
```

---

# 9️⃣ Conclusione

Gli operatori `<<` e `>>` sono strumenti fondamentali per capire:

- Rappresentazione binaria
- Bit manipulation
- Ottimizzazioni low-level

Se padroneggi questi, hai capito davvero come funzionano i numeri in memoria.
