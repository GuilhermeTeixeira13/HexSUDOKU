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
    
    @FXML
    Button inicializaFacil;

    @FXML
    Button inicializaMedio;

    @FXML
    Button inicializaDificil;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
    }
    
    @FXML
    private void testSolution(ActionEvent event) {
        if (event.getSource() instanceof Button) {
            int[][] BoardContent = getBoardContent(board);
            if(verifyIfItsFull(BoardContent)){
                if(CheckAnswer.isValidSudoku(BoardContent))
                    System.out.println("Solution accepted!");
                else
                    System.out.println("Solution unaccepted!");
            }
            else
                System.out.println("Board not completed yet!"); 
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
                if(board[row][col] == -1)
                    return false;
            }
        }
        return true;
    }

    public GridPane createBoard(GridPane board, int dif) {
        int N = 16, K=0, clues=0, min=0, max=0;
        
        if(dif == 1){
            max = 143;
            min = 111;
        }
        if(dif == 2){
            max = 109;
            min = 99;
        }
        if(dif == 3){
            max = 97;
            min = 86;
        }
        clues = (int)(Math.random() * ((max - min) + 1)) + min;
        K = (N*N)-clues;
        
        Sudoku sudoku = new Sudoku(N, K);
        sudoku.fillValues();
        int[][] SudokuBoard = sudoku.getBoard();

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
                board.add(textField, col, row);
            }
        }
        System.out.println("Board that you need to solve (dif="+dif+", Clues="+clues+"):");
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
}
