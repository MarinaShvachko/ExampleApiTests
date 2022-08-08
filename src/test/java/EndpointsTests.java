import org.testng.annotations.Test;
import utulity.Url;

import static com.jayway.restassured.RestAssured.given;


public class EndpointsTests {

    @Test
    public void bookings() {
        given()
                .baseUri(Url.getBaseUri())
                .when()
                .get("/booking")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void pingTest() {
        given()
                .baseUri(Url.getBaseUri())
                .when()
                .get("/ping")
                .then()
                .assertThat()
                .statusCode(201);
    }
}
