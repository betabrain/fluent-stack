package ch.ergonomics.tests;

import ch.ergonomics.Fluent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.Random;

public class Examples6 {
    @Test
    void example1() {
        final Random random = new SecureRandom();
        Assertions.assertTrue(
            Fluent.stack()
                .push(random::nextDouble)
                .push(random::nextFloat)
                .push(random::nextInt)
                .map((a, b, c) -> a * b * c)
                .tos() > 0.0
        );
    }
}
