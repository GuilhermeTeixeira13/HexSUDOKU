package HexSudoku2;

import java.io.IOException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.PseudoClass;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameController implements Initializable {

    @FXML
    GridPane board;

    @FXML
    Button btn0;

    @FXML
    Button btn1;

    @FXML
    Button btn2;

    @FXML
    Button btn3;

    @FXML
    Button btn4;

    @FXML
    Button btn5;

    @FXML
    Button btn6;

    @FXML
    Button btn7;

    @FXML
    Button btn8;

    @FXML
    Button btn9;

    @FXML
    Button btnA;

    @FXML
    Button btnB;

    @FXML
    Button btnC;

    @FXML
    Button btnD;

    @FXML
    Button btnE;

    @FXML
    Button btnF;

    @FXML
    Button btnpause;

    @FXML
    Label labelCronometro;

    @FXML
    Label labelUsername;

    @FXML
    Button btnFacil;

    @FXML
    Button btnMedio;

    @FXML
    Button btnDificil;

    @FXML
    Button btnRecord;

    @FXML
    Button btnTestSolution;

    @FXML
    Button btnDelete;
    
    @FXML
    Button btnQuit;

    @FXML
    Button[] arrayButtons;
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    int dificuldade;
    String nomeUser;

    Timeline timeline;
    LocalTime time = LocalTime.parse("00:00:00");
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

    public GameController(int dif, String username) {
        this.dificuldade = dif;
        this.nomeUser = username;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializeButtonArray();
        timeline = new Timeline(new KeyFrame(Duration.millis(1000), ae -> incrementTime()));
        timeline.setCycleCount(Animation.INDEFINITE);

        System.out.println("Username -> " + nomeUser);

        labelUsername.setText(this.nomeUser);
        board = createBoard(this.dificuldade, board);
        timeline.play();
    }

    public void inicializeButtonArray() {
        arrayButtons = new Button[16];
        arrayButtons[0] = btn0;
        arrayButtons[1] = btn1;
        arrayButtons[2] = btn2;
        arrayButtons[3] = btn3;
        arrayButtons[4] = btn4;
        arrayButtons[5] = btn5;
        arrayButtons[6] = btn6;
        arrayButtons[7] = btn7;
        arrayButtons[8] = btn8;
        arrayButtons[9] = btn9;
        arrayButtons[10] = btnA;
        arrayButtons[11] = btnB;
        arrayButtons[12] = btnC;
        arrayButtons[13] = btnD;
        arrayButtons[14] = btnE;
        arrayButtons[15] = btnF;
    }

    private void incrementTime() {
        time = time.plusSeconds(1);
        labelCronometro.setText(time.format(dtf));
    }

    @FXML
    private void pauseTimer(ActionEvent event) {
        if (timeline.getStatus().equals(Animation.Status.PAUSED)) {
            timeline.play();
            board.setVisible(true);
            btnpause.setText("Pause");
        } else if (timeline.getStatus().equals(Animation.Status.RUNNING)) {
            timeline.pause();
            board.setVisible(false);
            btnpause.setText("Continue");
        }
    }

    @FXML
    public void printBoard(int[][] board) {
        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 16; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.print("\n");
        }
    }

    @FXML
    public GridPane createBoard(int dif, GridPane b) {
        int N = 16;
        int espacosBranco = 0;
        int pistas = 0;
        int min = 0;
        int max = 0;
        if (dif == 1) {
            max = 143;
            min = 111;
        }
        if (dif == 2) {
            max = 109;
            min = 99;
        }
        if (dif == 3) {
            max = 97;
            min = 86;
        }
        pistas = (int) (Math.random() * ((max - min) + 1)) + min;

        espacosBranco = (N * N) - pistas;

        GeradorBoards sudoku = new GeradorBoards(N, 1);
        sudoku.fillValues();

        int[][] SudokuBoard = sudoku.getBoard();

        PseudoClass right = PseudoClass.getPseudoClass("right");
        PseudoClass bottom = PseudoClass.getPseudoClass("bottom");

        for (int colunas = 0; colunas < 16; colunas++) {
            for (int linhas = 0; linhas < 16; linhas++) {
                TextField casa = new TextField();
                StackPane cell = new StackPane();
                cell.getStyleClass().add("cell");
                cell.pseudoClassStateChanged(right, colunas == 3 || colunas == 7 || colunas == 11);
                cell.pseudoClassStateChanged(bottom, linhas == 3 || linhas == 7 || linhas == 11);
                cell.getChildren().add(casa);

                if (SudokuBoard[linhas][colunas] != -1) {
                    casa.setText((Integer.toHexString(SudokuBoard[linhas][colunas])).toUpperCase());
                    casa.setStyle("-fx-control-inner-background: rgb(196,243,255);");
                    casa.setEditable(false);
                } else {
                    casa.setText("");
                    casa.setEditable(false);

                    for (int i = 0; i < 16; i++) {
                        Button but = arrayButtons[i];
                        but.focusedProperty().addListener(new ChangeListener<Boolean>() {
                            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                                casa.focusedProperty().addListener(new ChangeListener<Boolean>() {
                                    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                                        casa.setText(but.getText());
                                    }
                                });
                            }
                        });
                    }

                    btnDelete.focusedProperty().addListener(new ChangeListener<Boolean>() {
                        public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                            casa.focusedProperty().addListener(new ChangeListener<Boolean>() {
                                public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                                    casa.setText("");
                                }
                            });
                        }
                    });
                }

                board.add(cell, colunas, linhas);
            }
        }
        System.out.println("Board that you need to solve (dif=" + dif + ", Pistas=" + pistas + "):");
        printBoard(SudokuBoard);
        System.out.println();
        return board;
    }

    @FXML
    private void testSolution(ActionEvent event) throws IOException {
        int[][] BoardContent = getBoardContent(board);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        if (verifyIfItsFull(BoardContent)) {
            if (CheckAnswer.isValidSudoku(BoardContent)) {
                System.out.println("Solution accepted! Solved in: " + labelCronometro.getText());
                CorrectAnswerController correctAnswerController = new CorrectAnswerController(labelCronometro.getText(), this.nomeUser);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLCorrectAnswer.fxml"));
                loader.setController(correctAnswerController);
                Parent root = loader.load();

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                System.out.println("Solution unaccepted!");
                alert.setTitle("WRONG SOLUTION");
                alert.setHeaderText("Your submission failed in the tests!");
                alert.showAndWait();
            }
        } else {
            System.out.println("Board not completed yet!");
            alert.setTitle("BOARD NOT COMPLETED");
            alert.setHeaderText("You need to complete the board in order to submit the solution!");
            alert.showAndWait();
        }
        printBoard(BoardContent);

    }

    public int[][] getBoardContent(GridPane board) {
        int[][] content = new int[16][16];
        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 16; col++) {
                TextField casa = new TextField();
                StackPane cell = new StackPane();
                cell = (StackPane) getNodeFromGridPane(board, row, col);
                casa = (TextField) cell.getChildren().get(0);
                if (!casa.getText().equals("")) {
                    content[row][col] = Integer.parseInt(casa.getText(), 16);
                } else {
                    content[row][col] = -1;
                }
            }
        }
        return content;
    }

    private Node getNodeFromGridPane(GridPane gridPane, int row, int col) {
        for (Node node : gridPane.getChildren()) {
            if (gridPane.getColumnIndex(node) == col && gridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    public boolean verifyIfItsFull(int[][] board) {
        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 16; col++) {
                if (board[row][col] == -1) {
                    return false;
                }
            }
        }
        return true;
    }
}
