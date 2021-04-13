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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

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
    public CompletionStage<Question> save(Question question) {
        return supplyAsync(() -> wrap(em -> insert(em, question)), executionContext);
    }

    @Override
    public Question saveWait(Question question) {
        return wrap(em -> insert(em, question));
    }

    @Override
    public CompletionStage<Question> update(Question question) {
        return supplyAsync(() -> wrap(em -> updateJPA(em, question)), executionContext);
    }

    @Override
    public CompletionStage<Question> getQuestionById(Long examId) {
        return supplyAsync(() -> wrap(em -> getQuestionJPA(em, examId)), executionContext);
    }

    @Override
    public CompletionStage<Stream<Question>> list() {
        return supplyAsync(() -> wrap(this::list), executionContext);
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

    private Stream<Question> list(EntityManager em) {
        List<Question> exams = em.createQuery("select e from Question e", Question.class).getResultList();
        return exams.stream();
    }

    public Question updateJPA(EntityManager em, Question question) {
        em.merge(question);
        return question;
    }
}
