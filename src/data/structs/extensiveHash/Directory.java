package data.structs.extensiveHash;

import data.db.Game;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Directory {
    private final int bucketSize = 10; //Registros por bucket.

    private final RandomAccessFile dFile; //Onde o diretorio fica armazenado.
    private final RandomAccessFile bFile; //Onde os buckets ficam armazenados.
    public int getDepth() throws IOException {
        long pos =  dFile.getFilePointer();
        int out;
        dFile.seek(0);
        out = dFile.readInt();
        dFile.seek(pos);
        return out;
    }
    /**
     * @param dFile where the directory file will be stored.
     * @param bFile where the buckets file will be stored.
     */
    public Directory(RandomAccessFile dFile,RandomAccessFile bFile) throws IOException {
        this.dFile = dFile;
        this.bFile = bFile;
        this.start();
    }


    /**
     * Start the hash directory with the default depth value 2.
     * @throws IOException Random access file Exception.
     */
    private void start() throws IOException {
        dFile.writeInt(2);
        int bSize = bucketSize*160;
        for(int b = 0; b<4; b++){ // Creating the buckets file with "null" values.
            dFile.writeLong(bFile.getFilePointer());
            bFile.writeInt(2);
            for(int i = 0; i< bucketSize; i++){
                bFile.writeInt(-1);
                bFile.writeLong(-1);
            }
        }
        bFile.seek(0);
        dFile.seek(0);
    }

    /**
     * Create a new index in the hash structure.
     *
     * @param key hashed game id.
     * @param gameAddress where the game is stores in the games.db.
     */
    public void create(int key, long gameAddress) throws IOException {
        long dpos = dFile.getFilePointer(); //don`t remove
        int hkey = hashFunction(key);
        dFile.seek(0);
        int depth = dFile.readInt();
        long kPos = 4 + ((long)hkey*Long.BYTES); //where the key is in the directory file.
        dFile.seek(kPos);
        long bucketPos = dFile.readLong();
        addInBucket(key, gameAddress, bucketPos);




        dFile.seek(dpos); //don`t remove
    }

    private void addInBucket(int key, long gameAddress, long bucketPos) throws IOException {
        long bpos = bFile.getFilePointer(); //Don`t remove.
        bFile.seek(bucketPos);
        int bucketDepth = bFile.readInt();
        for(int i=0; i<bucketSize; i++){
            int bkey = bFile.readInt();
            if(bkey == -1){
                bFile.seek(bFile.getFilePointer()-Integer.BYTES);
                bFile.writeInt(key);
                bFile.writeLong(gameAddress);
                break;
            }
        }
        bFile.seek(bpos); // Don`t remove.
    }

    private int hashFunction(int id) throws IOException {
        return (int) (id%(Math.pow(2, this.getDepth())));
    }

// bucket size = 10(4+4+8)





}
