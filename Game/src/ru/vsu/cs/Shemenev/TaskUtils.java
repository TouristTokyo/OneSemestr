package ru.vsu.cs.Shemenev;

import ru.vsu.cs.util.JTableUtils;

import javax.swing.*;
import java.util.List;

public class TaskUtils {
    /**
     * Утилиты для Вогл(Demo)
     * 1)newArrayGame1
     * 2)checkWinGame1
     */
    public static String[][] newArrayGame1(String[][][] array, int n) {
        String[][] newArray = new String[array[n].length][array[n][0].length];
        for (int i = 0; i < newArray.length; i++) {
            for (int j = 0; j < newArray[0].length; j++) {
                newArray[i][j] = array[n][i][j];
            }
        }
        return newArray;
    }

    public static boolean checkWinGame1(String[][] matrix) {
        int countElements = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j].equals("O")) {
                    countElements++;
                }
            }
        }
        return (countElements == 1) ? true : false;
    }
    /**
     * Утилиты для Крестики нолики
     * 1)newArrayGame2
     * 2)checkWinGame2
     * 3)checkCell
     */
    public static String[][] newArrayGame2(String[][] array) {
        String[][] newArray = new String[array.length][array[0].length];
        for (int i = 0; i < newArray.length; i++) {
            for (int j = 0; j < newArray[0].length; j++) {
                newArray[i][j] = array[i][j];
            }
        }
        return newArray;
    }

    public static boolean checkWinGame2(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0].equals(matrix[i][1]) && matrix[i][2].equals(matrix[i][0]) &&
                    (matrix[i][0].equals("X") || matrix[i][0].equals("O"))) {
                return true;
            }
        }
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i].equals(matrix[1][i]) && matrix[2][i].equals(matrix[0][i]) &&
                    (matrix[0][i].equals("X") || matrix[0][i].equals("O"))) {
                return true;
            }
        }
        if (((matrix[0][0].equals(matrix[1][1]) && matrix[2][2].equals(matrix[0][0])) ||
                (matrix[0][2].equals(matrix[1][1]) && matrix[2][0].equals(matrix[0][2]))) &&
                (matrix[0][0].equals("X") || matrix[0][0].equals("O"))){
            return true;
        }
        return false;
    }

    public static boolean checkCell(int x, int y, List<Integer> cordX, List<Integer> cordY, JTable FieldGame){
        for(int i = 0; i<cordX.size();i++){
            if(FieldGame.getModel().getValueAt(x,y).equals(FieldGame.getModel().getValueAt(cordX.get(i),cordY.get(i)))){
                return true;
            }
        }
        return false;
    }
    /**
     * Утилиты для Тетриса
     * 1)matrixField
     * 2)paintCell
     * 3)Geolocation
     * 4)GeolocationColor
     * 5)GeolocationEmptyCell
     * 6)indexSearchDown
     * 7)FieldDown
     */
    public static String[][] matrixField(String[][] field) {
        String[][] matrix = new String[field.length][field[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = field[i][j];
            }
        }
        return matrix;
    }

    public static void paintCell(JTable GameField, String[][] matrix) {
        for (int i = 0; i < GameField.getColumnCount(); i++) {
            GameField.getColumnModel().getColumn(i).setCellRenderer(new RenderTetrisGame());
        }
    }

    public static int Geolocation(String[][] matrix) {
        int a = 0;
        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[matrix.length - 1][j].equals("-O-") || (matrix[matrix.length - 1][j].equals("-O<O>-"))
                    || (matrix[matrix.length - 1][j].equals("-O<R>-")) || (matrix[matrix.length - 1][j].equals("-O<B>-"))
                    || (matrix[matrix.length - 1][j].equals("-O<Y>-"))) {
                a = j;
            }
        }
        return a;
    }

    public static int GeolocationColor(String[][] matrix, int j) {
        int a = -1;
        for (int i = matrix.length - 2; i >= 0; i--) {
            if (!matrix[i][j].equals(" ")) {
                a = i;
                break;
            }
        }
        return a;
    }

    public static int GeolocationEmptyCell(String[][] matrix, int j) {
        int i = matrix.length - 2;
        while (i > -1 && matrix[i][j].equals(" ")) {
            i--;
        }
        return i + 1;
    }

    public static int indexSearchDown(String[][] matrix) {
        for (int i = matrix.length - 2; i >= 0; i--) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (!matrix[i][j].equals(" ")) {
                    return i;
                }
            }
        }
        return -1;
    }
    public static void FieldDown(String[][] field, String[][] matrix, int numRow, int rowEnd, JTable GameField) {
        for (int i = rowEnd; i >= 0; i--) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i + 1][j] = matrix[i][j];
            }
        }
        for (int j = 0; j < matrix[0].length; j++) {
            matrix[0][j] = field[numRow][j];
        }
        JTableUtils.writeArrayToJTable(GameField, matrix);
        TaskUtils.paintCell(GameField, matrix);
    }
    /**
     * Утилиты для Кубика Рубика:
     * 1) paintCellRubik
     */
    public static void paintCellRubik(JTable GameField, String[][] matrix) {
        for (int i = 0; i < GameField.getColumnCount(); i++) {
            GameField.getColumnModel().getColumn(i).setCellRenderer(new RenderRubikGame());
        }
    }
}

