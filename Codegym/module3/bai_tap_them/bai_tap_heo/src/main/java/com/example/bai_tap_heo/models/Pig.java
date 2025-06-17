package com.example.bai_tap_heo.models;

import java.time.LocalDate;

public class Pig {
    private int id;
    private String pidNumber;
    private LocalDate entryDate;
    private double entryWeight;
    private LocalDate exitDate;
    private double exitWeight;
    private Origin origin;
    private boolean sold;

    public Pig() {}

    public Pig(int id, String pidNumber, LocalDate entryDate, double entryWeight, LocalDate exitDate, double exitWeight, Origin origin, boolean sold) {
        this.id = id;
        this.pidNumber = pidNumber;
        this.entryDate = entryDate;
        this.entryWeight = entryWeight;
        this.exitDate = exitDate;
        this.exitWeight = exitWeight;
        this.origin = origin;
        this.sold = sold;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPidNumber() {
        return pidNumber;
    }

    public void setPidNumber(String pidNumber) {
        this.pidNumber = pidNumber;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public double getEntryWeight() {
        return entryWeight;
    }

    public void setEntryWeight(double entryWeight) {
        this.entryWeight = entryWeight;
    }

    public LocalDate getExitDate() {
        return exitDate;
    }

    public void setExitDate(LocalDate exitDate) {
        this.exitDate = exitDate;
    }

    public double getExitWeight() {
        return exitWeight;
    }

    public void setExitWeight(double exitWeight) {
        this.exitWeight = exitWeight;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }
}