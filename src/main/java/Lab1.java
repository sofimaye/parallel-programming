import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;
//lab1
//створення потоків з використанням anonymous Runnable(синхронізація потоків за допомогою Synchronized)
public class Lab1 {
    private final static Logger log = LoggerFactory.getLogger(Lab1.class);
    public static void main(String[] args) {
        var vectorB = RandomData.vectorB;
        var matrixMC = RandomData.matrixMC;
        var vectorD = RandomData.vectorD;
        var matrixMT = RandomData.matrixMT;
        var matrixME = RandomData.matrixME;
        var matrixMZ = RandomData.matrixMZ;

        Runnable vectorA = new Runnable() {
            @Override
            public void run() {
                long time = System.currentTimeMillis();
                var vectorBMC = RandomData.product(vectorB, matrixMC);
                var vectorDMT = RandomData.product(vectorD, matrixMT);
                var vectorA = RandomData.sum(vectorBMC, vectorDMT);
                if(vectorA.length >= 30){
                    log.info("VECTOR A length: " + vectorA.length);
                }else{
                    log.info("Vector A from the thread1: " + Arrays.toString(vectorA));
                }
                log.info("TIME OF vectorA (ms): " + Long.toString(System.currentTimeMillis() - time));
            }
        };

        Runnable matrixMA = new Runnable() {
            @Override
            public void run() {
                long time = System.currentTimeMillis();
                var minD = RandomData.findTheSmallestNumInVector(vectorD);
                //MC*ME*min(D)
                var MCME = RandomData.productMatrix(matrixMC, matrixME);
                var MCMEminD = RandomData.productMatrixAndNumber(MCME, minD);
                //MZ*MT
                var MZMT = RandomData.productMatrix(matrixMZ, matrixMT);
                //MA
                var MA = RandomData.sumOfMatrices(MCMEminD, MZMT);
                if(MA.length >= 30){
                    log.info("MATRIX MA length: " + MA.length);
                }else{
                    log.info("MA matrix from thread2: " + RandomData.matrixToString(MA));
                }
                log.info("TIME OF MA matrix (ms): " + Long.toString(System.currentTimeMillis() - time));
            }
        };
        Thread thread1 = new Thread(vectorA);
        thread1.start();
        Thread thread2 = new Thread(matrixMA);
        thread2.start();
    }

}
