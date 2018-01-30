package com.aby.roomforall.dto;

import java.io.Serializable;

public class Planning implements Serializable {

    private String departure;
    private String destination;
    private int places;
    private int placesLeft;
    private Idate date;
    private Meta meta;
    private int price;

    public Planning() {}

    public Planning(String departure, String destination, int places, int placesLeft, Idate date, Meta meta, int price) {
        this.departure = departure;
        this.destination = destination;
        this.places = places;
        this.placesLeft = placesLeft;
        this.date = date;
        this.meta = meta;
        this.price = price;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public int getPlacesLeft() {
        return placesLeft;
    }

    public void setPlacesLeft(int placesLeft) {
        this.placesLeft = placesLeft;
    }

    public Idate getDate() {
        return date;
    }

    public void setDate(Idate date) {
        this.date = date;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
