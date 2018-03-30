package sample;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import sample.models.AbstractFigure;
import sample.models.Board;
import sample.models.Pawn;

import java.util.LinkedList;

public class Controller {
    public Board board=new Board();
    public Button whiteHorse1;
    public Button whiteTower1;
    public GridPane chessBoard;
    public Button whiteBishop1;
    public Button whiteKing;
    public Button whiteBishop2;
    public Button whiteHorse2;
    public Button whiteTower2;
    public Button whitePawn0;
    public Button whitePawn1;
    public Button whiteQueen;
    public Button whitePawn3;
    public Button whitePawn4;
    public Button whitePawn5;
    public Button whitePawn6;
    public Button whitePawn7;
    public Button whitePawn2;
    public Button blackPawn0;
    private String clickedButtonId="";
    LinkedList<String> list;
    private Button clickedFigure;


    public void onClickShowPossibleMoves(MouseEvent mouseEvent) {
        if (clickedButtonId!=((Button)mouseEvent.getSource()).getId() && clickedButtonId.length()>0) unshowPossibleMoves(list);

        clickedFigure=(Button)mouseEvent.getSource();

        clickedButtonId=clickedFigure.getId();
        board.figures.get(clickedFigure.getId()).check();
        list=board.figures.get(clickedFigure.getId()).getAvailable();



        showPossibleMoves(list);


    }
    private void unshowPossibleMoves(LinkedList<String> cords){
        Integer possibleRow,possibleCol;
        for (String cord :cords) {
            possibleRow=7-Integer.valueOf(cord.substring(0,1));
            possibleCol=Integer.valueOf(cord.substring(1,2));
            for (Node node : chessBoard.getChildren()) {


                Integer indexOfColumn = GridPane.getColumnIndex(node);
                Integer indexOfRow = GridPane.getRowIndex(node);
                if (indexOfColumn != null && indexOfRow != null && indexOfColumn.equals(possibleCol) && indexOfRow.equals(possibleRow)) {
                     if (possibleCol%2==possibleRow%2) node.setStyle("-fx-background-color: black");
                     else node.setStyle("-fx-background-color: white");

                }

                if (indexOfColumn == null && indexOfRow != null && possibleCol == 0 && indexOfRow.equals(possibleRow)){
                    if (possibleCol%2==possibleRow%2) node.setStyle("-fx-background-color: black");
                    else node.setStyle("-fx-background-color: white");
                }
                if (indexOfColumn != null && indexOfRow == null && possibleRow == 0 && indexOfColumn.equals(possibleCol)){
                    if (possibleCol%2==possibleRow%2) node.setStyle("-fx-background-color: black");
                    else node.setStyle("-fx-background-color: white");
                }
                if (indexOfColumn == null && indexOfRow == null && possibleCol == 0 && possibleRow == 0) {
                    if (possibleCol % 2 == possibleRow % 2) node.setStyle("-fx-background-color: black");
                    else node.setStyle("-fx-background-color: white");
                }
            }

        }

    }
    private void showPossibleMoves(LinkedList<String> cords){

        Integer possibleRow,possibleCol;
        for (String cord :cords) {
            possibleRow=7-Integer.valueOf(cord.substring(0,1));
            possibleCol=Integer.valueOf(cord.substring(1,2));
            for (Node node : chessBoard.getChildren()) {


                Integer indexOfColumn=GridPane.getColumnIndex(node);
                Integer indexOfRow=GridPane.getRowIndex(node);
                if (indexOfColumn!=null && indexOfRow!=null && indexOfColumn.equals(possibleCol) && indexOfRow.equals(possibleRow) ){
                    node.setStyle("-fx-background-color: green");
                }
                if (indexOfColumn==null && indexOfRow!=null && possibleCol==0 && indexOfRow.equals(possibleRow)) node.setStyle("-fx-background-color: green") ;
                if (indexOfColumn!=null && indexOfRow==null && possibleRow==0 && indexOfColumn.equals(possibleCol)) node.setStyle("-fx-background-color: green") ;
                if (indexOfColumn==null && indexOfRow==null && possibleCol==0 && possibleRow==0) node.setStyle("-fx-background-color: green") ;



            }

        }
    }


    public void moveFigureThere(MouseEvent mouseEvent) {
        Pane clickedPane = (Pane)mouseEvent.getSource();
        if (clickedPane.styleProperty().getValue().equals("-fx-background-color: green")) {
            if (clickedPane.getChildren().size()>0) clickedPane.getChildren().clear();
            clickedPane.getChildren().add(clickedFigure);

            int row=Integer.valueOf(clickedPane.getId().substring(1,2));
            int col=Integer.valueOf(clickedPane.getId().substring(2,3));
            board.figures.get(clickedButtonId).move(row,col);

            for (int i = 0; i <8 ; i++) {
                for (int j = 0; j <8 ; j++) {
                    System.out.print(AbstractFigure.board[i][j].toString()+"   ");
                }
                System.out.println();

            }

            unshowPossibleMoves(list);
        }
    }
}



