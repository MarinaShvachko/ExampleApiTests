package utulity;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import pojoClasses.Booking;
import pojoClasses.BookingResponse;

public class BaseClass {
    public static BookingResponse postRequest(String requestURI, Booking booking) {
        RequestSpecification requestSpecification = RestAssured.given().body(booking).log().all();
        requestSpecification.contentType(ContentType.JSON);
        BookingResponse bookingResponse = requestSpecification.post(requestURI).then().assertThat().statusCode(200).extract().response().as(BookingResponse.class);
        return bookingResponse;
    }

    public static Booking putRequest(String requestURI, Booking booking, String token) {
        RequestSpecification requestSpecification = RestAssured.given().body(booking).log().all();
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token", token);
        Booking response = requestSpecification.put(requestURI).then().assertThat().statusCode(200).extract().response().as(Booking.class);
        return response;
    }

    public static Booking patchRequest(String requestURI, Booking booking, String token) {
        RequestSpecification requestSpecification = RestAssured.given().body(booking).log().all();
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token", token);
        Booking response = requestSpecification.patch(requestURI).then().assertThat().statusCode(200).extract().response().as(Booking.class);
        return response;
    }

    public static Response deleteRequest(String requestURI, String token) {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token", token).log().all();
        Response response = requestSpecification.delete(requestURI);
        return response;
    }

    public static Response getAllBookings(String requestURI) {
        RequestSpecification requestSpecification = RestAssured.given();
        Response response = requestSpecification.get(requestURI);
        return response;
    }
}
