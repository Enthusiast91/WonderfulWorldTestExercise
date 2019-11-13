package com.google.enthusiast91.app.elements;

import java.util.List;

public class Country {
    private int number;
    private final Budget budget;
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


    private void buy(Country sellerCountry, int sumOfExpenses) {
        if (sumOfExpenses > 0) {
            List<Coin> coinPaid = budget.subCoins(sumOfExpenses);
            sellerCountry.sale(coinPaid);
        }
    }

    private void sale(List<Coin> profit) {
        budget.addCoins(profit);
    }

    public void trade() {
        int moneyMonth = budget.getMoneyForMonth();
        int moneyForBuyInBottomCountry = 0;
        int moneyForBuyInRightCountry = 0;
        int moneyForBuyInLeftCountry = 0;
        int moneyForBuyInTopCountry = 0;

        while (moneyMonth > 0) {
            moneyForBuyInBottomCountry++;
            if (--moneyMonth == 0) { break; }

            moneyForBuyInRightCountry++;
            if (--moneyMonth == 0) { break; }

            moneyForBuyInLeftCountry++;
            if (--moneyMonth == 0) { break; }

            moneyForBuyInTopCountry++;
            if (--moneyMonth == 0) { break; }
        }

        buy(countryBottom, moneyForBuyInBottomCountry);
        buy(countryRight, moneyForBuyInRightCountry);
        buy(countryLeft, moneyForBuyInLeftCountry);
        buy(countryTop, moneyForBuyInTopCountry);
    }

    public void report() {
        System.out.println("Бюджет на начало месяца: " + budget.getMoneyOfTreasury() +
                "\nРасход: " + sumBuy +
                "\nПриход: " + sumSale);
    }
}