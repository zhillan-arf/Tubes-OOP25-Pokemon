package com.monstersaku;
import java.util.List;

public class Monster {
    // Attributes
    private static final int MAX_MOVES_NUM = 4;  // According to Bulbapedia
    private String nama;
    List<ElementType> elementTypes;
    Stats beaseStats;
    List<Move> moves;

    // Konstruktor

    // Methods
    public void printMoves() {
        // Print all of the monster's moves to terminal, 
        // numbered from 1...moves.size(), and an option to 
        // cancel for 0

    }
    public Move getNumthMove(int num) {
        // Get the move at index idx [1...MAX_MOVES_NUM]
        // Takes num, NOT index(index starts from 0)
        return moves.get(num - 1);
    }
    public int getMovesSize() {
        // Get moves size
        return moves.size();
    }
}
