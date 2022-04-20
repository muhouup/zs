package entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class Park {
    private int total;
    private int used;
    private List<Car> carList;
    private int weight;

    public boolean doParking(int carNum) {
        if (total >= used + carNum) {
            used += carNum;
            return true;
        }
        return false;
    }


    public boolean doParking(Car car) {
        if (weight > car.getWeight() && total >= used++) {
            car.setStatus(true);
            return true;
        }
        return carList.stream().filter(s -> s.getCarID() == car.getCarID()).collect(Collectors.toList()).size() > 0;
    }

    public List<Car> doParking(List<Car> list) {
        if (total == used) {
            return list;
        }
        for (int i = 0; i < total - used - 1; i++) {
            Car car = list.get(i);
            car.setStatus(true);
            list.add(i, car);
        }
        return list;
    }

    public boolean doTaking(int carNum) {
        used -= carNum;
        return true;
    }

    public boolean doTaking(Car car) {
        for (Car car1 : carList) {
            if (car1.getCarID().equals(car.getCarID())) {
                carList.remove(car1);
                used++;
                return true;
            }
        }
        return false;
    }
}
