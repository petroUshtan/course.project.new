package interfaces;

import objects.ComingProduct;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Work on 18.04.2017.
 */
public interface ComingProductDao {

    public void addComingProduct(ComingProduct coming) throws SQLException;
    public void deleteComingProduct(ComingProduct coming) throws SQLException;
    public ComingProduct getComingProduct(Long id) throws SQLException;
    public List<ComingProduct>  getComingProducts() throws SQLException;
    public void clearTable() throws SQLException;
}
