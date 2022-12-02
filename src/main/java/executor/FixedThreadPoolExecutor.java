package executor;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FixedThreadPoolExecutor implements ParallelExecutor {
    private final int parallelism;

    public FixedThreadPoolExecutor(int parallelism) {
        this.parallelism = parallelism;
    }

    @Override
    public void runInParallel(int rows, WorkerCreator creator) {
        var executor = Executors.newFixedThreadPool(parallelism);
        var futures = new ArrayList<Future<?>>();
        for (int i = 0; i < rows; i++) {
            var task = creator.createForRow(i);
            futures.add(executor.submit(task));
        }
        for (var future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        executor.shutdown();
    }
}
