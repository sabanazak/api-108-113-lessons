package utils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthenticationGmiBank {
    public static void main(String[] args) {
        System.out.println(generateToken());
    }

    public static String generateToken(){
        String url = "https://www.gmibank.com/api/authenticate";
        Map<String, Object> postBody = new HashMap<>();
        postBody.put("password","John.123");
        postBody.put("rememberme",true);
        postBody.put("username","john_doe");

        Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(postBody).when().post(url);
        //response.prettyPrint();

        return response.jsonPath().getString("id_token");
    }
}