package executor;

public interface WorkerCreator {
    Runnable createForRow(int row);
}
