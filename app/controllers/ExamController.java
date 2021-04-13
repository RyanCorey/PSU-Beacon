package controllers;

import forms.QuestionForm;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.MessagesApi;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.ExamService;
import services.QuestionService;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class ExamController extends Controller {
    private final Logger logger = Logger.getLogger(ExamController.class.toString());

    private final ExamService examService;

    private final FormFactory formFactory;

    private final HttpExecutionContext ec;

    private final play.i18n.MessagesApi messagesApi;


    @Inject
    public ExamController(ExamService examService,
                          FormFactory formFactory,
                          HttpExecutionContext ec,
                          MessagesApi messagesApi) {
        this.examService = examService;
        this.formFactory = formFactory;
        this.ec = ec;
        this.messagesApi = messagesApi;
    }

    public CompletionStage<Result> getExams() {
        logger.log(Level.INFO, "Request to get All Exams");
        return examService
                .list()
                .thenApplyAsync(examStream ->
                                ok(views.html.exam.exam.render(examStream.collect(Collectors.toList()))),
                        ec.current());
    }

    public CompletionStage<Result> getExam(Long id, Http.Request request) {
        logger.log(Level.INFO, "Request to get Exam: {}", id);
        Form<QuestionForm> questionForm = formFactory.form(QuestionForm.class).withDirectFieldAccess(true);
        return examService
                .getExamById(id)
                .thenApplyAsync(exam -> ok(views.html.exam.exam_detail.render(exam, questionForm, messagesApi.preferred(request))), ec.current());
    }

    public Result addQuestion(Long id, Http.Request request) {
        final Form<QuestionForm> form = formFactory.form(QuestionForm.class).bindFromRequest(request);
        examService.addQuestion(id, form.get());
        return redirect(routes.ExamController.getExam(id));
    }

}
