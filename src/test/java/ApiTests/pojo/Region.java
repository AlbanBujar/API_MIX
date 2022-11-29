package ApiTests.pojo;



import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import java.util.*;

@Getter
@Setter
@ToString
public class Region {
    //region_id
    //if your jsonkey and variable name not matching, you can map it with jsonProperty
    @JsonProperty("region_id")
    private int region_id; // rId bu boyle olursa yukaridakini yazarak hatayi duzeltebiliriz
    @JsonProperty("region_name")
    private String region_name;
    @JsonProperty("links")
    private List<Link> links;


}
