package ru.job4j.serialization.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Store {
    private final boolean isOpen;
    private final int employeesCount;
    private final String name;
    private final StoreManager storeManager;
    private final String[] products;

    public Store(boolean isOpen, int employeesCount, String name, StoreManager storeManager, String[] products) {
        this.isOpen = isOpen;
        this.employeesCount = employeesCount;
        this.name = name;
        this.storeManager = storeManager;
        this.products = products;
    }

    @Override
    public String toString() {
        return "Store{"
                + "isOpen=" + isOpen
                + ", employeesCount=" + employeesCount
                + ", name='" + name + '\''
                + ", storeManager=" + storeManager
                + ", products=" + Arrays.toString(products)
                + '}';
    }

    public static void main(String[] args) {
        final Store store = new Store(true, 10, "Roga",
                new StoreManager("Ga", 5),
                new String[] {"Milk", "Bread"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(store));

        final String storeStr =
                "{"
                        + "\"isOpen\":false,"
                        + "\"employeesCount\":35,"
                        + "\"name\":\"Roga\","
                        + "\"storeManager\":"
                        + "{"
                        + "\"name\":\"Ga\","
                        + "\"experience\":15"
                        + "},"
                        + "\"products\":"
                        + "[\"Student\",\"Free\"]"
                        + "}";
        final Store storeMod = gson.fromJson(storeStr, Store.class);
        System.out.println(storeStr);
    }
}


