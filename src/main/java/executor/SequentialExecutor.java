package executor;

public class SequentialExecutor implements ParallelExecutor {
    @Override
    public void runInParallel(int rows, WorkerCreator creator) {
        for (int i = 0; i < rows; i++) {
            var task = creator.createForRow(i);
            task.run();
        }
    }
}
