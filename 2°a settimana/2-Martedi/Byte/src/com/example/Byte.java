package com.example;

public class Byte {
    static void main() {
        int vol1 = 10; //1010 00001010
        int vol2 = 12; //1100

        //AND &
        int result = vol1 & vol2;
        System.out.println(result); //1000

        //OR |
        int result1 = vol1 | vol2;
        System.out.println(result1); //1110

        //XOR ^
        int result2 = vol1 ^ vol2;
        System.out.println(result2); //0110

        //NOT ~
        int result3 = ~vol1;
        System.out.println(result3); //11110101
    }
}
