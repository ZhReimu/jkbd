package com.mrx.jkbd.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Mr.X
 * @since 2022-05-08-0008
 **/
public class XImageView extends JLabel {

    private static final int WIDTH = 400;

    private static final int HEIGHT = 400;

    private XImageView() {

    }

    public XImageView(BufferedImage img) {
        BufferedImage scaleImg = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        scaleImg.getGraphics().drawImage(img.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH), 0, 0, null);
        setIcon(new ImageIcon(scaleImg));
        setHorizontalAlignment(JLabel.CENTER);
    }

}
