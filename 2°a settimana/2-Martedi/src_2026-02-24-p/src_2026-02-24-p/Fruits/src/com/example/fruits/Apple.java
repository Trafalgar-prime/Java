package com.example.fruits;

public class Apple {
    public int volume = 0;
    public String color = "green";

    public void grow(int increment) {
        volume += increment;
    };

    public String fall() {
        return "Cadooo!";
    }

    public void checkColor() {
        if (color.equals("red")) {
            IO.println("Questa è una mela rossa");
        } else if (color.equals("green")) {
            IO.println("Questa è una mela verde");
        } else {
            IO.println("Questa mela ha un colore insolito: " + color);
        }
    }

    public void describeSize() {
        String size = (volume >= 10) ? "grande" : "piccola";
        IO.println("Questa mela è " + size);
    }

    public void describeApple() {
        switch (color.toLowerCase()) {
            case "red":
                IO.println("Questa è una mela rossa, tipicamente dolce e croccante.");
            break;
            case "green":
                IO.println("Questa è una mela verde, solitamente asprigna e fresca.");
            break;
            case "yellow":
                IO.println("Questa è una mela gialla, spesso molto succosa.");
            break;
            default:
                IO.println("Questa mela ha un colore insolito: " + color);
        }
    }

    public void mature() {
        int maxVolume = 20;
        IO.println("Inizio maturazione. Volume attuale: " + volume);

        while (maxVolume > volume) {
            grow(2);
            IO.println("La mela sta maturando. Volume attuale: " + volume);
        }

        IO.println("Maturazione completata! Volume finale: " + volume);
    }

    public void eat() {
        int minVolume = 2;
        IO.println("Inizio a mangiare la mela. Volume attuale: " + volume);

        do {
            volume -= 3;
            if (volume > 0) {
                IO.println("Ho dato un morso. Volume attuale: " + volume);
            } else  {
                IO.println("La mela è finita!");
            }
        } while (volume > minVolume);

        IO.println("Ho finito di mangiare la mela.");
    }

    @Override
    public String toString() {
        return "Mela - Colore: " + color + ", Volume: " + volume;
    }
}
