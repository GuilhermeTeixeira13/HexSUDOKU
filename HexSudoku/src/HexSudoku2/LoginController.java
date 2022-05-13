package HexSudoku2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

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

        try {
            String username = txtFieldUsername.getText();
            String pw = passFieldPassword.getText();
            int cred = checkLogin();
            if (cred != -1) {
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
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int checkLogin() throws IOException, FileNotFoundException, InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, IllegalBlockSizeException, IllegalBlockSizeException, IllegalBlockSizeException, BadPaddingException {
        String usernameRecebido = txtFieldUsername.getText();
        String passwordRecebida = passFieldPassword.getText();
        int validacaoCredenciais = -1;

        if ((!txtFieldUsername.getText().isEmpty()) && (!passFieldPassword.getText().isEmpty())) {
            ArrayList<String> usernameArray = new ArrayList<String>();
            ArrayList<String> passwordArray = new ArrayList<String>();

            File file = new File("accounts.txt");

            Scanner scan = new Scanner(file);
            Base64.Decoder decoder = Base64.getDecoder();
            while (scan.hasNextLine()) {
                String fileContent = "";
                fileContent = scan.nextLine();

                String[] usernamepass = fileContent.split(" ");

                if (!fileContent.equals("")) {
                    usernameArray.add(new String(decoder.decode(usernamepass[0])));
                    passwordArray.add(new String(decoder.decode(usernamepass[1])));
                }
            }
            for (int i = 0; i < usernameArray.size(); i++) {
                if (usernameArray.get(i).equals(usernameRecebido) && passwordArray.get(i).equals(passwordRecebida)) {
                    validacaoCredenciais = 1;
                }
            }
        } else {
            labelAvisoLogin.setText("Please enter your data.");
        }
        if (validacaoCredenciais == -1 && (!txtFieldUsername.getText().isEmpty()) && (!passFieldPassword.getText().isEmpty())) {
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
    public void goToRegister(ActionEvent event) throws IOException {
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
