import org.junit.Test;
import play.twirl.api.Content;
import views.html.*;
import views.html.fragments.footer;
import views.html.fragments.head;
import views.html.fragments.navbar;
import views.html.fragments.navbar_aux;

public class ApplicationTest {

    @Test
    public void simpleCheck() {
        /* Make sure testing framework is alive and well. */
        int a = 1 + 1;
        assert a == 2;
    }

    /* Render Templates will check to ensure that rendering pages, as well as fragment injection works correctly by checking
    *  for known text.*/
    @Test
    public void renderTemplates() {
        /* Splash Page */
        Content splashPage = splash.render("Ursa", head.render(), footer.render());
        assert splashPage.contentType().equals("text/html");
        assert splashPage.toString().contains("<!-- For Testing Purposes -->");
        assert splashPage.toString().contains("<p class=\"little-text\">(Using Google)</p>");

        /* Index Page */
        Content indexPage = index.render("Ursa | Dashboard", head.render(), navbar.render("Dashboard"), footer.render());
        assert indexPage.contentType().equals("text/html");
        assert indexPage.toString().contains("<!-- For Testing Purposes -->");
        assert indexPage.toString().contains("Dashboard");

        /* About Page */
        Content aboutPage = about.render("Ursa | About", head.render(), navbar_aux.render("About Ursa"), footer.render());
        assert aboutPage.contentType().equals("text/html");
        assert aboutPage.toString().contains("<!-- For Testing Purposes -->");
        assert aboutPage.toString().contains("About Ursa");

        /* Contact Page */
        Content contactPage = contact.render("57x6rc8tiuvbh", head.render(), navbar_aux.render("1t348791"), footer.render());
        assert contactPage.contentType().equals("text/html");
        assert contactPage.toString().contains("<!-- For Testing Purposes -->");
        assert contactPage.toString().contains("57x6rc8tiuvbh");
        assert contactPage.toString().contains("1t348791");

        /* Devs Page */
        Content devsPage = devs.render("6d85f7tiyvubi", head.render(), navbar_aux.render("87261730796ru"), footer.render());
        assert devsPage.contentType().equals("text/html");
        assert devsPage.toString().contains("<!-- For Testing Purposes -->");
        assert devsPage.toString().contains("6d85f7tiyvubi");
        assert devsPage.toString().contains("87261730796ru");

        /* Help Page */
        Content helpPage = help.render("18203759", head.render(), navbar_aux.render("b2g8307hu242g"), footer.render());
        assert helpPage.contentType().equals("text/html");
        assert helpPage.toString().contains("<!-- For Testing Purposes -->");
        assert helpPage.toString().contains("18203759");
        assert helpPage.toString().contains("b2g8307hu242g");

        /* Press Page */
        Content pressPage = press.render("2g71h0fif1", head.render(), navbar_aux.render("1h9g40hg1"), footer.render());
        assert pressPage.contentType().equals("text/html");
        assert pressPage.toString().contains("<!-- For Testing Purposes -->");
        assert pressPage.toString().contains("2g71h0fif1");
        assert pressPage.toString().contains("1h9g40hg1");

        /* Privacy Page */
        Content privacyPage = privacypolicy.render("2h20vrhqijnakdv", head.render(), navbar_aux.render("d68f7tivyub"), footer.render());
        assert privacyPage.contentType().equals("text/html");
        assert privacyPage.toString().contains("<!-- For Testing Purposes -->");
        assert privacyPage.toString().contains("2h20vrhqijnakdv");
        assert privacyPage.toString().contains("d68f7tivyub");
    }
}
