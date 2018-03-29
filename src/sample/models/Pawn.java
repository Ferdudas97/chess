package sample.models;

public class Pawn extends AbstractFigure {


    public Pawn(Integer spawnRow, Integer spawnCol,TakenBy color) {
        this.move(spawnRow,spawnCol);
        this.color=color;

    }

    @Override
    public void check() {
        if (color==TakenBy.White){
            if (row==1) {
                System.out.println(board[2][col]);
                if (board[2][col].equals(TakenBy.Empty)) {
                    System.out.println("tutaj");
                    this.available.add("2"+col);
                    if (board[3][col].equals(TakenBy.Empty))this.available.add("3"+col);

                }
            }
            else if (board[row+1][col].equals(TakenBy.Empty)) this.available.add(String.valueOf(row+1)+col);
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
            else if (board[row-1][col].equals(TakenBy.Empty)) this.available.add(String.valueOf(row-1)+col);
            if ( row-1>=0 && col+1<board.length && board[row-1][col+1].equals(TakenBy.White)) this.available.add(String.valueOf(row-1)+String.valueOf(col+1));
            if ( row-1>=0 && col-1>=0 && board[row-1][col-1].equals(TakenBy.White)) this.available.add(String.valueOf(row-1)+String.valueOf(col-1));

        }
        System.out.println(this.available);
    }

}
