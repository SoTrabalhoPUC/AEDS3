import data.DataParser;
import data.Game;

import java.io.RandomAccessFile;
import java.util.Arrays;

class Main{
    public static void main(String[] args)throws Exception {
        var raf = new RandomAccessFile("/Users/gabrieltodt/IdeaProjects/AEDS/resources/games.csv", "r");
        String[] teste = DataParser.parseAtts(raf.readLine());
        System.out.println(Arrays.toString(teste));
    }
}

///Users/gabrieltodt/IdeaProjects/AEDS/resources