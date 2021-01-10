package com.jarekjal.endo.models;

public class TrackPoint {

    private String time;
    private String lat;
    private String lon;
    private String alt;
    private String dist;
    private String hr;
    private String cad;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public String getHr() {
        return hr;
    }

    public void setHr(String hr) {
        this.hr = hr;
    }

    public String getCad() {
        return cad;
    }

    public void setCad(String cad) {
        this.cad = cad;
    }

    @Override
    public String toString() {
        return "TrackPoint{" +
                "time='" + time + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", alt='" + alt + '\'' +
                ", dist='" + dist + '\'' +
                ", hr='" + hr + '\'' +
                ", cad='" + cad + '\'' +
                '}';
    }
}
