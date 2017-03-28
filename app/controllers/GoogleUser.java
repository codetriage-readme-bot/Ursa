package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import play.Logger;
import play.api.db.DB;
import play.libs.F;
import play.libs.ws.WS;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Statement;


/*  Google User will handle the storage of user accounts, token validation, detection of logged in / existence, as well as
*   other Google Account actions.
*/
public class GoogleUser extends Controller {
    private static String googleUserID = "";
    private static String googleUserName = "";
    private static String googleUserImageURL = "";
    private static String googleUserEmail = "";
    private static String googleIdToken = "";
    private static String locale = "";
    private static String first_name = "";
    private static String last_name = "";

    public static String getGoogleUserID() {
        return googleUserID;
    }

    public static void setGoogleUserID(String googlerUserID) {
        GoogleUser.googleUserID = googlerUserID;
    }

    public static String getGoogleUserName() {
        return googleUserName;
    }

    public static void setGoogleUserName(String googleUserName) {
        GoogleUser.googleUserName = googleUserName;
    }

    public static String getGoogleUserEmail() {
        return googleUserEmail;
    }

    public static void setGoogleUserEmail(String googleUserEmail) {
        GoogleUser.googleUserEmail = googleUserEmail;
    }

    public static String getGoogleUserImageURL() {
        return googleUserImageURL;
    }

    public static void setGoogleUserImageURL(String googleUserImageURL) {
        GoogleUser.googleUserImageURL = googleUserImageURL;
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result googleUser() {
        JsonNode json = request().body().asJson();
        json.fields().forEachRemaining(e -> {
            if (e.getKey().equals("id")) {
                setGoogleUserID(e.getValue().textValue());
            } else if (e.getKey().equals("name")) {
                setGoogleUserName(e.getValue().textValue());
            } else if (e.getKey().equals("imageurl")) {
                setGoogleUserImageURL(e.getValue().textValue());
            } else if (e.getKey().equals("email")) {
                setGoogleUserEmail(e.getValue().textValue());
            } else if (e.getKey().equals("idtoken")) {
                setGoogleIdToken(e.getValue().textValue());
            }

        });
        GoogleUserObject googleUserObject = new GoogleUserObject(getGoogleUserID(),
                getGoogleUserName(),
                getGoogleUserImageURL(),
                getGoogleUserEmail(),
                getGoogleIdToken());
        googleUserObject.getGoogleUserObject();
        /* TODO: Authenticate user w/ tokens instead of ID, store information in a database (mongodb) */
        return ok("200");
    }

    public static String getGoogleIdToken() {
        return googleIdToken;
    }

    public static void setGoogleIdToken(String googleIdToken) {
        GoogleUser.googleIdToken = googleIdToken;
    }

    public static Result signOut() {
        //TODO wait to test signOut on standard setup (non-localhost) before implementing
//        JsonNode json = request().body().asJson();
//        json.fields().forEachRemaining(e -> {
//            if (e.getKey().equals("id")) {
//                setGoogleUserID(e.getValue().textValue());
//            }
//        });
//        String baseUrl = "https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=";
//        F.Promise<play.libs.ws.WSResponse> request =
//                WS.url(baseUrl + getGoogleIdToken())
//                        .post("");
//        JsonNode response = request.get(1000).asJson();
//        response.fields().forEachRemaining(e -> {
//            if (e.getKey().equals("sub")) {
//                String dbUserToken = e.getValue().textValue();
//                Logger.info(dbUserToken);//
//            }
//        });

        return ok("200");
    }

    static class GoogleUserObject {
        static DataSource ds = DB.getDataSource("default", play.api.Play.current());
        private String id;
        private String name;
        private String imageurl;
        private String email;
        private String idToken;

        public GoogleUserObject(String id, String name, String imageurl, String email, String idToken) {
            this.id = id;
            this.name = name;
            this.imageurl = imageurl;
            this.email = email;
            this.idToken = idToken;
        }

        public void getGoogleUserObject() {
            String baseUrl = "https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=";
            F.Promise<play.libs.ws.WSResponse> request =
                    WS.url(baseUrl + getGoogleIdToken())
                            .post("");
            JsonNode response = request.get(1000).asJson();
            response.fields().forEachRemaining(e -> {
                if (e.getKey().equals("sub")) {
                    this.idToken = e.getValue().textValue();
                } else if (e.getKey().equals("email")) {
                    this.email = e.getValue().textValue();
                } else if (e.getKey().equals("given_name")) {
                    first_name = e.getValue().textValue();
                } else if (e.getKey().equals("family_name")) {
                    last_name = e.getValue().textValue();
                } else if (e.getKey().equals("picture")) {
                    this.imageurl = e.getValue().textValue();
                } else if (e.getKey().equals("locale")) {
                    locale = e.getValue().textValue();
                }
            });
            try {
                //TODO check if primary key exists, if so - update don't try to insert
                String sql = ds.getConnection().nativeSQL("INSERT INTO ursausers VALUES(" + "\"" + idToken + "\","
                        + "\"" + first_name + "\"," +
                        "\"" + last_name + "\", " +
                        "\"" + email + "\", " +
                        "\"" + locale + "\", " +
                        "\"" + imageurl + "\");");

                Statement stmt = ds.getConnection().createStatement();

                stmt.execute(sql);
            } catch (SQLException e1) {
                Logger.error(e1.toString());
            }
        }


    }
}
