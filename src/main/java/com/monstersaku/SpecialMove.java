package com.monstersaku;

public class SpecialMove extends Move {
    // Extended attributes
    private int basePower;
    private final int maxAmmunition;

    // Constructor
    public SpecialMove (
            String name, 
            ElementType elementType, 
            int accuracy, 
            int priority, 
            int ammunition,
            int basePower
        ) {
            super(name, elementType, accuracy, priority, ammunition);
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
