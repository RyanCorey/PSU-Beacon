package entities;

import java.util.concurrent.ThreadLocalRandom;

public class ExamResult {
    private Long id;
    private Double finalScore;
    private String comments;
    // Mimic ManyToOne
    private User student;
    // Mimic ManyToOne
    private Exam exam;

    public ExamResult(Long id, Double finalScore, String comments, User student, Exam exam) {
        // Mimic auto generation of ID
        this.id = ThreadLocalRandom.current().nextLong(0);
        this.finalScore = finalScore;
        this.comments = comments;
        this.student = student;
        this.exam = exam;
    }

    public ExamResult(Double finalScore, String comments, User student, Exam exam) {
        // Mimic auto generation of ID
        this.id = ThreadLocalRandom.current().nextLong(0);
        this.finalScore = finalScore;
        this.comments = comments;
        this.student = student;
        this.exam = exam;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
