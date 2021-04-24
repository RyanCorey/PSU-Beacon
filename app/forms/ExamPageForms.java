package forms;

import play.data.Form;

public class ExamPageForms {
    private Form<QuestionForm> questionForm;
    private Form<AnswerForm> answerForm;

    public ExamPageForms() {
    }

    public ExamPageForms(Form<QuestionForm> questionForm, Form<AnswerForm> answerForm) {
        this.questionForm = questionForm;
        this.answerForm = answerForm;
    }

    public Form<QuestionForm> getQuestionForm() {
        return questionForm;
    }

    public void setQuestionForm(Form<QuestionForm> questionForm) {
        this.questionForm = questionForm;
    }

    public Form<AnswerForm> getAnswerForm() {
        return answerForm;
    }

    public void setAnswerForm(Form<AnswerForm> answerForm) {
        this.answerForm = answerForm;
    }
}
