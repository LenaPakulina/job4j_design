package ru.job4j.kiss;

import org.junit.jupiter.api.Test;
import ru.job4j.iterator.ArrayIt;

import static org.assertj.core.api.Assertions.*;

class FoolTest {
    @Test
    void checkFizzBuzz() {
        assertThat(new Fool().getStringValue(15)).isEqualTo("FizzBuzz");
    }

    @Test
    void checkNotFizzBuzz() {
        assertThat(new Fool().getStringValue(9)).isNotEqualTo("FizzBuzz");
    }

    @Test
    void checkFizz() {
        assertThat(new Fool().getStringValue(12)).isEqualTo("Fizz");
    }

    @Test
    void checkBuzz() {
        assertThat(new Fool().getStringValue(10)).isEqualTo("Buzz");
    }

    @Test
    void checkNotValidUserStep() {
        assertThat(new Fool().userStep(14, "FizzBuzz")).isEqualTo(1);
    }

    @Test
    void checkValidUserStep() {
        assertThat(new Fool().userStep(15, "FizzBuzz")).isEqualTo(16);
    }
}