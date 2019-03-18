package com.github.dballinger.activityweather;

import java.util.Objects;

public class Semicircle {
    private long semicircles;

    public Semicircle(long semicircles) {
        this.semicircles = semicircles;
    }

    public DecimalDegree toDegrees() {
        return new DecimalDegree(semicircles * 180 / Math.pow(2, 31));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Semicircle that = (Semicircle) o;
        return semicircles == that.semicircles;
    }

    @Override
    public int hashCode() {
        return Objects.hash(semicircles);
    }
}
