import com.example.fruits.Apple;

void main() {
//    xor();
//    eggs();
//    checkColor();
//    ternary();
//    describe();
//    mature();
//    eat();
    picking();
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

void checkColor() {
    Apple apple1 = new Apple();
    Apple apple2 = new Apple();
    Apple apple3 = new Apple();
    apple1.color = "red";
    apple2.color = "green";
    apple3.color = "blue";

    apple1.checkColor();
    apple2.checkColor();
    apple3.checkColor();
}

void ternary() {
    Apple apple1 = new Apple();
    Apple apple2 = new Apple();
    apple1.volume = 5;
    apple2.volume = 15;
    apple1.describeSize();
    apple2.describeSize();
}

void describe() {
    Apple apple1 = new Apple();
    Apple apple2 = new Apple();
    Apple apple3 = new Apple();
    Apple apple4 = new Apple();
    apple1.color = "red";
    apple2.color = "green";
    apple3.color = "blue";
    apple4.color = "yellow";

    apple1.describeApple();
    apple2.describeApple();
    apple3.describeApple();
    apple4.describeApple();
}

void mature() {
    Apple apple1 = new Apple();
    apple1.mature();
}

void eat() {
    Apple apple1 = new Apple();
    apple1.mature();
    apple1.eat();
}

void picking() {
    String[] colors = {"red", "green", "yellow"};
    Apple[] apples = new Apple[10];

    for (int i = 0; i < apples.length; i++) {
        int color = new Random().nextInt(3);
        int volume = new Random().nextInt(20) + 1;
        apples[i] = new Apple();
        apples[i].color = colors[color];
        apples[i].volume = volume;
    }

    IO.println("Raccolta delle mele:");

    for (Apple apple : apples) {
        IO.println(apple);
    }
}