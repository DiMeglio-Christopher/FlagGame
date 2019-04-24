import org.apache.commons.io.FilenameUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Question {
    private int correctAnswer;
    private JButton[] options;
    private JLabel picture;
    private int correct;
    private JFrame frame;
    private File dir;
    private ArrayList<File> fileList;
    private JLabel label;
    private int questionNumber;

    public JLabel getNumber() {
        return number;
    }

    public void setNumber(JLabel number) {
        this.number = number;
    }

    private JLabel number;

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    Question(JButton[] buttons, JLabel img, JFrame f, JLabel l, JLabel l2) {
        this.options = buttons;
        this.picture = img;
        this.frame = f;
        this.label = l;
        this.questionNumber = 1;
        this.number = l2;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public JButton[] getOptions() {
        return options;
    }

    public void setOptions(JButton[] options) {
        this.options = options;
    }

    public JLabel getPicture() {
        return picture;
    }

    public void setPicture(JLabel picture) {
        this.picture = picture;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public void setQuestion() {
        Random rand = new Random();
        String fileName;
        correctAnswer = rand.nextInt(4);

        for (int i = 0; i < options.length; i++) {
            int randNumber;
            do {
                randNumber = rand.nextInt(fileList.size());
                File randomImage = fileList.get(randNumber);
                fileName = FilenameUtils.removeExtension(randomImage.getName());
                if (i == correctAnswer) {
                    picture.setIcon(new ImageIcon(randomImage.getAbsolutePath()));
                }
            } while (nameExists(fileName));
            if (i == correctAnswer) {
                fileList.remove(randNumber);
            }
            options[i].setText(fileName);
        }
    }

    public boolean nameExists(String imageName) {
        for (int i = 0; i < 3; i++) {
            if (imageName.equals(options[i].getText())) {
                break;
            } else if (i == 2) {
                return false;
            }
        }
        return true;
    }

    public void buildFrame() {
        label.setText("Number Correct: " + correct);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void setImageFiles() {
        dir = new File("src/../assets/images");
        fileList = new ArrayList<File>(Arrays.asList(Objects.requireNonNull(dir.listFiles())));
    }

    public void endMessage(int numberCorrect) {
        frame.dispose();
        JFrame messageFrame = new JFrame();
        messageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel messageLabel = new JLabel(String.format("You Finished With %d Correct Out Of 20 (%.02f%%)", numberCorrect, (float)(numberCorrect / 20.0) * 100));
        messageLabel.setFont(new Font("Times New Roman", Font.PLAIN, 48));
        messageFrame.add(messageLabel);
        messageFrame.pack();
        messageFrame.setLocationRelativeTo(null);
        messageFrame.setVisible(true);
    }
}
