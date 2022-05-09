package com.mrx.jkbd.ui;

import com.mrx.jkbd.entity.DTOQuestion;
import com.mrx.jkbd.ui.component.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Random;

/**
 * @author Mr.X
 * @since 2022-05-08-0008
 **/
public class UI extends JFrame implements ActionListener {

    private final XTableModel tableModel = new XTableModel();

    private final XTableCellRenderer renderer = new XTableCellRenderer();

    private static final String PREV = "上一题";

    private static final String NEXT = "下一题";

    private static final String SUBMIT = "交卷";

    private final XTextView questionLabel = new XTextView();

    private final List<DTOQuestion> questions;

    private final BufferedImage img;

    private int col = 1;

    private int row = 1;

    public UI(List<DTOQuestion> questions) throws Throwable {
        this.questions = questions;
        img = ImageIO.read(new File("1.jpg"));
        initComponents();
        questionLabel.setText(getQuestion(0));
    }

    private void initComponents() {
        setLocationRelativeTo(null);
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
//        contentPane.add(questionLabel, BorderLayout.CENTER);

        // 右下角按钮区域
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.SOUTH);
        panel.setLayout(new BorderLayout(0, 0));
        JPanel panel_1 = new JPanel();
        panel.add(panel_1, BorderLayout.EAST);
        panel_1.setLayout(new GridLayout(1, 0, 0, 0));
        // 上一题 按钮
        JButton btnNewButton = new JButton(PREV);
        btnNewButton.addActionListener(this);
        panel_1.add(btnNewButton);
        // 下一题 按钮
        JButton btnNewButton_1 = new JButton(NEXT);
        btnNewButton_1.addActionListener(this);
        panel_1.add(btnNewButton_1);
        // 交卷 按钮
        JButton btnNewButton_3 = new JButton(SUBMIT);
        btnNewButton_3.addActionListener(this);
        panel_1.add(btnNewButton_3);

        // 右边做题状况区域
        JPanel panel_2 = new JPanel();
        contentPane.add(panel_2, BorderLayout.EAST);
        JTable table = new XTableView();
        table.setModel(tableModel);
        table.getColumnModel().getColumns().asIterator().forEachRemaining(it -> {
            it.setPreferredWidth(20);
            it.setCellRenderer(renderer);
        });
        panel_2.add(table);

        // 左边 题干, 图片 区域
        JPanel panel_3 = new JPanel();
        contentPane.add(panel_3, BorderLayout.CENTER);
        panel_3.setLayout(new GridLayout(2, 1, 0, 40));

        // 题干区域
        panel_3.add(questionLabel);
        // 图片区域
        XImageView imageView = new XImageView(img);
        panel_3.add(imageView);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch (e.getActionCommand()) {
            case NEXT:
                nextQuestion();
                break;
            case PREV:
                break;
            case SUBMIT:
                break;
            default:
                break;
        }
    }

    private void nextQuestion() {
        if (col > 10) {
            row++;
            col = 1;
        }
        if (row > 10) {
            JOptionPane.showMessageDialog(this, "所有题目已经做完!", "提示", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        questionLabel.setText(getQuestion(1));
        tableModel.setStatusOnNum(new Random().nextBoolean(), row, col++);
    }

    private String getQuestion(int position) {
        return questions.get(position).getStringQuestion(position + 1);
    }

}
