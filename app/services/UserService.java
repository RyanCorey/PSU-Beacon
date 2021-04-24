package services;


import entities.User;
import entities.db.DatabaseExecutionContext;
import entities.enumerations.Role;
import forms.UserForm;
import play.db.jpa.JPAApi;
import repositories.UserRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;


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
    public User save(User user) {
        return wrap(em -> insert(em, user));
    }

    @Override
    public User update(User user) {
        return wrap(em -> updateJPA(em, user));
    }

    @Override
    public void delete(Long id) {
        wrap(em -> deleteJPA(em, id));
    }

    @Override
    public User getUserById(Long examId) {
        return wrap(em -> getUserJPA(em, examId));
    }

    @Override
    public List<User> list() {
        return wrap(this::list);
    }

    @Override
    public List<User> findAllByRole(Role role) {
        return wrap(em -> getAllByRole(em, role));
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

    private List<User> list(EntityManager em) {
        return em.createQuery("select e from User e", User.class).getResultList();
    }

    public List<User> getAllByRole(EntityManager em, Role role) {
        Query query = em.createQuery("SELECT u from User u WHERE u.role = :role", User.class);
        query.setParameter("role", role);
        return query.getResultList();
    }

    public User updateJPA(EntityManager em, User user) {
        em.merge(user);
        return user;
    }

    public int deleteJPA(EntityManager em, Long id) {
        return em.createQuery("DELETE FROM User WHERE id = " + id).executeUpdate();
    }

    public User createUser(UserForm userForm) {
        var user = new User();
        user.setEmail(userForm.getEmail());
        user.setProfilePictureUrl(userForm.getProfilePictureUrl());
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setRole(userForm.getRole());
        return save(user);
    }
}
