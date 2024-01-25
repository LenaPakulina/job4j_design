package ru.job4j.generics;

public class Animal {
    private String name;
    private int type;

    public Animal(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public Animal() {
        this.name = "default";
        this.type = 0;
    }
}
