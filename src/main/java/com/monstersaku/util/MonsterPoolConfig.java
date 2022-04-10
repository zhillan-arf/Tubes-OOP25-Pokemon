package com.monstersaku.util;
import com.monstersaku.*;
import java.util.*;
import java.io.File;

public class MonsterPoolConfig {
    private static String fileName = "../configs/monsterpool.csv";

    public static List<Monster> create(List<Move> movePool){
        List<Monster> monsterPool = new ArrayList<Monster>();

        try {
            CSVReader reader = new CSVReader(new File(MonsterPoolConfig.class.getResource(fileName).toURI()), ";");
            reader.setSkipHeader(true);
            List<String[]> lines = reader.read();
            for(String[] line : lines){
                // id monster
                int id = Integer.valueOf(line[0]);

                // Nama monster
                String nama = line[1];

                // ElementType monster
                String[] elements = (line[2]).split(",");
                List<ElementType> eltype = new ArrayList<ElementType>();
                for (String el : elements){
                    ElementType elt = ElementType.valueOf(el); 
                    eltype.add(elt);
                }

                // BaseStats
                String[] bs = (line[3]).split(",");
                int[] baseStats = {0,0,0,0,0,0};
                for (int i = 0; i < 6; i++) {
                    baseStats[i] = Integer.valueOf(bs[i]);
                }
                Stats attrBaseStats = new Stats(baseStats[0], baseStats[0], baseStats[1], baseStats[2], baseStats[3], baseStats[4], baseStats[5]);

                // Add move
                // Create an array of IDs
                String[] strMoveIDs = line[4].split(",");
                int[] intMoveIDs = new int[strMoveIDs.length];
                for (int i = 0; i < intMoveIDs.length; i++) {
                    intMoveIDs[i] = Integer.valueOf(strMoveIDs[i]);
                }

                // Fill moves with the correct IDs
                List<Move> moves = new ArrayList<Move>();
                // Assume that IDs in move config file is 1,2,3,4...
                // and NOT 0,1,2,3... or other forms
                for (int idx : intMoveIDs) {
                    moves.add(movePool.get(idx));
                }
                /** IN CASE OF EMERGENCY (ID IN MOVE FILE NOT 1,2,3,4..) BREAK GLASS
                if (movePool.size() > 0) {
                    for (int i = 0; i < 6; i++) {
                        Iterator<Move> it = movePool.iterator();
                        boolean isNotFound = true;
                        Move move = movePool.get(0);
                        while (it.hasNext() && isNotFound) {
                            move = it.next();
                            isNotFound = (move.getId() == intMoveIDs[i]);
                        }
                        if (!isNotFound) {
                            // Move with the correct id is found
                            moves.add(move);
                        }
                    }
                }
                */
                DefaultMove defMove = new DefaultMove();
                moves.add(defMove);
                // moves are filled
                
                // Instatiate the monster using the datas
                Monster monster = new Monster(id, nama, eltype, attrBaseStats, moves);

                // Add monster to monsterPool
                monsterPool.add(monster);
            }    
        }
        catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }

        try {Thread.sleep(1000);} 
        catch (InterruptedException e) {e.printStackTrace();}
        System.out.println("monsterpool.csv loaded...");
        return monsterPool;
    }
}
