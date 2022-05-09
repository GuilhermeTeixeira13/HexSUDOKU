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
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

    @FXML
    Label LabelNewRecord;

    String time;
    String userName;
    String content;

    StringBuilder stringBuilder;
    ArrayList<LocalTime> arrayTimes = new ArrayList<>();
    ArrayList<String> tenBestTimesByOrder = new ArrayList<>();

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        labeltime.setText(this.time);
      
        LocalTime timeMade = LocalTime.parse(this.time, DateTimeFormatter.ofPattern("HH:mm:ss"));
        if (timeMade.isBefore(arrayTimes.get(1))) {
            LabelNewRecord.setVisible(true);
            System.out.println("New record set! --> " + this.time);
        } else {
            LabelNewRecord.setVisible(false);
        }
    }

    public CorrectAnswerController(String time, String username) throws IOException {
        this.time = time;
        this.userName = username;

        BufferedReader reader;
        try {
            LocalTime timeMade = LocalTime.parse(this.time, DateTimeFormatter.ofPattern("HH:mm:ss"));

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
            arrayTimes.add(LocalTime.parse(timeMade.toString(), DateTimeFormatter.ofPattern("HH:mm:ss")));

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
    private void records(ActionEvent event) throws IOException {
        System.out.println("\n10 best times:\n" + tenBestTimesByOrder.toString());

        String recordsString = "";
        for (int i = 0; i < tenBestTimesByOrder.size(); i++) {
            recordsString = recordsString + (i + 1) + "º -> " + tenBestTimesByOrder.get(i) + "\n";
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("YOUR 10 BEST TIMES");
        alert.setHeaderText(recordsString);
        alert.showAndWait();
    }
}
