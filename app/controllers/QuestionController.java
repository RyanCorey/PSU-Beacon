package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class QuestionController extends Controller {
    public Result getQuestions() {
        return ok(views.html.questions.question.render());
    }
}
