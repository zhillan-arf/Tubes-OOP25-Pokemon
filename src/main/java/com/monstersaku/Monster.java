package com.monstersaku;
import java.util.List;

public class Monster {
    // Attributes
    private String nama;
    List<ElementType> elementTypes;
    Stats baseStats;
    List<Move> moves;

    // Konstruktor

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
