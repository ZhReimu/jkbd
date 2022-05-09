package com.mrx.jkbd.ui;

import com.mrx.jkbd.entity.DecodedQuestion;
import com.mrx.jkbd.ui.component.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger logger = LoggerFactory.getLogger(UI.class);
    private final JPanel btnPanel = new JPanel();

    private static final String PREV = "上一题";

    private static final String NEXT = "下一题";

    private static final String SUBMIT = "交卷";

    private final XTextView questionLabel = new XTextView();

    private final List<DecodedQuestion> questions;

    private final BufferedImage img;

    private int col = 1;

    private int row = 1;

    public UI(List<DecodedQuestion> questions) throws Throwable {
        this.questions = questions;
        logger.debug("初始化题目: {}", questions.size());
        img = ImageIO.read(new File("1.jpg"));
        initComponents();
        questionLabel.setText(getQuestion(0));
    }

    private void initComponents() {
        setLocationRelativeTo(null);
        setTitle("驾考宝典");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 600);
        // 菜单栏
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu mnNewMenu = new JMenu("菜单");
        menuBar.add(mnNewMenu);
        JMenuItem menuAbout = new JMenuItem("About");
        menuAbout.addActionListener(this);
        mnNewMenu.add(menuAbout);
        // 主面板
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        // 右下角按钮区域
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.SOUTH);
        panel.setLayout(new BorderLayout(0, 0));

        panel.add(btnPanel, BorderLayout.EAST);
        btnPanel.setLayout(new GridLayout(1, 0, 0, 0));
        // 上一题 按钮
        addButton(PREV);
        // 下一题 按钮
        addButton(NEXT);
        // 交卷 按钮
        addButton(SUBMIT);
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
        int c = (row - 1) * 10 + col;
        System.out.println("第 " + c + " 题");
        questionLabel.setText(getQuestion(c));
        tableModel.setStatusOnNum(new Random().nextBoolean(), row, col++);
    }

    private String getQuestion(int position) {
        DecodedQuestion question = questions.get(position);
        int optionNum = question.getOptionCount();
        while (optionNum-- > 0) {
            addButton(String.valueOf((char) ('A' + optionNum)), 0);
        }
        return question.getStringQuestion(position + 1);
    }

    private void addButton(String action, int index) {
        JButton button = new JButton(action);
        button.addActionListener(this);
        btnPanel.add(button, index);
    }

    private void addButton(String action) {
        addButton(action, -1);
    }
}
