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

import javax.swing.event.TableColumnModelListener;

public class Main {
    // KAMUS
    // Base statics
    private static final List<String> CSV_FILE_PATHS = Collections.unmodifiableList(Arrays.asList(
            "configs/monsterpool.csv",
            "configs/movepool.csv",
            "configs/element-type-effectivity-chart.csv"));
    
    // New statics
    
    //[mzh] Melakukan print pilihan command di main menu
    private static final void printMainMenuCommands() {
        System.out.println(
        		"Select Command!\n" +
        		"> Start Game\n" +
        		"> Help\n" +
        		"> Exit"
        		);
    }
    
    private static final void printActions() {
        // Used in turns
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
    
    
    // [mzh]: Melakukan print logo utama
    public static final void printArt() {
    	System.out.println("=============================================================================");
    	System.out.println("");
    	System.out.println(" ____    ____  ______  ____  _____   _______ _________ _________ ________    ");
    	System.out.println("|_   \\  /   _|/      \\ |_   \\|_   _|/  ___  |  _   _  |_   ___  |_   __  \\   ");
    	System.out.println("  |   \\/   | /  /--\\  \\ |   \\ | |  |  (___\\_|_/ | | \\_| | |_  \\_| | |__)  |  ");
    	System.out.println("  | |\\  /| | | |    | | | |\\ \\| |   \\____  \\    | |     |  _|  _  |  __  /   ");
    	System.out.println(" _| |_\\/_| |_\\  \\--/  /_| |_\\   |_|| \\___)  |  _| |_   _| |___/ |_| |  \\ \\_ ");
    	System.out.println("|_____||_____|\\______/|_____|\\____||_______/  |_____| |_________|____| |___|");
    	System.out.println("                  _______      __      ___  ____  _____  _____ ");
    	System.out.println("                 /  ___  |    /  \\    |_  ||_  _||_   _||_   _|");
    	System.out.println("                |  (__ \\_|   / /\\ \\     | |_/ /    | |    | |  ");
    	System.out.println("                 \\____  \\   / ____ \\    |  __ \\    | |    | |  ");
    	System.out.println("                |\\____) | _/ /    \\ \\_ _| |  \\ \\_   \\ \\__/ /   ");
    	System.out.println("                |_______/|____|  |____|____||____|   \\____/    ");
    	System.out.println("");
    	System.out.println("=============================================================================\n");
    }
    
    // [mzh]: Melakukan print pesan Help
    public static void printHelp() {
    	System.out.println(
    			"\n[Game Description]\n"
    			+ "Monster Saku is a pokemon battle adaptation on a CLI style\n"
    			+ "It covers the duel battle system with 6 random starting monsters\n"
    			+ "on each player at the start.\n"
    			+ "\n"
    			+ "[How to Start?]\n"
    			+ "At the main menu, enter\n"
    			+ "\"Start Game\"\n"
    			+ "as input to start playing.\n"
    			+ "\n"
    			+ "[How to Play?]\n"
    			+ "Each player are given 6 random monsters at the start of the game.\n"
    			+ "Players will be given turns to either use a move or switch monster.\n"
    			+ "Your goal is to bring your opponent's monster HP to 0.\n"
    			+ "Exploit their weakness to deal higher damage.\n"
    			+ "Switch your monster to avoid getting hit.\n"
    			+ "Use the best strategy to win the game.\n"
    			);
    }
    

    private static final void fatalError() {
        System.out.println("FATAL ERROR. Something went TERRIBLY wrong...");
        System.exit(1);
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

       
        // Print ASCII Art
        try {
        	printArt();
			Thread.sleep(1000);
		} 
        catch (InterruptedException e) {
		}
        
        //Main Menu
        printMainMenuCommands();

        Scanner scanner1 = new Scanner(System.in);
        boolean startGame = false;
        while (!startGame) {
        	String commandMainMenu =  scanner1.nextLine();
        	if (commandMainMenu.equals("Help")) {
        		printHelp();
        		printMainMenuCommands();
        	}
        	else if (commandMainMenu.equals("Exit")) {
        		System.exit(-1);
        	}
        	else if (commandMainMenu.equals("Start Game")) {
        		startGame = true;
        	}
        	else {
        		System.out.println("Invalid command");
        		printMainMenuCommands();
        	}
        	
        }
        scanner1.close();
        
        // Game Initialization
        Player arrayPlayer[] = new Player[2];
        //...?

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
                        // Show menu
                        printActions();

                        // Get arrayPlayer[i]'s input
                        Monster currentMonster = arrayPlayer[i].getCurrentMonster();
                        boolean isInputValid = false;
                        int idxNum;
                        switch ((scanner2.next()).toLowerCase()) {
                            case "move" :
                                while (!isInputValid) {
                                    try {
                                        currentMonster.printMoves();
                                        idxNum = scanner2.nextInt() - 1;
                                        // If input isnt a number, throw InputMismatchException
                                        // If not, proceeds
                                        if (idxNum == 0) {
                                            // 0 = cancel
                                            try {
                                                System.out.println("Returning to your turn...");
                                                Thread.sleep(1000);
                                                isInputValid = true;
                                            }
                                            catch (InterruptedException sleepE) {
                                                fatalError();
                                            }
                                            
                                        }
                                        else {
                                            // Adds selected move to action list
                                            listActs.add(currentMonster.getNumthMove(idxNum));
                                            // If idxNum out of bounds, throw IndexOutOfBoundsException
                                            // if not, proceeds
                                            isInputValid = false;
                                            isTurnForPlayer = false;
                                        }
                                    }
                                    catch (InputMismatchException e) {
                                        try {
                                            System.out.println("......");
                                            Thread.sleep(1000);
                                            System.out.println("ERROR. Enter a number! (e.g. '1')");
                                            Thread.sleep(1000);
                                            // Return to input idxNum
                                        }
                                        catch (InterruptedException sleepE) {
                                            fatalError();
                                        }
                                    }
                                    catch (IndexOutOfBoundsException e) {
                                        try {
                                            System.out.println("......");
                                            Thread.sleep(1000);
                                            System.out.println("ERROR. Enter a *valid* number! (e.g. '1')");
                                            Thread.sleep(1000);
                                            // Return to input idxNum
                                        }
                                        catch (InterruptedException sleepE) {
                                            fatalError();
                                        }
                                    }
                                }
                                // End of loop, caancelled or move inputted
                                break;

                            case "switch" :
                                while (!isInputValid) {
                                    try {
                                        currentMonster.printMoves();
                                        idxNum = scanner2.nextInt() - 1;
                                        // If input isnt a number, throw InputMismatchException
                                        // If not, proceeds
                                        if (idxNum == 0) {
                                            // 0 = cancel
                                            try {
                                                System.out.println("Returning to your turn...");
                                                Thread.sleep(1000);
                                                isInputValid = true;
                                            }
                                            catch (InterruptedException sleepE) {
                                                fatalError();
                                            }
                                            
                                        }
                                        else {
                                            // Adds selected move to action list
                                            listActs.add(currentMonster.getNumthMove(idxNum));
                                            // If idxNum out of bounds, throw IndexOutOfBoundsException
                                            // if not, proceeds
                                            isInputValid = false;
                                            isTurnForPlayer = false;
                                        }
                                    }
                                    catch (InputMismatchException e) {
                                        try {
                                            System.out.println("......");
                                            Thread.sleep(1000);
                                            System.out.println("ERROR. Enter a number! (e.g. '1')");
                                            Thread.sleep(1000);
                                            // Return to input idxNum
                                        }
                                        catch (InterruptedException sleepE) {
                                            fatalError();
                                        }
                                    }
                                    catch (IndexOutOfBoundsException e) {
                                        try {
                                            System.out.println("......");
                                            Thread.sleep(1000);
                                            System.out.println("ERROR. Enter a *valid* number! (e.g. '1')");
                                            Thread.sleep(1000);
                                            // Return to input idxNum
                                        }
                                        catch (InterruptedException sleepE) {
                                            fatalError();
                                        }
                                    }
                                }
                                // End of loop, caancelled or move inputted
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
                    scanner2.close();
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
