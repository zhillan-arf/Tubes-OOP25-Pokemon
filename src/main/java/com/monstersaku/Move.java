package com.monstersaku;

public class Move {
    // Attributes
    private String name;
    private MoveType moveType;
    private ElementType elementType;
    private int accuracy;
    private int priority;
    private int ammunition;
    

    // Konstruktor
    public Move(
            String name, 
            MoveType moveType, 
            ElementType elementType, 
            int accuracy, 
            int priority, 
            int ammunition
        ) {
            this.name = name;
            this.moveType = moveType;
            this.elementType = elementType;
            this.accuracy = accuracy;
            this.priority = priority;
            this.ammunition = ammunition;
    }

    // Method Getter
    public String getName() {
        return this.name;
    }

    public MoveType getMoveType() {
        return this.moveType;
    }

    public ElementType getElementType() {
        return this.elementType;
    }

    public int getAccuracy() {
        return this.accuracy;
    }

    public int getAmmunition() {
        return this.ammunition;
    }

    public int getPriority() {
        return this.priority;
    }

    // Method eksekusi move
    public void executeMove(Monster targetMonster) {
        // Left empty. Overriden/overloaded by subclasses
    }
}
