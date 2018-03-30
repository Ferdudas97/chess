package sample.models;

public class King extends AbstractFigure  {

    public King(Integer spawnRow, Integer spawnCol,TakenBy color) {
        this.color=color;
        this.row=spawnRow;
        this.col=spawnCol;
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


        if (row+1<board.length  && col+1<board.length && !board[row+1][col+1].equals(friends)) this.available.add(cordAsString(row+1,col+1));
        if (col+1<board.length && !board[row][col+1].equals(friends)) this.available.add(cordAsString(row,col+1));
        if (row-1>=0 && col+1<board.length && !board[row-1][col+1].equals(friends)) this.available.add(cordAsString(row-1,col+1));
        if (row-1>=0 && !board[row-1][col].equals(friends)) this.available.add(cordAsString(row-1,col));
        if (row-1>=0 && col-1>=0 && !board[row-1][col-1].equals(friends)) this.available.add(cordAsString(row-1,col-1));
        if (col-1>=0 && !board[row][col-1].equals(friends)) this.available.add(cordAsString(row,col-1));
        if (row+1<board.length && col-1>=0 && !board[row+1][col-1].equals(friends)) this.available.add(cordAsString(row+1, col-1));
        if (row+1<board.length && !board[row+1][col].equals(friends)) this.available.add(cordAsString(row+1, col));


    }

}
