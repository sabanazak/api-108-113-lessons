package zExercises01;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;
import test_data.HerOkuAppTestData;
import utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static zExercises01.E01_Post_Pojo.BOOKING_ID;

public class E02_Get_BeforeUpdate extends HerOkuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/23873
    When
        User sends Get request
    Then
        Status code is 200
    And
        Response body is like
           {
                "firstname": "Murat",
                "lastname": "JSONObject",
                "totalprice": 135,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2022-11-27",
                    "checkout": "2022-11-28"
                },
                "additionalneeds": "Tea as 12:00 am"
            }

     */

    @Test
    public void getBeforeUpdate() {
        System.out.println("===========getBeforeUpdate===========");
        System.out.println();
        //Set url
        spec.pathParams("first","booking","second",BOOKING_ID);

        //Set the expected data (Payload)
        JSONObject jObject=new JSONObject();
        jObject.put("firstname","Murat");
        jObject.put("lastname","JSONObject");
        jObject.put("totalprice",135);
        jObject.put("depositpaid",true);
            JSONObject dates=new JSONObject();
            dates.put("checkin","2022-11-27");
            dates.put("checkout","2022-11-28");
        jObject.put("bookingdates",dates);
        jObject.put("additionalneeds","Tea as 12:00 am");

        BookingPojo expectedData = JsonUtils.convertJsonToJavaObject(jObject.toString(),BookingPojo.class);

        //Send the repuest and get rsponse
        Response response = given().spec(spec).contentType(ContentType.JSON).when().get("/{first}/{second}");
        response.prettyPrint();

        BookingPojo actualData_Pojo = JsonUtils.convertJsonToJavaObject(response.asString(),BookingPojo.class);
        System.out.println("actualData_Pojo="+ actualData_Pojo);
        Map<String,Object> actualData_Map = JsonUtils.convertJsonToJavaObject(response.asString(), HashMap.class);
        System.out.println("actualData_Map="+ actualData_Map);

        //Do assertion
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getFirstname(),actualData_Pojo.getFirstname());
        assertEquals(expectedData.getLastname(),actualData_Pojo.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData_Pojo.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData_Pojo.getDepositpaid());

        assertEquals(expectedData.getBookingdates().getCheckin(),actualData_Pojo.getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(),actualData_Pojo.getBookingdates().getCheckout());

        assertEquals(expectedData.getAdditionalneeds(),actualData_Pojo.getAdditionalneeds());

        //OR
        assertEquals(200,response.statusCode());
        assertEquals(jObject.get("firstname"),actualData_Pojo.getFirstname());
        assertEquals(jObject.get("lastname"),actualData_Pojo.getLastname());
        assertEquals(jObject.get("totalprice"),actualData_Pojo.getTotalprice());
        assertEquals(jObject.get("depositpaid"),actualData_Pojo.getDepositpaid());

        assertEquals(jObject.getJSONObject("bookingdates").get("checkin"),actualData_Pojo.getBookingdates().getCheckin());
        assertEquals(jObject.getJSONObject("bookingdates").get("checkout"),actualData_Pojo.getBookingdates().getCheckout());

        assertEquals(jObject.get("additionalneeds"),actualData_Pojo.getAdditionalneeds());
    }

}
