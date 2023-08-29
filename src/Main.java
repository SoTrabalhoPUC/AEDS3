import data.DataParser;
import data.Game;

import javax.xml.transform.stream.StreamSource;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.util.Arrays;

class Main{
    public static void main(String[] args)throws Exception {
        FileInputStream fis = new FileInputStream("/Users/gabrieltodt/IdeaProjects/AEDS/out/byteArchive/game.bin");
        DataInputStream dis = new DataInputStream(fis);


        var raf = new RandomAccessFile("/Users/gabrieltodt/IdeaProjects/AEDS/resources/games.csv", "r");
        var bin = new RandomAccessFile("/Users/gabrieltodt/IdeaProjects/AEDS/out/byteArchive/game.bin", "rw");
        //Game = new Game(DataParser.parse(raf.readLine()));
        int size = bin.readInt();
        System.out.println(size);
        byte [] byteArray = new byte[size];
        Game g1 = new Game();
        dis.read(byteArray);
        g1.fromByteArray(byteArray);
        System.out.println(g1.getName());

    }
}

///Users/gabrieltodt/IdeaProjects/AEDS/resources


