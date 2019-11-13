package com.google.enthusiast91.app.elements;

public class World {
    public static final int SIZE = 10;
    public static final int AMOUNT_COUNTRIES = SIZE * SIZE;
    private final Country[][] map = new Country[SIZE][SIZE];

    public World() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = new Country(i * SIZE + j);
            }
        }

        map[0][0].setCountryTop(map[SIZE - 1][0]);
        map[SIZE - 1][0].setCountryBottom(map[0][0]);
        map[0][0].setCountryLeft(map[0][SIZE - 1]);
        map[0][SIZE - 1].setCountryRight(map[0][0]);

        for (int i = 1; i < SIZE; i++) {
            for (int j = 1; j < SIZE; j++) {
                map[i][j].setCountryTop(map[i - 1][j]);
                map[i - 1][j].setCountryBottom(map[i][j]);
                map[i][j].setCountryLeft(map[i][j - 1]);
                map[i][j - 1].setCountryRight(map[i][j]);
            }
            map[i][0].setCountryTop(map[i - 1][0]);
            map[i - 1][0].setCountryBottom(map[i][0]);
            map[i][0].setCountryLeft(map[i][SIZE - 1]);
            map[i][SIZE - 1].setCountryRight(map[i][0]);

            map[0][i].setCountryTop(map[SIZE - 1][i]);
            map[SIZE - 1][i].setCountryBottom(map[0][i]);
            map[0][i].setCountryLeft(map[0][i - 1]);
            map[0][i - 1].setCountryRight(map[0][i]);
        }
    }

//    public Country getCountry(int countryNumber) {
//        if (countryNumber < 0 || countryNumber >= SIZE) {
//            return null;
//        }
//        int line = countryNumber / SIZE;
//        int column = countryNumber % SIZE;
//        return map[column][line];
//    }
//
//    public Country getCountry(int line, int column) {
//        if (line < 0 || line >= SIZE || column < 0 || column >= SIZE) {
//            return null;
//        }
//        return map[column][line];
//    }
}
