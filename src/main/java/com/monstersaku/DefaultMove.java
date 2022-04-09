package com.monstersaku;
import java.text.DecimalFormat;

public class DefaultMove extends Move {
    // Extended attributes
    private static final DecimalFormat df = new DecimalFormat("0.00");

    // Constructor
    public DefaultMove (
            int id,
            String name, 
            ElementType elementType, 
            int accuracy, 
            int priority, 
            int ammunition
        ) {
            super(id, name, elementType, 100, 0, 999);
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
                // Get soucerMonster attack and targetMonster defense
                double sourceAttack = sourceMonster.getBaseStats().getAttack();
                double targetDefense = targetMonster.getBaseStats().getDefense();
    
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
                if (sourceMonster.getStatusCondition() == StatusCondition.BURN){
                    burn = 0.5;
                }
    
                // Get damage calculation
                double damage = Math.floor(((50) * (sourceAttack/targetDefense) + 2) * rand * effectivity * burn);

                // HP target reduction 
                Stats newStats = targetMonster.getBaseStats();
                double newHP = newStats.getHealthPoint() - damage;
                newStats.setHealthPoint(newHP);
                targetMonster.setStats(newStats);
                
                // HP source reduction 
                Stats newSourceStats = sourceMonster.getBaseStats();
                double newSourceHP = newSourceStats.getHealthPoint() - (newSourceStats.getMaxHealthPoint() * 1/4);
                newSourceStats.setHealthPoint(newSourceHP);
                sourceMonster.setStats(newSourceStats);  
            }    
        }
    }
}
