package io.github.eliassink.bankaccount.model;

import java.util.Objects;

public final class Cents implements Comparable<Cents> {
    private final long cents;

    public Cents(long cents) {
        this.cents = cents;
    }

    public Cents() {
        this(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cents cents = (Cents) o;
        return this.cents == cents.cents;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cents);
    }

    public Cents negate() {
        return new Cents(-cents);
    }

    public Cents add(Cents addend) {
        return new Cents(cents + addend.cents);
    }

    public Cents subtract(Cents subtrahend) {
        return new Cents(cents - subtrahend.cents);
    }

    @Override
    public int compareTo(Cents otherCents) {
        return Long.compare(cents, otherCents.cents);
    }

    @Override
    public String toString() {
        return (cents < 0 ? "-" : "") + '$' + Math.abs(cents / 100)
                + '.' + (cents % 100 == 0 ? "00" : Math.abs(cents % 100));
    }

    static Cents parseCents(String string) {
        if (!string.matches("-?\\$?\\d*\\.\\d\\d"))
            throw new NumberFormatException("invalid format");
        return new Cents(Long.parseLong(string.replaceAll("[$.]","")));
    }
}
