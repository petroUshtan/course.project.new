package objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Work on 20.04.2017.
 */
public class Status {
    private static String status;

    public Status() {
        this.status = "USER";
    }

    public static void setUSER() {
        status = "USER";
    }

    public static void setADMIN() {
        status = "ADMIN";
    }

    public static void setMANAGER() {
        status = "MANAGER";
    }

    public static String getADMIN() {
        return "ADMIN";
    }

    public static String getUSER() {
        return "USER";
    }

    public static String getMANAGER() {
        return "MANAGER";
    }

    public static List<String> getStatuses() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("ADMIN");
        list.add("MANAGER");
        list.add("USER");
        return list;
    }
}
