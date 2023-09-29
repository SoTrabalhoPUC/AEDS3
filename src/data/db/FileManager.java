package data.db;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;

public class FileManager {

    private static String csvFile = "resources/games.csv";
    private static String binFile = "out/byteArchive/games.db";

    public static String getBinFile() {
        return binFile;
    }
    public static void setBinFile(String binFile) {
        FileManager.binFile = binFile;
    }
    public static String getCsvFile() {
        return csvFile;
    }
    public static void setCsvFile(String csvFile) {
        FileManager.csvFile = csvFile;
    }

    /**
     * Reads game data from the CSV file and writes the records in byte format to another file.
     *
     * @throws IOException if the csv file does not exist.
     */
    public static void start(RandomAccessFile dest) throws IOException, ParseException {
        Game game;
        RandomAccessFile csv = new RandomAccessFile(csvFile, "rw");
        for(int i = 0; i<4121; i++){ // Number of games in the csv file.
            game = new Game(DataParser.parse(csv.readLine()));
            create(game, dest);
        }
        csv.close();
    }

    /**
     * Creates a record of a new game at the end of the destination file.
     *
     * @param game the game to be created in the file.
     * @param dest the file where the game will be created.
     * @throws IOException RandomAccessFile Exception.
     */
    public static void create(Game game, RandomAccessFile dest) throws IOException { // valid flag | game length | game
        dest.seek(dest.length());
        byte[] gameBytes = game.toByteArray();
        // Valid flag //1 byte
        dest.writeBoolean(true);
        // Game length //4 bytes
        dest.writeInt(gameBytes.length);
        dest.write(gameBytes);
    }

    /**
     * Encontra  o registro referente ao id fornecido, de forma sequencial.
     *
     * @param id identificador do registro a ser procurado.
     * @return em caso de sucesso, retorna um objeto referente ao registro encontrado, caso contrário
     *         retorna um objeto nulo.
     * @throws IOException RandomAccessFile Exception.
     */
    public static Game read(int id) throws IOException{
        Game out = null;
        RandomAccessFile bin = new RandomAccessFile(binFile,"rw");
        while (bin.getFilePointer() < bin.length()){
            if(bin.readBoolean()){
                 int size = bin.readInt();
                 int regId = bin.readInt();
                 if(id == regId){
                     bin.seek(bin.getFilePointer()-Integer.BYTES);
                     byte [] gameArrayBytes = new byte[size];
                     bin.read(gameArrayBytes);
                     out = new Game();
                     out.fromByteArray(gameArrayBytes);
                     break;
                 }
                 else {
                     bin.seek(bin.getFilePointer()+size-4);
                 }
            }
            else{
                System.out.println("Conferir-read-bobao.");
                int size = bin.readInt();
                bin.seek(bin.getFilePointer()+size);
            }
        }
        bin.close();
        return out;
    }


    public static void update(Game game, RandomAccessFile file) throws IOException {
        int id = game.getGameId();
        long filePoiter;
        while(file.getFilePointer()<file.length()){
            if(file.readBoolean()){
                int size = file.readInt();
                filePoiter = file.getFilePointer();
                int regId = file.readInt();
                if(regId == id){
                    file.seek(filePoiter);
                    file.writeBoolean(false);
                    break;
                } else {
                    file.seek(filePoiter + size);
                }
            } else {
                int size = file.readInt();
                file.seek(file.getFilePointer() + size);
            }

        }
        create(game, file);
    }

    public static void delete(int id) throws Exception{
        RandomAccessFile bin = new RandomAccessFile(binFile, "rw");
        boolean validFlag;
        int gameId;
        int size;
        long filePointer;
        while(bin.getFilePointer()<bin.length()){
            filePointer = bin.getFilePointer();
            bin.seek(bin.getFilePointer()+1); // Skip the valid flag.
            size = bin.readInt();
            gameId = bin.readInt();
            if(gameId == id){
                bin.seek(filePointer);
                bin.writeBoolean(false);
                break;
            }
            else{
                bin.seek(bin.getFilePointer()+size-4);
            }
        }
        bin.close();
    }

    public static void printGamesId(RandomAccessFile source, int blockSize) throws IOException {
        int counter = 0;
        int gameSize;
        int control = 0;
        boolean validFlag;
        while (source.getFilePointer()<source.length()){
            validFlag = source.readBoolean();
            gameSize = source.readInt();
            byte[] gameBytes = new byte[gameSize];
            source.read(gameBytes);
            Game tmp = new Game();
            tmp.fromByteArray(gameBytes);
            System.out.println(tmp.getGameId());
            counter++;

            if(control++ == blockSize-1){
                System.out.println("------------------");
                control = 0;
            }
        }
        System.out.println("Total: "+counter);
    }




}
