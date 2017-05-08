package controllers;

import interfaces.ComingProductDao;
import interfaces.DepartmentDao;
import interfaces.SoldProductDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import objects.*;
import util.MyUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Work on 13.04.2017.
 */
public class MainWindowController {

    static final String SOLD_PRODUCT_TYPE = "soldproduc";
    static final String COMING_PRODUCT_TYPE = "comingproduc";
    static final String USER_TYPE = "user";
    static final String DEPARTMENT_TYPE = "department";

    @FXML
    ComboBox<String> cbCurrentUser;
    @FXML
    ComboBox<String> cbCurrentYear;
    @FXML
    TableView soldProductTableView;
    @FXML
    TableView comingProductTableView;
    @FXML
    TableView departmentProductTableView;
    @FXML
    BarChart<String,Number> barChartStatistic;
    @FXML
    Label lbCurrentUserName;
    @FXML
    Label lbCurrentUserStatus;
    @FXML
    TextField tfSearch;

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

    TableColumn<Department,String> tcDepartmentId=new TableColumn<>();
    TableColumn<Department,String> tcDepartmentName=new TableColumn<>();
    TableColumn<Department,String> tcDepartmentAddress=new TableColumn<>();

    ObservableList<SoldProduct> observableList = null;

    @FXML
    void initialize() throws FileNotFoundException, SQLException {
        lbCurrentUserName.setText(MyUtils.readTmpFile()[0].trim());
        lbCurrentUserStatus.setText(MyUtils.readTmpFile()[1].trim());
        if (MyUtils.readTmpFile()[1].trim().equals(Status.getUSER())) {
            cbCurrentUser.getItems().add(MyUtils.readTmpFile()[0]);
            cbCurrentUser.getSelectionModel().selectFirst();
            cbCurrentYear.getItems().addAll(MyUtils.getYearsFromDB());
            cbCurrentYear.getSelectionModel().selectFirst();
            fillForUser();
        } else if (MyUtils.readTmpFile()[1].trim().equals(Status.getMANAGER())) {
            cbCurrentUser.getItems().addAll(MyUtils.getUsersFromDB());
            cbCurrentUser.getSelectionModel().selectFirst();
            cbCurrentYear.getItems().addAll(MyUtils.getYearsFromDB());
            cbCurrentYear.getSelectionModel().selectFirst();
            fillForManager();
        }
        createBarChart();
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
                tcComingProductName,tcComingUserName,tcComingClientName,tcComingProductNumber,
                tcComingProductPrice,tcComingDateTime);
        comingProductTableView.setItems(observableList);

    }

    void fillSoldProductTable(List<SoldProduct> soldProductList){

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
    }

    void fillDepartmentTable(List<Department> departments){
        ObservableList<Department> observableList = null;
        observableList= FXCollections.observableArrayList(departments);
        tcDepartmentId.setCellValueFactory(new PropertyValueFactory<Department,String>("departmentId"));
        tcDepartmentId.setText("ID");
        tcDepartmentName.setCellValueFactory(new PropertyValueFactory<Department,String>("departmentName"));
        tcDepartmentName.setText("Назва відділу");
        tcDepartmentAddress.setCellValueFactory(new PropertyValueFactory<Department,String>("departmentAddress"));
        tcDepartmentAddress.setText("Адреса");
        if(!departmentProductTableView.getColumns().isEmpty()){
            departmentProductTableView.getColumns().clear();
        }
        departmentProductTableView.getColumns().addAll(tcDepartmentId,
                tcDepartmentName,tcDepartmentAddress);
        departmentProductTableView.setItems(observableList);
    }

    public void fillForUser() throws FileNotFoundException {
        SoldProductDao soldProductDao = CFactory.getInstance().getSoldProductDao();
        ComingProductDao comingProductDao = CFactory.getInstance().getComingProductDao();
        String[] strings = MyUtils.readTmpFile();
        List<ComingProduct> comingProducts = null;
        List<SoldProduct> soldProducts = null;
        if(strings[0].trim().equals(MyUtils.ALL)){
            try{
                soldProducts = soldProductDao.getSoldProducts();
                comingProducts = comingProductDao.getComingProducts();
                fillSoldProductTable(soldProducts);
                fillComingProductTable(comingProducts);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }else {
            try{
                soldProducts = soldProductDao.getSoldProducts(strings[0].trim());
                comingProducts = comingProductDao.getComingProducts(strings[0].trim());
                fillSoldProductTable(soldProducts);
                fillComingProductTable(comingProducts);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    private void fillForManager() throws SQLException, FileNotFoundException {
        fillForUser();
        DepartmentDao departmentDao = CFactory.getInstance().getDepartmentDao();
        fillDepartmentTable(departmentDao.getDepartment());
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

    private void createSampleByDay(){

    }
    private void createSampleByMonth(){

    }
    private void createSampleByYear(){

    }

    public void onMbAddProduct(ActionEvent actionEvent) {

    }

    public void onMbAddProvider(ActionEvent actionEvent) {
    }

    public void onMbAddDepartment(ActionEvent actionEvent) {
    }

    public void onMbDeleteProduct(ActionEvent actionEvent) {
    }

    public void onMbDeleteProvider(ActionEvent actionEvent) {
    }

    public void onMbDeleteDepartment(ActionEvent actionEvent) {

    }

    public void onUpdateData() throws IOException, SQLException {
        MyUtils.writeTmpFile(cbCurrentUser.getSelectionModel().getSelectedItem(),
                            MyUtils.getStatusOfUser(cbCurrentUser.getSelectionModel().getSelectedItem()));
        fillForUser();
        createBarChart();
        if(lbCurrentUserStatus.getText().trim().equals(Status.getMANAGER())){
            fillForManager();
        }

    }

    private void createBarChart() throws FileNotFoundException, SQLException {
        barChartStatistic.getData().clear();
        ArrayList<String> usersForChart=new ArrayList<String>();
        String currentChoice = MyUtils.readTmpFile()[0].trim();
        if(currentChoice.trim().equals(MyUtils.ALL.trim())){
            List<User> users = CFactory.getInstance().getUserDao().getUser();
            for(User user : users){
                usersForChart.add(user.getUsername());
            }
        }else {
            usersForChart.add(currentChoice);
        }
        DataForChart dataForChart = MyUtils.prepareDataForChart(usersForChart,cbCurrentYear.getSelectionModel().getSelectedItem());
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        barChartStatistic.setTitle(dataForChart.getChartTitle());
        xAxis.setLabel(dataForChart.getxLabel());
        yAxis.setLabel(dataForChart.getyLabel());

        ArrayList<XYChart.Series> series = new ArrayList<XYChart.Series>();
        int num = 0;
        for (String seriaTitle : dataForChart.getSeriaTitles())   {
            XYChart.Series ser = new XYChart.Series();
            ser.setName(seriaTitle);
            ArrayList<Double> arrayList = new ArrayList<Double>();
            arrayList= dataForChart.getSeriaValues().get(num);
            num++;
            int numOfData =0;
            for (String title : dataForChart.getDataTitles()){
                ser.getData().add(new XYChart.Data(title,arrayList.get(numOfData) ));
                numOfData++;
            }
            barChartStatistic.getData().add(ser);
        }

    }
    public static <T> ObservableList<T> filterForTable(String type,ObservableList<T> observableList,TextField filterField) {
        ObservableList<T> newObservableList = FXCollections.observableArrayList();
        final String filteredString = filterField.getText().trim();
        switch (type){
            case SOLD_PRODUCT_TYPE:{
                for (SoldProduct soldProduct : ((ObservableList<SoldProduct>)observableList)){
                    if (soldProduct.getUserName().contains(filteredString)){
                        newObservableList.add((T)soldProduct);
                    }
                }
            };break;
            case COMING_PRODUCT_TYPE:{

            };break;
            case USER_TYPE:{

            };break;
            case DEPARTMENT_TYPE:{

            };break;
        }
        return newObservableList;
    }

    public void onSearch(Event actionEvent) {
        soldProductTableView.setItems(filterForTable(SOLD_PRODUCT_TYPE,observableList,tfSearch));
    }
}
