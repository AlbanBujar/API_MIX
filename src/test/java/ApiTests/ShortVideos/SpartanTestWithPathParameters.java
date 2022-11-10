package ApiTests.ShortVideos;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class SpartanTestWithPathParameters {

    @BeforeClass
    public void setUpClass(){
        RestAssured.baseURI="http://52.90.4.223:8000/";
    }

    /*
    Given accept type is Json
    And Id parameter values is 18
    When user sends Get request to /api/spartans/{id}
    Then response status code should be 200
    And response content-type: application/json;charset=UTF-8
    And "Allen" should be in response payload
     */
    @Test
    public void PartTest1(){
        Response response= RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 18)
                .when().get("/api/spartans/{id}");

        //verify sattus code
        assertEquals(response.statusCode(),200);

        //verify content type
        assertEquals(response.contentType(), "application/json;charset=UTF-8");

        //verify Allen exists
        assertTrue(response.body().asString().contains("Allen"));

        response.body().prettyPrint();

    }

}
