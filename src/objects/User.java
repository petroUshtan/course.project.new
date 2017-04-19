package objects;

/**
 * Created by Work on 12.04.2017.
 */
public class User {
    private String username;
    private String password;
    private Long id;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.id = generateId();
    }

    public User() {
        this.username = "username";
        this.password = "password";
        this.id = generateId();
    }

    long generateId(){
        return System.currentTimeMillis();
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }
}