import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pojoClasses.Booking;
import utulity.BaseClass;
import utulity.Url;

import static com.jayway.restassured.RestAssured.given;
import static utulity.Constants.ErrorMessages.*;

public class PostTest {
    String endPoint = Url.getBaseUri("/booking");
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void createBooking() {
        Booking body = new Booking();
        body = body.addBookingAllFieldsFilled("Diana", "Round", 6, true, "pijamas", "2022-08-04", "2022-08-08");

        Response response = BaseClass.postRequest(endPoint, body);
        int actualTotalPrice = response.path("booking.totalprice");
        int expectedTotalPrice = body.getTotalprice();
        boolean actualDeposit = response.path("booking.depositpaid");
        boolean expectedDeposit = body.isDepositpaid();

        softAssert.assertEquals(response.path("booking.firstname"), body.getFirstname(), NAME_IS_NOT_RIGHT);
        softAssert.assertEquals(response.path("booking.lastname"), body.getLastname(), LAST_NAME_IS_NOT_RIGHT);
        softAssert.assertEquals(actualTotalPrice, expectedTotalPrice, TOTAL_PRICE_IS_NOT_RIGHT);
        softAssert.assertEquals(actualDeposit, expectedDeposit, DEPOSIT_IS_NOT_RIGHT);
        softAssert.assertEquals(response.path("booking.bookingdates.checkin"), body.getBookingdates().getCheckin(), CHECKIN_DATE_IS_NOT_RIGHT);
        softAssert.assertEquals(response.path("booking.bookingdates.checkout"), body.getBookingdates().getCheckout(), CHECKOUT_DATE_IS_NOT_RIGHT);
        softAssert.assertEquals(response.getStatusCode(), 200, STATUS_CODE_IS_NOT_RIGHT);
        softAssert.assertAll();

        int id = response.path("bookingid");
        System.out.println(id);
        response.prettyPrint();
    }


    @Test
    public void createToken() {
        RestAssured.baseURI = Url.getBaseUri();

        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"username\" : \"admin\",\n" +
                        "    \"password\" : \"password123\"\n" +
                        "}")
                .post("/auth")
                .then()
                .assertThat()
                .statusCode(200)
                .log()
                .all()
                .and()
                .extract()
                .response();

        String token = response.path("token");
        System.out.println(token);
    }
}
