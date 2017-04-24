package controllers;

import impls.SoldProductDaoImplDB;
import interfaces.SoldProductDao;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;

/**
 * Created by Work on 13.04.2017.
 */
public class MainWindowController {
    @FXML
    ListView<String> usersListView;
    @FXML
    TableView<String> soldProductTableView;


    void initialize() {

    }

    void fillSoldProductTable(){
        SoldProductDao soldProductDao = new SoldProductDaoImplDB();
//        List<String>
    }
}
