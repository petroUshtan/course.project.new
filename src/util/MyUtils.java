package util;

import controllers.CFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import objects.DataForChart;
import objects.SoldProduct;
import objects.User;
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
        ObservableList<User> users = FXCollections.observableArrayList( CFactory.getInstance().getUserDao().getUser());
        ObservableList<String> strings = FXCollections.observableArrayList();
        for(User user : users){
            strings.add(user.getUsername());
        }
        strings.add(ALL);
        return strings;
    }

    public static ObservableList<String> getYearsFromDB() throws SQLException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        List<SoldProduct> soldProducts = CFactory.getInstance().getSoldProductDao().getSoldProducts();
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
        List<SoldProduct> soldProducts = CFactory.getInstance().getSoldProductDao().getSoldProducts();
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
            List<User> users = CFactory.getInstance().getUserDao().getUser();
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


}
