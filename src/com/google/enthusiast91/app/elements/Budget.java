package com.google.enthusiast91.app.elements;

import java.util.*;

class Budget {
    private final HashMap<Integer, Coin> treasury = new HashMap<>();
    private final HashMap<Integer, Coin> untouchableCoinCollection = new HashMap<>();
    private final HashMap<Integer, Coin> temporaryStorage = new HashMap<>();
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
        if (coin.getValue() == 0) { return; }
        if (!untouchableCoinCollection.containsKey(coin.getNumCountry())) {
            untouchableCoinCollection.put(coin.getNumCountry(), coin.subValue(1));
        }
        if (coin.getValue() == 0) { return; }

        addCoinToHashMap(coin, treasury);
        moneyOfTreasury += coin.getValue();
    }

    /**
     * Для того что-бы страна сразу же не воспользовалась деньгами, которые ей пришли в этом месяце
     * помещаем прибыль во временное хранилище, до наступления следующего месяца.
     */
    void addCoinsInTemporaryStorageUntilNextMonth(HashMap<Integer, Coin> sumOfExpenses) {
        for (Coin coin : sumOfExpenses.values()) {
            addCoinToHashMap(coin, temporaryStorage);
        }
    }

    /**
     * Создание набора монет для оплаты:
     * <li>- Несколько раз перебираем монеты казны, доступные для расходов;</li>
     * <li>- Добавляем по 1-ой монете всех доступных валют, пока не наберётся неоходимое количество.</li>
     * @return Набор монет для оплаты расходов.
     */
    HashMap<Integer, Coin> subCoins(int valueOfExpenses) {
        moneyOfTreasury -= valueOfExpenses;
        moneyForMonth -= valueOfExpenses;
        HashMap<Integer, Coin> coinMap = new HashMap<>();

        while (valueOfExpenses > 0) {
            Iterator<Map.Entry<Integer, Coin>> treasuryEntry = treasury.entrySet().iterator();
            while (treasuryEntry.hasNext() && valueOfExpenses > 0) {
                Coin treasuryCoin = getNextCoin(treasuryEntry);
                addCoinToHashMap(treasuryCoin.subValue(1), coinMap);
                valueOfExpenses--;
                if (treasuryCoin.getValue() == 0) {
                    treasuryEntry.remove();
                }
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
        if (coin.getValue() == 0) { return; }
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