package com.monstersaku;
import java.text.DecimalFormat;

public class DefaultMove extends Move {
    // Extended attributes
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private int basePower;

    // Constructor
    public DefaultMove () {
            super(999, "Default Move", ElementType.NORMAL, 100, 0, 999);
            this.basePower = 50;
    }
    
    // Copy constructor
    public DefaultMove(DefaultMove oldMove) {
        super(
            oldMove.getId(), 
            oldMove.getName(), 
            oldMove.getElementType(), 
            oldMove.getAccuracy(), 
            oldMove.getPriority(), 
            oldMove.getAmmunition()
        );
        this.basePower = 50;
    }

    // Methods
    @Override
    /**
     * Ammunition TIDAK dikurangi setelah eksekusi
     * Setelah eksekusi, HP sourceMonster -= 1/4 maxHP sourceMonster
     */
    public void executeMove(Monster sourceMonster, Monster targetMonster) {
        int randAccuracy = (int)Math.floor(Math.random()*(100-1+1)+1);
        if (randAccuracy > this.getAccuracy()){
            System.out.println(sourceMonster.getNama() + "'s move missed!");
        }
        else if (sourceMonster.isMonsterAlive()){
            if (this.getAmmunition() == 0) {
                System.out.printf("Not enough ammo! %s stumbled. Move failed...", sourceMonster.getNama());
            }
            else{
                System.out.println("It's a desperate move!");
                // Get soucerMonster sourceAttack
                double baseAtk = sourceMonster.getBaseStats().getAttack();
                int atkBuff = sourceMonster.getSB().getArrSB()[0];
                double sourceAttack = StatsBuff.getStat(baseAtk, atkBuff);

                // Get targetMonster targetDefense
                double baseDef = targetMonster.getBaseStats().getDefense();
                int defBuff = targetMonster.getSB().getArrSB()[1];
                double targetDefense = StatsBuff.getStat(baseDef, defBuff);
    
                //get random between 0.85 - 1
                double rand = Math.random() / 100 * 15 + 0.85;
                rand = Double.valueOf(df.format(rand));
    
                //get effectivity
                double effectivity = 1;
                for (ElementType enemyType : targetMonster.getElement()){
                    effectivity = com.monstersaku.util.EffectivityConfig.getEffectivity(this.getElementType(), enemyType);
                }
    
                // Get burn
                double burn = 1;
                if (sourceMonster.getStatusCondition() == StatusCondition.BURN) {burn = 0.5;}
    
                // Get damage calculation
                double damage = Math.floor(((this.basePower) * (sourceAttack/targetDefense) + 2) * rand * effectivity * burn);

                // HP targetMonster reduction 
                Stats newStats = targetMonster.getBaseStats();
                double maxHP = newStats.getMaxHealthPoint();
                double newHP = newStats.getHealthPoint() - damage;
                newStats.setHealthPoint(newHP, maxHP);
                System.out.printf("It dealt %d damages!\n", (int) damage);
                targetMonster.setStats(newStats);
                
                // HP sourceMonster reduction 
                Stats newSourceStats = sourceMonster.getBaseStats();
                double sourceHP = newSourceStats.getHealthPoint();
                double maxSourceHP = newStats.getMaxHealthPoint();
                double newSourceHP = sourceHP - (maxSourceHP * 1/4);
                newSourceStats.setHealthPoint(newSourceHP, maxSourceHP);
                System.out.printf("It costed %d damages!\n", (int) (maxSourceHP * 1/4));
                sourceMonster.setStats(newSourceStats);  
            }    
        }
    }
}
