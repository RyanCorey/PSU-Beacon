package services.dto;

import entities.Exam;
import entities.User;

import java.util.List;

public class ExamResultDTO {
    List<Exam> exams;
    List<User> users;

    public ExamResultDTO() {
    }

    public ExamResultDTO(List<Exam> exams, List<User> users) {
        this.exams = exams;
        this.users = users;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
