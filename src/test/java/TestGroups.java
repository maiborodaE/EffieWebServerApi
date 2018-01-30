import io.restassured.mapper.ObjectMapper;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class TestGroups {

    private String createUrl = "https://portal-test.effie.mobi/api/directory/usergroups/";
    private String getUrl = "https://portal-test.effie.mobi/api/directory/usergroups";
    String cookies = "SESSION=4598eb20-5880-442f-b574-5d5ea5f910e8";
    String rukMSid = "0564D537-C174-4C3B-8F1D-D5B0173FF851";
    String[] manufId = {"0485e1a8-1222-46c0-b39c-6fbbd75ad0cd"};

    Date date = new Date();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
    String randomName = dateFormat.format( date);
    String randomUpdateName = dateFormat.format( date);
    Map<String, Object> Template = new HashMap<>();
//    Map<String, String[]> TemplateManuf = new HashMap<>();

    @Test
    public void getGroup() {
        given()
                .when()
                .cookie(cookies)
                .get(getUrl)
                .then()
                .statusCode(200)
                .contentType("application/json;charset=UTF-8");
//                .body("name", equalTo(randomName));
    }
//    @Test
//    public void createGroup() {
//        Template.put("name", randomName);
//        Template.put("roleId", rukMSid);
//        given()
//                .contentType("application/json")
//                .body(Template)
//                .when()
//                .cookie(cookies)
//                .post(createUrl)
//                .then()
//                .statusCode(200)
//                .body("name", equalTo(randomName));
//    }

    @Test
    public void createGroupManuf() {
        Template.put("name", randomName);
        Template.put("roleId", rukMSid);
        Template.put("manufacturerIds", manufId);

        given()
                .contentType("application/json")
                .body(Template)
                .when()
                .cookie(cookies)
                .post(createUrl)
                .then()
                .statusCode(200)
                .body("name", equalTo(randomName));
    }
    @Test
    public void updateGroup() {
        Template.put("name", randomUpdateName);
        Template.put("roleId", rukMSid);
        Template.put("id", "3795ce53-379b-4f00-9f02-ef6e1e164e5e");
        given()
                .contentType("application/json")
                .body(Template)
                .when()
                .cookie(cookies)
                .post(createUrl)
                .then()
                .statusCode(200)
                .body("name", equalTo(randomUpdateName));
    }


}
