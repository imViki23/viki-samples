import java.time.Duration;
import java.util.concurrent.atomic.AtomicBoolean;

public class InfiniteRunner {

    private static AtomicBoolean running = new AtomicBoolean(true);

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        runtime.addShutdownHook(new Thread(() -> {
            System.out.println("Shutting Down 👋");
            running.set(false);
        }));

        while (running.get()) {
            System.out.println("Running 🏃‍♀️🏃‍♀️");
            
            long maxHeapMemory = runtime.maxMemory() / (1024 * 1024);
            long totalMemory = runtime.totalMemory() / (1024 * 1024);
            long freeMemory = runtime.freeMemory() / (1024 * 1024);
            long usedMemory = totalMemory - freeMemory;
            System.out.println("Heap (" + maxHeapMemory + "MB): " + usedMemory + "MB / " + totalMemory + "MB");

            try {
                Thread.sleep(Duration.ofSeconds(2));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
