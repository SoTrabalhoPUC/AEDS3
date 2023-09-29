package data.sorts;

import data.db.FileManager;
import data.db.Game;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class ExternalSort{

    public static void Distribution(int blockSize, int nFiles, RandomAccessFile source, RandomAccessFile[] outPut) throws Exception{ // valid flag | game size | game
        int counter;
        int[] gameSize = new int[blockSize];
        int control = 0;
        //boolean[] validFlag = new boolean[10];
        Game[] games = new Game[blockSize];
        for(int i=0;i<blockSize;i++){
            games[i] = new Game();
        }
        while(source.getFilePointer()<source.length()){
            counter = 0;
            //System.out.println(source.getFilePointer());
            for(int i = 0; i<blockSize && source.getFilePointer()<source.length(); i++){ // Ignorando os registros com flag false.
                counter++;
                source.seek(source.getFilePointer()+1); //Skip the valid flag
                gameSize[i] = source.readInt();
                byte[] gameBytes = new byte[gameSize[i]];
                source.read(gameBytes);
                games[i].fromByteArray(gameBytes);
            }
            InternalSorts.quickSort(0,counter-1,games);

            for(int i=0; i<counter; i++){
                FileManager.create(games[i],outPut[control%(nFiles/2)]);
            }
            control++;
        }


        // Move the pointer to the begging of the file.
        for(int i=0; i< nFiles; i++){
            outPut[i].seek(0);
        }
//        tmp1.seek(0);
//        tmp2.seek(0);
    }

    public static void Sort(int blockSize, int nFiles, RandomAccessFile source) throws Exception {
        RandomAccessFile[] files = new RandomAccessFile[nFiles];
        long sourceLength = source.length();
        /*
         * Create the files to be used.
         */
        try {
            for (int i = 0; i < nFiles; i++) {
                files[i] = new RandomAccessFile("/Users/gabrieltodt/AEDS3/out/byteArchive/tmp" + i + ".db", "rw");
            }
        } catch (Exception e){
            System.err.println(e+" --> Error while creating the temporary files.");
        }
        Distribution(blockSize, nFiles, source, files);

    }

    /**
     * Finds the largest file within an array and returns its index.
     *
     * @param files array of files to be evaluated
     * @return index of the largest file in the array.
     * @throws IOException RandomAccessFile Exception
     */
    public static int getHighestLenIndex(RandomAccessFile[] files) throws IOException {
        int biggestPos = 0;
        long h = files[0].length();
        for(int i=1; i<files.length; i++){
            if(files[i].length()>h){
                h = files[i].length();
                biggestPos = i;
            }
        }
        return biggestPos;
    }

    public byte[] getLowestId(RandomAccessFile[] files, int[] control, int block) throws IOException {
        long pos = files[0].getFilePointer();
        boolean validFlag = files[0].readBoolean();
        byte[] out = new byte[files[0].readInt()];
        int lowestId = files[0].readInt();
        files[0].seek(files[0].getFilePointer() - Integer.BYTES);
        files[0].read(out);
        files[0].seek(pos);
        for(int i=0; i<files.length/2; i++){
            if(control[i]<block && files[i].getFilePointer()<files[i].length()){ //talvez alterar para <= !!!!!!
                pos = files[i].getFilePointer();
                validFlag = files[i].readBoolean(); //Tratar o caso em que a flag é false.
                int gameSize = files[i].readInt();
                int idTmp = files[i].readInt();
                if(idTmp <= lowestId){
                    lowestId = idTmp;
                }
            }
        }
        return out;
    }


    public static void Merge(int blockSize, long sourceLength, RandomAccessFile[] files) throws IOException {
       // int mergingControl = blockSize; //controls the next block size.
        int filesLength = files.length; //
        int selectFile = files.length/2; // Used to select witch file will be used.
        int[] control = new int[filesLength/2];


        RandomAccessFile sizeControl = files[getHighestLenIndex(files)];
        // TODO: 09/09/23 conferir se deu certo o select.
//
//        while(sizeControl.length()<sourceLength){
//            while(){
//
//            }
//
//
//            //Atualizar o sizeControl
//        }







    }


}
