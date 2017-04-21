package interfaces;

/**
 * Created by Work on 19.04.2017.
 */
public interface Login {
    public boolean verify(String username, String password);
    public String getStatusOfUser(String username, String password);

}
