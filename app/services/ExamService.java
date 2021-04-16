package services;

import entities.Answer;
import entities.Exam;
import entities.Question;
import entities.db.DatabaseExecutionContext;
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


    @Inject
    public ExamService(JPAApi jpaApi, DatabaseExecutionContext executionContext, QuestionService questionService, AnswerService answerService) {
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
        this.questionService = questionService;
        this.answerService = answerService;
    }

    public void addQuestion(Long examId, QuestionForm questionForm) {
        Exam exam = getExam(examId);
        Question question = questionService.save(new Question(questionForm.getQuestionText(), exam));
        for (QuestionForm.AnswerForm answerForm: questionForm.getAnswers()) {
            answerService.save(new Answer(answerForm.getAnswerText(), answerForm.getCorrect(), question));
        }
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

    private List<Exam> list(EntityManager em) {
        return em.createQuery("select e from Exam e", Exam.class).getResultList();
    }

    public Exam updateJPA(EntityManager em, Exam exam) {
        em.merge(exam);
        return exam;
    }
}
