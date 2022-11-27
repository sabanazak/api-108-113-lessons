package post_request;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post02 extends HerOkuAppBaseUrl {
/*
           1) https://restful-booker.herokuapp.com/booking
            2) {
                 "firstname": "John",
                 "lastname": "Doe",
                 "totalprice": 11111,
                 "depositpaid": true,
                 "bookingdates": {
                     "checkin": "2021-09-09",
                     "checkout": "2021-09-21"
                  },
                "additionalneeds": "Breakfast"
               }
        When
            I send POST Request to the Url
        Then
            Status code is 200
            And response body should be like {
                                                "bookingid": 5315,
                                                "booking": {
                                                    "firstname": "John",
                                                    "lastname": "Doe",
                                                    "totalprice": 11111,
                                                    "depositpaid": true,
                                                    "bookingdates": {
                                                        "checkin": "2021-09-09",
                                                        "checkout": "2021-09-21"
                                                    }
                                                "additionalneeds": "Breakfast"
                                                }
                                             }

 */

    @Test
    public void post02(){
        //Set the url
        spec.pathParam("first","booking");

        //Set the expected data
        HerOkuAppTestData obj = new HerOkuAppTestData();
        Map<String ,String> bookingdatesMap = obj.bookingdatesMapSetUp("2021-09-09","2021-09-21");
        Map<String,Object> expectedData = obj.expectedDataSetUp("John","Doe",11111,true, bookingdatesMap,"Breakfast");
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        //Do assertion
        Map<String,Object> actualData = response.as(HashMap.class);//De-Serialization
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.statusCode());


        //1st Way
        Map<String,Object> response_booking=(Map)actualData.get("booking");  //actualData.get("booking") -> LinkedHashMap
        Map<String,String> response_bookingdates=(Map)response_booking.get("bookingdates");
        //or
        response_bookingdates=(Map) ((Map)(actualData.get("booking"))).get("bookingdates");

        assertEquals(expectedData.get("firstname"),response_booking.get("firstname"));
        assertEquals(expectedData.get("lastname"),response_booking.get("lastname"));
        assertEquals(expectedData.get("totalprice"),response_booking.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),response_booking.get("depositpaid"));
        assertEquals(expectedData.get("additionalneeds"),response_booking.get("additionalneeds"));

        assertEquals(((Map)expectedData.get("bookingdates")).get("checkin"),response_bookingdates.get("checkin"));
        assertEquals(((Map)expectedData.get("bookingdates")).get("checkout"),response_bookingdates.get("checkout"));
        //OR
        assertEquals(bookingdatesMap.get("checkin"),response_bookingdates.get("checkin"));
        assertEquals(bookingdatesMap.get("checkout"),response_bookingdates.get("checkout"));

        //2. way
        assertEquals(expectedData.get("firstname"),((Map)actualData.get("booking")).get("firstname"));
        assertEquals(expectedData.get("lastname"),((Map)actualData.get("booking")).get("lastname"));
        assertEquals(expectedData.get("totalprice"),((Map)actualData.get("booking")).get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),((Map)actualData.get("booking")).get("depositpaid"));
        assertEquals(expectedData.get("additionalneeds"),((Map)actualData.get("booking")).get("additionalneeds"));

        assertEquals(bookingdatesMap.get("checkin"),((Map)(((Map)actualData.get("booking")).get("bookingdates"))).get("checkin"));
        assertEquals(bookingdatesMap.get("checkout"),((Map)(((Map)actualData.get("booking")).get("bookingdates"))).get("checkout"));

    }

}
