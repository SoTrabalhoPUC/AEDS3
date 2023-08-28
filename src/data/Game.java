package data;
import java.util.Date;
public class Game {
    //Class atributes
    private boolean linux;
    private boolean mac;
    private boolean windows;
    private int age;
    private int avg_pt;
    private int dlcs;
    private int gameId;
    private float price;
    private float upvotes;
    private String developers;
    private String name;
    private String owners;
    private String website;
    private String[] genres;
    private String[] languages;
    private Date releaseDate;
    //Gets and Sets
    public boolean getLinux(){
        return linux;
    }
    public void setLinux(boolean linux) {
        this.linux = linux;
    }
    public boolean getMac(){
        return mac;
    }
    public void setMac(boolean mac) {
        this.mac = mac;
    }
    public boolean getWindows(){
        return windows;
    }
    public void setWindows(boolean windows) {
        this.windows = windows;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getAvg_pt() {
        return avg_pt;
    }
    public void setAvg_pt(int avg_pt) {
        this.avg_pt = avg_pt;
    }
    public int getDlcs() {
        return dlcs;
    }
    public void setDlcs(int dlcs) {
        this.dlcs = dlcs;
    }
    public int getGameId() {
        return gameId;
    }
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public float getUpvotes() {
        return upvotes;
    }
    public void setUpvotes(float upvotes) {
        this.upvotes = upvotes;
    }
    public String getDevelopers() {
        return developers;
    }
    public void setDevelopers(String developers) {
        this.developers = developers;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getOwners() {
        return owners;
    }
    public void setOwners(String owners) {
        this.owners = owners;
    }
    public String getWebsite() {
        return website;
    }
    public void setWebsite(String website) {
        this.website = website;
    }
    public String[] getGenres() {
        return genres;
    }
    public void setGenres(String[] genres) {
        this.genres = genres;
    }
    public String[] getLanguages() {
        return languages;
    }
    public void setLanguages(String[] languages) {
        this.languages = languages;
    }
    public Date getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
    //Constructors
    public Game(boolean linux, boolean mac, boolean windows, int age,int avg_pt, int dlcs, int gameId, float price, float upvotes, String developers, String name, String owners, String website, String[] genres, String[] languages, Date releaseDate){
        this.linux = linux;
        this.mac = mac;
        this.windows = windows;
        this.age = age;
        this.avg_pt = avg_pt;
        this.dlcs = dlcs;
        this.gameId = gameId;
        this.price = price;
        this.upvotes = upvotes;
        this.developers = developers;
        this.name = name;
        this.owners = owners;
        this.website = website;
        this.genres = genres;
        this.languages = languages;
        this.releaseDate = releaseDate;
    }
    //Empty constructor
    public Game(){
        this(false, false,false,-1,-1,-1,-1,-1,-1,null,null,null,null,null,null,null);
    }

}

