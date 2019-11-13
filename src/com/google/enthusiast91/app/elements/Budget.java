package com.google.enthusiast91.app.elements;

import java.util.*;

public class Budget {
    private int money;
    private int moneyForMonth;
    private final HashMap<Integer, Coins> treasury = new HashMap<>();
    private final HashMap<Integer, Coins> coinCollection = new HashMap<>();

    public Budget(int numberOfCountry, int startValue) {
        treasury.put(numberOfCountry, new Coins(startValue - 1, numberOfCountry));
        coinCollection.put(numberOfCountry, new Coins(1, numberOfCountry));
        money = startValue;
        moneyForMonth = money / 2;
    }

    public int getMoney() {
        return money;
    }

    public int getMoneyForMonth() {
        return moneyForMonth;
    }

      public void addCoins(Coins coins) {
        if (treasury.containsKey(coins.getNumCountry())) {
            treasury.get(coins.getNumCountry()).addValue(coins.getValue());
        } else {
            if(!coinCollection.containsKey(coins.getNumCountry())) {
                coinCollection.put(coins.getNumCountry(), coins.subValue(1));
            }
            if (coins.getValue() > 0) {
                treasury.put(coins.getNumCountry(), coins);
            }
        }
        money += coins.getValue();
    }

    public List<Coins> subCoins(int value) {
        money -= value;
        moneyForMonth -= value;
        List<Coins> coinsList = new ArrayList<>();
        Iterator<Map.Entry<Integer, Coins>> treasuryEntry = treasury.entrySet().iterator();

        while (treasuryEntry.hasNext() && value > 0) {
            Coins coins = treasuryEntry.next().getValue().subValue(value);
            value -= coins.getValue();
            coinsList.add(coins);
        }
        return coinsList;
    }

    public boolean coinsAllCountriesAvailable() {
        return coinCollection.size() == World.AMOUNT_COUNTRIES;
    }
}