package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.fragments.*;
import views.html.*;

public class Application extends Controller {
    public static String currentPage = "index";

    /* Main Pages */
    public static Result splash() {
        currentPage = "index";
        return ok(splash.render("Ursa", head.render(), footer.render()));
    }

    public static Result index() {
        /* TODO check is user is logged in, if so redirect to /application, if not redirect to splash (/) */
//        if(!GoogleUser.getGoogleUserID().equals("")) {
        currentPage = "index";
        return ok(index.render("Ursa | Dashboard", head.render(), navbar.render("Dashboard"), footer.render()));
//        } else {
//            return splash();
//        }

    }

    public static Result research() {
        currentPage = "research";
        return ok(research.render("Ursa |Research", head.render(), navbar.render("Research"), footer.render()));
    }

    /* Auxiliary Pages */
    public static Result about() {
        currentPage = "about";
        return ok(about.render("Ursa | About", head.render(), navbar_aux.render("About Ursa"), footer.render()));
    }

    public static Result help() {
        currentPage = "help";
        return ok(help.render("Ursa | Help", head.render(), navbar_aux.render("Help"), footer.render()));
    }

    public static Result contact() {
        currentPage = "contact";
        return ok(contact.render("Ursa | Contact", head.render(), navbar_aux.render("Get in touch"), footer.render()));
    }

    public static Result press() {
        currentPage = "press";
        return ok(press.render("Ursa | Press", head.render(), navbar_aux.render("Press Kit"), footer.render()));
    }

    public static Result devs() {
        currentPage = "devs";
        return ok(devs.render("Ursa | Devs", head.render(), navbar_aux.render("Developers"), footer.render()));
    }

    public static Result privacyPolicy() {
        currentPage = "privacy";
        return ok(privacypolicy.render("Ursa | Privacy", head.render(), navbar_aux.render("Privacy Policy"), footer.render()));
    }
}
