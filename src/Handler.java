import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Handler implements ActionListener {
    Question question;

    Handler(Question q) {
        this.question = q;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JButton button = (JButton) actionEvent.getSource();
        if (button.getText().equals(question.getOptions()[question.getCorrectAnswer()].getText())) {
            question.setCorrect(question.getCorrect() + 1);
        }
        question.setQuestionNumber(question.getQuestionNumber() + 1);
        question.getNumber().setText(String.format("%d of 20", question.getQuestionNumber()));
        question.setQuestion();
        question.buildFrame();
        if (question.getQuestionNumber() == 21) {
            question.endMessage(question.getCorrect());
        }
    }
}
