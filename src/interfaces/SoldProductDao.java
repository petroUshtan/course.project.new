package interfaces;

import objects.SoldProduct;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Work on 18.04.2017.
 */
public interface SoldProductDao {

    public void addSoldProduct(SoldProduct soldProduct) throws SQLException;
    public void deleteSoldProduct(SoldProduct soldProduct) throws SQLException;
    public SoldProduct getSoldProduct(Long id) throws SQLException;
    public List<SoldProduct>  getSoldProducts() throws SQLException;
    public List<SoldProduct>  getSoldProducts(String userName) throws SQLException;
    public void clearTable() throws SQLException;


}
