package ch.ergonomics.tests;

import ch.ergonomics.Fluent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Examples5 {
    @Test
    void example1() {
        Assertions.assertEquals(
            12,
            Fluent
                .stack(1, "ab")
                .over()
                .over()
                .over()
                .over()
                .over()
                .over()
                .map(String::length)
                .map(Integer::sum)
                .swap()
                .map(String::length)
                .map(Integer::sum)
                .map(Integer::sum)
                .swap()
                .map(String::length)
                .map(Integer::sum)
                .map(Integer::sum)
                .swap()
                .map(String::length)
                .map(Integer::sum)
                .map(Integer::sum)
                .tos()
        );
    }
}
