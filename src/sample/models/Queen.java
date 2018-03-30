package sample.models;

public class Queen extends AbstractFigure {

    public Queen(Integer spawnRow, Integer spawnCol, TakenBy color) {
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
        int a=0,b=0,c=0,d=0;
        int incrementColumn=col+1,incrementRow=row+1;
        int decrementColumn=col-1,decrementRow=row-1;
        while (!(a==1 && b==1 && c==1 && d==1)) {
            if (a == 0 && incrementRow<board.length && incrementColumn<board.length && !board[incrementRow][incrementColumn].equals(friends)) {
                this.available.add(cordAsString(incrementRow, incrementColumn));
                if (board[incrementRow][incrementColumn].equals(enemies)) a=1;

            } else a = 1;
            if (b == 0 &&  incrementRow<board.length && decrementColumn>=0 && !board[incrementRow][decrementColumn].equals(friends)) {
                this.available.add(cordAsString(incrementRow, decrementColumn));
                if (board[incrementRow][decrementColumn].equals(enemies)) b=1;


            } else b = 1;
            if (c == 0 && decrementRow>=0 && decrementColumn>=0 && !board[decrementRow][decrementColumn].equals(friends)) {
                this.available.add(cordAsString(decrementRow, decrementColumn));
                if (board[decrementRow][decrementColumn].equals(enemies)) c=1;



            } else c = 1;
            if (d == 0 && decrementRow>=0 && incrementColumn<board.length && !board[decrementRow][incrementColumn].equals(friends)) {
                this.available.add(cordAsString(decrementRow, incrementColumn));
                if (board[decrementRow][incrementColumn].equals(enemies)) d=1;



            } else d = 1;
            incrementColumn++;
            decrementColumn--;
            incrementRow++;
            decrementRow--;
        }

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
