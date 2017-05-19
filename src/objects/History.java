package objects;

import util.MyUtils;

import java.util.Date;

/**
 * Created by Work on 19.05.2017.
 */
public class History {
    Long id;
    String date;

    public History() {
        id= MyUtils.setUniqueId();
        date=new Date().toString();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
