package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();

    private int size = 0;

    private void swapStack(SimpleStack<T> first, SimpleStack<T> second) {
        for (int i = 0; i < size; i++) {
            first.push(second.pop());
        }
    }

    public T poll() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        swapStack(output, input);
        size--;
        T ans = output.pop();
        swapStack(input, output);
        return ans;
    }

    public void push(T value) {
        input.push(value);
        size++;
    }
}