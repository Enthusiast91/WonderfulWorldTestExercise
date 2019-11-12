package com.google.enthusiast91.app.elements;

public class Country {
    private int number;
    private Budget budget;
    private int sumBuy;
    private int sumSale;

    private Country countryLeft;
    private Country countryRight;
    private Country countryTop;
    private Country countryBottom;

    public Country(int number) {
        this.number = number;
        budget = new Budget(number, 20);
    }

    public int getNumber() {
        return number;
    }

    public int getSumBuy() {
        return sumBuy;
    }

    public int getSumSale() {
        return sumSale;
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


    private void buy(Country country) {
    }

    private void sale(Country country) {

    }

    public void trade() {

    }

    public void report() {
        System.out.println("Бюджет на начало месяца: " + budget.getMoney() +
                "\nРасход: " + sumBuy +
                "\nПриход: " + sumSale);
    }
}