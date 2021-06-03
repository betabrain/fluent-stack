package ch.ergonomics.tests;

import ch.ergonomics.Fluent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Examples2 {
    @Test
    void example1() {
        Assertions.assertThrows(Exception.class, () -> {
            Fluent
                .stack(1)
                .map( a -> a / 0)
                .map(Object::toString)
                .rethrow();
        });
    }

    @Test
    void example2() {
        Assertions.assertThrows(Exception.class, () -> {
            Fluent
                .stack(1, 2)
                .map( a -> a / 0)
                .map(Object::toString)
                .rethrow();
        });
    }

    @Test
    void example3() {
        Assertions.assertThrows(Exception.class, () -> {
            Fluent
                .stack(1, 2, 3)
                .map( a -> a / 0)
                .map(Object::toString)
                .rethrow();
        });
    }

    @Test
    void example4() {
        Assertions.assertThrows(Exception.class, () -> {
            Fluent
                .stack(1, 2, 3, 4)
                .map( a -> a / 0)
                .map(Object::toString)
                .rethrow();
        });
    }

    @Test
    void example5() {
        Assertions.assertThrows(Exception.class, () -> {
            Fluent
                .stack(1, 2, 3, 4, 5)
                .map( a -> a / 0)
                .map(Object::toString)
                .rethrow();
        });
    }

    @Test
    void example6() {
        Assertions.assertThrows(Exception.class, () -> {
            Fluent
                .stack(1, 2, 3, 4, 5, 6)
                .map( a -> a / 0)
                .map(Object::toString)
                .rethrow();
        });
    }

    @Test
    void example7() {
        Assertions.assertThrows(Exception.class, () -> {
            Fluent
                .stack(1, 2, 3, 4, 5, 6, 7)
                .map( a -> a / 0)
                .map(Object::toString)
                .rethrow();
        });
    }

    @Test
    void example8() {
        Assertions.assertThrows(Exception.class, () -> {
            Fluent
                .stack(1, 2, 3, 4, 5, 6, 7, 8)
                .map( a -> a / 0)
                .map(Object::toString)
                .rethrow();
        });
    }
}
