package com.mrx.jkbd.ui;

import com.mrx.jkbd.ui.component.XImageView;
import com.mrx.jkbd.ui.component.XTableCellRenderer;
import com.mrx.jkbd.ui.component.XTableModel;
import com.mrx.jkbd.ui.component.XTableView;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

/**
 * @author Mr.X
 * @since 2022-05-08-0008
 **/
public class UI extends JFrame implements ActionListener {

    private final XTableModel tableModel = new XTableModel();

    private final XTableCellRenderer renderer = new XTableCellRenderer();

    private int col = 1;

    private int row = 1;

    public UI() throws Throwable {
        setLocationRelativeTo(null);
        BufferedImage img = ImageIO.read(new File("1.jpg"));
        setTitle("驾考宝典");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 600);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnNewMenu = new JMenu("菜单");
        menuBar.add(mnNewMenu);

        JMenuItem menuAbout = new JMenuItem("About");
        menuAbout.addActionListener(this);
        mnNewMenu.add(menuAbout);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JTextArea textArea = new JTextArea();
        contentPane.add(textArea, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.SOUTH);
        panel.setLayout(new BorderLayout(0, 0));

        JPanel panel_1 = new JPanel();
        panel.add(panel_1, BorderLayout.EAST);
        panel_1.setLayout(new GridLayout(1, 0, 0, 0));

        JButton btnNewButton = new JButton("Prev");
        btnNewButton.addActionListener(this);
        panel_1.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Next");
        btnNewButton_1.addActionListener(this);
        panel_1.add(btnNewButton_1);

        JButton btnNewButton_3 = new JButton("Exit");
        btnNewButton_3.addActionListener(this);
        panel_1.add(btnNewButton_3);

        JPanel panel_2 = new JPanel();
        contentPane.add(panel_2, BorderLayout.EAST);

        JTable table = new XTableView();
        table.setModel(tableModel);
        table.getColumnModel().getColumns().asIterator().forEachRemaining(it -> {
            it.setPreferredWidth(20);
            it.setCellRenderer(renderer);
        });
        panel_2.add(table);

        JPanel panel_3 = new JPanel();
        contentPane.add(panel_3, BorderLayout.CENTER);
        panel_3.setLayout(new GridLayout(2, 1, 0, 40));

        JTextPane textPane = new JTextPane();
        panel_3.add(textPane);

        XImageView imageView = new XImageView(img);
        panel_3.add(imageView);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if (col > 10) {
            row++;
            col = 1;
        }
        if (row > 10) {
            JOptionPane.showMessageDialog(this, "测试", "提示", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        tableModel.setStatusOnNum(new Random().nextBoolean(), row, col++);
    }

}
