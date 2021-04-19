package services;

import entities.Answer;
import entities.Exam;
import entities.Question;
import entities.db.DatabaseExecutionContext;
import forms.QuestionForm;
import play.db.jpa.JPAApi;
import repositories.QuestionRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;

public class QuestionService implements QuestionRepository {
    private final Logger log = Logger.getLogger(QuestionService.class.toString());

    private final JPAApi jpaApi;

    private final DatabaseExecutionContext executionContext;


    @Inject
    public QuestionService(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
    }

    @Override
    public Question save(Question question) {
        return wrap(em -> insert(em, question));
    }

    @Override
    public Question update(Question question) {
        return wrap(em -> updateJPA(em, question));
    }

    @Override
    public Question getQuestionById(Long examId) {
        return wrap(em -> getQuestionJPA(em, examId));
    }

    @Override
    public List<Question> getListQuestionJPA(Long examId) {
        return wrap(em -> getListQuestionJPA(em, examId));
    }

    @Override
    public List<Question> list() {
        return wrap(entityManager -> list());
    }

    @Override
    public List<Question> getQuestionListById(Long id) {
        return wrap(em -> getListQuestionJPA(em, id));
    }

    private <T> T wrap(Function<EntityManager, T> function) {
        return jpaApi.withTransaction(function);
    }

    public Question getQuestionJPA(EntityManager em, Long examId) {
        return em.find(Question.class, examId);
    }

    private Question insert(EntityManager em, Question question) {
        em.persist(question);
        return question;
    }

    private List<Question> list(EntityManager em) {
        return em.createQuery("select e from Question e", Question.class).getResultList();
    }

    public List<Question> getListQuestionJPA(EntityManager em, Long examId) {
        return em.createQuery("select e from Question e where e.exam.id IN :examId").setParameter("examId", examId).getResultList();
    }

    /*private List<Question> getQuestionListById(EntityManager em) {
        return em.createQuery("select e from Question e where e.exam.id IN :examId", Question.class).getResultList();
    }*/

    public Question updateJPA(EntityManager em, Question question) {
        em.merge(question);
        return question;
    }
}
