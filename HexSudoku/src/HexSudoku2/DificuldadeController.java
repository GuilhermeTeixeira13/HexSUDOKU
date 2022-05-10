package HexSudoku2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DificuldadeController {

    @FXML
    Label labelUsername;
    
    @FXML
    Button btnLogOut;
    
 

    String pw;
    int dif;

    ArrayList<String> tenBestTimesByOrder = new ArrayList<>();

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    private String username;

    public void displayName(String username) {
        this.username = username;
        labelUsername.setText(username);
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
        stage.setTitle("HexSudoku - Easy");
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
        stage.setTitle("HexSudoku - Medium");
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
        stage.setTitle("HexSudoku - Hard");
        stage.show();
    }

    @FXML
    private void records(ActionEvent event) throws IOException {
        BufferedReader reader;
        ArrayList<PairsStringString> tenBestTimesDatesByOrder = new ArrayList<>();
        PairsStringString timeDate;
        int linesCount = 0;
        try {
            reader = new BufferedReader(new FileReader("Records.txt"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                linesCount++;
            }
            
            tenBestTimesDatesByOrder.clear();
            int limite = Math.min(10, linesCount);

            int c = 0;
            String lineContent[];
            reader = new BufferedReader(new FileReader("Records.txt"));
            while (((line = reader.readLine()) != null) && c < limite) {
                lineContent = line.split(" ");
                LocalDate date = LocalDate.parse(lineContent[1]);      
                timeDate = new PairsStringString(lineContent[0], date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
                tenBestTimesDatesByOrder.add(timeDate);
            }
            reader.close();
            
        } catch (FileNotFoundException ex) {
        }

        String recordsString = "";
        for (int i = 0; i < tenBestTimesDatesByOrder.size(); i++) {
            recordsString = recordsString + (i + 1) + "º -> " + tenBestTimesDatesByOrder.get(i).time + " " + tenBestTimesDatesByOrder.get(i).date +"\n"; 
        }
        System.out.println("10 best times:\n" + recordsString);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("YOUR " + tenBestTimesDatesByOrder.size() + " BEST TIMES");
        alert.setHeaderText(recordsString);    
        alert.showAndWait();
    }
    
    @FXML
    public void logOutAction(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("HexSudoku - Login");
        stage.show();
    }
    
   
}
