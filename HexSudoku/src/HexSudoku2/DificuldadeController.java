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
        ArrayList<timeDateNameDif> BestTimesDatesByOrder = new ArrayList<>();
        timeDateNameDif timeDateNameDif;
        int linesCount = 0;
        try {
            reader = new BufferedReader(new FileReader("Records.txt"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                linesCount++;
            }

            BestTimesDatesByOrder.clear();

            int c = 0;
            String lineContent[];
            reader = new BufferedReader(new FileReader("Records.txt"));
            while ((line = reader.readLine()) != null) {
                lineContent = line.split(" ");
                LocalDate date = LocalDate.parse(lineContent[1]);
                timeDateNameDif = new timeDateNameDif(lineContent[0], date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)), lineContent[2], lineContent[3]);
                BestTimesDatesByOrder.add(timeDateNameDif);
                c++;
            }
            reader.close();

        } catch (FileNotFoundException ex) {
        }

        String recordsString = "";
        
        int countFaceis = 0;
        recordsString = "FÁCIL\n";
        for (int i = 0; i < BestTimesDatesByOrder.size() && countFaceis < 3; i++) {
            if (BestTimesDatesByOrder.get(i).user.equals(this.username)) {
                if (BestTimesDatesByOrder.get(i).dif.equals("1")) {
                    recordsString = recordsString + (countFaceis + 1) + "º -> " + BestTimesDatesByOrder.get(i).time + " " + BestTimesDatesByOrder.get(i).date + " -> Dif: EASY\n";
                    countFaceis++;
                }
            }
        }
        if (countFaceis == 0) {
            recordsString = recordsString + "You didnt complete any easy board yet!\n";
        }

        int countMedios = 0;
        recordsString = recordsString + "---------------------------------------------------\nMEDIUM\n";
        for (int i = 0; i < BestTimesDatesByOrder.size() && countMedios < 3; i++) {
            if (BestTimesDatesByOrder.get(i).user.equals(this.username)) {
                if (BestTimesDatesByOrder.get(i).dif.equals("2")) {
                    recordsString = recordsString + (countMedios + 1) + "º -> " + BestTimesDatesByOrder.get(i).time + " " + BestTimesDatesByOrder.get(i).date + " -> Dif: MEDIUM\n";
                    countMedios++;
                }
            }
        }
        if (countMedios == 0) {
            recordsString = recordsString + "You didnt complete any medium board yet!\n";
        }

        int countDificeis = 0;
        recordsString = recordsString + "---------------------------------------------------\nHARD\n";
        for (int i = 0; i < BestTimesDatesByOrder.size() && countDificeis < 3; i++) {
            if (BestTimesDatesByOrder.get(i).user.equals(this.username)) {
                if (BestTimesDatesByOrder.get(i).dif.equals("3")) {
                    recordsString = recordsString + (countDificeis + 1) + "º -> " + BestTimesDatesByOrder.get(i).time + " " + BestTimesDatesByOrder.get(i).date + " -> Dif: HARD\n";
                    countDificeis++;
                }
            }
        }
        if(countDificeis == 0)
            recordsString = recordsString + "You didnt complete any hard board yet!\n";

        
        System.out.println("\n10 best times:\n" + recordsString);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        int countTotal = countFaceis + countMedios + countDificeis;
        if(countTotal > 0 ){
            alert.setTitle("YOUR " + countTotal + " BEST TIMES");
            alert.setHeaderText(recordsString);
        }
        else{
            alert.setTitle("NO RECORDS");
            alert.setHeaderText("You didnt complete any board yet!");
        }
    
        alert.showAndWait();
    }
    
    @FXML
    public void logOutAction(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);scene.getStylesheets().add("fxmlview.css");
        stage.setScene(scene);
        stage.setTitle("HexSudoku - Login");
        stage.show();
    }
    
   
}
