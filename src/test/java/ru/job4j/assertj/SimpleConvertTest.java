package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkConvertToArray() {
        SimpleConvert smplConvert = new SimpleConvert();
        String[] array = smplConvert.toArray("5", "1", "2", "3");
        assertThat(array)
                .hasSize(4)
                .contains("2")
                .doesNotContain("7")
                .startsWith("5")
                .containsSequence("1", "2")
                .doesNotContainSequence("1", "5");
    }

    @Test
    void checkConvertToList() {
        SimpleConvert smpl = new SimpleConvert();
        List<String> ans = smpl.toList("5", "1", "2", "3");
        assertThat(ans)
                .isNotEmpty()
                .allSatisfy(e -> {
                    assertThat(e).isGreaterThan("0");
                });
    }

    @Test
    void checkConvertToSet() {
        SimpleConvert smpl = new SimpleConvert();
        Set<String> ans = smpl.toSet("2", "5", "1", "2", "3");
        assertThat(ans)
                .hasSize(4)
                .element(0).isNotNull();
    }

    @Test
    void checkConvertToMap() {
        SimpleConvert smpl = new SimpleConvert();
        Map<String, Integer> ans = smpl.toMap("2", "5", "1", "2", "3", "1");
        assertThat(ans)
                .hasSize(4)
                .containsKeys("2", "1")
                .containsEntry("1", 2);
    }
}