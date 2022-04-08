package com.monstersaku;
import java.util.List;
import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Random;


public class Player {
    // Attributes
    private String playerName;
    private List<Monster> monsters;
    private Monster currentMonster;
    private int countMonster;

    // Konstruktor
    public Player (String playerName, List<Monster> monsters){
        this.playerName = playerName;
        this.monsters = monsters;
    }
    //add 6 random monster
    public void getMonster(Player player, List<Monster> monsterPool){
        List<Monster> playermonster = new ArrayList<Monster>();
        List<Monster> allmonster = monsterPool;

        for (int i = 0; i < 6; i++){
            Collections.shuffle(allmonster);
            playermonster.add(allmonster.get(0));
        }
        player.setMonster(playermonster);
    }


    // Methods
    public void setName(String playerName){
        this.playerName = playerName;
    }

    public String getName(){
        return this.playerName;
    }

    public void addMonster(Monster monster){
        this.monsters.add(monster);
    }

    public int countMonster(){
        for (Monster monster : monsters){
            if (monster.isMonsterAlive()){
                countMonster += 1;
            }
            
        }
        return countMonster;
    }

    public boolean hasAliveMonsters() {
        return this.countMonster() != 0;
    }

    public void setMonster(List<Monster> monsters){
        this.monsters = monsters;
    }
    
    public List<Monster> getListMonster(){
        return this.monsters;
    }

    public void setCurrentMonster(Monster monster){
        this.currentMonster = monster;
    }

    // Return reference to current monster
    public Monster getCurrentMonster() {
        return currentMonster;
    }

    /**
     * Print all monsters, NUMBERED from 1...monsters.size()
     * Looks like this:
     * 1. Charizard (155/213)
     * 2. Pikachu (140/193)
     * (Cont...)
     */
    public void printMonsters() {
        for (int i = 0; i < this.monsters.size(); i ++){
            System.out.printf(
                (i+1) + ". " + this.monsters.get(i).getNama() 
                + " (" + this.monsters.get(i).getBaseStats().getHealthPoint() 
                + "/" + this.monsters.get(i).getBaseStats().getMaxHealthPoint() + ")");
        }
    }

    /**
     * Print only alive  monsters, NUMBERED from 1...monsters.size()
     * Looks like this:
     * 1. Charizard (155/213)
     * 2. Pikachu (140/193)
     * (Cont...)
     */
    public void printAliveMonsters() {
        for (int i = 0; i < this.monsters.size(); i ++){
            if (this.monsters.get(i).getBaseStats().getHealthPoint() > 0) {
                // Moster is alive
                System.out.printf(
                (i+1) + ". " + this.monsters.get(i).getNama() 
                + " (" + this.monsters.get(i).getBaseStats().getHealthPoint() 
                + "/" + this.monsters.get(i).getBaseStats().getMaxHealthPoint() + ")");
            }
            // else: Monster is K.O. and skipped
        }
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
