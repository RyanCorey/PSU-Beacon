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
public class Question{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private String questionText;

    @ManyToOne(fetch = FetchType.EAGER)
    private Exam exam;

    @OneToMany(mappedBy="question", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Answer> answers;
    
    public Question() {}
    
    public Question(String questionText, Exam exam) {
        this.questionText = questionText;
        this.exam = exam;
    }
    
    public Question(String questionText, Exam exam, Set<Answer> answers) {
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

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }
}
 