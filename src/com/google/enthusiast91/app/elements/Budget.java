package com.google.enthusiast91.app.elements;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class Budget {

    /**
     * Неприкосновенный запас.
     * Коллекция монет, для сбора иностранной валюты. Монеты из НЗ не тратятся.
     * Хранится не более 1-ой монеты каждой валюты.
     */
    private final HashMap<Integer, Coin> untouchableCoinCollection = new HashMap<>();

    /**
     * Временное хранилище.
     * Используется для хранения прибыли до наступления следующего месяца.
     */
    private final HashMap<Integer, Coin> temporaryStorage = new HashMap<>();

    /**
     * Казна.
     * Деньги которые идут на расходы.
     */
    private final HashMap<Integer, Coin> treasury = new HashMap<>();

    private int moneyForMonth = 0;

    int getSizeUntouchableCoinCollection() {
        return untouchableCoinCollection.size();
    }

    int getMoneyForMonth() {
        return moneyForMonth;
    }

    int getMoneyOfTreasury() {
        return treasury.values()
                .stream()
                .mapToInt(Coin::getValue)
                .sum();
    }

    /**
     * <li>Перед началом нового месяца деньги из временного хранилища перевозят в казну.</li>
     * <li>При расчете денег на месяц учитываются как деньги казны, так и неприкосновенного запаса.</li>
     */
    void calculateMoneyForMonth() {
        for (Coin coin : temporaryStorage.values()) {
            addCoinToTreasury(coin);
        }
        temporaryStorage.clear();

        int moneyOfTreasury = getMoneyOfTreasury();
        moneyForMonth = (moneyOfTreasury + untouchableCoinCollection.size()) / 2;
        if (moneyForMonth > moneyOfTreasury) {
            moneyForMonth = moneyOfTreasury;
        }
    }

    /**
     * Создание набора монет для оплаты:
     * <li>- Несколько раз перебираем монеты казны, доступные для расходов;</li>
     * <li>- Добавляем по 1-ой монете всех доступных валют, пока не наберётся неоходимое количество.</li>
     *
     * @return Набор монет для оплаты расходов.
     */
    HashMap<Integer, Coin> subCoins(int valueOfExpenses) {
        moneyForMonth -= valueOfExpenses;
        HashMap<Integer, Coin> coinMap = new HashMap<>();

        while (valueOfExpenses > 0) {
            Iterator<Map.Entry<Integer, Coin>> treasuryEntry = treasury.entrySet().iterator();
            while (treasuryEntry.hasNext() && valueOfExpenses > 0) {
                Coin treasuryCoin = getNextCoin(treasuryEntry);
                addCoin(treasuryCoin.subValue(1), coinMap);
                valueOfExpenses--;
                if (treasuryCoin.getValue() == 0) {
                    treasuryEntry.remove();
                }
            }
        }
        return coinMap;
    }

    /**
     * Для того что-бы страна сразу же не воспользовалась деньгами, которые ей пришли в этом месяце
     * прибыль помещается во временное хранилище, до наступления следующего месяца.
     */
    void addCoins(HashMap<Integer, Coin> sumOfExpenses) {
        for (Coin coin : sumOfExpenses.values()) {
            addCoin(coin, temporaryStorage);
        }
    }

    void addCoinToTreasury(Coin coin) {
        if (coin.getValue() == 0) {
            return;
        }
        if (!untouchableCoinCollection.containsKey(coin.getNumCountry())) {
            untouchableCoinCollection.put(coin.getNumCountry(), coin.subValue(1));
        }
        addCoin(coin, treasury);
    }

    private void addCoin(Coin coin, HashMap<Integer, Coin> purpose) {
        if (coin.getValue() == 0) {
            return;
        }
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