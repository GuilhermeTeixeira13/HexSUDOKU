package HexSudoku2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CorrectAnswerController {
    
    @FXML
    Label time;
    
    @FXML
    Button btnQuit;
    
    @FXML
    Button btnPlayAgain;
    
    @FXML
    Button btnRecords;
     
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void initialize(URL url, ResourceBundle rb) {

    }  
}
