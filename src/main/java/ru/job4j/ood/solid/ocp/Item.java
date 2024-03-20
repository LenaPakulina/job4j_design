package ru.job4j.ood.solid.ocp;

/*
Класс содержит нарушение принципа OCP
3) Наличие методов getSoftItem и getHardItem - ошибка.
Можно получать обобщенный тип и дальше кастовать его, при необходимости
 */

public class Item {
    public class Element {
        public String getInfo() {
            return "Info";
        }
    }

    public class Soft extends Element {
    }

    public class Hard extends Element {
    }

    public Soft getSoftItem() {
        return new Soft();
    }

    public Hard getHardItem() {
        return new Hard();
    }
}
