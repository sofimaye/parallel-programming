import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;
import java.util.Random;

// data and traditional approach for calculations(to compare) without multithreading

public class RandomData {
    private final static Logger log = LoggerFactory.getLogger(RandomData.class);

    public static void main(String[] args) {
        var minD = findTheSmallestNumInVector(vectorD);
        //VECTOR A CALCULATIONS
        long timeForVectorA = System.currentTimeMillis();
        //vectors should be equal
        //vector.length === matrix.length (row)
        var BMC = product(vectorB, matrixMC);
        var DMT = product(vectorD, matrixMT);
        var A = sum(BMC, DMT);
        if (A.length >= 30) {
            log.info("VECTOR A length: " + A.length);
        } else {
            log.info("VECTOR A: " + Arrays.toString(A));
        }
        log.info("TIME FOR VECTOR A: " + Long.toString(System.currentTimeMillis() - timeForVectorA));
        log.info("---------------------------------------");

        //MATRIX MA CALCULATIONS
        long timeForMatrixMA = System.currentTimeMillis();
        var matrixProdMZMT = productMatrices(matrixMZ, matrixMT);
        log.info("Matrix MZ: \n", matrixToString(matrixMZ));
        log.info("Matrix MT: \n", matrixToString(matrixMT));
        log.info(matrixToString(matrixProdMZMT));
        var matrixProdMCME = productMatrices(matrixMC, matrixME);
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
    //vector.length must be equal to matrix.length (row)
    //vectors should be equal

    //множення мариць можливе лише
    //тоді коли число стовпців першої матриці = кількості рядків другої

    //static double[][] matrixMC = matrix(4, 3);
    //static double[][] matrixME = matrix(3, 4);

    //static double[][] matrixMT = matrix(3, 4);
    //static double[][] matrixMZ = matrix(4, 3);

    static double[][] matrixMC = {{1, 8, 11, 8}, {9, 11, 2, 9}, {1, 5, 7, 8}, {2, 9, 1, 8}};
    static double[][] matrixME = {{1, 0, 1, 6}, {4, 2, 1, 8}, {1, 8, 5, 8}, {8, 9, 1, 6}};

    static double[][] matrixMZ = {{1, 8, 9, 8}, {9, 11, 0, 8}, {3, 5, 7, 5}, {8, 5, 1, 8}};
    static double[][] matrixMT = {{1, 2, 3, 9}, {4, 9, 12, 5}, {1, 3, 5, 8}, {8, 3, 1, 8}};


//    static double[] vectorB = vector(3);
//    static double[] vectorD = vector(3);

    static double[] vectorB = {1,2,3,8};
    static double[] vectorD = {1,2,3,2}; // виходить трьохзначний вектор

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

    //for multiplying vector and matrix
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

    // for multiplying 2 matrices
    public static double[][] productMatrices(double[][] matrix1, double[][] matrix2) {
            var result = new double[matrix1.length][matrix1.length];
            for (int i = 0; i < matrix1.length; i++) {
                for (int j = 0; j < matrix1.length; j++) {
                    for (int k = 0; k < matrix2.length; k++) {
                        result[i][j] += matrix1[i][k] * matrix2[k][j];
                    }
                }
            }
            return result;
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



