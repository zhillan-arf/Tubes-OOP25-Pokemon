package com.monstersaku;

public class DefaultMove extends Move {
    // Extended attributes

    // Constructor
    public DefaultMove (
            String name, 
            MoveType moveType,
            ElementType elementType, 
            int accuracy, 
            int priority, 
            int ammunition
        ) {
            super(name, moveType, elementType, 100, 0, 999);
        }
    
    // Methods
    // Overloading
    /**
     * Ammunition TIDAK dikurangi setelah eksekusi
     * ATK dan DEF target diabaikan. Power = 50
     * Setelah eksekusi, HP sourceMonster -= 1/4 maxHP sourceMonster
     */
    public void executeMove(Monster sourceMonster, Monster targetMonster) {
        //
    }
}
