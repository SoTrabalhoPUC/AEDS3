package data.structs.extensiveHash;

import data.db.Game;

import java.io.IOException;
import java.io.RandomAccessFile;

public class HashManeger {

    private final Directory directory;

    public HashManeger(RandomAccessFile dFile, RandomAccessFile bFile) throws IOException {
        directory = new Directory(dFile, bFile);
    }

    public void create(int id, long address) throws IOException {
        directory.create(id, address);
    }







}
