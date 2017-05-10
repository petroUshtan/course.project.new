package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import objects.Department;
import util.MyUtils;
import util.UtilForDBWorking;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Work on 06.05.2017.
 */
public class DialogForAddingDepartmentController {

    @FXML
    TextField tfDepartmentName;
    @FXML
    TextField tfDepartmentAddress;


    public void onOK(ActionEvent actionEvent) throws SQLException {
        List<Department> departments = UtilForDBWorking.getRecords(new Department());
        ArrayList<String> strings = new ArrayList<>();
        for (Department department : departments) {
            strings.add(department.getDepartmentName());
        }
        if ((!tfDepartmentAddress.getText().equals("")) && (!tfDepartmentName.getText().equals(""))) {
            if (MyUtils.isUniqInArray(strings, tfDepartmentName.getText())) {
                Department department = new Department(tfDepartmentAddress.getText(),
                        tfDepartmentName.getText());
                UtilForDBWorking.addRecord(department);
            } else {
                MyUtils.AlertError("Помилка введення", "Даний відділ вже існує!");
            }
        } else {
            MyUtils.AlertError("Помилка введення", "Заповніть всі поля!");
        }

    }

    public void onCancel(ActionEvent actionEvent) {

    }
}
