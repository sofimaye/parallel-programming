package operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FindSmallestNum implements Runnable {
    private final static Logger log = LoggerFactory.getLogger(FindSmallestNum.class);
    private final double[] vector;
    private double[] number;
    private final int row;

    public FindSmallestNum(double[] number, double[] vector, int row) {
        this.number = number;
        this.vector = vector;
        this.row = row;
    }

    @Override
    public void run() {
//        log.debug("Looking for the smallest num in vector: " + row);
        number[0] = vector[0];
        if (number[0] > vector[row]) {
            number[0] = vector[row];
        }
    }
}



