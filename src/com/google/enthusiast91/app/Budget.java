package com.google.enthusiast91.app;

import java.util.ArrayList;
import java.util.List;

public class Budget {
    private int sum;
    private final List<Coins> coinsList = new ArrayList<>(100);

    public Budget() {
        initCoins(20);
    }

    public int getSum() {
        return sum;
    }

    void initCoins(int startValue) {
        for (int i = 0; i < 100; i++) {
            coinsList.set(i, new Coins(startValue, i));
        }
    }

    public void addCoins(Coins coins) {
        int index = coins.getNumberOfCountry();
        int value = coinsList.get(index).getValue() + coins.getValue();
        coinsList.get(index).setValue(value);
        sum += coins.getValue();
    }

    public void subCoins(Coins coins) {
        int index = coins.getNumberOfCountry();
        int value = coinsList.get(index).getValue() - coins.getValue();
        coinsList.get(index).setValue(value);
        sum -= coins.getValue();
    }
}
