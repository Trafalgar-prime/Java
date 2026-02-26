package com.example.BattagliaNavale;

public class Grid {

    int numRow;
    int numCol;
    boolean [][] Grid = new boolean[numRow][numCol];

    public void CreateGrid(int numRow, int numCol){
        this.numRow = numRow;
        this.numCol = numCol;
        for(int i = 0; i < numRow; i++){
            for(int j = 0; j < numCol; j++){
                Grid[i][j] = false;
            }
        }
    }

    public boolean isEmptyCell(int row, int col) {
        if (Grid[row][col] == false){
            return true;
        }
        return false;
    }

    public boolean fillCell(int row, int col){
        for(int i = 0; i < numRow; i++){
            for(int j = 0; j < numCol; j++){
                if(i == row && j == col){
                    Grid[i][j] = true;
                }
            }
        }
        return Grid[row][col];
    }


}
