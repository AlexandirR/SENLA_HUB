package task3;

public class Consumer extends Thread {

    private MyBuffer buffer;

    public Consumer(MyBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Consumer delete: " + buffer.deleteInt());
            } catch (SizeException e) {
                System.out.println("Consumer wait...");
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
