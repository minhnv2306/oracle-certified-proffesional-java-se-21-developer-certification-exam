import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureCallableExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Callable<String> callableTask = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " is executing the task.");
            Thread.sleep(2000); // Simulate a long-running task
            return "Result from " + threadName;
        };

        Future<String> futureResult = executorService.submit(callableTask);
        String result = futureResult.get(); // This will block until the result is available

        System.out.println("Received: " + result);
        executorService.shutdown();
    }
}
