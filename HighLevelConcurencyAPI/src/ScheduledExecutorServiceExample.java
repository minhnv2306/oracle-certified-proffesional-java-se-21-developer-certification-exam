

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import concurrencydesignpatterns.Task;

public class ScheduledExecutorServiceExample {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);

        for (int i = 1; i <= 5; i++) {
            Task task = new Task(i);
            scheduledExecutorService.schedule(task, i * 2L, TimeUnit.SECONDS);
        }

        scheduledExecutorService.scheduleAtFixedRate(new Task(0), 1, 5, TimeUnit.SECONDS);

        scheduledExecutorService.scheduleWithFixedDelay(new Task(-1), 2, 5, TimeUnit.SECONDS);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        scheduledExecutorService.shutdown();

        System.out.println("ScheduledExecutorService shutdown.");
    }
}
