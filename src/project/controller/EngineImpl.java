package project.controller;

import project.common.ConstantMessages;
import project.models.Matrix;

import java.util.Arrays;
import java.util.Scanner;

import static project.common.ConstantMessages.*;

public class EngineImpl implements Engine {
    private final Controller controller;
    private final Scanner scanner = new Scanner(System.in);

    public EngineImpl(Controller controller) {
        this.controller = controller;

    }

    @Override
    public void run() {
        System.out.println(HELLO);
        System.out.println(ENTER_DIMENSIONS);

        int[] params = getParams();
        int matrixRows = params[0];
        int matrixCols = params[1];

        checkIfParamsAreInRange(matrixRows, matrixCols);

        System.out.println(ENTER_GRID_VALUES);

        int[][] matrixInit = fillMatrix(params);

        Matrix matrix = this.controller.createMatrix(matrixRows,matrixCols,matrixInit);
        System.out.println(ENTER_CELL_COORDINATES_AND_ITERATIONS);

        int[] lastRow = Arrays.stream(scanner.nextLine().split(","))
                .mapToInt(Integer::parseInt)
                .toArray();

        int x1 = lastRow[0];
        int y1 = lastRow[1];
        int n = lastRow[2];

        int greenCounter = 0;

        while (n > 0) {

            Matrix newMatrix = this.controller.createMatrix(matrixRows,matrixCols,new int[matrixRows][matrixCols]);

            for (int row = 0; row < matrix.getRows(); row++) {
                for (int col = 0; col < matrix.getCols(); col++) {

                    int positionToCheck = matrix.getGridValues()[row][col];

                    int numberOfGreenCells = controller.checkAroundCells(row, col, matrix);

                    if (positionToCheck == 0) {  //ако клетката е червена
                        if (numberOfGreenCells == 3 || numberOfGreenCells == 6) {
                           this.controller.updateGridValues(row,col,newMatrix,1);

                        } else {
                            this.controller.updateGridValues(row,col,newMatrix,0);
                        }
                    } else if (positionToCheck == 1) {  //ако клетката е зелена
                        if (controller.isInRange(numberOfGreenCells)) {
                            this.controller.updateGridValues(row,col,newMatrix,0);
                        } else {
                            this.controller.updateGridValues(row,col,newMatrix,1);
                        }
                    }
                }

            }

            matrix = this.controller.updateGridForNextGeneration(matrix,newMatrix);


            if (matrix.getGridValues()[x1][y1] == 1) {
                greenCounter++;
            }

            n--;
        }

        System.out.printf(RESULT, lastRow[2], x1, y1, greenCounter);

    }

    private boolean checkIfParamsAreInRange(int matrixRows, int matrixCols) {
        if ((matrixRows >= 2 && matrixRows <= 1000) && (matrixCols >= 2 && matrixCols <= 1000)) {
            return true;
        } else {
            throw new IllegalArgumentException(DIMENSIONS_ERROR);

        }

    }


    public int[][] fillMatrix(int[] params) {
        int rows = params[0];

        int[][] resultMatrix = new int[rows][];

        for (int row = 0; row < rows; row++) {
            int[] arr = Arrays.stream(scanner.nextLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            resultMatrix[row] = arr;

        }

        return resultMatrix;
    }

    public int[] getParams() {

        return Arrays.stream(scanner.nextLine().split(","))
                .mapToInt(Integer::parseInt)
                .toArray();

    }
}
