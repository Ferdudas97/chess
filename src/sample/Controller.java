package sample;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import sample.models.AbstractFigure;
import sample.models.Board;
import sample.models.Pawn;
import sample.models.TakenBy;

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
    TakenBy whoIsMoving=TakenBy.White;
    private Boolean whiteKingMate=false;
    private Boolean blackKingMate=false;

    /*public Controller(){
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    whiteKingMate=board.isMate("whiteKing");
                    blackKingMate=board.isMate("blackKing");
                }
            }
        });
        thread.start();
        System.out.println(whiteKingMate);
    }*/
    public void onClickShowPossibleMoves(MouseEvent mouseEvent) {

        if (clickedButtonId!=((Button)mouseEvent.getSource()).getId() && clickedButtonId.length()>0) unshowPossibleMoves(list);

        clickedFigure=(Button)mouseEvent.getSource();
        if (clickedFigure.getId().contains(whoIsMoving.toString().toLowerCase())){
            clickedButtonId=clickedFigure.getId();
            board.figures.get(clickedFigure.getId()).check();
            list=board.figures.get(clickedFigure.getId()).getAvailable();



            showPossibleMoves(list);

        }


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
        if (clickedPane.styleProperty().getValue().equals("-fx-background-color: green") ) {


            int row=Integer.valueOf(clickedPane.getId().substring(1,2));
            int col=Integer.valueOf(clickedPane.getId().substring(2,3));
            int previousCol=board.figures.get(clickedButtonId).col;
            int previousRow=board.figures.get(clickedButtonId).row;
            TakenBy whoWasThere=AbstractFigure.board[row][col];
            Button buttonOnClickedPane=null;;
            AbstractFigure figureOnClickedPane=null;

            if ((board.getBlackKingCol()!=col || board.getBlackKingRow()!=row) && (board.getWhiteKingCol()!=col || board.getWhiteKingRow()!=row)) {


                board.figures.get(clickedButtonId).move(row, col);
                if (clickedPane.getChildren().size()>0) buttonOnClickedPane=(Button) clickedPane.getChildren().get(0);
                if (buttonOnClickedPane!=null){
                    figureOnClickedPane=board.figures.get(buttonOnClickedPane.getId());
                    board.figures.remove(buttonOnClickedPane.getId());

                }
                unshowPossibleMoves(list);

                if (whoIsMoving.equals(TakenBy.White)){
                    if (board.isMate("whiteKing")) {
                        board.figures.get(clickedButtonId).move(previousRow,previousCol);
                        AbstractFigure.board[row][col]=whoWasThere;
                        if (buttonOnClickedPane!=null){
                            board.figures.put(buttonOnClickedPane.getId(),figureOnClickedPane );
                        }

                    }
                    else {
                        whoIsMoving = TakenBy.Black;
                        if (clickedPane.getChildren().size() > 0) clickedPane.getChildren().clear();
                        if (clickedFigure.getId().contains("King")) {
                            board.setWhiteKingRow(row);
                            board.setWhiteKingCol(col);
                        }
                        clickedPane.getChildren().add(clickedFigure);
                    }
                }else {
                    if (board.isMate("blackKing")) {
                        board.figures.get(clickedButtonId).move(previousRow, previousCol);
                        AbstractFigure.board[row][col] = whoWasThere;
                        if (buttonOnClickedPane!=null){
                            board.figures.put(buttonOnClickedPane.getId(),figureOnClickedPane );
                        }
                    }
                    else  {
                        whoIsMoving=TakenBy.White;
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

}



