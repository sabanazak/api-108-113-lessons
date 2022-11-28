package zExercises01;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import utils.JsonUtils;
import zExercises01.Utils.Authentication;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static zExercises01.E01_Post_Pojo.BOOKING_ID;

public class E03_Put extends HerOkuAppBaseUrl {
     /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    And
        {
            "firstname": "Murat-Updated",
            "lastname": "JSONObject-Updated",
            "totalprice": 200,
            "depositpaid": false,
            "bookingdates": {
                "checkin": "2022-11-28",
                "checkout": "2022-11-29"
            },
            "additionalneeds": "Breakfast"
        }
    When
        User sends Put request
    Then
        Status code is 200
    And
        Response body is like
        {
            "firstname": "Murat-Updated",
            "lastname": "JSONObject-Updated",
            "totalprice": 200,
            "depositpaid": false,
            "bookingdates": {
                "checkin": "2022-11-28",
                "checkout": "2022-11-29"
            },
            "additionalneeds": "Breakfast"
        }
     */

    @Test
    public void putE03() {
        System.out.println("===========putE03===========");
        System.out.println();
        //Set the url
        spec.pathParams("first","booking","second",BOOKING_ID);

        //Set the expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2022-11-27","2022-11-29");
        BookingPojo expectedData = new BookingPojo("Murat-Updated","JSONObject-Updated",200,false,bookingDatesPojo,"Breakfast");
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).
                contentType(ContentType.JSON).headers("Cookie","token="+ Authentication.getToken()).
                body(expectedData).
                when().put("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        BookingPojo actualData = JsonUtils.convertJsonToJavaObject(response.asString(),BookingPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());

        assertEquals(bookingDatesPojo.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(),actualData.getBookingdates().getCheckout());

    }


}
