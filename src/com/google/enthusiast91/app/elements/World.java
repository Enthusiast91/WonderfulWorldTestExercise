package com.google.enthusiast91.app.elements;

public class World {
    private final Country[][] map = new Country[10][10];

    public World() {
        int size = 10;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = new Country(i * size + j);
            }
        }

        map[0][0].setCountryTop(map[size - 1][0]);
        map[size - 1][0].setCountryBottom(map[0][0]);
        map[0][0].setCountryLeft(map[0][size - 1]);
        map[0][size - 1].setCountryRight(map[0][0]);

        for (int i = 1; i < size; i++) {
            for (int j = 1; j < size; j++) {
                map[i][j].setCountryTop(map[i - 1][j]);
                map[i - 1][j].setCountryBottom(map[i][j]);
                map[i][j].setCountryLeft(map[i][j - 1]);
                map[i][j - 1].setCountryRight(map[i][j]);
            }
            map[i][0].setCountryTop(map[i - 1][0]);
            map[i - 1][0].setCountryBottom(map[i][0]);
            map[i][0].setCountryLeft(map[i][size - 1]);
            map[i][size - 1].setCountryRight(map[i][0]);

            map[0][i].setCountryTop(map[size - 1][i]);
            map[size - 1][i].setCountryBottom(map[0][i]);
            map[0][i].setCountryLeft(map[0][i - 1]);
            map[0][i - 1].setCountryRight(map[0][i]);
        }
    }
}
