package com.monstersaku.util;
import com.monstersaku.*;
import java.util.*;
import java.io.File;


public class MovePoolConfig {
    private static String fileName = "../configs/movepool.csv";

    public static List<Move> create() {
        // Parse movepool.csv and create a list of Moves
        List<Move> movePool = new ArrayList<Move>();

        try {
            CSVReader reader = new CSVReader(new File(MovePoolConfig.class.getResource(fileName).toURI()), ";");
            reader.setSkipHeader(true);
            List<String[]> lines = reader.read();
            for(String[] line : lines){
                // Each of the move attributes and its non-attribute characteristics
                int id = Integer.valueOf(line[0]);
                String moveType = line[1];
                String name = line[2];
                ElementType elementType = ElementType.valueOf(line[3]);
                int accuracy = Integer.valueOf(line[4]);
                int priority = Integer.valueOf(line[5]);
                int ammunition = Integer.valueOf(line[6]);
                Target target = Target.valueOf(line[7]);
                
                if (moveType.equals("STATUS")) {  // Get elmts for StatusMove, one by one
                    StatusCondition statCondBuff = StatusCondition.NONE;
                    if (!line[8].equals("-")) {statCondBuff = StatusCondition.valueOf(line[8]);}
                    String[] buffs = (line[9]).split(",");
                    int hpBuff = Integer.valueOf(buffs[0]);
                    int[] arrSB = new int[5];
                    for (int i = 0; i < 5; i++) {
                        arrSB[i] = Integer.valueOf(buffs[i + 1]);
                    }
                    // Instantiate
                    StatusMove move = new StatusMove(id,
                                                    name, 
                                                    elementType, 
                                                    accuracy, 
                                                    priority, 
                                                    ammunition,
                                                    target,
                                                    statCondBuff, 
                                                    hpBuff, 
                                                    arrSB);
                    // Add
                    movePool.add(move);
                }
                else {  // Get the 2 elmts for Normal and Special move
                    int basePower = Integer.valueOf(line[8]);
                    if (moveType.equals("NORMAL")) {
                        // Instantiate
                        NormalMove move = new NormalMove(id,
                                                        name, 
                                                        elementType, 
                                                        accuracy, 
                                                        priority, 
                                                        ammunition, 
                                                        basePower);
                        // Add
                        movePool.add(move);
                    }
                    else if (moveType.equals("SPECIAL")) {
                        // Instantiate
                        SpecialMove move = new SpecialMove(id,
                                                        name, 
                                                        elementType, 
                                                        accuracy, 
                                                        priority, 
                                                        ammunition, 
                                                        basePower);
                        // Add
                        movePool.add(move);
                    }
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        
        try {Thread.sleep(1000);} 
        catch (InterruptedException e) {e.printStackTrace();}
        System.out.println("movepool.csv loaded...");
        return movePool;
    }
    
}
