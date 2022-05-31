package io.github.eliassink.bankaccount.model;

import java.util.Objects;

public final class Dollars implements Comparable<Dollars> {
    private final long cents;

    public Dollars(long cents) {
        this.cents = cents;
    }

    public Dollars() {
        this(0);
    }

    public long cents() {
        return cents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dollars dollars = (Dollars) o;
        return cents == dollars.cents;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cents);
    }

    public Dollars add(Dollars addend) {
        return new Dollars(cents + addend.cents);
    }

    public Dollars subtract(Dollars subtrahend) {
        return new Dollars(cents - subtrahend.cents);
    }

    @Override
    public int compareTo(Dollars otherDollars) {
        return Long.compare(cents, otherDollars.cents);
    }

    @Override
    public String toString() {
        return (cents < 0 ? "-" : "") + '$' + Math.abs(cents / 100)
                + '.' + (cents % 100 == 0 ? "00" : Math.abs(cents % 100));
    }
}
