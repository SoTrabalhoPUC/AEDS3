package main;

import data.db.FileManager;
import data.sorts.ExternalSort;

import java.io.RandomAccessFile;

class Main{
    public static void main(String[] args)throws Exception {
        RandomAccessFile source0 = new RandomAccessFile("/Users/gabrieltodt/AEDS3/out/byteArchive/tmp0.db", "rw");
        source0.writeInt(10);
        RandomAccessFile tmp = source0;
        System.out.println(tmp.getFilePointer());




    }
}


