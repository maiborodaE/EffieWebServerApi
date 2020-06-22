package Settings;

import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import org.hamcrest.Matchers.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.xml.ws.handler.HandlerResolver;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

@Getter
@Setter
public class LoginAuth {

    public static String Login() {

        Map<String, String> auth = new HashMap<>();
        String logUrl = "https://portal-test.effie.mobi/api/user/auth";

//        u0.BA:
        auth.put("userName", "login");
        auth.put("password", "pswrd");

        String session = "";

        Response r = given()
                .contentType("application/json")
                .body(auth)
                .when()
                .post(logUrl)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .log()
                .headers()
                .extract()
                .response();

        session = r.header("Set-Cookie");
        return session;
    }
}
