package com.monstersaku;

public class NormalMove extends Move {
    // Extended attributes
    private int basePower;
    private final int maxAmmunition;

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
        //
    }
}
