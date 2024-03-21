package ru.job4j.ood.lsp;

import ru.job4j.ood.lsp.food.Food;

import java.util.List;

public interface Store {
    public void addProduct(Food product);

    public void clearStore();

    public boolean supports(Food product);

    public List<Food> getProducts();
}
