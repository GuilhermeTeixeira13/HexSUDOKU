package HexSudoku2;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    Label labelAvisoLogin;
    
    @FXML
    TextField txtFieldUsername;
    
    @FXML
    ImageView imageViewSudokuLogo;

    @FXML
    PasswordField passFieldPassword;

    @FXML
    private BorderPane sceneBorderPane;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Image logoSudoku = new Image(getClass().getResourceAsStream("logoLoginSudoku.png"));

    public void displayImageLogoSudoku() {
        imageViewSudokuLogo.setImage(logoSudoku);
    }

    public void userLogin(ActionEvent event) throws IOException {

        String username = txtFieldUsername.getText();
        String pw = passFieldPassword.getText();
        int cred = checkLogin();
        if (cred == 1) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDificuldade.fxml"));
            root = loader.load();

            DificuldadeController dificuldadeController = loader.getController();
            dificuldadeController.displayName(username);
            dificuldadeController.getpw(pw);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.getStylesheets().add("fxmlview.css");
            stage.setScene(scene);
            stage.setTitle("HexSudoku - Dificulty level");
            stage.show();
        }
    }

    public int checkLogin() throws IOException {
        File file = new File("C:\\Users\\joaob\\OneDrive\\Documentos\\GitHub\\HexSudokuTeste\\HexSudoku\\src\\HexSudoku2\\accounts.txt");
        Scanner scan = new Scanner(file);
        String usernameRecebido = txtFieldUsername.getText();
        String passwordRecebida = passFieldPassword.getText();
        int validacaoCredenciais = 0;

        if ((!txtFieldUsername.getText().isEmpty()) && (!passFieldPassword.getText().isEmpty())) {
            while (scan.hasNextLine()) {
                String fileContent = "";
                fileContent = scan.nextLine();
                if (fileContent.contains("Username: " + usernameRecebido) && fileContent.contains("Password: " + passwordRecebida)) {
                    validacaoCredenciais = 1;
                }
                else {
                    labelAvisoLogin.setText("Wrong username or password!");
                }
            }
        } else if (txtFieldUsername.getText().isEmpty() && passFieldPassword.getText().isEmpty()) {
            labelAvisoLogin.setText("Please enter your data.");
        } else {
            labelAvisoLogin.setText("Wrong username or password!");
        }
        return validacaoCredenciais;
    }

    @FXML
    public void exit(ActionEvent event) {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("EXIT!");
        alert.setHeaderText("You 're about to close the application!");
        alert.setContentText("Are you sure you want to exit? ");
        
        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) sceneBorderPane.getScene().getWindow();
            stage.close();
        }
    }
    
    @FXML
    public void goToRegister(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLRegisto.fxml"));
        root = loader.load();


        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add("fxmlview.css");
        stage.setScene(scene);
        stage.setTitle("HexSudoku - Register");
        stage.show();
    }
}
