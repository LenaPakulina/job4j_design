package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User user = (User) obj;
        return children == user.children
                && Objects.equals(name, user.name)
                && Objects.equals(birthday, user.birthday);
    }

    public static void main(String[] args) {
        Calendar date = Calendar.getInstance();
        User user1 = new User("Vika", 1, date);
        User user2 = new User("Vika", 1, date);
        Map<User, Object> map = new HashMap<>(16);
        map.put(user1, new Object());
        map.put(user2, new Object());
        System.out.println(map);
        int hashCode1 = user1.hashCode();
        int hashCode2 = user2.hashCode();
        int hash1 = hashCode1 & 15;
        int hash2 = hashCode2 & 15;
        System.out.println(hash1 + "    " + hash2);
    }
}
