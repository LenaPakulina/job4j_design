package ru.job4j.ood.lsp;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.food.Bread;
import ru.job4j.ood.lsp.food.Food;
import ru.job4j.ood.lsp.food.Milk;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ControlQualityTest {
    @Test
    public void testUpdateWarehouse() {
        AbstractStore warehouse = new Warehouse();
        AbstractStore shop = new Shop();
        AbstractStore trash = new Trash();
        List<Store> stores = List.of(warehouse, shop, trash);
        ControlQuality controlQuality = new ControlQuality(stores);
        LocalDate currentDate = LocalDate.now();
        LocalDate expiryDate = currentDate.plusDays(10);
        Food product1 = new Food("Test1", expiryDate, currentDate, 100, 0);
        controlQuality.addFood(product1);
        controlQuality.updateStores();
        assertEquals(1, warehouse.getProducts().size());
        assertEquals(0, shop.getProducts().size());
        assertEquals(0, trash.getProducts().size());
    }

    @Test
    public void testUpdateShop() {
        AbstractStore warehouse = new Warehouse();
        AbstractStore shop = new Shop();
        AbstractStore trash = new Trash();
        List<Store> stores = List.of(warehouse, shop, trash);
        ControlQuality controlQuality = new ControlQuality(stores);
        LocalDate expiryDate = LocalDate.now().plusDays(10);
        LocalDate createDate = LocalDate.now().minusDays(10);
        Food product1 = new Food("Test1", expiryDate, createDate, 100, 0);
        controlQuality.addFood(product1);
        controlQuality.updateStores();
        assertEquals(0, warehouse.getProducts().size());
        assertEquals(1, shop.getProducts().size());
        assertEquals(0, trash.getProducts().size());
    }

    @Test
    public void testUpdateTrash() {
        AbstractStore warehouse = new Warehouse();
        AbstractStore shop = new Shop();
        AbstractStore trash = new Trash();
        List<Store> stores = List.of(warehouse, shop, trash);
        ControlQuality controlQuality = new ControlQuality(stores);
        LocalDate expiryDate = LocalDate.now().minusDays(1);
        LocalDate createDate = LocalDate.now().minusDays(10);
        Food product1 = new Food("Test1", expiryDate, createDate, 100, 0);
        controlQuality.addFood(product1);
        controlQuality.updateStores();
        assertEquals(0, warehouse.getProducts().size());
        assertEquals(0, shop.getProducts().size());
        assertEquals(1, trash.getProducts().size());
    }
}