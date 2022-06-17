package number1;

import java.util.Random;

public class Point2 {

    public static void main(String[] args) {
        int sumDigits = 0;
        for (int i = 0; i < 3; ++i) {
            int number = new Random().nextInt(899) + 100;
            sumDigits += number / 100;
            System.out.println(number);
        }
        System.out.println("sum of first digit: " + sumDigits);
    }
}
