package entities;

import javax.persistence.*;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String answerText;

    private Boolean isCorrect;

    @ManyToOne(fetch = FetchType.EAGER)
    private Question question;

    public Answer() {
    }

    public Answer(String answerText, Boolean isCorrect, Question question) {
        this.answerText = answerText;
        this.isCorrect = isCorrect;
        this.question = question;
    }

    public Long getId() {
        return id;
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

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
