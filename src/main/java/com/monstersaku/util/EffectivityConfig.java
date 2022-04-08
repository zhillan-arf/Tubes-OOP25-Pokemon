package TUBES.Tubes-OOP25-Pokemon.src.main.java.com.monstersaku.util;

import com.monstersaku.Main;
import com.monstersaku.ElementType;

import com.monstersaku.util.CSVReader;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class EffectivityConfig {
    private static String fileName;

    public static void setFileName(String fn){
        this.fileName = fn;
    }

    public static double getEffectivity(ElementType source, ElementType target){
        double value = 1; //default

        try{
            CSVReader reader = new CSVReader(new File(Main.class.getResource(fileName).toURI()), ";");
            reader.setSkipHeader(true);
            List<String[]> lines = reader.read();
            for (String[] line : lines){
                ElementType sourceType = ElementType.valueOf(line[0]);
                ElementType targetType = ElementType.valueOf(line[1]);
                if (souce == sourceType && target == targetType){
                    value = Double.valueOf(line[2]);
                    break;
                }
            }
        }
        catch (Exception e){
            System.out.println("Error getEffectivity config");
        }
        return value;
    }
}