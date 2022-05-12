package HexSudoku2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class RegistoController implements Initializable {
    
    @FXML
    BorderPane borderPane;
    
    @FXML
    Button btnRegisto;
    
    @FXML
    Button btnbackToLogin;
    
    @FXML
    TextField txtFieldUsername;
    
    @FXML
    PasswordField passFieldPassword;
    
    @FXML
    Label labelAvisoRegisto;
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    
    @FXML
    public void backtoLoginEvent(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        root = loader.load();


        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add("fxmlview.css");
        stage.setScene(scene);
        stage.setTitle("HexSudoku - Login");
        stage.show();
    }
    
    @FXML
    public void registerEvent(ActionEvent event) throws IOException{
        int jaExisteUsername = 0;
        
        File file = new File("C:\\Users\\joaob\\OneDrive\\Documentos\\GitHub\\HexSudokuTeste\\HexSudoku\\src\\HexSudoku2\\accounts.txt");
        Scanner scan = new Scanner(file);
        
        
        while(scan.hasNextLine()) {
            String fileContent = "";
            fileContent = scan.nextLine();
            if(fileContent.contains("Username: " + txtFieldUsername.getText())) {
                jaExisteUsername = -1;
                labelAvisoRegisto.setText("Já existe o username " + txtFieldUsername.getText());
                labelAvisoRegisto.setStyle("-fx-text-fill: red");
                System.out.println("Já existe o username: " + txtFieldUsername.getText() + "\n");
            }
        }
        
        
        
        if (jaExisteUsername != -1) {
            try {
                FileWriter writer = new FileWriter(
                        ("C:\\Users\\joaob\\OneDrive\\Documentos\\GitHub\\HexSudokuTeste\\HexSudoku\\src\\HexSudoku2\\accounts.txt"), true);
                writer.write("Username: " + txtFieldUsername.getText() + "  Password: " + passFieldPassword.getText() + "\n");
                writer.close();
                labelAvisoRegisto.setText("Registado com sucesso");
                labelAvisoRegisto.setStyle("-fx-text-fill: green");
                txtFieldUsername.setText("");
                passFieldPassword.setText("");
                System.out.println("Registado com sucesso");
            } catch (Exception ex) {
                System.out.println("Problema na escrita para o ficheiro!");
            }
        }
    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
