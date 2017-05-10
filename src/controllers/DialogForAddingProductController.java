package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import objects.Department;
import objects.Product;
import util.MyUtils;
import util.UtilForDBWorking;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Work on 06.05.2017.
 */
public class DialogForAddingProductController {
    @FXML
    TextField tfProductName;
    @FXML
    TextField tfProductPrice;
    @FXML
    ComboBox<String > cbDepartment;

    @FXML
    void initialize() throws SQLException {
        List<Department> departments = UtilForDBWorking.getRecords(new Department());
        ObservableList<String> forComboBox = FXCollections.observableArrayList();
        for(Department department : departments){
            forComboBox.add(department.getDepartmentName());
        }
        cbDepartment.getItems().addAll(forComboBox);
    }

    public void onOK(ActionEvent actionEvent){
        try {
            if(!cbDepartment.getSelectionModel().isEmpty()){
                Department department = (Department) UtilForDBWorking.getRecordByFieldValue(new Department(),
                        "departmentName",
                        cbDepartment.getSelectionModel().getSelectedItem().trim()).get(0);
                if((!tfProductName.getText().equals(""))&&(!tfProductPrice.getText().equals(""))){
                    Product product = new Product(tfProductName.getText().trim(),
                            0D,
                            Double.parseDouble(tfProductPrice.getText().trim()),
                            department.getDepartmentId());
                    UtilForDBWorking.addRecord(product);
                }else {
                    MyUtils.AlertError("Помилка введення","Заповніть всі поля!");
                }
            }else {
                MyUtils.AlertError("Помилка введення","Виберіть відділ!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onCancel(ActionEvent actionEvent) {

    }
}
