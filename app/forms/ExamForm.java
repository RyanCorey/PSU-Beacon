package forms;

import entities.User;

public class ExamForm {
    private String examTitle;
    private Long professorId;

    public ExamForm() {
    }

    public ExamForm(String examTitle, Long professorId) {
        this.examTitle = examTitle;
        this.professorId = professorId;
    }

    public String getExamTitle() {
        return examTitle;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    public Long getProfessor() {
        return professorId;
    }

    public void setProfessor(Long professorId) {
        this.professorId = professorId;
    }
}
