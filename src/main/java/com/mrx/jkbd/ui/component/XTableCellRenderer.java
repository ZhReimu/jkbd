package com.mrx.jkbd.ui.component;

import com.mrx.jkbd.ui.util.BoundedMapQueue;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class XTableCellRenderer extends DefaultTableCellRenderer {

    private static final BoundedMapQueue<Integer, Component> queue = new BoundedMapQueue<>(11 * 11);

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        int index = row * 11 + column;
        if (!queue.isFull()) {
            Component p = new DefaultTableCellRenderer().getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            queue.put(index, p);
        }
        Component p = queue.get(index);
        if (value != null) {
            if (value.equals("√")) {
                ((JLabel) p).setText("√");
                p.setBackground(Color.GREEN);
            } else if (value.equals("×")) {
                ((JLabel) p).setText("×");
                p.setBackground(Color.RED);
            }
        }
        return p;
    }

}