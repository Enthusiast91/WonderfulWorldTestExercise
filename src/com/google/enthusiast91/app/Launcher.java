package com.google.enthusiast91.app;

import com.google.enthusiast91.app.elements.World;

public class Launcher {
    public static void main(String[] args) {
        float startTime = System.nanoTime();
        gameLaunch();
        System.out.println((char) 27 + "[31m" +
                "****************************************************\n" +
                "************** Поздравляю с победой ! **************\n" +
                "****************************************************");
        float endTime = System.nanoTime();
        System.out.println((char) 27 + "[0m" + (endTime - startTime) / 1_000_000);
    }

    private static void gameLaunch() {
        World world = new World();
        for (int i = 0; true; i++) {
            world.issueAnnualBudget(20);
            for (int j = 0; j < 12; j++) {
                world.calculateCountriesBudgetForMonth();
                world.report(World.SIZE);
                System.out.printf((char) 27 + "[34m================ Завершен месяц %03d ================\n", i * 12 + j);
                System.out.println((char) 27 + "[0m");
                if (world.victoryConditionsMet()) {
                    return;
                }
                world.resetCountriesTradingData();
                world.launchOfTading();
            }
        }
    }
}
