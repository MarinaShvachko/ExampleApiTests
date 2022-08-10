import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.testng.annotations.Test;
import utulity.BaseClass;
import utulity.Url;

import static com.jayway.restassured.RestAssured.when;

public class GetTest {
    String endPoint = Url.getBaseUri("/booking");

    @Test
    public void getBookById() {
        RestAssured.baseURI = Url.getBaseUri();
        when()
                .get("/booking/5840")
                .then()
                .assertThat()
                .statusCode(200)
                .log()
                .all();
    }

    @Test
    public void getAllBookings() {
        RestAssured.baseURI = Url.getBaseUri();
        when()
                .get("/booking")
                .then()
                .assertThat()
                .statusCode(200)
                .log()
                .all();
    }

    @Test
    public void getAllBookingsWithBaseMethod() {
        Response response = BaseClass.getAllBookings(endPoint);
        response.prettyPrint();
    }
}
