package com.google.enthusiast91.app.elements;

import java.util.HashMap;
import java.util.List;

public class Country {
    private final Budget budget;
    private int number;
    private int amountOfExpenses;
    private int amountOfProfit;

    private Country countryLeft;
    private Country countryRight;
    private Country countryTop;
    private Country countryBottom;

    Country(int number) {
        this.number = number;
        budget = new Budget(number);
    }

    Budget getBudget() {
        return budget;
    }

    void setCountryLeft(Country countryLeft) {
        this.countryLeft = countryLeft;
    }

    void setCountryRight(Country countryRight) {
        this.countryRight = countryRight;
    }

    void setCountryTop(Country countryTop) {
        this.countryTop = countryTop;
    }

    void setCountryBottom(Country countryBottom) {
        this.countryBottom = countryBottom;
    }

    void replenishBudget(int annualBudget) {
        budget.addCoin(new Coin(annualBudget, number));
    }

    private void buy(Country sellerCountry, int expenses) {
        if (expenses > 0) {
            amountOfExpenses += expenses;
            HashMap<Integer, Coin> coinPaid = budget.subCoins(expenses);
            sellerCountry.sale(coinPaid);
        }
    }

    private void sale(HashMap<Integer, Coin> profit) {
        for (Coin coin : profit.values()) {
            amountOfProfit += coin.getValue();
        }
        budget.addCoinsInTemporaryStorageUntilNextMonth(profit);
    }

    void trade() {
        int moneyForMonth = budget.getMoneyForMonth();
        int moneyForBuyInBottomCountry = 0;
        int moneyForBuyInRightCountry = 0;
        int moneyForBuyInLeftCountry = 0;
        int moneyForBuyInTopCountry = 0;

        while (moneyForMonth > 0) {
            moneyForBuyInBottomCountry++;
            if (--moneyForMonth == 0) { break; }

            moneyForBuyInRightCountry++;
            if (--moneyForMonth == 0) { break; }

            moneyForBuyInLeftCountry++;
            if (--moneyForMonth == 0) { break; }

            moneyForBuyInTopCountry++;
            if (--moneyForMonth == 0) { break; }
        }

        buy(countryBottom, moneyForBuyInBottomCountry);
        buy(countryRight, moneyForBuyInRightCountry);
        buy(countryLeft, moneyForBuyInLeftCountry);
        buy(countryTop, moneyForBuyInTopCountry);
    }

    void clearData() {
        amountOfExpenses = 0;
        amountOfProfit = 0;
    }

    String[] getReport() {
        String[] arrString = new String[5];
        arrString[0] = "Бюджет на начало месяца страны №" + number + ": " + (budget.getMoneyOfTreasury() + budget.getSizeUntouchableCoinCollection());
        arrString[1] = "Бюджет на месяц: " + budget.getMoneyForMonth();
        arrString[2] = "Монет в коллекции: " + budget.getSizeUntouchableCoinCollection();
        arrString[3] = "Расход: " + amountOfExpenses;
        arrString[4] = "Приход: " + amountOfProfit;
        return arrString;
    }
}