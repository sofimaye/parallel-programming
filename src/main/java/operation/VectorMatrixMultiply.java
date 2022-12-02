package operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VectorMatrixMultiply implements Runnable {
    private final static Logger log = LoggerFactory.getLogger(VectorMatrixMultiply.class);

    private final double[] result;
    private double[] vector;
    private double[][] matrix;
    private final int row;

    public VectorMatrixMultiply(double[] result, double[] vector, double[][] matrix, int row) {
        this.result = result;
        this.vector = vector;
        this.matrix = matrix;
        this.row = row;
        log.debug(String.format("Multiplying matrix: %dx%d and vector %d", matrix.length, matrix[0].length, vector.length));
    }

    @Override
    public void run() {
        log.debug("Multiplying row: " + row);
//        for (int i = 0; i < matrix[0].length; i++) {
            var sum = 0;
            for (int j = 0; j < vector.length; j++) {
                sum += vector[j] * matrix[j][row];
            }
            result[row] = sum;
//        }
    }

}
