package ApiTests.Day2;


import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpartanNegativeGetTest {

    //BeforeAll is a annotation equals to @BeforeClass in testNg, we use with static method name
    @BeforeAll
    public static void init() {
        //save baseyrl inside this variable so that we dont need to type each http method.
       RestAssured.baseURI = "http://52.90.4.223:8000";
    }
    /*
    Given accept type is application/xml
    When user sends get request to /api/spartans end point
    Then response satatus code must be 406
    And response Content Type must be  application/xml;charset=UTF-8

     */
    @DisplayName("Get request to /api/spartans/10")
    @Test
    public void  Test1(){
        Response response = given().accept(ContentType.XML)
                .when()
                .get(baseURI + "/api/spartans/10");

        System.out.println( response.statusCode());
//406 yi sonda da yazabilirsin sonuc degismez
        assertEquals(406, response.statusCode());

//"application/xml;charset=UTF-8"  sonda da yazabilirsin sonuc degismez
        assertEquals("application/xml;charset=UTF-8", response.contentType() );


    }
}
