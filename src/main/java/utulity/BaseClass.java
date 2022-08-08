package utulity;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import pojoClasses.Booking;

public class BaseClass {
    public static Response postRequest(String requestURI, Booking booking) {
        RequestSpecification requestSpecification = RestAssured.given().body(booking);
        requestSpecification.contentType(ContentType.JSON);
        Response response = requestSpecification.post(requestURI);
        return response;
    }

    public static Response putRequest(String requestURI, Booking booking, String token) {
        RequestSpecification requestSpecification = RestAssured.given().body(booking).log().all();
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token", token);
        Response response = requestSpecification.put(requestURI);
        return response;
    }

    public static Response patchRequest(String requestURI, Booking booking, String token) {
        RequestSpecification requestSpecification = RestAssured.given().body(booking).log().all();
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token", token);
        Response response = requestSpecification.patch(requestURI);
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
