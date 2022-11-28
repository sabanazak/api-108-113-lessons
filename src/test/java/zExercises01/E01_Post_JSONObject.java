package zExercises01;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;
import test_data.HerOkuAppTestData;
import utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class E01_Post_JSONObject extends HerOkuAppBaseUrl {
     /*
        Given
            https://restful-booker.herokuapp.com/booking
        And
            {
                "firstname" : "Jim",
                "lastname" : "Brown",
                "totalprice" : 111,
                "depositpaid" : true,
                "bookingdates" : {
                    "checkin" : "2018-01-01",
                    "checkout" : "2019-01-01"
                },
                "additionalneeds" : "Breakfast"
            }
        When
            User sends Post request
        Then
            Status code must be 200
        And
            Response body is like {
                                    "bookingid": 18217,
                                    "booking": {
                                        "firstname": "Jim",
                                        "lastname": "Brown",
                                        "totalprice": 111,
                                        "depositpaid": true,
                                        "bookingdates": {
                                            "checkin": "2018-01-01",
                                            "checkout": "2019-01-01"
                                        },
                                        "additionalneeds": "Breakfast"
                                    }
                                }
     */

    @Test
    public void post06_JSONObject() {
        //Set url
        spec.pathParams("first","booking");

        //Set the expected data (Payload)
        String expectedDataInString=new HerOkuAppTestData().expectedData_With_JSONObject("Murat","JSONObject",135,true,"2022-11-28","2022-11-29","Tea as 12:00 am");
        BookingPojo expectedData=JsonUtils.convertJsonToJavaObject(expectedDataInString,BookingPojo.class);

        /*    CANCELED TEMPORARILY
        ------------------------------------


        //Send the repuest and get rsponse
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        BookingResponsePojo actualData_Pojo = JsonUtils.convertJsonToJavaObject(response.asString(),BookingResponsePojo.class);
        Map<String,Object> actualData_Map = JsonUtils.convertJsonToJavaObject(response.asString(), HashMap.class);

        //Do Assertion
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getFirstname(),actualData_Pojo.getBooking().getFirstname());
        assertEquals(expectedData.getLastname(),actualData_Pojo.getBooking().getLastname());
        assertEquals(expectedData.getTotalprice(),actualData_Pojo.getBooking().getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData_Pojo.getBooking().getDepositpaid());

        assertEquals(expectedData.getBookingdates().getCheckin(),actualData_Pojo.getBooking().getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(),actualData_Pojo.getBooking().getBookingdates().getCheckout());

        assertEquals(expectedData.getAdditionalneeds(),actualData_Pojo.getBooking().getAdditionalneeds());

         */
    }
}
