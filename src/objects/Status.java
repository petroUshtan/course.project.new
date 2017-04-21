package objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Work on 20.04.2017.
 */
public class Status {
    String status;

    Status(){
        this.status="USER";
    }

    void setUSER(){
        this.status="USER";
    }
    void setADMIN(){
        this.status="ADMIN";
    }
    void setMANAGER(){
        this.status="MANAGER";
    }
    String getStatus(){
        return status;
    }

    public static List<String> getStatuses(){
        ArrayList<String> list = new ArrayList<String>();
        list.add("ADMIN");
        list.add("MANAGER");
        list.add("USER");
        return list;
    }
}
