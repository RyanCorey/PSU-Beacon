/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import org.checkerframework.checker.units.qual.A;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author mtripo225
 */
public class Question implements Serializable{
    private long id;
    
    private String questionText;
    
    private Exam exam;

    private Set<Answer> answers;
    
    public Question() {}
    
    public Question(String questionText, Exam exam) {
        
    }
    
    public Question(long id, String questionText, Exam exam, Set<Answer> answers) {
        // Mimic auto generation of ID
        this.id = ThreadLocalRandom.current().nextLong(0);
        this.questionText = questionText;
        this.exam = exam;
        this.answers = answers;
    }
    
    public long getId() {
        return this.id;
    }
    
    public String getQuestionText() { return this.questionText; }
    
    public Exam getExam() { return this.exam; }
    
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
    
    public void setExam(Exam exam) {
        this.exam = exam;
    }
}
 