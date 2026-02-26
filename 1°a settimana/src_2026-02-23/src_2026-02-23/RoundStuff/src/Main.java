import com.example.roundstuff.RoundStuffCalc;

void main2() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Inserisci un raggio: ");
    double radius = sc.nextDouble();

    System.out.println(
            "Un cerchio di raggio "
                    + radius
                    + " ha un'area pari a "
                    + RoundStuffCalc.area(radius) + RoundStuffCalc.UNIT_MEASURE + " quadrati"
    );

    sc.close();
}

void main() {
    double radius = Double.parseDouble(IO.readln("Inserisci un raggio: "));
    IO.println("Una sfera di raggio: "
            + radius
            + " ha un volume pari a "
            + RoundStuffCalc.volume(radius) + RoundStuffCalc.UNIT_MEASURE + " cubici");
}