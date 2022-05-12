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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mr.X
 * @since 2022-05-08-0008
 **/
public class UI extends JFrame implements ActionListener {

    private final XTableModel tableModel = new XTableModel();

    private final XTableCellRenderer renderer = new XTableCellRenderer();

    private static final Logger logger = LoggerFactory.getLogger(UI.class);

    private final JPanel btnPanel = new JPanel();

    private static final String NEXT = "下一题";

    private static final String SUBMIT = "交卷";

    private final XTextView questionLabel = new XTextView();

    private final List<DecodedQuestion> questions;

    private final BufferedImage img;

    private final Map<String, JButton> options = new HashMap<>();

    private int col = 1;

    private int row = 1;

    private DecodedQuestion question;

    public UI(List<DecodedQuestion> questions) throws Throwable {
        this.questions = questions;
        logger.debug("初始化题目: {}", questions.size());
        img = ImageIO.read(new File("1.jpg"));
        logger.debug("初始化界面");
        initComponents();
        questionLabel.setText(getQuestion(0));
        logger.debug("界面初始化完毕");
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
        initOptionButtons();
    }

    private void initOptionButtons() {
        int optionNum = 8;
        while (optionNum-- > 0) {
            addButton(String.valueOf((char) ('A' + optionNum)), 0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().length() == 1) {
            question.setYourAnswer(e.getActionCommand().toCharArray()[0]);
            return;
        }
        switch (e.getActionCommand()) {
            case NEXT:
                nextQuestion();
                break;
            case SUBMIT:
                submit();
                break;
        }
    }

    private void nextQuestion() {
        if (col > 10) {
            row++;
            col = 1;
        }
        if (row > 10) {
            showDialog("所有题目已经做完!", "提示", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (!question.isCorrect()) {
            showDialog("你做错辣, 正确答案是: " + question.getAnswer() + "\n" + question.getExplain(), "错误", JOptionPane.ERROR_MESSAGE);
        }
        int c = (row - 1) * 10 + col;
        tableModel.setStatusOnNum(question.isCorrect(), row, col++);
        questionLabel.setText(getQuestion(c));
    }

    private void submit() {
        int res = JOptionPane.showConfirmDialog(this, "确定要交卷吗?", "提示", JOptionPane.YES_NO_OPTION);
        if (res == 0) {
            long score = questions.stream().filter(DecodedQuestion::isCorrect).count();
            showDialog("交卷成功, 您的分数为: " + score + "分", "提示", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    private String getQuestion(int position) {
        question = questions.get(position);
        logger.debug("question: {}", question);
        removeOptions();
        addOptions(question.getOptionCount());
        return question.getStringQuestion(position + 1);
    }

    private void removeOptions() {
        options.forEach((k, v) -> btnPanel.remove(v));
    }

    private void addOptions(int num) {
        int optionNum = num;
        while (optionNum-- > 0) {
            String key = String.valueOf((char) ('A' + optionNum));
            JButton button = options.get(key);
            button.setVisible(true);
            btnPanel.add(button, 0);
        }
    }

    private void addButton(String action, int index) {
        JButton button = new JButton(action);
        button.addActionListener(this);
        if (index == 0) {
            options.put(action, button);
            button.setVisible(false);
            return;
        }
        btnPanel.add(button, index);
    }

    private void addButton(String action) {
        addButton(action, -1);
    }

    private void showDialog(String msg, String title, int messageType) {
        JOptionPane.showMessageDialog(this, msg, title, messageType);
    }

}
