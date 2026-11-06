import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

// java -Xms50M -Xmx50M -XX:+HeapDumpOnOutOfMemoryError HeapOverload.java
public class HeapOverload {

    private static AtomicBoolean running = new AtomicBoolean(true);

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        runtime.addShutdownHook(new Thread(() -> {
            System.out.println("Shutting Down 👋");
            running.set(false);
        }));

        List<User> data = new ArrayList<>();

        while (running.get()) {

            for (int i = 0; i < 100000; i++) {
                data.add(new User());
            }

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

    public static class User {
        public String name = "MyTestName";
        public Integer age = 5;
    }
}
