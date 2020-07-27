package project.controller;

import project.models.Matrix;

public interface Controller {

    Matrix createMatrix(int row, int col, int[][] matrix);

    int checkAroundCells(int row, int col, int[][] matrix);

    boolean isNotWall(int row, int col, int[][] matrix);

    boolean isOnBottomWall(int row,int length);

    boolean isOnRightWall(int col,int width);

    boolean isOnLeftWall(int col);

    boolean isOnTopWall(int row);

    boolean isTopRightCorner(int row, int col,int length);

    boolean isBottomRightCorner(int row, int col,int length, int width);

    boolean isBottomLeftCorner(int row, int col,int length);

    boolean isTopLeftCorner(int row, int col);

    boolean isInRange(int numberOfGreenCells);
}
