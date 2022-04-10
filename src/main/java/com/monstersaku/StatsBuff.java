package com.monstersaku;

public class StatsBuff {
    /**
     * Attribute: an array
     * 0. ATK
     * 1. DEF
     * 2. SP ATK
     * 3. SP DEF
     * 4. SPEED
     */
    int[] arrSB;

    // Constructor
    public StatsBuff() {
        this.arrSB = new int[5];
        arrSB[0] = 0;
        arrSB[1] = 0;
        arrSB[2] = 0;
        arrSB[3] = 0;
        arrSB[4] = 0;
    }

    // Getter methods
    public int[] getArrSB() {return this.arrSB;}

    // Setter
    public void setSB(int atk, int def, int spAtk, int spDef, int speed) {
        // ATK
        if (atk < -4) this.arrSB[0] = -4;
        else if (atk > 4) this.arrSB[0] = 4;
        else this.arrSB[0] = atk;

        // DEF
        if (def < -4) this.arrSB[1] = -4;
        else if (def > 4) this.arrSB[1] = 4;
        else this.arrSB[1] = def;

        // SP ATK
        if (spAtk < -4) this.arrSB[2] = -4;
        else if (spAtk > 4) this.arrSB[2] = 4;
        else this.arrSB[2] = spAtk;

        // SP DEF
        if (spDef < -4) this.arrSB[3] = -4;
        else if (spDef > 4) this.arrSB[3] = 4;
        else this.arrSB[3] = spDef;

        // SPEED
        if (speed < -4) this.arrSB[4] = -4;
        else if (speed > 4) this.arrSB[4] = 4;
        else this.arrSB[4] = speed;
    }

    public void resetStatsBuff() {
        arrSB[0] = 0;
        arrSB[1] = 0;
        arrSB[2] = 0;
        arrSB[3] = 0;
        arrSB[4] = 0;
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
