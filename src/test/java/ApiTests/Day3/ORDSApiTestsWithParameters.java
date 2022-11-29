package ApiTests.Day3;

import static io.restassured.RestAssured.baseURI;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ORDSApiTestsWithParameters {
    //BeforeAll is a annotation equals to @BeforeClass in testNg, we use with static method name
    @BeforeAll
    public static void init() {
        //save baseyrl inside this variable so that we dont need to type each http method.
        baseURI = "http://52.90.4.223:1000/ords/hr";
    }
     /*
    Given accept type is Json
    And parameters: q = {"region_id":2}
    When user sends a GET request to "countries"
    Then response status code should be 200
    And response content*type: application/json
    And Payload should contain "United States of America"
     */

    @DisplayName("GET request to /countries with Query Param")
    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParam("q", "{\"region_id\":2}")
                .when()
                .get("countries");

        //verify status code
        assertEquals(200, response.statusCode());
        //verify content type
        assertEquals("application/json", response.contentType());
        //verify contain "United States of America"
        assertTrue(response.body().asString().contains("United States of America"));

        response.prettyPrint();
    }

    /*
    Send  a GET request to employees and get only employees as a IT_PROG
     */
    @DisplayName("GET request to /employees with Query Param")
    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when()
                .get("employees");

        //verify status code
        assertEquals(200, response.statusCode());
        //verify content type
        assertEquals("application/json", response.contentType());
        //verify contain "United States of America"
        assertTrue(response.body().asString().contains("IT_PROG"));


        response.prettyPrint();
    }
}
