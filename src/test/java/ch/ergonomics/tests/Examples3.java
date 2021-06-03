package ch.ergonomics.tests;

import ch.ergonomics.Fluent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Examples3 {
    @Test
    void example1() {
        Assertions.assertEquals(1, Fluent.stack(1).tos());
    }

    @Test
    void example2() {
        Assertions.assertEquals(2, Fluent.stack(1, 2).tos());
    }

    @Test
    void example3() {
        Assertions.assertEquals(3, Fluent.stack(1, 2, 3).tos());
    }

    @Test
    void example4() {
        Assertions.assertEquals(4, Fluent.stack(1, 2, 3, 4).tos());
    }

    @Test
    void example5() {
        Assertions.assertEquals(5, Fluent.stack(1, 2, 3, 4, 5).tos());
    }

    @Test
    void example6() {
        Assertions.assertEquals(6, Fluent.stack(1, 2, 3, 4, 5, 6).tos());
    }

    @Test
    void example7() {
        Assertions.assertEquals(7, Fluent.stack(1, 2, 3, 4, 5, 6, 7).tos());
    }

    @Test
    void example8() {
        Assertions.assertEquals(8, Fluent.stack(1, 2, 3, 4, 5, 6, 7, 8).tos());
    }
}
