package operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MatrixNumberMultiply implements Runnable {
    private final static Logger log = LoggerFactory.getLogger(MatrixNumberMultiply.class);
    private final double[][] matrix;
    private final double[][] result;
    private final double number;
    private final int row;

    public MatrixNumberMultiply(double[][] result, double number, double[][] matrix, int row) {
        this.result = result;
        this.number = number;
        this.matrix = matrix;
        this.row = row;
//        log.debug(String.format("Multiplying matrix: %dx%d and number %.2f", matrix.length, matrix[0].length, number));
    }

    @Override
    public void run() {
        for(int j=0; j < matrix[row].length; j++){
            result[row][j] = matrix[row][j] * number;
        }
    }
}

