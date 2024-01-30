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
        int index = indexFor(Objects.hashCode(key));
        MapEntry<K, V> entry = table[index];
        V ans = null;
        if (entry != null && equalsHashCode(entry.key, key)
                && Objects.equals(entry.key, key)) {
            ans = entry.value;
        }
        return ans;
    }

    @Override
    public boolean remove(K key) {
        int index = indexFor(Objects.hashCode(key));
        MapEntry<K, V> entry = table[index];
        boolean ans = false;
        if (entry != null && equalsHashCode(entry.key, key)
                && Objects.equals(entry.key, key)) {
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

            private boolean isIndexValid() {
                return index < table.length;
            }

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (isIndexValid() && table[index] == null) {
                    index++;
                }
                return isIndexValid();
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

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private boolean addingEntry(MapEntry<K, V>[] array, K key, V value) {
        int index = indexFor(Objects.hashCode(key));
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

    private boolean equalsHashCode(K key1, K key2) {
        int hashCode1 = (key1 == null ? 0 : key1.hashCode());
        int hashCode2 = (key2 == null ? 0 : key2.hashCode());
        return hashCode1 == hashCode2;
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