package operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MatricesMultiplier implements Runnable {
    private final static Logger log = LoggerFactory.getLogger(MatricesMultiplier.class);

    private double[][] result;
    private double[][] matrix1;
    private double[][] matrix2;
    private final int row;

    public MatricesMultiplier(double[][] result, double[][] matrix1, double[][] matrix2, int row) {
        this.result = result;
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.row = row;
        log.debug(String.format("Multiplying matrixes: %dx%d and %dx%d", matrix1.length, matrix1[0].length, matrix2.length, matrix2[0].length));

    }

    @Override
    public void run() {
        // працює також якщо порядки різні
        log.debug("Multiplying row: " + row);
        for (int i = 0; i < matrix2[0].length; i++) {
            result[row][i] = 0;
            for (int j = 0; j < matrix1[row].length; j++) {
                result[row][i] += matrix1[row][j] * matrix2[j][i];
            }
        }
    }

}
