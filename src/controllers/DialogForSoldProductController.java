package controllers;

import impls.ProductDaoImplDB;
import impls.SoldProductDaoImplDB;
import interfaces.ProductDao;
import interfaces.SoldProductDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import objects.Product;
import objects.SoldProduct;
import util.MyUtils;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Work on 18.04.2017.
 */
public class DialogForSoldProductController {

    @FXML
    TextField tfSoldProductCode;
    @FXML
    TextField tfSoldProductClient;
    @FXML
    TextField tfSoldProductNumber;


    public void onOk(ActionEvent actionEvent) throws SQLException, FileNotFoundException {
        SoldProductDao soldProductDao = new SoldProductDaoImplDB();
        ProductDao productDao = new ProductDaoImplDB();
        Product product = productDao.getProduct(Long.parseLong(tfSoldProductCode.getText()));
        Long currentTime = new Long(System.currentTimeMillis());

        String soldProductName = product.getProductName();
        String clientName = tfSoldProductClient.getText();
        String userName= MyUtils.readTmpFile()[0].trim();
        Double soldProductNumber = Double.parseDouble(tfSoldProductNumber.getText());
        Double soldProductPrice = product.getProductPrice();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date dateTime = new Date();
        SoldProduct soldProduct = new SoldProduct(currentTime,soldProductName,userName,clientName,
                soldProductNumber,soldProductPrice,dateTime);
//        System.out.println(soldProduct);
        soldProductDao.addSoldProduct(soldProduct);
    }

    public void onCancel(ActionEvent actionEvent) {

    }
}
