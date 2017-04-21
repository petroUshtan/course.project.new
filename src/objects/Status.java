package objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Work on 20.04.2017.
 */
public class Status {
    String status;

    public Status() {
        this.status = "USER";
    }

    public void setUSER() {
        this.status = "USER";
    }

    public void setADMIN() {
        this.status = "ADMIN";
    }

    public void setMANAGER() {
        this.status = "MANAGER";
    }

    public String getADMIN() {
        return "ADMIN";
    }

    public String getUSER() {
        return "USER";
    }

    public String getMANAGER() {
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
