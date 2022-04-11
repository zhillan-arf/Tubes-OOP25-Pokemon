package com.monstersaku;
import java.util.*;

public class Player {
    // Attributes
    private String playerName;
    private List<Monster> monsters;
    private Monster currentMonster;

    // Constructors
    public Player (String playerName, List<Monster> monsterTemplatePool){
        this.playerName = playerName;
        List<Monster> monsters = new ArrayList<Monster>();
        List<Monster> shakeablePool = monsterTemplatePool;
        for (int i = 0; i < 6; i++){
            Collections.shuffle(shakeablePool);
            monsters.add(new Monster(shakeablePool.get(0)));
        }
        this.monsters = monsters;
        Random random1 = new Random();
        this.currentMonster = monsters.get(random1.nextInt(6));
    }

    // Getter methods
    public void setName(String playerName) {this.playerName = playerName;}
    public String getPlayerName() {return this.playerName;}
    public void addMonster(Monster monster) {this.monsters.add(monster);}
    public boolean hasAliveMonsters() {
        int ctr = 0;
        for (Monster monster : monsters) {if (monster.isMonsterAlive()) ctr += 1;}
        return ctr > 0;
    }
    public Monster getNumthMonster(int num) {return monsters.get(num - 1);}

    // Setter methods
    public void setMonster(List<Monster> monsters) {this.monsters = monsters;}
    public List<Monster> getListMonster() {return this.monsters;}
    public void setCurrentMonster(Monster monster) {this.currentMonster = monster;}

    // Return reference to current monster
    public Monster getCurrentMonster() {return currentMonster;}

    /**
     * Print all monsters, NUMBERED from 1...monsters.size()
     * Looks like this:
     * 1. Charizard (155/213)
     * 2. Pikachu (140/193)
     * (Cont...)
     */
    public void printMonsters(boolean isAliveOnly) {
        for (int i = 0; i < this.monsters.size(); i ++){
            if ((this.monsters.get(i).getBaseStats().getHealthPoint() > 0) || !(isAliveOnly)) {
                // Moster is alive, or not isAliveOnly
                System.out.printf(
                "   " + (i+1) + ". " + this.monsters.get(i).getNama() 
                + " (" + this.monsters.get(i).getBaseStats().getHealthPoint() 
                + "/" + this.monsters.get(i).getBaseStats().getMaxHealthPoint() + ")\n");
            }
        }
    }
}
