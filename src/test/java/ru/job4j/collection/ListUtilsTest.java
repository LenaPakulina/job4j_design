package ru.job4j.collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIf() {
        input.addAll(List.of(2, 4, 6, 8, 1, 0));
        ListUtils.removeIf(input, (value) -> value > 5);
        assertThat(input).hasSize(6).containsSequence(1, 3, 2, 4, 1, 0);
    }

    @Test
    void whenReplaceIf() {
        input.addAll(List.of(4, 6, 0));
        ListUtils.replaceIf(input, (value) -> value > 5, -1);
        assertThat(input).hasSize(5).containsSequence(1, 3, 4, -1, 0);
    }

    @Test
    void whenRemoveAll() {
        input.addAll(List.of(2, 4, 6, 8, 1, 0));
        ListUtils.removeAll(input, List.of(1, 4, 6));
        assertThat(input).hasSize(4).containsSequence(3, 2, 8, 0);
    }
}