package com.example.junit.assertj;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;
import static org.assertj.core.api.Assertions.entry;

public class SimpleAssertionsTest {

    @Test
    void a_few_simple_assertions() {
        assertThat("The Lord of the Rings").isNotNull()
                .startsWith("The")
                .contains("Lord")
                .endsWith("Rings");
    }

    public class Dog {
        private String name;
        private Float weight;

        public Dog(String name, Float weight) {
            this.name = name;
            this.weight = weight;
        }

        public String getName() {
            return name;
        }

        public Float getWeight() {
            return weight;
        }
    }

    Dog fido = new Dog("Fido", 5.25f);

    Dog fidosClone = new Dog("Fido", 5.25f);

    @Test
    void object_assertions() {
        assertThat(fido).isEqualTo(fidosClone);
        assertThat(fido).isEqualToComparingFieldByFieldRecursively(fidosClone);
    }

    @Test
    void boolean_assertions() {
        assertThat("".isEmpty()).isTrue();
    }

    @Test
    void iterable_array_assertions() {
        List<String> list = Arrays.asList("1", "2", "3");

        assertThat(list).contains("1");
        assertThat(list).isNotEmpty();
        assertThat(list).startsWith("1");
        assertThat(list)
                .isNotEmpty()
                .contains("1")
                .doesNotContainNull()
                .containsSequence("2", "3");
    }

    @Test
    void character_assertions() {
        assertThat('c')
                .isNotEqualTo('a')
                .inUnicode()
                .isGreaterThanOrEqualTo('b')
                .isLowerCase();
    }

    @Test
    void class_assertions() {
        assertThat(Runnable.class).isInterface();
        assertThat(Exception.class).isAssignableFrom(NoSuchElementException.class);
    }

    @Test
    void double_float_integer_assertions() {
        assertThat(5.1).isEqualTo(5, withPrecision(1d));
    }

    @Test
    void map_assertions() {
        Map<Integer, String> map = new HashMap<>();
        map.put(2, "a");
        assertThat(map)
                .isNotEmpty()
                .containsKey(2)
                .doesNotContainKeys(10)
                .contains(entry(2, "a"));
    }

    @Test
    void throwable_assertions() {
        assertThat(new Exception("c")).hasNoCause().hasMessageEndingWith("c");
    }

    @Test
    void describing_assertions() {
        Dog dog = new Dog("John", 10f);
        assertThat(dog.getWeight())
                .as("%s's age should be equal to 100", dog.getName())
                .isEqualTo(100);
    }
}
