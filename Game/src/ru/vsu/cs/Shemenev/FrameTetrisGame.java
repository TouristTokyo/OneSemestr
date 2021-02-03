package ru.vsu.cs.Shemenev;

import ru.vsu.cs.util.JTableUtils;
import ru.vsu.cs.util.SwingUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameTetrisGame extends JFrame {
    private JPanel panelGame;
    private JTable GameField;
    private JButton buttonRelease;
    private JButton buttonLeft;
    private JButton buttonTake;
    private JButton buttonRight;
    private JButton buttonGameOver;
    private JLabel labelTime;
    private JLabel labelPoints;
    private JButton buttonInfo;
    private String[][] field = new String[][]{
            {"Y", "R", "R", "B", "Y", "Y", "O", "O", "Y", "O"},
            {"R", "O", "O", "Y", "B", "O", "Y", "R", "B", "O"},
            {"B", "O", "R", "R", "O", "O", "R", "Y", "Y", "O"},
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
            {" ", " ", " ", " ", "-O-", " ", " ", " ", " ", " "}
    };
    private String[][] matrix = TaskUtils.matrixField(field);
    ;
    private boolean takeCell = false;
    private int countTakeCell = 0;
    private boolean GameOver = false;
    private int time = 15;
    private int countFieldDown = 3;
    private Timer timer = new Timer(1000, e -> {
        if (time > -1 && !GameOver) {
            labelTime.setText("" + time);
            time--;
        } else if (!GameOver) {
            time = 15;
            int rowEnd = TaskUtils.indexSearchDown(matrix);
            TaskUtils.FieldDown(field, matrix, countFieldDown - 1, rowEnd, GameField);
            if (rowEnd == matrix.length - 2) {
                GameOver = true;
                labelTime.setText("");
            }
            if (countFieldDown == 0) {
                countFieldDown = 3;
            }
        }
    });
    private int points = 0;

    public FrameTetrisGame() {
        this.setTitle("Tetris");
        this.setContentPane(panelGame);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();

        JTableUtils.initJTableForArray(GameField, 40, false, false, false, false);
        JTableUtils.writeArrayToJTable(GameField, field);
        TaskUtils.paintCell(GameField, field);
        GameField.setRowHeight(25);
        timer.start();
        labelPoints.setText("" + points);
        buttonRight.addActionListener(new ActionListener() {// Движение вправо
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!GameOver) {
                    int i = matrix.length - 1;
                    int j = TaskUtils.Geolocation(matrix);
                    if (i == matrix.length - 1 && j == matrix[0].length - 1) {
                        return;
                    }
                    matrix[i][j + 1] = matrix[i][j];
                    matrix[i][j] = " ";
                    JTableUtils.writeArrayToJTable(GameField, matrix);
                    TaskUtils.paintCell(GameField, matrix);
                } else {
                    timer.stop();
                    SwingUtils.showInfoMessageBox("Game Over", "Tetris");
                }
            }
        });
        buttonLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!GameOver) {
                    int i = matrix.length - 1;
                    int j = TaskUtils.Geolocation(matrix);
                    if (i == matrix.length - 1 && j == 0) {
                        return;
                    }
                    matrix[i][j - 1] = matrix[i][j];
                    matrix[i][j] = " ";
                    JTableUtils.writeArrayToJTable(GameField, matrix);
                    TaskUtils.paintCell(GameField, matrix);
                } else {
                    timer.stop();
                    SwingUtils.showInfoMessageBox("Game Over", "Tetris");
                }
            }
        });
        buttonTake.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!takeCell) {
                    int j = TaskUtils.Geolocation(matrix);
                    int i = TaskUtils.GeolocationColor(matrix, j);
                    if (i == -1) {
                        return;
                    }
                    String temp = matrix[i][j];
                    while (i > -1 && matrix[i][j].equals(temp)) {
                        matrix[i][j] = " ";
                        i--;
                        countTakeCell++;
                    }
                    matrix[field.length - 1][j] = "-O<" + temp + ">-";
                    JTableUtils.writeArrayToJTable(GameField, matrix);
                    TaskUtils.paintCell(GameField, matrix);
                    takeCell = true;
                } else if (GameOver) {
                    timer.stop();
                    SwingUtils.showInfoMessageBox("Game Over", "Tetris");
                }
            }
        });
        buttonRelease.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (takeCell && !GameOver) {
                    int j = TaskUtils.Geolocation(matrix);
                    int i = TaskUtils.GeolocationEmptyCell(matrix, j);
                    while (i < field.length && countTakeCell > 0) {
                        matrix[i][j] = matrix[matrix.length - 1][j];
                        matrix[i][j] = matrix[i][j].substring(3, 4);
                        countTakeCell--;
                        if (i == field.length - 1) {
                            GameOver = true;
                            timer.stop();
                            labelTime.setText("");
                            JTableUtils.writeArrayToJTable(GameField, matrix);
                            TaskUtils.paintCell(GameField, matrix);
                            SwingUtils.showInfoMessageBox("Game Over", "Tetris");
                            return;
                        }
                        i++;
                    }
                    if (!GameOver) {
                        matrix[matrix.length - 1][j] = "-O-";
                        takeCell = false;
                        points = LogicTetrisGame.gamePoints(matrix, j, GameField, points);
                        labelPoints.setText("" + points);
                        if(points>=1000){
                            SwingUtils.showInfoMessageBox("You win!","Tetris");
                            GameOver = true;
                        }
                    }
                } else if (GameOver) {
                    timer.stop();
                    SwingUtils.showInfoMessageBox("Game Over", "Tetris");
                }
            }
        });
        buttonGameOver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                matrix = TaskUtils.matrixField(field);
                JTableUtils.writeArrayToJTable(GameField, matrix);
                TaskUtils.paintCell(GameField, matrix);
                GameOver = false;
                takeCell = false;
                countFieldDown = 3;
                time = 15;
                labelTime.setText(""+time);
                timer.start();
                points = 0;
                labelPoints.setText("" + points);
            }
        });
        buttonInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                SwingUtils.showInfoMessageBox("Тетрис наоборот. Берем цветной блок и подбрасываем вверх.\n"+
                                "Цель: набррать свыше 1000 очков.",
                        "Tetris");
                timer.start();
            }
        });
    }
}
