package data;

public class FileManager {
    private Game game;
    private int gameSize;
    private  boolean isValid;
    public Game getGame() {
        return game;
    }
    public void setGame(Game game) {
        this.game = game;
    }
    public boolean getValid() {
        return isValid;
    }
    public void setValid(boolean valid) {
        isValid = valid;
    }
    public int getGameSize() {
        return gameSize;
    }
    
}
