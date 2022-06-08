package io.github.eliassink.bankaccount.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CentsTest {

    @Test
    void testEquals() {
        assertEquals(new Cents(5), new Cents(5));
    }

    @Test
    void testNotEquals() {
        assertNotEquals(new Cents(5), new Cents(6));
    }

    @Test
    void testAddition() {
        assertEquals(new Cents(5), new Cents(2).add(new Cents(3)));
    }

    @Test
    void testSubtractionWithPositiveResult() {
        assertEquals(new Cents(50), new Cents(120).subtract(new Cents(70)));
    }

    @Test
    void testSubtractionWithNegativeResult() {
        assertEquals(new Cents(-20), new Cents(50).subtract(new Cents(70)));
    }

    @Test
    void testCompare() {
        assertTrue(new Cents(100).compareTo(new Cents(50)) > 0);
        assertTrue(new Cents(175).compareTo(new Cents(250)) < 0);
        assertEquals(0, new Cents(10).compareTo(new Cents(10)));
    }

    @Test
    void testToStringPositive() {
        assertEquals("$1.25", new Cents(125).toString());
    }

    @Test
    void testToStringNegative() {
        assertEquals("-$1.00", new Cents(-100).toString());
    }

    @Test
    void testParseCents() {
        assertEquals(new Cents(125),Cents.parseCents("$1.25"));
        assertEquals(new Cents(-100),Cents.parseCents("-$1.00"));
    }

}