package com.monstersaku;

public enum StatusCondition {
    NONE,
    BURN,
    POISON,
    SLEEP,
    PARALYZE;

    public static void printGotStatus(Monster monster, StatusCondition statCond) {
        String nama = monster.getNama();
        double maxhp = monster.getBaseStats().getMaxHealthPoint();
        switch (statCond) {
            case NONE:
                System.out.printf("%s is cleared of all Status Conditions! \n", nama);
            case BURN:
                System.out.printf("%s is now BURNING! \n", nama);
                System.out.printf("BURN reduces %s's HP by %d per turn!\n", nama, maxhp * 1/8);
                break;
            case PARALYZE:
                System.out.printf("%s is now PARALYZED! \n", nama);
                System.out.printf("%s's speed is down by half! \n", nama);
                System.out.printf("25 percent chance %s fail to execute move while PARALYZED!\n", nama);
                break;
            case POISON:
                System.out.printf("%s is now POISONED! \n", nama);
                System.out.printf("POISON reduces %s's HP by %d per turn!\n", nama, maxhp * 1/16);
                break;
            case SLEEP:
                System.out.printf("%s is fast asleep! \n", nama);
                System.out.printf("Any moves chosen by %s will not be executed while SLEEPING!");
                break;
            default:
                break;
        }
        try {
            Thread.sleep(1500);
        }
        catch (InterruptedException e) {
            System.out.println("FATAL ERROR. Something TERRIBLE has happened...");
            System.exit(1);
        }
    }
}