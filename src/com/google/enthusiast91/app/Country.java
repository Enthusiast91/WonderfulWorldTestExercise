package com.google.enthusiast91.app;

public class Country {
    private Budget budget;

    private Country countryLeft;
    private Country countryRight;
    private Country countryTop;
    private Country countryBottom;

    public Country(int n) {
        budget = new Budget();
    }

    public Country getCountryLeft() {
        return countryLeft;
    }

    public void setCountryLeft(Country countryLeft) {
        this.countryLeft = countryLeft;
    }

    public Country getCountryRight() {
        return countryRight;
    }

    public void setCountryRight(Country countryRight) {
        this.countryRight = countryRight;
    }

    public Country getCountryTop() {
        return countryTop;
    }

    public void setCountryTop(Country countryTop) {
        this.countryTop = countryTop;
    }

    public Country getCountryBottom() {
        return countryBottom;
    }

    public void setCountryBottom(Country countryBottom) {
        this.countryBottom = countryBottom;
    }
}