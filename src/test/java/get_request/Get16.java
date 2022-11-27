package get_request;

import base_urls.HerOkuAppBaseUrl;
import groovy.json.JsonBuilder;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingPojo;
import test_data.HerOkuAppTestData;
import test_data.JsonPlaceHolderTestData;
import utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Get16 extends HerOkuAppBaseUrl {
    /*
        Given
                https://restful-booker.herokuapp.com/booking/55
        When
                I send GET Request to the URL
        Then
                Status code is 200
                    {
                        "firstname": "Edgar",
                        "lastname": "Dominguez",
                        "totalprice": 111,
                        "depositpaid": true,
                        "bookingdates": {
                            "checkin": "2018-01-01",
                            "checkout": "2019-01-01"
                        },
                        "additionalneeds": "Breakfast"
                    }
     */

    @Test
    public void get16() {
        //Set url
        spec.pathParams("first","booking","second",555);

        //Set expected Data
        String expectedDataInString=new HerOkuAppTestData().expectedDataInString("Edgar","Dominguez",111,true,"2018-01-01","2019-01-01","Breakfast");
        System.out.println("expectedDataInString = \n" + expectedDataInString);

        //import org.json.JSONObject; Bu işlem örnek olması açısından farklı yöntemle yapıldı
        //--------------------------------------------------------------------------------------
        String expectedDataInStringWithJSONObject = new HerOkuAppTestData().expectedData_With_JSONObject("Edgar","Dominguez",111,true,"2018-01-01","2019-01-01","Breakfast");
        //System.out.println(expectedDataInStringWithJSONObject);


        //--------------------------------------------------------------------------------------

        BookingPojo expectedData = JsonUtils.convertJsonToJavaObject(expectedDataInString, BookingPojo.class);
        System.out.println("expectedDataInPojo=\n"+expectedData);

        //

        //Send request and get response
        Response response=given().spec(spec).when().get("/{first}/{second}");
        System.out.println("Response");
        response.prettyPrint();

        BookingPojo actualData = JsonUtils.convertJsonToJavaObject(response.asString(), BookingPojo.class);

        //Do assertion
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(expectedData.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());



    }

}
