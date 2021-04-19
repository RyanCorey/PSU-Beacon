package controllers;

import play.data.FormFactory;
import play.i18n.MessagesApi;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import repositories.ExamResultRepository;
import services.AnswerService;
import services.ExamResultService;
import services.ExamService;
import services.QuestionService;

import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExamResultsController extends Controller {

    // Create logger for Exam Results
    private final Logger logger = Logger.getLogger(ExamResultsController.class.toString());

    private final FormFactory formFactory;
    private final play.i18n.MessagesApi messagesApi;

    // Create instance of services
    private final ExamService examService;
    private final ExamResultService examResultService;
    private final AnswerService answerService;
    private final QuestionService questionService;

    @Inject
    public ExamResultsController(FormFactory formFactory, MessagesApi messagesApi, ExamService examService, ExamResultService examResultService, AnswerService answerService, QuestionService questionService) {
        this.formFactory = formFactory;
        this.messagesApi = messagesApi;
        this.examService = examService;
        this.examResultService = examResultService;
        this.answerService = answerService;
        this.questionService = questionService;
    }

    public Result getExamResults() {
        var results = examResultService.list();
        return ok(views.html.results.results.render(results));
    }

    /**
     * Get request. Get's an exam result based on its database PK(id)
     * @param id The PK of the entity to retrieve
     * @param request The HTTP request
     * @return Result (Single Exam Answer Item)
     */
    public Result getExamResult(Long id, Http.Request request){
        logger.log(Level.INFO, "Request to get Exam Result Item: {}", id);
        //logger.log(Level.INFO, "Request to get Exam Result for Student: {}", sid);
        //var result = questionService.getQuestionById(id);
        //var result = examResultService.getExamResultListById(id);
        var res = questionService.getQuestionListById(id);
        var result = answerService.getAnswerListById(id);
        //return ok(views.html.results.results_detail.render(result));
        return ok(views.html.results.results_detail.render(res, result));
    }
}
