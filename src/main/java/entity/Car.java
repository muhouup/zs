package entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Car {
    private String carID;
    private Boolean status;
    private int weight;

}
