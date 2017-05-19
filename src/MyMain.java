import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import objects.History;
import util.UtilForDBWorking;

public class MyMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        UtilForDBWorking.addRecord(new History());
        Parent root = FXMLLoader.load(getClass().getResource("fxml/Login.fxml"));
        primaryStage.setTitle("АРМ працівника складу");
        primaryStage.setScene(new Scene(root/*, 320, 160*/));
        primaryStage.show();

    }

    public static void main(String[] args) {launch(args);}
}
