package delete_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.testng.AssertJUnit;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Delete01 extends JsonPlaceHolderBaseUrl {
    /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is { }
     */

    @Test
    public void delete01() {
        //Set url
        spec.pathParams("first","todos","second","198");

        //Set the expected Data
        Map<String,Object> expectedData = new HashMap<>();
        System.out.println("expectedData"+expectedData);

        //Send the request and get Response
        Response response=given().spec(spec).when().delete("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Map<String,Object> actualData=response.as(HashMap.class);
        System.out.println("actualData"+expectedData);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData,actualData);
        //or
        assertEquals(expectedData.size(),actualData.size());
        //or
        assertTrue(actualData.isEmpty());
        //or
        response.then().assertThat().body("isEmpty()", Matchers.is(true));
        //or
        assertEquals(0,response.asString().replaceAll("[^A-Za-z0-9]","").length());

        /*
            How to automate Delete Request in Api Testing?
            i) Create a new data by using "Post Request"
            ii) Use "Delete Request" to delete new data.

            Note: Do not delete existing data, create a data to delete.
         */


    }

}
