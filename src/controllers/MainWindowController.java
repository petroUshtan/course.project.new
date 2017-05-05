package controllers;

import interfaces.ComingProductDao;
import interfaces.SoldProductDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import objects.ComingProduct;
import objects.SoldProduct;
import util.MyUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Work on 13.04.2017.
 */
public class MainWindowController {
    @FXML
    ListView<String> usersListView;
    @FXML
    TableView soldProductTableView;
    @FXML
    TableView comingProductTableView;

    TableColumn<SoldProduct,String> tcsoldProductId=new TableColumn<>();
    TableColumn<SoldProduct,String> tcSoldProductName=new TableColumn<>();
    TableColumn<SoldProduct,String> tcSoldUserName=new TableColumn<>();
    TableColumn<SoldProduct,String> tcSoldClientName=new TableColumn<>();
    TableColumn<SoldProduct,String> tcSoldProductNumber=new TableColumn<>();
    TableColumn<SoldProduct,String> tcSoldProductPrice=new TableColumn<>();
    TableColumn<SoldProduct,String> tcSoldDateTime=new TableColumn<>();

    TableColumn<ComingProduct,String> tcComingProductId=new TableColumn<>();
    TableColumn<ComingProduct,String> tcComingProductName=new TableColumn<>();
    TableColumn<ComingProduct,String> tcComingUserName=new TableColumn<>();
    TableColumn<ComingProduct,String> tcComingClientName=new TableColumn<>();
    TableColumn<ComingProduct,String> tcComingProductNumber=new TableColumn<>();
    TableColumn<ComingProduct,String> tcComingProductPrice=new TableColumn<>();
    TableColumn<ComingProduct,String> tcComingDateTime=new TableColumn<>();



    @FXML
    void initialize() throws FileNotFoundException {
            fillForUser();
    }

    void fillComingProductTable(List<ComingProduct> comingProducts){
        ObservableList<ComingProduct> observableList = null;
        observableList= FXCollections.observableArrayList(comingProducts);

        tcComingProductId.setCellValueFactory(new PropertyValueFactory<ComingProduct,String>("comingProductId"));
        tcComingProductId.setText("ID");
        tcComingProductName.setCellValueFactory(new PropertyValueFactory<ComingProduct,String>("comingProductName"));
        tcComingProductName.setText("Назва продукту");
        tcComingUserName.setCellValueFactory(new PropertyValueFactory<ComingProduct,String>("userName"));
        tcComingUserName.setText("Продавець");
        tcComingClientName.setCellValueFactory(new PropertyValueFactory<ComingProduct,String>("clientName"));
        tcComingClientName.setText("Клієнт");
        tcComingProductNumber.setCellValueFactory(new PropertyValueFactory<ComingProduct,String>("comingProductNumber"));
        tcComingProductNumber.setText("Кількість");
        tcComingProductPrice.setCellValueFactory(new PropertyValueFactory<ComingProduct,String>("comingProductPrice"));
        tcComingProductPrice.setText("Ціна");
        tcComingDateTime.setCellValueFactory(new PropertyValueFactory<ComingProduct,String>("dateTime"));
        tcComingDateTime.setText("Дата");
        if(!comingProductTableView.getColumns().isEmpty()){
            comingProductTableView.getColumns().clear();
        }
        comingProductTableView.getColumns().addAll(tcComingProductId,
                tcComingProductName,tcComingUserName,tcComingClientName,tcComingProductNumber,tcComingProductPrice,tcComingDateTime);
        comingProductTableView.setItems(observableList);
//        tcUsername.setCellValueFactory(new PropertyValueFactory<SoldProduct,String>("password"));
//        tcStatus.setCellValueFactory(new PropertyValueFactory<SoldProduct, String>("status"));
//        tvUserList.setItems(observableList);

    }

    void fillSoldProductTable(List<SoldProduct> soldProductList){
        ObservableList<SoldProduct> observableList = null;
        observableList= FXCollections.observableArrayList(soldProductList);

        tcsoldProductId.setCellValueFactory(new PropertyValueFactory<SoldProduct,String>("soldProductId"));
        tcsoldProductId.setText("ID");
        tcSoldProductName.setCellValueFactory(new PropertyValueFactory<SoldProduct,String>("soldProductName"));
        tcSoldProductName.setText("Назва продукту");
        tcSoldUserName.setCellValueFactory(new PropertyValueFactory<SoldProduct,String>("userName"));
        tcSoldUserName.setText("Продавець");
        tcSoldClientName.setCellValueFactory(new PropertyValueFactory<SoldProduct,String>("clientName"));
        tcSoldClientName.setText("Клієнт");
        tcSoldProductNumber.setCellValueFactory(new PropertyValueFactory<SoldProduct,String>("soldProductNumber"));
        tcSoldProductNumber.setText("Кількість");
        tcSoldProductPrice.setCellValueFactory(new PropertyValueFactory<SoldProduct,String>("soldProductPrice"));
        tcSoldProductPrice.setText("Ціна");
        tcSoldDateTime.setCellValueFactory(new PropertyValueFactory<SoldProduct,String>("dateTime"));
        tcSoldDateTime.setText("Дата");

        if(!soldProductTableView.getColumns().isEmpty()){
            soldProductTableView.getColumns().clear();
        }
        soldProductTableView.getColumns().addAll(tcsoldProductId,
                tcSoldProductName,tcSoldUserName,tcSoldClientName,tcSoldProductNumber,tcSoldProductPrice,tcSoldDateTime);
        soldProductTableView.setItems(observableList);
//        tcUsername.setCellValueFactory(new PropertyValueFactory<SoldProduct,String>("password"));
//        tcStatus.setCellValueFactory(new PropertyValueFactory<SoldProduct, String>("status"));
//        tvUserList.setItems(observableList);

    }

    public void fillForUser() throws FileNotFoundException {
        SoldProductDao soldProductDao = CFactory.getInstance().getSoldProductDao();
        ComingProductDao comingProductDao = CFactory.getInstance().getComingProductDao();
        String[] strings = MyUtils.readTmpFile();
        List<ComingProduct> comingProducts = null;
        List<SoldProduct> soldProducts = null;
        try{
            soldProducts = soldProductDao.getSoldProducts(strings[0].trim());
            comingProducts = comingProductDao.getComingProducts(strings[0].trim());
            fillSoldProductTable(soldProducts);
            fillComingProductTable(comingProducts);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    private void fillForManager(){

    }

    public void onExcelReport(ActionEvent actionEvent) {
    }

    public void onWordReport(ActionEvent actionEvent) {
    }

    public void onMbHelp(ActionEvent actionEvent) {
    }

    public void onMbAddSoldProduct(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            Parent rootNode = null;
            rootNode = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/DialogForSoldProduct.fxml"));
            Scene scene = new Scene(rootNode);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setOnCloseRequest((WindowEvent we) -> {
                try {
                    fillForUser();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            });
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onMbAddComingProduct(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            Parent rootNode = null;
            rootNode = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/DialogForComingProduct.fxml"));
            Scene scene = new Scene(rootNode);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);
//            stage.initOwner(((Stage)actionEvent.getSource()).getScene().getWindow());
            stage.setOnCloseRequest((WindowEvent we) -> {
                try {
                    fillForUser();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            });
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onMbDeleteSoldProduct(ActionEvent actionEvent) throws SQLException, FileNotFoundException {
         deleteSoldProduct((SoldProduct)soldProductTableView.getSelectionModel().getSelectedItem(),
                 CFactory.getInstance().getSoldProductDao());
    }

    public void onMbDeleteCommingProduct(ActionEvent actionEvent) throws FileNotFoundException {
        deleteComingProduct((ComingProduct) comingProductTableView.getSelectionModel().getSelectedItem(),
                CFactory.getInstance().getComingProductDao());
    }

    private void deleteSoldProduct(SoldProduct soldProduct,SoldProductDao soldProductDao) throws FileNotFoundException {

        try {
            if(soldProduct!=null){
                soldProductDao.deleteSoldProduct(soldProduct);
            }else {
                MyUtils.AlertError("Помилка видалення", "Виберіть запис!");
            }
            fillForUser();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteComingProduct(ComingProduct comingProduct,ComingProductDao comingProductDao) throws FileNotFoundException {

        try {
            if(comingProduct!=null){
                comingProductDao.deleteComingProduct(comingProduct);
            }else {
                MyUtils.AlertError("Помилка видалення", "Виберіть запис!");
            }
            fillForUser();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
