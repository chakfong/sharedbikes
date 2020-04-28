package com.fly.common.dto;

import org.locationtech.spatial4j.io.GeohashUtils;

public class LocationDTO {

    private Double lat;
    private Double lng;
    private String geo;

    public LocationDTO() {
    }

    public LocationDTO(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
        this.geo = GeohashUtils.encodeLatLon(lat,lng);
    }

    public LocationDTO(Double lat, Double lng, String geo) {
        this.lat = lat;
        this.lng = lng;
        this.geo = geo;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getGeo() {
        return geo;
    }

    public void setGeo(String geo) {
        this.geo = geo;
    }

    @Override
    public String toString() {
        return "LocationDTO{" +
                "lat=" + lat +
                ", lng=" + lng +
                ", geo='" + geo + '\'' +
                '}';
    }
}
