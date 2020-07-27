package project.controller;

import project.models.Matrix;

import java.util.Arrays;
import java.util.Scanner;

public class ControllerImpl implements Controller{

    private Matrix grid;

    public ControllerImpl() {

//        this.scanner = new Scanner(System.in);

    }


    @Override
    public Matrix updateGridForNextGeneration(Matrix matrix, Matrix newMatrix) {
        for (int row = 0; row < matrix.getRows(); row++) {
            for (int col = 0; col < matrix.getCols(); col++) {
                matrix.getGridValues()[row][col] = newMatrix.getGridValues()[row][col];
            }
        }
        return matrix;
    }

    @Override
    public Matrix updateGridValues(int row, int col, Matrix matrix, int newValue) {
        for (int rowN = 0; rowN < matrix.getRows(); rowN++) {
            for (int colN = 0; colN < matrix.getCols(); colN++) {
                if(rowN == row && colN == col){
                    matrix.getGridValues()[row][col] = newValue;
                }
            }
        }
        return matrix;
    }

    @Override
    public Matrix createMatrix(int row, int col, int[][] matrix) {
        return grid = new Matrix(row,col,matrix);
    }

    @Override
    public int checkAroundCells(int row, int col, Matrix matrix) {

        int counter = 0;

        if (isTopLeftCorner(row, col)) {
            if (matrix.getGridValues()[row][col + 1] == 1) {
                counter++;
            }

            if (matrix.getGridValues()[row + 1][col + 1] == 1) {
                counter++;
            }

            if (matrix.getGridValues()[row + 1][col] == 1) {
                counter++;
            }
            return counter;
        }

        if (isBottomLeftCorner(row, col,matrix.getRows())) {
            if (matrix.getGridValues()[row][col + 1] == 1) {
                counter++;
            }

            if (matrix.getGridValues()[row - 1][col + 1] == 1) {
                counter++;
            }

            if (matrix.getGridValues()[row - 1][col] == 1) {
                counter++;
            }
            return counter;
        }

        if (isBottomRightCorner(row, col,matrix.getRows(),matrix.getCols())) {
            if (matrix.getGridValues()[row][col - 1] == 1) {
                counter++;
            }

            if (matrix.getGridValues()[row - 1][col - 1] == 1) {
                counter++;
            }

            if (matrix.getGridValues()[row - 1][col] == 1) {
                counter++;
            }
            return counter;
        }

        if (isTopRightCorner(row, col,matrix.getRows())) {
            if (matrix.getGridValues()[row][col - 1] == 1) {
                counter++;
            }

            if (matrix.getGridValues()[row + 1][col - 1] == 1) {
                counter++;
            }

            if (matrix.getGridValues()[row + 1][col] == 1) {
                counter++;
            }
            return counter;
        }

        if (isOnTopWall(row) && !isTopRightCorner(row,col,matrix.getRows())) {
            if (matrix.getGridValues()[row][col - 1] == 1) {
                counter++;
            }

            if (matrix.getGridValues()[row][col + 1] == 1) {
                counter++;
            }

            if (matrix.getGridValues()[row + 1][col] == 1) {
                counter++;
            }

            if (matrix.getGridValues()[row + 1][col - 1] == 1) {
                counter++;
            }

            if (matrix.getGridValues()[row + 1][col + 1] == 1) {
                counter++;
            }
            return counter;
        }

        if (isOnLeftWall(col) && !isBottomLeftCorner(row,col,matrix.getRows())) {
            if (matrix.getGridValues()[row][col + 1] == 1) {
                counter++;
            }

            if (matrix.getGridValues()[row - 1][col + 1] == 1) {
                counter++;
            }
            if (matrix.getGridValues()[row - 1][col] == 1) {
                counter++;
            }

            if (matrix.getGridValues()[row + 1][col + 1] == 1) {
                counter++;
            }
            if (matrix.getGridValues()[row + 1][col] == 1) {
                counter++;
            }
            return counter;
        }

        if (isOnRightWall(col,matrix.getRows()) && !isBottomRightCorner(row, col,matrix.getRows(),matrix.getCols())) {
            if (matrix.getGridValues()[row][col - 1] == 1) {
                counter++;
            }
            if (matrix.getGridValues()[row + 1][col] == 1) {
                counter++;
            }
            if (matrix.getGridValues()[row + 1][col - 1] == 1) {
                counter++;
            }
            if (matrix.getGridValues()[row - 1][col - 1] == 1) {
                counter++;
            }
            if (matrix.getGridValues()[row - 1][col] == 1) {
                counter++;
            }
            return counter;
        }

        if (isOnBottomWall(row,matrix.getRows())) {
            if (matrix.getGridValues()[row][col - 1] == 1) {
                counter++;
            }
            if (matrix.getGridValues()[row][col + 1] == 1) {
                counter++;
            }
            if (matrix.getGridValues()[row - 1][col] == 1) {
                counter++;
            }
            if (matrix.getGridValues()[row - 1][col - 1] == 1) {
                counter++;
            }
            if (matrix.getGridValues()[row - 1][col + 1] == 1) {
                counter++;
            }
            return counter;
        }

        if (isNotWall(row, col, matrix)) {
            if (matrix.getGridValues()[row][col - 1] == 1) {
                counter++;
            }
            if (matrix.getGridValues()[row][col + 1] == 1) {
                counter++;
            }
            if (matrix.getGridValues()[row - 1][col - 1] == 1) {
                counter++;
            }
            if (matrix.getGridValues()[row - 1][col] == 1) {
                counter++;
            }
            if (matrix.getGridValues()[row - 1][col + 1] == 1) {
                counter++;
            }
            if (matrix.getGridValues()[row + 1][col - 1] == 1) {
                counter++;
            }
            if (matrix.getGridValues()[row + 1][col + 1] == 1) {
                counter++;
            }
            if (matrix.getGridValues()[row + 1][col] == 1) {
                counter++;
            }
            return counter;
        }

        return counter;
    }
    public boolean isNotWall(int row, int col, Matrix matrix) {
        return (row > 0 && row < matrix.getRows() - 1) &&
                (col > 0 && col < matrix.getCols() - 1);
    }

    public boolean isOnBottomWall(int row,int length){
        return row == length - 1;
    }

    public boolean isOnRightWall(int col,int width) {
        return col == width - 1;
    }

   public boolean isOnLeftWall(int col) {
        return col - 1 < 0;
    }

    public boolean isOnTopWall(int row) {
        return row - 1 < 0;
    }

    public boolean isTopRightCorner(int row, int col,int length) {

        return row - 1 < 0 && col + 1 == length;
    }

    public boolean isBottomRightCorner(int row, int col, int length, int width) {
        return row + 1 == length && col + 1 == width;
    }

    public boolean isBottomLeftCorner(int row, int col,int length) {
        return row + 1 == length &&
                col == 0;   //za toq row ne sam mn sig che e taka pravilnoto
    }


    public boolean isTopLeftCorner(int row, int col) {
        return row - 1 < 0 && col - 1 < 0;
    }

    @Override
    public boolean isInRange(int numberOfGreenCells) {
        return numberOfGreenCells == 0 || numberOfGreenCells == 1 ||
                numberOfGreenCells == 4 ||
                numberOfGreenCells == 5 || numberOfGreenCells == 7 ||
                numberOfGreenCells == 8;
    }

}
