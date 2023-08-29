import data.DataParser;
import data.Game;

import java.io.RandomAccessFile;
import java.util.Arrays;

class Main{
    public static void main(String[] args)throws Exception {
        RandomAccessFile gamesCsv;
        RandomAccessFile gamesBin;
        System.out.println("antesf");
        try {
            gamesCsv = new RandomAccessFile("/Users/gabrieltodt/AEDS3/resources/games.csv", "rw");
            gamesBin = new RandomAccessFile("/Users/gabrieltodt/AEDS3/out/byteArchive/games.db", "rw");
            //Create new game
            for(int i=0; i<4121; i++){
                Game tmp = new Game(DataParser.parse(gamesCsv.readLine()));
                byte[] bytes = tmp.toByteArray();
                int size = bytes.length;
                gamesBin.writeInt(size);
                gamesBin.write(bytes);
            }
            Game[] games = new Game[4121];
            //Carregar arquivos do hexadecimal
            gamesBin.seek(0);
            for(int i=0; i<4121;i++){
                int size = gamesBin.readInt();
                byte[] gameAttsBytes = new byte[size];
                gamesBin.read(gameAttsBytes);
                games[i] = new Game();
                games[i].fromByteArray(gameAttsBytes);
            }
            for(int i=0; i<games.length; i++){
                System.out.println(games[i].getName());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

///Users/gabrieltodt/IdeaProjects/AEDS/resources


