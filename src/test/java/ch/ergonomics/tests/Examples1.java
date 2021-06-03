package ch.ergonomics.tests;

import ch.ergonomics.Fluent;
import org.junit.jupiter.api.Test;

public class Examples1 {
    @Test
    void example1() {
        Fluent
            .stack();
    }

    @Test
    void example2() {
        Fluent
            .stack()
            .push(1);
    }

    @Test
    void example3() {
        Fluent
            .stack()
            .push(1)
            .map(a -> a * 3.1415);
    }

    @Test
    void example4() {
        Fluent
            .stack()
            .push(1)
            .push("Hi")
            .map(String::length);
    }

    @Test
    void example5() {
        Fluent
            .stack()
            .push(1)
            .push("Hi")
            .map(String::length)
            .map(Integer::sum);
    }

    @Test
    void example6() {
        Fluent
            .stack(1);
    }

    @Test
    void example7() {
        Fluent
            .stack(1, 2);
    }

    @Test
    void example8() {
        Fluent
            .stack(1, 2, 3);
    }

    @Test
    void example9() {
        Fluent
            .stack(1, 2, 3, 4);
    }

    @Test
    void example10() {
        Fluent
            .stack(1, 2, 3, 4, 5);
    }

    @Test
    void example11() {
        Fluent
            .stack(1, 2, 3, 4, 5, 6);
    }

    @Test
    void example12() {
        Fluent
            .stack(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void example13() {
        Fluent
            .stack(1, 2, 3, 4, 5, 6, 7, 8);
    }

    @Test
    void example14() {
        Fluent
            .stack()
            .push(1)
            .push(2)
            .push(3)
            .push(4)
            .push(5)
            .push(6)
            .push(7)
            .push(8)
            .drop()
            .drop()
            .drop()
            .drop()
            .drop()
            .drop()
            .drop()
            .drop();
    }
}
