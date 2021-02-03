package ru.vsu.cs.Shemenev;

import ru.vsu.cs.util.JTableUtils;

import javax.swing.*;

public class LogicKrestiksNoliksGame {
    public static void toWalkPerson(String[][] matrixGame, int x, int y, JTable FieldGame, int countStep) {
        if (countStep % 2 == 0) {
            matrixGame[x][y] = "O";
        } else {
            matrixGame[x][y] = "X";
        }
        JTableUtils.writeArrayToJTable(FieldGame, matrixGame);
    }

    public static boolean checkWinGame2Cross(String[][] matrix) {// Победа крестиков
        for (int i = 0; i < matrix.length; i++) {
            if ((matrix[i][0].equals(matrix[i][1]) && matrix[i][2].equals(matrix[i][0])) && matrix[i][0].equals("X")) {
                return true;
            }
        }
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i].equals(matrix[1][i]) && matrix[2][i].equals(matrix[0][i]) && matrix[0][i].equals("X")) {
                return true;
            }
        }
        if ((matrix[0][0].equals(matrix[1][1]) && matrix[2][2].equals(matrix[0][0]) && matrix[0][0].equals("X")) ||
                (matrix[0][2].equals(matrix[1][1]) && matrix[2][0].equals(matrix[0][2]) && matrix[0][2].equals("X"))) {
            return true;
        }
        return false;
    }

    public static boolean checkDraw(String[][] matrix) {
        int countTrue = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (!matrix[i][j].equals(" ")) {
                    countTrue++;
                }
            }
        }
        return (countTrue == 9) ? true : false;
    }
}
