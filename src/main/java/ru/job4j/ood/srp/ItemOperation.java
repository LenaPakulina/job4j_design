package ru.job4j.ood.srp;

/*
Интерфейс содержит нарушение принципа SRP
Т.к. имеет функционал для работы с БД а также работу по анализу данных
 */

import java.util.List;

public interface ItemOperation {

    public class Item {
    }

    public void calculateItemParams(List<Item> items);

    public void analysisByParams(List<Item> items);

    public Item findById(long id);

    public Item addItem(long id);
}
