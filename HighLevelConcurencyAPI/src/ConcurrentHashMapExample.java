import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentHashMapExample {
    public static void main(String[] args) {
        // Sử dụng ConcurrentHashMap để lưu trữ kết quả từ các tác vụ chạy song song, không lo race condition
        Map<String, String> taskResult = new ConcurrentHashMap<>();
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 3; i++) {
            String taskName = "Task-" + i;
            executor.submit(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + " started " + taskName);

                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                taskResult.put(taskName, "Completed by: " + threadName);
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Task Results: ");
        taskResult.forEach((task, result) -> System.out.println(task + " -> " + result));
    }
}
