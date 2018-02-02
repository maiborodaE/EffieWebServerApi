package Manufacturers;
import Settings.LoginAuth;
import Settings.UserCredentials;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import org.testng.annotations.Test;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Getter
@Setter

public class TestManufacturers {
//    @BeforeClass
//    public String Login() {
//
//        Map<String, String> auth = new HashMap<>();
////        u0.BA:
//        auth.put("userName", "u0ba2@mail.ru");
//        auth.put("password", "testPass");
////        u5.BA
////        auth.put("userName", "zb@stv.kharkov.com");
////        auth.put("password", "STV_ipland_16");
////        u9.BA:
////        auth.put("userName", "effieadmin@komo.ua");
////        auth.put("password", "komoadmin931");
//        String session = "";
//
//        Response r = given()
//                .contentType("application/json")
//                .body(auth)
//                .when()
//                .post(logUrl)
//                .then()
//                .statusCode(200)
//                .contentType(ContentType.JSON)
//                .log()
//                .headers()
//                .extract()
//                .response();
//
//        session = r.header("Set-Cookie");
//        return session;
//    }

    @ParametersAreNonnullByDefault
    String manufUrl = "https://portal-test.effie.mobi/api/manufacturer";
//    String logUrl = "https://portal-test.effie.mobi/api/user/auth";
    Map<String, Object> Template = new HashMap<>();
    String randomName = UserCredentials.randomName;
    String cookie = LoginAuth.Login();
    private String newManufId = "";
    private String newManufName = "";
    private String updManufName ="";
    private String updManufId = "";
    private String serverName = "nginx";

    @Test
    public void createManufactur() {
        Template.put("name", randomName+"new");

        given()
                .contentType("application/json")
                .body(Template)
                .when()
                .cookie(cookie)
                .post(manufUrl)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("name", equalTo(randomName+"new"))
                .body("deleted", equalTo(false))
                .body("id",notNullValue())
                .header("Server", serverName)
                .log()
                .all();
    }

    @Test
    public void getManufacture() {
        given()
                .when()
                .cookie(cookie)
                .get(manufUrl)
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .header("Server", serverName)
                .log()
                .body()
                .extract()
                .response();
    }

    @Test
    public void CreateGet() {
        Template.put("name", randomName);

        Response res = given()
                .contentType("application/json")
                .body(Template)
                .when()
                .cookie(cookie)
                .post(manufUrl)
                .then()
                .statusCode(200)
                .log()
                .body()
                .extract()
                .response();

        newManufName = res.path("name");
        newManufId = res.path("id");
        given()
                .when()
                .cookie(cookie)
                .get(manufUrl)
                .then()
                .statusCode(200)
                .body("name",(hasItem(newManufName)))
                .body("id",(hasItem(newManufId)))
                .log()
                .all();
    }
    @Test
    public void CreateExist() {
        Template.put("name", randomName+"exist");

        given()
                .contentType("application/json")
                .body(Template)
                .when()
                .cookie(cookie)
                .post(manufUrl)
                .then()
                .statusCode(200)
                .log()
                .body();
        given()
                .contentType("application/json")
                .body(Template)
                .when()
                .cookie(cookie)
                .post(manufUrl)
                .then()
                .statusCode(409)
                .log()
                .body();
    }
    @Test
    public void CreateUpdateGet() {
        Template.put("name", randomName+"for Update");


        Response res = given()
                .contentType("application/json")
                .body(Template)
                .when()
                .cookie(cookie)
                .post(manufUrl)
                .then()
                .statusCode(200)
                .log()
                .body()
                .extract()
                .response();

        newManufName = res.path("name");
        newManufId = res.path("id");
        Template.put("id", newManufId);
                Template.put("name",newManufName+"Updated");
                Template.put("deleted", false);
        Response update = given()
                .contentType("application/json")
                .body(Template)
                .when()
                .cookie(cookie)
                .put(manufUrl)
                .then()
                .statusCode(200)
                .log()
                .body()
                .extract()
                .response();
        updManufName = update.path("name");
        updManufId = update.path("id");
        given()
                .when()
                .cookie(cookie)
                .get(manufUrl)
                .then()
                .statusCode(200)
                .body("name",(hasItem(updManufName)))
                .body("id",(hasItem(updManufId)))
                .log()
                .all();
    }
}
