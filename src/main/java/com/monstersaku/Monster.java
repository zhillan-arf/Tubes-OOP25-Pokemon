package com.monstersaku;
import java.util.*;

public class Monster {
    // Attributes
    private int id;
    private String nama;
    private List<ElementType> elementTypes;
    private Stats baseStats;
    private List<Move> moves;
    private StatusCondition status = StatusCondition.NONE;
    private int sleepDuration = 0;
    private StatsBuff statsBuff;

    // Konstruktor
    public Monster(
        int id, 
        String nama, 
        List<ElementType> elementTypes, 
        Stats baseStats,
        List<Move> moves
    ) {
        this.id = id;
        this.nama = nama;
        this.elementTypes = elementTypes;
        this.baseStats = baseStats;
        this.moves = moves;
        this.statsBuff = new StatsBuff();
    }

    // Getter methods
    public String getNama() {return this.nama;}
    public Stats getBaseStats() {return this.baseStats;}
    public void addElement(ElementType elements) {this.elementTypes.add(elements);}
    public List<ElementType> getElement() {return this.elementTypes;}
    public void addMoves(Move move) {this.moves.add(move);}
    public List<Move> getMoves() {return this.moves;}
    public StatusCondition getStatusCondition() {return this.status;}
    public Move getNumthMove(int num) {return moves.get(num - 1);}
    public int getMovesSize() {return moves.size();}
    public int getSleepDuration() {return this.sleepDuration;}
    public StatsBuff getSB() {return this.statsBuff;}

    // Setter methods
    public void setStatusCondition (StatusCondition status){
        this.status = status;
        if (status == StatusCondition.SLEEP) {
            int min = 1;
            int max = 7;
            int rand = (int)Math.floor(Math.random()*(max-min+1)+min);
            setSleepDuration(rand);
        }
    }
    public void setSleepDuration(int rand) {this.sleepDuration = rand;}
    public void setStats(Stats baseStats) {this.baseStats = baseStats;}
    public void setMove(List<Move> moves) {this.moves = moves;}

    // Other methods
    public void reduceSleepDuration() {
        if (sleepDuration == 0) {
            this.status = StatusCondition.NONE;
            System.out.printf("%s has woken up!", this.nama);
        }
        this.sleepDuration -= 1;
        // Can go negative, which is fine
    }
    public boolean isMonsterAlive() {return baseStats.getHealthPoint() > 0;}
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
            System.out.println (
                "    " + (i+1) + ". " + this.moves.get(i).getName() 
                + " (Elmt. " + this.moves.get(i).getElementType() 
                + ", Amm. " + this.moves.get(i).getAmmunition() + ")");
        }
    }
    public void printMonsterAttr() {
        /**
         * Print all of the monster's attributes
         * Looks like this:
         *  >>> CHARIZARD <<<
            ELEMENT : GRASS, FIRE
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
        System.out.printf(">> %s <<\n", this.nama);
        System.out.printf("Element: ");
        for (ListIterator<ElementType> it = this.elementTypes.listIterator(); it.hasNext(); ) {
            System.out.print(it);
            if (it.hasNext()) {System.out.print(", ");}
        }
        System.out.println("HP      : " + this.baseStats.getHealthPoint());
        System.out.println("ATK     : " + this.baseStats.getAttack());
        System.out.println("DEF     : " + this.baseStats.getDefense());
        System.out.println("SP. ATK : " + this.baseStats.getSpecialAttack());
        System.out.println("SP. DEF : " + this.baseStats.getSpecialDefense());
        System.out.println("SPEED   : " + this.baseStats.getSpeed());
        System.out.println("MOVES   :");
        printMonsterMoves();
    }

    public void damage(double statusMultiplier) {
        double dmg = this.baseStats.getMaxHealthPoint() * statusMultiplier;
        if (dmg > this.baseStats.getHealthPoint()) dmg = this.baseStats.getHealthPoint();
        this.baseStats.setHealthPoint(this.baseStats.getHealthPoint() - dmg);
        System.out.printf("%s receives %d damages!\n", this.nama, dmg);
    }
}
