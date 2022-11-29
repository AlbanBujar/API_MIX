package baseURLDeposu;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;

public class JsonPlaceHolderBaseURL{

    protected RequestSpecification specJsonPlace;

    @Before
    public void setUp() {

        specJsonPlace = new RequestSpecBuilder().
                setBaseUri("https://jsonplaceholder.typicode.com/posts").
                build();

    }
}
