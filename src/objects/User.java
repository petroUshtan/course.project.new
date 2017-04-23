package objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Work on 12.04.2017.
 */


@Entity
@Table(name = "users")
public class User {
    @Column(name = "username")
    private String username;

    @Column(name = "status")
    private String status;

    @Column(name = "password")
    private String password;

    @Id
    @Column(name = "id")
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
                ", status='" + status + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }
}