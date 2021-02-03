package ru.vsu.cs.Shemenev;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class RenderTetrisGame extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(value.equals("R") || value.equals("-O<R>-")){
            cell.setBackground(Color.RED);
            cell.setForeground(Color.RED);
        }
        else if(value.equals("B") || value.equals("-O<B>-")){
            cell.setBackground(Color.BLUE);
            cell.setForeground(Color.BLUE);
        }
        else if(value.equals("Y") || value.equals("-O<Y>-")){
            cell.setBackground(Color.YELLOW);
            cell.setForeground(Color.YELLOW);
        }
        else if(value.equals("O") || value.equals("-O<O>-")){
            cell.setBackground(Color.ORANGE);
            cell.setForeground(Color.ORANGE);
        }
        else {
            cell.setBackground(Color.WHITE);
            cell.setForeground(Color.BLACK);
        }
        return cell;
    }

}
