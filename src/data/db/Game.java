package data.db;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class Game containing attributes related to each game from the CSV file.
 * Implements methods for transforming the object to bytes.
 */

public class Game {
    /**
     * Compatible with Linux.
     */
    private boolean linux;
    /**
     * Compatible with macOS.
     */
    private boolean mac;
    /**
     * Compatible with Windows.
     */
    private boolean windows;
    /**
     * Ages in years.
     */
    private int age;
    /**
     * Average playtime.
     */
    private int avg_pt;
    /**
     *  Number of DLCs.
     */
    private int dlcs;
    /**
     * Game identifier.
     */
    private int gameId;
    /**
     * Positive upvotes.
     */
    private int upvotes;
    /**
     * Price in Dollars.
     */
    private float price;
    /**
     * Game developers.
     */
    private String developers;
    /**
     * Game name on Steam.
     */
    private String name;
    /**
     * Number of owners.
     */
    private String owners;
    /**
     * Game website.
     */
    private String website;
    /**
     * Available languages.
     */
    private String[] languages;
    /**
     * Release Date
     */
    private Date releaseDate;
    /**
     * Gets and sets.
     */
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
    public int getUpvotes() {
        return upvotes;
    }
    public void setUpvotes(int upvotes) {
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

    /**
     * Constructor of the class.
     *
     * @param linux Compatibility with Linux.
     * @param mac Compatibility with macOS.
     * @param windows Compatibility with Windows.
     * @param age Age.
     * @param avg_pt Average playtime.
     * @param dlcs Number of DLCs.
     * @param gameId Game identifier.
     * @param price Price in dollars.
     * @param upvotes Positive upvotes.
     * @param developers Developers.
     * @param name Name on Steam.
     * @param owners Number of players.
     * @param website Game website.
     * @param languages Available languages.
     * @param releaseDate Release date.
     */

    public Game(boolean linux, boolean mac, boolean windows, int age,int avg_pt, int dlcs, int gameId, float price, int upvotes, String developers, String name, String owners, String website, String[] languages, Date releaseDate){
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
        this.languages = languages;
        this.releaseDate = releaseDate;
    }
    /**
     * Empty constructor.
     */
    public Game(){
        this(false, false,false,-1,-1,-1,-1,-1,-1,null,"SemNome",null,null,null,null);
    }
    /**
     * Constructor of the class.
     *
     * @param attributes String array containing the game attributes.
     * @throws ParseException se a data estiver fora do formato esperado (MMM dd, yyyy)
     */

    public Game(String[] attributes) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy");
        gameId = Integer.parseInt(attributes[0]);
        name = attributes[1];
        releaseDate = format.parse(attributes[2]);
        owners = attributes[3];
        age = Integer.parseInt(attributes[4]);
        price = Float.parseFloat(attributes[5]);
        dlcs = Integer.parseInt(attributes[6]);
        // Remove brackets and double quotes, then split the languages.
        languages = attributes[7].replaceAll("[\\[\\]']", "").split(",\\s*");
        website = attributes[8];
        windows = attributes[9].contains("True");
        mac = attributes[10].contains("True");
        linux = attributes[11].contains("True");
        upvotes = Integer.parseInt(attributes[12]);
        avg_pt = Integer.parseInt(attributes[13]);
        developers = attributes[15];
    }

    /**
     * Transforms the Game object into a byte array.
     *
     * @return Byte array containing the game attributes.
     * @throws IOException DataOutputStream exception.
     */

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        dos.writeInt(gameId);
        dos.writeUTF(name);
        dos.writeLong(releaseDate.getTime());
        dos.writeUTF(owners);
        dos.writeInt(age);
        dos.writeFloat(price);
        dos.writeInt(dlcs);
        // Languages vector length.
        dos.writeInt(languages.length);
        for (String language : languages) {
            dos.writeUTF(language);
        }
        dos.writeUTF(website);
        dos.writeBoolean(windows);
        dos.writeBoolean(mac);
        dos.writeBoolean(linux);
        dos.writeInt(upvotes);
        dos.writeInt(avg_pt);
        dos.writeUTF(developers);
        return baos.toByteArray();
    }

    /**
     *
     * @param byteArray Byte array containing the game attributes.
     * @throws IOException input/outputStream exception.
     */
    public void fromByteArray(byte[] byteArray) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
        DataInputStream dis = new DataInputStream(bais);
        gameId = dis.readInt();
        name = dis.readUTF();
        releaseDate = new Date(dis.readLong());
        owners = dis.readUTF();
        age = dis.readInt();
        price = dis.readFloat();
        dlcs = dis.readInt();
        int vectorLen = dis.readInt();
        languages = new String[vectorLen];
        for(int i=0; i<vectorLen; i++){
            languages[i] = dis.readUTF();
        }
        website = dis.readUTF();
        windows = dis.readBoolean();
        mac = dis.readBoolean();
        linux = dis.readBoolean();
        upvotes = dis.readInt();
        avg_pt = dis.readInt();
        developers = dis.readUTF();
    }
}
// id -> Name -> releaseDate -> owners -> age -> price -> dlcs -> languages -> website -> windows -> mac -> linux -> upvotes -> avgpt -> developers

