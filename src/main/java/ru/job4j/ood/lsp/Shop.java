package ru.job4j.ood.lsp;

import ru.job4j.ood.lsp.food.Food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Shop extends AbstractStore {
    @Override
    public boolean supports(Food product) {
        long allDaysLife = ChronoUnit.DAYS.between(product.getCreateDate(), product.getExpiryDate());
        long daysSpent = ChronoUnit.DAYS.between(product.getCreateDate(), LocalDate.now());
        double shelfLife = (double) daysSpent / allDaysLife;
        if (shelfLife >= 0.25 && shelfLife <= 0.75) {
            return true;
        }
        if (shelfLife > 0.75 && shelfLife < 1.0) {
            double newPrice = product.getPrice() * (1 - product.getDiscount() * 0.2);
            product.setPrice(newPrice);
            return true;
        }
        return false;
    }
}
