package com.mrx.jkbd.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * @author Mr.X
 * @since 2022-05-08-0008
 **/
public class QuestionTableCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (value == null) return component;
        if (value.equals("√")) {
            component.setBackground(Color.GREEN);
        } else if (value.equals("×")) {
            component.setBackground(Color.RED);
        }
        return component;
    }
}
