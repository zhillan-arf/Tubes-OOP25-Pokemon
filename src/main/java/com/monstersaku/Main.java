package com.monstersaku;

import com.monstersaku.util.CSVReader;

import java.io.File;
import java.util.*;

/**
 * TUBES OOP K2 KELOMPOK 25 "JANDA BERKANTONG"
 * 18219062 Rahmat Pujiatno
 * 18220008 Zhillan Attarizal Rezyarifin
 * 18220034 Muhammad Zhaffran Haris
 * 182200102 Danendra Gilang Raharjo
 */

public class Main {
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
            }
        }
        // [zh]: End of file reading section. Now, begin tubes.
       
        // Print ASCII Art
        printArt();
        delay(1000);
        
        // Main Menu
        Scanner scanner = new Scanner(System.in);
        boolean isStartGame = false;
        while (!isStartGame) {
            printMainMenuCommands();
            System.out.print(">> ");
        	String commandMainMenu = scanner.nextLine();
        	if (commandMainMenu.equals("Help")) {
        		printHelp();
        		printMainMenuCommands();
        	}
        	else if (commandMainMenu.equals("Exit")) {
                System.out.println("Alright, bye-onara!");
        		System.exit(0);
        	}
        	else if (commandMainMenu.equals("Start Game")) { isStartGame = true;}
        	else {System.out.println("ERROR. Unrecognized command!\n");}
        	
        }
        //scanner.close();

        // Parse and store all file datas
        System.out.println("Loading files...");
        List<Move> movePool = com.monstersaku.util.MovePoolConfig.create();
        List<Monster> monsterPool = com.monstersaku.util.MonsterPoolConfig.create(movePool);
        delay(1000);
        System.out.println("monsterpool.csv loaded...");
        
        // Instantiate two players and their content
        Player arrayPlayers[] = new Player[2];
        for (int i = 0; i <= 1; i++) {
            // Get name
            System.out.printf("Enter Player %d's name: \n>> ", i + 1);
            String playerName = scanner.nextLine();

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
                    //Scanner scanner = new Scanner(System.in);
                    // Show menu
                    printActions();

                    // Get arrayPlayer[i]'s input
                    Monster currentMonster = arrayPlayers[i].getCurrentMonster();
                    boolean isInputValid = false;
                    int num;
                    switch ((scanner.next()).toLowerCase()) {
                        case "1" :
                            while (!isInputValid) {
                                try {
                                    currentMonster.printMonsterMoves();
                                    System.out.println("0. Cancel");
                                    System.out.print("\n> ");
                                    num = scanner.nextInt() - 1;
                                    // If input isnt a number, throw InputMismatchException
                                    // If not, proceeds
                                    if (num == 0) {
                                        // 0 = cancel
                                        System.out.println("Returning to your turn...");
                                        delay(1000);
                                        isInputValid = true;
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
                                catch (InputMismatchException e) { WarnInputMismatch();} // Return to input num
                                catch (IndexOutOfBoundsException e) {WarnIndexOutOfBounds();} // Return to input num
                            }
                            // End of loop, caancelled or move inputted
                            break;

                        case "2" :
                            while (!isInputValid) {
                                try {
                                    arrayPlayers[i].printAliveMonsters();
                                    System.out.println("0. Cancel");
                                    num = scanner.nextInt();
                                    // If input isnt a number, throw InputMismatchException
                                    // If not, proceeds
                                    if (num == 0) {
                                        // 0 = cancel
                                        System.out.println("Returning to your turn...");
                                        delay(1000);
                                        isInputValid = true;
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
                                catch (InputMismatchException e) { WarnInputMismatch();} // Return to input num
                                catch (IndexOutOfBoundsException e) {WarnIndexOutOfBounds();} // Return to input num
                            }
                            // End of loop, cancelled or move inputted
                            break;

                        case "3" :
                            int idx;
                            while (!isInputValid) {
                                try {
                                    printPlayers(arrayPlayers);
                                    System.out.print("> ");
                                    idx = scanner.nextInt() - 1;
                                    if (idx == 0) {
                                        // 0 = back
                                        System.out.println("Returning to your turn...");
                                        delay(1000);
                                        isInputValid = true;
                                    }
                                    else {
                                        System.out.print("Retrieving data...");
                                        delay(500);
                                        boolean hasSelectedMonster = false;
                                        int numM;
                                        while (!hasSelectedMonster) {
                                            System.out.printf("What will %s do?\n", arrayPlayers[i].getCurrentMonster().getNama());
                                            System.out.println("Select monster number: ");
                                            arrayPlayers[idx].printMonsters();
                                            numM = scanner.nextInt();
                                            if (numM == 0) {
                                                // 0 == back
                                                System.out.println("Returning to players...");
                                                delay(1000);
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
                                                    int numV = scanner.nextInt();
                                                    switch (numV) {
                                                        case 1 :
                                                            isViewInputValid = true;
                                                            hasSelectedMonster = true;
                                                            System.out.println("Returning to monster selection...");
                                                            delay(1000);
                                                            break;
                                                        case 2 :
                                                            isViewInputValid = true;
                                                            hasSelectedMonster = true;
                                                            isInputValid = true;
                                                            System.out.println("Returning to your turn...");
                                                            delay(1000);
                                                            break;
                                                        default :
                                                            System.out.println("Error. Enter a *valid* number!\n");
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                catch (InputMismatchException e) { WarnInputMismatch();} // Return to input num
                                catch (IndexOutOfBoundsException e) {WarnIndexOutOfBounds();} // Return to input num
                            // End of loop
                            }
                            break;

                        case "4" :
                            printGameInfo(ctrTurn, i, arrayPlayers);
                            delay(1000);
                            System.out.println("Returning to your turn...");
                            delay(1000);
                            break;

                        case "5" :
                            printHelpTurn();
                            delay(2000);
                            System.out.println("Returning to your turn...");
                            delay(1000);
                            break;
                        default :
                            System.out.print("ERROR. Unrecognized action number!\n");
                            delay(500);
                            break;
                    }
                    //scanner.close();
                }
                
            }
            // End of action givings loop

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
                if (move0.getPriority() > move1.getPriority()) {} // Current order: P0, P1
                else if (move0.getPriority() < move1.getPriority()) {arrOrder = P1First;} // Current order: P1, P0
                else {
                    // Same priority. Excutes based on speed
                    double speed0 = arrayPlayers[0].getCurrentMonster().getModifiedSPeed();
                    double speed1 = arrayPlayers[1].getCurrentMonster().getModifiedSPeed();
                    if (speed0 > speed1) {} // Current order: P0, P1
                    else if(speed0 < speed1) {arrOrder = P1First;} // Current order: P1, P0
                    else {
                        // Same speed. Executes randomly
                        Random random1 = new Random();
                        if (random1.nextInt(10) <= random1.nextInt(10)) {} // Current order: P0, P1
                        else {arrOrder = P1First;} // Current order: P1, P0
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
                Monster currentPMonster = arrayPlayers[i].getCurrentMonster();
                Monster currentTMonster = arrayPlayers[targetIdx].getCurrentMonster();

                // Depend on type of listAct content
                if (listActs.get(i) instanceof Monster) {
                    // Switch monster
                    currentPMonster.getSB().setSB(0, 0, 0, 0, 0);
                    System.out.printf("%s, good job! Get back!\n", 
                        currentPMonster.getNama());
                    Monster monster = (Monster) listActs.get(i);
                    arrayPlayers[i].setCurrentMonster(monster);
                    System.out.printf("PKMN Trainer %s sent out %S!\n",  arrayPlayers[i].getPlayerName(), 
                        monster.getNama());}
                else {
                    // Execute move upon the other player
                    Random random1 = new Random();
                    int paralyzeVal = random1.nextInt(5);
                    if ((currentPMonster.getStatusCondition() == StatusCondition.PARALYZE) && (paralyzeVal == 0)) {
                        System.out.printf("%s is PARALYZED! Failed to move...\n", currentPMonster.getNama());
                    }
                    else {
                        Move move = (Move) listActs.get(i);
                        System.out.printf("%s's %s used %s!\n", arrayPlayers[i].getPlayerName(), 
                            currentPMonster.getNama(), move.getName());
                        move.executeMove(currentPMonster, currentTMonster);
                        delay(1000);
                        if (!currentPMonster.isMonsterAlive()) {
                            System.out.printf("%s fainted!\n", currentPMonster.getNama());
                        }
                        // Did we win?
                        if (!arrayPlayers[targetIdx].hasAliveMonsters()) {
                            isGameEnd = true;
                            playerWin = arrayPlayers[i];
                            playerLost = arrayPlayers[targetIdx];
                            break;  // break the loop, even if the other player haven't acted
                        }
                        // Print fainted message if currentPMonster KO
                        System.out.printf("%s's %s fainted!\n", 
                            arrayPlayers[i].getPlayerName(), 
                            currentPMonster.getNama());
                    }
                }
                // Moves has been executed
                    
                // PHASE 2B: calculate after damages
                // Damages are inflicted on ALL of opposing player's monsters
                for (Monster monster : arrayPlayers[targetIdx].getListMonster()) {
                    if (monster.isMonsterAlive()) {
                        StatusCondition statCon = monster.getStatusCondition();
                        if (statCon == StatusCondition.BURN) {
                            System.out.printf("%s suffered BURN!\n", monster.getNama());
                            monster.damage(1/8);
                        }
                        else if (statCon == StatusCondition.POISON) {
                            System.out.printf("%s suffered POISON!\n", monster.getNama());
                            monster.damage(1/16);
                        }
                        else if (statCon == StatusCondition.SLEEP) {monster.reduceSleepDuration();}
                        // Display if KO
                        if (!monster.isMonsterAlive()) {
                            delay(1000);
                            System.out.printf("%s fainted!\n", monster.getNama());
                            delay(1000);
                        }
                    }       
                }
                // Did we win?
                if (!arrayPlayers[targetIdx].hasAliveMonsters()) {
                    isGameEnd = true;
                    playerWin = arrayPlayers[i];
                    playerLost = arrayPlayers[targetIdx];
                    break;  // break the loop, even if the other player haven't acted
                }

                // Phase 2C: If targetMonster became K-O'd after attacks, 
                // but still hasn't lost
                if (!currentTMonster.isMonsterAlive()) {
                    // Reset various statuses
                    currentTMonster.setStatusCondition(StatusCondition.NONE);
                    currentTMonster.getSB().setSB(0, 0, 0, 0, 0);
                    // Input a new current monster
                    //Scanner scanner = new Scanner(System.in);
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
                             * it will also throw IndexOutOfBoundsException
                             */
                            int num = scanner.nextInt();
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
                        catch (InputMismatchException e) {WarnInputMismatch();}
                        catch (IndexOutOfBoundsException e) {WarnIndexOutOfBounds();}
                        finally {scanner.close();}
                    }
                    // Inputting loop ends
                    // if the targetMonster is KO, all its actions are cancelled. break
                    break; 
                }
                // else targetMonster is alive
                // Go to next player, if possible
            }
            // Turn ends
            System.out.println("Starting the next turn...");
            delay(100);
        }
        // Game has ended

        System.out.printf("%s has defeated %s!\n", playerWin.getPlayerName(), playerLost.getPlayerName());
        System.out.println("Thank you for playing!");

    }
    // End of main



    // STATICS
    private static final List<String> CSV_FILE_PATHS = Collections.unmodifiableList(Arrays.asList(
            "configs/monsterpool.csv",
            "configs/movepool.csv",
            "configs/element-type-effectivity-chart.csv"));
    
    // Melakukan print pilihan command di main menu
    private static final void printMainMenuCommands() {
        System.out.println(
        		"Select Command!\n" +
        		"> Start Game\n" +
        		"> Help\n" +
        		"> Exit"
        		);
    }
    
    private static void delay(int milis) {
        try {
            Thread.sleep(milis);
        }
        catch (InterruptedException e) {
            System.out.println("FATAL INTERRUPTION ERROR. Something went TERRIBLY wrong...\n");
            System.exit(2);
        }
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
    
    // Print logo utama
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
    
    // Print pesan Help
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

    private static void WarnInputMismatch() {
        System.out.println("......");
        delay(1000);
        System.out.println("ERROR. Enter a number! (e.g. '1')");
        delay(1000);
    }

    private static void WarnIndexOutOfBounds() {
        System.out.println("......");
        delay(1000);
        System.out.println("ERROR. Enter a *valid* number! (e.g. '1')\n");
        delay(1000);
    }
}
