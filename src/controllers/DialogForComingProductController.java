package controllers;

import interfaces.ComingProductDao;
import interfaces.ProductDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import objects.ComingProduct;
import objects.Product;
import util.MyUtils;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Work on 26.04.2017.
 */
public class DialogForComingProductController {
    @FXML
    TextField tfComingProductName;
    @FXML
    TextField tfComingProductClient;
    @FXML
    TextField tfComingProductNumber;
    @FXML
    TextField tfComingProductPrice;
    @FXML
    TextField tfComingProductDepartment;


    public void onOK(ActionEvent actionEvent) throws SQLException, FileNotFoundException {
        ProductDao productDao = CFactory.getInstance().getProductDao();
        ComingProductDao comingProductDao = CFactory.getInstance().getComingProductDao();
        Date dateTime = new Date();
        Product product = new Product(tfComingProductName.getText(),Double.parseDouble(tfComingProductNumber.getText()),
                Double.parseDouble(tfComingProductPrice.getText()),Long.parseLong(tfComingProductDepartment.getText()));
        ComingProduct comingProduct = new ComingProduct(tfComingProductName.getText(),
                MyUtils.readTmpFile()[0].trim(),tfComingProductClient.getText(),Double.parseDouble(tfComingProductNumber.getText()),
                Double.parseDouble(tfComingProductPrice.getText()),dateTime );

        productDao.addProduct(product);
        comingProductDao.addComingProduct(comingProduct);
    }

    public void onCancel(ActionEvent actionEvent) {

    }

}
