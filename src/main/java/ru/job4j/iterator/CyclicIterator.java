package ru.job4j.iterator;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CyclicIterator<T> implements Iterator<T> {
    private List<T> data;
    private Iterator<T> iterator = Collections.emptyIterator();

    public CyclicIterator(List<T> data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (!iterator.hasNext()) {
            iterator = data.iterator();
        }
        return iterator.hasNext();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return iterator.next();
    }
}