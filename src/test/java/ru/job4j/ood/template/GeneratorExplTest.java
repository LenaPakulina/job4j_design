package ru.job4j.ood.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
class GeneratorExplTest {
    @Test
    void checkingInsertion() {
        Generator generator = new GeneratorExpl();
        String template = "I am a ${name}, Who are ${subject}?";
        String expected = "I am a Petr, Who are You?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr");
        args.put("subject", "You");
        assertThat(generator.produce(template, args)).isEqualTo(expected);
    }

    @Test
    void whenThereAreNoInsertsInLine() {
        Generator generator = new GeneratorExpl();
        String template = "I am a Petr, Who are You? ";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr");
        args.put("subject", "You");
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenRequiredKeyIsMissing() {
        Generator generator = new GeneratorExpl();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr");
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void checkMatchingStrings() {
        Generator generator = new GeneratorExpl();
        String template = "I am a Petr, Who are You?";
        String expected = "I am a Petr, Who are You?";
        Map<String, String> args = new HashMap<>();
        assertThat(generator.produce(template, args)).isEqualTo(expected);
    }
}