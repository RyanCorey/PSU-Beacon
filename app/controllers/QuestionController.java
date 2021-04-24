package controllers;

import entities.Question;
import play.data.FormFactory;
import play.i18n.MessagesApi;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.QuestionService;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuestionController extends Controller {
    private final Logger logger = Logger.getLogger(QuestionController.class.toString());

    private final QuestionService questionService;

    private final FormFactory formFactory;

    private final HttpExecutionContext ec;

    private final play.i18n.MessagesApi messagesApi;

    @Inject
    public QuestionController(QuestionService questionService,
                              FormFactory formFactory,
                              HttpExecutionContext ec,
                              MessagesApi messagesApi) {
        this.questionService = questionService;
        this.formFactory = formFactory;
        this.ec = ec;
        this.messagesApi = messagesApi;
    }

    public Result getQuestions() {
        List<Question> results = questionService.list();
        return ok(views.html.questions.question.render(results));
    }

    public Result getQuestionsAsJson() {
        List<Question> results = questionService.list();
        return ok(Json.toJson(results));
    }

    public Result getQuestion(Long id, Http.Request request) {
        logger.log(Level.INFO, "Request to get Question: {}", id);
        var result = questionService.getQuestionById(id);
        return ok(views.html.questions.question_detail.render(result));
    }

    public Result getQuestionAsJson(Long id) {
        logger.log(Level.INFO, "Request to get Question: {}", id);
        var result = questionService.getQuestionById(id);
        return ok(Json.toJson(result));
    }

    public Result deleteQuestion(Long id) {
        questionService.delete(id);
        return redirect(routes.QuestionController.getQuestions());
    }
}

