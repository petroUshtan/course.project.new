package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import objects.Product;
import util.MyUtils;
import util.UtilForDBWorking;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Work on 10.05.2017.
 */
public class DialogForDeletingProductController {
    @FXML
    ComboBox<String> cbProductName;
    @FXML
    void initialize() throws SQLException {
        List<Product> products = UtilForDBWorking.getRecords(new Product());
        ObservableList<String> stringObservableList = FXCollections.observableArrayList();
        for (Product p : products){
            stringObservableList.add(p.getProductName());
        }
        cbProductName.getItems().addAll(stringObservableList);
    }

    public void onOK(ActionEvent actionEvent) throws SQLException {
        if(!cbProductName.getSelectionModel().isEmpty()){
            List<Product> products = UtilForDBWorking.getRecords(new Product());
            for (Product product : products){
                if(product.getProductName().trim().equals(cbProductName.getSelectionModel().getSelectedItem().trim())){
                    UtilForDBWorking.deleteRecord(product);
                    break;
                }
            }

        }else {
            MyUtils.AlertError("Помилка введення","Виберіть продукта для видалення!");
        }
    }

    public void onCancel(ActionEvent actionEvent) {

    }
}
