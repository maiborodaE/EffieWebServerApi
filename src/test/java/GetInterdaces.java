import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;
import static io.restassured.RestAssured.sessionId;
import static javax.swing.UIManager.get;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetInterdaces {
@Test
    public void VerifyFULLjSON() {
        {
            String myJson = "{\"userName\":\"u0ba2@mail.ru\",\"password\":\"testPass\"}";
            RestAssured.baseURI = "https://portal-test.effie.mobi/api/user/auth";

            given()
                    .header("Session","123")
                    .contentType("application/json").
//                            header("cookies",sessionId).
                            body("{\"userName\":\"u0ba2@mail.ru\",\"password\":\"testPass\"}").
                            when()
                            .post("").then().body(equalTo(myJson));


        }
    }

        @Test
        public void getInterfacescook() {
            given()
                    .contentType("application/json")
                    .when()
                    .header("Cookie",sessionId)
                    .get("https://portal-test.effie.mobi/api/webrole/interfaces/assigned/user")
                    .then()
                    .statusCode(200);

        }

    }

