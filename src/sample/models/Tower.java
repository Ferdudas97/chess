package sample.models;

public class Tower extends AbstractFigure {


    public Tower(Integer spawnRow, Integer spawnCol, TakenBy color) {
        this.color=color;
        this.move(spawnRow,spawnCol);

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
        for (int i = row+1; i<board.length ; i++) {

                if (board[i][col].equals(TakenBy.Empty)) this.available.add(String.valueOf(i+row));
                if (board[i][col].equals(friends)){

                        this.available.add(String.valueOf(i+row));
                        i=board.length;
                }
                if (board[i][col].equals(enemies))break;


        }
        for (int i = col+1; i<board.length ; i++) {

            if (board[row][i].equals(TakenBy.Empty)) this.available.add(String.valueOf(row+i));
            if (board[row][i].equals(friends)){

                this.available.add(String.valueOf(row+i));
                i=board.length;
            }
            if (board[row][i].equals(enemies))break;


        }


    }
}
