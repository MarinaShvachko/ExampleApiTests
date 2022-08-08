import com.jayway.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pojoClasses.Booking;
import utulity.BaseClass;
import utulity.Url;

import static utulity.Authentication.getToken;
import static utulity.Constants.ErrorMessages.NAME_IS_NOT_RIGHT;
import static utulity.Constants.ErrorMessages.STATUS_CODE_IS_NOT_RIGHT;


public class PutTest {
    String endPoint = Url.getBaseUri("/booking/");
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void updateNameInBooking() {
        Booking body = new Booking();
        body = body.addBookingAllFieldsFilled("Diana", "Round", 6, true, "pijamas", "2022-08-04", "2022-08-08");
        Response response = BaseClass.postRequest(endPoint, body);
        endPoint = endPoint + response.path("bookingid");
        body.setFirstname("Kostya");

        Response responseAfterUpdate = BaseClass.putRequest(endPoint, body, getToken());

        softAssert.assertEquals(responseAfterUpdate.getStatusCode(), 200, STATUS_CODE_IS_NOT_RIGHT);
        softAssert.assertEquals(response.path("booking.firstname"), body.getFirstname(), NAME_IS_NOT_RIGHT);
    }
}
