package com.monstersaku;
import java.io.IOException;
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
        // Config env
        Scanner scanner = new Scanner(System.in);
        boolean isInterfaceInputted = false;
        while (!isInterfaceInputted) {
            System.out.println("Select interface: ");
            System.out.println("> VSCode");
            System.out.println("> Other");
            System.out.print(">> ");
            String interfaceStr = scanner.nextLine().toLowerCase();
            if (interfaceStr.equals("vscode")) {
                isInterfaceInputted = true;
            }
            else if (interfaceStr.equals("other")) {
                isCmd = true;
                isInterfaceInputted = true;
            }
            else {
                System.out.println("\nERROR. Please enter the correct input!\n");
                delay(1000);
            }
        }

        // Print ASCII Art
        clearTerminal();
        printArt();
        delay(3000);
        clearTerminal();
        
        // Main Menu
        boolean isStartGame = false;
        while (!isStartGame) {
            printMainMenuCommands();
            System.out.print(">> ");
        	String commandMainMenu = scanner.nextLine().toLowerCase();
        	if (commandMainMenu.equals("help")) {
        		printHelp();
                delay(2000);
        	}
        	else if (commandMainMenu.equals("exit")) {
                System.out.println("Alright, bye-onara!");
        		System.exit(0);
        	}
        	else if (commandMainMenu.equals("start")) { isStartGame = true;}
        	else {
                System.out.println("ERROR. Unrecognized command!\n");
                delay(500);
            }
        }
        clearTerminal();
        // Parse and store all file datas
        System.out.println("Loading files...");
        List<Move> movePool = com.monstersaku.util.MovePoolConfig.create();
        List<Monster> monsterTemplatePool = com.monstersaku.util.MonsterPoolConfig.create(movePool);
        System.out.println("element-type-effectivity-chart.csv loaded...\n");
        delay(1000);
        clearTerminal();

        // Instantiate two players and instantiate six monsters for each
        Player arrayPlayers[] = new Player[2];
        for (int i = 0; i <= 1; i++) {
            // Get name
            System.out.printf("Enter Player %d's name: \n>> ", i + 1);
            String playerName = scanner.nextLine();

            // Instantiate player-i
            arrayPlayers[i] = new Player(playerName, monsterTemplatePool);
        }
        clearTerminal();
        
        // Game begins!
        boolean isGameEnd = false;
        int ctrTurn = 0;
        Player playerWin = arrayPlayers[0];
        Player playerLost = arrayPlayers[0];
        System.out.print("Initializing game");
        for (int i = 0; i < 3; i++) {
            delay(600);
            System.out.print(".");
        }
        delay(500);
        clearTerminal();
        
        delay(500);
        System.out.printf(">>> %s\n", arrayPlayers[0].getPlayerName().toUpperCase());
        delay(1000);
        System.out.printf("    V.S.\n");
        delay(1000);
        System.out.printf("    %s <<<\n", arrayPlayers[1].getPlayerName().toUpperCase());
        delay(1000);
        System.out.println("\n>>>>> GAME BEGINS!!! <<<<<\n");
        delay(1000);

        System.out.printf("PKMN Trainer %s sent out %S!\n",  
            arrayPlayers[0].getPlayerName(), arrayPlayers[0].getCurrentMonster().getNama());
        delay(1000);
        System.out.printf("PKMN Trainer %s sent out %S!\n\n",  
            arrayPlayers[1].getPlayerName(), arrayPlayers[1].getCurrentMonster().getNama());
        delay(1000);

        while (!isGameEnd) {
            // A turn starts
            ctrTurn++;
            System.out.printf(">> ROUND %d...", ctrTurn);
            delay(500);
            ArrayList<Object> listActs = new ArrayList<Object>();
            for (int i = 0; i <= 1; i++) {
                // arrayPlayer[i]'s turn. Loop scanner
                boolean isTurnForPlayer = true;
                while (isTurnForPlayer) {
                    // Show menu
                    System.out.printf("\n>> Your turn, %s!\n", arrayPlayers[i].getPlayerName());
                    delay(500);
                    printActions();
                    // Get arrayPlayer[i]'s input
                    Monster currentMonster = arrayPlayers[i].getCurrentMonster();
                    boolean isInputValid = false;
                    int num;
                    System.out.print(">> ");
                    switch (scanner.nextLine()) {
                        case "1" :
                            while (!isInputValid) {
                                try {
                                    System.out.printf("Select a move for %s :\n", arrayPlayers[i].getCurrentMonster().getNama());
                                    currentMonster.printMonsterMoves();
                                    System.out.println("       0. Cancel");
                                    System.out.print(">> ");
                                    num = Integer.valueOf(scanner.nextLine());
                                    // If input isnt a number, throw NumberFormatException
                                    // If not, proceeds
                                    if (num == 0) {
                                        // 0 = cancel
                                        System.out.println("Returning to your turn...");
                                        delay(1000);
                                        isInputValid = true;
                                        clearTerminal();
                                    }
                                    else {
                                        // Adds selected move to action list
                                        listActs.add(currentMonster.getNumthMove(num));
                                        // If num out of bounds, throw IndexOutOfBoundsException
                                        // if not, proceeds
                                        isInputValid = true;
                                        isTurnForPlayer = false;
                                    }
                                }
                                catch (NumberFormatException e) { WarnInputMismatch();} // Return to input num
                                catch (IndexOutOfBoundsException e) {WarnIndexOutOfBounds();} // Return to input num
                            }
                            // End of loop, caancelled or move inputted
                            break;

                        case "2" :
                            while (!isInputValid) {
                                try {
                                    System.out.println("Which monster do you want to switch with?");
                                    arrayPlayers[i].printMonsters(true);
                                    System.out.println("   0. Cancel");
                                    System.out.print(">> ");
                                    num = Integer.valueOf(scanner.nextLine());
                                    // If input isnt a number, throw InputMismatchException
                                    // If not, proceeds
                                    if (num == 0) {
                                        // 0 = cancel
                                        System.out.println("\nReturning to your turn...");
                                        delay(1000);
                                        isInputValid = true;
                                        clearTerminal();
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
                                            throw new NumberFormatException("Monster is K.O.");
                                        }
                                        else {
                                            // Moster is alive. Switch.
                                            listActs.add(selectedMonster);
                                            System.out.println("\nReturning to your turn...");
                                            isInputValid = true;
                                            isTurnForPlayer = false;
                                            delay(1000);
                                            clearTerminal();
                                        }
                                    }
                                }
                                catch (NumberFormatException e) { WarnInputMismatch();} // Return to input num
                                catch (IndexOutOfBoundsException e) {WarnIndexOutOfBounds();} // Return to input num
                            }
                            // End of loop, cancelled or move inputted
                            break;

                        case "3" :
                            int numP;
                            while (!isInputValid) {
                                try {
                                    clearTerminal();
                                    System.out.println("Which player do you want to inspect?");
                                    printPlayers(arrayPlayers);
                                    System.out.println("0. Cancel");
                                    System.out.print(">> ");
                                    numP = Integer.valueOf(scanner.nextLine());
                                    if (numP == 0) {
                                        // 0 = back
                                        System.out.println("\nReturning to your turn...");
                                        delay(1000);
                                        isInputValid = true;
                                        clearTerminal();
                                    }
                                    else {
                                        System.out.print("Retrieving data...");
                                        delay(1000);
                                        boolean hasSelectedMonster = false;
                                        int numM;
                                        while (!hasSelectedMonster) {
                                            System.out.println("\nWhich monster do you wish to inspect?");
                                            System.out.println("Select monster number: ");
                                            arrayPlayers[numP - 1].printMonsters(false);
                                            System.out.print(">> ");
                                            delay(1000);
                                            numM = Integer.valueOf(scanner.nextLine());
                                            if (numM == 0) {
                                                // 0 == back
                                                System.out.println("\nReturning to players...");
                                                delay(1000);
                                                hasSelectedMonster = true;
                                            }
                                            else {
                                                arrayPlayers[numP - 1].getNumthMonster(numM).printMonsterAttr();
                                                boolean isViewInputValid = false;
                                                while (!isViewInputValid) {
                                                    System.out.print(
                                                        "Enter an option number...\n"
                                                    + "  1. Go back to monster selection\n"
                                                    + "  2. Go back to turn\n"
                                                    + ">> "
                                                    );
                                                    String numV = scanner.nextLine();
                                                    switch (numV) {
                                                        case "1" :
                                                            isViewInputValid = true;
                                                            System.out.println("Returning to monster selection...");
                                                            delay(1000);
                                                            break;
                                                        case "2" :
                                                            isViewInputValid = true;
                                                            hasSelectedMonster = true;
                                                            isInputValid = true;
                                                            System.out.println("\nReturning to your turn...");
                                                            delay(1000);
                                                            clearTerminal();
                                                            break;
                                                        default :
                                                            System.out.println("Error. Enter a *valid* number!\n");
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                catch (NumberFormatException e) {WarnInputMismatch();} // Return to input num
                                catch (IndexOutOfBoundsException e) {WarnIndexOutOfBounds();} // Return to input num
                            // End of loop
                            }
                            break;

                        case "4" :
                            clearTerminal();
                            printGameInfo(ctrTurn, arrayPlayers[i]);
                            System.out.printf("\nPress ENTER to finish reading... ");
                            String idleStr = scanner.nextLine();
                            System.out.println("Returning to your turn...");
                            clearTerminal();
                            delay(1500);
                            break;

                        case "5" :
                        clearTerminal();
                            printHelpTurn();
                            System.out.printf("\nPress ENTER to finish reading... ");
                            String idleStr2 = scanner.nextLine();
                            System.out.println("Returning to your turn...");
                            delay(1500);
                            clearTerminal();
                            break;
                        default :
                            System.out.println(".....");
                            delay(1000);
                            System.out.print("ERROR. Unrecognized action number!\n");
                            delay(500);
                            clearTerminal();
                            break;
                    }
                }
                // Next player's turn
                clearTerminal();
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
                        if (random1.nextInt(10) <= random1.nextInt(10)) {arrOrder = P1First;} // Current order: P1, P0
                    }
                }
            }
            // else: P0-S P1-M or P0-S P1-S. In both instance, order = P0, P1
            // listAct is now ordered

            // Phase 2a: Executes actions
            clearTerminal();
            System.out.print("Processing actions");
            for (int i = 0; i < 6; i++) {
                delay(250);
                System.out.print(".");
            }
            System.out.println("\n");
            for (int i : arrOrder) {
                int targetIdx = iToTargetIdx(i);
                Monster currentPMonster = arrayPlayers[i].getCurrentMonster();
                Monster currentTMonster = arrayPlayers[targetIdx].getCurrentMonster();

                // Depend on type of listAct content
                if (listActs.get(i) instanceof Monster) {
                    // Switch monster
                    currentPMonster.getSB().setSB(0, 0, 0, 0, 0);
                    System.out.printf("%s, good job! Get back!\n", currentPMonster.getNama());
                    Monster monster = (Monster) listActs.get(i);
                    arrayPlayers[i].setCurrentMonster(monster);
                    System.out.printf("PKMN Trainer %s sent out %S!\n", arrayPlayers[i].getPlayerName(), monster.getNama());
                    delay(1000);
                }
                else {
                    // Execute move upon the other player
                    Random random1 = new Random();
                    int paralyzeVal = random1.nextInt(5);
                    if ((currentPMonster.getStatusCondition() == StatusCondition.PARALYZE) && (paralyzeVal == 0)) {
                        System.out.printf("%s is PARALYZED! Failed to move...\n", currentPMonster.getNama());
                    }
                    else {
                        Move move = (Move) listActs.get(i);
                        System.out.printf("PKMN Trainer %s's %s used %s!\n", arrayPlayers[i].getPlayerName(), 
                            currentPMonster.getNama(), move.getName());
                        move.executeMove(currentPMonster, currentTMonster);
                        delay(1000);
                        checkPrintFainted(currentPMonster, arrayPlayers[i]);
                        if (!currentTMonster.isMonsterAlive()) {
                            checkPrintFainted(currentTMonster, arrayPlayers[targetIdx]);
                            break;  // If the fainted monster hasn't acted it is skipped
                        }
                        // Did we win?
                        if (!arrayPlayers[targetIdx].hasAliveMonsters()) {
                            isGameEnd = true;
                            playerWin = arrayPlayers[i];
                            playerLost = arrayPlayers[targetIdx];
                            break;  // break the loop, even if the other player haven't acted
                        }
                    }
                }
            }
            if (isGameEnd) {break;}
            // Moves and switches has been executed

            // PHASE 2B: calculate after damages
            System.out.println();
            Random random1 = new Random();
            arrOrder = P0First;
            if (random1.nextInt(10) <= random1.nextInt()) {arrOrder = P1First;}
            for (int i : arrOrder) {
                Monster currentPMonster = arrayPlayers[i].getCurrentMonster();
                
                // Inflict status damages on the current monsters
                if (currentPMonster.getStatusCondition() == StatusCondition.BURN && currentPMonster.isMonsterAlive()) {
                    System.out.printf("PKMN Trainer %s's %s suffered BURN!\n", 
                        arrayPlayers[i].getPlayerName(), currentPMonster.getNama());
                    currentPMonster.damage((double)1/8);
                    delay(500);
                    checkPrintFainted(currentPMonster, arrayPlayers[i]);
                }
                else if (currentPMonster.getStatusCondition() == StatusCondition.POISON && currentPMonster.isMonsterAlive()) {
                    System.out.printf("PKMN Trainer %s's %s suffered POISON!\n", 
                        arrayPlayers[i].getPlayerName(), currentPMonster.getNama());
                    currentPMonster.damage((double)1/16);
                    delay(500);
                    checkPrintFainted(currentPMonster, arrayPlayers[i]);
                }

                // Reduce the SLEEP counter on ALL monsters
                for (Monster monster : arrayPlayers[i].getListMonster()) {
                    if (monster.isMonsterAlive() && monster.getStatusCondition() == StatusCondition.SLEEP) {
                        monster.reduceSleepDuration();
                    }
                }

                // Did we win?
                if (!arrayPlayers[i].hasAliveMonsters()) {
                    isGameEnd = true;
                    playerWin = arrayPlayers[iToTargetIdx(i)];
                    playerLost = arrayPlayers[i];
                    break;  // break the loop, even if the other player haven't acted
                }

                // Phase 2C: If currentMonster became K-O'd after attacks, 
                // but still hasn't lost
                if (!currentPMonster.isMonsterAlive()) {
                    // Put dead monster on the bottom of the list
                    arrayPlayers[i].sortMonsters();
                    // Reset various statuses
                    currentPMonster.setStatusCondition(StatusCondition.NONE);
                    currentPMonster.getSB().resetStatsBuff();
                    boolean isKOReplaced = false;
                    while (!isKOReplaced) {
                        try {
                            delay(1000);
                            System.out.printf("PKMN Trainer %s! Select a new monster: \n", arrayPlayers[i].getPlayerName());
                            arrayPlayers[i].printMonsters(true);
                            System.out.print(">> ");
                            /**
                             * Try replace currentMonster with a new one
                             * If monster of selected num is KO, throw an
                             * IndexOutOfBoundsException
                             * If it tries to access a num larger than the List,
                             * it will also throw IndexOutOfBoundsException
                             */
                            int num = Integer.valueOf(scanner.nextLine());
                            Monster selectedMonster = arrayPlayers[i].getNumthMonster(num);
                            if (!selectedMonster.isMonsterAlive()) {
                                // Selected monster is KO (and doesnt appear on terminal)
                                throw new IndexOutOfBoundsException("Selected monster is KO!");
                            }
                            else {
                                arrayPlayers[i].setCurrentMonster(selectedMonster);
                                isKOReplaced = true;
                            }
                        }
                        catch (InputMismatchException e) {WarnInputMismatch();}
                        catch (IndexOutOfBoundsException e) {WarnIndexOutOfBounds();}
                    }
                    // Inputting loop ends
                }
                // Evaluate the next player
            }
            // Turn ends
            System.out.print("Press ENTER to continue... ");
            String idleStr = scanner.nextLine();
            System.out.println("\nStarting the next turn...");
            delay(1000);
            clearTerminal();
        }
        // Game has ended
        System.out.printf("%s has defeated %s!\n", playerWin.getPlayerName(), playerLost.getPlayerName());
        delay(2000);
        System.out.println("Thank you for playing!");
        delay(2000);
        com.monstersaku.util.EneConfig.printEne();
        scanner.close();

    }
    // End of main



    // STATICS
    // Melakukan print pilihan command di main menu
    private static final void printMainMenuCommands() {
        System.out.println(
        		"Select Command!\n" +
        		"> Start\n" +
        		"> Help\n" +
        		"> Exit"
        		);
    }
    
    private static void delay(int milis) {
        try {Thread.sleep(milis);} catch (InterruptedException e) {}
    }

    private static final void printActions() {
        // Used in turns
        System.out.println(
            "\nSelect action number!\n" +
            "    1. Move\n" +
            "    2. Switch\n" +
            "    3. View Monsters Info\n" +
            "    4. View Game Info\n" +
            "    5. Help"
        );
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
    			+ "\"Start\"\n"
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
        System.out.println("\n>>> HELP <<<");
        System.out.println(">> 1. Move");
        System.out.println("   \"An action for your Pokemon to attack opposing Pokemon, heal,");
        System.out.println("   etc. Your Pokemon cannot move if it has the buff 'SLEEP'!\"");
        delay(200);
        System.out.println(">> 2. Switch");
        System.out.println("   \"Replace your current pokemon with another one. You cannot switch");
        System.out.println("   a knocked-out pokemon!\"");
        delay(200);
        System.out.println(">> 3. View Monster Info");
        System.out.println("   \"Select a monster and view its stats and moves.\"");
        delay(200);
        System.out.println(">> 4. View Game Info");
        System.out.println("   \"Print out current game status, as the name suggests.\"");
        delay(200);
        System.out.println(">> 5. Help");
        System.out.println("   \"You are using that command right now.\"");
        delay(1000);
    }

    private static void printPlayers(Player[] arrayPlayers) {
        // Print players
        System.out.println("Select player number: ");
        for (int i = 0; i <= 1; i++) {
            System.out.printf("    %d. %s\n", i+1, arrayPlayers[i].getPlayerName());
        }
    } 

    private static void printGameInfo(int turn, Player currentPlayer) {
        // Print info about the turn
        System.out.printf("\n>>>> TURN %d <<<<\n", turn);
        delay(200);
        System.out.printf(">> Player  : %s\n", currentPlayer.getPlayerName());
        delay(700);
        System.out.printf(">> Current Monster:\n");
        currentPlayer.getCurrentMonster().printMonsterAttr();
        delay(200);
        System.out.printf("\nPKMN Trainer %s's Monsters:\n", currentPlayer.getPlayerName());
        currentPlayer.printMonsters(false);
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

    private static void clearTerminal() {
        try {
            if (System.getProperty("os.name").contains("Windows") && isCmd) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else {
                System.out.print("\033\143");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int iToTargetIdx (int i) {
        int targetIdx = 0;
        if (i == 0) targetIdx = 1;
        return targetIdx;
    }

    public static boolean isCmd = false;

    public static void checkPrintFainted(Monster monster, Player player) {
        if (!monster.isMonsterAlive()) {
            System.out.printf("PKMN Trainer %s's %s fainted!\n", 
            player.getPlayerName(), monster.getNama());
        }
    }
}
