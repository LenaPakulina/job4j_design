package ru.job4j.serialization.common;

import java.io.Serializable;

public class StoreManager {
    private final String name;
    private final int experience;

    public StoreManager(String name, int experience) {
        this.name = name;
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "StoreManager{"
                + "name='" + name + '\''
                + ", experience=" + experience
                + '}';
    }
}
