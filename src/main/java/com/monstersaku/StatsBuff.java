package com.monstersaku;

public class StatsBuff {
    // Attributes
    int attack;
    int defense;
    int specialAttack;
    int specialDefense;
    int speed;

    // Constructor
    public StatsBuff() {
        this.attack = 0;
        this.defense = 0;
        this.specialAttack = 0;
        this.specialDefense = 0;
        this.speed = 0;
    }

    // Getter methods
    public int getAttack() {
        return this.attack;
    }

    public int getDefese() {
        return this.defense;
    }

    public int getSpecialAttack() {
        return this.specialAttack;
    }

    public int getSpecialDefense() {
        return this.specialDefense;
    }

    public int getSpeed() {
        return this.speed;
    }

    // Setter methods
    public void setAttack(int attack) {
        if (attack >= -4 && attack <= 4) this.attack = attack;
    }

    public void setDefense(int defense) {
        if (defense >= -4 && defense <= 4) this.defense = defense;
    }

    public void setSpecialAttack(int specialAttack) {
        if (specialAttack >= -4 && specialAttack <= 4) this.specialAttack = specialAttack;
    }

    public void setSpecialDefense (int specialDefense) {
        if (specialDefense >= -4 && specialDefense <= 4) this.specialDefense = specialDefense;
    }

    public void setSpeed(int speed) {
        if (speed >= -4 && speed <= 4) this.speed = speed;
    }

    public void resetStatsBuff() {
        this.attack = 0;
        this.defense = 0;
        this.specialAttack = 0;
        this.specialDefense = 0;
        this.speed = 0;
    }
    
    // Calculation methods
    public double getFactor(int statBuff) {
        double ret = 1;
        switch (statBuff) {
            case -4 :
                ret = 2/6;
                break;
            case -3 :
                ret = 2/5;
                break;
            case -2 :
                ret = 2/6;
                break;
            case -1 :
                ret = 2/3;
                break;
            case -0 :
                ret = 1;
                break;
            case 1 :
                ret = 3/2;
                break;
            case 2 :
                ret = 4/2;
                break;
            case 3 :
                ret = 5/2;
                break;
            case 4 :
                ret = 6/2;
                break;
        }
        return ret;
    }

    public double getStat(int baseStat, int statBuff) {
        return baseStat * getFactor(statBuff);
    }
}
