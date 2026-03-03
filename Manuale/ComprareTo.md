# Comparable e Comparator in Java

## 1️⃣ Introduzione

In Java non esiste un'interfaccia chiamata "compare".\
Esistono invece:

-   `Comparable<T>` → per definire l'ordinamento naturale di una classe
-   `Comparator<T>` → per definire ordinamenti alternativi

La parola `compare` è il nome del metodo usato dentro queste interfacce.

------------------------------------------------------------------------

## 2️⃣ Comparable`<T>`{=html} → Ordinamento Naturale

Si trova nel package:

``` java
java.lang.Comparable
```

Metodo obbligatorio:

``` java
int compareTo(T o);
```

### Significato del valore di ritorno

  Valore   Significato
  -------- -------------
  \< 0     this \< o
  = 0      this == o
  \> 0     this \> o

------------------------------------------------------------------------

## 3️⃣ Esempio -- Ordinare Studenti per Voto

``` java
public class Student implements Comparable<Student> {

    private String name;
    private int grade;

    public Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    @Override
    public int compareTo(Student other) {
        return this.grade - other.grade;
    }
}
```

### Spiegazione

``` java
return this.grade - other.grade;
```

-   Se this.grade = 8 e other.grade = 6 → ritorna +2 → maggiore\
-   Se this.grade = 5 e other.grade = 7 → ritorna -2 → minore\
-   Se uguali → 0

------------------------------------------------------------------------

## 4️⃣ Utilizzo con Collections.sort()

``` java
ArrayList<Student> students = new ArrayList<>();

students.add(new Student("Marco", 7));
students.add(new Student("Luca", 9));
students.add(new Student("Anna", 6));

Collections.sort(students);
```

Funziona perché la classe implementa `Comparable`.

Se NON implementi Comparable, il sort non funziona.

------------------------------------------------------------------------

## 5️⃣ Quando usare Comparable?

Usalo quando:

✔ Esiste un solo criterio naturale di ordinamento\
✔ L'oggetto deve sapere sempre come ordinarsi

Esempi classici:

-   String → ordine alfabetico\
-   Integer → ordine numerico\
-   Date → ordine cronologico

------------------------------------------------------------------------

## 6️⃣ Comparator`<T>`{=html} → Ordinamenti Alternativi

Si trova nel package:

``` java
java.util.Comparator
```

Metodo:

``` java
int compare(T o1, T o2);
```

------------------------------------------------------------------------

## 7️⃣ Esempio -- Ordinare Studenti per Nome

``` java
Comparator<Student> nameComparator = new Comparator<Student>() {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.getName().compareTo(s2.getName());
    }
};

Collections.sort(students, nameComparator);
```

------------------------------------------------------------------------

## 8️⃣ Versione Moderna con Lambda

``` java
Collections.sort(students,
    (s1, s2) -> s1.getName().compareTo(s2.getName()));
```

Versione ancora più pulita:

``` java
students.sort(Comparator.comparing(Student::getName));
```

------------------------------------------------------------------------

## 9️⃣ Multi-criterio (Avanzato)

Ordinare:

1.  Prima per voto
2.  Poi per nome

``` java
students.sort(
    Comparator.comparing(Student::getGrade)
              .thenComparing(Student::getName)
);
```

------------------------------------------------------------------------

## 🔟 Differenze Chiave

  Comparable             Comparator
  ---------------------- -------------------------
  compareTo              compare
  Dentro la classe       Fuori dalla classe
  Ordinamento naturale   Ordinamento alternativo
  Uno solo               Infiniti

------------------------------------------------------------------------

## ⚠ Errore Comune

Questo NON funziona con oggetti:

``` java
if (student1 > student2)
```

Con oggetti devi usare:

``` java
student1.compareTo(student2)
```

------------------------------------------------------------------------

## 🧠 Dove Diventa Fondamentale?

Comparable e Comparator sono essenziali quando lavori con:

-   TreeSet
-   TreeMap
-   PriorityQueue
-   Algoritmi di ordinamento
-   Strutture dati avanzate

Senza uno dei due, Java non sa come confrontare gli oggetti.
