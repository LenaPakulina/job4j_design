package ru.job4j.ood.lsp;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.food.Bread;
import ru.job4j.ood.lsp.food.Food;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ShopTest {
    @Test
    public void checkDiscount() {
        AbstractStore shop = new Shop();
        Food product = new Bread("White", LocalDate.now().plusDays(10), LocalDate.now().plusDays(8), 80.0, 0.5);
        assertTrue(shop.supports(product));
        assertEquals(72.0, product.getPrice(), 0.01);
    }

    @Test
    public void checkByDate() {
        AbstractStore shop = new Shop();
        Food product = new Bread("White", LocalDate.now().plusDays(10), LocalDate.now(), 80.0, 0.2);
        assertTrue(shop.supports(product));
        assertEquals(product.getPrice(), product.getPrice(), 0.01);
    }
}