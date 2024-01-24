package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator<Integer> {
    private final int[][] data;
    private int row;
    private int column;

    public MatrixIterator(int[][] data) {
        this.data = data;
    }

    private boolean isValidRowIndex(int index) {
        return data.length > index;
    }

    private boolean isValidColumnIndex(int index) {
        return data[row].length > index;
    }

    @Override
    public boolean hasNext() {
        while (!isValidColumnIndex(column)) {
            if (!isValidColumnIndex(column + 1) && isValidRowIndex(row + 1)) {
                row++;
                column = 0;
            } else {
                break;
            }
        }
        return isValidColumnIndex(column);
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}