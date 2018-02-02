package Activity.KRD;
import Settings.UserCredentials;
import Settings.LoginAuth;
import lombok.Getter;
import lombok.Setter;
import org.testng.annotations.Test;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

@Getter
@Setter


public class KRDtest {
    @ParametersAreNonnullByDefault
    String getStepsUrl = "https://portal-test.effie.mobi/api/step/items?groupId=4ADBB652-F1EE-427E-AAE4-96C41A25C97B&stageId=8";
    String logUrl = "https://portal-test.effie.mobi/api/user/auth";
    String postQHurl = "https://portal-test.effie.mobi/api/quest/one";

    Map<String, Object> Template = new HashMap<>();
    String randomName = UserCredentials.randomName;
    String cookie = LoginAuth.Login();
    private String manufForQHid = "";

    private String serverName = "nginx";
    @Test
    public void getSteps() {
        given()
                .when()
                .cookie(cookie)
                .get(getStepsUrl)
                .then()
                .statusCode(200)
                .contentType("application/json;charset=UTF-8");
//                .body("name", equalTo(randomName));
    }

}
