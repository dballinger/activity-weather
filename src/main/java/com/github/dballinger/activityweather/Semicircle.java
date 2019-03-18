package com.github.dballinger.activityweather;

public class Semicircle {
    public long fromDegrees(double degrees) {
        return Math.round(degrees * Math.pow(2, 31) / 180);
    }

    public double toDegrees(long semicircles) {
        return semicircles * 180 / Math.pow(2, 31);
    }
}
