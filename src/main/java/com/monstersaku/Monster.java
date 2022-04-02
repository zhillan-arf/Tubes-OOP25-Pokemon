package com.monstersaku;
import java.util.List;
import java.util.ArrayList;

public class Monster {
    // Attributes
    private String nama;
    List<ElementType> elementTypes;
    Stats baseStats;
    List<Move> moves;

    // Konstruktor
    public Monster(String nama, Stats baseStats){
        this.nama = nama;
        this.elementTypes = new ArrayList<ElementType>();
        this.baseStats = baseStats;
        this.moves = new ArrayList<Move>();
    }

    // Methods
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

    public void addMoves(Move move){
        this.moves.add(move);
    }

    public list<moves> getMoves(){
        return this.moves;
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
        System.out.println(){
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
