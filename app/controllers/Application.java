package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.fragments.*;
import views.html.*;

public class Application extends Controller {
    public static String currentPage = "index";

    public static Result splash() {
        currentPage = "index";
        return ok(splash.render("Ursa", head.render(), footer.render()));
    }

    public static Result index() {
        /* TODO check is user is logged in, if not redirect to splash */
//        if(!GoogleUser.getGoogleUserID().equals("")) {
        currentPage = "index";
        response().setCookie(
                "userToken",        // name
                "" + GoogleUser.getGoogleIdToken(),         // value
                36000,           // maximum age
                "/",   // path
                "localhost", // domain
                true,          // secure
                false            // http only
        );
        return ok(index.render("Ursa - Dashboard", head.render(), navbar.render(), footer.render()));
//        } else {
//            return splash();
//        }

    }

    public static Result research() {
        currentPage = "research";
        return ok(research.render("Ursa - Research", head.render(), navbar.render(), footer.render()));
    }

}
