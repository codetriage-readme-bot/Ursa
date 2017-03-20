package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import play.Logger;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by caden on 3/19/2017.
 */
public class GoogleUser extends Controller {
    private static String googleUserID = "";
    private static String googleUserName = "";
    private static String googleUserImageURL = "";
    private static String googleUserEmail = "";

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
        json.fields().forEachRemaining(e -> Logger.info(" : " + e.getKey() + " " + e.getValue()));
//        Logger.info(" TEST"  + json.asText());

        json.fields().forEachRemaining(e -> {
            if (e.getKey().equals("id")) {
                setGoogleUserID(e.getValue().textValue());
            } else if (e.getKey().equals("name")) {
                setGoogleUserName(e.getValue().textValue());
            } else if (e.getKey().equals("imageurl")) {
                setGoogleUserImageURL(e.getValue().textValue());
            } else if (e.getKey().equals("email")) {
                setGoogleUserEmail(e.getValue().textValue());
            }
        });
        return ok();
    }
}
