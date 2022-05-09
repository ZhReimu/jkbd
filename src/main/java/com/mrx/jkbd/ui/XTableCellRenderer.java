package com.mrx.jkbd.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class XTableCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // TODO: 2022-05-09-0009 Mr.X 复用 Component 对象
        Component p = new DefaultTableCellRenderer().getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (value != null) {
            if (value.equals("√")) {
                p.setBackground(Color.GREEN);
            } else if (value.equals("×")) {
                p.setBackground(Color.RED);
            }
        }
        return p;
    }

}