package project.models;

public class Matrix {
    private int rows;
    private int cols;
    private int[][] gridValues;

    public Matrix() {
    }

    public Matrix(int rows, int cols, int[][] gridValues) {
        this.rows = rows;
        this.cols = cols;
        this.gridValues = gridValues;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int[][] getGridValues() {
        return gridValues;
    }

    public void setGridValues(int[][] gridValues) {
        this.gridValues = gridValues;
    }
}
