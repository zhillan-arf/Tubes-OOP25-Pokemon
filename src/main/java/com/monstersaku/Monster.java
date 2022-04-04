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
    public Monster(int id, String nama, ElementType elementTypes, Stats baseStats){
        this.id = id;
        this.nama = nama;
        this.elementTypes = elementType;
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
              0. Cancel
         */
        System.out.println(this.nama + " move list: ");
        for (int i = 0; i < this.getMove().size(); i++){
            System.out.println((i+1) + ". " + this.moves.get(i).name + "(Elmt. " + this.moves.get(i).elementType + ", Amm. " + this.moves.get(i).ammunition + ")");
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

    public List<lementType> getElement(){
        return this.elementTypes;
    }

    public void setStats(Stats baseStats){
        this.baseStats = baseStats
    }

    public void setMove(Move moves){
        this.moves = moves
    }

    public void addMoves(Move move){
        this.moves.add(move);
    }

    public list<moves> getMoves(){
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
        if (status = StatusCondition.SLEEP){
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
        System.out.printf("Element: "));
        for (ElementType e : this.elementTypes){
            System.out.printf(e + " ");
        }
        System.out.println("");
        System.out.println("HP: " + this.baseStats.getHealthPoint());
        System.out.println("Attack: " + this.baseStats.getAttack());
        System.out.println("Defense: " + this.baseStats.getDefense());
        System.out.println("Special Attack: " + this.baseStats.getSpecialAttack());
        System.out.println("Special Defense: " + this.baseStats.getSpecialDefense());
        System.out.println("Speed: " + this.baseStats.getSpeed());
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
}
