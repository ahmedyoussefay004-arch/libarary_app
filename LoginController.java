import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


public class LoginController {
    users u=new users();
    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private void loginClicked(ActionEvent event) {
        String user = usernameField.getText();
        String pass = passwordField.getText();
        String role = u.login(user, pass);

        if (role == null) {
            shared.alertt("Wrong username or password!");
            return;
        }

        if (role.equals("admin")) {
//            openPage("admin.fxml", "Admin Dashboard");
        } else if (role.equals("USER")) {

           new shared().openpage("user.fxml", event);

        }
    }

    @FXML
    private void register_click(ActionEvent event) {
        shared db=new shared();
        db.openpage("/register.fxml",event);
    }
}