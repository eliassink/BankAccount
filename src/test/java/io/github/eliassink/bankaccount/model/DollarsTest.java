package io.github.eliassink.bankaccount.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DollarsTest {

    @Test
    void testEquals() {
        assertEquals(new Dollars(5), new Dollars(5));
    }

    @Test
    void testNotEquals() {
        assertNotEquals(new Dollars(5), new Dollars(6));
    }

    @Test
    void testAddition() {
        assertEquals(new Dollars(5), new Dollars(2).add(new Dollars(3)));
    }

    @Test
    void testSubtractionWithPositiveResult() {
        assertEquals(new Dollars(50), new Dollars(120).subtract(new Dollars(70)));
    }

    @Test
    void testSubtractionWithNegativeResult() {
        assertEquals(new Dollars(-20), new Dollars(50).subtract(new Dollars(70)));
    }

    @Test
    void testCompare() {
        assertTrue(new Dollars(100).compareTo(new Dollars(50)) > 0);
        assertTrue(new Dollars(175).compareTo(new Dollars(250)) < 0);
        assertEquals(0, new Dollars(10).compareTo(new Dollars(10)));
    }

    @Test
    void testToStringPositive() {
        assertEquals("$1.25", new Dollars(125).toString());
    }

    @Test
    void testToStringNegative() {
        assertEquals("-$1.25", new Dollars(-125).toString());
    }

}