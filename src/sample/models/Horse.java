package sample.models;

public class Horse extends AbstractFigure{



    public Horse(Integer spawnRow, Integer spawnCol,TakenBy color) {
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


        if (row+1<board.length && col+2<board.length && !board[row+1][col+2].equals(friends)) this.available.add(cordAsString(row+1,col+2));
        if (row-1>=0 && col-2>=0 && !board[row-1][col-2].equals(friends))this.available.add(cordAsString(row-1,col-2));
        if (row-1>=0 && col+2<board.length && !board[row-1][col+2].equals(friends)) this.available.add(cordAsString(row-1,col+2));
        if (row+1<board.length && col-2>=0 && !board[row+1][col-2].equals(friends)) this.available.add(cordAsString(row+1,col-2));
        if (row-2>=0 && col+1<board.length && !board[row-2][col+1].equals(friends)) this.available.add(cordAsString(row-2,col+1));
        if ( row-2 >=0 && col-1>=0  && !board[row-2][col-1].equals(friends))this.available.add(cordAsString(row-2,col-1));
        if ( row+2<board.length && col-1>=0 && !board[row+2][col-1].equals(friends)) this.available.add(cordAsString(row+2,col-1));
        if ( row+2<board.length && col+1<board.length && !board[row+2][col+1].equals(friends)) this.available.add(cordAsString(row+2,col+1));

    }
}
