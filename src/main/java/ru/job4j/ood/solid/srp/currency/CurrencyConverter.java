package ru.job4j.ood.solid.srp.currency;

public interface CurrencyConverter {
    double convert(Currency source, double sourceValue, Currency target);
}
