# Classi anonime in Java

1. COME NASCE NORMALMENTE UN OGGETTO

Supponiamo:

class Persona {

    public void saluta() {
        System.out.println("ciao");
    }
}

Poi:

Persona p = new Persona();

Cosa succede?

STEP INTERNI

Java:

trova la classe Persona
crea un oggetto in memoria
mette dentro l’oggetto:
variabili
metodi
restituisce il riferimento
2. MA COSA SUCCEDE SE VOGLIO CAMBIARE UN METODO SOLO UNA VOLTA?

Esempio:

Persona p = new Persona();
p.saluta();

output:

ciao

Ma magari TU vuoi:

CIAO DA LORENZO

senza creare:

class PersonaSpeciale extends Persona

solo per una riga di codice.

Ed ecco perché esistono le classi anonime.

3. COSA FA DAVVERO UNA CLASSE ANONIMA

Quando scrivi:

Persona p = new Persona() {

    @Override
    public void saluta() {
        System.out.println("CIAO DA LORENZO");
    }
};

Java NON sta modificando Persona.

Sta creando:

UNA NUOVA SOTTOCLASSE TEMPORANEA

internamente.

Come se scrivesse:

class ClasseTemporanea extends Persona {

    @Override
    public void saluta() {
        System.out.println("CIAO DA LORENZO");
    }
}

e poi:

Persona p = new ClasseTemporanea();

MA:

la classe non ha nome
esiste solo lì
Java la genera automaticamente
4. LA COSA PIÙ IMPORTANTE DA CAPIRE

Questa parte:

new Persona()

NORMALMENTE crea un oggetto.

MA:

quando aggiungi:

{
   ...
}

NON stai più solo creando un oggetto.

Stai dicendo:

“prima crea una sottoclasse nuova di Persona, poi crea l’oggetto”

5. DIFFERENZA VISIVA
Oggetto normale
new Persona();

Java usa:

class Persona

già esistente.

6. CLASSE ANONIMA
new Persona() {

}

Java crea:

class Qualcosa extends Persona {

}

automaticamente.

7. QUINDI UNA CLASSE ANONIMA È:
una sottoclasse nascosta
generata automaticamente
usata una sola volta
senza nome
8. PERCHE SI USA?

Per evitare:

class MioListener extends ActionListener

oppure:

class MioThread extends Thread

quando il codice serve una sola volta.

9. ESEMPIO MENTALE REALE

Immagina:

class Cane {

    public void abbaia() {
        System.out.println("bau");
    }
}
Uso normale
Cane c = new Cane();
c.abbaia();

Output:

bau
10. ORA VUOI UN CANE SPECIALE SOLO UNA VOLTA

Tu NON vuoi creare:

class SuperCane extends Cane

perché ti serve una volta sola.

Allora fai:

Cane c = new Cane() {

    @Override
    public void abbaia() {
        System.out.println("BAU POTENTE");
    }
};
11. COSA CREA JAVA INTERNAMENTE?

Praticamente:

class Cane$1 extends Cane {

    @Override
    public void abbaia() {
        System.out.println("BAU POTENTE");
    }
}

Poi:

Cane c = new Cane$1();
12. PERCHE IL TIPO È ANCORA "Cane"?

Qui:

Cane c =

la variabile è di tipo Cane.

MA l’oggetto vero è:

Cane$1

cioè la sottoclasse anonima.

13. POLIMORFISMO

Questo è importantissimo.

Quando fai:

c.abbaia();

Java usa il metodo dell’oggetto reale.

NON della variabile.

Quindi esegue:

BAU POTENTE
14. ESEMPIO CON INTERFACCIA

Qui si capisce ancora meglio.

Interfaccia
interface Animale {

    void verso();
}
NON PUOI FARE
new Animale();

ERRORE.

Perché le interfacce non hanno implementazione.

15. ALLORA COSA FA JAVA?

Quando scrivi:

Animale a = new Animale() {

    @Override
    public void verso() {
        System.out.println("miao");
    }
};

Java crea internamente:

class Animale$1 implements Animale {

    @Override
    public void verso() {
        System.out.println("miao");
    }
}

Poi:

Animale a = new Animale$1();
16. ECCO PERCHÉ FUNZIONA

Perché NON stai creando un’interfaccia.

Stai creando UNA CLASSE CHE IMPLEMENTA L’INTERFACCIA.

17. QUESTA È LA COSA CHE CONFONDE TUTTI

Molti pensano:

new Runnable()

stia creando un Runnable.

NO.

Sta creando:

una classe anonima che implementa Runnable
18. ESEMPIO THREAD
Runnable r = new Runnable() {

    @Override
    public void run() {
        System.out.println("thread");
    }
};

Internamente:

class Runnable$1 implements Runnable {

    @Override
    public void run() {
        System.out.println("thread");
    }
}
19. PERCHE SI USA TANTISSIMO IN SWING?

Perché gli eventi servono una volta sola.

SENZA CLASSE ANONIMA
class MioListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("click");
    }
}

Poi:

btn.addActionListener(new MioListener());
20. CON CLASSE ANONIMA
btn.addActionListener(new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("click");
    }
});

Più corto.

21. LE CLASSI ANONIME POSSONO AVERE:
Variabili
new Cane() {

    int eta = 5;
}
Metodi
new Cane() {

    public void test() {

    }
}
Override
@Override
public void abbaia()
22. MA ATTENZIONE

Questi metodi:

public void test()

NON li puoi chiamare così:

c.test();

Perché c è di tipo Cane.

23. ESEMPIO IMPORTANTISSIMO
Cane c = new Cane() {

    public void test() {
        System.out.println("test");
    }
};

Poi:

c.test();

ERRORE.

24. PERCHE?

Perché Java guarda il tipo della variabile:

Cane c

e Cane NON ha test().

25. MA IL METODO ESISTE?

Sì.

Dentro la classe anonima.

Solo che il riferimento è tipizzato come Cane.

26. DIFFERENZA TRA:
QUESTO
new Cane();

e

QUESTO
new Cane() {

}

è ENORME.

27. NEL PRIMO CASO

usi una classe esistente.

28. NEL SECONDO CASO

CREI una nuova sottoclasse.

29. LE CLASSI ANONIME HANNO UN FILE .CLASS?

Sì.

Java genera file tipo:

Main$1.class
Main$2.class
30. CLASSI ANONIME VS LAMBDA

Dal Java 8 spesso si usa:

Runnable r = () -> System.out.println("ciao");

al posto di:

Runnable r = new Runnable() {

    @Override
    public void run() {
        System.out.println("ciao");
    }
};
31. MA NON SONO IDENTICHE

La lambda:

NON crea veramente una classe come le anonime
è più leggera
funziona solo con interfacce funzionali
32. LE CLASSI ANONIME POSSONO:
Operazione	Classe anonima
estendere classe	sì
implementare interfaccia	sì
avere costruttore	no
avere nome	no
avere campi	sì
override metodi	sì
33. RIASSUNTO TECNICO VERO

Quando scrivi:

new Tipo() {

}

Java:

crea una sottoclasse nascosta
la compila automaticamente
crea un oggetto di quella sottoclasse
restituisce il riferimento come tipo Tipo
34. DEFINIZIONE PERFETTA

Una classe anonima è:

una sottoclasse o implementazione temporanea, senza nome, creata direttamente nel punto in cui serve per ridefinire comportamento o implementare metodi senza creare una classe separata.