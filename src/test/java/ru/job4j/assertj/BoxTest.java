package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void whatsThisWhenCreateSphere() {
        Box box = new Box(0, 10);
        String ans = box.whatsThis();
        assertThat(ans)
                .isNotNull()
                .isNotEmpty()
                .isEqualTo("Sphere");
    }

    @Test
    void whatsThisWhenCreateUnknownFig() {
        Box box = new Box(5, 10);
        String ans = box.whatsThis();
        assertThat(ans)
                .isNotNull()
                .isNotEmpty()
                .isEqualTo("Unknown object");
    }

    @Test
    void isExistWhenCreateTetrahedron() {
        Box box = new Box(4, 10);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void isExistWhenCreateUnknownFig() {
        Box box = new Box(10, -10);
        assertThat(box.isExist()).isFalse();
    }

    @Test
    void getAreaWhenCreateCube() {
        Box box = new Box(8, 10);
        double ans = box.getArea();
        assertThat(ans).isEqualTo(600, withPrecision(0.01D));
    }

    @Test
    void getAreaWhenCreateNegativeCube() {
        Box box = new Box(8, -10);
        double ans = box.getArea();
        assertThat(ans).isEqualTo(0, withPrecision(0.0001D));
    }

    @Test
    void getNumberOfVerticesWhenCreateCube() {
        Box box = new Box(8, 4);
        int ans = box.getNumberOfVertices();
        assertThat(ans)
                .isPositive()
                .isEqualTo(8);

    }

    @Test
    void getNumberOfVerticesWhenCreateNegativeCube() {
        Box box = new Box(8, -4);
        int ans = box.getNumberOfVertices();
        assertThat(ans)
                .isNegative()
                .isEqualTo(-1);
    }
}