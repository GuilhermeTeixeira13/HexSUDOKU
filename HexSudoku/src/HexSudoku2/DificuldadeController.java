package HexSudoku2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
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

    ArrayList<String> tenBestTimesByOrder = new ArrayList<>();

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    private String username;

    public void displayName(String username) {
        this.username = username;
        labelUsername.setText(username + ", please choose the game's dificulty:");
    }

    public void getpw(String pw) {
        this.pw = pw;
    }
    
     public String getUsername() {
        return this.username;
    }
    

    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void inicializaFacil(ActionEvent event) throws IOException {
        dif = 1;

        GameController gameController = new GameController(dif, this.getUsername());
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

        GameController gameController = new GameController(dif, this.getUsername());
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

        GameController gameController = new GameController(dif, this.getUsername());
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
    private void records(ActionEvent event) throws IOException {
        BufferedReader reader;
        ArrayList<LocalTime> arrayTimes = new ArrayList<>();
        ArrayList<String> tenBestTimesByOrder = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader("Records.txt"));
            String line = null;
            String ls = System.getProperty("line.separator");
            while ((line = reader.readLine()) != null) {
                arrayTimes.add(LocalTime.parse(line, DateTimeFormatter.ofPattern("HH:mm:ss")));
            }
            reader.close();

            Collections.sort(arrayTimes);
            tenBestTimesByOrder.clear();
            int limite = Math.min(10, arrayTimes.size());
            for (int i = 0; i < limite; i++) {
                tenBestTimesByOrder.add(arrayTimes.get(i).toString());
            }
            
        } catch (FileNotFoundException ex) {
        }

        System.out.println("\n10 best times:\n" + tenBestTimesByOrder.toString() + "\n");
    }
}
