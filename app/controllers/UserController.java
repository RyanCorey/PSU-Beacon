package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class UserController extends Controller {
    public Result getUsers() {
        return ok(views.html.users.user.render());
    }
}
