package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size;
    private int modCount;
    private Node<T> head;
    private Node<T> last;

    public void add(T value) {
        Node<T> newNode = new Node<>(value, null);
        if (head == null) {
            head = newNode;
        } else {
            last.next = newNode;
        }
        last = newNode;
        modCount++;
        size++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> currElement = head;
        for (int i = 0; i < index; i++) {
            currElement = currElement.next;
        }
        return currElement.item;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T elem = head.item;
        head.item = null;
        if (head.next != null) {
            Node<T> temp = head.next;
            head.next = null;
            head = temp;
        }
        size--;
        modCount++;
        return elem;
    }

    public void addFirst(T value) {
        if (head == null) {
            head = new Node<>(value, null);
            return;
        }
        Node<T> element = head;
        head = new Node<>(value, null);
        head.next = element;
        modCount++;
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