package org.codejudge.sb.model;

public class Location {
    private double logitude;
    private double latitude;

    public Location(double logitude, double latitude) {
        this.logitude = logitude;
        this.latitude = latitude;
    }

    public double getLogitude() {
        return logitude;
    }

    public void setLogitude(double logitude) {
        this.logitude = logitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
