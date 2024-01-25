package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size;
    private int modCount;
    private Node<T> head;

    public void add(T value) {
        modCount++;
        if (head == null) {
            head = new Node<>(value, null);
        } else {
            Node<T> currElement = head;
            while (currElement.next != null) {
                currElement = currElement.next;
            }
            currElement.next = new Node<>(value, null);
        }
        size++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> currElement = head;
        for (int i = 0; i < size; i++, currElement = currElement.next) {
            if (i == index) {
                return currElement.item;
            }
        }
        return null;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        size--;
        Node<T> oldHead = head;
        head = head.next;
        oldHead.next = null;
        return oldHead.item;
    }

    public void addFirst(T value) {
        if (head == null) {
            head = new Node<>(value, null);
            return;
        }
        Node<T> element = head;
        head = new Node<>(value, null);
        head.next = element;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private final int expectedModCount = modCount;
            private Node<T> nodeHead = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return nodeHead != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<T> ans = nodeHead;
                nodeHead = nodeHead.next;
                return ans.item;
            }
        };
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}