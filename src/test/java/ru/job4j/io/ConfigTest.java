package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigTest {
    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairWithCommentAndEmptyLines() {
        String path = "./data/pair_with_comment_and_emptyLines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.getMap().size()).isEqualTo(6);
        assertThat(config.value("ключ")).isEqualTo("значение=1");
    }

    @Test
    void whenPairWithErrors1() {
        String path = "./data/pair_with_errors1.properties";
        Config config = new Config(path);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, config::load);
        assertThat(exception.getMessage()).isEqualTo("Error");
    }

    @Test
    void whenPairWithErrors2() {
        String path = "./data/pair_with_errors2.properties";
        Config config = new Config(path);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, config::load);
        assertThat(exception.getMessage()).isEqualTo("Error");
    }

    @Test
    void whenPairWithErrors3() {
        String path = "./data/pair_with_errors3.properties";
        Config config = new Config(path);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, config::load);
        assertThat(exception.getMessage()).isEqualTo("Error");
    }

    @Test
    void whenPairWithErrors4() {
        String path = "./data/pair_with_errors4.properties";
        Config config = new Config(path);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, config::load);
        assertThat(exception.getMessage()).isEqualTo("Error");
    }
}