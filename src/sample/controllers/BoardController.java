package sample.controllers;


import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.models.AbstractFigure;
import sample.models.Board;
import sample.models.TakenBy;

import java.io.IOException;

import java.util.LinkedList;


public class BoardController {
    public Label labelWithTime;
    private Board board = new Board();
    public GridPane chessBoard;

    public Label turnNumber;
    public Label whoseTurn;
    public Button newGame;
    private String clickedButtonId = "";
     private LinkedList<String> list;
    private Button clickedFigure;
    private TakenBy whoIsMoving = TakenBy.White;
    private Boolean whiteKingMate = false;
    private Boolean blackKingMate = false;
    private Integer turn = 1;
    private Thread timer;


    public void onClickShowPossibleMoves(MouseEvent mouseEvent) {

        if (clickedButtonId != ((Button) mouseEvent.getSource()).getId() && clickedButtonId.length() > 0)
            unshowPossibleMoves(list);

        clickedFigure = (Button) mouseEvent.getSource();
        if (clickedFigure.getId().contains(whoIsMoving.toString().toLowerCase())) {
            clickedButtonId = clickedFigure.getId();
            board.figures.get(clickedFigure.getId()).check();
            list = board.figures.get(clickedFigure.getId()).getAvailable();


            showPossibleMoves(list);

        }


    }

    private void unshowPossibleMoves(LinkedList<String> cords) {
        Integer possibleRow, possibleCol;
        for (String cord : cords) {
            possibleRow = 7 - Integer.valueOf(cord.substring(0, 1));
            possibleCol = Integer.valueOf(cord.substring(1, 2));
            for (Node node : chessBoard.getChildren()) {


                Integer indexOfColumn = GridPane.getColumnIndex(node);
                Integer indexOfRow = GridPane.getRowIndex(node);
                if (indexOfColumn != null && indexOfRow != null && indexOfColumn.equals(possibleCol) && indexOfRow.equals(possibleRow)) {
                    if (possibleCol % 2 == possibleRow % 2) node.setStyle("-fx-background-color: black");
                    else node.setStyle("-fx-background-color: white");

                }

                if (indexOfColumn == null && indexOfRow != null && possibleCol == 0 && indexOfRow.equals(possibleRow)) {
                    if (possibleCol % 2 == possibleRow % 2) node.setStyle("-fx-background-color: black");
                    else node.setStyle("-fx-background-color: white");
                }
                if (indexOfColumn != null && indexOfRow == null && possibleRow == 0 && indexOfColumn.equals(possibleCol)) {
                    if (possibleCol % 2 == possibleRow % 2) node.setStyle("-fx-background-color: black");
                    else node.setStyle("-fx-background-color: white");
                }
                if (indexOfColumn == null && indexOfRow == null && possibleCol == 0 && possibleRow == 0) {
                    if (possibleCol % 2 == possibleRow % 2) node.setStyle("-fx-background-color: black");
                    else node.setStyle("-fx-background-color: white");
                }
            }

        }

    }

    private void showPossibleMoves(LinkedList<String> cords) {

        Integer possibleRow, possibleCol;
        for (String cord : cords) {
            possibleRow = 7 - Integer.valueOf(cord.substring(0, 1));
            possibleCol = Integer.valueOf(cord.substring(1, 2));
            for (Node node : chessBoard.getChildren()) {


                Integer indexOfColumn = GridPane.getColumnIndex(node);
                Integer indexOfRow = GridPane.getRowIndex(node);
                if (indexOfColumn != null && indexOfRow != null && indexOfColumn.equals(possibleCol) && indexOfRow.equals(possibleRow)) {
                    node.setStyle("-fx-background-color: green");
                }
                if (indexOfColumn == null && indexOfRow != null && possibleCol == 0 && indexOfRow.equals(possibleRow))
                    node.setStyle("-fx-background-color: green");
                if (indexOfColumn != null && indexOfRow == null && possibleRow == 0 && indexOfColumn.equals(possibleCol))
                    node.setStyle("-fx-background-color: green");
                if (indexOfColumn == null && indexOfRow == null && possibleCol == 0 && possibleRow == 0)
                    node.setStyle("-fx-background-color: green");


            }

        }
    }


    public void moveFigureThere(MouseEvent mouseEvent) {
        Pane clickedPane = (Pane) mouseEvent.getSource();
        if (clickedPane.styleProperty().getValue().equals("-fx-background-color: green")) {


            int row = Integer.valueOf(clickedPane.getId().substring(1, 2));
            int col = Integer.valueOf(clickedPane.getId().substring(2, 3));
            int previousCol = board.figures.get(clickedButtonId).col;
            int previousRow = board.figures.get(clickedButtonId).row;
            TakenBy whoWasThere = AbstractFigure.board[row][col];
            Button buttonOnClickedPane = null;

            AbstractFigure figureOnClickedPane = null;

            if ((board.getBlackKingCol() != col || board.getBlackKingRow() != row) && (board.getWhiteKingCol() != col || board.getWhiteKingRow() != row)) {


                board.figures.get(clickedButtonId).move(row, col);
                if (clickedPane.getChildren().size() > 0)
                    buttonOnClickedPane = (Button) clickedPane.getChildren().get(0);
                if (buttonOnClickedPane != null) {
                    figureOnClickedPane = board.figures.get(buttonOnClickedPane.getId());
                    board.figures.remove(buttonOnClickedPane.getId());

                }
                unshowPossibleMoves(list);

                if (whoIsMoving.equals(TakenBy.White)) {
                    if (board.isMate("whiteKing")) {
                        board.figures.get(clickedButtonId).move(previousRow, previousCol);
                        AbstractFigure.board[row][col] = whoWasThere;
                        if (buttonOnClickedPane != null) {
                            board.figures.put(buttonOnClickedPane.getId(), figureOnClickedPane);
                        }

                    } else {
                        whoIsMoving = TakenBy.Black;
                        updateTurn();
                        if (clickedPane.getChildren().size() > 0) clickedPane.getChildren().clear();
                        if (clickedFigure.getId().contains("King")) {
                            board.setWhiteKingRow(row);
                            board.setWhiteKingCol(col);
                        }
                        clickedPane.getChildren().add(clickedFigure);
                    }
                } else {
                    if (board.isMate("blackKing")) {
                        board.figures.get(clickedButtonId).move(previousRow, previousCol);
                        AbstractFigure.board[row][col] = whoWasThere;
                        if (buttonOnClickedPane != null) {
                            board.figures.put(buttonOnClickedPane.getId(), figureOnClickedPane);
                        }
                    } else {
                        whoIsMoving = TakenBy.White;
                        updateTurn();
                        if (clickedPane.getChildren().size() > 0) clickedPane.getChildren().clear();
                        if (clickedFigure.getId().contains("King")) {
                            board.setBlackKingRow(row);
                            board.setBlackKingCol(col);
                        }
                        clickedPane.getChildren().add(clickedFigure);
                    }
                }


            }
        }
    }

    private void updateTurn() {
        turn++;
        turnNumber.setText("Turn:  " + turn.toString());
        if (whoIsMoving.equals(TakenBy.White)) whoseTurn.setText("player White");
        else whoseTurn.setText("player Black");
        changePawn();
    }

    private void changePawn() {
        if ((clickedFigure.getId().contains("whitePawn") && board.figures.get(clickedFigure.getId()).row == AbstractFigure.board.length - 1) || (clickedFigure.getId().contains("blackPawn") && board.figures.get(clickedFigure.getId()).row == 0)) {
            Parent root;
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource(
                                "/sample/views/pawnChange.fxml"
                        )
                );

                Pane pane = (Pane) loader.load();
                root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
                PawnChangeBoardController controller =
                        loader.<PawnChangeBoardController>getController();
                controller.initData(clickedFigure, board.figures);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public void startNewGame(MouseEvent mouseEvent) {
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/sample/views/chess.fxml"
                    )
            );

            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

            //set Stage boundaries to visible bounds of the main screen

            Pane pane = (Pane) loader.load();
            AbstractFigure.board=new Board().getBoard();;
            root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, Screen.getPrimary().getVisualBounds().getMaxX(),  Screen.getPrimary().getVisualBounds().getMaxY()-30));
            stage.setTitle("Chess made by me :D");

            stage.show();
            this.newGame.getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}



