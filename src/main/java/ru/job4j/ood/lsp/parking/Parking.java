package ru.job4j.ood.lsp.parking;

import ru.job4j.ood.lsp.parking.car.Car;
import ru.job4j.ood.lsp.parking.place.Place;

import java.util.*;

public class Parking {
    private final List<Place> truckPlaces;
    private final List<Place> autoPlaces;
    private final List<Car> cars = new ArrayList<>();

    public Parking(int cargoSize, int passengerSize, int cargoPlaceSize) {
        autoPlaces = new ArrayList<>(passengerSize);
        truckPlaces = new ArrayList<>(cargoSize);
        Random rand = new Random();
        for (int i = 0; i < passengerSize; i++) {
            autoPlaces.add(new Place(String.valueOf(i), 1));
        }
        for (int i = 0; i < cargoSize; i++) {
            truckPlaces.add(new Place(String.valueOf(i),
                    cargoPlaceSize == 0 ? rand.nextInt(2, 10) : cargoPlaceSize));
        }
    }

    public HashMap<Integer, Integer> getAutoPlacesSum() {
        HashMap<Integer, Integer> result = new LinkedHashMap<>();
        for (int i = 0; i < autoPlaces.size(); i++) {
            int sum = 0;
            while (i < autoPlaces.size()
                    && autoPlaces.get(i).isFree()
                    && autoPlaces.get(i).getSize() == 1) {
                sum++;
                i++;
            }
            result.put(i - sum, sum);
        }
        return result;
    }

    public boolean addCar(Car car) {
        boolean result = false;
        for (Place place : truckPlaces) {
            if (car.validPlace(place) && place.isFree() && car.getSize() <= place.getSize()) {
                cars.add(car);
                place.setCar(car);
                result = true;
                break;
            }
        }
        if (!result) {
            HashMap<Integer, Integer> autoPlacesSum = getAutoPlacesSum();
            for (Map.Entry<Integer, Integer> autoSum : autoPlacesSum.entrySet()) {
                if (autoSum.getValue() >= car.getSize()) {
                    for (int i = autoSum.getKey(); i < autoSum.getKey() + car.getSize(); i++) {
                        cars.add(car);
                        autoPlaces.get(i).setCar(car);
                    }
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public void removeCar(Car car) {
        if (cars.contains(car)) {
            List<Place> allPlaces = getAllPlaces();
            for (Place place : allPlaces) {
                if (car.equals(place.getCar())) {
                    place.setCar(null);
                }
            }
            cars.remove(car);
        }
    }

    private List<Place> getAllPlaces() {
        List<Place> allPlaces = new ArrayList<>();
        allPlaces.addAll(autoPlaces);
        allPlaces.addAll(truckPlaces);
        return allPlaces;
    }
}
