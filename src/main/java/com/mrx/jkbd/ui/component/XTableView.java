package com.mrx.jkbd.ui.component;

import javax.swing.*;

/**
 * @author Mr.X
 * @since 2022-05-09-0009
 **/
public class XTableView extends JTable {

    public XTableView() {
        cellSelectionEnabled = false;
        rowSelectionAllowed = false;
        setColumnSelectionAllowed(false);
        setFocusable(false);
    }

}
