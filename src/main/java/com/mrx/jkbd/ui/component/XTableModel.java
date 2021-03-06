package com.mrx.jkbd.ui.component;

import javax.swing.table.DefaultTableModel;

/**
 * @author Mr.X
 * @since 2022-05-08-0008
 **/
public class XTableModel extends DefaultTableModel {

    private static final Object[][] data = new Object[][]{
            {null, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
            {1, null, null, null, null, null, null, null, null, null},
            {2, null, null, null, null, null, null, null, null, null},
            {3, null, null, null, null, null, null, null, null, null},
            {4, null, null, null, null, null, null, null, null, null},
            {5, null, null, null, null, null, null, null, null, null},
            {6, null, null, null, null, null, null, null, null, null},
            {7, null, null, null, null, null, null, null, null, null},
            {8, null, null, null, null, null, null, null, null, null},
            {9, null, null, null, null, null, null, null, null, null},
            {10, null, null, null, null, null, null, null, null, null}
    };

    private static final String[] columns = new String[11];

    public XTableModel() {
        super(data, columns);
    }

    public void setStatusOnNum(boolean status, int row, int col) {
        setValueAt(status ? "√" : "×", row, col);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
