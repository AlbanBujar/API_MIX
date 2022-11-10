package ApiTests.ShortVideos;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SpartanTests {

    String spartanBaseUrl = "http://52.90.4.223:8000/";

    @Test
    public void viewSpartanTest1() {
        Response response = RestAssured.get(spartanBaseUrl + "/api/spartans");

        //response ile basliyoruz sonra istedigim code lari yaziyoruz.
        System.out.println(response.statusCode());

        //System.out.println(response.body().asString());// bu code tek line veriyor.

        System.out.println(response.body().prettyPrint());// bu tek line vermiyor alta dogru siraliyor

    }

    /*
    When user GET request to /api/spartans end point
    Thwn status code must be 200
    And body should contains Allen
     */
    @Test
    public void viewSpartanTest2() {
        Response response = RestAssured.get(spartanBaseUrl); // + sonrasini cikarinca 200 oluyor ve test geciyor. Bu sekilde sonuc 400 ve test kaldi.

        //verify status code 200
        Assert.assertEquals(response.statusCode(), 200);

        //verify body contains Allen
       Assert.assertTrue(response.body().asString().contains("Allen")); // bu hat verdi test failed


     /*
     Given accept type is Json
    When user sends a GET request to spartanAllURL
    Thwn status code IS 200
    And response body should be json format
     */

      //8.video



        }


    }


