package ru.job4j.ood.lsp;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.food.Food;
import ru.job4j.ood.lsp.food.Milk;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class TrashTest {
    @Test
    public void checkByDate() {
        AbstractStore shop = new Trash();
        Food product = new Milk("White", LocalDate.now().minusDays(1), LocalDate.now().minusDays(2), 80.0, 0.2);
        assertTrue(shop.supports(product));
    }
}