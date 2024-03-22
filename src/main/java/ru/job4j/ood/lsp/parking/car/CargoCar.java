package ru.job4j.ood.lsp.parking.car;

import ru.job4j.ood.lsp.parking.place.Place;

public class CargoCar extends Car {
    @Override
    public boolean validPlace(Place place) {
        return true;
    }

    public CargoCar(int size, String number) {
        super(size, number);
    }
}
