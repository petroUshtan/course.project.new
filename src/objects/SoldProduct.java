package objects;

/**
 * Created by Work on 24.04.2017.
 */
public class SoldProduct {
    private Long soldProductId;
    private Long soldProductCode;
    private String soldProductName;
    private String userName;
    private String clientName;
    private Double soldProductNumber;
    private Double soldProductPrice;
    private Long dateTime;

    public SoldProduct() {}

    public SoldProduct(Long soldProductCode, String soldProductName, String userName, String clientName, Double soldProductNumber, Double soldProductPrice, Long dateTime) {
        this.soldProductCode = soldProductCode;
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

    public Long getSoldProductCode() {
        return soldProductCode;
    }

    public void setSoldProductCode(Long soldProductCode) {
        this.soldProductCode = soldProductCode;
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

    public Long getDateTime() {
        return dateTime;
    }

    public void setDateTime(Long dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "SoldProduct{" +
                "soldProductId=" + soldProductId +
                ", soldProductCode=" + soldProductCode +
                ", soldProductName='" + soldProductName + '\'' +
                ", userName='" + userName + '\'' +
                ", clientName='" + clientName + '\'' +
                ", soldProductNumber=" + soldProductNumber +
                ", soldProductPrice=" + soldProductPrice +
                ", dateTime=" + dateTime +
                '}';
    }
}
