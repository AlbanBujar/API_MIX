package ApiTests.Day4;

import ApiTests.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestWithPath extends HRTestBase {

    @DisplayName("GET request to countries  with Path Method")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParam("q", "{\"region_id\":2}")
                .when()
                .get("/countries");
        //verify status code
        assertEquals(200, response.statusCode());

        //pint limit result
        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        //print hasMore
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        //print first CountryId
        String firstCountryId = response.path("items[0].country_id");
        System.out.println("firstCountryId = " + firstCountryId);

        //second countryName
        String secondCountryName = response.path("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        //print http://52.90.4.223:1000/ords/hr/countries/CA
        String urlName = response.path("items[2].links[0].href"); // links icinde sadece 1 tane Arraylist oldugu icin links sonrasi 0 koyduk
        System.out.println("urlName = " + urlName);

        //get me all country name
        List<String> countryNames = response.path("items.country_name");
        System.out.println("countryNames = " + countryNames);

        //assert that all regions ids are equal to 2
        List<Integer> allRegionId = response.path("items.region_id");

        for (Integer regionsID : allRegionId) {
            System.out.println("allRegionId = " + allRegionId);
            assertEquals(2, regionsID);
        }


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

        //make sure we have only IT_PROG as a job_id
        List<String> allJobIds = response.path("items.job_id");

        for (String jobID : allJobIds) {
            System.out.println("jobId = " + jobID);
            assertEquals("IT_PROG", jobID);
        }
        response.prettyPrint();
    }
}
