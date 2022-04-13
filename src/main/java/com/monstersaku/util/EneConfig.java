package com.monstersaku.util;
import com.monstersaku.*;
import java.util.*;
import java.io.File;

public class EneConfig {
    private static String fileName = "../configs/ene.csv";

    public static void printEne() {
        try{
            CSVReader reader = new CSVReader(new File(EffectivityConfig.class.getResource(fileName).toURI()), ";");
            reader.setSkipHeader(true);
            List<String[]> lines = reader.read();
            for (String[] line : lines) {
                for (String word : line) {System.out.print(word);}
                System.out.println();
                Thread.sleep(100);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
