package HexSudoku2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
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
    ArrayList<PairsStringString> tenBestTimesDatesByOrder = new ArrayList<>();

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        labeltime.setText(this.time);

        LocalTime timeMade = LocalTime.parse(this.time, DateTimeFormatter.ofPattern("HH:mm:ss"));

        if (tenBestTimesDatesByOrder.size() == 1) {
            if (timeMade.isBefore(LocalTime.parse(tenBestTimesDatesByOrder.get(0).time, DateTimeFormatter.ofPattern("HH:mm:ss"))) || timeMade.compareTo(LocalTime.parse(tenBestTimesDatesByOrder.get(0).time, DateTimeFormatter.ofPattern("HH:mm:ss"))) == 0) {
                LabelNewRecord.setVisible(true);
                System.out.println("New record set! --> " + this.time);
            } else {
                LabelNewRecord.setVisible(false);
            }
        } else {
            if (timeMade.isBefore(LocalTime.parse(tenBestTimesDatesByOrder.get(1).time, DateTimeFormatter.ofPattern("HH:mm:ss")))) {
                LabelNewRecord.setVisible(true);
                System.out.println("New record set! --> " + this.time);
            } else {
                LabelNewRecord.setVisible(false);
            }
        }

    }

    public CorrectAnswerController(String time, String username) throws IOException {
        this.time = time;
        this.userName = username;
        PairsStringString timeDate;
        BufferedReader reader;
        int linesCount = 1;
        try {
            reader = new BufferedReader(new FileReader("Records.txt"));
            this.stringBuilder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
                linesCount++;
            }

            reader.close();

            this.content = stringBuilder.toString();
            this.content = this.content + this.time + " " + LocalDate.now().toString();

            String fileContent[] = this.content.split("\n");
            Arrays.sort(fileContent);
            this.content = Arrays.toString(fileContent);
            this.content = this.content.replaceAll("\\[", "");
            this.content = this.content.replaceAll("\\]", "");
            this.content = this.content.replaceAll(", ", "\n");

            FileWriter myWriter = new FileWriter("Records.txt");
            myWriter.write(this.content);
            myWriter.close();

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
    }

    @FXML
    private void records(ActionEvent event) throws IOException {
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
}
