import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ThreadTutorial {

    public static final ExecutorService service = Executors.newFixedThreadPool(10);
    public static final HttpClient httpClient = HttpClient.newBuilder().build();

    public static void main(String args[]) throws InterruptedException {
        // simpleThread(); // 100 - 1st sec but 100 threads
        blockingFixedThreadPool(); // 10 - 1st  sec, 10 - 2nd sec, 10 - 3rd sec ..... but 10 threads
        // nonBlockingFixedThreadPool(); // 100 - 1st sec but only 10 threads
    }

    public static void simpleThread() {
        for (int i = 0; i < 100; i++) {
            Thread worker = new Thread(() -> call());
            worker.start();
        }
    }

    public static void blockingFixedThreadPool() {
        List<CompletableFuture<String>> futures = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            futures.add(CompletableFuture.supplyAsync(() -> {
                call();
                return null;
            }, service));
        }

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        allFutures.join();
    }

    public static void nonBlockingFixedThreadPool() {
        List<CompletionStage<String>> futures = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            futures.add(callAsync());
        }

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        allFutures.join();
    }

    public static void call() {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:3000/employees"))
                .timeout(Duration.ofSeconds(5))
                .GET()
                .build();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            Date date = new Date();
            System.out.println("[" + date.toString() + "] [" + Thread.currentThread().getName() + "]    " + httpResponse.statusCode());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static CompletionStage<String> callAsync() {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:3000/employees"))
                .timeout(Duration.ofSeconds(5))
                .GET()
                .build();
            return httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(httpResponse -> {
                    Date date = new Date();
                    System.out.println("[" + date.toString() + "] [" + Thread.currentThread().getName() + "]    " + httpResponse.statusCode());
                    return httpResponse.body();
                });
    }
}