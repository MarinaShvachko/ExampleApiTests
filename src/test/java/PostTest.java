import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pojoClasses.Booking;
import pojoClasses.BookingResponse;
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

        BookingResponse bookingResponse = BaseClass.postRequest(endPoint, body);

        softAssert.assertEquals(bookingResponse.getBooking().getFirstname(), body.getFirstname(), NAME_IS_NOT_RIGHT);
        softAssert.assertEquals(bookingResponse.getBooking().getLastname(), body.getLastname(), LAST_NAME_IS_NOT_RIGHT);
        softAssert.assertEquals(bookingResponse.getBooking().getTotalprice(), body.getTotalprice(), TOTAL_PRICE_IS_NOT_RIGHT);
        softAssert.assertEquals(bookingResponse.getBooking().isDepositpaid(), body.isDepositpaid(), DEPOSIT_IS_NOT_RIGHT);
        softAssert.assertEquals(bookingResponse.getBooking().getBookingdates().getCheckin(), body.getBookingdates().getCheckin(), CHECKIN_DATE_IS_NOT_RIGHT);
        softAssert.assertEquals(bookingResponse.getBooking().getBookingdates().getCheckout(), body.getBookingdates().getCheckout(), CHECKOUT_DATE_IS_NOT_RIGHT);
        softAssert.assertAll();
        System.out.println(bookingResponse.getBookingid() + " id of booking");
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
