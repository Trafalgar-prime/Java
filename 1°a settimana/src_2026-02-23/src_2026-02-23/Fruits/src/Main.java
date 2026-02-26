import com.example.fruits.Apple;

void main() {
//    xor();
    eggs();
}

void xor() {
    Apple apple1 = new Apple();
    Apple apple2 = new Apple();

    boolean isDifferent = apple1.volume == apple2.volume ^ apple1.color.equals(apple2.color);

    IO.println(isDifferent);

    apple1.volume = 10;
    apple2.volume = 15;

    isDifferent = apple1.volume == apple2.volume ^ apple1.color.equals(apple2.color);

    IO.println(isDifferent);
}

void eggs() {
    double budget = .86;
    double eggPrice = 0.09;
    int eggNumber;

    eggNumber = (int) (budget / eggPrice);

    IO.println("Con 89 cent compro " + eggNumber + " uova.");
}
