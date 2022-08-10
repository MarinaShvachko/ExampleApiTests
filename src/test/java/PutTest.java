import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pojoClasses.Booking;
import pojoClasses.BookingResponse;
import utulity.BaseClass;
import utulity.Url;

import static utulity.Authentication.getToken;
import static utulity.Constants.ErrorMessages.NAME_IS_NOT_RIGHT;


public class PutTest {
    String endPoint = Url.getBaseUri("/booking/");
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void updateNameInBooking() {
        Booking body = new Booking();
        body = body.addBookingAllFieldsFilled("Diana", "Round", 6, true, "pijamas", "2022-08-04", "2022-08-08");
        BookingResponse bookingResponse = BaseClass.postRequest(endPoint, body);
        endPoint = endPoint + bookingResponse.getBookingid();
        body.setFirstname("Kostya");

        Booking updatedBookingResponse = BaseClass.putRequest(endPoint, body, getToken());

        softAssert.assertEquals(updatedBookingResponse.getFirstname(), body.getFirstname(), NAME_IS_NOT_RIGHT);
    }
}
