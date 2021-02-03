package ru.vsu.cs.Shemenev;

import ru.vsu.cs.util.JTableUtils;
import ru.vsu.cs.util.SwingUtils;

import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class FrameKrestiksNoliksGame extends JFrame{
    private JPanel panelGame;
    private JTable FieldGame;
    private String[][] matrix = new String[][]{
            {" ", " ", " "},
            {" ", " ", " "},
            {" ", " ", " "}
    };
    private String[][] matrixGame;
    private boolean startGame = true;
    private int countStep = 1;
    private List<Integer> cordX = new ArrayList<>();
    private List<Integer> cordY = new ArrayList<>();
    public FrameKrestiksNoliksGame(){
        this.setTitle("Крестики нолики");
        this.setContentPane(panelGame);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();

        JTableUtils.initJTableForArray(FieldGame, 40, false, false, false, false);
        JTableUtils.writeArrayToJTable(FieldGame, matrix);
        FieldGame.setRowHeight(25);

        FieldGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(startGame){
                    matrixGame = TaskUtils.newArrayGame2(matrix);
                    startGame = false;
                }
                int x = FieldGame.getSelectedRow();
                int y = FieldGame.getSelectedColumn();
                if(TaskUtils.checkCell(x,y,cordX,cordY,FieldGame)){
                    SwingUtils.showInfoMessageBox("Неверный ход","Крестики нолики");
                    return;
                }
                cordX.add(x);
                cordY.add(y);
                LogicKrestiksNoliksGame.toWalkPerson(matrixGame,x,y,FieldGame,countStep);
                countStep++;
                if(TaskUtils.checkWinGame2(matrixGame)){
                    if(LogicKrestiksNoliksGame.checkWinGame2Cross(matrixGame)){
                        SwingUtils.showInfoMessageBox("Победили Крестики!","Крестики нолики");
                    }
                    else {
                        SwingUtils.showInfoMessageBox("Победили Нолики!","Крестики нолики");
                    }
                    JTableUtils.writeArrayToJTable(FieldGame,matrix);
                    countStep = 1;
                    startGame = true;
                    cordX.clear();
                    cordY.clear();
                }
                else if(LogicKrestiksNoliksGame.checkDraw(matrixGame)){
                    SwingUtils.showInfoMessageBox("Ничья!","Крестики нолики");
                    JTableUtils.writeArrayToJTable(FieldGame,matrix);
                    countStep = 1;
                    startGame = true;
                    cordX.clear();
                    cordY.clear();
                }
            }
        });
    }
}
