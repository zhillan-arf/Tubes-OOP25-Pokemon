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
    // "KAMUS"
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
            "Select action number!\n" +
            "1. Move\n" +
            "2. Switch\n" +
            "3. View Monsters Info\n" +
            "4. View Game Info\n" +
            "5. Help"
        );
        System.out.print("\n> ");
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

    public static void printHelpTurn() {
        // Describes available options during the turn
        System.out.println(
              "Move   : an action for your Pokemon to attack opposing Pokemon, heal,\n"
            + "        etc. Your Pokemon cannot move if it has the buff 'SLEEP'!"
            + "Switch : replace your current pokemon with another one. You cannot switch\n"
            + "        a knocked-out pokemon!"
            + "View Monster Info : select a monster and view its stats and moves"
            + "View Game Info : print out current game status, as the name suggests"
            + "Help   : you are using that command right now."
            );
    }
    
    private static void fatalError() {
        // Primarily used if there is an interruption in, say, sleep()
        // Interruptions aren't supposed to happen
        System.out.println("FATAL ERROR. Something went TERRIBLY wrong...\n");
        System.exit(1);
    }

    private static void printPlayers(Player[] arrayPlayers) {
        // Print players
        System.out.println("Select player number: ");
        for (int i = 0; i <= 1; i++) {
            System.out.printf("%d. %s\n", i, arrayPlayers[i].getPlayerName());
            System.out.println("0. Cancel");
        }
    } 

    private static void printGameInfo(int turn, int i, Player arrayPlayers[]) {
        // Print info about the turn
        System.out.printf("******** TURN %d ********\n", turn);
        System.out.printf("NOW PLAYING: %s\n", arrayPlayers[i].getPlayerName());
        System.out.println("Selected Monster:");
        arrayPlayers[i].getCurrentMonster().printMonsterAttr();
        System.out.println("Availabke Monsters:");
        arrayPlayers[i].printMonsters();
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
                fatalError();
            }
        }
        // [zh]: end of file reading section. Now, begin tubes.
       
        // Print ASCII Art
        try {
        	printArt();
			Thread.sleep(1000);
		} 
        catch (InterruptedException e) {
            fatalError();
		}
        
        //Main Menu
        Scanner scanner1 = new Scanner(System.in);
        boolean isStartGame = false;
        while (!isStartGame) {
            printMainMenuCommands();
        	String commandMainMenu =  scanner1.nextLine();
        	if (commandMainMenu.equals("Help")) {
        		printHelp();
        		printMainMenuCommands();
        	}
        	else if (commandMainMenu.equals("Exit")) {
                System.out.println("Alright, bye-onara!");
        		System.exit(-1);
        	}
        	else if (commandMainMenu.equals("Start Game")) {
        		isStartGame = true;
        	}
        	else {
        		System.out.println("ERROR. Unrecognized command!\n");
        	}
        	
        }
        scanner1.close();
        
        // Game Initialization
        /** [zh] How to initiate game (this is not an ordered thing, more like a to-do-list)
         * 1. Instantiate two player objects (check out the attributes). Give them name, using:
         *      a. User input (watch out for space char! If we enter "Shuuya Kano" using Scanner object,
         *         it will only receive "Shuuya" and pass "Kano" to the next functions, causing 
         *         disaster). Or
         *      b. If it's too hard, then just use PLAYER1 and PLAYER2
         * 2. re-Read the CSV files. Create a code (how? idk for now) to pick 6 random monsters, 
         *    instantiate them, and assign them to each player. There's an example on CSV management
         *    on the tubes template.
         * 3. Put the two players in an array called arrayPlayers. (Note that index starts from 0)
         * 4. Display an asethetic GUI and provide a scanner to receive player's choices (Help, Exit)
         * 5. If this part uses a scanner, don't forget to close it
         */
        //...?
        Player arrayPlayers[] = new Player[2];
        //...?

        // For each player, assign one RANDOM currentMonster
        //...
        
        // Game Begins! Loops
        boolean isGameEnd = false;
        int ctrTurn = 0;
        while (!isGameEnd) {
            // A turn starts
            ctrTurn++;
            ArrayList<Object> listActs = new ArrayList<Object>();
            for (int i = 0; i <= 1; i++) {
                // arrayPlayer[i]'s turn. Loop scanner
                boolean isTurnForPlayer = true;
                while (isTurnForPlayer) {
                    Scanner scanner2 = new Scanner(System.in);
                    // Show menu
                    printActions();

                    // Get arrayPlayer[i]'s input
                    Monster currentMonster = arrayPlayers[i].getCurrentMonster();
                    boolean isInputValid = false;
                    int num;
                    switch ((scanner2.next()).toLowerCase()) {
                        case "1" :
                            while (!isInputValid) {
                                try {
                                    currentMonster.printMonsterMoves();
                                    System.out.println("0. Cancel");
                                    System.out.print("\n> ");
                                    num = scanner2.nextInt() - 1;
                                    // If input isnt a number, throw InputMismatchException
                                    // If not, proceeds
                                    if (num == 0) {
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
                                        listActs.add(currentMonster.getNumthMove(num));
                                        // If num out of bounds, throw IndexOutOfBoundsException
                                        // if not, proceeds
                                        isInputValid = false;
                                        isTurnForPlayer = false;
                                        isTurnForPlayer = false;
                                    }
                                }
                                catch (InputMismatchException e) {
                                    try {
                                        System.out.println("......");
                                        Thread.sleep(1000);
                                        System.out.println("ERROR. Enter a number! (e.g. '1')");
                                        Thread.sleep(1000);
                                        // Return to input num
                                    }
                                    catch (InterruptedException sleepE) {
                                        fatalError();
                                    }
                                }
                                catch (IndexOutOfBoundsException e) {
                                    try {
                                        System.out.println("......");
                                        Thread.sleep(1000);
                                        System.out.println("ERROR. Enter a *valid* number! (e.g. '1')\n");
                                        Thread.sleep(1000);
                                        // Return to input num
                                    }
                                    catch (InterruptedException sleepE) {
                                        fatalError();
                                    }
                                }
                            }
                            // End of loop, caancelled or move inputted
                            break;

                        case "2" :
                            while (!isInputValid) {
                                try {
                                    arrayPlayers[i].printMonsters();
                                    System.out.println("0. Cancel");
                                    num = scanner2.nextInt();
                                    // If input isnt a number, throw InputMismatchException
                                    // If not, proceeds
                                    if (num == 0) {
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
                                        listActs.add(arrayPlayers[i].getNumthMonster(num));
                                        // If num out of bounds, throw IndexOutOfBoundsException
                                        // if not, proceeds
                                        isInputValid = false;
                                        isTurnForPlayer = false;
                                    }
                                }
                                catch (InputMismatchException e) {
                                    try {
                                        System.out.println("......");
                                        Thread.sleep(1000);
                                        System.out.println("ERROR. Enter a number! (e.g. '1')\n");
                                        Thread.sleep(1000);
                                        // Return to input num
                                    }
                                    catch (InterruptedException sleepE) {
                                        fatalError();
                                    }
                                }
                                catch (IndexOutOfBoundsException e) {
                                    try {
                                        System.out.println("......");
                                        Thread.sleep(1000);
                                        System.out.println("ERROR. Enter a *valid* number! (e.g. '1')\n");
                                        Thread.sleep(1000);
                                        // Return to input num
                                    }
                                    catch (InterruptedException sleepE) {
                                        fatalError();
                                    }
                                }
                            }
                            // End of loop, caancelled or move inputted
                            break;

                        case "3" :
                            int idx;
                            while (!isInputValid) {
                                try {
                                    printPlayers(arrayPlayers);
                                    System.out.print("> ");
                                    idx = scanner2.nextInt() - 1;
                                    if (idx == 0) {
                                        // 0 = back
                                        System.out.println("Returning to your turn...");
                                        Thread.sleep(1000);
                                        isInputValid = true;
                                    }
                                    else {
                                        System.out.print("Retrieving data...");
                                        Thread.sleep(500);
                                        boolean hasSelectedMonster = false;
                                        int numM;
                                        while (!hasSelectedMonster) {
                                            System.out.println("Select monster number: ");
                                            arrayPlayers[idx].printMonsters();
                                            numM = scanner2.nextInt();
                                            if (numM == 0) {
                                                // 0 == back
                                                System.out.println("Returning to players...");
                                                Thread.sleep(1000);
                                                hasSelectedMonster = true;
                                            }
                                            else {
                                                arrayPlayers[idx].getNumthMonster(numM).printMonsterAttr();
                                                boolean isViewInputValid = false;
                                                while (!isViewInputValid) {
                                                    System.out.println(
                                                        "Enter an option number..."
                                                    + "  1. Go back to monster selection\n"
                                                    + "  2. Go back to turn\n"
                                                    );
                                                    int numV = scanner2.nextInt();
                                                    switch (numV) {
                                                        case 1 :
                                                            isViewInputValid = true;
                                                            hasSelectedMonster = true;
                                                            System.out.println("Returning to monster selection...");
                                                            Thread.sleep(1000);
                                                            break;
                                                        case 2 :
                                                            isViewInputValid = true;
                                                            hasSelectedMonster = true;
                                                            isInputValid = true;
                                                            System.out.println("Returning to your turn...");
                                                            Thread.sleep(1000);
                                                            break;
                                                        default :
                                                            System.out.println("Error. Enter a *valid* number!\n");
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                catch (InterruptedException e) {
                                    fatalError();
                                }
                                catch (InputMismatchException e) {
                                    try {
                                        System.out.println("......");
                                        Thread.sleep(1000);
                                        System.out.println("ERROR. Enter a number! (e.g. '1')");
                                        Thread.sleep(1000);
                                        // Return to input num
                                    }
                                    catch (InterruptedException sleepE) {
                                        fatalError();
                                    }
                                }
                                catch (IndexOutOfBoundsException e) {
                                    try {
                                        System.out.println("......");
                                        Thread.sleep(1000);
                                        System.out.println("ERROR. Enter a *valid* number! (e.g. '1')\n");
                                        Thread.sleep(1000);
                                        // Return to input num
                                    }
                                    catch (InterruptedException sleepE) {
                                        fatalError();
                                    }
                                }
                            }
                            break;

                        case "4" :
                            try {
                                printGameInfo(ctrTurn, i, arrayPlayers);
                                Thread.sleep(1000);
                                System.out.println("Returning to your turn...");
                                Thread.sleep(1000);
                            }
                            catch (InterruptedException e) {
                                fatalError();
                            }
                            break;

                        case "5" :
                            try {
                                printHelpTurn();
                                Thread.sleep(1000);
                                System.out.println("Returning to your turn...");
                                Thread.sleep(1000);
                            }
                            catch (InterruptedException e) {
                                fatalError();
                            }
                            break;

                        default :
                            System.out.print("ERROR. Unrecognized action number!\n");
                            break;
                    }
                    scanner2.close();
                }
                
            }
            // listActs is now filled with two items. Calculate effects. 
            //...         
        }
    }
}
