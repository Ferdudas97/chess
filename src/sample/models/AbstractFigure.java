package sample.models;

import javafx.scene.control.Button;

import java.util.Arrays;
import java.util.LinkedList;

public abstract class AbstractFigure{
    static public TakenBy board [][]=new TakenBy[8][8];
    static  {
        //b -- black | w -- white | n -- none
        for (int i = 0; i <8 ; i++) {
            if (i == 0 || i == 1) Arrays.fill(board[i], TakenBy.White);
            else if (i == 6 || i == 7) Arrays.fill(board[i], TakenBy.Black);
            else Arrays.fill(board[i], TakenBy.Empty);
        }
    }
    protected LinkedList<String> available =new LinkedList<>();
    public Integer row;
    public Integer col;
    protected TakenBy color;
    protected TakenBy enemies;
    protected TakenBy friends;
    private   Button figure;
    public void move(Integer rowM, Integer colM){
        board[row][col]=TakenBy.Empty;
        this.col=colM;
        this.row=rowM;

        if (color.equals(TakenBy.White)) board[row][col]=TakenBy.White;
        else board[row][col]=TakenBy.Black;

    }

    public void setFigure(Button figure) {
        this.figure = figure;
    }

    public Button getFigure() {
        return figure;
    }
    private void showAvaliblePools(){


    }
    protected String cordAsString(Integer x,Integer y){
        String string=x.toString()+y.toString();
        return string;
    }

    public abstract void check();

    public LinkedList<String> getAvailable() {
        return available;
    }
}
