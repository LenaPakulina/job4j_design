package ru.job4j.ood.lsp;

import ru.job4j.ood.lsp.food.Food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Warehouse extends AbstractStore {
    @Override
    public boolean supports(Food product) {
        long allDaysLife = ChronoUnit.DAYS.between(product.getCreateDate(), product.getExpiryDate());
        long daysSpent = ChronoUnit.DAYS.between(product.getCreateDate(), LocalDate.now());
        return daysSpent < 0.25 * allDaysLife;
    }
}
