package com.google.enthusiast91.app;

import com.google.enthusiast91.app.elements.World;

public class Launcher {
    public static void main(String[] args) {
        World world = new World();
        boolean victory = false;
        for (int i = 0; !victory; i++) {
            world.issueAnnualBudget(20);
            for (int j = 0; (j < 12) && !victory; j++) {
                world.calculateCountriesBudgetForMonth();
                world.report(World.SIZE);
                System.out.printf((char) 27 + "[34m================ Завершен месяц %03d ================\n", i * 12 + j);
                System.out.println((char) 27 + "[0m");
                world.resetCountriesDataAboutTrade();
                world.trade();
                victory = world.victoryConditionsMet();
            }
        }
        System.out.println((char) 27 + "[31m" +
                        "****************************************************\n" +
                        "************** Поздравляю с победой ! **************\n" +
                        "****************************************************");
    }
}
