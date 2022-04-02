package com.monstersaku;
import java.util.List;

public class Player {
    // Attributes
    String playerName;
    List<Monster> monsters;
    Monster currentMonster;

    // Konstruktor

    // Methods
    public Monster getCurrentMonster() {
        // Return reference to current monster
        return currentMonster;
    }
    public void printMonsters() {
        /**
         * Print all monsters, NUMBERED from 1...monsters.size()
         * Looks like this:
         * 1. Charizard (155/213)
         * 2. Pikachu (140/193)
         * (Cont...)
         * [zh] If this isn't aesthetic pelase replace with another design
         */
    }
    public Monster getNumthMonster(int num) {
        // Get the num-th monster
        // monsters uses an indexing system of [1..monsters.size()]
        return monsters.get(num - 1);
    }
    public String getPlayerName() {
        return this.playerName;
    }

}
