package com.google.enthusiast91.app.elements;

import java.util.*;

class Budget {
    private final HashMap<Integer, Coin> treasury = new HashMap<>();
    private final HashMap<Integer, Coin> untouchableCoinCollection = new HashMap<>();
    private List<Coin> temporaryStorage = new ArrayList<>();
    private int moneyOfTreasury = 0;
    private int moneyForMonth = 0;

    int getMoneyOfTreasury() {
        return moneyOfTreasury;
    }

    int getMoneyForMonth() {
        return moneyForMonth;
    }

    int getSizeUntouchableCoinCollection() {
        return untouchableCoinCollection.size();
    }

    void calculateMoneyForMonth() {
        moneyForMonth = (moneyOfTreasury + untouchableCoinCollection.size()) / 2;
        if (moneyForMonth > moneyOfTreasury) {
            moneyForMonth = moneyOfTreasury;
        }
    }

    void moneyTransferFromTemporaryStorage() {
        addCoins(temporaryStorage);
        temporaryStorage.clear();
    }

    void addCoin(Coin coin) {
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

    private void addCoins(List<Coin> sumOfExpenses) {
        for (Coin coin : sumOfExpenses) {
           addCoin(coin);
        }
    }

    void addCoinsInTemporaryStorageUntilNextMonth(List<Coin> sumOfExpenses) {
        temporaryStorage.addAll(sumOfExpenses);
    }

    List<Coin> subCoins(int valueOfExpenses) {
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
}