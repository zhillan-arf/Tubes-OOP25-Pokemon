package com.monstersaku;

public class SpecialMove extends Move {
    // Extended attributes
    private final int maxAmmunition;

    // Constructor
    public SpecialMove (
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
}
