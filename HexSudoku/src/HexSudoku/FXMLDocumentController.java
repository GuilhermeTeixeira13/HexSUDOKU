package HexSudoku;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class FXMLDocumentController implements Initializable {

    @FXML
    GridPane board;

    @FXML
    Button teste;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        board = createBoard(board);
    }

    @FXML
    private void testSolution(ActionEvent event) {
        if (event.getSource() instanceof Button) {
            int[][] BoardContent = getBoardContent(board);
            printBoard(BoardContent);
            if(verifyIfItsFull(BoardContent)){
                if(CheckAnswer.isValidSudoku(BoardContent))
                    System.out.println("é válido");
                else
                    System.out.println("não é válido");
            }
            else
                System.out.println("tabuleiro incompleto");         
        }
    }

    public void printBoard(int[][] board) {
        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 16; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.print("\n");
        }
        System.out.println("\n");
    }
    
    public boolean verifyIfItsFull(int[][] board) {
        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 16; col++) {
                if(board[row][col] == -1)
                    return false;
            }
        }
        return true;
    }

    public GridPane createBoard(GridPane board) {
        int N = 16, K = 1;
        Sudoku sudoku = new Sudoku(N, K);
        sudoku.fillValues();

        int[][] SudokuBoard = sudoku.geetBoard();

        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 16; col++) {
                TextField textField = new TextField();
                if (SudokuBoard[row][col] != -1) {
                    textField.setText((Integer.toHexString(SudokuBoard[row][col])).toUpperCase());
                    textField.setEditable(false);
                } else {
                    textField.setText("");
                    textField.setEditable(true);
                }

                board.add(textField, row, col);
            }
        }
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
}
