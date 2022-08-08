import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import pojoClasses.Booking;
import utulity.Authentication;
import utulity.BaseClass;
import utulity.Url;

import static com.jayway.restassured.RestAssured.given;


public class PatchTest {
    String endPoint = Url.getBaseUri("/booking/");

    @Test
    public void updateNameAndLastName() {
        Booking body = new Booking();
        body = body.addBookingAllFieldsFilled("Diana", "Round", 6, true, "pijamas", "2022-08-04", "2022-08-08");
        Response response = BaseClass.postRequest(endPoint, body);
        endPoint = endPoint + response.path("bookingid");


        given()
                .cookie("token", Authentication.getToken())
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"firstname\" : \"Tarara\",\n" +
                        "    \"lastname\" : \"Hahaha\"\n" +
                        "}")
                .when()
                .log()
                .all()
                .patch(endPoint)
                .then()
                .assertThat()
                .statusCode(200)
                .body("firstname", Matchers.equalTo("Tarara"))
                .body("lastname", Matchers.equalTo("Hahaha"))
                .log().all();
    }
}
