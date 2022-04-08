package com.monstersaku;

public class SpecialMove extends Move {
    // Extended attributes
    private final int maxAmmunition;

    // Constructor
    public SpecialMove (
            String name, 
            ElementType elementType, 
            int accuracy, 
            int priority, 
            int ammunition
        ) {
            super(name, elementType, accuracy, priority, ammunition);
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
