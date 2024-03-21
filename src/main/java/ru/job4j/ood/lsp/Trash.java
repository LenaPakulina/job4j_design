package ru.job4j.ood.lsp;

import ru.job4j.ood.lsp.food.Food;

import java.time.LocalDate;

public class Trash extends AbstractStore {
    @Override
    public boolean supports(Food product) {
        return LocalDate.now().isAfter(product.getExpiryDate());
    }
}
