package com.monstersaku;

public class StatusMove extends Move {
    // Extended attributes
    private Target target;
    private StatusCondition statCondBuff;
    private int hpBuff;
    /**
     * Contents of arrSB
     * 0. ATK
     * 1. DEF
     * 2. SP ATK
     * 3. SP DEF
     * 4. SPEED
     */
    private int arrSB[];

    // Constructor
    public StatusMove (
            int id,
            String name,  
            ElementType elementType, 
            int accuracy, 
            int priority, 
            int ammunition,
            Target target,
            StatusCondition statCondBuff,
            int hpBuff,
            int[] arrSB
        ) {
            super(id, name, elementType, accuracy, priority, ammunition);
            this.target = target;
            this.statCondBuff = statCondBuff;
            this.hpBuff = hpBuff;
            this.arrSB = arrSB;
    }
    
    // Methods
    @Override
    public void executeMove(Monster sourceMonster, Monster targetMonster) {
        if (this.getAmmunition() == 0) {
            System.out.printf("Not enough ammo! %s stumbled. Move failed...\n", sourceMonster.getNama());
        }
        else {
            // Determine target
            Monster monster = sourceMonster;
            if (this.target == Target.ENEMY) monster = targetMonster;

            // Give status condition
            monster.setStatusCondition(this.statCondBuff);

            // Give HP buff
            double hp = monster.getBaseStats().getHealthPoint();
            double maxHP = monster.getBaseStats().getMaxHealthPoint();
            double deltaHP = (this.hpBuff * 0.01 * maxHP);
            monster.getBaseStats().setHealthPoint(hp + deltaHP);
            
            // Add stat buffs
            int[] oldArr = monster.getSB().getArrSB();
            int[] resultArr = new int[5];
            for (int i = 0; i < 5;  i++) {
                resultArr[i] = oldArr[i] + arrSB[i];
            }
            monster.getSB().setSB (
                resultArr[0], 
                resultArr[1], 
                resultArr[2], 
                resultArr[3], 
                resultArr[4]);
            
            // Print results
            String nama = monster.getNama();
            int[] newArr = monster.getSB().getArrSB();
            StatusCondition.printGotStatus(monster, this.statCondBuff);
            if (deltaHP != 0) System.out.printf("%s healed %d HP.", nama, deltaHP);
            if (oldArr[0] != newArr[0]) System.out.printf("%s's ATK buffed into %d.", nama, newArr[0]);
            if (oldArr[1] != newArr[1]) System.out.printf("%s's DEF buffed into %d.", nama, newArr[1]);
            if (oldArr[2] != newArr[2]) System.out.printf("%s's SP. ATK buffed into %d.", nama, newArr[2]);
            if (oldArr[3] != newArr[3]) System.out.printf("%s's SP. DEF buffed into %d.", nama, newArr[3]);
            if (oldArr[4] != newArr[4]) System.out.printf("%s's SPEED buffed into %d.", nama, newArr[4]);

            this.setAmmunition(this.getAmmunition() - 1);
        }
    }
}
