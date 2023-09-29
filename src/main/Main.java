package main;

import data.db.FileManager;
import data.sorts.ExternalSort;

import java.io.RandomAccessFile;

class Main{
    public static void main(String[] args)throws Exception {
        RandomAccessFile raf  = new RandomAccessFile("out/byteArchive/games.db", "rw");
        FileManager.start(raf);
        raf.close();
    }
}


