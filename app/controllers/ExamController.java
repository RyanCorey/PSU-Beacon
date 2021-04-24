package controllers;

import entities.Exam;
import entities.ExamResult;
import entities.enumerations.Role;
import forms.*;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.MessagesApi;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.ExamService;
import services.UserService;
import services.dto.ExamResultDTO;

import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ExamController extends Controller {
    private final Logger logger = Logger.getLogger(ExamController.class.toString());

    private final ExamService examService;

    private final UserService userService;

    private final FormFactory formFactory;

    private final HttpExecutionContext ec;

    private final play.i18n.MessagesApi messagesApi;


    @Inject
    public ExamController(ExamService examService,
                          UserService userService,
                          FormFactory formFactory,
                          HttpExecutionContext ec,
                          MessagesApi messagesApi) {
        this.examService = examService;
        this.userService = userService;
        this.formFactory = formFactory;
        this.ec = ec;
        this.messagesApi = messagesApi;
    }

    /**
     * Serves as the initial page a user would see /exams
     * @return Request (List of Exams)
     */
    public Result getExams(Http.Request request) {
        logger.log(Level.INFO, "Request to get All Exams");
        var results = examService.list();
        var users = userService.findAllByRole(Role.TEACHER);
        var examResult = new ExamResultDTO(results, users);
        Form<ExamForm> examForm = formFactory.form(ExamForm.class).withDirectFieldAccess(true);
        return ok(views.html.exam.exam.render(examResult, examForm, messagesApi.preferred(request)));
    }

    public Result getExamsAsJson() {
        var results = examService.list();
        return ok(Json.toJson(results));
    }

    /**
     * Get request. Get's an exam based on its database PK(id)
     * @param id The PK of the entity to retrieve
     * @param request The HTTP request
     * @return Result (Single Exam Item)
     */
    public Result getExam(Long id, Http.Request request) {
        logger.log(Level.INFO, "Request to get Exam: {}", id);
        Form<QuestionForm> questionForm = formFactory.form(QuestionForm.class).withDirectFieldAccess(true);
        Form<AnswerForm> answerForm = formFactory.form(AnswerForm.class).withDirectFieldAccess(true);
        var result = examService.getExamById(id);
        return ok(views.html.exam.exam_detail.render(result, questionForm, answerForm, messagesApi.preferred(request)));
    }

    public Result getExamAsJson(Long id) {
        var result = examService.getExamById(id);
        return ok(Json.toJson(result));
    }

    public Result createExam(Http.Request request) {
        final Form<ExamForm> form = formFactory.form(ExamForm.class).bindFromRequest(request);
        var exam = examService.createExam(form.get());
        return redirect(routes.ExamController.getExam(exam.getId()));
    }

    /**
     * Post request. Creates a new question for an Exam
     * @param examId The PK of the EXAM to add a question to
     * @param request The HTTP request
     * @return Return a redirect to the getExam() method
     */
    public Result addQuestion(Long examId, Http.Request request) {
        final Form<QuestionForm> form = formFactory.form(QuestionForm.class).bindFromRequest(request);
        examService.addQuestion(examId, form.get());
        return redirect(routes.ExamController.getExam(examId));
    }

    public Result addAnswer(Long examId, Long questionId, Http.Request request) {
        final Form<AnswerForm> form = formFactory.form(AnswerForm.class).bindFromRequest(request);
        examService.addAnswer(examId, questionId, form.get());
        return redirect(routes.ExamController.getExam(examId));
    }

    public Result deleteExam(Long id) {
        examService.delete(id);
        return redirect(routes.ExamController.getExams());
    }

    public Result deleteQuestion(Long examId, Long questionId) {
        examService.deleteQuestion(questionId);
        return redirect(routes.ExamController.getExam(examId));
    }

    public Result deleteAnswer(Long examId, Long answerId) {
        examService.deleteAnswer(answerId);
        return redirect(routes.ExamController.getExam(examId));
    }
}
