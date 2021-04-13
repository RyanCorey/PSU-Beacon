package services;


import entities.User;
import entities.db.DatabaseExecutionContext;
import play.db.jpa.JPAApi;
import repositories.UserRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class UserService implements UserRepository {
    private final Logger log = Logger.getLogger(UserService.class.toString());

    private final JPAApi jpaApi;

    private final DatabaseExecutionContext executionContext;

    @Inject
    public UserService(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
    }

    @Override
    public CompletionStage<User> save(User user) {
        return supplyAsync(() -> wrap(em -> insert(em, user)), executionContext);
    }

    @Override
    public CompletionStage<User> update(User user) {
        return supplyAsync(() -> wrap(em -> updateJPA(em, user)), executionContext);
    }

    @Override
    public CompletionStage<User> getUserById(Long examId) {
        return supplyAsync(() -> wrap(em -> getUserJPA(em, examId)), executionContext);
    }

    @Override
    public CompletionStage<Stream<User>> list() {
        return supplyAsync(() -> wrap(this::list), executionContext);
    }

    private <T> T wrap(Function<EntityManager, T> function) {
        return jpaApi.withTransaction(function);
    }

    public User getUserJPA(EntityManager em, Long examId) {
        return em.find(User.class, examId);
    }

    private User insert(EntityManager em, User user) {
        em.persist(user);
        return user;
    }

    private Stream<User> list(EntityManager em) {
        List<User> exams = em.createQuery("select e from User e", User.class).getResultList();
        return exams.stream();
    }

    public User updateJPA(EntityManager em, User user) {
        em.merge(user);
        return user;
    }
}
