package task1;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        System.out.println(myThread.getName() + " " + myThread.getState());

        myThread.start();

        Thread.sleep(300);
        System.out.println(myThread.getName() + " " + myThread.getState());

        Thread.sleep(500);
        myThread.waitAndBlock();

        Thread.sleep(500);
        System.out.println(myThread.getName() + " " + myThread.getState());
    }
}
