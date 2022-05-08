package HexSudoku2;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    Label labelAvisoLogin;
    
    @FXML
    TextField txtFieldUsername;
    
    @FXML
    PasswordField passFieldPassword;
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void userLogin(ActionEvent event) throws IOException {
        checkLogin();

        String username = txtFieldUsername.getText();
        String pw = passFieldPassword.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDificuldade.fxml"));
        root = loader.load();

        DificuldadeController dificuldadeController = loader.getController();
        dificuldadeController.displayName(username);
        dificuldadeController.getpw(pw);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void checkLogin() {
        if (txtFieldUsername.getText().toString().equals("javacoding") && passFieldPassword.getText().toString().equals("123")) {
            labelAvisoLogin.setText("Sucess!");  
        } else if (txtFieldUsername.getText().isEmpty() && passFieldPassword.getText().isEmpty()) {
            labelAvisoLogin.setText("Please enter your data.");
        } else {
            labelAvisoLogin.setText("Wrong username or password!");
        }
    }
}
