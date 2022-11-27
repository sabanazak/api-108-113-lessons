package get_request;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.GoRestDataPojo;
import pojos.GoRestPojo;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Get13Pojo extends GoRestBaseUrl {
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
                        "name": "Rahul Jha",
                        "email": "jha_rahul@beahan.co",
                        "gender": "male",
                        "status": "active"
                    }
                }

     */
    @Test
    public void get13(){
        //Set the url
        spec.pathParams("first","users","second","13");

        //Ser the expected Data
        GoRestDataPojo data=new GoRestDataPojo("Rahul Jha","jha_rahul@beahan.co","male","active");
        GoRestPojo expectedData=new GoRestPojo(null,data);

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        GoRestPojo actualData=response.as(GoRestPojo.class);
        System.out.println(actualData);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getMeta(),actualData.getMeta());
        assertEquals(data.getName(),actualData.getData().getName());
        assertEquals(data.getEmail(),actualData.getData().getEmail());
        assertEquals(data.getGender(),actualData.getData().getGender());
        assertEquals(data.getStatus(),actualData.getData().getStatus());

        //Soft Assertion
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(200,response.statusCode());
        softAssert.assertEquals(expectedData.getMeta(),actualData.getMeta());
        softAssert.assertEquals(data.getName(),actualData.getData().getName());
        softAssert.assertEquals(data.getEmail(),actualData.getData().getEmail());
        softAssert.assertEquals(data.getGender(),actualData.getData().getGender());
        softAssert.assertEquals(data.getStatus(),actualData.getData().getStatus());
        softAssert.assertAll();


    }




}
