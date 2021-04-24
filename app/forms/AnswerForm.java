package forms;

public class AnswerForm {

    private String answerText;

    private Boolean isCorrect = false;

    public AnswerForm() {
    }

    public AnswerForm(String answerText, Boolean isCorrect) {
        this.answerText = answerText;
        this.isCorrect = isCorrect;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }
}
