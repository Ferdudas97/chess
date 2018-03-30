package sample.models;

public class Tower extends AbstractFigure {


    public Tower(Integer spawnRow, Integer spawnCol, TakenBy color) {
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

        for (int i = row+1; i<board.length ; i++) {

            if (board[i][col].equals(TakenBy.Empty)) this.available.add(cordAsString(i,col));
            if (board[i][col].equals(enemies)){

                this.available.add(cordAsString(i,col));
                break;
            }
            if (board[i][col].equals(friends))break;


        }
        for (int i = row-1; i>=0 ; i--) {

            if (board[i][col].equals(TakenBy.Empty)) this.available.add(cordAsString(i,col));
            if (board[i][col].equals(enemies)){

                this.available.add(cordAsString(i,col));
                break;
            }
            if (board[i][col].equals(friends))break;


        }
        for (int i = col+1; i<board.length ; i++) {

            if (board[row][i].equals(TakenBy.Empty)) this.available.add(cordAsString(row,i));
            if (board[row][i].equals(enemies)){

                this.available.add(cordAsString(row,i));
                break;
            }
            if (board[row][i].equals(friends))break;


        }
        for (int i = col-1; i>=0 ; i--) {

            if (board[row][i].equals(TakenBy.Empty)) this.available.add(cordAsString(row,i));
            if (board[row][i].equals(enemies)){

                this.available.add(cordAsString(row,i));
                break;
            }
            if (board[row][i].equals(friends))break;


        }


    }
}
