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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Work on 27.04.2017.
 */
public class MyUtils {
    public static String[] readTmpFile() throws FileNotFoundException {
        String content = new Scanner(new File("tmp.txt")).useDelimiter("\\Z").next();
        String[] strings = content.split("         ");
        System.out.println("|"+strings[0]+"|");
        System.out.println("|"+strings[1]+"|");
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
        return strings;
    }

    public static DataForChart prepareDataForChart(ArrayList<String> seriaTitles) throws FileNotFoundException, SQLException {
        DataForChart dataForChart = new DataForChart();
        dataForChart.setChartTitle("Статистика по користувачу: "+MyUtils.readTmpFile()[0].trim());
        dataForChart.setxLabel("Місяць");
        dataForChart.setyLabel("Виручка");
        dataForChart.setSeriaTitles(seriaTitles);
        List<SoldProduct> soldProducts = CFactory.getInstance().getSoldProductDao().getSoldProducts();
        ArrayList<String> months = new ArrayList<String>();
        ArrayList<ArrayList<Double>> dataValues = new ArrayList<ArrayList<Double>>() ;
        for(SoldProduct soldProduct : soldProducts){
            String tmpValue = getMonthName(soldProduct.getDateTime().getMonth()).trim();
            if(isUniqInArray(months,tmpValue)) {
                months.add(tmpValue);
            }
        }
        for(String seriaTitle : seriaTitles){
            ArrayList<Double> arrayList =new ArrayList<Double>();
            Double tmp =new Double(0);
            for (String month : months){
                for (SoldProduct soldProduct : soldProducts){
                    if(month.equals(getMonthName(soldProduct.getDateTime().getMonth()).trim()))  {
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
        return new DateFormatSymbols().getMonths()[month-1];
    }
}
