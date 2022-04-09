package com.monstersaku;

public class DefaultMove extends Move {
    // Extended attributes

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
                double damage = Math.floor(((50) * (sourceAttack/targetDefense) + 2) * rand * effectivity * burn);

                //HP target reduction 
                Stats newStats = targetMonster.getBaseStats();
                double newHP = newStats.setHealthPoint(newStats.getHealthPoint() - damage);
                targetMonster.setStats(newStats);
                
                //HP souce reduction 
                Stats newSourceStats = soucerMonster.getBaseStats();
                double newSourceHP = newSourceStats.setHealthPoint(newSourceStats.getHealthPoint() * 3/4);
                soucerMonster.setStats(newSourceStats);
            }    
        }    
    }
}
