public class Main {
    static void main() {
        int points = Integer.valueOf(IO.readln("Dimmi quanti punti hai: "));

        if (points == 0){
            IO.println("Ti consigliamo di sottoscrivere la tessera");
        } else if (points == 10) {
            IO.println("Benvenuto sei un nuovo cliente");
        } else if (points > 10 && points <= 14) {
            IO.println("Sei un cliente junior");
        }else if (points >= 15 && points <= 40) {
            IO.println("Sei un cliente super fedele");
        }else{
          IO.println("Raccolta punti in corso...");
        }
    }
}