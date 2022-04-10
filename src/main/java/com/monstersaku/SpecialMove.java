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
                // Get sourceMonster sourceAttack
                double spBaseAtk = sourceMonster.getBaseStats().getSpecialAttack();
                int spAtkBuff = sourceMonster.getSB().getArrSB()[2];
                double sourceAttack = StatsBuff.getStat(spBaseAtk, spAtkBuff);

                // Get targetMonster targetDefense
                double spBaseDef = targetMonster.getBaseStats().getDefense();
                int spDefBuff = targetMonster.getSB().getArrSB()[3];
                double targetDefense = StatsBuff.getStat(spBaseDef, spDefBuff);
    
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
                double maxHP = newStats.getMaxHealthPoint();
                newStats.setHealthPoint(newHP, maxHP);
                targetMonster.setStats(newStats);
                this.setAmmunition(getAmmunition() - 1);
            }    
        }
    }
}
