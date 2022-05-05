package HexSudoku2;

import HexSudoku.*;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author jpc
 */
public class Main extends Application {
    
    
    private static Stage stg;
    
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        stg = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));   
        primaryStage.setTitle("Sudoku Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        
    }
    
    public void changeScene(String fxml, String title) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene2 = new Scene(root2, 933, 684);
        stg.setScene(scene2);
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
