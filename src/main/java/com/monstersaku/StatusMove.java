package com.monstersaku;

public class StatusMove extends Move {
    // Extended attributes
    private final int maxAmmunition;
    private Target target;
    private StatusCondition statCondBuff;
    private int hpBuff;
    private int atkBuff;
    private int defBuff;
    private int spAtkBuff;
    private int spDefBuff;
    private int speedBuff;


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
            int atkBuff,
            int defBuff,
            int spAtkBuff,
            int spDefBuff,
            int speedBuff
        ) {
            super(id, name, elementType, accuracy, priority, ammunition);
            this.maxAmmunition = ammunition;
            this.target = target;
            this.statCondBuff = statCondBuff;
            this.hpBuff = hpBuff;
            this.atkBuff = atkBuff;
            this.defBuff = defBuff;
            this.spAtkBuff = spAtkBuff;
            this.spDefBuff = spDefBuff;
            this.speedBuff = speedBuff;
    }
    
    // Methods
    @Override
    /**
     */
    public void executeMove(Monster sourceMonster, Monster targetMonster) {
        //
    }
}
