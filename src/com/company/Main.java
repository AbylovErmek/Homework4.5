package com.company;

import java.util.Random;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {260, 270, 300, 350, 380, 340,250,250};
    public static int[] heroesDamage = {10, 20, 5, 0, 10, 5,5,5};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic", "Golem", "Lucky", "Berserk","Thor"};
    public static String[] heroesName = {"Warir", "Magic", "Bool", "Medic", "Golem", "Lucky", "Berserk"};
    public static int roundNumber = 0;
    public static boolean stunned = false;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void round() {
        roundNumber++;
        System.out.println("ROUND №" + roundNumber);
        changeDefence();
        if (bossHealth > 0) {
            bossHits();
        }

        thorStun();
        golemAbility();
        luckyAbility();
        berserkAbility();
        medicHelp();
        heroesHit();
        printStatistics();
    }

    private static void thorStun() {
        if(heroesHealth[7]>0){
            Random r = new Random();
            int chanse = r.nextInt(2);
            System.out.println(chanse);
            if(chanse == 1){
                bossDamage = 0;
                System.out.println("босс оглушен!");
                stunned = true;
            }
        }
    }

    public static void changeDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss chose: " + bossDefenceType);
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void printStatistics() {
        System.out.println("_______________________");
        System.out.println("Boss health: " + bossHealth + " [" + bossDamage + "]");
        for (int i = 0; i < heroesAttackType.length; i++) {
            System.out.println(heroesAttackType[i] + " health: "
                    + heroesHealth[i] + " [" + heroesDamage[i] + "]");
            System.out.println("Medic health:");
        }
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static int randomNum() {
        return new Random().nextInt(100) + 1;

    }

    public static void medicHelp() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] < 100 && heroesDamage[i] != 0) {
                    int r = randomNum();
                    heroesHealth[i] += r;
                    System.out.println("medic " + heroesName[i] + "+" + r);
                    break;

                }
            }

        }
    }

    public static void golemAbility(){
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[4] > 0 && !stunned) {
                heroesHealth[i] = heroesHealth[i] + (bossDamage/5);
                heroesHealth[4] = heroesHealth[4] - (bossDamage/5);
            }
        }
    }

    public static void luckyAbility(){
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[5]>0){
                Random random = new Random();
                int chance = random.nextInt(2);
                if(chance == 1){
                    heroesHealth[5] = heroesHealth[5] - (0);

                }
            }
        }
    }

    public static void berserkAbility(){
        if (heroesHealth[6]>0 && !stunned){
            bossDamage = 25;
            heroesDamage[6] +=25;}

    }

    public static void heroesHit() {
        if (!stunned){
            bossDamage = 50;}
        for (int i = 0; i < heroesDamage.length; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                if (bossDefenceType == heroesAttackType[i]) {
                    Random random = new Random();
                    int coeff = random.nextInt(10) + 2;
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println("Critical damage: " + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }
}
