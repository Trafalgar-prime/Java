import java.util.Scanner;
import com.example.RoundStuff.RoundStuffCalc;

void main2() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the radius of the circle: ");
    double radius = sc.nextDouble(); //nextdouble serve per ottenere un double dall'input

    IO.println("Calcolo l'area di un cerchio con raggio: " +  radius);
    double area = RoundStuffCalc.area(radius);
    IO.println("e l'area di: " +  area + " " + RoundStuffCalc.UNIT_MEASURE + " quadrati");

    sc.close(); //ricordiamo di chiuede una volta finito
}

void main() { //devo mettere il main per compilare e non main2

    double radius = Double.parseDouble(IO.readln("Inserisci un raggio: ")); // passo da stringa a double
    //double area = RoundStuffCalc.volume(radius); //cosi ho bisogno di static perchè non genero un elemento
    RoundStuffCalc cose = new RoundStuffCalc(); // cosi non mi serve lo static perche genero cose
    double area = cose.volume(radius);
    IO.println("Il volume è di : " + area + " " + RoundStuffCalc.UNIT_MEASURE + " cubici");

}
