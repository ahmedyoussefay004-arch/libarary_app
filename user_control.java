import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class user_control {



    private final String user = "project";
    private final String password = "1234";

    @FXML
    private ChoiceBox<String> ch;

    @FXML
    private Label lab;

    @FXML
    private TextField tx;

    @FXML
    private TextField book;

    // ================= initialize =================
    @FXML
    private void initialize() {




        ch.getItems().addAll("Programming", "Database", "novel", "all");
        ch.setValue("all");
    }

    // ================= Show Books =================
    @FXML
    private void sh() {

        String category = ch.getValue();

        if (category == null) {
            lab.setText("⚠️ Choose category");
            return;
        }

        try {
            String[] books = new users().showBooks(category);

            if (books.length == 0) {
                lab.setText("No books available");
                return;
            }

            StringBuilder sb = new StringBuilder();
            for (String b : books) {
                sb.append(b).append("\n");
            }

            lab.setText(sb.toString());

        } catch (SQLException e) {
            lab.setText("❌ Error loading books");
            e.printStackTrace();
        }
    }

    // ================= Borrow =================
    @FXML
    private void br() {

        String id = tx.getText();
        String boook = book.getText();

        if (id.isEmpty() || boook.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Enter ID and Book name");
            return;
        }

        new users().borrow(id, boook);


        showAlert(Alert.AlertType.INFORMATION, "SUCCESS", "Book borrowed successfully");
    }

    // ================= Alert Helper =================
    private void showAlert(Alert.AlertType type, String title, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
