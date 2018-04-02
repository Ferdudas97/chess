package sample.models;


import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Board {
    public Map<String,AbstractFigure> figures =new HashMap<>();
    private int whiteKingRow;
    private int whiteKingCol;
    private int blackKingRow;
    private int blackKingCol;
    private  TakenBy board [][]=new TakenBy[8][8];


    public Board(){

        for (int i = 0; i <8 ; i++) {
            if (i == 0 || i == 1) Arrays.fill(board[i], TakenBy.White);
            else if (i == 6 || i == 7) Arrays.fill(board[i], TakenBy.Black);
            else Arrays.fill(board[i], TakenBy.Empty);

            figures.put("whitePawn"+i,new Pawn(1,i,TakenBy.White));
            figures.put("blackPawn"+i,new Pawn(6,i,TakenBy.Black));
        }

        figures.put("whiteTower0",new Tower(0,0,TakenBy.White));
        figures.put("whiteTower1",new Tower(0,7,TakenBy.White));
        figures.put("whiteHorse0",new Horse(0,1,TakenBy.White));
        figures.put("whiteHorse1",new Horse(0,6,TakenBy.White));
        figures.put("whiteBishop0",new Bishop(0,2,TakenBy.White));
        figures.put("whiteBishop1",new Bishop(0,5,TakenBy.White));
        figures.put("whiteQueen",new Queen(0,3,TakenBy.White));
        figures.put("whiteKing",new King(0,4,TakenBy.White));
        figures.put("blackTower0",new Tower(7,0,TakenBy.Black));
        figures.put("blackTower1",new Tower(7,7,TakenBy.Black));
        figures.put("blackHorse0",new Horse(7,1,TakenBy.Black));
        figures.put("blackHorse1",new Horse(7,6,TakenBy.Black));
        figures.put("blackBishop0",new Bishop(7,2,TakenBy.Black));
        figures.put("blackBishop1",new Bishop(7,5,TakenBy.Black));
        figures.put("blackQueen",new Queen(7,3,TakenBy.Black));
        figures.put("blackKing",new King(7,4,TakenBy.Black));
        setBlackKingCol(figures.get("blackKing").col);
        setBlackKingRow(figures.get("blackKing").row);
        setWhiteKingCol(figures.get("whiteKing").col);
        setWhiteKingRow(figures.get("whiteKing").row);


    }
    public boolean isMate(String king){
        int kingRow=figures.get(king).row;
        int kingCol=figures.get(king).col;
        String kingPosition=String.valueOf(kingRow)+String.valueOf(kingCol);
        for (AbstractFigure figure: figures.values()) {
            figure.check();
            if (figure.available.contains(kingPosition)){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText("It`s mate");
                alert.setContentText(" You should protect the King");

                alert.showAndWait();
                return true;
            }

        }

        return false;
    }


    public void setWhiteKingCol(int whiteKingCol) {
        this.whiteKingCol = whiteKingCol;
    }

    public void setWhiteKingRow(int whiteKingRow) {
        this.whiteKingRow = whiteKingRow;
    }

    public void setBlackKingRow(int blackKingRow) {
        this.blackKingRow = blackKingRow;
    }

    public void setBlackKingCol(int blacKingCol) {
        blackKingCol = blacKingCol;
    }

    public int getBlackKingCol() {
        return blackKingCol;
    }

    public int getBlackKingRow() {
        return blackKingRow;
    }

    public int getWhiteKingCol() {
        return whiteKingCol;
    }

    public int getWhiteKingRow() {
        return whiteKingRow;
    }

    public TakenBy[][] getBoard() {
        return board;
    }
}
