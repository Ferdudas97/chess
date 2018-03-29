package sample;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import sample.models.Bishop;
import sample.models.Board;

import java.util.ArrayList;

public class Controller {
    public Board board=new Board();
    public Button whiteHorse1;
    public Button whiteTower1;
    public GridPane chessBoard;


    public void onClick(MouseEvent mouseEvent) {
        chessBoard.getChildren().get(3).setStyle("-fx-background-color: green");
        for (Node node: chessBoard.getChildren()) {
            if (node!=null && GridPane.getColumnIndex(node)==1);

        }

    }

}



