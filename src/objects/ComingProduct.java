package objects;

import java.util.Date;

/**
 * Created by Work on 24.04.2017.
 */
public class ComingProduct {
    private Long comingProductId;
    private Long comingProductCode;
    private String comingProductName;
    private String userName;
    private String clientName;
    private Double comingProductNumber;
    private Double comingProductPrice;
    private Date dateTime;

    public ComingProduct() {
    }

    public ComingProduct(Long comingProductId, Long comingProductCode, String comingProductName, String userName, String clientName, Double comingProductNumber, Double comingProductPrice, Date dateTime) {
        this.comingProductId = comingProductId;
        this.comingProductCode = comingProductCode;
        this.comingProductName = comingProductName;
        this.userName = userName;
        this.clientName = clientName;
        this.comingProductNumber = comingProductNumber;
        this.comingProductPrice = comingProductPrice;
        this.dateTime = dateTime;
    }

    public Long getComingProductId() {
        return comingProductId;
    }

    public void setComingProductId(Long comingProductId) {
        this.comingProductId = comingProductId;
    }

    public Long getComingProductCode() {
        return comingProductCode;
    }

    public void setComingProductCode(Long comingProductCode) {
        this.comingProductCode = comingProductCode;
    }

    public String getComingProductName() {
        return comingProductName;
    }

    public void setComingProductName(String comingProductName) {
        this.comingProductName = comingProductName;
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

    public Double getComingProductNumber() {
        return comingProductNumber;
    }

    public void setComingProductNumber(Double comingProductNumber) {
        this.comingProductNumber = comingProductNumber;
    }

    public Double getComingProductPrice() {
        return comingProductPrice;
    }

    public void setComingProductPrice(Double comingProductPrice) {
        this.comingProductPrice = comingProductPrice;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return comingProductId +comingProductCode+ comingProductName + userName +
                clientName + comingProductNumber + comingProductPrice + dateTime;
    }
}
