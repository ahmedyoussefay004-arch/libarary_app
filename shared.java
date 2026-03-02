
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.*;

class shared {
    static Connection con;

    shared() {
        try {
            String url = "jdbc:sqlserver://localhost:1433;" +
                    "databaseName=project;" +
                    "encrypt=true;" +
                    "trustServerCertificate=true;";

            String user = "project";
            String password = "1234";
            con = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void openpage( String nxt, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nxt));
            Parent root = loader.load();
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    static void alertt(String s){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();
    }
    static void success_alert(String s){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("VALID");
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();
    }
}