package services;

import entities.ExamResult;
import entities.db.DatabaseExecutionContext;
import play.db.jpa.JPAApi;
import repositories.ExamResultRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;


public class ExamResultService implements ExamResultRepository{
    private final Logger log = Logger.getLogger(ExamResultService.class.toString());

    private final JPAApi jpaApi;

    private final DatabaseExecutionContext executionContext;

    @Inject
    public ExamResultService(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
    }

    @Override
    public ExamResult save(ExamResult examResult) {
        return wrap(em -> insert(em, examResult));
    }

    @Override
    public ExamResult update(ExamResult examResult) {
        return wrap(em -> updateJPA(em, examResult));
    }

    @Override
    public ExamResult getExamResultById(Long examId) {
        return wrap(em -> getExamResultJPA(em, examId));
    }

    @Override
    public List<ExamResult> getExamResultListById(Long examId) {
        return wrap((this::list));
    }

    @Override
    public List<ExamResult> list() {
        return wrap(this::list);
    }


    private <T> T wrap(Function<EntityManager, T> function) {
        return jpaApi.withTransaction(function);
    }

    public ExamResult getExamResultJPA(EntityManager em, Long examId) {
        return em.find(ExamResult.class, examId);
    }

    private ExamResult insert(EntityManager em, ExamResult examResult) {
        em.persist(examResult);
        return examResult;
    }

    private List<ExamResult> list(EntityManager em) {
        return em.createQuery("select e from ExamResult e", ExamResult.class).getResultList();
    }

    public ExamResult updateJPA(EntityManager em, ExamResult examResult) {
        em.merge(examResult);
        return examResult;
    }
}
