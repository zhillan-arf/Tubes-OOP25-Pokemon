package com.monstersaku;

public class Stats {
    // Attributes
    private double healthPoint;
    public final double maxHealthPoint;
    private double attack;
    private double defense;
    private double specialAttack;
    private double specialDefense;
    private double speed;

    // Constructor
    public Stats(double healthPoint, double maxHealthPoint, double attack, double defense, 
    double specialAttack, double specialDefense, double speed){
        this.healthPoint = healthPoint;
        this.maxHealthPoint = maxHealthPoint;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
    }

    // Getter methods
    public double getHealthPoint() {return this.healthPoint;}
    public double getMaxHealthPoint() {return this.maxHealthPoint;}
    public double getAttack() {return this.attack;}
    public double getDefense() {return this.defense;}
    public double getSpecialAttack() {return this.specialAttack;}
    public double getSpecialDefense() {return this.specialDefense;}
    public double getSpeed() {return this.speed;}

    // Setter methods
    public void setHealthPoint(double healthPoint, double maxHealthPoint) {
        if (healthPoint < 0) this.healthPoint = 0;
        else if (healthPoint > maxHealthPoint) this.healthPoint = maxHealthPoint;
        else this.healthPoint = healthPoint;
    }

    public void printStats(){
        System.out.println("Health Point: " + this.healthPoint);
        System.out.println("Attack: " + this.attack);
        System.out.println("Defense: " + this.defense);
        System.out.println("Special Attack: " + this.specialAttack);
        System.out.println("Special Defense: " + this.specialDefense);
        System.out.println("Speed: " + this.speed);
    }
}
