package GAME;

import java.util.HashSet;
import java.util.Iterator;

public class GameBoard {

    protected Character[][] board;
    private int gamesize;
    private final Character empty_charecter = '-';
    private String winner = null;
    private final Character you_playing_as;
    HashSet<Character> winner_check;


    GameBoard(int gamesize) {
        board = new Character[gamesize][gamesize];
        you_playing_as = 'X';
        this.gamesize = gamesize;
        setBoardReady();


    }


    boolean check_if_game_ended(int moves_made) {
        if (moves_made < (gamesize * 2 - 1))
            return false;

        if (leftDiagonalCheck() || rightDiagonalCheck() || rowCheck() || columnCheck()) //check wether any row,column,diagonal contains all same symbols

        {
            System.out.print("SomeOne Won!! Lets see who" + "\r\n");
            setWinner(winner_check);
            return true;
        }

        if (moves_made >= gamesize * gamesize) {
            setWinner(null);
            System.out.println("tie");
        }
        return false;

    }

    void setWinner(HashSet<Character> set) {

        if (set == null) {
            this.winner = "None";
        } else {
            Iterator<Character> iterator = set.iterator();
            winner = iterator.next().toString();
        }
    }


    String getWinner() {

        return winner;
    }

    void setBoardReady() {
        for (int row = 0; row < gamesize; row++) {
            for (int col = 0; col < gamesize; col++) {
                this.board[row][col] = empty_charecter;
            }
        }

    }

    void change(int i, int j, char c) {
        board[i][j] = c;
    }

    boolean CellEmpty(int row, int column) {
        return board[row][column] == empty_charecter;
    }


    void set_Player_Mark(Character c, MoveDetails moveDetails) {
        int row = moveDetails.getRow();
        int column = moveDetails.getCol();
        board[row][column] = c;
    }

    boolean isValidInput(MoveDetails move) {
        int row = move.getRow();
        int column = move.getCol();
        return (row < 3 && row >= 0 && column < 3 && column >= 0) && CellEmpty(row, column);
    }


    Character[][] getBoardState() {

        return make_copy(board);


    }

    Character human_playing_as() {
        return you_playing_as;
    }


    Character[][] make_copy(Character[][] board) {
        Character[][] copy = new Character[gamesize][gamesize];

        for (int row = 0; row < gamesize; row++) {
            System.arraycopy(board[row], 0, copy[row], 0, gamesize);
        }

        return copy;


    }


    private boolean rowCheck() {
        int row, column;
        boolean sameSymbol = false;
        this.winner_check = new HashSet<>();     //this array will hold elements in a row

        for (row = 0; row < 3; row++) {
            for (column = 0; column < 3; column++) {
                this.winner_check.add(this.board[row][column]);  //fill up the container with elements from a column
            }
            if (allValuesSame(this.winner_check))              //if all values in column are identical
                return true;                      //thus stop searching
        }
        return false;
    }


    private boolean columnCheck() {
        int row, column;

        this.winner_check = new HashSet<>();

        for (column = 0; column < gamesize; column++) {
            for (row = 0; row < gamesize; row++) {

                this.winner_check.add(this.board[row][column]);   //fill up the container with elements from a column
            }
            if (allValuesSame(this.winner_check))                      //if all values in column are identical
                return true;                        //thus stop searching
        }
        return false;
    }

    private boolean rightDiagonalCheck() {

        int row, column;

        this.winner_check = new HashSet<>();      //this array will hold elements in diagonal from top left to bottom right

        for (row = 0; row < gamesize; row++) {

            //check if all elements in right diagonal is identical and not blank


            this.winner_check.add(this.board[row][row]);
        }

        return allValuesSame(this.winner_check);

    }

    private boolean leftDiagonalCheck() {

        int row, column;

        this.winner_check = new HashSet<>();


        for (row = gamesize - 1, column = 0; row > 0 || column < gamesize; row--, column++) {

            //check if all elements in left diagonal is identical and not blank

            this.winner_check.add(this.board[row][column]);


        }

        return allValuesSame(this.winner_check);
    }


    public boolean allValuesSame(HashSet<Character> set) {

        return set.size() == 1 && !set.contains('-');

    }


}




