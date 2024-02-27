package ru.job4j.serialization.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.serialization.xml.Contact;
import ru.job4j.serialization.xml.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Store {
    private boolean isOpen;
    private int employeesCount;
    private String name;
    private StoreManager storeManager;
    private String[] products;

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

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public int getEmployeesCount() {
        return employeesCount;
    }

    public void setEmployeesCount(int employeesCount) {
        this.employeesCount = employeesCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StoreManager getStoreManager() {
        return storeManager;
    }

    public void setStoreManager(StoreManager storeManager) {
        this.storeManager = storeManager;
    }

    public String[] getProducts() {
        return products;
    }

    public void setProducts(String[] products) {
        this.products = products;
    }

    public static void func(String[] args) {
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

    public static void main(String[] args) {
        JSONObject jsonStoreManager = new JSONObject("{\"name\":\"Polly\"}");

        List<String> list = new ArrayList<>();
        list.add("Milk");
        list.add("Bread");
        JSONArray jsonProducts = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Store store = new Store(false, 30, "Billy",
                new StoreManager("Ien", 3), new String[] {"Milk", "Bread"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isOpen", store.isOpen());
        jsonObject.put("employeesCount", store.getEmployeesCount());
        jsonObject.put("name", store.getName());
        jsonObject.put("storeManager", jsonStoreManager);
        jsonObject.put("products", jsonProducts);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(store).toString());
    }
}


