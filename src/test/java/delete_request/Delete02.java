package delete_request;
import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import utils.AuthenticationHerOkuApp;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.AuthenticationHerOkuApp.generateToken;

public class Delete02 extends HerOkuAppBaseUrl {
    /*
      Given
          https://restful-booker.herokuapp.com/booking/{bookingId}
      When
           I send DELETE Request to the Url
       Then
           Status code is 201
           And Response body is "Created"
   */
    @Test
    public void delete02(){
        //Set the url
        spec.pathParams("first","booking","second",863);

        //Set the expected data
        String expectedData = "Created";

        //Send the request and get the response
        Response response = given().
                spec(spec).
                headers("Cookie", "token="+generateToken()).
                contentType(ContentType.JSON).
                when().
                delete("/{first}/{second}");

        response.prettyPrint();

        //Do assertion
        assertEquals(201,response.statusCode());
        assertEquals(expectedData,response.asString());
    }
}
