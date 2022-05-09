package HexSudoku2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
    ArrayList<PairsTimeDate> arrayTimesDates = new ArrayList<>();
    ArrayList<String> tenBestTimesDatesByOrder = new ArrayList<>();

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        labeltime.setText(this.time);

        LocalTime timeMade = LocalTime.parse(this.time, DateTimeFormatter.ofPattern("HH:mm:ss"));

        if (tenBestTimesDatesByOrder.size() == 1) {
            if (timeMade.isBefore(LocalTime.parse(tenBestTimesDatesByOrder.get(0), DateTimeFormatter.ofPattern("HH:mm:ss"))) || timeMade.compareTo(LocalTime.parse(tenBestTimesDatesByOrder.get(0), DateTimeFormatter.ofPattern("HH:mm:ss"))) == 0) {
                LabelNewRecord.setVisible(true);
                System.out.println("New record set! --> " + this.time);
            } else {
                LabelNewRecord.setVisible(false);
            }
        } else {
            if (timeMade.isBefore(LocalTime.parse(tenBestTimesDatesByOrder.get(1), DateTimeFormatter.ofPattern("HH:mm:ss")))) {
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
        PairsTimeDate timeDate;
        BufferedReader reader;

        try {
            LocalTime timeMade = LocalTime.parse(this.time, DateTimeFormatter.ofPattern("HH:mm:ss"));
            LocalDate DateMade = LocalDate.now();
            SimpleDateFormat df = new SimpleDateFormat("dd-mm-yyyy");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-mm-yyyy");
            int linesCount = 1;

            reader = new BufferedReader(new FileReader("Records.txt"));
            this.stringBuilder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
                linesCount++;
            }

            //stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            reader.close();

            this.content = stringBuilder.toString();
            this.content = this.content + this.time + " " + LocalDate.now().toString();
            timeDate = new PairsTimeDate(LocalTime.parse(timeMade.toString(), DateTimeFormatter.ofPattern("HH:mm:ss")), LocalDate.now());
            arrayTimesDates.add(timeDate);

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
            String teste[];
            reader = new BufferedReader(new FileReader("Records.txt"));
            while (((line = reader.readLine()) != null) && c < limite) {
                teste = line.split(" ");
                tenBestTimesDatesByOrder.add(teste[0]);
            }
            reader.close();

        } catch (FileNotFoundException ex) {
        }
    }

    @FXML
    private void records(ActionEvent event) throws IOException {
        System.out.println("\n10 best times:\n" + tenBestTimesDatesByOrder.toString());

        String recordsString = "";
        for (int i = 0; i < tenBestTimesDatesByOrder.size(); i++) {
            recordsString = recordsString + (i + 1) + "º -> " + tenBestTimesDatesByOrder.get(i) + "\n";
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("YOUR 10 BEST TIMES");
        alert.setHeaderText(recordsString);
        alert.showAndWait();
    }
}
