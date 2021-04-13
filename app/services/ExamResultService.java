package services;

import entities.ExamResult;
import entities.db.DatabaseExecutionContext;
import play.db.jpa.JPAApi;
import repositories.ExamResultRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

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
    public CompletionStage<ExamResult> save(ExamResult examResult) {
        return supplyAsync(() -> wrap(em -> insert(em, examResult)), executionContext);
    }

    @Override
    public CompletionStage<ExamResult> update(ExamResult examResult) {
        return supplyAsync(() -> wrap(em -> updateJPA(em, examResult)), executionContext);
    }

    @Override
    public CompletionStage<ExamResult> getExamResultById(Long examId) {
        return supplyAsync(() -> wrap(em -> getExamResultJPA(em, examId)), executionContext);
    }

    @Override
    public CompletionStage<Stream<ExamResult>> list() {
        return supplyAsync(() -> wrap(this::list), executionContext);
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

    private Stream<ExamResult> list(EntityManager em) {
        List<ExamResult> exams = em.createQuery("select e from ExamResult e", ExamResult.class).getResultList();
        return exams.stream();
    }

    public ExamResult updateJPA(EntityManager em, ExamResult examResult) {
        em.merge(examResult);
        return examResult;
    }
}
