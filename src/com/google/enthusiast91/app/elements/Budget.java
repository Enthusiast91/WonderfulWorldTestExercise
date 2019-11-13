package com.google.enthusiast91.app.elements;

import java.util.*;

public class Budget {
    private final HashMap<Integer, Coin> treasury = new HashMap<>();
    private final HashMap<Integer, Coin> untouchableCoinCollection = new HashMap<>();
    private int moneyOfTreasury;
    private int moneyForMonth;

    public Budget(int numberOfCountry, int startValue) {
        treasury.put(numberOfCountry, new Coin(startValue - 1, numberOfCountry));
        untouchableCoinCollection.put(numberOfCountry, new Coin(1, numberOfCountry));
        moneyOfTreasury = startValue - 1;
        moneyForMonth = startValue / 2;
    }

    public int getMoneyOfTreasury() {
        return moneyOfTreasury;
    }

    public int getMoneyForMonth() {
        return moneyForMonth;
    }

    public void addCoin(Coin coin) {
        if (!untouchableCoinCollection.containsKey(coin.getNumCountry())) {
            untouchableCoinCollection.put(coin.getNumCountry(), coin.subValue(1));
        }
        if (coin.getValue() == 0)
            return;

        if (treasury.containsKey(coin.getNumCountry())) {
            treasury.get(coin.getNumCountry()).addValue(coin.getValue());
        } else {
            treasury.put(coin.getNumCountry(), coin);
        }
        moneyOfTreasury += coin.getValue();
    }

    public void addCoins(List<Coin> sumOfExpenses) {
        for (Coin coin : sumOfExpenses) {
            addCoin(coin);
        }
    }

    public List<Coin> subCoins(int valueOfExpenses) {
        moneyOfTreasury -= valueOfExpenses;
        moneyForMonth -= valueOfExpenses;
        List<Coin> coinList = new ArrayList<>();
        Iterator<Map.Entry<Integer, Coin>> treasuryEntry = treasury.entrySet().iterator();

        while (treasuryEntry.hasNext() && valueOfExpenses > 0) {
            Coin treasuryCoin = getNextCoin(treasuryEntry);
            Coin coin = treasuryCoin.subValue(valueOfExpenses);
            valueOfExpenses -= coin.getValue();
            coinList.add(coin);

            if (treasuryCoin.getValue() == 0) {
                treasuryEntry.remove();
            }
        }
        return coinList;
    }

    private Coin getNextCoin(Iterator<Map.Entry<Integer, Coin>> treasuryEntry) {
        return treasuryEntry.next().getValue();
    }

    public boolean coinsAllCountriesAvailable() {
        return untouchableCoinCollection.size() == World.AMOUNT_COUNTRIES;
    }
}