package util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import objects.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Work on 27.04.2017.
 */
public class MyUtils {

    public enum TYPES_OF_TABLE{
        SOLD_PRODUCT_TYPE,COMING_PRODUCT_TYPE,USER_TYPE,DEPARTMENT_TYPE;
    }

    public enum COLUMN_IN_SOLD_PRODUCT_TABLE{
        SOLD_ID,
        SOLD_PRODUCT_NAME,
        SOLD_USER_NAME,
        SOLD_CLIENT_NAME,
        SOLD_PRODUCT_NUMBER,
        SOLD_PRODUCT_PRICE,
        SOLD_DATE_TIME;
    }
    public enum COLUMN_IN_COMING_PRODUCT_TABLE{
        COMING_ID,
        COMING_PRODUCT_NAME,
        COMING_USER_NAME,
        COMING_CLIENT_NAME,
        COMING_PRODUCT_NUMBER,
        COMING_PRODUCT_PRICE,
        COMING_DATE_TIME;
    }

    public enum COLUMN_IN_DEPARTMENT_TABLE{
        DEPARTMENT_ID,
        DEPARTMENT_NAME,
        DEPARTMENT_ADDRESS;
    }

    public final static String ALL="Всіх";
    public static String[] readTmpFile() throws FileNotFoundException {
        String content = new Scanner(new File("tmp.txt")).useDelimiter("\\Z").next();
        String[] strings = content.split("         ");
        return strings;
    }

    public static void writeTmpFile(String userName, String status) throws IOException {
        try(  PrintWriter out = new PrintWriter( "tmp.txt" )  ){
            out.println(userName+"         "+status);
        }
    }

    public static Long setUniqueId(){
        return System.currentTimeMillis();
    }

    public static void AlertError(String title,String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void createExcelReport(String fileName,String sheetName, Object[][] datatypes){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(sheetName);

        int rowNum = 0;
        System.out.println("Creating excel");

        for (Object[] datatype : datatypes) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (Object field : datatype) {
                Cell cell = row.createCell(colNum++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(fileName);
            workbook.write(outputStream);
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
    }

    public static ObservableList<String> getUsersFromDB() throws SQLException {
        ObservableList<User> users = FXCollections.observableArrayList(UtilForDBWorking.getRecords(new User()));
        ObservableList<String> strings = FXCollections.observableArrayList();
        for(User user : users){
            strings.add(user.getUsername());
        }
        strings.add(ALL);
        return strings;
    }

    public static ObservableList<String> getYearsFromDB() throws SQLException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        List<SoldProduct> soldProducts = UtilForDBWorking.getRecords(new SoldProduct());
        ArrayList<String> tmpArray = new ArrayList<>();
        for(SoldProduct soldProduct : soldProducts){
            String tmp = Integer.toString(Integer.parseInt(Integer.toString(Integer.parseInt(df.format(soldProduct.getDateTime()))).trim()));
            if(isUniqInArray(tmpArray,tmp)){
                tmpArray.add(tmp);
            }
        }
        tmpArray.add(ALL);
        return FXCollections.observableArrayList(tmpArray);
    }

    public static DataForChart prepareDataForChart(ArrayList<String> seriaTitles,String currentYear) throws FileNotFoundException, SQLException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        DataForChart dataForChart = new DataForChart();
        dataForChart.setChartTitle("Статистика по користувачу: "+MyUtils.readTmpFile()[0].trim());
        dataForChart.setxLabel("Місяць");
        dataForChart.setyLabel("Виручка");
        dataForChart.setSeriaTitles(seriaTitles);
        List<SoldProduct> soldProducts = UtilForDBWorking.getRecords(new SoldProduct());
        ArrayList<Integer> monthsInt = new ArrayList<Integer>();
        ArrayList<ArrayList<Double>> dataValues = new ArrayList<ArrayList<Double>>() ;
        for(SoldProduct soldProduct : soldProducts){
            Integer tmpValue = new Integer(soldProduct.getDateTime().getMonth());
            if((isUniqInArray(monthsInt,tmpValue))&&(currentYear.trim().equals(Integer.toString(Integer.parseInt(df.format(soldProduct.getDateTime()))).trim()))) {
                monthsInt.add(tmpValue);
            }
        }
        monthsInt.sort((o1, o2) -> {
            return o1.compareTo(o2);
        });

        ArrayList<String> months = new ArrayList<String>();
        for(Integer integer : monthsInt){
            months.add(getMonthName(integer));
            System.out.println(integer+" "+getMonthName(integer));
        }
        for(String seriaTitle : seriaTitles){
            ArrayList<Double> arrayList =new ArrayList<Double>();
            for (String month : months){
                Double tmp =new Double(0);
                for (SoldProduct soldProduct : soldProducts){

                    System.out.println(df.format(soldProduct.getDateTime()));
                    if((month.equals(getMonthName(soldProduct.getDateTime().getMonth()).trim()))&&
                            (seriaTitle.trim().equals(soldProduct.getUserName().trim()))&&
                            (currentYear.trim().equals(Integer.toString(Integer.parseInt(df.format(soldProduct.getDateTime()))).trim())))  {
                        tmp+=soldProduct.getSoldProductNumber()*soldProduct.getSoldProductPrice();
                    }
                }
                arrayList.add(tmp);
            }
            dataValues.add(arrayList);
        }
        dataForChart.setSeriaValues(dataValues);
        dataForChart.setDataTitles(months);
        return dataForChart;
    }

    public static <T> boolean isUniqInArray(ArrayList<T> arrayList,T tmp){
        for (T t : arrayList){
            if(tmp.equals(t)){
                return false;
            }
        }
        return true;
    }

    public static String getMonthName(Integer month) {
        return new DateFormatSymbols().getMonths()[month];
    }


    public static String getStatusOfUser(String username){
        String status="";
        try {
            List<User> users = UtilForDBWorking.getRecords(new User());
            for (User u : users){
                if((u.getUsername().equals(username))){
                    return u.getStatus();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static <T,D> ObservableList<T> filterForTable(MyUtils.TYPES_OF_TABLE TYPE, D COLUMN1,
                                                         ObservableList<T> observableList, TextField filterField) {
        ObservableList<T> newObservableList = FXCollections.observableArrayList();
        String filteredString = filterField.getText().trim();
        switch (TYPE){
            case SOLD_PRODUCT_TYPE:{
                ObservableList<SoldProduct> soldProducts = (ObservableList<SoldProduct>)observableList;
                MyUtils.COLUMN_IN_SOLD_PRODUCT_TABLE COLUMN=(MyUtils.COLUMN_IN_SOLD_PRODUCT_TABLE)COLUMN1;
                switch (COLUMN){
                    case SOLD_ID:{
                        for (SoldProduct soldProduct : soldProducts){
                            if (soldProduct.getSoldProductId().toString().trim().contains(filteredString)){
                                newObservableList.add((T) soldProduct);
                            }
                        }
                    }break;
                    case SOLD_USER_NAME:{
                        for (SoldProduct soldProduct : soldProducts){
                            if (soldProduct.getUserName().trim().contains(filteredString)){
                                newObservableList.add((T) soldProduct);
                            }
                        }
                    }break;
                    case SOLD_CLIENT_NAME:{
                        for (SoldProduct soldProduct : soldProducts){
                            if (soldProduct.getClientName().trim().contains(filteredString)){
                                newObservableList.add((T) soldProduct);
                            }
                        }
                    }break;
                    case SOLD_PRODUCT_PRICE:{
                        for (SoldProduct soldProduct : soldProducts){
                            if (soldProduct.getSoldProductPrice().toString().trim().contains(filteredString)){
                                newObservableList.add((T) soldProduct);
                            }
                        }
                    }break;
                    case SOLD_PRODUCT_NAME:{
                        for (SoldProduct soldProduct : soldProducts){
                            if (soldProduct.getSoldProductName().trim().contains(filteredString)){
                                newObservableList.add((T) soldProduct);
                            }
                        }
                    }break;
                    case SOLD_DATE_TIME:{
                        for (SoldProduct soldProduct : soldProducts){
                            if (soldProduct.getDateTime().toString().trim().contains(filteredString)){
                                newObservableList.add((T) soldProduct);
                            }
                        }
                    }break;
                    case SOLD_PRODUCT_NUMBER:{
                        for (SoldProduct soldProduct : soldProducts){
                            if (soldProduct.getSoldProductNumber().toString().trim().contains(filteredString)){
                                newObservableList.add((T) soldProduct);
                            }
                        }
                    }break;
                }
            };break;

            case COMING_PRODUCT_TYPE:{
                MyUtils.COLUMN_IN_COMING_PRODUCT_TABLE COLUMN=(MyUtils.COLUMN_IN_COMING_PRODUCT_TABLE)COLUMN1;
                ObservableList<ComingProduct> comingProducts = (ObservableList<ComingProduct>)observableList;
                switch (COLUMN){
                    case COMING_ID:{
                        for (ComingProduct comingProduct : comingProducts){
                            if (comingProduct.getComingProductId().toString().trim().contains(filteredString)){
                                newObservableList.add((T) comingProduct);
                            }
                        }
                    }break;
                    case COMING_USER_NAME:{
                        for (ComingProduct comingProduct : comingProducts){
                            if (comingProduct.getUserName().trim().contains(filteredString)){
                                newObservableList.add((T) comingProduct);
                            }
                        }
                    }break;
                    case COMING_CLIENT_NAME:{
                        for (ComingProduct comingProduct : comingProducts){
                            if (comingProduct.getClientName().trim().contains(filteredString)){
                                newObservableList.add((T) comingProduct);
                            }
                        }
                    }break;
                    case COMING_PRODUCT_PRICE:{
                        for (ComingProduct comingProduct : comingProducts){
                            if (comingProduct.getComingProductPrice().toString().trim().contains(filteredString)){
                                newObservableList.add((T) comingProduct);
                            }
                        }
                    }break;
                    case COMING_PRODUCT_NAME:{
                        for (ComingProduct comingProduct : comingProducts){
                            if (comingProduct.getComingProductName().trim().contains(filteredString)){
                                newObservableList.add((T) comingProduct);
                            }
                        }
                    }break;
                    case COMING_DATE_TIME:{
                        for (ComingProduct comingProduct : comingProducts){
                            if (comingProduct.getDateTime().toString().trim().contains(filteredString)){
                                newObservableList.add((T) comingProduct);
                            }
                        }
                    }break;
                    case COMING_PRODUCT_NUMBER:{
                        for (ComingProduct comingProduct : comingProducts){
                            if (comingProduct.getComingProductNumber().toString().trim().contains(filteredString)){
                                newObservableList.add((T) comingProduct);
                            }
                        }
                    }break;
                }
            };break;

            case DEPARTMENT_TYPE:{
                MyUtils.COLUMN_IN_DEPARTMENT_TABLE COLUMN=(MyUtils.COLUMN_IN_DEPARTMENT_TABLE)COLUMN1;
                ObservableList<Department> departments = (ObservableList<Department>)observableList;
                switch (COLUMN){
                    case DEPARTMENT_ID:{
                        for (Department department : departments){
                            if (department.getDepartmentId().toString().trim().contains(filteredString)){
                                newObservableList.add((T) department);
                            }
                        }
                    }break;
                    case DEPARTMENT_NAME:{
                        for (Department department : departments){
                            if (department.getDepartmentName().trim().contains(filteredString)){
                                newObservableList.add((T) department);
                            }
                        }
                    }break;
                    case DEPARTMENT_ADDRESS:{
                        for (Department department : departments){
                            if (department.getDepartmentAddress().trim().contains(filteredString)){
                                newObservableList.add((T) department);
                            }
                        }
                    }break;

                }
            };break;
        }
        return newObservableList;
    }

}
