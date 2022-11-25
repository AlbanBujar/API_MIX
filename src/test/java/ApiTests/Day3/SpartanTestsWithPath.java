package ApiTests.Day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpartanTestsWithPath {
    //BeforeAll is a annotation equals to @BeforeClass in testNg, we use with static method name
    @BeforeAll
    public static void init() {
        //save baseyrl inside this variable so that we dont need to type each http method.
        baseURI = "http://52.90.4.223:8000";
    }
  /*
    Given accept type is Json
    And path param id is 10
    When user sends a GET request to "api/spartan/{id}"
    Then response status code should be 200
    And response content*type: application/json
    And response Payload values match the following
        id is 10,
        name is "Lorenza",
        gender is "Female",
        phone is 3312820936l
     */

    @DisplayName("GET reone spartan with Path Method")
    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("id", 10)
                .when()
                .get("api/spartans/{id}");

        //verify status code
        assertEquals(200, response.statusCode());
        //verify content type
        assertEquals("application/json", response.contentType());


        //verify each json key has specific value
        System.out.println(response.path("id").toString());
        System.out.println(response.path("name").toString());
        System.out.println(response.path("gender").toString());
        System.out.println(response.path("phone").toString());


        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        //assert the values
        assertEquals(10,id);
        assertEquals("Lorenza", name);
        assertEquals("Female", gender);
        assertEquals(3312820936l, phone);

    }

    @DisplayName("GET all spartan and negative with Path() ")
    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .when()
                .get("api/spartans");

        //response.prettyPrint();

        int firstId = response.path("id[0]"); // bu code selenium daki index locate etmekle ayni tek fark koseli parantez kullaniyoruz
        System.out.println("firstId= "+ firstId);
    }
}
