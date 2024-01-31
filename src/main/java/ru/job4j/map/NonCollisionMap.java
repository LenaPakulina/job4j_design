package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (capacity * LOAD_FACTOR <= count) {
            expand();
        }
        return addingEntry(table, key, value);
    }

    @Override
    public V get(K key) {
        MapEntry<K, V> entry = table[indexByKey(key)];
        V ans = null;
        if (keyCompare(entry, key)) {
            ans = entry.value;
        }
        return ans;
    }

    @Override
    public boolean remove(K key) {
        int index = indexByKey(key);
        MapEntry<K, V> entry = table[index];
        boolean ans = false;
        if (keyCompare(entry, key)) {
            ans = true;
            table[index] = null;
            modCount++;
            count--;
        }
        return ans;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private final int expectedModCount = modCount;
            private int index;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private int indexByKey(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private boolean addingEntry(MapEntry<K, V>[] array, K key, V value) {
        int index = indexByKey(key);
        boolean ans = (array[index] == null);
        if (ans) {
            array[index] = new MapEntry<>(key, value);
            modCount++;
            count++;
        }
        return ans;
    }

    private void expand() {
        capacity = capacity * 2;
        count = 0;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> entry : table) {
            if (entry != null) {
                addingEntry(newTable, entry.key, entry.value);
            }
        }
        table = newTable;
    }

    private boolean keyCompare(MapEntry<K, V> entry, K key) {
        boolean ans = false;
        if (entry != null) {
            int hash1 = hash(Objects.hashCode(entry.key));
            int hash2 = hash(Objects.hashCode(key));
            ans = (hash1 == hash2) && Objects.equals(entry.key, key);
        }
        return ans;
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        NonCollisionMap<Integer, Integer> map = new NonCollisionMap<>();
        if (map.hash(0) != 0) {
            throw new NoSuchElementException("Step " + 1);
        }
        if (map.hash(65535) != 65535) {
            throw new NoSuchElementException("Step " + 2);
        }
        if (map.hash(65536) != 65537) {
            throw new NoSuchElementException("Step " + 3);
        }
        if (map.indexFor(0) != 0) {
            throw new NoSuchElementException("Step " + 4);
        }
        if (map.indexFor(7) != 7) {
            throw new NoSuchElementException("Step " + 5);
        }
        if (map.indexFor(8) != 0) {
            throw new NoSuchElementException("Step " + 6);
        }
    }
}