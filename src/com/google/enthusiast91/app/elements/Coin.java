package com.google.enthusiast91.app.elements;

class Coin {
    private int value;
    private int numberOfCountry;

    Coin(int value, int numberOfCountry) {
        this.value = value;
        this.numberOfCountry = numberOfCountry;
    }

    int getValue() {
        return value;
    }

    void addValue(int value) {
        this.value += value;
    }

    Coin subValue(int value) {
        if (this.value < value) {
            value = this.value;
        }
        this.value -= value;
        return new Coin(value, numberOfCountry);
    }

    int getNumCountry() {
        return numberOfCountry;
    }
}
