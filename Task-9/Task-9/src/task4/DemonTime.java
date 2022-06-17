package task4;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DemonTime extends Thread {

    private int timeSleep;
    private LocalDateTime time;

    public DemonTime(int timeSleep) {
        this.timeSleep = timeSleep;
    }

    @Override
    public void run() {
        while (true) {
            time = LocalDateTime.now();
            System.out.println(time);
            try {
                Thread.sleep(1000 * timeSleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
