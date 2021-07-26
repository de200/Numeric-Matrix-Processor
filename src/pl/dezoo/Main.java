package pl.dezoo;

import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);


    public static void main(String[] args) {

        chooseOption();

    }

    private static Matrix createMatrix() {
        int rowNumber = SCANNER.nextInt();
        int columnNumber = SCANNER.nextInt();
        return new Matrix(rowNumber, columnNumber);
    }

    private static void fillMatrix(Matrix matrix) {
        double[][] array = matrix.getArray();

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = SCANNER.nextDouble();
            }
        }
    }

    private static boolean isSameSize(Matrix m1, Matrix m2) {
        double[][] a1 = m1.getArray();
        double[][] a2 = m2.getArray();

        return a1.length == a2.length && a1[0].length == a2[0].length;
    }

    private static double[][] addMatrices(Matrix m1, Matrix m2) {
        double[][] a1 = m1.getArray();
        double[][] a2 = m2.getArray();
        double[][] matrixSum = new double[a1.length][a1[0].length];

        for (int i = 0; i < a1.length; i++) {
            for (int j = 0; j < a1[i].length; j++) {
                matrixSum[i][j] = a1[i][j] + a2[i][j];
            }
        }
        return matrixSum;
    }

    private static double[][] multiplyMatrix(Matrix matrix, double constant) {
        double[][] array = matrix.getArray();

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] *= constant;
            }
        }
        return array;
    }

    private static double[][] multiplyMatrices(Matrix m1, Matrix m2) {
        double[][] a = m1.getArray();
        double[][] b = m2.getArray();

        int rowsNumberA = a.length;
        int columnsNumberA = a[0].length;
        int rowsNumberB = b.length;
        int columnsNumberB = b[0].length;

        double[][] matrixProduct = new double[rowsNumberA][columnsNumberB];

        if (columnsNumberA == rowsNumberB) {
            for (int i = 0; i < rowsNumberA; i++) {
                for (int j = 0; j < columnsNumberB; j++) {
                    for (int k = 0; k < columnsNumberA; k++)
                        matrixProduct[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return matrixProduct;
    }

    private static void printResult(double[][] array) {
        System.out.println("The result is:");
        if (array != null) {
            for (double[] doubles : array) {
                for (double aDoubles : doubles) {
                    System.out.print(aDoubles + " ");
                }
                System.out.println();
            }
        }
    }

    private static void printOptions() {
        System.out.println("1. Add matrices\n" +
                "2. Multiply matrix by a constant\n" +
                "3. Multiply matrices\n" +
                "4. Transpose matrix\n" +
                "5. Calculate a determinant\n" +
                "6. Inverse matrix\n" +
                "0. Exit\n" +
                "Your choice:");
    }

    private static void printTransposeOptions() {
        System.out.println("1. Main diagonal\n" +
                "2. Side diagonal\n" +
                "3. Vertical line\n" +
                "4. Horizontal line");
    }


    private static void chooseOption() {
        printOptions();
        int option = SCANNER.nextInt();

        while (option != 0) {
            switch (option) {
                case 1:
                    System.out.println("Enter size of first matrix:");
                    Matrix m1A = createMatrix();
                    System.out.println("Enter first matrix:");
                    fillMatrix(m1A);
                    System.out.println("Enter size of second matrix:");
                    Matrix m1B = createMatrix();
                    System.out.println("Enter second matrix:");
                    fillMatrix(m1B);
                    if (isSameSize(m1A, m1B)) {
                        double[][] additionResult = addMatrices(m1A, m1B);
                        printResult(additionResult);
                    } else {
                        System.out.println("The operation cannot be performed.");
                    }
                    break;
                case 2:
                    System.out.println("Enter size of matrix:");
                    Matrix m2 = createMatrix();
                    System.out.println("Enter matrix:");
                    fillMatrix(m2);
                    System.out.println("Enter constant:");
                    double constant = SCANNER.nextDouble();
                    double[][] multiplicationResult = multiplyMatrix(m2, constant);
                    printResult(multiplicationResult);
                    break;
                case 3:
                    System.out.println("Enter size of first matrix:");
                    Matrix m3A = createMatrix();
                    System.out.println("Enter first matrix:");
                    fillMatrix(m3A);
                    System.out.println("Enter size of second matrix:");
                    Matrix m3B = createMatrix();
                    System.out.println("Enter second matrix:");
                    fillMatrix(m3B);
                    double[][] multiplicationMatricesResult = multiplyMatrices(m3A, m3B);
                    printResult(multiplicationMatricesResult);
                    break;
                case 4:
                    double[][] m4 = chooseTransposeOption();
                    printResult(m4);
                    break;
                case 5:
                    System.out.println("Enter matrix size:");
                    Matrix m5 = createMatrix();
                    if (m5.getColumnNumber() != m5.getRowNumber()) {
                        System.out.println("Row and column number can not be different. Try again!");
                    } else {
                        System.out.println("Enter matrix:");
                        fillMatrix(m5);
                        System.out.println("The result is:\n" + calculateDeterminant(m5) + "\n");
                    }
                    break;
                case 6:
                    System.out.println("Enter matrix size:");
                    Matrix m6 = createMatrix();
                    if (m6.getColumnNumber() != m6.getRowNumber()) {
                        System.out.println("Row and column number can not be different. Try again!");
                    } else {
                        System.out.println("Enter matrix:");
                        fillMatrix(m6);
                        double[][] inverse = inverseMatrix(m6);
                        printResult(inverse);
                    }
                default:
                    System.out.println("Unknown option");
                    break;

            }
            printOptions();
            option = SCANNER.nextInt();
        }
    }

    private static double[][] chooseTransposeOption() {
        printTransposeOptions();
        int option = SCANNER.nextInt();

        switch (option) {
            case 1:
                System.out.println("Enter size of matrix:");
                Matrix m1 = createMatrix();
                System.out.println("Enter matrix:");
                fillMatrix(m1);
                return transposeMainDiagonal(m1);
            case 2:
                System.out.println("Enter size of matrix:");
                Matrix m2 = createMatrix();
                System.out.println("Enter matrix:");
                fillMatrix(m2);
                return transposeSideDiagonal(m2);
            case 3:
                System.out.println("Enter size of matrix:");
                Matrix m3 = createMatrix();
                System.out.println("Enter matrix:");
                fillMatrix(m3);
                return transposeVerticalLine(m3);
            case 4:
                System.out.println("Enter size of matrix:");
                Matrix m4 = createMatrix();
                System.out.println("Enter matrix:");
                fillMatrix(m4);
                return transposeHorizontalLine(m4);
            default:
                System.out.println("Unknown option!");
                return null;
        }

    }

    private static double[][] transposeMainDiagonal(Matrix matrix) {
        double[][] m = matrix.getArray();
        double[][] mainDiagonalMatrix = new double[m.length][m[0].length];

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                mainDiagonalMatrix[j][i] = m[i][j];
            }
        }
        return mainDiagonalMatrix;
    }

    private static double[][] transposeSideDiagonal(Matrix matrix) {
        double[][] m = matrix.getArray();
        double[][] sideDiagonalMatrix = new double[m.length][m[0].length];

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                sideDiagonalMatrix[sideDiagonalMatrix[i].length - 1 - j][sideDiagonalMatrix.length - 1 - i] = m[i][j];
            }
        }
        return sideDiagonalMatrix;
    }

    private static double[][] transposeVerticalLine(Matrix matrix) {
        double[][] m = matrix.getArray();
        double[][] verticalLineMatrix = new double[m.length][m[0].length];

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                verticalLineMatrix[i][m[i].length - 1 - j] = m[i][j];
            }
        }
        return verticalLineMatrix;
    }

    private static double[][] transposeHorizontalLine(Matrix matrix) {
        double[][] m = matrix.getArray();
        double[][] horizontalLineMatrix = new double[m.length][m[0].length];

        for (int i = 0; i < m.length; i++) {
            System.arraycopy(m[i], 0, horizontalLineMatrix[m.length - 1 - i], 0, m[i].length);
        }
        return horizontalLineMatrix;
    }

    private static double calculateDeterminant(Matrix matrix) {

        double determinant = 0;
        int size = matrix.getColumnNumber();

        double[][] m1 = matrix.getArray();


        if (size == 1) {
            return m1[0][0];
        }

        if (size == 2) {
            determinant = m1[0][0] * m1[1][1] - m1[0][1] * m1[1][0];
            return determinant;
        }
        for (int i = 0; i < size; i++) {
            Matrix minorMatrix = minor(matrix, 0, i);


            determinant += m1[0][i] * Math.pow(-1, i) * calculateDeterminant(minorMatrix);

        }
        return determinant;
    }

    private static Matrix minor(Matrix matrix, int row, int column) {
        Matrix minorMatrix = new Matrix(matrix.getRowNumber() - 1, matrix.getColumnNumber() - 1);
        double[][] minor = minorMatrix.getArray();

        for (int i = 0; i < matrix.getRowNumber(); i++) {
            for (int j = 0; i != row && j < matrix.getColumnNumber(); j++) {
                if (j != column) {
                    minor[i < row ? i : i - 1][j < column ? j : j - 1] = matrix.getArray()[i][j];
                }
            }
        }
        return minorMatrix;
    }

    private static double[][] inverseMatrix(Matrix matrix) {

        Matrix matrixInverse = new Matrix(matrix.getRowNumber(), matrix.getColumnNumber());
        double[][] inverse = matrixInverse.getArray();

        for (int i = 0; i < matrix.getRowNumber(); i++) {
            for (int j = 0; j < matrix.getColumnNumber(); j++) {
                inverse[i][j] = Math.pow(-1, i + j) * calculateDeterminant(minor(matrix, i, j));
            }
        }

        double determinant = 1 / calculateDeterminant(matrix);

        for (int i = 0; i < matrixInverse.getRowNumber(); i++) {
            for (int j = 0; j <= i; j++) {
                double tempNum = inverse[i][j];
                inverse[i][j] = inverse[j][i] * determinant;
                inverse[j][i] = tempNum * determinant;
            }
        }

        return inverse;
    }
}
