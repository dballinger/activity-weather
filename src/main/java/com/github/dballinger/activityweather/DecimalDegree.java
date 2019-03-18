package com.github.dballinger.activityweather;

import java.util.Objects;

public class DecimalDegree {
    private double degrees;

    public DecimalDegree(double degrees) {
        this.degrees = degrees;
    }

    public Semicircle toSemicircle() {
        return new Semicircle(Math.round(degrees * Math.pow(2, 31) / 180));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DecimalDegree that = (DecimalDegree) o;
        return Double.compare(that.degrees, degrees) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(degrees);
    }
}
