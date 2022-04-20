
import entity.Car;
import entity.Park;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ParkingTest extends BDDMockito {

    //given 停车场有空闲 when 停车 then 停车成功
    @Test
    void should_parking_success_when_parking_lot_free() {
        // given
        Park park = Park.builder().total(100).used(99).build();
        // when
        boolean result = park.doParking(1);
        // then
        assertThat(result).isEqualTo(true);

    }

    //given 停车场没有车位 when 停车 then 停车失败
    @Test
    void should_parking_fail_when_parking_lot_full() {
        // given
        Park park = Park.builder().total(100).used(100).build();
        // when
        boolean result = park.doParking(1);
        // then
        assertThat(result).isEqualTo(false);
    }


    //given 停车场满了 when 取车 then 取车成功
    @Test
    void should_taking_fail_when_parking_lot_full() {
        // given
        Park park = Park.builder().total(100).used(100).build();
        // when
        boolean result = park.doTaking(1);
        // then
        assertThat(result).isEqualTo(false);
    }

    //given 车牌号是停过的 when 取车 then 取车成功
    @Test
    void should_taking_success_when_car_is_parked() {
        // given
        Car car = Car.builder().carID("A018").build();
        Park park = Park.builder().carList(Arrays.asList(car)).build();
        // when
        boolean result = park.doTaking(car);
        //then
        assertThat(result).isEqualTo(true);

    }

    //given 停车场没有停自己的车 when 取车 then 取车失败
    @Test
    void should_taking_fail_when_park_no_own_car() {
        // given
        Park park = Park.builder().build();
        Car car = Car.builder().carID("A018").build();
        // when
        boolean result = park.doTaking(car);
        //then
        assertThat(result).isEqualTo(false);
    }

    //given 停车场剩余车位少于要停的车 when 批量停车 then 部分成功部分失败
    @Test
    void should_some_taking_fail_other_taking_success_when_park_remain_less_than_car() {
        // given
        Park park = Park.builder().build();
        List<Car> list = Arrays.asList(Car.builder().carID("A001").build(), Car.builder().carID("A002").build());
        // when
        park.doParking(list);
        //then
        assertThat(list.stream().filter(s -> s.getStatus() == true).collect(Collectors.toList()).size() > 0).isEqualTo(true);
        assertThat(list.stream().filter(s -> s.getStatus() == false).collect(Collectors.toList()).size() > 0).isEqualTo(true);
    }


    //given 大卡车 车宽大于车场宽度  when 停大卡车 then 停车失败
    @Test
    void should_taking_fail_when_big_car_weight_greater_than_park_weight() {
        // given
        Park park = Park.builder().weight(5).build();
        Car car = Car.builder().weight(10).build();
        // when
        boolean result = park.doParking(car);
        //then
        assertThat(result).isEqualTo(false);

    }
}
