//package Manufacturers;
//
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//import lombok.Getter;
//import lombok.Setter;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import javax.annotation.ParametersAreNonnullByDefault;
//import java.util.HashMap;
//import java.util.Map;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.core.IsCollectionContaining.hasItem;
//
//@Getter
//@Setter
//
//
//public class CreateAndGet {
//    @ParametersAreNonnullByDefault
//    String manufUrl = "https://portal-dev.effie.mobi/api/manufacturer";
//    String logUrl = "https://portal-dev.effie.mobi/api/user/auth";
//    Map<String, Object> Template = new HashMap<>();
//    String randomName = UserCredentials.randomName;
//    String cookie = Login();
//    private String newManufId = "";
//    private String newManufName = "";
//    @BeforeClass
//    public String Login() {
//
//        Map<String, String> auth = new HashMap<>();
//        auth.put("userName", "u4ba@ipland.com.ua");
//        auth.put("password", "Qq123456");
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
//
//        @Test
//        public void CreateGet() {
//            Template.put("name", randomName);
//
//            Response res = given()
//                    .contentType("application/json")
//                    .body(Template)
//                    .when()
//                    .cookie(cookie)
//                    .post(manufUrl)
//                    .then()
//                    .statusCode(200)
//                    .log()
//                    .body()
//                    .extract()
//                    .response();
//
//             newManufName = res.path("name");
//             newManufId = res.path("id");
//         given()
//                    .when()
//                    .cookie(cookie)
//                    .get(manufUrl)
//                    .then()
//                    .statusCode(200)
//                    .body("name",(hasItem(newManufName)))
//                    .body("id",(hasItem(newManufId)))
//                    .log()
//                    .all();
//        }
//    }
