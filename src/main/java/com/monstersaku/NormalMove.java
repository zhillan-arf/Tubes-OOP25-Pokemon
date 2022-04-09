package com.monstersaku;

import com.monstersaku.util.*
import java.text.DecimalFormat;

public class NormalMove extends Move {
    // Extended attributes
    private int basePower;
    private final int maxAmmunition;

    private static final DecimalFormat df = new DecimalFormat("0.00")

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
            this.maxAmmunition = ammunition;
    }

    // Methods
    @Override
    /**
     */
    public void executeMove(Monster sourceMonster, Monster targetMonster) {
        if (sourceMonster != isMonsterAlive()){
            System.out.println("Your Monster has died! Move can't be done");
        }
        else{
            if (this.getAmmunition() == 0) {
                System.out.printf("Not enough ammo! %s stumbled. Move failed...", sourceMonster.getNama());
            }
            else{
                ////get soucerMonster attack and targetMonster defense
                double sourceAttack = sourceMonster.getBaseStats().getAttack();
                double targetDefense = targetMonster.getBaseStats().getDefense();
    
                //get random between 0.85 - 1
                double rand = Math.random() / 100 * 15 + 0.85;
                rand = Double.valueOf(df.format(rand));
    
                //get effectivity
                double effectivity = 1;
                for (ElementType enemyType : targetMonster.getElementType()){
                    effectivity = EffectivityConfig.getEffectivity(elementType, enemyType);
                }
    
                //get burn
                double burn = 1;
                if (sourceMonster.getStatusCondition() == StatusCondition.BURN){
                    burn = 0.5;
                }
    
                //get damage calculation
                double damage = Math.floor(((this.basePower) * (sourceAttack/targetDefense) + 2) * rand * effectivity * burn);

                //HP reduction 
                Stats newStats = targetMonster.getBaseStats();
                double newHP = newStats.setHealthPoint(getHealthPoint() - damage);
                targetMonster.setStats(newStats);
                this.setAmmunition(getAmmunition() - 1);
            }    
        }    
    }
}
