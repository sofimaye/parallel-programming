package operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MatrixNumberMultiply implements Runnable {
    private final static Logger log = LoggerFactory.getLogger(FindSmallestNum.class);
    private final double[][] matrix;
    private final double[][] result;
    private double[] number;
    private final int row;

    public MatrixNumberMultiply(double[][] result, double[] number, double[][] matrix, int row) {
        this.result = result;
        this.number = number;
        this.matrix = matrix;
        this.row = row;
    }

    @Override
    public void run() {
        log.debug("Multiplying MCME*minD: " + row);
        for(int j=0; j < matrix.length; j++){
            result[row][j] = matrix[row][j] * number[0];
        }
    }
}

