package com.monstersaku;

public class Stats {
    // Attributes
    double healthPoint;
    double maxHealthPoint;
    double attack;
    double defense;
    double specialAttack;
    double specialDefense;
    double speed;

    // Konstruktor
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

    // Method
    public double getHealthPoint(){
        return this.healthPoint;
    }

    public double getMaxHealthPoint(){
        return this.maxHealthPoint;
    }

    public double getAttack(){
        return this.attack;
    }

    public double getDefense(){
        return this.defense;
    }

    public double getSpecialAttack(){
        return this.specialAttack;
    }

    public double getSpecialDefense(){
        return this.specialDefense;
    }

    public double getSpeed(){
        return this.speed;
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
