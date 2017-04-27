package controllers;

import impls.LoginImplDB;
import interfaces.ClientDao;
import interfaces.DepartmentDao;
import interfaces.Login;
import interfaces.ProductDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import objects.Status;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


public class LoginController {
    @FXML
    Button singinButton;
    @FXML
    TextField tfUsername;
    @FXML
    TextField tfPassword;


    public void onSingin() {
        Parent rootNode = null;
        try {
            Login lg = new LoginImplDB();
            if(lg.verify(tfUsername.getText(),tfPassword.getText())){
               writeInTmpFile(tfUsername.getText(),lg.getStatusOfUser(tfUsername.getText(),tfPassword.getText()));
               if((lg.getStatusOfUser(tfUsername.getText(),tfPassword.getText())).equals(Status.getADMIN())){
                   rootNode = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/AdminWindow.fxml"));
//                   Session session= HibernateUtil.getSessionFactory().openSession();

               }else if((lg.getStatusOfUser(tfUsername.getText(),tfPassword.getText())).equals(Status.getUSER())){
                   rootNode = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/MainWindow.fxml"));
               }
               Stage stage = (Stage) singinButton.getScene().getWindow();
               stage.close();

               Stage mainStage = new Stage();
               Scene scene = new Scene(rootNode);
               mainStage.setScene(scene);
               mainStage.setTitle("АРМ працівника складу");
               mainStage.show();
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onCancel() throws SQLException {
        CFactory cFactory = CFactory.getInstance();
        ClientDao clientDao= cFactory.getClientDao();
        ProductDao productDao = cFactory.getProductDao();
        DepartmentDao departmentDao = cFactory.getDepartmentDao();
    }

    private void writeInTmpFile(String userName, String status) throws IOException {
        try(  PrintWriter out = new PrintWriter( "tmp.txt" )  ){
            out.println(userName+"         "+status);
        }
    }

}
