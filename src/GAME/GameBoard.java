package GAME;

public class GameBoard {

    protected Character[][] board;
    public final int gamesize;
    public final Character empty_charecter = '-';
    private String winner = "None";
    private final Character you_playing_as;


    GameBoard(GameBoard g)
    {
        this.board=g.getBoardState();
        this.gamesize=g.gamesize;
        this.you_playing_as=g.you_playing_as;
    }

    GameBoard(int gamesize) {
        board = new Character[gamesize][gamesize];
        you_playing_as = 'X';
        this.gamesize = gamesize;
        setBoardReady();


    }


   public boolean check_if_game_ended(int moves_made) {


        // minimum moves to win in 3X3 grid is 5
        if (moves_made < (gamesize * 2 - 1))
            return false;


        Status winOrNot;

        winOrNot=leftDiagonalCheck();
        if(winOrNot.hasSomeoneWon()) {
            winner = String.valueOf(winOrNot.getWinner());
            System.out.print("SomeOne Won!! Lets see who" + "\r\n");
            return true;
        }

        winOrNot=rightDiagonalCheck();
        if(winOrNot.hasSomeoneWon()) {
            winner = String.valueOf(winOrNot.getWinner());
            System.out.print("SomeOne Won!! Lets see who" + "\r\n");
            return true;
        }

        winOrNot=rowCheck();
        if(winOrNot.hasSomeoneWon()) {
            winner = String.valueOf(winOrNot.getWinner());
            System.out.print("SomeOne Won!! Lets see who" + "\r\n");
            return true;
        }

        winOrNot=columnCheck();
        if(winOrNot.hasSomeoneWon()) {
            winner = String.valueOf(winOrNot.getWinner());
            System.out.print("SomeOne Won!! Lets see who" + "\r\n");
            return true;
        }



        return false;

    }

   public Character getCellValue(int i, int j)
    {
        return board[i][j];
    }


    boolean movesLeft()
    {
        for(int i=0;i<gamesize;i++)
        {
            for (int j=0;j<gamesize;j++)
            {
                if(board[i][j].equals(empty_charecter))
                    return true;
            }
        }
        return false;
    }

   public String getWinner() {

        return winner;
    }

   public void setBoardReady() {
        for (int row = 0; row < gamesize; row++) {
            for (int col = 0; col < gamesize; col++) {
                this.board[row][col] = empty_charecter;
            }
        }

    }

   public void setCellValue(int i, int j, Character c) {
        board[i][j] = c;
    }

   public boolean CellEmpty(int row, int column) {
        return board[row][column] == empty_charecter;
    }


   public void set_Player_Mark(Character c, MoveDetails moveDetails) {
        int row = moveDetails.getRow();
        int column = moveDetails.getCol();
        board[row][column] = c;
    }

   public boolean isValidInput(MoveDetails move) {
        int row = move.getRow();
        int column = move.getCol();
        return (row < 3 && row >= 0 && column < 3 && column >= 0) && CellEmpty(row, column);
    }


   public Character[][] getBoardState() {

        return make_copy(board);

    }

   public Character human_playing_as() {
        return you_playing_as;
    }


   private Character[][] make_copy(Character[][] board) {
        Character[][] copy = new Character[gamesize][gamesize];

        for (int row = 0; row < gamesize; row++) {
            System.arraycopy(board[row], 0, copy[row], 0, gamesize);
        }

        return copy;


    }


    public Status rowCheck() {

        for ( int row = 0; row < gamesize; row++)
        {
            if(board[row][0]==board[row][1] && board[row][1]==board[row][2] && board[row][2]!='-')
                return new Status(board[row][0],true);
        }
        return new Status(empty_charecter,false);
    }


    public Status columnCheck() {




        for ( int column = 0; column < gamesize; column++)
        {
            if(board[0][column]==board[1][column] && board[1][column]==board[2][column] && board[2][column]!='-')
                return new Status(board[0][column],true);
        }
        return new Status(empty_charecter,false);
    }

    public Status  rightDiagonalCheck() {

       if (board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[0][2]!='-')
            return new Status(board[2][0],true);
        else
            return new Status(empty_charecter,false);
    }

    public Status leftDiagonalCheck() {

       if(board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] !='-')
            return new Status(board[0][0],true);
        else
           return new Status(empty_charecter,false);
    }





}




