package task2;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        MyThread thread1 = new MyThread(lock);
        MyThread thread2 = new MyThread(lock);

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}
