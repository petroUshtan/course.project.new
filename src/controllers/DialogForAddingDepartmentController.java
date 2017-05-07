package controllers;

import interfaces.DepartmentDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import objects.Department;
import util.MyUtils;

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
        DepartmentDao departmentDao = CFactory.getInstance().getDepartmentDao();
        if((!tfDepartmentAddress.getText().equals(""))&&(!tfDepartmentName.getText().equals(""))){
            Department department = new Department(tfDepartmentAddress.getText(),
                    tfDepartmentName.getText());
            departmentDao.addDepartment(department);
        }else {
            MyUtils.AlertError("Помилка введення","Заповніть всі поля!");
        }
    }

    public void onCancel(ActionEvent actionEvent) {

    }
}
