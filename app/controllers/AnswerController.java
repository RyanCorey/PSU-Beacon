package controllers;


import forms.QuestionForm;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.MessagesApi;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.AnswerService;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AnswerController extends Controller {

    private final Logger logger = Logger.getLogger(ExamController.class.toString());

    private AnswerService answerService;

    private  FormFactory formFactory;

    private  HttpExecutionContext ec;

    private play.i18n.MessagesApi messagesApi;

    @Inject
    public AnswerController(AnswerService answerService,
                          FormFactory formFactory,
                          HttpExecutionContext ec,
                          MessagesApi messagesApi) {
        this.answerService = answerService;
        this.formFactory = formFactory;
        this.ec = ec;
        this.messagesApi = messagesApi;
    }

    public Result getAnswers() {
        logger.log(Level.INFO, "Request to get All Answers");
        var answers = answerService.list();
        return ok(views.html.answers.answer.render(answers));
    }

    public Result getAnswersAsJson() {
        logger.log(Level.INFO, "Request to get All Answers");
        var answers = answerService.list();
        return ok(Json.toJson(answers));
    }

    public Result getAnswer(Long id, Http.Request request) {
        logger.log(Level.INFO, "Request to get Answer: {}", id);
        Form<QuestionForm.AnswerForm> answerForm = formFactory.form(QuestionForm.AnswerForm.class).withDirectFieldAccess(true);
        var answer = answerService.getAnswerById(id);
        return ok(views.html.answers.answer_detail.render(answer));
    }

    public Result getAnswerAsJson(Long id) {
        logger.log(Level.INFO, "Request to get Answer: {}", id);
        var answer = answerService.getAnswerById(id);
        return ok(Json.toJson(answer));
    }

    public Result deleteAnswer(Long id) {
        answerService.delete(id);
        return redirect(routes.AnswerController.getAnswers());
    }
}


