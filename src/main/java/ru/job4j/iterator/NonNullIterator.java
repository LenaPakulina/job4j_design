package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NonNullIterator implements Iterator<Integer> {

    private Integer[] data;
    private int index;

    public NonNullIterator(Integer[] data) {
        this.data = data;
    }

    private boolean isIndexValid() {
        return index < data.length;
    }

    @Override
    public boolean hasNext() {
        while (isIndexValid() && data[index] == null) {
            index++;
        }
        return isIndexValid();
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }

}