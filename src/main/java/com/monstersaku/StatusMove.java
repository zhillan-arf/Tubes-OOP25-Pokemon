package com.monstersaku;

public class StatusMove extends Move {
    // Extended attributes
    private final int maxAmmunition;

    // Constructor
    public StatusMove (
            String name, 
            MoveType moveType, 
            ElementType elementType, 
            int accuracy, 
            int priority, 
            int ammunition
        ) {
            super(name, moveType, elementType, accuracy, priority, ammunition);
            this.maxAmmunition = ammunition;
        }
    // Methods
    // Overloading
    /**
     */
    public void executeMove(Monster sourceMonster, Monster targetMonster) {
        //
    }
}
