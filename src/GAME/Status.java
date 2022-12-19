package GAME;

public class Status {


    private Character winner;
    private boolean someoneWon;

    public Status(Character winner, boolean someoneWon) {
        this.winner = winner;
        this.someoneWon = someoneWon;
    }

    public Character getWinner() {
        return winner;
    }

    public boolean hasSomeoneWon() {
        return someoneWon;
    }



}
