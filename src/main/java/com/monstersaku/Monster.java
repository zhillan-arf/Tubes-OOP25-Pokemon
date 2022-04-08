package com.monstersaku;
import java.util.List;
import java.util.ArrayList;

public class Monster {
    // Attributes
    private int id;
    private String nama;
    private List<ElementType> elementTypes;
    private Stats baseStats;
    private List<Move> moves;
    private StatusCondition status = StatusCondition.NONE;
    private int sleepDuration = 0;

    // Konstruktor
    public Monster(int id, String nama, List<ElementType> elementTypes, Stats baseStats){
        this.id = id;
        this.nama = nama;
        this.elementTypes = elementTypes;
        this.baseStats = baseStats;
        this.moves = new ArrayList<Move>();
    }

    // Methods getter 
    public void printMonsterMoves() {
        /**
         * Print all of the monster's move to terminal
         * Numbered from 1...moves.size() + 0- for cancel
         * Looks like this:
            1. Surf (Elmt. WATER, Amm. 12/15)
            2. Slack Off (Elmt. WATER, Amm. 7/10)
            (Cont...)
         */
        for (int i = 0; i < this.getMoves().size(); i++){
            System.out.println(
                (i+1) + ". " + this.moves.get(i).getName() 
                + " (Elmt. " + this.moves.get(i).getElementType() 
                + ", Amm. " + this.moves.get(i).getAmmunition() + ")");
        }
    }
    public String getNama(){
        return this.nama;
    }

    public Stats getBaseStats(){
        return this.baseStats;
    }

    public void addElement(ElementType elements){
        this.elementTypes.add(elements);
    }

    public List<ElementType> getElement(){
        return this.elementTypes;
    }

    public void setStats(Stats baseStats){
        this.baseStats = baseStats;
    }

    public void setMove(List<Move> moves){
        this.moves = moves;
    }

    public void addMoves(Move move){
        this.moves.add(move);
    }

    public List<Move> getMoves(){
        return this.moves;
    }

    public StatusCondition getStatusCondition(){
        return this.status;
    }

    public void setStatusCondition (StatusCondition status){
        this.status = status;
        int min = 1;
        int max = 7;
        int rand = (int)Math.floor(Math.random()*(max-min+1)+min);
        if (status == StatusCondition.SLEEP){
            setSleepDuration(rand);
        }
    }

    public int getSleepDuration(){
        return this.sleepDuration;

    }
    public void setSleepDuration(int rand){
        this.sleepDuration = rand;
    }

    public void reduceSleepDuration(){
        if (sleepDuration > 0){
            this.sleepDuration -= 1;
        }
        else{
            this.status = StatusCondition.NONE;
        }
    }

    public boolean isMonsterAlive(){
        if (baseStats.getHealthPoint() > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public void printMonsterAttr() {
        /**
         * Print all of the monster's attributes
         * Looks like this:
         *  >>  CHARIZARD
                ELEMENT: NORMAL
                HP      : 106/122
                ATK     : 11
                DEF     : 90
                SP. ATK : 154
                SP. DEF : 90
                SPEED   : 130
                MOVES   :
                    1. Surf (Elmt. WATER, Amm. 12/15)
                    2. Slack Off (Elmt. WATER, Amm. 7/10)
                    ...
         * Of course, MOVES uses printMoves()
         * [zh] If this isn't aesthetic pelase replace with another design
         */
        System.out.println(this.nama);
        System.out.printf("Element: ");
        for (ElementType e : this.elementTypes){
            System.out.printf(e + " ");
        }
        System.out.println("");
        System.out.println("HP      : " + this.baseStats.getHealthPoint());
        System.out.println("ATK     : " + this.baseStats.getAttack());
        System.out.println("DEF     : " + this.baseStats.getDefense());
        System.out.println("SP. ATK : " + this.baseStats.getSpecialAttack());
        System.out.println("SP. DEF : " + this.baseStats.getSpecialDefense());
        System.out.println("SPEED   : " + this.baseStats.getSpeed());
        System.out.println("MOVES   :");
        printMonsterMoves();
    }
    public Move getNumthMove(int num) {
        // Get numth move
        // moves uses an indexing system of [1...moves.size()]
        return moves.get(num - 1);
    }
    public int getMovesSize() {
        // Get moves size
        return moves.size();
    }

    public void damage(double statusMultiplier) {
        double dmg = this.baseStats.getMaxHealthPoint() * statusMultiplier;
        this.baseStats.setHealthPoint(this.baseStats.getHealthPoint() - dmg);
        if (this.baseStats.getHealthPoint() <= 0) this.baseStats.setHealthPoint(0);
    }
}
