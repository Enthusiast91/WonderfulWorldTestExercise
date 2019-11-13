package com.google.enthusiast91.app.elements;

public class Coins {
    private int value;
    private int numberOfCountry;

    Coins(int value, int numberOfCountry) {
        this.value = value;
        this.numberOfCountry = numberOfCountry;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void addValue(int value) {
        this.value += value;
    }

    public Coins subValue(int value) {
        if (this.value < value) {
            value = this.value;
        }
        this.value -= value;
        return new Coins(value, numberOfCountry);
    }

    public int getNumCountry() {
        return numberOfCountry;
    }
}
