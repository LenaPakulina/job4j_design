package ru.job4j.question;

import java.util.*;
import java.util.stream.Collectors;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int mapSize = Math.min(previous.size() + current.size(), 8);
        HashMap<Integer, String> map = new HashMap<>(mapSize);
        map.putAll(previous
                .stream()
                .collect(Collectors
                        .toMap(User::getId, User::getName)));
        for (User user : current) {
            if (map.containsKey(user.getId())) {
                if (!map.get(user.getId()).equals(user.getName())) {
                    changed++;
                }
                map.remove(user.getId());
            } else {
                added++;
            }
        }
        return new Info(added, changed, map.size());
    }
}
