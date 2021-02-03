package ru.vsu.cs.Shemenev;


public class LogicRubikGame {
    public static String[][] matrixLeftUpTurn(String[][] matrixColor){
        String saveCell1 = matrixColor[0][0];
        String saveCell2 = matrixColor[0][1];
        String saveCell3 = matrixColor[1][1];
        String saveCell4 = matrixColor[1][0];
        matrixColor[0][0] = saveCell4;
        matrixColor[0][1] = saveCell1;
        matrixColor[1][1] = saveCell2;
        matrixColor[1][0] = saveCell3;
        return matrixColor;
    }
    public static String[][] matrixLeftDownTurn(String[][] matrixColor){
        String saveCell1 = matrixColor[3][0];
        String saveCell2 = matrixColor[3][1];
        String saveCell3 = matrixColor[4][1];
        String saveCell4 = matrixColor[4][0];
        matrixColor[3][0] = saveCell4;
        matrixColor[3][1] = saveCell1;
        matrixColor[4][1] = saveCell2;
        matrixColor[4][0] = saveCell3;
        return matrixColor;
    }
    public static String[][] matrixRightUpTurn(String[][] matrixColor){
        String saveCell1 = matrixColor[0][3];
        String saveCell2 = matrixColor[0][4];
        String saveCell3 = matrixColor[1][4];
        String saveCell4 = matrixColor[1][3];
        matrixColor[0][3] = saveCell4;
        matrixColor[0][4] = saveCell1;
        matrixColor[1][4] = saveCell2;
        matrixColor[1][3] = saveCell3;
        return matrixColor;
    }
    public static String[][] matrixRightDownTurn(String[][] matrixColor){
        String saveCell1 = matrixColor[3][3];
        String saveCell2 = matrixColor[3][4];
        String saveCell3 = matrixColor[4][4];
        String saveCell4 = matrixColor[4][3];
        matrixColor[3][3] = saveCell4;
        matrixColor[3][4] = saveCell1;
        matrixColor[4][4] = saveCell2;
        matrixColor[4][3] = saveCell3;
        return matrixColor;
    }
    public static String[][] matrixCenterTurn(String[][] matrixColor){
        String saveCell1 = matrixColor[1][1];
        String saveCell2 = matrixColor[1][3];
        String saveCell3 = matrixColor[3][3];
        String saveCell4 = matrixColor[3][1];
        matrixColor[1][1] = saveCell4;
        matrixColor[1][3] = saveCell1;
        matrixColor[3][3] = saveCell2;
        matrixColor[3][1] = saveCell3;
        return matrixColor;
    }
}
