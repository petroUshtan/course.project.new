package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import util.UtilForDBWorking;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static util.MyUtils.filterForTable;


/**
 * Created by Work on 13.04.2017.
 */
public class MainWindowController {

    @FXML
    ComboBox<String> cbCurrentUser;
    @FXML
    ComboBox<String> cbCurrentYear;
    @FXML
    ComboBox<String> cbSearchTable;
    @FXML
    ComboBox<String> cbSearchColumn;
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

    ObservableList<SoldProduct> soldProductObservableList = null;
    ObservableList<Department> departmentObservableList = null;
    ObservableList<ComingProduct> comingProductObservableList = null;

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
        cbSearchTable.getItems().addAll( "Продані",
                                                    "Прийняті",
                                                    "Відділи");
        createBarChart();
        tfSearch.textProperty().addListener((obs, oldText, newText) -> {
            onSearch();
        });
    }

    void fillComingProductTable(List<ComingProduct> comingProducts){
        comingProductObservableList= FXCollections.observableArrayList(comingProducts);
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
        comingProductTableView.setItems(comingProductObservableList);

    }

    void fillSoldProductTable(List<SoldProduct> soldProductList){

        soldProductObservableList= FXCollections.observableArrayList(soldProductList);
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
        soldProductTableView.setItems(soldProductObservableList);
    }

    void fillDepartmentTable(List<Department> departments){
        departmentObservableList= FXCollections.observableArrayList(departments);
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
        departmentProductTableView.setItems(departmentObservableList);
    }

    public void fillForUser() throws FileNotFoundException {
        String[] strings = MyUtils.readTmpFile();
        List<ComingProduct> comingProducts = null;
        List<SoldProduct> soldProducts = null;
        if(strings[0].trim().equals(MyUtils.ALL)){
            try{
                soldProducts = UtilForDBWorking.getRecords(new SoldProduct());
                comingProducts = UtilForDBWorking.getRecords(new ComingProduct());
                fillSoldProductTable(soldProducts);
                fillComingProductTable(comingProducts);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }else {
            try{
                soldProducts = UtilForDBWorking.getRecordByFieldValue(new SoldProduct(),"userName",strings[0].trim());
                comingProducts = UtilForDBWorking.getRecordByFieldValue(new ComingProduct(),"userName",strings[0].trim());
                fillSoldProductTable(soldProducts);
                fillComingProductTable(comingProducts);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    private void fillForManager() throws SQLException, FileNotFoundException {
        fillForUser();
        fillDepartmentTable(UtilForDBWorking.getRecords(new Department()));
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
        deleteRecord(soldProductTableView.getSelectionModel().getSelectedItem());
    }

    public void onMbDeleteCommingProduct(ActionEvent actionEvent) throws FileNotFoundException {
        deleteRecord(comingProductTableView.getSelectionModel().getSelectedItem());
    }

    private <T> void deleteRecord(T t) throws FileNotFoundException {
        try {
            if(t!=null){
                UtilForDBWorking.deleteRecord(t);
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
            List<User> users = UtilForDBWorking.getRecords(new User());
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

    public void onSearch() {
        switch (cbSearchTable.getSelectionModel().getSelectedItem().trim()){
            case "Продані":{
                switch (cbSearchColumn.getSelectionModel().getSelectedItem().trim()){
                    case "ID":{
                        soldProductTableView.setItems(filterForTable(MyUtils.TYPES_OF_TABLE.SOLD_PRODUCT_TYPE,
                                MyUtils.COLUMN_IN_SOLD_PRODUCT_TABLE.SOLD_ID,
                                soldProductObservableList,tfSearch));
                    }break;
                    case "Назва продукту":{
                        soldProductTableView.setItems(filterForTable(MyUtils.TYPES_OF_TABLE.SOLD_PRODUCT_TYPE,
                                MyUtils.COLUMN_IN_SOLD_PRODUCT_TABLE.SOLD_PRODUCT_NAME,
                                soldProductObservableList,tfSearch));
                    }break;
                    case "Ім'я користувача":{
                       soldProductTableView.setItems( filterForTable(MyUtils.TYPES_OF_TABLE.SOLD_PRODUCT_TYPE,
                               MyUtils.COLUMN_IN_SOLD_PRODUCT_TABLE.SOLD_USER_NAME,
                               soldProductObservableList,tfSearch));
                    }break;
                    case "Ім'я клієнта":{
                        soldProductTableView.setItems(filterForTable(MyUtils.TYPES_OF_TABLE.SOLD_PRODUCT_TYPE,
                                MyUtils.COLUMN_IN_SOLD_PRODUCT_TABLE.SOLD_CLIENT_NAME,
                                soldProductObservableList,tfSearch));
                    }break;
                    case "Кількість продукту":{
                        soldProductTableView.setItems(filterForTable(MyUtils.TYPES_OF_TABLE.SOLD_PRODUCT_TYPE,
                                MyUtils.COLUMN_IN_SOLD_PRODUCT_TABLE.SOLD_PRODUCT_NUMBER,
                                soldProductObservableList,tfSearch));
                    }break;
                    case "Ціна продукту":{
                        soldProductTableView.setItems(filterForTable(MyUtils.TYPES_OF_TABLE.SOLD_PRODUCT_TYPE,
                                MyUtils.COLUMN_IN_SOLD_PRODUCT_TABLE.SOLD_PRODUCT_PRICE,
                                soldProductObservableList,tfSearch));
                    }break;
                    case "Дата":{
                        soldProductTableView.setItems(filterForTable(MyUtils.TYPES_OF_TABLE.SOLD_PRODUCT_TYPE,
                                MyUtils.COLUMN_IN_SOLD_PRODUCT_TABLE.SOLD_DATE_TIME,
                                soldProductObservableList,tfSearch));
                    }break;
                }
            }break;

            case "Прийняті":{
                switch (cbSearchColumn.getSelectionModel().getSelectedItem().trim()){
                    case "ID":{
                        comingProductTableView.setItems(filterForTable(MyUtils.TYPES_OF_TABLE.COMING_PRODUCT_TYPE,
                                MyUtils.COLUMN_IN_COMING_PRODUCT_TABLE.COMING_ID,
                                comingProductObservableList,tfSearch));
                    }break;
                    case "Назва продукту":{
                        comingProductTableView.setItems(filterForTable(MyUtils.TYPES_OF_TABLE.COMING_PRODUCT_TYPE,
                                MyUtils.COLUMN_IN_COMING_PRODUCT_TABLE.COMING_PRODUCT_NAME,
                                comingProductObservableList,tfSearch));
                    }break;
                    case "Ім'я користувача":{
                        comingProductTableView.setItems(filterForTable(MyUtils.TYPES_OF_TABLE.COMING_PRODUCT_TYPE,
                                MyUtils.COLUMN_IN_COMING_PRODUCT_TABLE.COMING_USER_NAME,
                                comingProductObservableList,tfSearch));
                    }break;
                    case "Ім'я клієнта":{
                        comingProductTableView.setItems(filterForTable(MyUtils.TYPES_OF_TABLE.COMING_PRODUCT_TYPE,
                                MyUtils.COLUMN_IN_COMING_PRODUCT_TABLE.COMING_CLIENT_NAME,
                                comingProductObservableList,tfSearch));
                    }break;
                    case "Кількість продукту":{
                        comingProductTableView.setItems( filterForTable(MyUtils.TYPES_OF_TABLE.COMING_PRODUCT_TYPE,
                                MyUtils.COLUMN_IN_COMING_PRODUCT_TABLE.COMING_PRODUCT_NUMBER,
                                comingProductObservableList,tfSearch));
                    }break;
                    case "Ціна продукту":{
                        comingProductTableView.setItems(filterForTable(MyUtils.TYPES_OF_TABLE.COMING_PRODUCT_TYPE,
                                MyUtils.COLUMN_IN_COMING_PRODUCT_TABLE.COMING_PRODUCT_PRICE,
                                comingProductObservableList,tfSearch));
                    }break;
                    case "Дата":{
                        comingProductTableView.setItems(filterForTable(MyUtils.TYPES_OF_TABLE.COMING_PRODUCT_TYPE,
                                MyUtils.COLUMN_IN_COMING_PRODUCT_TABLE.COMING_DATE_TIME,
                                comingProductObservableList,tfSearch));
                    }break;
                }
            }break;

            case "Відділи":{
                switch (cbSearchColumn.getSelectionModel().getSelectedItem().trim()){
                    case "ID":{
                        departmentProductTableView.setItems(filterForTable(MyUtils.TYPES_OF_TABLE.DEPARTMENT_TYPE,
                                MyUtils.COLUMN_IN_DEPARTMENT_TABLE.DEPARTMENT_ID,
                                departmentObservableList,tfSearch));
                    }break;
                    case "Назва":{
                        departmentProductTableView.setItems(filterForTable(MyUtils.TYPES_OF_TABLE.DEPARTMENT_TYPE,
                                MyUtils.COLUMN_IN_DEPARTMENT_TABLE.DEPARTMENT_NAME,
                                departmentObservableList,tfSearch));
                    }break;
                    case "Адреса":{
                        departmentProductTableView.setItems(filterForTable(MyUtils.TYPES_OF_TABLE.DEPARTMENT_TYPE,
                                MyUtils.COLUMN_IN_DEPARTMENT_TABLE.DEPARTMENT_ADDRESS,
                                departmentObservableList,tfSearch));
                    }break;
                }
            }break;
        }
    }

    public void onTableSelect(ActionEvent actionEvent) {
        cbSearchColumn.getItems().clear();
        if((cbSearchTable.getSelectionModel().getSelectedItem().trim().equals("Продані"))||
                (cbSearchTable.getSelectionModel().getSelectedItem().trim().equals("Прийняті"))){
            cbSearchColumn.getItems().addAll("ID",
                    "Назва продукту",
                    "Ім'я користувача",
                    "Ім'я клієнта",
                    "Кількість продукту",
                    "Ціна продукту",
                    "Дата");
        }else if ((cbSearchTable.getSelectionModel().getSelectedItem().trim().equals("Відділи"))){
            cbSearchColumn.getItems().addAll("ID",
                    "Назва",
                    "Адреса");
        }
    }

    public void onColumnSelect(ActionEvent actionEvent) {
    }


}
