package data;

import java.util.Arrays;

public class DataParser {
    @notNull public static String[] parseAtts(String gameAtts){
        int vecIndex = 0;
        int stringIndex = 0;
        String[] out = new String[16];
        //Initialize Strings
        Arrays.fill(out, "");
        //getId
        while(gameAtts.charAt(stringIndex) != ','){
            out[vecIndex]+=gameAtts.charAt(stringIndex++);
        }
        System.out.println(sizeOf);

        return out;
    }
}
