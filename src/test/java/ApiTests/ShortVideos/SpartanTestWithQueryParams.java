package ApiTests.ShortVideos;


import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;

import static org.testng.Assert.*;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class SpartanTestWithQueryParams {

    @BeforeClass
    public void setUpClass() {
        baseURI = "http://52.90.4.223:8000/";
    }

    /*
    Given accept type is Json
    And query parameter values are:
    gender|Female
    nameContains|J
    When user sends GET request to /api/spartans/search
    Then responce status code should be 200
    And response content-type: application/json;charset=UTF-8
    And "Female" should be in response payload
    And "Janette" should be in response payload
     */
    @Test
    public void QueryParam1() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("gender", "Female")
                .and().queryParam("nameContains", "J")
                .when().get("/api/spartans/search");

        //verify status code
        assertEquals(response.statusCode(), 200);

        //verify content type
        assertEquals(response.contentType(), "application/json;charset=UTF-8");

        //verify status code
        assertTrue(response.body().asString().contains("Female"));

        //verify Male not exists
        assertFalse(response.body().asString().contains("Female"));

        //verify Janette
        assertTrue(response.body().asString().contains("Janette"));

        response.prettyPrint();

    }
    @Test
    public void queryParams2(){
        //creating map for query Params

        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("gender", "Female");
        paramsMap.put("nameContains", "J");

        //send request
        Response response = given().accept(ContentType.JSON)
                .and().queryParams(paramsMap)
                .when().get("/api/spartans/search");

        //verify status code
        assertEquals(response.statusCode(), 200);

        //verify content type
        assertEquals(response.contentType(), "application/json;charset=UTF-8");

        //verify status code
        assertTrue(response.body().asString().contains("Female"));

        //verify Male not exists
        assertFalse(response.body().asString().contains("Female"));

        //verify Janette
        assertTrue(response.body().asString().contains("Janette"));

        response.prettyPrint();


    }
}
