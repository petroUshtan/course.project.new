package interfaces;

import objects.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Work on 18.04.2017.
 */
public interface ProductDao {

    public void addProduct(Product product) throws SQLException;
    public void deleteProduct(Product product) throws SQLException;
    public Product getProduct(Long id) throws SQLException;
    public List<Product>  getProducts() throws SQLException;
    public void clearTable() throws SQLException;
}
