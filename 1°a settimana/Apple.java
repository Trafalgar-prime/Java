public class Apple{
    int volume = 0;
    String color = "Rossa";

    public void grow(int increment){
        volume += increment;
    }

    public String fall(){
        return "Caduta";   // il return è obbligatorio nelle funzioni non void
    }

    public static void main(String[] args) {  // senza questo il codice non funziona perchè non runna niente; ma deve trovarsi nella classe public altrimenti non va
        for (int i = 0; i < args.length; i++){
        System.out.println("Stampo gli argomenti [" + i + "] :" + args[i] + "\n");
        }

        Apple apple = new Apple();  //così genero l'ggetto mela ripreso dalla classe mela
        int volume = apple.volume;
        System.out.println("Volume: " + volume);

        apple.grow(10);
        volume = apple.volume;
        System.out.println("Volume: " + volume);
        System.out.println("Volume: " + apple.volume);
        apple.grow(20);
        System.out.println("Volume: " + apple.volume);

        if (apple.volume >= 25){
            String appleMesssage = apple.fall(); //devo necessariamente salvarlo su un altra variabile
            System.out.println(appleMesssage);
        }
        System.out.println("Volume: " + apple.volume);
    }
}

