package com.google.enthusiast91.app.elements;

import java.util.Iterator;

public class World implements Iterable<Country> {
    private static final int SIZE = 10;
    private static final int AMOUNT_COUNTRIES = SIZE * SIZE;
    private final Country[][] countries = new Country[SIZE][SIZE];

    public World() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                countries[i][j] = new Country(i * SIZE + j);
            }
        }

        countries[0][0].setCountryTop(countries[SIZE - 1][0]);
        countries[SIZE - 1][0].setCountryBottom(countries[0][0]);
        countries[0][0].setCountryLeft(countries[0][SIZE - 1]);
        countries[0][SIZE - 1].setCountryRight(countries[0][0]);

        for (int i = 1; i < SIZE; i++) {
            for (int j = 1; j < SIZE; j++) {
                countries[i][j].setCountryTop(countries[i - 1][j]);
                countries[i - 1][j].setCountryBottom(countries[i][j]);
                countries[i][j].setCountryLeft(countries[i][j - 1]);
                countries[i][j - 1].setCountryRight(countries[i][j]);
            }
            countries[i][0].setCountryTop(countries[i - 1][0]);
            countries[i - 1][0].setCountryBottom(countries[i][0]);
            countries[i][0].setCountryLeft(countries[i][SIZE - 1]);
            countries[i][SIZE - 1].setCountryRight(countries[i][0]);

            countries[0][i].setCountryTop(countries[SIZE - 1][i]);
            countries[SIZE - 1][i].setCountryBottom(countries[0][i]);
            countries[0][i].setCountryLeft(countries[0][i - 1]);
            countries[0][i - 1].setCountryRight(countries[0][i]);
        }
    }

    public void issueAnnualBudget(int annualBudget) {
        for (Country country : this) {
            country.replenishBudget(annualBudget);
        }
    }

    public void calculateCountriesBudgetforMonth() {
        for (Country country : this) {
            country.getBudget().moneyTransferFromTemporaryStorage();
            country.getBudget().calculateMoneyForMonth();
        }
    }

    public void resetCountriesDataAboutTrade() {
        for (Country country : this) {
            country.clearData();
        }
    }

    public void trade() {
        for (Country country : this) {
            country.trade();
        }
    }

    public void report(int numberOfColumns) {
        int amountStrings = countries[0][0].getReport().length;

        for (int i = 0; i < AMOUNT_COUNTRIES / numberOfColumns; i++) {
            String[][] arrReport = new String[numberOfColumns][amountStrings];
            for (int j = 0; j < numberOfColumns; j++) {
                int m = (i * numberOfColumns + j) / SIZE;
                int n = (i * numberOfColumns + j) % SIZE;
                arrReport[j] = countries[m][n].getReport();
            }
            for (int k = 0; k < amountStrings; k++) {
                for (int j = 0; j < numberOfColumns; j++) {
                    System.out.printf("%46s", arrReport[j][k]);
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public boolean victoryConditionsExecuted() {
        for (Country country : this) {
            if (country.getBudget().getSizeUntouchableCoinCollection() < AMOUNT_COUNTRIES) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Iterator<Country> iterator() {
        return new Iterator<Country>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return (currentIndex < AMOUNT_COUNTRIES);
            }

            @Override
            public Country next() {
                int line = currentIndex / SIZE;
                int column = currentIndex % SIZE;
                currentIndex++;
                return countries[line][column];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
