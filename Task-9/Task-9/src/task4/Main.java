package task4;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int timeSleep = scanner.nextInt();
        DemonTime demonTime = new DemonTime(timeSleep);
        demonTime.setDaemon(true);
        demonTime.start();
        System.out.println("Print something to stop");
        String command = scanner.next();
        System.out.println("END");
    }
}
