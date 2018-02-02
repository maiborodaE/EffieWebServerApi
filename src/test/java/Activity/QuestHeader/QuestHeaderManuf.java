package Activity.QuestHeader;
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

@Getter
@Setter

public class QuestHeaderManuf {
    @ParametersAreNonnullByDefault
    String manufUrl = "https://portal-test.effie.mobi/api/manufacturer";
    String logUrl = "https://portal-test.effie.mobi/api/user/auth";
    String postQHurl = "https://portal-test.effie.mobi/api/quest/one";

    Map<String, Object> Template = new HashMap<>();
    String randomName = UserCredentials.randomName;
    String cookie = LoginAuth.Login();
    private String manufForQHid = "";

    private String serverName = "nginx";

    @Test
    public void createManufactur() {
        Template.put("name", randomName + "forQH");

       Response response  = given()
                .contentType("application/json")
                .body(Template)
                .when()
                .cookie(cookie)
                .post(manufUrl)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("name", equalTo(randomName + "forQH"))
                .body("deleted", equalTo(false))
                .body("id", notNullValue())
                .header("Server", serverName)
                .log()
                .all()
                .extract()
                .response();
        manufForQHid = response.path("id");


        given()
                .contentType("application/json")
                .body("{\"id\":\"8fd67b72-d4bd-4bf3-a6e1-91ead9500a4e\"," +
                        "\"questHeaderTypeID\":0,\"useTemplate\":false,\"name\":\"!!!!123\"," +
                        "\"ManufacturerID\":\""+manufForQHid+"\"," +
                        "\"questType\":[2],\"stepName\":[\"!!!!123 (отчетность)\"]}")
                .when()
                .cookie(cookie)
                .post(postQHurl)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("ManufacturerID",hasItem(manufForQHid))
                .log()
                .all();
    }
}