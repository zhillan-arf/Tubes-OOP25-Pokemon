package com.monstersaku;

import com.monstersaku.util.CSVReader;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Main {
    // Base statics
    private static final List<String> CSV_FILE_PATHS = Collections.unmodifiableList(Arrays.asList(
            "configs/monsterpool.csv",
            "configs/movepool.csv",
            "configs/element-type-effectivity-chart.csv"));
    
    // New statics
    private static void showMenuSelection2() {
        // Menu to display AFTER the game begin
        // Move, Switch, View Monsters Info, View Game Info, Help
    }

    // Main
    public static void main(String[] args) {
        for (String fileName : CSV_FILE_PATHS) {
            // [zh]: This section reach each file in CSV_FILE_PATHS and print them to the terminal. 
            try {
                System.out.printf("Filename: %s\n", fileName);
                CSVReader reader = new CSVReader(new File(Main.class.getResource(fileName).toURI()), ";");
                reader.setSkipHeader(true);
                List<String[]> lines = reader.read();
                System.out.println("=========== CONTENT START ===========");
                for (String[] line : lines) {
                    for (String word : line) {
                        System.out.printf("%s ", word);
                    }
                    System.out.println();
                }
                System.out.println("=========== CONTENT END ===========");
                System.out.println();
            } catch (Exception e) {
                System.out.println("\nAn error occured while trying to read CSV file. :sadge:.");
            }
        }
        // [zh]: end of file reading section. Now, begin tubes.

        /** [zh] Initiate game (this is not an ordered thing, more like a to-do-list)
         * 1. Instantiate two player objects. 
         * 2. re-Read the CSV files. Create a code (how? idk for now) to pick 6 random monsters, 
         *    instantiate them, and assign them to each player. Use the above as a reference on how to
         *    read CSV files.
         * 3. Put the two players in an array called arrayPlayers. (Note that index starts from 0)
         * 4. Display an asethetic GUI and provide a scanner to receive player's choices (Help, Exit)
         * 5. etc
         */

        // Game Initialization
        ArrayList arrayPlayer = new ArrayList<>();  // [zh]: Idk how to fill the parameters or set up generics
        //...

        // For each player, choose one random currentMonster

        // Game Begins!
        boolean isGameEnd = false;

        // Loop
        while (!isGameEnd) {
            // Players makes moves
            for (int i = 0; i <= 1; i++) {
                showMenuSelection2();
                // Get player[i]'s response
                // Switch case for each
                    // Move
                    // 1. Show available moves
                    // 2. Receive player response
                    // 2. Calculate and implement effects
                    // Switch
                    // Other commands
                // switch Players
            }
            // Effects are calculated
            
        }
    }
}
