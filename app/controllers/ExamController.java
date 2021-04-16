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
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;


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

    /**
     * Serves as the initial page a user would see /exams
     * @return Request (List of Exams)
     */
    public Result getExams() {
        logger.log(Level.INFO, "Request to get All Exams");
        var results = examService.list();
        return ok(views.html.exam.exam.render(results));
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
        var result = examService.getExamById(id);
        return ok(views.html.exam.exam_detail.render(result, questionForm, messagesApi.preferred(request)));
    }

    /**
     * Post request. Creates a new question for an Exam
     * @param id The PK of the EXAM to add a question to
     * @param request The HTTP request
     * @return Return a redirect to the getExam() method
     */
    public Result addQuestion(Long id, Http.Request request) {
        final Form<QuestionForm> form = formFactory.form(QuestionForm.class).bindFromRequest(request);
        examService.addQuestion(id, form.get());
        return redirect(routes.ExamController.getExam(id));
    }

}
