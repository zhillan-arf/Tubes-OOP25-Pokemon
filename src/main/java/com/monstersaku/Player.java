package com.monstersaku;
import java.util.List;

public class Player {
    // Attributes
    List<Monster> monsters;
    Monster currentMonster;

    // Methods
    public Monster getCurrentMonster() {
        // Return reference to current monster
        return currentMonster;
    }
    public void printMonsters() {
        // Print all monsters, numbered from 1...monsters.size()
    }
}
