package ru.job4j.ood.solid.srp.violation;

/*
Класс содержит нарушение принципа SRP
Условие для проверки можно было бы передать Предатором в конструкторе
 */

public class Finder {
    public boolean checkValid(int value) {
        boolean result = false;
        if (value > 5) {
            result = true;
        }
        return result;
    }
}
