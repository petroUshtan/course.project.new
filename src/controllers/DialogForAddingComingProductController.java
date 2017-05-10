package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import objects.ComingProduct;
import objects.Product;
import util.MyUtils;
import util.UtilForDBWorking;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by Work on 26.04.2017.
 */
public class DialogForAddingComingProductController {
    @FXML
    ComboBox<String> cbProductName;
    @FXML
    TextField tfComingProductClient;
    @FXML
    TextField tfComingProductNumber;

    @FXML
    void initialize() throws SQLException {
        List<Product> products = UtilForDBWorking.getRecords(new Product());
        ObservableList<String> forComboBox = FXCollections.observableArrayList();
        for(Product product : products){
            forComboBox.add(product.getProductName());
        }
        cbProductName.getItems().addAll(forComboBox);
    }
    public void onOK(ActionEvent actionEvent) throws SQLException, FileNotFoundException {
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
            if((!tfComingProductClient.getText().equals(""))&&(!tfComingProductNumber.getText().equals(""))){
                UtilForDBWorking.deleteRecord(tmp);
                tmp.setProductNumber((tmp.getProductNumber())+(Double.parseDouble(tfComingProductNumber.getText().trim())));
                ComingProduct comingProduct = new ComingProduct(tmp.getProductName(),
                        MyUtils.readTmpFile()[0].trim(),tfComingProductClient.getText(),Double.parseDouble(tfComingProductNumber.getText()),
                        tmp.getProductPrice(),dateTime );
                UtilForDBWorking.addRecord(tmp);
                UtilForDBWorking.addRecord(comingProduct);
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
