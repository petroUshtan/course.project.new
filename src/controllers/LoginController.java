package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    Button singinButton;

    public void onSingin() {
        try {
            Parent rootNode = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/MainWindow.fxml"));

            Stage stage = (Stage) singinButton.getScene().getWindow();
            stage.close();

            Stage mainStage = new Stage();
            Scene scene = new Scene(rootNode);
            mainStage.setScene(scene);
            mainStage.setTitle("АРМ працівника складу");
            mainStage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void onCancel(){

    }
}
