package objects;

import java.util.Date;

/**
 * Created by Work on 26.04.2017.
 */
public class Client {
    private Long clientId;
    private String clientFIO;
    private Date registerTime;

    public Client(){}

    public Client(Long clientId, String clientFIO, Date registerTime) {
        this.clientId = clientId;
        this.clientFIO = clientFIO;
        this.registerTime = registerTime;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientFIO() {
        return clientFIO;
    }

    public void setClientFIO(String clientFIO) {
        this.clientFIO = clientFIO;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", clientFIO='" + clientFIO + '\'' +
                ", registerTime=" + registerTime +
                '}';
    }
}

