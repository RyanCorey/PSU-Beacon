package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class ExamResultsController extends Controller {
    public Result getExamResults() {
        return ok(views.html.results.results.render());
    }
}
