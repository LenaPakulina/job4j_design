package ru.job4j.generics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RoleStoreTest {
    @Test
    void whenAddAndFindThenUsernameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mafia"));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("Mafia");
    }

    @Test
    void whenAddAndFindThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mafia"));
        Role result = store.findById("2");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindUsernameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mafia"));
        store.add(new Role("1", "Sheriff"));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("Mafia");
    }

    @Test
    void whenReplaceThenUsernameIsMaxim() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mafia"));
        store.replace("1", new Role("1", "Sheriff"));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("Sheriff");
    }

    @Test
    void whenNoReplaceUserThenNoChangeUsername() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mafia"));
        store.replace("10", new Role("10", "Sheriff"));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("Mafia");
    }

    @Test
    void whenDeleteUserThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mafia"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteUserThenUsernameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mafia"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("Mafia");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mafia"));
        boolean result = store.replace("1", new Role("1", "Maxim"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Mafia"));
        boolean result = store.replace("10", new Role("10", "Sheriff"));
        assertThat(result).isFalse();
    }
}
