package zExercises01;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;
import utils.JsonUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static zExercises01.E01_Post_Pojo.BOOKING_ID;
import static zExercises01.Utils.Authentication.getToken;

public class E04_Patch extends HerOkuAppBaseUrl {
    /*
      Given
    Given
        https://restful-booker.herokuapp.com/booking/:id
    And
        {
            "firstname": "Murat-Patched",
            "lastname": "JSONObject-Patched",
            "bookingdates": {
                "checkin": "2022-11-30"
            }
        }
      When
           I send PATCH Request to the Url
      Then
            Status code is 200
      And
            response body is like
                    {
                        "firstname": "Murat-Patched",
                        "lastname": "JSONObject-Patched",
                        "totalprice": 200,
                        "depositpaid": false,
                        "bookingdates": {
                            "checkin": "2022-11-30",
                            "checkout": "2022-11-29"
                        },
                        "additionalneeds": "Breakfast"
                    }
   */

    @Test
    public void patchE04() {
        System.out.println("===========patchE04===========");
        System.out.println();
        //Set the url
        spec.pathParams("first","booking","second",BOOKING_ID);

        //Set the expected data
        JSONObject jObject=new JSONObject();
        jObject.put("firstname","Murat-Patched");
        jObject.put("lastname","JSONObject-Patched");
            JSONObject dates=new JSONObject();
            dates.put("checkin","2022-10-30");
            dates.put("checkout","2022-12-30");
        jObject.put("bookingdates",dates);

        //System.out.println(jObject.toString());

        //BookingPojo expectedData= JsonUtils.convertJsonToJavaObject(jObject.toString(),BookingPojo.class);
        //System.out.println(expectedData);

        Response response=given().spec(spec).
                contentType(ContentType.JSON).
                headers("Cookie","token="+getToken()).
                body(jObject.toString()).
                when().patch("/{first}/{second}");
        response.prettyPrint();

        BookingPojo actualData_Pojo = JsonUtils.convertJsonToJavaObject(response.asString(),BookingPojo.class);
        System.out.println("actualData_Pojo = " + actualData_Pojo);


        //Do assertion
        assertEquals(200,response.statusCode());
        assertEquals(jObject.get("firstname"),actualData_Pojo.getFirstname());
        assertEquals(jObject.get("lastname"),actualData_Pojo.getLastname());
        assertEquals(Integer.valueOf(200),actualData_Pojo.getTotalprice());
        assertEquals(false,actualData_Pojo.getDepositpaid());

        assertEquals(jObject.getJSONObject("bookingdates").get("checkin"),actualData_Pojo.getBookingdates().getCheckin());
        assertEquals(jObject.getJSONObject("bookingdates").get("checkout"),actualData_Pojo.getBookingdates().getCheckout());

        assertEquals("Breakfast",actualData_Pojo.getAdditionalneeds());


    }

}
