package operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SumOfMatrices implements Runnable {
    private final static Logger log = LoggerFactory.getLogger(SumOfMatrices.class);

    private final double[][] result;
    private double[][] matrix1;
    private double[][] matrix2;
    private final int row;

    public SumOfMatrices(double[][] result, double[][] matrix1, double[][] matrix2, int row) {
        this.result = result;
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.row = row;
        log.debug(String.format("Summing matrixes: %dx%d and %dx%d", matrix1.length, matrix1[0].length, matrix2.length, matrix2[0].length));
    }

    @Override
    public void run() {
        log.debug("Adding row of matrices: " + row);
        for (int j = 0; j < result[row].length; j++) {
            result[row][j] = matrix1[row][j] + matrix2[row][j];
        }
    }

}

