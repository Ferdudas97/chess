package sample.models;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class Bishop extends AbstractFigure {

    public Bishop(Integer spawnRow, Integer spawnCol, TakenBy color) {
        this.move(spawnRow,spawnCol);
        this.color=color;

    }



    @Override
    public void check() {
        TakenBy friends,enemies;
        if (color.equals(TakenBy.White))
        {
            friends=TakenBy.White;
            enemies=TakenBy.Black;
        }
        else {
            friends=TakenBy.Black;
            enemies=TakenBy.White;
        }
        int a=0,b=0,c=0,d=0;
        for (int i = row+1, j=col+1;j<board.length || i <board.length ;j++, i++) {
         if (a==0 && !board[i][j].equals(friends))  {
             this.available.add(cordAsString(i,j));;

         }
         else a=1;
            if (b==0 && !board[i][board.length-j].equals(friends))  {
                this.available.add(cordAsString(i,board.length-j));;

            }
            else b=1;
            if (c==0 && !board[board.length-i][board.length-j].equals(friends))  {
                this.available.add(cordAsString(board.length-i,board.length-j));;

            }
            else c=1;
            if (d==0 && !board[board.length-i][j].equals(friends))  {
                this.available.add(cordAsString(board.length-i,j));;

            }
            else d=1;
            if (a==1 && b==1 && c==1 && d==1)break;
        }
    }
}
