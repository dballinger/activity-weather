package com.github.dballinger.activityweather;

public class Coord {
    private final Semicircle lat;
    private final Semicircle lon;

    public Coord(Semicircle lat, Semicircle lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public Semicircle getLat() {
        return lat;
    }

    public Semicircle getLon() {
        return lon;
    }

    @Override
    public String toString() {
        return "Coord{" +
                "lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
