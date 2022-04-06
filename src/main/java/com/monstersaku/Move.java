package com.monstersaku;

public class Move {
    // Attributes
    private String name;
    private ElementType elementType;
    private int accuracy;
    private int priority;
    private int ammunition;
    private final int maxAmmunition;

    // Konstruktor
    public Move(String name, ElementType elementType, int accuracy, int priority, int ammunition, int maxAmmunition) {
        this.name = name;
        this.elementType = elementType;
        this.accuracy = accuracy;
        this.priority = priority;
        this.ammunition = ammunition;
        this.maxAmmunition = maxAmmunition;
    }

    // Method Getter
    public String getName() {
        return name;
    }
    public ElementType getElementType() {
        return elementType;
    }
    public int getAmmunition() {
        return ammunition;
    }
}
