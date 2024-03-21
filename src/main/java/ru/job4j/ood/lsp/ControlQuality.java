package ru.job4j.ood.lsp;

import ru.job4j.ood.lsp.food.Food;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void addFood(Food food) {
        for (Store store : stores) {
            if (store.supports(food)) {
                store.addProduct(food);
                break;
            }
        }
    }

    public void updateStores() {
        List<Food> allFoods = new ArrayList<>();
        for (Store store : stores) {
            allFoods.addAll(store.getProducts());
            store.clearStore();
        }
        for (Food food : allFoods) {
            addFood(food);
        }
    }
}
