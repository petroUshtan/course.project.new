package objects;

import util.MyUtils;

import java.util.Date;

/**
 * Created by Work on 26.04.2017.
 */
public class Provider {
    private Long providerId;
    private String companyName;
    private String companyRepresentative;
    private Date registerTimer;

    public Provider() {
    }

    public Provider(String companyName, String companyRepresentative, Date registerTimer) {
        this.providerId = MyUtils.setUniqueId();
        this.companyName = companyName;
        this.companyRepresentative = companyRepresentative;
        this.registerTimer = registerTimer;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyRepresentative() {
        return companyRepresentative;
    }

    public void setCompanyRepresentative(String companyRepresentative) {
        this.companyRepresentative = companyRepresentative;
    }

    public Date getRegisterTimer() {
        return registerTimer;
    }

    public void setRegisterTimer(Date registerTimer) {
        this.registerTimer = registerTimer;
    }
}

