/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author mtripo225
 */
public class Exam {
    private long id;
    private String examTitle;
    private Set<Question> questions;
    
    public Exam() {
        
    }
    
    public Exam(long id, String examTitle) {
        this.id = id;
        this.examTitle = examTitle;
    }
    
    public Exam(long id, String examTitle, Set<Question> questions) {
        this.id = id;
        this.examTitle = examTitle;
        this.questions = questions;
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
