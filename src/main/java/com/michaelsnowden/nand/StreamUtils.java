package com.michaelsnowden.nand;

/**
 * @author michael.snowden
 */
public class StreamUtils {
    public static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
