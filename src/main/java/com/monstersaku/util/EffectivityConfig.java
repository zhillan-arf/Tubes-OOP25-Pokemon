package com.monstersaku.util;
import com.monstersaku.*;
import java.io.File;
import java.util.*;

public class EffectivityConfig {
    private static String fileName = "../configs/element-type-effectivity-chat.csv";

    public static void setFileName(String fn){
        fileName = fn;
    }

    public static double getEffectivity(ElementType source, ElementType target){
        double value = 1; //default

        try{
            CSVReader reader = new CSVReader(new File(EffectivityConfig.class.getResource(fileName).toURI()), ";");
            reader.setSkipHeader(true);
            List<String[]> lines = reader.read();
            for (String[] line : lines){
                ElementType sourceType = ElementType.valueOf(line[0]);
                ElementType targetType = ElementType.valueOf(line[1]);
                if (source == sourceType && target == targetType){
                    value = Double.valueOf(line[2]);
                    break;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        return value;
    }
}
