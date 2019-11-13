package com.google.enthusiast91.app.elements;

public class Coin {
    private int value;
    private int numberOfCountry;

    Coin(int value, int numberOfCountry) {
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

    public Coin subValue(int value) {
        if (this.value < value) {
            value = this.value;
        }
        this.value -= value;
        return new Coin(value, numberOfCountry);
    }

    public int getNumCountry() {
        return numberOfCountry;
    }
}
