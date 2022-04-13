package com.monstersaku;
import java.text.DecimalFormat;

public class NormalMove extends Move {
    // Extended attributes
    private int basePower;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    // Constructor
    public NormalMove (
            int id,
            String name, 
            ElementType elementType, 
            int accuracy, 
            int priority, 
            int ammunition,
            int basePower
        ) {
            super(id, name, elementType, accuracy, priority, ammunition);
            this.basePower = basePower;
    }

    // Methods
    @Override
    /**
     * Menggunakan ATK dan DEF
     */
    public void executeMove(Monster sourceMonster, Monster targetMonster) {
        int randAccuracy = (int)Math.floor(Math.random()*(100-1+1)+1);
        if (randAccuracy > this.getAccuracy()){
            System.out.printf("%s's move missed!\n", sourceMonster.getNama());
        }
        else if (sourceMonster.isMonsterAlive()) {
            if (this.getAmmunition() == 0) {
                System.out.printf("Not enough ammo! %s's move failed...\n", sourceMonster.getNama());
            }
            else {
                // Get soucerMonster sourceAttack
                double baseAtk = sourceMonster.getBaseStats().getAttack();
                int atkBuff = sourceMonster.getSB().getArrSB()[0];
                double sourceAttack = StatsBuff.getStat(baseAtk, atkBuff);

                // Get targetMonster targetDefense
                double baseDef = targetMonster.getBaseStats().getDefense();
                int defBuff = targetMonster.getSB().getArrSB()[1];
                double targetDefense = StatsBuff.getStat(baseDef, defBuff);
    
                // Get random between 0.85 - 1
                double rand = Math.random() / 100 * 15 + 0.85;
                rand = Double.valueOf(df.format(rand));
    
                // Get effectivity
                double effectivity = 1;
                for (ElementType enemyType : targetMonster.getElement()){
                    effectivity = com.monstersaku.util.EffectivityConfig.getEffectivity(this.getElementType(), enemyType);
                }
    
                // Get burn
                double burn = 1;
                if (sourceMonster.getStatusCondition() == StatusCondition.BURN) {burn = 0.5;}

                // Get damage calculation
                double damage = Math.floor(((this.basePower) * (sourceAttack/targetDefense) + 2) * rand * effectivity * burn);

                // HP reduction 
                Stats newStats = targetMonster.getBaseStats();
                double newHP = newStats.getHealthPoint() - damage;
                double maxHP = newStats.getMaxHealthPoint();
                newStats.setHealthPoint(newHP, maxHP);
                targetMonster.setStats(newStats);
                System.out.printf("It dealt %d damages!\n", (int) damage);
                this.setAmmunition(getAmmunition() - 1);
            }    
        } 
    }
}
