import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class regcontroller {
    users u=new users();

    @FXML
    private TextField ID;
    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private TextField email;
    @FXML
    private TextField pass;
    @FXML
    void reg(ActionEvent event) {
        String id=ID.getText();
        String fn=fname.getText();
        String ln=lname.getText();
        String e=email.getText();
        String password=pass.getText();
        if (id.isEmpty() || fn.isEmpty() || ln.isEmpty() || e.isEmpty() || password.isEmpty()) {
            shared.alertt("Please fill all the fields");
            return;
        }
        if (password.length()<7) {
            shared.alertt("Password must be at least 7 characters");
            return;
        }
        if (e.indexOf("@")==-1){
            shared.alertt("Invalid Email");
            return;
        }
        if (users.is_user_found(id)){
            shared.alertt("id already exists");
            return;
        }
        if (users.is_email_found(e)){
            shared.alertt("email already exists");
            return;
        }
        u.register(id,fn,ln,e,password);
        shared.success_alert("Registration Successful");
        new shared().openpage("/try.fxml",event);
    }
    @FXML
    void back(ActionEvent event) {
        new shared().openpage("/welcome.fxml",event);
    }
}