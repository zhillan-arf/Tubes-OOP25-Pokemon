package com.monstersaku;

public class DefaultMove extends Move {
    // Extended attributes

    // Constructor
    public DefaultMove (
            String name, 
            ElementType elementType, 
            int accuracy, 
            int priority, 
            int ammunition
        ) {
            super(name, elementType, 100, 0, 999);
        }
    
    // Methods
    @Override
    /**
     * Ammunition TIDAK dikurangi setelah eksekusi
     * ATK dan DEF target diabaikan. Power = 50
     * Setelah eksekusi, HP sourceMonster -= 1/4 maxHP sourceMonster
     */
    public void executeMove(Monster sourceMonster, Monster targetMonster) {
        //
    }
}
