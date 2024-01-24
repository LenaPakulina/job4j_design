package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        if (nodes.isEmpty()) {
            return;
        }
        Iterator<ArrayList<Integer>> iterator = nodes.iterator();
        while (source.hasNext()) {
            if (!iterator.hasNext()) {
                iterator = nodes.iterator();
            }
            iterator.next().add(source.next());
        }
    }
}