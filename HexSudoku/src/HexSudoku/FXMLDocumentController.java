package HexSudoku;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable {

    @FXML
    GridPane board;

    @FXML
    Button teste;

    @FXML
    Button inicializaFacil;

    @FXML
    Button inicializaMedio;

    @FXML
    Button inicializaDificil;

    @FXML
    Button b1;

    @FXML
    Button b2;

    @FXML
    Button b3;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        board.getStyleClass().add("board");
    }

    @FXML
    private void testSolution(ActionEvent event) {
        if (event.getSource() instanceof Button) {
            int[][] BoardContent = getBoardContent(board);
            if (verifyIfItsFull(BoardContent)) {
                if (CheckAnswer.isValidSudoku(BoardContent)) {
                    System.out.println("Solution accepted!");
                } else {
                    System.out.println("Solution unaccepted!");
                }
            } else {
                System.out.println("Board not completed yet!");
            }
            printBoard(BoardContent);
        }
    }

    @FXML
    private void inicializaFacil(ActionEvent event) {
        board = createBoard(board, 1);
    }

    @FXML
    private void inicializaMedio(ActionEvent event) {
        board = createBoard(board, 2);
    }

    @FXML
    private void inicializaDificl(ActionEvent event) {
        board = createBoard(board, 3);
    }

    public void printBoard(int[][] board) {
        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 16; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.print("\n");
        }
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

    public GridPane createBoard(GridPane board, int dif) {
        int N = 16, K = 0, clues = 0, min = 0, max = 0;

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
        clues = (int) (Math.random() * ((max - min) + 1)) + min;
        K = (N * N) - clues;

        Sudoku sudoku = new Sudoku(N, K);
        sudoku.fillValues();
        int[][] SudokuBoard = sudoku.getBoard();

        PseudoClass right = PseudoClass.getPseudoClass("right");
        PseudoClass bottom = PseudoClass.getPseudoClass("bottom");

        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 16; col++) {
                TextField textField = new TextField();

                textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
                    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                        b1.focusedProperty().addListener(new ChangeListener<Boolean>() {
                            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                                textField.setText(b1.getText());
                            }
                        });
                    }
                });

                textField.getStyleClass().add("cell");
                textField.pseudoClassStateChanged(right, col == 3 || col == 7 || col == 11);
                textField.pseudoClassStateChanged(bottom, row == 3 || row == 7 || row == 11);

                if (SudokuBoard[row][col] != -1) {
                    textField.setText((Integer.toHexString(SudokuBoard[row][col])).toUpperCase());
                    textField.setEditable(false);
                } else {
                    textField.setText("");
                    textField.setTextFormatter(new TextFormatter<>(change -> {
                        if (!isValid(change.getText())) {
                            return null;
                        }
                        return change;
                    }));
                    textField.setEditable(true);
                }
                board.add(textField, col, row);
            }
        }
        System.out.println("Board that you need to solve (dif=" + dif + ", Clues=" + clues + "):");
        printBoard(SudokuBoard);
        System.out.println();
        return board;
    }

    public int[][] getBoardContent(GridPane board) {
        int[][] content = new int[16][16];
        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 16; col++) {
                TextField textField = new TextField();
                textField = (TextField) getNodeFromGridPane(board, row, col);
                if (!textField.getText().equals("")) {
                    content[row][col] = Integer.parseInt(textField.getText(), 16);
                } else {
                    content[row][col] = -1;
                }
            }
        }
        return content;
    }

    private Node getNodeFromGridPane(GridPane gridPane, int row, int col) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    public boolean isValid(String text) {
        if (text.length() == 1) {
            if ((text.charAt(0) >= 48 && text.charAt(0) <= 57) || (text.charAt(0) >= 65 && text.charAt(0) <= 70) || text.charAt(0) == 8) {
                return true;
            }
            return false;
        } else {
            return false;
        }
    }

    public void escapeKeyPressed(KeyCode keyCode) {
        if (keyCode == KeyCode.ESCAPE) {
            System.out.println("escape got called");
        }
    }

    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() instanceof Button) {
            Button b = (Button) event.getSource();
            
        }
    }
}
