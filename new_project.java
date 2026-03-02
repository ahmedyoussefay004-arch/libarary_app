import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class new_project extends Application {
    public void start(Stage stage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/try.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args)throws SQLException {


        String url = "jdbc:sqlserver://localhost:1433;" +
                "databaseName=project;" +
                "encrypt=true;" +
                "trustServerCertificate=true;";

        String user = "project";
        String password = "1234";

        Connection con = DriverManager.getConnection(url, user, password);
        Statement st = con.createStatement();
       new shared();
        launch(args);


    }
}