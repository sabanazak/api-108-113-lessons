package zExercises01.Utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Authentication {

    public static void main(String[] args) {
        System.out.println(getToken());
    }

    public static String getToken() {
        String url = "https://restful-booker.herokuapp.com/auth";

        Map<String,String> payloadData=new HashMap<>();
        payloadData.put("username","admin");
        payloadData.put("password","password123");

        Response response=given().contentType(ContentType.JSON).body(payloadData).when().post(url);
        //response.prettyPrint();
        return response.jsonPath().get("token");
    }
}
