import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicVariableExample {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicCounter = new AtomicInteger(0);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + " is incrementing the counter.");
                int newValue = atomicCounter.incrementAndGet();
                System.out.println("Counter value: " + newValue);
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("Final Counter Value: " + atomicCounter.get());
    }
}
