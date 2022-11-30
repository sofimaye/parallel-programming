import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Random;

public class RandomData {
    private final static Logger log = LoggerFactory.getLogger(RandomData.class);
    public static void main(String[] args) {
        var matrixMC = matrix(400, 300);
        var matrixME = matrix(300, 400);
        var matrixMZ = matrix(300, 400);
        var matrixMT = matrix(400, 300);
        var minD = findTheSmallestNumInVector(vectorD);
        //vector.length must be equal to matrix.length (row)
        //vectors should be equal
        var vectorB = vector(400);
        var vectorD = vector(400);

        //VECTOR A CALCULATIONS
        long timeForVectorA = System.currentTimeMillis();
        //vectors should be equal
        //vector.length === matrix.length (row)
        var BMC = product(vectorB, matrixMC);
        var DMT = product(vectorD, matrixMT);
        var A = sum(BMC, DMT);
        if(A.length >= 30){
            log.info("VECTOR A length: " + A.length);
        }else{
            log.info("VECTOR A: " + Arrays.toString(A));
        }
        log.info("TIME FOR VECTOR A: " + Long.toString(System.currentTimeMillis() - timeForVectorA));
        log.info("---------------------------------------");

        //множення мариць можливе лише тоді коли число стовпців першої матриці = кількості рядків другої
        //MATRIX MA CALCULATIONS
        long timeForMatrixMA = System.currentTimeMillis();
        var matrixProdMZMT = productMatrix(matrixMZ, matrixMT);
        var matrixProdMCME = productMatrix(matrixMC, matrixME);
        var prodMatrixAndNum = productMatrixAndNumber(matrixProdMCME, minD);
        //MA
        var matrixMA = sumOfMatrices(prodMatrixAndNum, matrixProdMZMT);
        if (matrixMA.length >= 30) {
            log.info("MATRIX MA length: " + matrixMA.length);
        } else {
            log.info("MA matrix:\n" + matrixToString(matrixMA));
        }
        log.info("TIME FOR MATRIX MA: " + Long.toString(System.currentTimeMillis() - timeForMatrixMA));
        log.info("---------------------------------------");
   }

    static double[][] matrixMC = matrix(400, 300);
    static double[][] matrixME = matrix(300, 400);
    static double[][] matrixMZ = matrix(300, 400);
    static double[][] matrixMT = matrix(400, 300);

    //vector.length must be equal to matrix.length (row)
    //vectors should be equal
    static double[] vectorB = vector(400);
    static double[] vectorD = vector(400);

    //generate random matrix
    public static double[][] matrix(int row, int col) {
        var r = new Random();
        var m = new double[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                m[i][j] = r.nextDouble() * 100;
            }
        }
        return m;
    }

    //generate random vector
    public static double[] vector(int size) {
        var r = new Random();
        var v = new double[size];
        for (int i = 0; i < v.length; i++) {
            v[i] = r.nextDouble() * 100;
        }
        return v;
    }

    public static String matrixToString(double[][] matrix) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                out.append(matrix[i][j]);
                out.append(" ");
            }
            out.append("\n");
        }
        return out.toString();
    }

    //for operations with vector and matrix
    public static double[] product(double[] vector, double[][] matrix) {
        var result = new double[matrix[0].length];
        for (int i = 0; i < matrix[0].length; i++) {
            var sum = 0;
            for (int j = 0; j < vector.length; j++) {
                sum += vector[j] * matrix[j][i];
            }
            result[i] = sum;
        }
        return result;
    }

    public static double[][] productMatrix(double[][] matrix1, double[][] matrix2) {
        if (matrix1.length <= matrix2.length) {
            var result = new double[matrix1.length][matrix1.length];
            for (int i = 0; i < matrix1.length; i++) {
                for (int j = 0; j < matrix1.length; j++) {
                    for (int k = 0; k < matrix2.length; k++) {
                        result[i][j] += matrix1[i][k] * matrix2[k][j];
                    }
                }
            }
            return result;
        } else {
            var result = new double[matrix2.length][matrix2.length];
            for (int i = 0; i < matrix2.length; i++) {
                for (int j = 0; j < matrix2.length; j++) {
                    for (int k = 0; k < matrix1.length; k++) {
                        result[i][j] += matrix2[i][k] * matrix1[k][j];
                    }
                }
            }
            return result;
        }
    }

    public static double[][] productMatrixAndNumber(double[][] matrix, double number) {
        var result = new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                result[i][j] = matrix[i][j] * number;
            }
        }
        return result;
    }

    // the result of adding vectors
    public static double[] sum(double[] vector1, double[] vector2) {
        var newVector = new double[vector1.length];
        for (int i = 0; i < vector1.length; i++) {
            newVector[i] = vector1[i] + vector2[i];
        }
        return newVector;
    }

    //adding two matrices
    public static double[][] sumOfMatrices(double[][] matrix1, double[][] matrix2) {
        var newMatrix = new double[matrix1.length][matrix1.length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1.length; j++) {
                newMatrix[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return newMatrix;
    }

    //min(D)
    public static double findTheSmallestNumInVector(double[] vector) {
        var smallest = 0.0;
        smallest += vector[0];
        for (int i = 0; i < vector.length; i++) {
            if (smallest > vector[i]) {
                smallest = vector[i];
            }
        }
        return smallest;
    }
}



