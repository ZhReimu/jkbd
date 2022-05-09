package com.mrx.jkbd.ui.component;

import javax.swing.*;
import java.awt.*;

/**
 * @author Mr.X
 * @since 2022-05-09-0009
 **/
public class XTextView extends JTextArea {

    public XTextView() {
        setEditable(false);
        setLineWrap(true);
        setFont(new Font("微软雅黑", Font.BOLD, 20));
    }

}
