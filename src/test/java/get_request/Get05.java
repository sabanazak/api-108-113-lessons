package get_request;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class Get05 extends HerOkuAppBaseUrl {


    @Test
    public void grt05() {
        /*
    Given
        https://restful-booker.herokuapp.com/booking
    When
        User sends get request to the URL
    Then
        Status code is 200
    And
       Among the data there should be someone whose firstname is "Adamz" and lastname is "Dear"
     */

        //Set the url
        spec.pathParams("first","booking")
                .queryParams("firstname","Adamz","lastname","Dear");
        //Set the expected data

        //Send the request and get the response
        Response response=given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //Do assertion
        assertEquals(200,response.getStatusCode());

        assertTrue(response.asString().contains("bookingid"));

    }

}
