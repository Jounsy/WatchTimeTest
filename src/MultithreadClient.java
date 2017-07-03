import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Work-TESTER on 03.07.2017.
 */
public class MultithreadClient {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //int cpu = Runtime.getRuntime().availableProcessors() + 1; //Оптимальное количество потоков если нет блокировок wait
        int cpu = 2 * Runtime.getRuntime().availableProcessors() + 1; //Оптимальное количество с блокировками
        ExecutorService threadPool = Executors.newFixedThreadPool(cpu);
        Counter counter = new Counter();

        long start = System.nanoTime();

        List<Future<Double>> futures = new ArrayList<>();
        for (int i = 0; i < 400; i++) {
            final int j = i;
            futures.add(
                    CompletableFuture.supplyAsync(
                            () -> counter.count(j),
                            threadPool
                    ));
        }

        double value = 0;
        for (Future<Double> future : futures) {
            value += future.get();
        }

        System.out.println(String.format("Executed by %d s, value : %f",
                (System.nanoTime() - start) / (1000_000_000),
                value));

        threadPool.shutdown();
    }
}
