import executor.FixedThreadPoolExecutor;
import executor.ParallelExecutor;
import executor.SequentialExecutor;
import executor.ThreadParallelExecutor;
import operation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class LabThreads {
    private final static Logger log = LoggerFactory.getLogger(LabThreads.class);

    public static void main(String[] args) {
//        execute(new SequentialExecutor());
        execute(new ThreadParallelExecutor(20));
//        execute(new FixedThreadPoolExecutor(8));
    }


//    A = B*MC + D*MT
//    MA = (min D)*MC*ME+MZ*MT

    private static void execute(ParallelExecutor parallelExecutor) {
        var matrixMT = RandomData.matrixMT;
        var matrixMZ = RandomData.matrixMZ;
        var vectorB = RandomData.vectorB;
        var matrixMC = RandomData.matrixMC;
        var vectorD = RandomData.vectorD;
        var matrixME = RandomData.matrixME;

        long time = System.currentTimeMillis();
        var minD = new double[1];
        //matrix length should be equal to matrix1.length
        var matrixMZMT = new double[matrixMZ.length][matrixMT[0].length];
        var matrixMCME = new double[matrixMC.length][matrixME[0].length];
        var matrixMCMED = new double[matrixMC.length][matrixME[0].length];
        var matrixMA = new double[matrixMCME.length][matrixMZMT.length];

        //MATRIX MA CALCULATIONS
        //matrixMZMT
        parallelExecutor.runInParallel(
                matrixMZMT.length,
                row -> new MatricesMultiplier(matrixMZMT, matrixMZ, matrixMT, row)
        );

        if (matrixMZMT.length >= 30) {
            log.info("The matrix MZMT length: " + matrixMZMT.length);
        } else {
            log.info("matrix MZ:\n" + RandomData.matrixToString(matrixMZ));
            log.info("matrix MT:\n" + RandomData.matrixToString(matrixMT));
            log.info("MZMT matrix:\n " + RandomData.matrixToString(matrixMZMT));
        }


        //matrixMCME
        parallelExecutor.runInParallel(
                matrixMCME.length,
                row -> new MatricesMultiplier(matrixMCME, matrixMC, matrixME, row)
        );

        if (matrixMCME.length >= 30) {
            log.info("The matrix MCME length: " + matrixMZMT.length);
        } else {
            log.info("matrix MC:\n" + RandomData.matrixToString(matrixMC));
            log.info("matrix ME:\n" + RandomData.matrixToString(matrixME));
            log.info("MCME matrix:\n" + RandomData.matrixToString(matrixMCME));
        }



        // minD
        parallelExecutor.runInParallel(
                vectorD.length,
                row -> new FindSmallestNum(minD, vectorD, row)
        );

        if(vectorD.length >= 30){
            log.info("Vector D length: " + vectorD.length);
        }else{
            log.info("vectorD" + Arrays.toString(vectorD));
            log.info("the smallest num D: " + Arrays.toString(minD));
        }



        //MCME*min(D)
        parallelExecutor.runInParallel(
                matrixMCMED.length,
                row -> new MatrixNumberMultiply(matrixMCMED, minD[0], matrixMCME, row)
        );
        if(matrixMCMED.length >=30){
            log.info("Matrix MCME*min(D): " + matrixMCMED.length);
        }else{
            log.info("Matrix MCME*min(D):\n" + RandomData.matrixToString(matrixMCMED));
        }

        //MCME*min(D) + MZMT
        parallelExecutor.runInParallel(
                matrixMA.length,
                row -> new SumOfMatrices(matrixMA, matrixMCMED, matrixMZMT, row)
        );

        if(matrixMA.length >= 30){
            log.info("Matrix MA: " + matrixMA.length);
        }else{
            log.info("Matrix MA:\n" + RandomData.matrixToString(matrixMA));
        }


        log.info("[" + parallelExecutor.getClass().getSimpleName() + "]" + " TIME of calc Matrix MA (ms): " + (System.currentTimeMillis() - time));

        long time2 = System.currentTimeMillis();

        //VECTOR A CALCULATIONS
        //A = B*MC+D*MT
        var BMC = new double[matrixMC[0].length];
        var DMT = new double[matrixMT[0].length];
        var A = new double[BMC.length];

        //BMC
        parallelExecutor.runInParallel(
                BMC.length,
                row -> new VectorMatrixMultiply(BMC, vectorB, matrixMC, row)
        );

        if(BMC.length >= 30){
            log.info("Vector BMC:" + BMC.length);
        }else{
            log.info("VECTOR BMC:\n" + Arrays.toString(BMC));
        }

        //DMT
        parallelExecutor.runInParallel(
                DMT.length,
                row -> new VectorMatrixMultiply(DMT, vectorD, matrixMT, row)
        );

        if(DMT.length >= 30){
            log.info("Vector DMT:" + DMT.length);
        }else{
            log.info("VECTOR DMT:\n" + Arrays.toString(DMT));
        }

        // A
        parallelExecutor.runInParallel(
                A.length,
                row -> new SumOfVectors(A, BMC, DMT, row)
        );

        if(A.length >= 30){
            log.info("Vector A:" + A.length);
        }else{
            log.info("VECTOR A:\n" + Arrays.toString(A));
        }
        log.info("[" + parallelExecutor.getClass().getSimpleName() + "]" + " TIME of calc Vector A (ms): " + (System.currentTimeMillis() - time2));

    }
}
