package com.monstersaku.util;
import com.monstersaku.Monster;
import com.monstersaku.Stats;
import com.monstersaku.Move;
import com.monstersaku.Main;
import com.monstersaku.ElementType;
import com.monstersaku.util.CSVReader;

import java.util.*;
import javax.lang.model.element.Element;
import java.io.File;

public class MonsterPoolConfig {
    private static String fileName = "configs.monsterpool.csv";

    public static List<Monster> create(){
        List<Monster> monsterPool = new ArrayList<Monster>();

        try {
            CSVReader reader = new CSVReader(new File(Main.class.getResource(fileName).toURI()), ";");
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
                int[] baseStats={0,0,0,0,0,0};
                for (int i=0;i<6;i++){
                    baseStats[i] = Integer.valueOf(bs[i]);
                }
                Stats attrBaseStats = new Stats(baseStats[0], baseStats[0], baseStats[1], baseStats[2], baseStats[3], baseStats[4], baseStats[5]);

                // Add move -> pake movepool config
                List<Move> moves = new ArrayList<Move>();
                //...
                
                // Instatiate the monster using the datas
                Monster monster = new Monster(id, nama, eltype, attrBaseStats, moves);

                // Add monster to monsterPool
                monsterPool.add(monster);
            }    
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Monster configuration failed");
        }
        return monsterPool;
    }
}
