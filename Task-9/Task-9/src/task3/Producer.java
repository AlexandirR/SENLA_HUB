package task3;

import java.util.Random;

public class Producer extends Thread {

    private MyBuffer buffer;

    public Producer(MyBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Integer element = new Random().nextInt(100);
                System.out.println("Producer add: " + buffer.addInt(element));
            } catch (SizeException e) {
                System.out.println("Producer wait...");
            } finally {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
