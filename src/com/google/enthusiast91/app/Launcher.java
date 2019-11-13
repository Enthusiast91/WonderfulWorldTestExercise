package com.google.enthusiast91.app;

import com.google.enthusiast91.app.elements.Country;
import com.google.enthusiast91.app.elements.World;

public class Launcher {
    public static void main(String[] args) {
        World world = new World();
        for (Country country : world) {
            country.trade();
        }
        for (Country country : world) {
            country.report();
        }
    }
}
