package TUBES.Tubes-OOP25-Pokemon.src.main.java.com.monstersaku.util;
import com.monstersaku.Monster;
import com.monstersaku.Stats;
import com.monstersaku.Move;
import java.util.List;

import javax.lang.model.element.Element;

import java.util.ArrayList;

import com.monstersaku.util.CSVReader;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import com.monstersaku.Main;
import com.monstersaku.elementType;

public class MonsterPoolConfig {
    private static String filename = "configs.monsterpool.csv";

    public static List<Monster> create(){
        List<Monster> monsterlist = new ArrayList<Monster>();

        try {
            CSVReader reader = new CSVReader(new File(Main.class.getResource(fileName).toURI()), ";");
            reader.setSkipHeader(true);
            List<String[]> lines = reader.read();
            for(String[] line : lines){
                //id monster
                int id = Integer.valueOf(line[0]);

                //nama monster
                String nama = line[1];

                //ElementType monster
                String[] elements = (line[2]).split(",");
                List<ElementType> eltype = new ArrayList<ElementType>();
                for (String el : elements){
                    ElementType elt = ElementType.valueOf(el); 
                    eltype.add(elt);
                }

                //BaseStats
                String[] bs = (line[3]).split(",");
                int[] baseStats={0,0,0,0,0,0};
                for (int i=0;i<6;i++){
                    baseStats[i] = Integer.valueOf(bs[i]);
                }
                Stats attrBaseStats = new Stats(baseStats[0], baseStats[0], baseStats[1], baseStats[2], baseStats[3], baseStats[4], baseStats[5]);
                Monster monsread = new Monster(id, name, eltype, attrBaseStats);

                //add move -> pake movepool config 
            }    
        }
        catch (Exception e){
            System.out.println("Monster configuration failed")
        }
        return monsterlist;
    }
}
