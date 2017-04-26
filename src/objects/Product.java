package objects;

/**
 * Created by Work on 26.04.2017.
 */
public class Product {
    private Long productId;
    private String productName;
    private Double productNumber;
    private Double productPrice;
    private Long productDepartmentId;

    public Product() {
    }

    public Product(Long productId, String productName, Double productNumber, Double productPrice, Long productDepartment) {
        this.productId = productId;
        this.productName = productName;
        this.productNumber = productNumber;
        this.productPrice = productPrice;
        this.productDepartmentId = productDepartment;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Double productNumber) {
        this.productNumber = productNumber;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Long getProductDepartmentId() {
        return productDepartmentId;
    }

    public void setProductDepartmentId(Long productDepartmentId) {
        this.productDepartmentId = productDepartmentId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productNumber=" + productNumber +
                ", productPrice=" + productPrice +
                ", productDepartmentId='" + productDepartmentId + '\'' +
                '}';
    }
}
