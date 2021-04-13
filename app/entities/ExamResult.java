package entities;

import javax.persistence.*;

@Entity
public class ExamResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double finalScore;

    private String comments;

    @ManyToOne(fetch = FetchType.EAGER)
    private User student;

    @ManyToOne(fetch = FetchType.EAGER)
    private Exam exam;


    public ExamResult(Double finalScore, String comments, User student, Exam exam) {
        this.finalScore = finalScore;
        this.comments = comments;
        this.student = student;
        this.exam = exam;
    }

    public ExamResult() {

    }

    public Long getId() {
        return id;
    }

    public Double getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(Double finalScore) {
        this.finalScore = finalScore;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}
