package com.github.dballinger.activityweather;

import java.util.Objects;

public class Semicircle {
    private long value;

    public Semicircle(long semicircles) {
        this.value = semicircles;
    }

    public DecimalDegree toDegrees() {
        return new DecimalDegree(value * 180 / Math.pow(2, 31));
    }

    public long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Semicircle that = (Semicircle) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
