package controllers;

import entities.User;
import play.data.FormFactory;
import play.i18n.MessagesApi;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.ExamService;
import services.UserService;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserController extends Controller {
    private final Logger logger = Logger.getLogger(UserController.class.toString());

    private final UserService userService;

    private final FormFactory formFactory;

    private final HttpExecutionContext ec;

    private final play.i18n.MessagesApi messagesApi;

    public Result getUsers() {
        List<User> results = userService.list();
        return ok(views.html.users.user.render(results));
    }


    public Result getUser(Long id, Http.Request request) {
        logger.log(Level.INFO, "Request to get User: {}", id);
        var result = userService.getUserById(id);
        return ok(views.html.users.user_detail.render(result));
    }

    @Inject
    public UserController(UserService userService,
                          FormFactory formFactory,
                          HttpExecutionContext ec,
                          MessagesApi messagesApi) {
        this.userService = userService;
        this.formFactory = formFactory;
        this.ec = ec;
        this.messagesApi = messagesApi;
    }


}

