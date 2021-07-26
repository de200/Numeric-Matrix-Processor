package pl.dezoo;

public class Matrix {

    private final int rowNumber;
    private final int columnNumber;
    private final double[][] array;

    public Matrix(int rowNumber, int columnNumber) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        this.array = new double[rowNumber][columnNumber];
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public double[][] getArray() {
        return array;
    }
}
