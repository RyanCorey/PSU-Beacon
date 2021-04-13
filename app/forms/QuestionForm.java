package forms;

import java.util.List;

public class QuestionForm {
    private String questionText;
    private List<AnswerForm> answers;

    public QuestionForm() {
    }

    public QuestionForm(String questionText, List<AnswerForm> answers) {
        this.questionText = questionText;
        this.answers = answers;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<AnswerForm> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerForm> answers) {
        this.answers = answers;
    }

    public static class AnswerForm {
        private String answerText;
        private boolean isCorrect = false;

        public AnswerForm() {
        }

        public AnswerForm(String answerText, boolean isCorrect) {
            this.answerText = answerText;
            this.isCorrect = isCorrect;
        }

        public String getAnswerText() {
            return answerText;
        }

        public void setAnswerText(String answerText) {
            this.answerText = answerText;
        }

        public boolean getCorrect() {
            return isCorrect;
        }

        public void setCorrect(boolean correct) {
            isCorrect = correct;
        }
    }
}
