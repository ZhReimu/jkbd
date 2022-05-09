package com.mrx.jkbd.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * @author Mr.X
 * @since 2022-05-08-0008
 **/
public class XTableCellRenderer extends DefaultTableCellRenderer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (value == null) return component;
        if (value.equals("√")) {
            logger.debug("设置绿色: {}", value);
            component.setBackground(Color.GREEN);
        } else if (value.equals("×")) {
            logger.debug("设置红色: {}", value);
            component.setBackground(Color.RED);
        }
        return component;
    }
}
