import com.jayway.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojoClasses.Booking;
import utulity.BaseClass;
import utulity.Url;

import static utulity.Authentication.getToken;
import static utulity.Constants.ErrorMessages.STATUS_CODE_IS_NOT_RIGHT;

public class DeleteTest {
    String endPoint = Url.getBaseUri("/booking/");

    @Test
    public void deleteBooking() {
        Booking body = new Booking();
        body = body.addBookingAllFieldsFilled("Diana", "Round", 6, true, "pijamas", "2022-08-04", "2022-08-08");
        Response response = BaseClass.postRequest(endPoint, body);
        endPoint = endPoint + response.path("bookingid");

        Response responseAfterDeletion = BaseClass.deleteRequest(endPoint, getToken());

        Assert.assertEquals(responseAfterDeletion.getStatusCode(), 201, STATUS_CODE_IS_NOT_RIGHT);
    }
}
