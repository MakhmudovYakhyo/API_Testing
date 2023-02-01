package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Region {

    @JsonProperty("region_id")
    private int region_id;
    @JsonProperty("region_name")
    private String region_name;
    private List<Link> links;

}
