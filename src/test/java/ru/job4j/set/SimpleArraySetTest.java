package ru.job4j.set;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SimpleArraySetTest {

    @Test
    void whenAddNonNull() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
    }

    @Test
    void whenAddSomeElementsNonNull() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        assertThat(set.contains(1)).isFalse();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
        assertThat(set.contains(2)).isFalse();
        assertThat(set.add(2)).isTrue();
        assertThat(set.contains(2)).isTrue();
        assertThat(set.add(2)).isFalse();
        assertThat(set.contains(3)).isFalse();
        assertThat(set.add(3)).isTrue();
        assertThat(set.contains(3)).isTrue();
        assertThat(set.add(3)).isFalse();
    }

    @Test
    void whenAddNull() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        assertThat(set.add(null)).isTrue();
        assertThat(set.contains(null)).isTrue();
        assertThat(set.add(null)).isFalse();
    }

    @Test
    void whenOurSetIsEmpty() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        assertThat(set.contains(0)).isFalse();
        assertThat(set.contains(null)).isFalse();
    }

    @Test
    void whenAllIsTrue() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        assertThat(set.add(0)).isTrue();
        assertThat(set.add(null)).isTrue();
        assertThat(set.add(1)).isTrue();
        assertThat(set.add(2)).isTrue();
        assertThat(set.add(3)).isTrue();
        assertThat(set.contains(0)).isTrue();
    }

    @Test
    void checkIterator() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        assertThat(set.add(0)).isTrue();
        assertThat(set.add(1)).isTrue();
        Iterator<Integer> iterator = set.iterator();
        assertThat(iterator).isNotNull();
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(0);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.hasNext()).isFalse();
        assertThatThrownBy(iterator::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void checkCorrectAddWhenRepeatingElements() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        assertThat(set.add(0)).isTrue();
        assertThat(set.add(0)).isFalse();
        assertThat(set.add(0)).isFalse();
    }

    @Test
    void checkCorrectIteratorWhenRepeatingElements() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        set.add(0);
        set.add(0);
        set.add(0);
        Iterator<Integer> iterator = set.iterator();
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(0);
        assertThat(iterator.hasNext()).isFalse();
    }
}