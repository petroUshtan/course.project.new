package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import objects.Department;
import util.MyUtils;
import util.UtilForDBWorking;

import java.awt.*;
import java.sql.SQLException;

/**
 * Created by Work on 06.05.2017.
 */
public class DialogForAddingDepartmentController {

    @FXML
    TextField tfDepartmentName;
    @FXML
    TextField tfDepartmentAddress;


    public void onOK(ActionEvent actionEvent) throws SQLException {
        if((!tfDepartmentAddress.getText().equals(""))&&(!tfDepartmentName.getText().equals(""))){
            Department department = new Department(tfDepartmentAddress.getText(),
                    tfDepartmentName.getText());
            UtilForDBWorking.addRecord(department);
        }else {
            MyUtils.AlertError("Помилка введення","Заповніть всі поля!");
        }
    }

    public void onCancel(ActionEvent actionEvent) {

    }
}
