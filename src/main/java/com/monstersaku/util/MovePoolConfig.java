package com.monstersaku.util;
import com.monstersaku.StatusCondition;
import com.monstersaku.StatusMove;
import com.monstersaku.Target;
import com.monstersaku.Move;
import com.monstersaku.NormalMove;
import com.monstersaku.SpecialMove;
import com.monstersaku.Main;
import com.monstersaku.ElementType;

import java.util.*;
import java.io.File;


public class MovePoolConfig {
    private static String fileName = "configs.movepool.csv";

    public static List<Move> create() {
        // Parse movepool.csv and create a list of Moves
        List<Move> movePool = new ArrayList<Move>();

        try {
            CSVReader reader = new CSVReader(new File(Main.class.getResource(fileName).toURI()), ";");
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
                // Get elmts for StatusMove, one by one
                if (moveType.equals("STATUS")) {
                    String[] buffs = (line[8]).split(",");
                    StatusCondition statCondBuff = StatusCondition.NONE;
                    if (buffs[0] != "-") {
                        statCondBuff = StatusCondition.valueOf(buffs[0]);
                    }
                    int hpBuff = Integer.valueOf(buffs[1]);
                    int atkBuff = Integer.valueOf(buffs[2]);
                    int defBuff = Integer.valueOf(buffs[3]);
                    int spAtkBuff = Integer.valueOf(buffs[4]);
                    int spDefBuff = Integer.valueOf(buffs[5]);
                    int speedBuff = Integer.valueOf(buffs[6]);
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
                                                    atkBuff, 
                                                    defBuff, 
                                                    spAtkBuff, 
                                                    spDefBuff, 
                                                    speedBuff);
                    // Add
                    movePool.add(move);
                }
                // Get the 2 elmts for Normal and Special move
                else {
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
            System.out.println(e.getMessage());
            System.out.println("FATAL ERROR. Monster configuration failed...");
            System.exit(1);
        }
        
        return movePool;
    }
    
}
