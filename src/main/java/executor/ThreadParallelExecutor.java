package executor;
import java.util.ArrayList;
import java.util.List;


//для кожного завдання створюється новий потік
//дуже затратно

public class ThreadParallelExecutor implements ParallelExecutor {
    private final int parallelism;

    public ThreadParallelExecutor(int parallelism) {
        this.parallelism = parallelism;
    }

    @Override
    public void runInParallel(int rows, WorkerCreator creator) {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            var task = creator.createForRow(i);
            var thread = new Thread(task);
            thread.start();
            threads.add(thread);
            if (threads.size() % parallelism == 0) {
                waitForThreads(threads);
            }
        }
        waitForThreads(threads);
    }

    private void waitForThreads(List<Thread> threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        threads.clear();
    }
}
