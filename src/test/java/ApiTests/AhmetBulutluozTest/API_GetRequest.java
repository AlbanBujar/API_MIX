package ApiTests.AhmetBulutluozTest;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class API_GetRequest {

    /*
    https://jsonplaceholder.typicode.com/posts/44 url`ine bir
    GET request yolladigimizda donen Response`in

    status code`unun 200
    ve content type`inin JSON,
    ve response body`sinde bulunan userID`nin 5,
    ve re ponse body`sinde bulunan titile`in "optio dolor molestias sit"
          oldugunu test edin.
     */

    @Test
    public void get01(){

        //1. Request URL ve Body olustur

        String Url = "https://jsonplaceholder.typicode.com/posts/44";

        //2. Expected Data olustur

        JSONObject expBody = new JSONObject();

        expBody.put("userId", 5);
        expBody.put("title", "optio dolor molestias sit");

        //3.Response kayit

        Response response = given().when().get(Url);

       // response.prettyPrint();

        //4. Assertion

        response
                .then()
                .assertThat()// bunu karsilastirma yapmak icin kullaniyoruz
                // direkt status code da yazabiliriz ama karsilastirma yapmak icin asserThat yazmamazi lazim
                .statusCode(200)
                .contentType(ContentType.JSON);

        JsonPath actBody =  response.jsonPath();

        Assert.assertEquals(expBody.get("userId"), actBody.get("userId"));
        Assert.assertEquals(expBody.get("title"), actBody.get("title"));

        response.prettyPrint();



    }
}
