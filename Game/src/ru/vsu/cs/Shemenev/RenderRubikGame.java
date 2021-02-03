package ru.vsu.cs.Shemenev;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class RenderRubikGame  extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(value.equals("R")){
            cell.setBackground(Color.RED);
            cell.setForeground(Color.RED);
        }
        else if(value.equals("B")){
            cell.setBackground(Color.BLUE);
            cell.setForeground(Color.BLUE);
        }
        else if(value.equals("Y")){
            cell.setBackground(Color.YELLOW);
            cell.setForeground(Color.YELLOW);
        }
        else if(value.equals("G")){
            cell.setBackground(Color.GREEN);
            cell.setForeground(Color.GREEN);
        }
        else {
            cell.setBackground(Color.BLACK);
        }
        return cell;
    }
}

