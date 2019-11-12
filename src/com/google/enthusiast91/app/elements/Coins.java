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

    public void subValue(int value) {
        if (value <= this.value)
            this.value -= value;
    }

    public int getNumberOfCountry() {
        return numberOfCountry;
    }
}
