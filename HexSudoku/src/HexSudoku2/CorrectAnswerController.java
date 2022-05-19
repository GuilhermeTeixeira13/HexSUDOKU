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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
    int dif;

    StringBuilder stringBuilder;
    ArrayList<timeDateNameDif> BestTimesDatesByOrder = new ArrayList<>();

    private Stage stage;
    private Scene scene;
    private Parent root;

    public String getUsername() {
        return this.userName;
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        labeltime.setText(this.time);

        LocalTime timeMade = LocalTime.parse(this.time, DateTimeFormatter.ofPattern("HH:mm:ss"));

        String bestTimeDif = "";

        for (int i = 0; i < BestTimesDatesByOrder.size(); i++) {
            if (BestTimesDatesByOrder.get(i).user.equals(this.userName) && BestTimesDatesByOrder.get(i).dif.equals(String.valueOf(this.dif))) {
                bestTimeDif = BestTimesDatesByOrder.get(i).time;
                break;
            }
        }

        System.out.println("bestTimeDif->" + bestTimeDif);

        if (timeMade.compareTo(LocalTime.parse(bestTimeDif, DateTimeFormatter.ofPattern("HH:mm:ss"))) == 0) {
            System.out.println("New record set! --> " + this.time);
            LabelNewRecord.setVisible(true);
        } else {
            LabelNewRecord.setVisible(false);
        }

    }

    public CorrectAnswerController(String time, String username, int dif) throws IOException {
        this.time = time;
        this.userName = username;
        this.dif = dif;
        timeDateNameDif timeDateNameDif;
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
            this.content = this.content + this.time + " " + LocalDate.now().toString() + " " + username + " " + dif;

            String fileContent[] = this.content.split("\n");
            Arrays.sort(fileContent);
            this.content = Arrays.toString(fileContent);
            this.content = this.content.replaceAll("\\[", "");
            this.content = this.content.replaceAll("\\]", "");
            this.content = this.content.replaceAll(", ", "\n");

            FileWriter myWriter = new FileWriter("Records.txt");
            myWriter.write(this.content);
            myWriter.close();

            BestTimesDatesByOrder.clear();

            int c = 0;
            String lineContent[];
            reader = new BufferedReader(new FileReader("Records.txt"));
            while (((line = reader.readLine()) != null)) {
                lineContent = line.split(" ");
                LocalDate date = LocalDate.parse(lineContent[1]);
                timeDateNameDif = new timeDateNameDif(lineContent[0], date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)), lineContent[2], lineContent[3]);
                BestTimesDatesByOrder.add(timeDateNameDif);
                c++;
            }
            reader.close();

        } catch (FileNotFoundException ex) {
        }
    }

    @FXML
    private void records(ActionEvent event) throws IOException {
        String recordsString = "";

        int countFaceis = 0;
        recordsString = "EASY\n";
        for (int i = 0; i < BestTimesDatesByOrder.size() && countFaceis < 3; i++) {
            if (BestTimesDatesByOrder.get(i).user.equals(this.userName)) {
                if (BestTimesDatesByOrder.get(i).dif.equals("1")) {
                    recordsString = recordsString + (countFaceis + 1) + "º -> " + BestTimesDatesByOrder.get(i).time + " " + BestTimesDatesByOrder.get(i).date + "\n";
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
            if (BestTimesDatesByOrder.get(i).user.equals(this.userName)) {
                if (BestTimesDatesByOrder.get(i).dif.equals("2")) {
                    recordsString = recordsString + (countMedios + 1) + "º -> " + BestTimesDatesByOrder.get(i).time + " " + BestTimesDatesByOrder.get(i).date + "\n";
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
            if (BestTimesDatesByOrder.get(i).user.equals(this.userName)) {
                if (BestTimesDatesByOrder.get(i).dif.equals("3")) {
                    recordsString = recordsString + (countDificeis + 1) + "º -> " + BestTimesDatesByOrder.get(i).time + " " + BestTimesDatesByOrder.get(i).date + "\n";
                    countDificeis++;
                }
            }
        }
        if (countDificeis == 0) {
            recordsString = recordsString + "You didnt complete any hard board yet!\n";
        }
        System.out.println("\n10 best times:\n" + recordsString);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        int countTotal = countFaceis + countMedios + countDificeis;
        if (countTotal != 0) {
            alert.setTitle("RECORDS");
            alert.setHeaderText(recordsString);
        } else {
            alert.setTitle("NO RECORDS");
            alert.setHeaderText("You didnt complete any board yet!");
        }

        alert.showAndWait();
    }

    @FXML
    private void playAgain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDificuldade.fxml"));
        root = loader.load();

        DificuldadeController dificuldadeController = loader.getController();
        dificuldadeController.displayName(this.getUsername());

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add("fxmlview.css");
        stage.setScene(scene);
        stage.setTitle("HexSudoku - Dificulty level");
        stage.show();
    }

    @FXML
    public void logOutAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add("fxmlview.css");
        stage.setScene(scene);
        stage.setTitle("HexSudoku - Login");
        stage.show();
    }
}
