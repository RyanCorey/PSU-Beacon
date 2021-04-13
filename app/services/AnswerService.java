package services;

import entities.Answer;
import entities.db.DatabaseExecutionContext;
import play.db.jpa.JPAApi;
import repositories.AnswerRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

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
    public CompletionStage<Answer> save(Answer answer) {
        return supplyAsync(() -> wrap(em -> insert(em, answer)), executionContext);
    }

    @Override
    public Answer saveWait(Answer answer) {
        return wrap(em -> insert(em, answer));
    }

    @Override
    public CompletionStage<Answer> update(Answer answer) {
        return supplyAsync(() -> wrap(em -> updateJPA(em, answer)), executionContext);
    }

    @Override
    public CompletionStage<Answer> getAnswerById(Long examId) {
        return supplyAsync(() -> wrap(em -> getAnswerJPA(em, examId)), executionContext);
    }

    @Override
    public CompletionStage<Stream<Answer>> list() {
        return supplyAsync(() -> wrap(this::list), executionContext);
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

    private Stream<Answer> list(EntityManager em) {
        List<Answer> exams = em.createQuery("select e from Answer e", Answer.class).getResultList();
        return exams.stream();
    }

    public Answer updateJPA(EntityManager em, Answer answer) {
        em.merge(answer);
        return answer;
    }
}
