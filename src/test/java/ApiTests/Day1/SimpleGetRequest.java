package ApiTests.Day1;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class SimpleGetRequest {

    String url = "http://52.90.4.223:8000/api/spartans";

    @Test
    public void test1() {

        //send a get reguest and save response inside the Response object
        Response response = RestAssured.get(url);

        //print response status code
        System.out.println(response.statusCode());

        //print response body
        response.prettyPrint();




        }

    }

