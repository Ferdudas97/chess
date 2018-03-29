package sample.models;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Board {
    public Integer board[][]=new Integer[8][8];
    public Map<String,AbstractFigure> whiteChess=new HashMap<>();
    public Map<String,AbstractFigure> blackChess=new HashMap<>();
    public Board(){

        for (int i = 0; i <8 ; i++) {

            whiteChess.put("whitePawn"+i,new Pawn(1,i,TakenBy.White));
            blackChess.put("blackPawn"+i,new Pawn(6,i,TakenBy.Black));
        }
        whiteChess.get("whitePawn0").check();

        whiteChess.put("whiteTower0",new Tower(0,0,TakenBy.White));
        whiteChess.put("whiteTower1",new Tower(0,7,TakenBy.White));
        whiteChess.put("whiteHorse0",new Horse(0,1,TakenBy.White));
        whiteChess.put("whiteHorse1",new Horse(0,6,TakenBy.White));
        whiteChess.put("whiteBishop0",new Bishop(0,2,TakenBy.White));
        whiteChess.put("whiteBishop1",new Bishop(0,5,TakenBy.White));
        whiteChess.put("whiteQueen",new Queen(0,3,TakenBy.White));
        whiteChess.put("whiteKing",new King(0,4,TakenBy.White));
        blackChess.put("blackTower0",new Tower(7,0,TakenBy.Black));
        blackChess.put("blackTower1",new Tower(7,7,TakenBy.Black));
        blackChess.put("blackHorse0",new Horse(7,1,TakenBy.Black));
        blackChess.put("blackHorse1",new Horse(7,6,TakenBy.Black));
        blackChess.put("blackBishop0",new Bishop(7,2,TakenBy.Black));
        blackChess.put("blackBishop1",new Bishop(7,5,TakenBy.Black));
        blackChess.put("blackQueen",new Queen(7,3,TakenBy.Black));
        blackChess.put("blackKing",new King(7,4,TakenBy.Black));
        whiteChess.get("whiteHorse0").check();

    }
}
