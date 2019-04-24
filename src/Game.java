import javax.swing.*;
import java.awt.*;

public class Game {
    public static void main(String[] args) {
        JButton[] buttons = new JButton[4];
        JFrame frame = new JFrame();
        JPanel panel = new JPanel(new BorderLayout());
        JPanel subPanel = new JPanel(new GridLayout(2, 1));
        JPanel answerPanel = new JPanel(new FlowLayout());
        JPanel infoPanel = new JPanel(new BorderLayout());
        JLabel pic = new JLabel();
        pic.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel number = new JLabel();
        number.setHorizontalAlignment(JLabel.CENTER);
        subPanel.add(answerPanel);
        JLabel count = new JLabel();
        count.setHorizontalAlignment(JLabel.CENTER);
        Question question = new Question(buttons, pic, frame, count, number);
        number.setText(String.format("%d of 20", question.getQuestionNumber()));
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            answerPanel.add(buttons[i]);
        }
        for (JButton button : buttons) {
            button.addActionListener(new Handler(question));
        }
        infoPanel.add(count, BorderLayout.NORTH);
        infoPanel.add(number, BorderLayout.SOUTH);
        subPanel.add(infoPanel);
        frame.add(panel);
        panel.add(subPanel, BorderLayout.SOUTH);
        panel.add(pic, BorderLayout.CENTER);
        question.setImageFiles();
        question.setQuestion();
        question.buildFrame();
    }
}
