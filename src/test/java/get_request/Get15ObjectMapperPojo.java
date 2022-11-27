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

public class Get15ObjectMapperPojo extends JsonPlaceHolderBaseUrl {
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
    public void get15WithPojo() {
        //Set url
        spec.pathParams("first", "todos", "second", 198);

        //Set expected Data

        String expectedDataInString = new JsonPlaceHolderTestData().expectedDataInString(10, "quis eius est sint explicabo", true);
        JsonPlaceHolderPojo expectedDataInPojo = JsonUtils.convertJsonToJavaObject(expectedDataInString, JsonPlaceHolderPojo.class);
        System.out.println(expectedDataInPojo);

        //Send request and get response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion

        JsonPlaceHolderPojo actualDataInPojo = JsonUtils.convertJsonToJavaObject(response.asString(), JsonPlaceHolderPojo.class);
        System.out.println(expectedDataInPojo);

        assertEquals(200, response.statusCode());

        assertEquals(expectedDataInPojo.getUserId(), actualDataInPojo.getUserId());
        assertEquals(expectedDataInPojo.getTitle(), actualDataInPojo.getTitle());
        assertEquals(expectedDataInPojo.isComplated(), actualDataInPojo.isComplated());

        //Soft Assertion
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(200, response.statusCode());
        softAssert.assertEquals(expectedDataInPojo.getUserId(), expectedDataInPojo.getUserId());
        softAssert.assertEquals(expectedDataInPojo.getTitle(), expectedDataInPojo.getTitle());
        softAssert.assertEquals(expectedDataInPojo.isComplated(), expectedDataInPojo.isComplated());
        softAssert.assertAll();
    }
}