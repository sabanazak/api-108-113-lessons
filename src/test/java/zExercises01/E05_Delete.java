package zExercises01;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static zExercises01.E01_Post_Pojo.BOOKING_ID;
import static zExercises01.Utils.Authentication.getToken;

public class E05_Delete extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/{bookingId}
        When
            User sends Delete request
        Then
            Status code is 201
        And
            Response body is "Created"
     */
    @Test
    public void deleteE05() {
        System.out.println("===========deleteE05===========");
        System.out.println();
        //Set the url
        spec.pathParams("first","booking","second",BOOKING_ID);

        //Set Expexted Data : None
        String expectedData="Created";
        //Send The Request
        Response response=given().spec(spec).contentType(ContentType.JSON).
                headers("Cookie","token=" + getToken()).
                when().delete("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        assertEquals(SC_CREATED,response.statusCode());//import static org.apache.http.HttpStatus.*
        assertEquals(expectedData,response.asString());
    }
}
