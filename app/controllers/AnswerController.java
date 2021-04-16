package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class AnswerController extends Controller {
    public Result getAnswers() {
        return ok(views.html.answers.answer.render());
    }
}
