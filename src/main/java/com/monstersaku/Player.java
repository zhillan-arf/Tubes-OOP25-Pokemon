package com.monstersaku;
import java.util.List;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Player {
    // Attributes
    private String playerName;
    private List<Monster> monsters = new ArrayList<Monster>();
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
        List<Monster> allmonster = monsterpool;

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

    public void setMonster(List<Monster> monsters){
        this.monsters = monsters;
    }
    
    public List<Monster> getListMonster(){
        return this.monsters;
    }

    public void setCurrentMonster(int idMonster){
        try{
            this.currentMonster = monsters.get(idMonster -1);
        }
        catch (Exception e){
            System.out.println("Monster tidak tersedia");
        }
    }

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
        for (int i = 0; i < this.monsters.size(); i ++){
            System.out.printf((i+1) + ". " + this.monsters.get(i).getBaseStats().getNama() + " (" + this.monsters.get(i).getBaseStats().getHealthPoint() + "/" + this.monsters.get(i).getBaseStats().getMaxHealthPoint() + ")");
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
