package util;

import javafx.scene.control.Alert;

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

    public static void AlertError(String title,String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}
