package test_data;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HerOkuAppTestData {

    public Map<String,String> bookingdatesMapSetUp(String checkin,String checkout){

        Map<String,String> bookingdatesMap = new HashMap<>();
        bookingdatesMap.put("checkin",checkin);
        bookingdatesMap.put("checkout",checkout);

        return bookingdatesMap;
    }

    public Map<String,Object> expectedDataSetUp(String firstname, String lastname, Integer totalprice,Boolean depositpaid, Map<String ,String > bookingdates,String additionalneeds ){

        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("firstname",firstname);
        expectedData.put("lastname",lastname);
        expectedData.put("totalprice",totalprice);
        expectedData.put("depositpaid",depositpaid);
        expectedData.put("bookingdates",bookingdates);
        expectedData.put("additionalneeds", additionalneeds);

        return expectedData;
    }

    public String expectedDataInString(String firstname,String lastname,int totalprice,boolean depositpaid,String checkin,String checkout,String additionalneeds){
        String expectedData=  "{\n" +
                "    \"firstname\": \""+firstname+"\",\n" +
                "    \"lastname\": \""+lastname+"\",\n" +
                "    \"totalprice\": "+totalprice+" ,\n" +
                "    \"depositpaid\": "+depositpaid+" ,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \""+checkin+"\",\n" +
                "        \"checkout\": \""+checkout+"\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \""+additionalneeds+"\"\n" +
                "}";

        return expectedData;
    }

    public String expectedData_With_JSONObject(String firstname,
                                               String lastname,
                                               int totalprice,
                                               boolean depositpaid,
                                               String checkin,
                                               String checkout,
                                               String additionalneeds){

        JSONObject expectedData = new JSONObject();

        expectedData.put("firstname", firstname);
        expectedData.put("lastname", lastname);
        expectedData.put("totalprice", totalprice);
        expectedData.put("depositpaid", depositpaid);
            JSONObject bookingDates = new JSONObject();
            bookingDates.put("checkin", checkin);
            bookingDates.put("checkout", checkout);
        expectedData.put("bookingdates", bookingDates);
        expectedData.put("additionalneeds", additionalneeds);

        return expectedData.toString();
    }

}