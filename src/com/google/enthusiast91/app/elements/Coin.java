package com.google.enthusiast91.app.elements;

class Coin {
    private final int countryNumber;
    private int value;

    Coin(int value, int countryNumber) {
        this.value = value;
        this.countryNumber = countryNumber;
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
        return new Coin(value, countryNumber);
    }

    int getNumCountry() {
        return countryNumber;
    }
}
