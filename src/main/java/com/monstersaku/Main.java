package com.monstersaku;

import com.monstersaku.util.CSVReader;

import java.io.File;
import java.util.*;

/**
 * LOREM IPSUM DOLOR SIT AMET
 * 182...
 * 2022 April
 */

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
        System.out.println("Available Monsters:");
        arrayPlayers[i].printMonsters();
    }

    // ALGORIITMA PROGRAM UTAMA
    public static void main(String[] args) {
        // [zh]: This section reach each file in CSV_FILE_PATHS and print them to the terminal. 
        // After things are done, delete this part.
        for (String fileName : CSV_FILE_PATHS) {
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
                System.out.println(e.getMessage());
                fatalError();
            }
        }
        // [zh]: End of file reading section. Now, begin tubes.
       
        // Print ASCII Art
        try {
        	printArt();
			Thread.sleep(1000);
		} 
        catch (InterruptedException e) {
            fatalError();
		}
        
        // Main Menu
        Scanner scanner0 = new Scanner(System.in);
        boolean isStartGame = false;
        while (!isStartGame) {
            printMainMenuCommands();
        	String commandMainMenu =  scanner0.nextLine();
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
        scanner0.close();

        // Parse and store all file datas
        System.out.println("Loading files...");
        List<Move> movePool = com.monstersaku.util.MovePoolConfig.create();
        List<Monster> monsterPool = com.monstersaku.util.MonsterPoolConfig.create(movePool);
        
        // Instantiate two players and their content
        Player arrayPlayers[] = new Player[2];
        for (int i = 0; i <= 1; i++) {
            // Get name
            System.out.printf("Enter Player %d's name: \n>> ", i + 1);
            Scanner scanner1 = new Scanner(System.in).useDelimiter("\n");
            String playerName = scanner1.next();
            scanner1.close();

            // Instantiate player-i
            arrayPlayers[i] = new Player(playerName, monsterPool);
        }
        
        // Game Begins! Loops
        boolean isGameEnd = false;
        int ctrTurn = 0;
        Player playerWin = arrayPlayers[0];
        Player playerLost = arrayPlayers[0];
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
                                    arrayPlayers[i].printAliveMonsters();
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
                                            scanner2.close();
                                            fatalError();
                                        }
                                    }
                                    else {
                                        /**
                                         * Try add selected Monster to action list
                                         * If monster is K.O. or number out of bounds,
                                         * throw IndexOutOfBoundsException.
                                         * If not, proceeds
                                        */
                                        Monster selectedMonster = arrayPlayers[i].getNumthMonster(num);
                                        if (!selectedMonster.isMonsterAlive()) {
                                            // Monster is K.O.
                                            throw new IndexOutOfBoundsException("Monster is K.O.");
                                        }
                                        else {
                                            // Moster is alive. Switch.
                                            listActs.add(selectedMonster);
                                            isInputValid = false;
                                            isTurnForPlayer = false;
                                        }
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
                                        scanner2.close();
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
                                        scanner2.close();
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
                                            System.out.printf("What will %s do?\n", arrayPlayers[i].getCurrentMonster().getNama());
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
                                    scanner2.close();
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
                                        scanner2.close();
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
                                        scanner2.close();
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
                                scanner2.close();
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
                                scanner2.close();
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
            // End of loop
            // listActs is now filled with two items. Start calculate effects.
            int[] P0First = {0, 1};
            int[] P1First = {1, 0};
            int[] arrOrder = P0First;

            // Phase 1. Determine order based on type -> priority -> speed -> random
            // Current order: P0, P1
            if (!(listActs.get(0) instanceof Monster) && (listActs.get(1) instanceof Monster)) {
                // Switch is executed first
                arrOrder = P1First;
                // Current order: P1, P0
            }
            else if((listActs.get(0) instanceof Move) && (listActs.get(1) instanceof Move)) {
                // Both players chose move; executes based on priority
                Move move0 = (Move) listActs.get(0);
                Move move1 = (Move) listActs.get(1);
                if (move0.getPriority() > move1.getPriority()) {
                    // P0 goes first
                    // Current order: P0, P1
                }
                else if (move0.getPriority() < move1.getPriority()) {
                    // P1 goes first
                    arrOrder = P1First;
                    // Current order: P1, P0
                }
                else {
                    // Same priority. Excutes based on speed
                    double speed0 = arrayPlayers[0].getCurrentMonster().getBaseStats().getSpeed();
                    double speed1 = arrayPlayers[1].getCurrentMonster().getBaseStats().getSpeed();
                    if (speed0 > speed1) {
                        // P0 goes first
                        // Current order: P0, P1
                    }
                    else if(speed0 < speed1) {
                        // P1 goes first
                        arrOrder = P1First;
                        // Current order: P1, P0
                    }
                    else {
                        // Same speed. Executes randomly
                        Random random1 = new Random();
                        if (random1.nextInt(10) <= random1.nextInt(10)) {
                            // P0 goes first
                            // Current order: P0, P1
                        }
                        else {
                            // P1 goes first
                            arrOrder = P1First;
                            // Current order: P1, P0
                        }
                    }
                }
            }
            // else: P0-S P1-M or P0-S P1-S. In both instance, order = P0, P1
            // listAct is now ordered

            // Phase 2a: Executes actions
            for (int i : arrOrder) {
                // Get the i of the other player. Why is this so complex
                int targetIdx;
                if (i == 0) targetIdx = 0;
                else targetIdx = 1;

                // Depend on type of listAct content
                if (listActs.get(i) instanceof Monster) {
                    // Switch monster
                    System.out.printf("%s, good job! Get back!\n", 
                        arrayPlayers[i].getCurrentMonster().getNama());
                    Monster monster = (Monster) listActs.get(i);
                    arrayPlayers[i].setCurrentMonster(monster);
                    System.out.printf("PKMN Trainer %s sent out %S!\n", 
                        arrayPlayers[i].getPlayerName(), 
                        monster.getNama());
                }
                else {
                    // Execute move upon the other player
                    Move move = (Move) listActs.get(i);
                    System.out.printf("%s's %s used %s!\n", 
                        arrayPlayers[i].getPlayerName(), 
                        arrayPlayers[i].getCurrentMonster().getNama(),
                        move.getName());
                    move.executeMove(
                        arrayPlayers[i].getCurrentMonster(),
                        arrayPlayers[targetIdx].getCurrentMonster());
                }
                // Move has been executed
                    
                // Phase 2B: calculate after damages
                Monster targetMonster = arrayPlayers[targetIdx].getCurrentMonster();
                StatusCondition statusCondition = targetMonster.getStatusCondition();
                if (statusCondition == StatusCondition.BURN) {
                    System.out.printf("%s is BURNED!\n", targetMonster.getNama());
                    targetMonster.damage(1/8);
                }
                else if (statusCondition == StatusCondition.POISON) {
                    System.out.printf("%s is POISONED!\n", targetMonster.getNama());
                    targetMonster.damage(1/16);
                }

                // Phase 2C: If targetMonster became K-O'd after attacks
                if (!targetMonster.isMonsterAlive()) {
                    System.out.printf("%s's %s fainted!\n", 
                        arrayPlayers[i].getPlayerName(), 
                        arrayPlayers[i].getCurrentMonster().getNama());
                }

                if (!arrayPlayers[targetIdx].hasAliveMonsters())  {
                    // targetPlayer has lost
                    playerWin = arrayPlayers[i];
                    playerLost = arrayPlayers[targetIdx];
                    // break loop, force end turn, force end game
                    isGameEnd = true;
                    break;
                }
                else if (!targetMonster.isMonsterAlive()) {
                    // targetMonster is KO. targetPlayer chooses new monster
                    Scanner scanner3 = new Scanner(System.in);
                    boolean isKOReplaced = false;
                    while (!isKOReplaced) {
                        try {
                            System.out.println("Select a monster: ");
                            arrayPlayers[targetIdx].printAliveMonsters();
                            System.out.print(">> ");
                            /**
                             * Try replace currentMonster with a new one
                             * If monster of selected num is KO, throw an
                             * IndexOutOfBoundsException
                             * If it tries to access a num larger than the List,
                             * it will also throw the similar
                             */
                            int num = scanner3.nextInt();
                            Monster selectedMonster = arrayPlayers[targetIdx].getNumthMonster(num);
                            if (!selectedMonster.isMonsterAlive()) {
                                // Selected monster is KO (and doesnt appear on terminal)
                                throw new IndexOutOfBoundsException("Selected monster is KO!");
                            }
                            else {
                                arrayPlayers[targetIdx].setCurrentMonster(selectedMonster);
                                isKOReplaced = true;
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
                                scanner3.close();
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
                                scanner3.close();
                                fatalError();
                            }
                        }
                        finally {
                            scanner3.close();
                        }
                        // Loop ends
                    }
                    // Break loop and force go to next turn
                    break;
                }
                // else: turn continues process
            }
            // Turn ends
            System.out.println("Starting the next turn...");
            try{
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                fatalError();
            }
        }
        // Game has ended

        System.out.printf("%s has defeated %s!\n", playerWin.getPlayerName(), playerLost.getPlayerName());
        System.out.println("Thank you for playing!");

    }
}
