package main;

import data.db.DataParser;
import data.db.FileManager;
import data.db.Game;
import data.sorts.ExternalSort;
import data.structs.extensiveHash.HashManeger;

import java.io.RandomAccessFile;

class Main{
    public static void main(String[] args)throws Exception {
        RandomAccessFile gamescsv = new RandomAccessFile("resources/games.csv", "rw");
        RandomAccessFile directoryFile = new RandomAccessFile("out/byteArchive/directory.db","rw");
        RandomAccessFile bucketsFile = new RandomAccessFile("out/byteArchive/buckets.db", "rw");
        Game teste = new Game(DataParser.parse(gamescsv.readLine()));
        HashManeger hash = new HashManeger(directoryFile, bucketsFile);
        hash.create(730 , 10);
        hash.create(2 , 20);

    }
}


