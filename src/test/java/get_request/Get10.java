package get_request;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.GoRestTestData;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get10 extends GoRestBaseUrl {
/*
        Given
            https://gorest.co.in/public/v1/users/13
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
            {
                "meta": null,
                "data": {
                    "id": 13,
                    "name": "Suresh Johar",
                    "email": "suresh_johar@von-damore.biz",
                    "gender": "female",
                    "status": "active"
                }
            }
     */

    @Test
    public void get10() {
        //Set the url
        //users/13
        spec.pathParams("first","users","second",13);

        //Set the expected data
        GoRestTestData obj = new GoRestTestData();
        Map<String ,String> dataMap = obj.goRestDataMap("Suresh Johar","suresh_johar@von-damore.biz","female","active");
        Map<String,Object> expectedData = obj.expectedDataSetUp(null,dataMap);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        Map<String ,Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        //1st Way
        Map<String,Object> response_data =  (Map)actualData.get("data");

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("meta"),null);

        assertEquals(dataMap.get("name"),response_data.get("name"));
        assertEquals(dataMap.get("email"),response_data.get("email"));
        assertEquals(dataMap.get("gender"),response_data.get("gender"));
        assertEquals(dataMap.get("status"),response_data.get("status"));
        //or
        assertEquals(dataMap.get("name"), ((Map)(actualData.get("data"))).get("name"));
        assertEquals(dataMap.get("email"),((Map)(actualData.get("data"))).get("email"));
        assertEquals(dataMap.get("gender"),((Map)(actualData.get("data"))).get("gender"));
        assertEquals(dataMap.get("status"),((Map)(actualData.get("data"))).get("status"));


    }

}
