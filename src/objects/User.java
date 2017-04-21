package objects;

/**
 * Created by Work on 12.04.2017.
 */
public class User {
    private String username;
    private String status;
    private String password;
    private Long id;

    public User(String username, String password, String status) {
        this.username = username;
        this.password = password;
        this.status = status;
        this.id = generateId();
    }

    public User() {
        this.username = "username";
        this.password = "password";
        this.status = "USER";
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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