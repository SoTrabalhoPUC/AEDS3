package data.db;
/**
 * Class responsible for handling CSV files.
 * This class provides methods to process data in the CSV file containing game records.
 */
public class DataParser {
    /**
     * Processes the string containing game data.
     * @param gameAtts The game string.
     * @return Separated attributes in an array.
     */
    public static String[] parse(String gameAtts){
       String[] tmp = gameAtts.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
       String[] out = new String[16];
       //Calculate upvotes
       int upVotes = Integer.parseInt(tmp[12]) - Integer.parseInt(tmp[13]);
       out[12] = Integer.toString(upVotes);
       System.arraycopy(tmp, 0, out, 0, 12);
       System.arraycopy(tmp, 13, out, 13, out.length - 13);
       //Remove double quotes.
       out[2] = out[2].replaceAll("\"", "");
       return  out;
   }

}
