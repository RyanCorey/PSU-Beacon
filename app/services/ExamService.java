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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class ExamService implements ExamRepository{
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
        Question question = questionService.saveWait(new Question(questionForm.getQuestionText(), exam));
        for (QuestionForm.AnswerForm answerForm: questionForm.getAnswers()) {
            answerService.saveWait(new Answer(answerForm.getAnswerText(), answerForm.getCorrect(), question));
        }
    }

    @Override
    public CompletionStage<Exam> save(Exam exam) {
        return supplyAsync(() -> wrap(em -> insert(em, exam)), executionContext);
    }

    @Override
    public Exam saveWait(Exam exam) {
        return wrap(em -> insert(em, exam));
    }

    @Override
    public CompletionStage<Exam> update(Exam exam) {
        return supplyAsync(() -> wrap(em -> updateJPA(em, exam)), executionContext);
    }

    @Override
    public CompletionStage<Exam> getExamById(Long examId) {
        return supplyAsync(() -> wrap(em -> {
            return getExamJPA(em, examId);
        }), executionContext);
    }

    @Override
    public Exam getExam(Long id) {
        return wrap(em -> getExamJPA(em, id));
    }

    @Override
    public CompletionStage<Stream<Exam>> list() {
        return supplyAsync(() -> wrap(this::list), executionContext);
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

    private Stream<Exam> list(EntityManager em) {
        List<Exam> exams = em.createQuery("select e from Exam e", Exam.class).getResultList();
        return exams.stream();
    }

    public Exam updateJPA(EntityManager em, Exam exam) {
        em.merge(exam);
        return exam;
    }
}
