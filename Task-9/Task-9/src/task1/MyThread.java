package task1;

public class MyThread extends Thread {

    public MyThread () {
        System.out.println(Thread.currentThread().getState());
    }

    @Override
    public void run() {
        synchronized (this) {
            System.out.println(this.getName() + " " + this.getState());
            try {
                this.wait(500);

                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized void waitAndBlock() {
        System.out.println(this.getName() + " " + this.getState());
        this.notifyAll();
        System.out.println(this.getName() + " " + this.getState());
    }
}
