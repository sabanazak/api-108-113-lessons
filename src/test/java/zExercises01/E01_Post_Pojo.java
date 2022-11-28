package zExercises01;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;
import utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class E01_Post_Pojo extends HerOkuAppBaseUrl {
     /*
        Given
            https://restful-booker.herokuapp.com/booking
        And
            {
                "firstname": "Murat",
                "lastname": "JSONObject",
                "totalprice": 135,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2022-11-28",
                    "checkout": "2022-11-29"
                },
                "additionalneeds": "Tea as 12:00 am"
            }
        When
            User sends Post request
        Then
            Status code must be 200
        And
            Response body is like {
                                    "bookingid": 18217,
                                    "booking": {
                                            "firstname": "Murat",
                                            "lastname": "JSONObject",
                                            "totalprice": 135,
                                            "depositpaid": true,
                                            "bookingdates": {
                                                "checkin": "2022-11-28",
                                                "checkout": "2022-11-29"
                                            },
                                            "additionalneeds": "Tea as 12:00 am"
                                        }
                                    }
     */

    static int BOOKING_ID;

    @Test
    public void post06_Pojo() {
        System.out.println("===========post06_Pojo===========");
        System.out.println();
        //Set url
        spec.pathParams("first","booking");

        //Set the expected data (Payload)
        BookingDatesPojo expectedDataBookingDates=new BookingDatesPojo("2022-11-27","2022-11-28");
        BookingPojo expectedData=new BookingPojo("Murat","JSONObject",135,true,expectedDataBookingDates,"Tea as 12:00 am");

        //Send the repuest and get rsponse
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        BookingResponsePojo actualData_Pojo = JsonUtils.convertJsonToJavaObject(response.asString(),BookingResponsePojo.class);
        Map<String,Object> actualData_Map = JsonUtils.convertJsonToJavaObject(response.asString(), HashMap.class);

        //Do Assertion
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(),200);
        softAssert.assertEquals(actualData_Pojo.getBooking().getFirstname(),expectedData.getFirstname(),"firstname did not match");
        softAssert.assertEquals(actualData_Pojo.getBooking().getLastname(),expectedData.getLastname(),"lastname did not match");
        softAssert.assertEquals(actualData_Pojo.getBooking().getTotalprice(),expectedData.getTotalprice(),"totalprice did not match");
        softAssert.assertEquals(actualData_Pojo.getBooking().getDepositpaid(),expectedData.getDepositpaid(),"deposidpaid did not match");

        softAssert.assertEquals(actualData_Pojo.getBooking().getBookingdates().getCheckin(),expectedDataBookingDates.getCheckin(),"checkin did not match");
        softAssert.assertEquals(actualData_Pojo.getBooking().getBookingdates().getCheckout(),expectedDataBookingDates.getCheckout(),"checkout did not match");

        softAssert.assertEquals(actualData_Pojo.getBooking().getAdditionalneeds(),expectedData.getAdditionalneeds(),"additionalneeds did not match");

        softAssert.assertAll();

        System.out.println("bookingid = "+actualData_Pojo.getBookingid());
        //Process over booking created by you
        BOOKING_ID=actualData_Pojo.getBookingid();




    }


}
