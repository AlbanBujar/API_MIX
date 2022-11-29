package ApiTests.Day5;

import ApiTests.utilities.HRTestBase;
import ApiTests.utilities.HRTestBase;
import groovy.json.JsonOutput;
import io.restassured.http.*;
import io.restassured.internal.RestAssuredResponseOptionsGroovyImpl;
import io.restassured.path.json.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class ORDSHamcrestTest extends HRTestBase {


    @DisplayName("GET request to Employees IT_PROG endpoint and chaining")
    @Test
    public void employeesTest() {
        //send a get request to emplyoees endpoint with query parameter job_id IT_PROG
        //verify each job_id is IT_PROG
        //verify first names are .... (find proper method to check list against list)
        //verify emails without checking order (provide emails in different order,just make sure it has same emails)
        //expected names
        List<String> names = Arrays.asList("Alexander", "Bruce", "David", "Valli", "Diana");

        given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id", everyItem(equalTo("IT_PROG")))
                //everyItem butun hepsini kontrol ediyor ve "IT_PROG" olmayanlari bildirecek
                .body("items.first_name", containsInRelativeOrder("Alexander", "Bruce", "David", "Valli", "Diana"))
                //contains with order  ayni sirayi vermek lazim yoksa hata veriyor
                .body("items.email", containsInAnyOrder("VPATABAL", "DAUSTIN", "BERNST", "AHUNOLD", "DLORENTZ"))
                //contains without order sirasiz calisiyor sorun yok
                .body("items.first_name", equalTo(names)); // equality of lists assertion

    }


    @Test
    public void employeesTest2() {
        //we want to chain and also get response object


        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id", everyItem(equalTo("IT_PROG")))
                .extract().jsonPath();
        //extract() --> method that allow us to get response object after we use then() method.
        //assert that we have only 5 firstnames
        assertThat(jsonPath.getList("items.first_name"), hasSize(5)); // hasSize kac tane oldugunu buluyor

        //assert firstnames order
        assertThat(jsonPath.getList("items.first_name"),
                containsInRelativeOrder("Alexander", "Bruce", "David", "Valli", "Diana"));

    }
}
