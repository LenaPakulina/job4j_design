package ru.job4j.ood.lsp.parking.place;

import ru.job4j.ood.lsp.parking.car.Car;

public class Place {
    private Car car;
    private final String number;
    private final int size;

    public Place(String number, int size) {
        this.number = number;
        this.size = size;
    }

    public Car getCar() {
        return car;
    }

    public String getNumber() {
        return number;
    }

    public int getSize() {
        return size;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public boolean isFree() {
        return car == null;
    }
}
