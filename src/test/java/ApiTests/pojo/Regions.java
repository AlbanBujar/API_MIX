package ApiTests.pojo;


import com.fasterxml.jackson.annotation.*;


import java.util.*;


@JsonIgnoreProperties(ignoreUnknown = true)  //this is from jackson dependency
public class Regions {

    private List<Region> items;
    private int count;

    @Override
    public String toString() {
        return "Regions{" +
                "items=" + items +
                ", count=" + count +
                '}';
    }

    public List<Region> getItems() {
        return items;
    }

    public void setItems(List<Region> items) {
        this.items = items;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
