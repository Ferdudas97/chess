package sample.models;

public class Pawn extends AbstractFigure {


    public Pawn(Integer spawnRow, Integer spawnCol,TakenBy color) {
        this.row=spawnRow;
        this.col=spawnCol;
        this.color=color;
        if (color.equals(TakenBy.White))
        {
            friends=TakenBy.White;
            enemies=TakenBy.Black;
        }
        else {
            friends=TakenBy.Black;
            enemies=TakenBy.White;
        }

    }

    @Override
    public void check() {
        available.clear();

        if (color==TakenBy.White){
            if (row==1) {
                if (board[2][col].equals(TakenBy.Empty)) {
                    this.available.add("2"+col);
                    if (board[3][col].equals(TakenBy.Empty))this.available.add("3"+col);

                }
            }
            else if (row+1<board.length &&board[row+1][col].equals(TakenBy.Empty)) this.available.add(String.valueOf(row+1)+col);
            if ( row+1<board.length && col+1<board.length && board[row+1][col+1].equals(TakenBy.Black)) this.available.add(String.valueOf(row+1)+String.valueOf(col+1));
         if ( row+1<board.length && col-1>=0 && board[row+1][col-1].equals(TakenBy.Black)) this.available.add(String.valueOf(row+1)+String.valueOf(col-1));

        }
        else {
            if (row==6) {
                if (board[5][col].equals(TakenBy.Empty)) {
                    this.available.add("5"+col);
                    if (board[4][col].equals(TakenBy.Empty))this.available.add("4"+col);

                }
            }
            else if ( row-1>=0 && board[row-1][col].equals(TakenBy.Empty)) this.available.add(String.valueOf(row-1)+col);
            if ( row-1>=0 && col+1<board.length && board[row-1][col+1].equals(TakenBy.White)) this.available.add(String.valueOf(row-1)+String.valueOf(col+1));
            if ( row-1>=0 && col-1>=0 && board[row-1][col-1].equals(TakenBy.White)) this.available.add(String.valueOf(row-1)+String.valueOf(col-1));

        }
    }

}
