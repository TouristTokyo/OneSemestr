package ru.vsu.cs.Shemenev;

import ru.vsu.cs.util.JTableUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;


public class FrameRubikGame extends JFrame {

    private JPanel panelGame;
    private JTable GameField;
    private JButton buttonUpLeft;
    private JButton buttonDownLeft;
    private JButton buttonCenter;
    private JButton buttonUpRight;
    private JButton buttonDownRight;
    private String[][] matrixColor = new String[][]{
            {"R","R"," ","G","G"},
            {"R","R"," ","G","G"},
            {" "," "," "," "," "},
            {"Y","Y"," ","B","B"},
            {"Y","Y"," ","B","B"},
    };

    public FrameRubikGame(){
        this.setTitle("Кубик Рубика");
        this.setContentPane(panelGame);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();

        JTableUtils.initJTableForArray(GameField, 40, false, false, false, false);
        JTableUtils.writeArrayToJTable(GameField, matrixColor);
        TaskUtils.paintCellRubik(GameField, matrixColor);
        GameField.setRowHeight(25);

        buttonUpLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogicRubikGame.matrixLeftUpTurn(matrixColor);
                JTableUtils.writeArrayToJTable(GameField, matrixColor);
                TaskUtils.paintCellRubik(GameField, matrixColor);
            }
        });
        buttonDownLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogicRubikGame.matrixLeftDownTurn(matrixColor);
                JTableUtils.writeArrayToJTable(GameField, matrixColor);
                TaskUtils.paintCellRubik(GameField, matrixColor);
            }
        });
        buttonCenter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogicRubikGame.matrixCenterTurn(matrixColor);
                JTableUtils.writeArrayToJTable(GameField, matrixColor);
                TaskUtils.paintCellRubik(GameField, matrixColor);
            }
        });
        buttonUpRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogicRubikGame.matrixRightUpTurn(matrixColor);
                JTableUtils.writeArrayToJTable(GameField, matrixColor);
                TaskUtils.paintCellRubik(GameField, matrixColor);
            }
        });
        buttonDownRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogicRubikGame.matrixRightDownTurn(matrixColor);
                JTableUtils.writeArrayToJTable(GameField, matrixColor);
                TaskUtils.paintCellRubik(GameField, matrixColor);
            }
        });
    }
}
