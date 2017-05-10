package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import objects.Product;
import objects.SoldProduct;
import util.MyUtils;
import util.UtilForDBWorking;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by Work on 18.04.2017.
 */
public class DialogForAddingSoldProductController {

    @FXML
    ComboBox<String> cbProductName;
    @FXML
    TextField tfSoldProductClient;
    @FXML
    TextField tfSoldProductNumber;

    @FXML
    void initialize() throws SQLException {
        List<Product> products = UtilForDBWorking.getRecords(new Product());
        ObservableList<String> forComboBox = FXCollections.observableArrayList();
        for(Product product : products){
            forComboBox.add(product.getProductName());
        }
        cbProductName.getItems().addAll(forComboBox);
    }

    public void onOk(ActionEvent actionEvent) throws SQLException, FileNotFoundException {
        if(!cbProductName.getSelectionModel().isEmpty()){
            Date dateTime = new Date();
            List<Product> products = UtilForDBWorking.getRecords(new Product());
            Product tmp=null;
            for(Product product : products){
                if(cbProductName.getSelectionModel().getSelectedItem().trim().equals(product.getProductName())){
                    tmp=product;
                    break;
                }
            }
            if((!tfSoldProductClient.getText().equals(""))&&(!tfSoldProductNumber.getText().equals(""))){
                String userName= MyUtils.readTmpFile()[0].trim();
                if(((tmp.getProductNumber())>=(Double.parseDouble(tfSoldProductNumber.getText().trim())))){
                    UtilForDBWorking.deleteRecord(tmp);
                    tmp.setProductNumber((tmp.getProductNumber())-(Double.parseDouble(tfSoldProductNumber.getText().trim())));
                    SoldProduct soldProduct = new SoldProduct(MyUtils.setUniqueId(),
                            tmp.getProductName(),userName,
                            tfSoldProductClient.getText(),
                            Double.parseDouble(tfSoldProductNumber.getText().trim()),
                            tmp.getProductPrice(),
                            dateTime);
                    UtilForDBWorking.addRecord(tmp);
                    UtilForDBWorking.addRecord(soldProduct);
                }else {
                    MyUtils.AlertError("Помилка введення","Бракує продукту! В наявності "+tmp.getProductNumber());
                }
            }else{
                MyUtils.AlertError("Помилка введення","Заповніть всі поля!");
            }
        }else {
            MyUtils.AlertError("Помилка введення","Виберіть назву продукту!");
        }
    }

    public void onCancel(ActionEvent actionEvent) {

    }
}
