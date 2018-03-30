package sample.models;


import java.util.HashMap;
import java.util.Map;

public class Board {
    public Integer board[][]=new Integer[8][8];
    public Map<String,AbstractFigure> figures =new HashMap<>();
    public Board(){

        for (int i = 0; i <8 ; i++) {

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

    }
}
