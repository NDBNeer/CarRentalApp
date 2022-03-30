package com.ndb345.carrentalapp;

public class Car {
    private String name;
    private double dailyrate;
    private int image,status;

    public Car(String name, double dailyrate, int image, int status) {
        this.name = name;
        this.dailyrate = dailyrate;
        this.image = image;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDailyrate() {
        return dailyrate;
    }

    public void setDailyrate(double dailyrate) {
        this.dailyrate = dailyrate;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
