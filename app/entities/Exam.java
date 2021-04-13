/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.*;
import java.util.Set;

/**
 *
 * @author mtripo225
 */
@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String examTitle;

    @OneToMany(mappedBy="exam", fetch = FetchType.EAGER)
    private Set<Question> questions;

    //ManyToOne
    @ManyToOne(fetch = FetchType.EAGER)
    private User professor;
    
    public Exam() {
        
    }
    
    public Exam(long id, String examTitle, User professor) {
        this.examTitle = examTitle;
        this.professor = professor;
    }
    
    public Exam(long id, String examTitle, Set<Question> questions) {
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
