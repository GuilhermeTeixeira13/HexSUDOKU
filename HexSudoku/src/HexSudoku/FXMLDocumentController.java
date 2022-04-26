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
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() instanceof Button) {
            String[][] content = getContent(board);
            for (int row = 0; row < 16; row++) {
                for (int col = 0; col < 16; col++){
                    System.out.print(content[row][col]+" ");
                }
            System.out.print("\n");
            }
            System.out.println("\n\n\n");
        }
    }
    
    public GridPane createBoard(GridPane board){
        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 16; col++) {
                TextField textField = createTextField();
                textField.setText("2");
                board.add(textField, row, col);
            }
        }
        return board;
    }
    public String[][] getContent(GridPane board){
        String[][] content = new String[16][16];
        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 16; col++) {
                TextField textField = createTextField();
                textField = (TextField) getNodeFromGridPane(board, row, col);
                content[row][col] = textField.getText();
            }
        }
        return content;
    }
    private TextField createTextField() {
        TextField textField = new TextField();

        // restrict input to integers:
        textField.setTextFormatter(new TextFormatter<Integer>(c -> {
            if (c.getControlNewText().matches("\\d?")) {
                return c ;
            } else {
                return null ;
            }
        }));
        return textField ;
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
