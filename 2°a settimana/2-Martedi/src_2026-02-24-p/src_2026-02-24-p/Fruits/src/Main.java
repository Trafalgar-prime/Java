import com.example.fruits.Apple;

void main() {
//    xor();
//    eggs();
//    checkColor();
//    ternary();
//    describe();
//    mature();
//    eat();
//    picking();
    bitwise();
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

void bitwise() {
    int vol1 = 10; // 1010 bin
    int vol2 = 12; // 1100

    // AND &
    int andResult = vol1 & vol2; // 1010 & 1100 -> 1000
    IO.println("AND: " + andResult); // 8

    // OR |
    int orResult = vol1 | vol2; // 1010 | 1100 -> 1110
    IO.println("OR: " + orResult); // 14

    // XOR ^
    int xorResult = vol1 ^ vol2; // 1010 ^ 1100 -> 0110
    IO.println("XOR: " + xorResult); // 6

    // NOT ~
    int notResult = ~vol1; // 00001010 -> 11110101
    IO.println("NOT: " + notResult); // -11

    // <<
    int leftResult = vol1 << 1; // 1010 -> 10100
    IO.println("LEFT shift: " + leftResult); // 20

    // >>
    int rightResult = vol1 >> 1; // 00001010 -> 101
    IO.println("RIGHT shift: " + rightResult); // 5

    // >>>
    int unsignedRightResult = vol1 >>> 1; // 1010 -> 101
    IO.println("UNSIGNED right shift: " + unsignedRightResult); //
}