import org.junit.Test;
import play.twirl.api.Content;
import views.html.fragments.*;
import views.html.*;

public class ApplicationTest {

    @Test
    public void simpleCheck() {
        /* Make sure testing framework is alive and well. */
        int a = 1 + 1;
        assert a == 2;
    }

    @Test
    public void renderTemplate() {
        /* Ensure splash page + head + footer render correctly */
        Content html = splash.render("Ursa", head.render(), footer.render());
        assert html.contentType().equals("text/html");
        assert html.toString().contains("<!-- For Testing Purposes -->");
    }


}
