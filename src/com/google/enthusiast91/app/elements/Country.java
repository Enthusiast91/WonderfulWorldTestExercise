package com.google.enthusiast91.app.elements;

import java.util.HashMap;

class Country {
    private final Budget budget = new Budget();
    private final int number;
    private int amountOfExpenses;
    private int amountOfProfit;

    private Country countryLeft;
    private Country countryRight;
    private Country countryTop;
    private Country countryBottom;

    Country(int number) {
        this.number = number;
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

    void trade() {
        int moneyForMonth = budget.getMoneyForMonth();
        int[] moneyForNeighbours = new int[4];

        for (int i = 4; (i > 0) && moneyForMonth > 0; i--) {
            int moneyForOne = moneyForMonth > i ? moneyForMonth / i : 1;
            moneyForNeighbours[4 - i] = moneyForOne;
            moneyForMonth -= moneyForOne;
        }

        buy(countryBottom, moneyForNeighbours[3]);
        buy(countryRight, moneyForNeighbours[2]);
        buy(countryLeft, moneyForNeighbours[1]);
        buy(countryTop, moneyForNeighbours[0]);
    }

    void clearData() {
        amountOfExpenses = 0;
        amountOfProfit = 0;
    }

    String[] getReport() {
        String[] arrString = new String[3];
        arrString[0] = "Бюджет на начало месяца страны №" + number + ": " + (budget.getMoneyOfTreasury() + budget.getSizeUntouchableCoinCollection());
        arrString[1] = "Расход: " + amountOfExpenses;
        arrString[2] = "Приход: " + amountOfProfit;
        return arrString;
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
}