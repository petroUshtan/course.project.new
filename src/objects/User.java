package objects;

/**
 * Created by Work on 12.04.2017.
 */
public class User {
    private String username;
    private String password;
    private int id;

    public User() {
        this.username = "1";
        this.password = "1";
        this.id = 125;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}