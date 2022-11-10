package ApiTests.Day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
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

        Response response = RestAssured.given().accept(ContentType.JSON).when().get(baseUrl + "/api/spartans");

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

    @DisplayName("Get request to Fidole")
    @Test
    public void Test2() {
        Response response = RestAssured.given().accept(ContentType.JSON).when().get(baseUrl + "/api/spartans/3");

        System.out.println("response.statusCode() = " + response.statusCode());

        System.out.println("response.contentType() = " + response.contentType());

        response.prettyPrint();
        //  verify status code  200
        Assertions.assertEquals(response.statusCode(), 200);

        //  verify contet type
        Assertions.assertEquals(response.contentType(), "application/json");

        //verify json body contains Fidole
        Assertions.assertTrue(response.body().asString().contains("Fidole"));
    }

    /*
        Given no headers provided
        When Users sends GET request to /api/hello
        Then response status code should be 200
        And Content type header should be "text/plain;charset=UTF-8"
        And header should contain date
        And Content-Length should be 17
        And body should be "Hello from Sparta"
     */
    @DisplayName("Get request to /api/hello")
    @Test
    public void Test3() {
        // send request and save response inside that the response abject
        Response response = RestAssured.when().get(baseUrl + "/api/hello");

        //  verify status code  200
        Assertions.assertEquals(response.statusCode(), 200);

        //  verify contet type
        Assertions.assertEquals(response.contentType(), "text/plain;charset=UTF-8");

        //  verify we have headers named date
        //  we use hasHeaderWithname method to verify header exist or not
       Assertions.assertTrue( response.headers().hasHeaderWithName("Date"));

       // how to get and header from response using header key?
        // we use response.header(String headerName) method to get any header value

        System.out.println("response.header(\"Content-Length\") = " + response.header("Content-Length"));
        System.out.println( response.header("Date"));

        // verify content lenght is 17
        Assertions.assertEquals(response.header("Content-Length"), "17");

        // verify body is "Hello from Sparta"
        Assertions.assertEquals(response.body().asString(), "Hello from Sparta");


    }
}

