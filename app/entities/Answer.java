package entities;

import java.util.concurrent.ThreadLocalRandom;

public class Answer {
    private Long id;
    private String answerText;
    private Boolean isCorrect;

    public Answer() {
    }

    public Answer(String answerText, Boolean isCorrect) {
        // Mimic auto generation of ID
        this.id = ThreadLocalRandom.current().nextLong(0);
        this.answerText = answerText;
        this.isCorrect = isCorrect;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
