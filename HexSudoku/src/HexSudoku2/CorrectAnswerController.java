package HexSudoku2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CorrectAnswerController implements Initializable {
    
    @FXML
    Label labeltime;
    
    @FXML
    Button btnQuit;
    
    @FXML
    Button btnPlayAgain;
    
    @FXML
    Button btnRecords;
    
    String time;
    String userName;
     
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    public CorrectAnswerController(String time, String username){
        System.out.println("1O tempo é --> "+ time);
        this.time = time;
        this.userName = username;
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("2O tempo é --> "+ this.time);
        labeltime.setText(this.time);
    }  
}
