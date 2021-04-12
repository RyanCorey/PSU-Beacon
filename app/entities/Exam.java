/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author mtripo225
 */
public class Exam {
    private long id;
    private String examTitle;
    private Set<Question> questions;
    //ManyToOne Mimic
    private User professor;
    
    public Exam() {
        
    }
    
    public Exam(long id, String examTitle, User professor) {
        // Mimic auto generation of ID
        this.id = ThreadLocalRandom.current().nextLong(0);
        this.examTitle = examTitle;
        this.professor = professor;
    }
    
    public Exam(long id, String examTitle, Set<Question> questions) {
        // Mimic auto generation of ID
        this.id = ThreadLocalRandom.current().nextLong(0);
        this.examTitle = examTitle;
        this.questions = questions;
    }

    public User getProfessor() {
        return professor;
    }

    public void setProfessor(User professor) {
        this.professor = professor;
    }

    public long getId() {
        return this.id;
    }
    
    public String getExamTitle() {
        return this.examTitle;
    }
    
    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }
    
    public Set<Question> getQuestions() {
        return this.questions;
    }
    
    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
}
