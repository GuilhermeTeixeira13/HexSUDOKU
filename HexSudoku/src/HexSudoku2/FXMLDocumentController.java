package HexSudoku2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class FXMLDocumentController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        board.getStyleClass().add("board");
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
    public void inicializaFacil(ActionEvent event) {
        board = createBoard(board, 1);
    }

    @FXML
    public void inicializaMedio(ActionEvent event) {
        board = createBoard(board, 2);
    }

    @FXML
    public void inicializaDificil(ActionEvent event) {
        board = createBoard(board, 3);
    }

    @FXML
    public GridPane createBoard(GridPane board, int dif) {
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

        Sudoku sudoku = new Sudoku(N, espacosBranco);
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

                    btn1.focusedProperty().addListener(new ChangeListener<Boolean>() {
                        public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                            casa.focusedProperty().addListener(new ChangeListener<Boolean>() {
                                public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                                    casa.setText(btn1.getText());
                                }
                            });
                        }
                    });
                    btn2.focusedProperty().addListener(new ChangeListener<Boolean>() {
                        public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                            casa.focusedProperty().addListener(new ChangeListener<Boolean>() {
                                public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                                    casa.setText(btn2.getText());
                                }
                            });
                        }
                    });
                    btn3.focusedProperty().addListener(new ChangeListener<Boolean>() {
                        public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                            casa.focusedProperty().addListener(new ChangeListener<Boolean>() {
                                public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                                    casa.setText(btn3.getText());
                                }
                            });
                        }
                    });
                    btn4.focusedProperty().addListener(new ChangeListener<Boolean>() {
                        public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                            casa.focusedProperty().addListener(new ChangeListener<Boolean>() {
                                public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                                    casa.setText(btn4.getText());
                                }
                            });
                        }
                    });
                    btn4.focusedProperty().addListener(new ChangeListener<Boolean>() {
                        public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                            casa.focusedProperty().addListener(new ChangeListener<Boolean>() {
                                public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                                    casa.setText(btn4.getText());
                                }
                            });
                        }
                    });
                    btn5.focusedProperty().addListener(new ChangeListener<Boolean>() {
                        public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                            casa.focusedProperty().addListener(new ChangeListener<Boolean>() {
                                public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                                    casa.setText(btn5.getText());
                                }
                            });
                        }
                    });
                    btn6.focusedProperty().addListener(new ChangeListener<Boolean>() {
                        public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                            casa.focusedProperty().addListener(new ChangeListener<Boolean>() {
                                public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                                    casa.setText(btn6.getText());
                                }
                            });
                        }
                    });
                    btn7.focusedProperty().addListener(new ChangeListener<Boolean>() {
                        public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                            casa.focusedProperty().addListener(new ChangeListener<Boolean>() {
                                public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                                    casa.setText(btn7.getText());
                                }
                            });
                        }
                    });
                    btn8.focusedProperty().addListener(new ChangeListener<Boolean>() {
                        public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                            casa.focusedProperty().addListener(new ChangeListener<Boolean>() {
                                public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                                    casa.setText(btn8.getText());
                                }
                            });
                        }
                    });
                    btn9.focusedProperty().addListener(new ChangeListener<Boolean>() {
                        public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                            casa.focusedProperty().addListener(new ChangeListener<Boolean>() {
                                public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                                    casa.setText(btn9.getText());
                                }
                            });
                        }
                    });
                    btn0.focusedProperty().addListener(new ChangeListener<Boolean>() {
                        public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                            casa.focusedProperty().addListener(new ChangeListener<Boolean>() {
                                public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                                    casa.setText(btn0.getText());
                                }
                            });
                        }
                    });
                    btnA.focusedProperty().addListener(new ChangeListener<Boolean>() {
                        public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                            casa.focusedProperty().addListener(new ChangeListener<Boolean>() {
                                public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                                    casa.setText(btnA.getText());
                                }
                            });
                        }
                    });
                    btnB.focusedProperty().addListener(new ChangeListener<Boolean>() {
                        public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                            casa.focusedProperty().addListener(new ChangeListener<Boolean>() {
                                public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                                    casa.setText(btnB.getText());
                                }
                            });
                        }
                    });
                    btnC.focusedProperty().addListener(new ChangeListener<Boolean>() {
                        public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                            casa.focusedProperty().addListener(new ChangeListener<Boolean>() {
                                public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                                    casa.setText(btnC.getText());
                                }
                            });
                        }
                    });
                    btnD.focusedProperty().addListener(new ChangeListener<Boolean>() {
                        public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                            casa.focusedProperty().addListener(new ChangeListener<Boolean>() {
                                public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                                    casa.setText(btnD.getText());
                                }
                            });
                        }
                    });
                    btnE.focusedProperty().addListener(new ChangeListener<Boolean>() {
                        public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                            casa.focusedProperty().addListener(new ChangeListener<Boolean>() {
                                public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                                    casa.setText(btnE.getText());
                                }
                            });
                        }
                    });
                    btnF.focusedProperty().addListener(new ChangeListener<Boolean>() {
                        public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                            casa.focusedProperty().addListener(new ChangeListener<Boolean>() {
                                public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                                    casa.setText(btnF.getText());
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
    private void testSolution(ActionEvent event) {
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
