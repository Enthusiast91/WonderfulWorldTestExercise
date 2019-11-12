package com.google.enthusiast91.app.elements;

public class Budget {
    private int money;
    private final Coins[] coinsWallet = new Coins[100];

    public Budget(int numberOfCountry, int startValue) {
        for (int i = 0; i < 100; i++) {
            coinsWallet[i] = new Coins(0, i);
        }
        coinsWallet[numberOfCountry].setValue(startValue);
        money = startValue;
    }

    public int getMoney() {
        return money;
    }

    public void addCoins(Coins coins) {
        coinsWallet[coins.getNumberOfCountry()].addValue(coins.getValue());
        money += coins.getValue();
    }

    public void subCoins(Coins coins) {
        coinsWallet[coins.getNumberOfCountry()].subValue(coins.getValue());
        money -= coins.getValue();
    }

    public boolean coinsAllCountriesAvailable() {
        for (Coins coins: coinsWallet) {
            if (coins.getValue() == 0 ) { return false; }
        }
        return true;
    }
}