package HexSudoku2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
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
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;


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
    public void registerEvent(ActionEvent event) throws IOException, FileNotFoundException, InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, IllegalBlockSizeException, IllegalBlockSizeException, IllegalBlockSizeException, BadPaddingException{
        int jaExisteUsername = -1;
        int j = 0;
        int space = 0;
        ArrayList<String> usernameArray = new ArrayList<String>();
        ArrayList<String> passwordArray = new ArrayList<String>();
        File file = new File("accounts.txt");
        String usernamepassString = txtFieldUsername.getText() + passFieldPassword.getText(); 
        Scanner scan = new Scanner(file);
        Decoder decoder = Base64.getDecoder();
        while(scan.hasNextLine()) {
            String fileContent = "";
            fileContent = scan.nextLine();
            String[] usernamepass = fileContent.split(" ");
            
        
            
            if(!fileContent.equals("")){
                usernameArray.add(new String(decoder.decode(usernamepass[0])));
                passwordArray.add(new String(decoder.decode(usernamepass[1])));   
            }
        }
        
        for(int i = 0; i < usernameArray.size(); i++) {
            if(usernameArray.get(i).equals(txtFieldUsername.getText())) {
                jaExisteUsername = i;
            }
        }
        
        while(j < usernamepassString.length()) {
            char a = usernamepassString.charAt(j);
            
            if(a == ' ') {
                space++;
            }
            j++;
        }

        if (jaExisteUsername == -1 && space == 0) {
            try {
                FileWriter writer = new FileWriter(("accounts.txt"), true);
                Encoder encoder = Base64.getEncoder();
                writer.write(encoder.encodeToString(txtFieldUsername.getText().getBytes()) + " " + encoder.encodeToString(passFieldPassword.getText().getBytes()) + "\n");
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
        else {
            labelAvisoRegisto.setText("Credenciais inválidas, tente novamente!");
            labelAvisoRegisto.setStyle("-fx-text-fill: red");
        }
    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
}
