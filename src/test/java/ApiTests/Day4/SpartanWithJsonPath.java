package ApiTests.Day4;

import ApiTests.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanWithJsonPath extends SpartanTestBase {
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
    @DisplayName("GET request to   with JsonPath")
    @Test
    public void Test1() {
        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("id", 10)
                .when()
                .get("/api/spartans/{id}");

        //verify status code
        assertEquals(200, response.statusCode());
        //verify content type
        assertEquals("application/json", response.contentType());

        //Print name with path method
        System.out.println(response.path("name").toString());

        //assigning response to jsonpath
        JsonPath jsonPath = response.jsonPath();

        int id = jsonPath.getInt("id");
        String name = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        long phone = jsonPath.getLong("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

    }
}

