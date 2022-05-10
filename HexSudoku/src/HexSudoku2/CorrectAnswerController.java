package HexSudoku2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
    String content;
    StringBuilder stringBuilder;
    ArrayList<LocalTime> arrayTimes = new ArrayList<>();
    ArrayList<String> tenBestTimesByOrder = new ArrayList<>();

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    public String getUsername() {
            return this.userName;
    }
    

    public CorrectAnswerController(String time, String username) throws IOException {
        this.time = time;
        this.userName = username;

     
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("Records.txt"));
            this.stringBuilder = new StringBuilder();
            String line = null;
            String ls = System.getProperty("line.separator");
            while ((line = reader.readLine()) != null) {
                arrayTimes.add(LocalTime.parse(line, DateTimeFormatter.ofPattern("HH:mm:ss")));
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            reader.close();

            this.content = stringBuilder.toString();
            this.content = this.content + this.time;

            FileWriter myWriter = new FileWriter("Records.txt");
            myWriter.write(this.content);
            myWriter.close();

            Collections.sort(arrayTimes);
            tenBestTimesByOrder.clear();
            int limite = Math.min(10, arrayTimes.size());
            for (int i = 0; i < limite; i++) {
                tenBestTimesByOrder.add(arrayTimes.get(i).toString());
            }
        } catch (FileNotFoundException ex) {
        }
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        labeltime.setText(this.time);
    }

    @FXML
    private void records(ActionEvent event) throws IOException {
        System.out.println("\n10 best times:\n" + tenBestTimesByOrder.toString());
    }
    
    @FXML
    private void quitGame(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDificuldade.fxml"));
        root = loader.load();

        DificuldadeController dificuldadeController = loader.getController();
        dificuldadeController.displayName(this.getUsername());

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
