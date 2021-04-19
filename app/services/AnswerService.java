package services;

import entities.Answer;
import entities.Question;
import entities.db.DatabaseExecutionContext;
import play.db.jpa.JPAApi;
import repositories.AnswerRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;


public class AnswerService implements AnswerRepository {
    private final Logger log = Logger.getLogger(AnswerService.class.toString());

    private final JPAApi jpaApi;

    private final DatabaseExecutionContext executionContext;


    @Inject
    public AnswerService(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
    }

    @Override
    public Answer save(Answer answer) {
        return wrap(em -> insert(em, answer));
    }

    @Override
    public Answer update(Answer answer) {
        return wrap(em -> updateJPA(em, answer));
    }

    @Override
    public Answer getAnswerById(Long examId) {
        return wrap(em -> getAnswerJPA(em, examId));
    }

    @Override
    public List<Answer> getAnswerListById(Long id) {
        return wrap(em -> getListAnswerJPA(em, id));
    }

    @Override
    public List<Answer> list() {
        return wrap(this::getList);
    }

    private <T> T wrap(Function<EntityManager, T> function) {
        return jpaApi.withTransaction(function);
    }

    public Answer getAnswerJPA(EntityManager em, Long examId) {
        return em.find(Answer.class, examId);
    }

    private Answer insert(EntityManager em, Answer answer) {
        em.persist(answer);
        return answer;
    }

    private List<Answer> getList(EntityManager em) {
        return em.createQuery("select e from Answer e", Answer.class).getResultList();
    }

    public List<Answer> getListAnswerJPA(EntityManager em, Long examId) {
        return em.createQuery("select e from Answer e where e.id IN :examId").setParameter("examId", examId).getResultList();
    }

    public Answer updateJPA(EntityManager em, Answer answer) {
        em.merge(answer);
        return answer;
    }
}
