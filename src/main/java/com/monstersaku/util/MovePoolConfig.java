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


public class MovePoolConfig {
    private static String fileName = "configs.movepool.csv";

    public static List<Move> create() {
        // Parse movepool.csv and create a list of Moves
        List<Move> movePool = new ArrayList<Move>();
        // ...

        return movePool;
    }
    
}
