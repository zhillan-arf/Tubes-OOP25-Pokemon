package com.monstersaku;

public abstract class Move {
    // Attributes
    private int id;
    private String name;
    private ElementType elementType;
    private int accuracy;
    private int priority;
    private int ammunition;
    

    // Konstruktor
    public Move(
            int id,
            String name, 
            ElementType elementType, 
            int accuracy, 
            int priority, 
            int ammunition
        ) {
            this.name = name;
            this.elementType = elementType;
            this.accuracy = accuracy;
            this.priority = priority;
            this.ammunition = ammunition;
    }

    // Getter methods
    public int getId() {return this.id;}
    public String getName() {return this.name;}
    public ElementType getElementType() {return this.elementType;}
    public int getAccuracy() {return this.accuracy;}
    public int getAmmunition() {return this.ammunition;}
    public int getPriority() {return this.priority;}

    // Setter methods
    public void setAmmunition(int ammo) {this.ammunition = ammo;}

    // Method eksekusi move
    public abstract void executeMove(Monster sourceMonster, Monster targetMonster);
}
