package com.monstersaku;
import java.text.DecimalFormat;

public class SpecialMove extends Move {
    // Extended attributes
    private int basePower;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    // Constructor
    public SpecialMove (
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
    // Menggunakan SP ATK dan SP DEF
    public void executeMove(Monster sourceMonster, Monster targetMonster) {
        int randAccuracy = (int)Math.floor(Math.random()*(100-1+1)+1);
        if (randAccuracy > this.getAccuracy()){
            System.out.println(sourceMonster.getNama() + "'s move missed!");
        }
        else if (sourceMonster.isMonsterAlive()) {
            if (this.getAmmunition() == 0) {
                System.out.printf("Not enough ammo! %s stumbled. Move failed...", sourceMonster.getNama());
            }
            else {
                // Get soucerMonster specialAttack and targetMonster specialDefense
                double sourceAttack = sourceMonster.getBaseStats().getSpecialAttack();
                double targetDefense = targetMonster.getBaseStats().getSpecialDefense();
    
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
                if (sourceMonster.getStatusCondition() == StatusCondition.BURN){
                    burn = 0.5;
                }
    
                // Get damage calculation
                double damage = Math.floor(((this.basePower) * (sourceAttack/targetDefense) + 2) * rand * effectivity * burn);

                // HP reduction 
                Stats newStats = targetMonster.getBaseStats();
                double newHP = newStats.getHealthPoint() - damage;
                newStats.setHealthPoint(newHP);
                targetMonster.setStats(newStats);
                this.setAmmunition(getAmmunition() - 1);
            }    
        }
    }
}
