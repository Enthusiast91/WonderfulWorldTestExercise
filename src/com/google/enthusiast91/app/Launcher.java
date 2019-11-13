package com.google.enthusiast91.app;

import com.google.enthusiast91.app.elements.Country;
import com.google.enthusiast91.app.elements.World;

public class Launcher {
    public static void main(String[] args) {
        int monthCounter = 0;
        World world = new World();
        for (int i = 0; !world.victoryConditionsExecuted(); i++) {
            world.issueAnnualBudget(20);
            for (int j = 0; j < 12; j++) {
                world.calculateCountriesBudgetforMonth();
                world.report(10);
                world.resetCountriesDataAboutTrade();
                world.trade();
                System.out.printf("\n\n" + (char)27 + "[34m============================================= Месяц %03d =============================================\n", i * 12 + j);
                System.out.println((char)27 + "[0m");
            }
        }
    }
}
