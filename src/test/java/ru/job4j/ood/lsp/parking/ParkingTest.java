package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.parking.car.Car;
import ru.job4j.ood.lsp.parking.car.CargoCar;
import ru.job4j.ood.lsp.parking.car.PassangerCar;

import java.util.Random;

import static org.assertj.core.api.Assertions.*;

class ParkingTest {
    @Test
    public void testAutoAdd() {
        Parking parking = new Parking(5, 5, 7);
        Car auto1 = new PassangerCar("car");
        Car auto2 = new PassangerCar("car");
        Car auto3 = new PassangerCar("car");
        Car auto4 = new PassangerCar("car");
        Car auto5 = new PassangerCar("car");
        Car auto6 = new PassangerCar("car");
        assertThat(parking.addCar(auto1)).isTrue();
        assertThat(parking.addCar(auto2)).isTrue();
        assertThat(parking.addCar(auto3)).isTrue();
        assertThat(parking.addCar(auto4)).isTrue();
        assertThat(parking.addCar(auto5)).isTrue();
        assertThat(parking.addCar(auto6)).isFalse();
        parking.removeCar(auto1);
        assertThat(parking.addCar(auto6)).isTrue();
    }

    @Test
    public void testTruckAdd() {
        Parking parking = new Parking(2, 10, 10);
        Car truck1 = new CargoCar(5, "car");
        Car truck2 = new CargoCar(5, "car");
        Car truck3 = new CargoCar(7, "car");
        Car truck4 = new CargoCar(2, "car");
        Car truck5 = new CargoCar(5, "car");
        assertThat(parking.addCar(truck1)).isTrue();
        assertThat(parking.addCar(truck2)).isTrue();
        assertThat(parking.addCar(truck3)).isTrue();
        assertThat(parking.addCar(truck4)).isTrue();
        assertThat(parking.addCar(truck5)).isFalse();
    }

    @Test
    public void testComboAdd() {
        Parking parking = new Parking(5, 13, 10);
        Car truck0 = new CargoCar(11, "car");
        Car truck1 = new CargoCar(5, "car");
        Car truck2 = new CargoCar(5, "car");
        Car truck3 = new CargoCar(7, "car");
        Car truck4 = new CargoCar(2, "car");
        Car truck5 = new CargoCar(5, "car");
        Car auto1 = new PassangerCar("car");
        Car auto2 = new PassangerCar("car");
        Car auto3 = new PassangerCar("car");

        assertThat(parking.addCar(truck0)).isTrue();
        assertThat(parking.addCar(truck1)).isTrue();
        assertThat(parking.addCar(truck2)).isTrue();
        assertThat(parking.addCar(truck3)).isTrue();
        assertThat(parking.addCar(truck4)).isTrue();
        assertThat(parking.addCar(truck5)).isTrue();
        assertThat(parking.addCar(auto1)).isTrue();
        assertThat(parking.addCar(auto2)).isTrue();
        assertThat(parking.addCar(auto3)).isFalse();
        parking.removeCar(truck1);
        assertThat(parking.addCar(auto3)).isFalse();
        parking.removeCar(truck0);
        assertThat(parking.addCar(auto3)).isTrue();
    }
}