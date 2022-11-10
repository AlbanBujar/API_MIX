package ApiTests.Day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testng.asserts.Assertion;

public class SpartanGetRequests {
    String baseUrl = "http://52.90.4.223:8000";

    //  Given Accept type application/json
    //  When user send GET request to api/spartans end point
    //  Then status code must 200
    //  Then response Content Type must be application/json
    //  And response body should include spartan result

    @Test
    public void Test1() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(baseUrl + "/api/spartans");

        //printing status code from response object
        System.out.println("response.statusCode() = " + response.statusCode());

        //printing  response content TYPE from response object
        System.out.println("response.contentType = " + response.contentType());

        //print whole result body
        response.prettyPrint();

        // how to do API testing then?
        //  Then status code must 200
        Assertions.assertEquals(response.statusCode(), 200);

        //verify  Content Type is application/json
        Assertions.assertEquals(response.contentType(), "application/json");

    }

    /*
    Given accept is application/json
    When user sends a get request to /api/spartans/3
    Then status code should be 200
    And content type should be application/json
    And json body should contain Fidole (isim 3 numaradki kisinin ismi)
     */

    @Test
    public void Test2() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(baseUrl + "/api/spartans/3");

        System.out.println("response.statusCode() = " + response.statusCode());

        System.out.println("response.contentType() = " + response.contentType());

        response.prettyPrint();

        Assertions.assertEquals(response.statusCode(), 200);

        Assertions.assertEquals(response.contentType(), "application/json");
    }
}

