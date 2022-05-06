package HexSudoku2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DificuldadeController {
    @FXML
    Label labelUsername; 
    
    String pw; 
    int dif;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    
    public void displayName(String username){
        labelUsername.setText(username);
    }
    
    public void getpw(String pw){
        this.pw = pw;
    }
    
    public void initialize(URL url, ResourceBundle rb) {
;
    }
    
    
    @FXML
    public void inicializaFacil(ActionEvent event) throws IOException {
        dif = 1;    
        
        GameController gameController = new GameController(dif);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLGame.fxml"));
        loader.setController(gameController);
        Parent root = loader.load();
        
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add("fxmlview.css");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void inicializaMedio(ActionEvent event) throws IOException {
        dif = 2;
        
        GameController gameController = new GameController(dif);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLGame.fxml"));
        loader.setController(gameController);
        Parent root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add("fxmlview.css");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void inicializaDificil(ActionEvent event) throws IOException {
        dif = 3;
        
        GameController gameController = new GameController(dif);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLGame.fxml"));
        loader.setController(gameController);
        Parent root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add("fxmlview.css");
        stage.setScene(scene);
        stage.show();
    }
}
