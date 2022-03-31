package com.monstersaku;

import com.monstersaku.util.CSVReader;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ArrayList;
import java.lang.System;
import java.util.Scanner;

public class Main {
    // KAMUS
    // Base statics
    private static final List<String> CSV_FILE_PATHS = Collections.unmodifiableList(Arrays.asList(
            "configs/monsterpool.csv",
            "configs/movepool.csv",
            "configs/element-type-effectivity-chart.csv"));
    
    // New statics
    private static final void printActions() {
        System.out.println(
            "Select action!\n" +
            "> Move\n" +
            "> Switch\n" +
            "> View Monsters Info\n" +
            "> View Game Info\n" +
            "> Help"
        );
        System.out.print("\n>> ");
    }

    // ALGORIITMA PROGRAM UTAMA
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
        Player arrayPlayer[] = new Player[2];
        //...

        // For each player, choose one random currentMonster
        //...

        // Game Begins! Loops
        boolean isGameEnd = false;
        while (!isGameEnd) {
            // A turn starts
            ArrayList<Object> listActs = new ArrayList<Object>();
            for (int i = 0; i <= 1; i++) {
                // arrayPlayer[i]'s turn. Loop scanner
                boolean isTurnForPlayer = true;
                while (isTurnForPlayer) {
                    Scanner scanner2 = new Scanner(System.in);
                    try {
                            // Show menu
                            printActions();

                            // Get arrayPlayer[i]'s input
                            Monster currentMonster = arrayPlayer[i].getCurrentMonster();
                            switch ((scanner2.next()).toLowerCase()) {
                                case "move" :
                                    boolean isInputValid = true;
                                    int idxNum;
                                    while (isInputValid) {
                                        try {
                                            currentMonster.printMoves();
                                            idxNum = scanner2.nextInt() - 1;
                                            if (idxNum == 0) {
                                                // Cancel
                                                System.out.println("Returning to your turn...");
                                                Thread.sleep(1000);
                                                isInputValid = true;
                                            }
                                            else {
                                                // Adds selected move to action list
                                                listActs.add(currentMonster.getNumthMove(idxNum));
                                                isInputValid = false;
                                                isTurnForPlayer = false;
                                            }
                                        }
                                        catch (InputMismatchException e) {
                                            System.out.println("......");
                                            Thread.sleep(1000);
                                            System.out.println("ERROR. Enter a number! (e.g. '1')");
                                            Thread.sleep(1000);
                                        }
                                        catch (IndexOutOfBoundsException e) {
                                            System.out.println("......");
                                            Thread.sleep(1000);
                                            System.out.println("ERROR. Enter a valid number! (e.g. '1')");
                                            Thread.sleep(1000);
                                        }
                                    }
                                    break;

                                case "switch" :
                                    //
                                    isTurnForPlayer = false;
                                    break;

                                case "view monsters info" :
                                    //
                                    break;

                                case "view game info" :
                                    //
                                    break;

                                case "help" :
                                    //
                                    break;

                                default :
                                    // throws exception
                                    break;
                            }
                        }
                    catch (Exception e) {
                        // Ideally should be a specific exception
                        
                    }
                    finally {
                        scanner2.close();
                    }
                }


                
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
