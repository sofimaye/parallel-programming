package executor;


public interface ParallelExecutor {
    void runInParallel(int rows, WorkerCreator creator);
}
