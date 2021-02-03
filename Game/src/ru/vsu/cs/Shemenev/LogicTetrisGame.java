package ru.vsu.cs.Shemenev;

import ru.vsu.cs.util.JTableUtils;

import javax.swing.*;


public class LogicTetrisGame {
    private static int  GamePoints;
    private static int countKillCell;

    public static int gamePoints(String[][] matrix, int j, JTable GameField, int points) {
        /*int i = GameUtils.GeolocationColor(matrix, j);
        String value = matrix[i][j];
        List<Integer> cord = cordKill(j, matrix, value, i);
        if (cord.size() > 6) {
            for (int k = 0; k < cord.size() - 1; k += 2) {
                matrix[cord.get(k)][cord.get(k + 1)] = " ";
                points += 10;
            }
        }
        JTableUtils.writeArrayToJTable(GameField, matrix);
        GameUtils.paintCell(GameField, matrix);
        return points;*/
        int i =TaskUtils.GeolocationColor(matrix,j);
        String value = matrix[i][j];
        countKillCell = 0;
        GamePoints = points;
        String[][] helpMatrix = TaskUtils.matrixField(matrix);
        countCheckKillCell(helpMatrix,value,i,j);
        if(countKillCell>3) {
            destructionCell(matrix, value, i, j, GameField);
        }
        else {
            JTableUtils.writeArrayToJTable(GameField, matrix);
            TaskUtils.paintCell(GameField, matrix);
        }
        return GamePoints;

    }
    private static void destructionCell(String[][] matrix, String value, int row, int col,
                                        JTable GameField){
        if(row >-1&& row<matrix.length-1 && col>-1 && col<matrix[0].length && matrix[row][col].equals(value)){
            matrix[row][col] = " ";
            GamePoints+=10;
            JTableUtils.writeArrayToJTable(GameField, matrix);
            TaskUtils.paintCell(GameField, matrix);
        }
        else {
            return;
        }
        destructionCell(matrix,value,row-1,col,GameField);
        destructionCell(matrix,value,row,col+1,GameField);
        destructionCell(matrix,value,row,col-1,GameField);
        destructionCell(matrix,value,row+1,col,GameField);
    }
    private static void countCheckKillCell(String[][] matrix, String value, int row, int col){
        if(row >-1&& row<matrix.length-1 && col>-1 && col<matrix[0].length && matrix[row][col].equals(value)){
            countKillCell++;
            matrix[row][col] = " ";
        }
        else {
            return;
        }
        countCheckKillCell(matrix,value,row-1,col);
        countCheckKillCell(matrix,value,row,col+1);
        countCheckKillCell(matrix,value,row,col-1);
        countCheckKillCell(matrix,value,row+1,col);
    }

    /*private static List<Integer> cordKill(int j, String[][] matrix, String value, int i) {
        List<Integer> listCord = new ArrayList<>();
        while (i > -1 && matrix[i][j].equals(value)) {
            listCord.add(i);
            listCord.add(j);
            int right = j + 1;
            int left = j - 1;
            while (right < matrix[0].length && matrix[i][right].equals(value)) {
                listCord.add(i);
                listCord.add(right);
                int down = i;
                int up = i;
                while (right > j + 1 && down > -1 && matrix[down][right].equals(value)) {
                    listCord.add(down);
                    listCord.add(right);
                    down--;
                }
                while (right > j + 1 && up < matrix.length - 1 && matrix[up][right].equals(value)) {
                    listCord.add(up);
                    listCord.add(right);
                    up++;
                }
                right++;
            }
            while (left > -1 && matrix[i][left].equals(value)) {
                listCord.add(i);
                listCord.add(left);
                int down = i;
                int up = i;
                while (left < j - 1 && down > -1 && matrix[down][left].equals(value)) {
                    listCord.add(down);
                    listCord.add(left);
                    down--;
                }
                while (left < j - 1 && up < matrix.length - 1 && matrix[up][left].equals(value)) {
                    listCord.add(up);
                    listCord.add(left);
                    up++;
                }
                left--;
            }
            i--;
        }
        return listCord;
    }*/

}
