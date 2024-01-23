package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {

    @Test
    void checkWhenParseEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("empty")
                .message()
                .isNotEmpty();
    }

    @Test
    void checkGetMapCorrectly() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data")
                .message()
                .isNotEmpty();
    }

    @Test
    void checkValidateInvalidStr() {
        NameLoad nameLoad = new NameLoad();
        String[] array = {"key:value"};
        assertThatThrownBy(() -> nameLoad.parse(array))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(array[0])
                .message()
                .isNotEmpty();
    }

    @Test
    void checkValidateStringStartsWithMark() {
        NameLoad nameLoad = new NameLoad();
        String[] array = {"=keyvalue"};
        assertThatThrownBy(() -> nameLoad.parse(array))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(array[0])
                .message()
                .isNotEmpty();
    }

    @Test
    void checkValidateStringEndsWithMark() {
        NameLoad nameLoad = new NameLoad();
        String[] array = {"keyvalue="};
        assertThatThrownBy(() -> nameLoad.parse(array))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(array[0])
                .message()
                .isNotEmpty();
    }
}