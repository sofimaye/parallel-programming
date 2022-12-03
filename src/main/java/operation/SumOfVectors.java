package operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SumOfVectors implements Runnable {
    private final static Logger log = LoggerFactory.getLogger(SumOfVectors.class);
    private final double[] vector;
    private final double[] vector1;
    private final double[] vector2;
    private final int row;

    public SumOfVectors(double[] vector, double[] vector1, double[] vector2, int row) {
        this.vector = vector;
        this.vector1 = vector1;
        this.vector2 = vector2;
        this.row = row;
    }

    @Override
    public void run() {
//        log.debug("Adding every num: " + row);
        vector[row] = vector1[row] + vector2[row];
    }
}
