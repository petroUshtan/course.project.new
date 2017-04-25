package controllers;

import impls.SoldProductDaoImplDB;
import interfaces.SoldProductDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import objects.SoldProduct;

import java.sql.SQLException;

/**
 * Created by Work on 13.04.2017.
 */
public class MainWindowController {
    @FXML
    ListView<String> usersListView;
    @FXML
    TableView soldProductTableView;

    TableColumn<SoldProduct,String> tcsoldProductId=new TableColumn<>();
    TableColumn<SoldProduct,String> tcSoldProductName=new TableColumn<>();
    TableColumn<SoldProduct,String> tcSoldProductCode=new TableColumn<>();
    TableColumn<SoldProduct,String> tcUserName=new TableColumn<>();
    TableColumn<SoldProduct,String> tcClientName=new TableColumn<>();
    TableColumn<SoldProduct,String> tcSoldProductNumber=new TableColumn<>();
    TableColumn<SoldProduct,String> tcSoldProductPrice=new TableColumn<>();
    TableColumn<SoldProduct,String> tcDateTime=new TableColumn<>();
@FXML
    void initialize() {
        this.fillSoldProductTable();
    }

    void fillSoldProductTable(){
        System.out.println("kjhvcjghkjljcfxhcghjkgxhcfhj");
        SoldProductDao soldProductDao = new SoldProductDaoImplDB();
        ObservableList<SoldProduct> observableList = null;
        try {
            observableList= FXCollections.observableArrayList(soldProductDao.getSoldProducts());
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        tcsoldProductId.setCellValueFactory(new PropertyValueFactory<SoldProduct,String>("soldProductId"));
        tcsoldProductId.setText("ID");
        tcSoldProductCode.setCellValueFactory(new PropertyValueFactory<SoldProduct,String>("soldProductCode"));
        tcSoldProductCode.setText("Код продукту");
        tcSoldProductName.setCellValueFactory(new PropertyValueFactory<SoldProduct,String>("soldProductName"));
        tcSoldProductName.setText("Назва продукту");
        tcUserName.setCellValueFactory(new PropertyValueFactory<SoldProduct,String>("userName"));
        tcUserName.setText("Продавець");
        tcClientName.setCellValueFactory(new PropertyValueFactory<SoldProduct,String>("clientName"));
        tcClientName.setText("Клієнт");
        tcSoldProductNumber.setCellValueFactory(new PropertyValueFactory<SoldProduct,String>("soldProductNumber"));
        tcSoldProductNumber.setText("Кількість");
        tcSoldProductPrice.setCellValueFactory(new PropertyValueFactory<SoldProduct,String>("soldProductPrice"));
        tcSoldProductPrice.setText("Ціна");
        tcDateTime.setCellValueFactory(new PropertyValueFactory<SoldProduct,String>("dateTime"));
        tcDateTime.setText("Дата");
        soldProductTableView.getColumns().addAll(tcsoldProductId,tcSoldProductCode,
                tcSoldProductName,tcUserName,tcClientName,tcSoldProductNumber,tcSoldProductPrice,tcDateTime);
        soldProductTableView.setItems(observableList);
//        tcUsername.setCellValueFactory(new PropertyValueFactory<SoldProduct,String>("password"));
//        tcStatus.setCellValueFactory(new PropertyValueFactory<SoldProduct, String>("status"));
//        tvUserList.setItems(observableList);

    }
}
