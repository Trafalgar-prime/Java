# Reflection in Java

Certo. In Java la Reflection è un meccanismo che permette a un programma di analizzare e usare classi, metodi, campi e costruttori durante l'esecuzione, anche se non li conosci direttamente mentre scrivi il codice.

In parole semplici:

con la Reflection Java può "guardare dentro" una classe e scoprire quali attributi, metodi, costruttori e informazioni contiene.

## 1. Che problema risolve la Reflection?

Normalmente in Java fai così:

```java
Persona p = new Persona();

p.saluta();
```

Qui tu conosci già:

`Persona`

e conosci già il metodo:

`saluta()`

Con la Reflection invece puoi fare cose più dinamiche.

Per esempio puoi avere il nome della classe come stringa:

```java
String nomeClasse = "com.example.Persona";
```

e dire a Java:

trovami questa classe, crea un oggetto e chiamami un metodo.

Questo è molto usato da framework come:

- Spring
- Hibernate
- JUnit
- Gson
- Jackson
- Maven plugin
- IDE
- librerie di serializzazione

## 2. La classe principale: Class

La Reflection parte quasi sempre da:

`Class`

Ogni classe Java ha un oggetto Class che la descrive.

Esempio:

```java
public class Persona {

}
```

Puoi ottenere informazioni su Persona così:

```java
Class<?> clazz = Persona.class;

System.out.println(clazz.getName());
```

Output:

```
Persona
```

Se la classe è dentro un package:

```
package com.example;
```

output:

```
com.example.Persona
```

## 3. Modi per ottenere un oggetto Class

Ci sono vari modi.

**Metodo 1: usando .class**

```java
Class<?> clazz = Persona.class;
```

Questo si usa quando conosci già la classe.

**Metodo 2: usando getClass()**

```java
Persona p = new Persona();

Class<?> clazz = p.getClass();
```

Qui parti da un oggetto già creato.

**Metodo 3: usando Class.forName()**

```java
Class<?> clazz = Class.forName("com.example.Persona");
```

Questo si usa quando hai il nome della classe come stringa.

Attenzione: devi scrivere il nome completo con il package.

Esempio:

```java
Class<?> clazz = Class.forName("com.betacom.veicoli.model.Macchina");
```

## 4. Esempio base completo

```java
class Persona {

    private String nome;
    private int eta;

    public void saluta() {
        System.out.println("Ciao");
    }
}

public class Main {

    public static void main(String[] args) {

        Class<?> clazz = Persona.class;

        System.out.println(clazz.getName());
        System.out.println(clazz.getSimpleName());
    }
}
```

Output:

```
Persona
Persona
```

Differenza:

`getName()`

restituisce il nome completo della classe.

`getSimpleName()`

restituisce solo il nome semplice.

## 5. Vedere i metodi di una classe

Con Reflection puoi vedere i metodi.

```java
import java.lang.reflect.Method;

class Persona {

    public void saluta() {
        System.out.println("Ciao");
    }

    public void cammina() {
        System.out.println("Sto camminando");
    }
}

public class Main {

    public static void main(String[] args) {

        Class<?> clazz = Persona.class;

        Method[] metodi = clazz.getDeclaredMethods();

        for (Method m : metodi) {
            System.out.println(m.getName());
        }
    }
}
```

Output:

```
saluta
cammina
```

## 6. getMethods() vs getDeclaredMethods()

Esistono due metodi importanti:

`getMethods()`

e:

`getDeclaredMethods()`

**getMethods()**

Restituisce solo i metodi public, compresi quelli ereditati.

```java
Method[] metodi = clazz.getMethods();
```

**getDeclaredMethods()**

Restituisce i metodi dichiarati nella classe, anche private, protected, package-private, ma non quelli ereditati.

```java
Method[] metodi = clazz.getDeclaredMethods();
```

Esempio:

```java
class Persona {

    private void metodoPrivato() {
    }

    public void metodoPubblico() {
    }
}
```

Con:

```java
clazz.getDeclaredMethods()
```

puoi vedere entrambi:

```
metodoPrivato
metodoPubblico
```

## 7. Vedere gli attributi/campi

Gli attributi si chiamano:

`Field`

Esempio:

```java
import java.lang.reflect.Field;

class Persona {

    private String nome;
    private int eta;
}

public class Main {

    public static void main(String[] args) {

        Class<?> clazz = Persona.class;

        Field[] campi = clazz.getDeclaredFields();

        for (Field f : campi) {
            System.out.println(f.getName() + " - " + f.getType().getSimpleName());
        }
    }
}
```

Output:

```
nome - String
eta - int
```

Qui:

`f.getName()`

restituisce il nome dell'attributo.

`f.getType()`

restituisce il tipo dell'attributo.

## 8. Vedere i costruttori

I costruttori si leggono con:

`Constructor`

Esempio:

```java
import java.lang.reflect.Constructor;

class Persona {

    public Persona() {
    }

    public Persona(String nome) {
    }

    public Persona(String nome, int eta) {
    }
}

public class Main {

    public static void main(String[] args) {

        Class<?> clazz = Persona.class;

        Constructor<?>[] costruttori = clazz.getDeclaredConstructors();

        for (Constructor<?> c : costruttori) {
            System.out.println(c);
        }
    }
}
```

Output possibile:

```
public Persona()
public Persona(java.lang.String)
public Persona(java.lang.String,int)
```

## 9. Creare un oggetto con Reflection

Normalmente fai:

```java
Persona p = new Persona();
```

Con Reflection puoi fare:

```java
Class<?> clazz = Persona.class;

Object obj = clazz.getDeclaredConstructor().newInstance();
```

Esempio completo:

```java
class Persona {

    public Persona() {
        System.out.println("Persona creata");
    }
}

public class Main {

    public static void main(String[] args) throws Exception {

        Class<?> clazz = Persona.class;

        Object obj = clazz.getDeclaredConstructor().newInstance();

        System.out.println(obj);
    }
}
```

Output:

```
Persona creata
Persona@...
```

## 10. Creare un oggetto con costruttore con parametri

Classe:

```java
class Persona {

    private String nome;

    public Persona(String nome) {
        this.nome = nome;
    }

    public void stampa() {
        System.out.println(nome);
    }
}
```

Reflection:

```java
import java.lang.reflect.Constructor;

public class Main {

    public static void main(String[] args) throws Exception {

        Class<?> clazz = Persona.class;

        Constructor<?> costruttore =
                clazz.getDeclaredConstructor(String.class);

        Object obj = costruttore.newInstance("Luca");

        Persona p = (Persona) obj;

        p.stampa();
    }
}
```

Output:

```
Luca
```

Questa riga:

```java
clazz.getDeclaredConstructor(String.class)
```

dice:

prendimi il costruttore che riceve una String.

Questa:

```java
costruttore.newInstance("Luca")
```

crea l'oggetto passando "Luca" al costruttore.

## 11. Chiamare un metodo con Reflection

Classe:

```java
class Persona {

    public void saluta() {
        System.out.println("Ciao");
    }
}
```

Reflection:

```java
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws Exception {

        Persona p = new Persona();

        Class<?> clazz = p.getClass();

        Method metodo = clazz.getDeclaredMethod("saluta");

        metodo.invoke(p);
    }
}
```

Output:

```
Ciao
```

Spiegazione:

`getDeclaredMethod("saluta")`

cerca il metodo chiamato saluta.

`metodo.invoke(p)`

esegue quel metodo sull'oggetto p.

## 12. Chiamare un metodo con parametri

Classe:

```java
class Persona {

    public void saluta(String nome) {
        System.out.println("Ciao " + nome);
    }
}
```

Reflection:

```java
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws Exception {

        Persona p = new Persona();

        Method metodo =
                Persona.class.getDeclaredMethod("saluta", String.class);

        metodo.invoke(p, "Luca");
    }
}
```

Output:

```
Ciao Luca
```

Questa parte:

```java
getDeclaredMethod("saluta", String.class)
```

cerca un metodo chiamato saluta che riceve una String.

Questa parte:

```java
metodo.invoke(p, "Luca")
```

chiama il metodo sull'oggetto p e gli passa "Luca".

## 13. Leggere un campo privato

Classe:

```java
class Persona {

    private String nome = "Luca";
}
```

Reflection:

```java
import java.lang.reflect.Field;

public class Main {

    public static void main(String[] args) throws Exception {

        Persona p = new Persona();

        Field campo = Persona.class.getDeclaredField("nome");

        campo.setAccessible(true);

        Object valore = campo.get(p);

        System.out.println(valore);
    }
}
```

Output:

```
Luca
```

Questa riga:

```java
campo.setAccessible(true);
```

dice a Java:

permettimi di accedere anche se il campo è private.

Attenzione: questa è una cosa potente ma da usare con cautela.

## 14. Modificare un campo privato

```java
import java.lang.reflect.Field;

class Persona {

    private String nome = "Luca";

    public void stampa() {
        System.out.println(nome);
    }
}

public class Main {

    public static void main(String[] args) throws Exception {

        Persona p = new Persona();

        Field campo = Persona.class.getDeclaredField("nome");

        campo.setAccessible(true);

        campo.set(p, "Marco");

        p.stampa();
    }
}
```

Output:

```
Marco
```

Hai modificato un campo private dall'esterno.

## 15. Reflection e metodi private

Classe:

```java
class Persona {

    private void segreto() {
        System.out.println("Metodo privato eseguito");
    }
}
```

Reflection:

```java
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws Exception {

        Persona p = new Persona();

        Method metodo = Persona.class.getDeclaredMethod("segreto");

        metodo.setAccessible(true);

        metodo.invoke(p);
    }
}
```

Output:

```
Metodo privato eseguito
```

Anche qui:

`setAccessible(true)`

permette di superare il modificatore private.

## 16. Perché Reflection è potente?

Perché puoi fare cose dinamiche.

Esempio:

```java
String nomeMetodo = "saluta";

Method metodo = Persona.class.getDeclaredMethod(nomeMetodo);

metodo.invoke(persona);
```

Il nome del metodo può arrivare da:

- file
- database
- input utente
- configurazione
- annotazioni
- framework

Quindi il programma può decidere cosa fare durante l'esecuzione.

## 17. Collegamento con il tuo progetto

Tu hai una mappa tipo:

```java
Map<String, GeneralProcess> pr = Map.ofEntries(
    Map.entry("base", new BaseManager()),
    Map.entry("string", new StringManager()),
    Map.entry("date", new DateManager()),
    Map.entry("enum", new EnumManager())
);
```

Con Reflection, potresti evitare di scrivere tutti i new ...Manager() manualmente.

Per esempio potresti avere una stringa:

```java
String className = "com.betacom.manager.DateManager";
```

e creare l'oggetto così:

```java
Class<?> clazz = Class.forName(className);

Object obj = clazz.getDeclaredConstructor().newInstance();

GeneralProcess process = (GeneralProcess) obj;

process.execute();
```

Questo significa:

creo un oggetto partendo dal nome della classe scritto come stringa.

## 18. Esempio simile al tuo progetto

Supponiamo di avere questa interfaccia:

```java
interface GeneralProcess {
    void execute();
}
```

Classe:

```java
class DateManager implements GeneralProcess {

    public void execute() {
        System.out.println("Eseguo DateManager");
    }
}
```

Main:

```java
public class Main {

    public static void main(String[] args) throws Exception {

        String nomeClasse = "DateManager";

        Class<?> clazz = Class.forName(nomeClasse);

        Object obj = clazz.getDeclaredConstructor().newInstance();

        GeneralProcess gp = (GeneralProcess) obj;

        gp.execute();
    }
}
```

Output:

```
Eseguo DateManager
```

Se la classe è dentro un package, devi scrivere:

```java
String nomeClasse = "com.betacom.process.DateManager";
```

non solo:

```
"DateManager"
```

## 19. Reflection per chiamare un metodo scelto da stringa

Classe:

```java
class Operazioni {

    public void add() {
        System.out.println("Aggiungo");
    }

    public void delete() {
        System.out.println("Cancello");
    }

    public void list() {
        System.out.println("Lista");
    }
}
```

Uso:

```java
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws Exception {

        Operazioni op = new Operazioni();

        String nomeMetodo = "delete";

        Method metodo = Operazioni.class.getDeclaredMethod(nomeMetodo);

        metodo.invoke(op);
    }
}
```

Output:

```
Cancello
```

Quindi invece di fare:

```java
switch (nomeMetodo) {
    case "add":
        op.add();
        break;
    case "delete":
        op.delete();
        break;
}
```

puoi chiamare il metodo dinamicamente.

Però non sempre conviene.

## 20. Quando usare Reflection?

Usa Reflection quando devi fare cose dinamiche.

Esempi buoni:

- framework
- plugin
- librerie
- serializzazione JSON
- mappare oggetti da database
- test automatici
- lettura annotazioni
- creazione dinamica di classi
- ispezione di oggetti

Per esempio Gson usa meccanismi simili per leggere i campi di una classe e trasformarli in JSON.

Quando fai:

```java
gson.toJson(oggetto);
```

Gson deve capire:

- quali campi ha l'oggetto?
- come si chiamano?
- che valore hanno?

Questo tipo di lavoro si fa anche tramite Reflection.

## 21. Quando NON usare Reflection?

Non usarla se puoi fare la stessa cosa in modo normale.

Esempio:

```java
persona.saluta();
```

è meglio di:

```java
Method metodo = Persona.class.getDeclaredMethod("saluta");
metodo.invoke(persona);
```

Reflection è più:

- lenta
- complessa
- difficile da leggere
- più fragile
- più pericolosa
- meno controllata dal compilatore

Se sbagli il nome di un metodo:

```java
getDeclaredMethod("salutaaa")
```

Java non se ne accorge durante la compilazione.

Te ne accorgi solo durante l'esecuzione.

## 22. Errori comuni con Reflection

**ClassNotFoundException**

Succede se la classe non esiste o hai scritto male il package.

```java
Class.forName("com.example.Personaa");
```

**NoSuchMethodException**

Succede se il metodo o il costruttore non esiste.

```java
clazz.getDeclaredMethod("salutaaa");
```

**NoSuchFieldException**

Succede se il campo non esiste.

```java
clazz.getDeclaredField("nomme");
```

**IllegalAccessException**

Succede se provi ad accedere a qualcosa non accessibile senza permesso.

Spesso si risolve con:

```java
setAccessible(true);
```

**InvocationTargetException**

Succede quando il metodo chiamato con Reflection lancia un'eccezione interna.

Esempio:

```java
metodo.invoke(obj);
```

Il metodo chiamato esplode, Java lo incapsula dentro InvocationTargetException.

## 23. Esempio completo molto utile

```java
import java.lang.reflect.Field;
import java.lang.reflect.Method;

class Persona {

    private String nome = "Luca";
    private int eta = 25;

    public void saluta() {
        System.out.println("Ciao, sono " + nome);
    }

    private void segreto() {
        System.out.println("Metodo segreto");
    }
}

public class Main {

    public static void main(String[] args) throws Exception {

        Persona persona = new Persona();

        Class<?> clazz = persona.getClass();

        System.out.println("Nome classe:");
        System.out.println(clazz.getSimpleName());

        System.out.println("Campi:");
        for (Field f : clazz.getDeclaredFields()) {
            System.out.println(f.getName() + " - " + f.getType().getSimpleName());
        }

        System.out.println("Metodi:");
        for (Method m : clazz.getDeclaredMethods()) {
            System.out.println(m.getName());
        }

        Field campoNome = clazz.getDeclaredField("nome");
        campoNome.setAccessible(true);
        campoNome.set(persona, "Marco");

        Method saluta = clazz.getDeclaredMethod("saluta");
        saluta.invoke(persona);

        Method segreto = clazz.getDeclaredMethod("segreto");
        segreto.setAccessible(true);
        segreto.invoke(persona);
    }
}
```

Output:

```
Nome classe:
Persona

Campi:
nome - String
eta - int

Metodi:
saluta
segreto

Ciao, sono Marco
Metodo segreto
```

## 24. Collegamento con annotazioni

Reflection si usa spesso con le annotazioni.

Esempio:

```java
@interface Test {
}
```

Classe:

```java
class MiaClasse {

    @Test
    public void prova() {
        System.out.println("Metodo di test");
    }

    public void normale() {
        System.out.println("Metodo normale");
    }
}
```

Con Reflection puoi controllare quali metodi hanno @Test.

```java
for (Method m : MiaClasse.class.getDeclaredMethods()) {
    if (m.isAnnotationPresent(Test.class)) {
        m.invoke(new MiaClasse());
    }
}
```

Questo è il concetto dietro framework di test come JUnit.

## 25. Reflection e modificatori

Puoi vedere se un campo/metodo è:

- public
- private
- protected
- static
- final
- abstract

Esempio:

```java
import java.lang.reflect.Modifier;

class Persona {

    private static final String SPECIE = "Umana";
}
```

Reflection:

```java
Field campo = Persona.class.getDeclaredField("SPECIE");

int modificatori = campo.getModifiers();

System.out.println(Modifier.isPrivate(modificatori));
System.out.println(Modifier.isStatic(modificatori));
System.out.println(Modifier.isFinal(modificatori));
```

Output:

```
true
true
true
```

## 26. Reflection nel tuo caso: alternativa alla mappa

Tu hai una logica tipo:

```
"date" -> new DateManager()
"enum" -> new EnumManager()
"stream" -> new StreamManager()
```

Con Reflection potresti fare una cosa del genere:

```java
public GeneralProcess creaProcesso(String nomeClasse) throws Exception {

    String packageBase = "com.betacom.process.";

    String nomeCompleto = packageBase + nomeClasse;

    Class<?> clazz = Class.forName(nomeCompleto);

    Object obj = clazz.getDeclaredConstructor().newInstance();

    return (GeneralProcess) obj;
}
```

Uso:

```java
GeneralProcess p = creaProcesso("DateManager");

p.execute();
```

Però attenzione: finché stai studiando, la mappa è più chiara.

La Reflection ha senso quando vuoi un sistema più dinamico, tipo:

- leggo da file quale classe caricare
- aggiungo plugin senza modificare troppo il main
- creo oggetti in base a configurazioni

## 27. Differenza tra Reflection e codice normale

Codice normale:

```java
DateManager dm = new DateManager();

dm.execute();
```

Reflection:

```java
Class<?> clazz = Class.forName("com.betacom.process.DateManager");

Object obj = clazz.getDeclaredConstructor().newInstance();

GeneralProcess dm = (GeneralProcess) obj;

dm.execute();
```

Il primo è più semplice.

Il secondo è più dinamico.

## 28. Regola pratica

Usa codice normale quando sai già quali classi usare.

Usa Reflection quando vuoi scoprire o creare classi/metodi a runtime.

In pratica:

- Programma semplice -> niente Reflection
- Framework/libreria dinamica -> Reflection
- Sistema plugin/configurabile -> Reflection

## 29. Riassunto secco

Reflection significa:

guardare e usare classi, metodi, campi e costruttori durante l'esecuzione.

Classe principale:

`Class<?>`

Metodi importanti:

```java
getDeclaredFields()
getDeclaredMethods()
getDeclaredConstructors()
getDeclaredField("nome")
getDeclaredMethod("nomeMetodo")
getDeclaredConstructor(...)
```

Per creare oggetti:

```java
clazz.getDeclaredConstructor().newInstance()
```

Per chiamare metodi:

```java
metodo.invoke(oggetto)
```

Per leggere/modificare campi:

```java
campo.get(oggetto)
campo.set(oggetto, valore)
```

Per accedere a private:

```java
setAccessible(true)
```

Reflection è potente, ma va usata con criterio. Per il tuo progetto, prima capiscila bene; poi puoi usarla per creare dinamicamente i tuoi Manager o Process partendo dal nome della classe.
