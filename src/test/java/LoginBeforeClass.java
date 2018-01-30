import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.omg.CORBA.Request;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.EasyMock2Matchers.equalTo;

public class LoginBeforeClass {
    @BeforeClass
    public static void loginBeforeClass(){
        Map<String, String> auth = new HashMap<>();
        auth.put("userName", "u0ba2@mail.ru");
        auth.put("password", "testPass");
        given()
                .contentType("application/json")
                .body(auth)
                .when()
//                .sessionId("session")
                .post("https://portal-test.effie.mobi/api/user/auth")
                .then()
                .statusCode(200)
                .header("cookies", sessionId);


        String sessionId = RestAssured.sessionId;
    }
    
    @Test
    public void getInterfacesP() {
//        ResponseBody r =
                given()
                .when()
                .cookie("SESSION=9d162ded-a65a-403a-9662-4311b1946436")
//                        .cookie("SESSION",sessionId)
                        .get("https://portal-test.effie.mobi/api/webrole/interfaces/assigned/user")
                .then()
                        .statusCode(200)
                        .contentType("application/json;charset=UTF-8");

//               .body("id", CoreMatchers.equalTo("[reports, main, directories, settings, routes, monitoring, actions]"));
//        System.out.println(r.prettyPrint());

    }
}
