package get_request;

import base_urls.DummyApiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

public class Get17 extends DummyApiBaseUrl {
    /*
        Given
            https://dummy.restapiexample.com/api/v1/employees
        When
            User sends Get Request to the Url
        Then
            Status code is 200
        And
            There are 24 employees
        And
            "Tiger Nixon" and "Garrett Winters" are among the employees
        And
            The greatest age is 66
        And
            The name of the lowest age is "[Tatyana Fitzpatrick]"
        And
            Total salary of all employees is 6,644,770
    */
    @Test
    public void get17(){
        //Set the url
        spec.pathParam("first","employees");

        //Set the expected data

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //Do assertion
        //Status code is 200, There are 24 employees
        response.
                then().
                assertThat().
                statusCode(200).
                body("data", hasSize(24),
                        "data.employee_name",hasItems("Tiger Nixon", "Garrett Winters"));

        JsonPath json=response.jsonPath();

        //The greatest age is 66
        List<Integer> ages = json.getList("data.employee_age");
        System.out.println("ages = " + ages);
        Collections.sort(ages);
        System.out.println("ages = " + ages);
        assertEquals(66, (int)ages.get(ages.size()-1));

        //The name of the lowest age is "[Tatyana Fitzpatrick]"
        List<String> namesOflowestAge = json.getList("data.findAll{it.employee_age==" + ages.get(0) + "}.employee_name");
        System.out.println("Got by List = "+namesOflowestAge);

        String nameOflowestAge = json.getString("data.findAll{it.employee_age==" + ages.get(0) + "}.employee_name");
        System.out.println("Got by String = "+nameOflowestAge);
        assertEquals("[Tatyana Fitzpatrick]", nameOflowestAge);

        //Total salary of all employees is 6,644,770
        List<Integer> employeeSalaries = json.getList("data.employee_salary");
        System.out.println(employeeSalaries);

        int totalSalary=0;
        for(Integer salary:employeeSalaries){
            totalSalary +=salary;
        }
        System.out.println("Total Salary : " + totalSalary);
        assertEquals(6644770,totalSalary);


        int totalSalaryLamda = employeeSalaries.stream().reduce(0,(t,u)->(t+u));
        System.out.println("Total Salary Lambda: " + totalSalaryLamda);

        //
        int totalSalaryLamda2 = employeeSalaries.stream().reduce(0,Math::addExact);
        System.out.println("Total Salary Lambda 2: " + totalSalaryLamda2);


    }
}
