package objects;

import util.MyUtils;

import java.util.Date;

/**
 * Created by Work on 24.04.2017.
 */
public class SoldProduct {
    private Long soldProductId;
    private String soldProductName;
    private String userName;
    private String clientName;
    private Double soldProductNumber;
    private Double soldProductPrice;
    private Date dateTime;

    public SoldProduct() {}

    public SoldProduct(Long soldProductCode, String soldProductName, String userName, String clientName, Double soldProductNumber, Double soldProductPrice, Date dateTime) {
        this.soldProductId= MyUtils.setUniqueId();
        this.soldProductName = soldProductName;
        this.userName = userName;
        this.clientName = clientName;
        this.soldProductNumber = soldProductNumber;
        this.soldProductPrice = soldProductPrice;
        this.dateTime = dateTime;
    }

    public Long getSoldProductId() {
        return soldProductId;
    }

    public void setSoldProductId(Long soldProductId) {
        this.soldProductId = soldProductId;
    }

    public String getSoldProductName() {
        return soldProductName;
    }

    public void setSoldProductName(String soldProductName) {
        this.soldProductName = soldProductName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Double getSoldProductNumber() {
        return soldProductNumber;
    }

    public void setSoldProductNumber(Double soldProductNumber) {
        this.soldProductNumber = soldProductNumber;
    }

    public Double getSoldProductPrice() {
        return soldProductPrice;
    }

    public void setSoldProductPrice(Double soldProductPrice) {
        this.soldProductPrice = soldProductPrice;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "SoldProduct{" +
                "soldProductId=" + soldProductId +
                ", soldProductName='" + soldProductName + '\'' +
                ", userName='" + userName + '\'' +
                ", clientName='" + clientName + '\'' +
                ", soldProductNumber=" + soldProductNumber +
                ", soldProductPrice=" + soldProductPrice +
                ", dateTime=" + dateTime +
                '}';
    }
}
