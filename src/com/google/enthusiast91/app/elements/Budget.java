package com.google.enthusiast91.app.elements;

import java.util.*;
import java.util.stream.Collectors;

class Budget {
    private final HashMap<Integer, Coin> treasury = new HashMap<>();
    private final HashMap<Integer, Coin> untouchableCoinCollection = new HashMap<>();
    private HashMap<Integer, Coin> temporaryStorage = new HashMap<>();
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

        addCoinToHashMap(coin, treasury);
        moneyOfTreasury += coin.getValue();
    }

    void addCoinsInTemporaryStorageUntilNextMonth(HashMap<Integer, Coin> sumOfExpenses) {
        for (Coin coin : sumOfExpenses.values()) {
            addCoinToHashMap(coin, temporaryStorage);
        }
    }

    HashMap<Integer, Coin> subCoins(int valueOfExpenses) {
        moneyOfTreasury -= valueOfExpenses;
        moneyForMonth -= valueOfExpenses;
        HashMap<Integer, Coin> coinMap = new HashMap<>();

        List<Integer> countryNumberSortedList = treasury.values().stream()
                .sorted(Comparator.comparingInt(Coin::getValue))
                .map(Coin::getNumCountry)
                .collect(Collectors.toList());

        for (Integer countryNumber : countryNumberSortedList) {
            int valueSubAtIter = (valueOfExpenses > treasury.size()) ? (valueOfExpenses / treasury.size()) : 1;
            Coin coin = treasury.get(countryNumber).subValue(valueSubAtIter);
            coinMap.put(countryNumber, coin);
            valueOfExpenses -= coin.getValue();
            if (coin.getValue() == 0) {
                treasury.remove(countryNumber);
            }
        }
        return coinMap;
    }

    private void addCoins(HashMap<Integer, Coin> sumOfExpenses) {
        for (Coin coin : sumOfExpenses.values()) {
            addCoin(coin);
        }
    }

    private void addCoinToHashMap(Coin coin, HashMap<Integer, Coin> purpose) {
        if (purpose.containsKey(coin.getNumCountry())) {
            purpose.get(coin.getNumCountry()).addValue(coin.getValue());
        } else {
            purpose.put(coin.getNumCountry(), coin);
        }
    }

    private Coin getNextCoin(Iterator<Map.Entry<Integer, Coin>> treasuryEntry) {
        return treasuryEntry.next().getValue();
    }
}