package zExercises01;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.junit.Assert.assertEquals;
import static zExercises01.E01_Post_Pojo.BOOKING_ID;

public class E06_Get extends HerOkuAppBaseUrl {
    /*
   Given
       https://restful-booker.herokuapp.com/booking/:id
   When
       User sends Get request
   Then
       Status code is 404
   And
       Response body is like "Not Found"
    */
    @Test
    public void getE06(){
        System.out.println("===========getE06===========");
        System.out.println();
        //Set the url
        spec.pathParams("first","booking","second",BOOKING_ID);

        //Set the expected data
        String expectedData = "Not Found";

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        assertEquals(404,response.statusCode());
        assertEquals(expectedData,response.asString());
    }
}
