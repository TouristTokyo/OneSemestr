package ru.vsu.cs.Shemenev;

import ru.vsu.cs.util.JTableUtils;
import ru.vsu.cs.util.SwingUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


public class FrameVoglGame extends JFrame {
    private JPanel panelGame;
    private JTable GameField;
    private JButton buttonLeve1;
    private JButton buttonLeve2;
    private JButton buttonLeve3;
    private JButton buttonInfo;
    private List<Integer> cordCell = new ArrayList<>();
    private int countClick = 0;
    private String[][][] field = new String[][][]{{
            {" ", "O", "O", " ", " ", " ", " "},
            {"O", " ", "O", "O", " ", " ", " "},
            {"O", " ", " ", " ", " ", " ", " "},
            {"O", "O", " ", "O", "O", " ", " "},
            {"O", "O", " ", " ", " ", " ", " "},
            {" ", " ", " ", " ", " ", " ", " "},
            {"O", " ", " ", " ", " ", " ", " "}
    }, {
            {" ", " ", " ", " ", " ", " ", " "},
            {" ", " ", "O", " ", "O", " ", " "},
            {" ", " ", " ", " ", "O", " ", " "},
            {" ", " ", " ", "O", "O", "O", " "},
            {" ", " ", " ", "O", " ", " ", " "},
            {" ", " ", "O", "O", " ", " ", " "},
            {" ", " ", " ", "O", "O", " ", " "}
    }, {
            {" ", " ", "O", "O", " ", " ", " ", " "},
            {" ", " ", "O", "O", "O", " ", " ", " "},
            {" ", " ", " ", " ", " ", " ", " ", " "},
            {" ", " ", " ", "O", "O", " ", " ", " "},
            {" ", " ", " ", "O", "O", " ", " ", " "},
            {" ", " ", " ", " ", "O", "O", " ", "O"},
            {" ", " ", " ", "O", "O", " ", " ", " "},
            {" ", " ", " ", " ", " ", " ", " ", " "}
    }
    };
    private String[][] matrix;
    private boolean startLevel = true;
    private int numLevel = 1;

    public FrameVoglGame() {
        this.setTitle("Вогл(Demo)");
        this.setContentPane(panelGame);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();

        JTableUtils.initJTableForArray(GameField, 40, false, false, false, false);
        JTableUtils.writeArrayToJTable(GameField, field[0]);
        GameField.setRowHeight(25);


        GameField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                GameField.setSelectionBackground(Color.PINK);
                GameField.setSelectionForeground(Color.BLACK);
                if (startLevel) {
                    matrix = TaskUtils.newArrayGame1(field, numLevel - 1);
                    startLevel = false;
                }
                if (countClick < 2) {
                    cordCell.add(GameField.getSelectedRow());
                    cordCell.add(GameField.getSelectedColumn());
                    countClick++;
                    if (countClick == 2) {
                        LogicVoglGame.replace(cordCell, matrix, GameField);
                        cordCell.clear();
                        countClick = 0;
                    }
                }
                if (TaskUtils.checkWinGame1(matrix)) {
                    if (numLevel == 3) {
                        SwingUtils.showInfoMessageBox("Вы прошли 3 уровня Вогл(Demo)", "Вогл(Demo)");
                    } else {
                        SwingUtils.showInfoMessageBox("Вы выиграли!", "Вогл(Demo)_WIN");
                        numLevel++;
                        JTableUtils.writeArrayToJTable(GameField, field[numLevel - 1]);
                        startLevel = true;
                    }
                }
            }
        });

        buttonLeve1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numLevel = 1;
                JTableUtils.writeArrayToJTable(GameField, field[numLevel - 1]);
                startLevel = true;

            }
        });
        buttonLeve2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numLevel = 2;
                JTableUtils.writeArrayToJTable(GameField, field[numLevel - 1]);
                startLevel = true;

            }
        });
        buttonLeve3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numLevel = 3;
                JTableUtils.writeArrayToJTable(GameField, field[numLevel - 1]);
                startLevel = true;
            }
        });
        buttonInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtils.showInfoMessageBox("Задача: перешагивая шариком через шарики, убирать их с игрового поля.\n" +
                        "Как играть в Вогл: мышкой.", "Вогл(Demo)_INFO");
            }
        });
    }

}
