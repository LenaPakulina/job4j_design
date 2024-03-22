package ru.job4j.ood.lsp.parking.car;

import ru.job4j.ood.lsp.parking.place.Place;

public class PassangerCar extends Car {
    @Override
    public boolean validPlace(Place place) {
        return place.getSize() == 1;
    }

    public PassangerCar(String number) {
        super(1, number);
    }
}
