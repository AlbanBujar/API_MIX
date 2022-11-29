package ApiTests.Day3;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.annotation.meta.When;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestsWithParameters {

    //BeforeAll is a annotation equals to @BeforeClass in testNg, we use with static method name
    @BeforeAll
    public static void init() {
        //save baseyrl inside this variable so that we dont need to type each http method.
        RestAssured.baseURI = "http://52.90.4.223:8000";
    }
    /*
    Given accept type is Json
    And Id parameter values is5
    When user sends GET request to /api/spartans/{id}
    Then response status code should be 200
    And response content*type: application/json
    And "Blythe" should be in response payload
     */

    @DisplayName("GET request to /api/spartans/{id} with ID 5")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("id", 5)
                .when()
                .get("/api/spartans/{id}");

        //verify status code
        assertEquals(200, response.statusCode());
        //verify content type
        assertEquals("application/json", response.contentType());
        //verify Blythe in the json payload/body
        assertTrue(response.body().asString().contains("Blythe"));

    }
      /*
    Given accept type is Json
    And Id parameter values is 500
    When user sends GET request to /api/spartans/{id}
    Then response status code should be 404
    And response content*type: application/json
    And "Not Found"  message should be in response payload
     */

    @DisplayName("GET request to /api/spartans/{id} with ID 500 Negative Test")
    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON)
                .and()// istersen koyma cok etkisi yok
                .pathParam("id", 500)
                .when()
                .get("/api/spartans/{id}");
        //verify status code
        assertEquals(404, response.statusCode());
        //verify content type
        assertEquals("application/json", response.contentType());
        //verify Blythe in the json payload/body
        assertTrue(response.body().asString().contains("Not Found"));
    }

       /*
    Given accept type is Json
    And query parameter values are:
    gender|Female
    nameContains|e
    When user sends GET request to /api/spartans/search
    Then response status code should be 200
    And response content*type: application/json
    And "Female"  message should be in response payload
    And "Janette"  message should be in response payload
        */


    @DisplayName("GET request to /api/spartans/search with Query Params")
    @Test
    public void test3() {

        //log().all() hepsini yazidiriyor Butun responsu yazdirmak icin kullaniyoruz.
        Response response = given().log().all().
                accept(ContentType.JSON)
                .and().queryParam("nameContains", "e")  // bu pathParam degil queryParam
                .and().queryParam("gender", "Female")
                .when()
                .get("/api/spartans/search");
        //verify status code
        assertEquals(200, response.statusCode());
        //verify content type
        assertEquals("application/json", response.contentType());

        //verify "Female" be in response payload
        assertTrue(response.body().asString().contains("Female"));
        //verify "Janette" be in response payload
        assertTrue(response.body().asString().contains("Janette"));
    }
    @DisplayName("GET request to /api/spartans/search with Query Params (MAP)")
    @Test
    public void test4() {
        //create a map and add query parameters
        Map<String, Object> queryMap = new HashMap<>(); // Map ile query param ayni islevi yapiyor Test 3 ve 4 buna ornek
        queryMap.put("nameContais", "e");
        queryMap.put("gender", "Female");

        Response response = given().log().all().
                accept(ContentType.JSON)
                .and().queryParams(queryMap)  // bu pathParam degil queryParam
                .when()
                .get("/api/spartans/search");
        //verify status code
        assertEquals(200, response.statusCode());
        //verify content type
        assertEquals("application/json", response.contentType());

        //verify "Female" be in response payload
        assertTrue(response.body().asString().contains("Female"));
        //verify "Janette" be in response payload
        assertTrue(response.body().asString().contains("Janette"));
    }
    }

