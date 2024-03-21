package ru.job4j.ood.lsp;

import ru.job4j.ood.lsp.food.Food;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {
    protected List<Food> products = new ArrayList<>();

    @Override
    public void addProduct(Food product) {
        products.add(product);
    }

    public void clearStore() {
        products.clear();
    }

    public List<Food> getProducts() {
        return products;
    }
}
