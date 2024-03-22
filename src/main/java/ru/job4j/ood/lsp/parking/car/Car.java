package ru.job4j.ood.lsp.parking.car;

import ru.job4j.ood.lsp.parking.place.Place;

public abstract class Car {
    protected final int size;

    protected final String number;

    abstract public boolean validPlace(Place place);

    public Car(int size, String number) {
        this.size = size;
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public String getNumber() {
        return number;
    }
}
