package test_data;

import java.util.HashMap;
import java.util.Map;

public class GoRestTestData {

    public Map<String,String> goRestDataMap(String pName, String pEmail, String pGender, String pStatus){

        Map<String,String> dataMap = new HashMap<>();
        dataMap.put("name",pName);
        dataMap.put("email",pEmail);
        dataMap.put("gender",pGender);
        dataMap.put("status",pStatus);

        return dataMap;
    }
/*
            {
                "meta": null,
                "data": {

                    "name": "Suresh Johar",
                    "email": "suresh_johar@von-damore.biz",
                    "gender": "female",
                    "status": "active"
                }
            }
 */
    public Map<String,Object> expectedDataSetUp(Object pMeta, Map<String ,String > pData){

        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("meta",pMeta);
        expectedData.put("data",pData);

        return expectedData;
    }
}
