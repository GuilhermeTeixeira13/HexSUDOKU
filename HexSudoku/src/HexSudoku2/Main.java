package HexSudoku2;

import HexSudoku.*;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author jpc
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add("fxmlview.css");
            stage.setScene(scene);
            stage.setTitle("HexSudoku - Login");
            stage.show();
            
            stage.setOnCloseRequest(event -> {
                event.consume();
                exit(stage);
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void exit(Stage stage) {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("EXIT!");
        alert.setHeaderText("You 're about to close the application");
        alert.setContentText("Are you sure you want to exit? ");
        
        if (alert.showAndWait().get() == ButtonType.OK) {
            stage.close();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
