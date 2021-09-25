package GAME;

public class ComputerGetMove extends Player implements GetMove {

    private final int game_size;
    private final MoveDetails move;

    ComputerGetMove(Character symbol, boolean isHuman, int gamesize) {
        super(symbol, isHuman);
        this.game_size = gamesize;
        move = new MoveDetails();
    }

    @Override
    public MoveDetails getMove() {

        int index_row = (int) (game_size * Math.random());     //get get coordinates of cell where computer will enter their symbol
        int index_column = (int) (game_size * Math.random());
        move.setRow(index_row);
        move.setCol(index_column);
        move.setSymbol(super.getSymbol());

        return move;

    }
}
