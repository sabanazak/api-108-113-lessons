package post_request;
import base_urls.GmiBankBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.CountryPost;
import pojos.State;
import utils.JsonUtils;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.AuthenticationGmiBank.generateToken;

public class Post08GmiBank extends GmiBankBaseUrl {
    /*
       Given
          https://www.gmibank.com/api/tp-countries
          {
            "name": "USA",
            "states": [
              {
                "id": 12,
                "name": "California",
                "tpcountry": null
              }
            ]
          }
      When
           I send POST Request to the URL
       Then
           Status code is 200
       And
           Response body is like {
                                  "id": 171555,
                                  "name": "USA",
                                  "states": [
                                      {
                                          "id": 12,
                                          "name": "California",
                                          "tpcountry": null
                                      }
                                  ]
                                }
   */
    @Test
    public void post06(){
        //Set the url
        spec.pathParams("first","tp-countries");

        //Set the expected data
        State states = new State(12,"California",null);
        ArrayList<State> statesList = new ArrayList<>();
        statesList.add(states);

        CountryPost expectedData = new CountryPost("USA",statesList);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().
                spec(spec).
                contentType(ContentType.JSON).
                headers("Authorization","Bearer "+generateToken()).
                body(expectedData).
                when().
                post("/{first}");
        response.prettyPrint();

        //Do Assertion
        CountryPost actualData = JsonUtils.convertJsonToJavaObject(response.asString(),CountryPost.class);
        System.out.println("actualData = " + actualData);

        assertEquals(201,response.statusCode());
        assertEquals(expectedData.getName(),actualData.getName());
        assertEquals(states.getId(),actualData.getStates().get(0).getId());
        assertEquals(states.getName(),actualData.getStates().get(0).getName());
        assertEquals(states.getTpcountry(),actualData.getStates().get(0).getTpcountry());

    }
}