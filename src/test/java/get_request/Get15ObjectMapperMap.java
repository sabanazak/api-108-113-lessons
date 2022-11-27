package get_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.JsonPlaceHolderPojo;
import test_data.JsonPlaceHolderTestData;
import utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class Get15ObjectMapperMap extends JsonPlaceHolderBaseUrl {
/*
        Given
	        https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send GET Request to the URL
	 	Then
	 		Status code is 200
	 		And response body is like {
									    "userId": 10,
									    "id": 198,
									    "title": "quis eius est sint explicabo",
									    "completed": true
									  }
     */

    @Test
    public void get15WithMap() {
        //Set url
        spec.pathParams("first","todos","second",198);

        //Set expected Data
        //NOT RECOMENTED
        //String expectedDataInString="{\n" +
        //                                "\"userId\": 10,\n" +
        //                                "\"id\": 198,\n" +
        //                                "\"title\": \"quis eius est sint explicabo\",\n" +
        //                                "\"completed\": true\n" +
        //                            "}";

        String expectedDataInString=new JsonPlaceHolderTestData().expectedDataInString(10,"quis eius est sint explicabo",true);
        Map<String,Object> expectedData = JsonUtils.convertJsonToJavaObject(expectedDataInString, HashMap.class);
        System.out.println(expectedData);


        //Send request and get response
        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        Map<String,Object> actualData = JsonUtils.convertJsonToJavaObject(response.asString(), HashMap.class);
        System.out.println(expectedData);


        assertEquals(200,response.statusCode());

        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));

        //Soft Assertion
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(200,response.statusCode());
        softAssert.assertEquals(expectedData.get("userId"),actualData.get("userId"));
        softAssert.assertEquals(expectedData.get("title"),actualData.get("title"));
        softAssert.assertEquals(expectedData.get("completed"),actualData.get("completed"));
        softAssert.assertAll();
    }


}
