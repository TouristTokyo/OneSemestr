package ru.vsu.cs.Shemenev;

import ru.vsu.cs.util.JTableUtils;

import javax.swing.*;
import java.util.List;

public class LogicVoglGame {
    public static void replace(List<Integer> cordCell, String[][] matrix, JTable GameField) {
        if (check(cordCell, matrix)) {
            return;
        }
        if (cordCell.get(2) < cordCell.get(0)) {
            replaceUp(cordCell, matrix, GameField);
        } else if (cordCell.get(0) < cordCell.get(2)) {
            replaceDown(cordCell, matrix, GameField);
        } else if (cordCell.get(1) > cordCell.get(3)) {
            replaceLeft(cordCell, matrix, GameField);
        } else {
            replaceRight(cordCell, matrix, GameField);
        }
    }

    private static boolean check(List<Integer> cordCell, String[][] matrix) {
        if (matrix[cordCell.get(0)][cordCell.get(1)].equals(matrix[cordCell.get(2)][cordCell.get(3)])) {
            return true;
        } else if ((Math.abs(cordCell.get(1) - cordCell.get(3)) == 0 && Math.abs(cordCell.get(0) - cordCell.get(2)) != 2)
                || (Math.abs(cordCell.get(1) - cordCell.get(3)) != 2 && Math.abs(cordCell.get(0) - cordCell.get(2)) == 0)
                || (Math.abs(cordCell.get(1) - cordCell.get(3)) != 0 && Math.abs(cordCell.get(0) - cordCell.get(2)) != 0)) {
            return true;
        }

        return false;
    }

    private static void replaceUp(List<Integer> cordCell, String[][] matrix, JTable GameField) {
        int i0 = cordCell.get(0);
        int j0 = cordCell.get(1);
        int i = cordCell.get(2);
        int j = cordCell.get(3);
        if (matrix[i0 - 1][j].equals(matrix[i0][j0])) {
            String save = matrix[i][j];
            matrix[i][j] = matrix[i0][j0];
            matrix[i0][j0] = save;
            matrix[i0 - 1][j] = " ";
            JTableUtils.writeArrayToJTable(GameField, matrix);
        }
    }

    private static void replaceDown(List<Integer> cordCell, String[][] matrix, JTable GameField) {
        int i0 = cordCell.get(0);
        int j0 = cordCell.get(1);
        int i = cordCell.get(2);
        int j = cordCell.get(3);
        if (matrix[i - 1][j].equals(matrix[i0][j0])) {
            String save = matrix[i][j];
            matrix[i][j] = matrix[i0][j0];
            matrix[i0][j0] = save;
            matrix[i - 1][j] = " ";
            JTableUtils.writeArrayToJTable(GameField, matrix);
        }
    }

    private static void replaceLeft(List<Integer> cordCell, String[][] matrix, JTable GameField) {
        int i0 = cordCell.get(0);
        int j0 = cordCell.get(1);
        int i = cordCell.get(2);
        int j = cordCell.get(3);
        if (matrix[i][j0 - 1].equals(matrix[i0][j0])) {
            String save = matrix[i][j];
            matrix[i][j] = matrix[i0][j0];
            matrix[i0][j0] = save;
            matrix[i][j0 - 1] = " ";
            JTableUtils.writeArrayToJTable(GameField, matrix);
        }
    }

    private static void replaceRight(List<Integer> cordCell, String[][] matrix, JTable GameField) {
        int i0 = cordCell.get(0);
        int j0 = cordCell.get(1);
        int i = cordCell.get(2);
        int j = cordCell.get(3);
        if (matrix[i][j - 1].equals(matrix[i0][j0])) {
            String save = matrix[i][j];
            matrix[i][j] = matrix[i0][j0];
            matrix[i0][j0] = save;
            matrix[i][j - 1] = " ";
            JTableUtils.writeArrayToJTable(GameField, matrix);
        }
    }
}
