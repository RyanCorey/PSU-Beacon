package services;

import entities.Answer;
import entities.Exam;
import entities.Question;
import entities.db.DatabaseExecutionContext;
import forms.AnswerForm;
import forms.ExamForm;
import forms.QuestionForm;
import play.db.jpa.JPAApi;
import repositories.ExamRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;


public class ExamService implements ExamRepository {
    private final Logger log = Logger.getLogger(ExamService.class.toString());

    private final JPAApi jpaApi;

    private final DatabaseExecutionContext executionContext;

    private final QuestionService questionService;

    private final AnswerService answerService;

    private final UserService userService;

    @Inject
    public ExamService(JPAApi jpaApi, DatabaseExecutionContext executionContext, QuestionService questionService, AnswerService answerService, UserService userService) {
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
        this.questionService = questionService;
        this.answerService = answerService;
        this.userService = userService;
    }


    public void addQuestion(Long examId, QuestionForm questionForm) {
        Exam exam = getExam(examId);
        Question question = questionService.save(new Question(questionForm.getQuestionText(), exam));
        for (QuestionForm.AnswerForm answerForm: questionForm.getAnswers()) {
            answerService.save(new Answer(answerForm.getAnswerText(), answerForm.getCorrect(), question));
        }
    }

    public void addAnswer(Long examId, Long questionId, AnswerForm answerForm) {
        var exam = getExam(examId);
        var question = questionService.getQuestionById(questionId);
        answerService.save(new Answer(answerForm.getAnswerText(), answerForm.getCorrect(), question));
    }

    public Exam createExam(ExamForm examForm) {
        var user = userService.getUserById(examForm.getProfessor());
        Exam exam = new Exam(examForm.getExamTitle(), user);
        return save(exam);
    }

    public void deleteQuestion(Long questionId) {
        var foundQuestion = questionService.getQuestionById(questionId);
        for (var answer: foundQuestion.getAnswers()) {
            answerService.delete(answer.getId());
        }
        questionService.delete(foundQuestion.getId());
    }

    public void deleteAnswer(Long answerId) {
        answerService.delete(answerId);
    }

    @Override
    public Exam save(Exam exam) {
        return wrap(em -> insert(em, exam));
    }

    @Override
    public Exam update(Exam exam) {
        return wrap(em -> updateJPA(em, exam));
    }

    @Override
    public void delete(Long id) {
        var foundExam = getExamById(id);
        for (var question : foundExam.getQuestions()) {
            for (var answer : question.getAnswers()) {
                answerService.delete(answer.getId());
            }
            questionService.delete(question.getId());
        }
        wrap(em -> deleteJPA(em, id));
    }

    @Override
    public Exam getExamById(Long examId) {
        return wrap(entityManager -> getExamJPA(entityManager, examId));
    }

    @Override
    public Exam getExam(Long id) {
        return wrap(em -> getExamJPA(em, id));
    }

    @Override
    public List<Exam> list() {
        return wrap(this::list);
    }

    private <T> T wrap(Function<EntityManager, T> function) {
        return jpaApi.withTransaction(function);
    }

    public Exam getExamJPA(EntityManager em, Long examId) {
        return em.find(Exam.class, examId);
    }

    private Exam insert(EntityManager em, Exam exam) {
        em.persist(exam);
        return exam;
    }
    public int deleteJPA(EntityManager em, Long id) {
        return em.createQuery("DELETE FROM Exam WHERE id = " + id).executeUpdate();
    }

    private List<Exam> list(EntityManager em) {
        return em.createQuery("select e from Exam e", Exam.class).getResultList();
    }

    public Exam updateJPA(EntityManager em, Exam exam) {
        em.merge(exam);
        return exam;
    }
}
