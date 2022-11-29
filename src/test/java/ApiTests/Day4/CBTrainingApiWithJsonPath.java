package ApiTests.Day4;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CBTrainingApiWithJsonPath {

        @BeforeAll
        public static void init() {
            //save baseurl inside this variable so that we dont need to type each http method.
            baseURI = "http://api.cybertektraining.com";

        }

        @DisplayName("GET Request to individual student")
        @Test
        public void test1() {
            //send a get request to student id 23401 as a path parameter and accept header application/json
            //verify status code=200 /content type=application/json;charset=UTF-8 /Content-Encoding = gzip
            //verify Date header exists
            //assert that
            /*
                firstName Vera
                batch 14
                section 12
                emailAddress aaa@gmail.com
                companyName Cybertek
                state IL
                zipCode 60606
                using JsonPath
             */

            given().log().all().
                    accept(ContentType.JSON)
                    .and().pathParam("id",23401)
                    .when()
                    .get("http://52.90.4.223:8000/api/teacher/{id}") //baseURI = "http://api.cybertektraining.com";
                    .then()
                    .statusCode(200)
                    .and().assertThat()
                    .contentType("application/json;charset=UTF-8")
                    .and()
                    .header("content-Encoding", is("gzip"))
                    .and()
                    .header("Date", notNullValue())
                    .and()
                    .assertThat()
                    .body("students[0].id",equalTo(23401)) //index nosunu bilmiyoruz link calismiyor 0 degisebilir
                    .body("students[0].firstName", is("Vera"))
                    .body("students[0].batch", is(14))
                    .body("students[0].section", is(12))
                    .body("students[0].emailAddress", is("aaa@gmail.com"))
                    .body("students[0].companyName", is("Cybertek"))
                    .body("students[0].state", is("IL"))
                    .body("students[0].zipCode", is(60606))
                    .body("students[0].using", is("JsonPath"));







        }
    }
