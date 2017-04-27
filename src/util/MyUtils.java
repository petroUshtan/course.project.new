package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Work on 27.04.2017.
 */
public class MyUtils {
    public static String[] readTmpFile() throws FileNotFoundException {
        String content = new Scanner(new File("tmp.txt")).useDelimiter("\\Z").next();
        String[] strings = content.split("         ");
        System.out.println("|"+strings[0]+"|");
        System.out.println("|"+strings[1]+"|");
        return strings;
    }

    public static Long setUniqueId(){
        return System.currentTimeMillis();
    }
}
