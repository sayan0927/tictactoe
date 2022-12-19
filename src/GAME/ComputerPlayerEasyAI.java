package GAME;

import javax.swing.*;
import java.util.concurrent.Semaphore;

public class ComputerPlayerEasyAI extends Player {

    private final int game_size;




    ComputerPlayerEasyAI(Character symbol, boolean isHuman, int gamesize) {
        super(symbol, isHuman);
        this.game_size = gamesize;


    }

    @Override
    public MoveDetails newMove(GameBoard gameBoard) {

        while (true) {
            MoveDetails latest_move = new MoveDetails();
            int index_row = (int) (3 * Math.random());     //get get coordinates of cell where computer will enter their symbol
            int index_column = (int) (3 * Math.random());
            latest_move.setRow(index_row);
            latest_move.setCol(index_column);
            latest_move.setSymbol(super.getSymbol());
            if(gameBoard.isValidInput(latest_move)) {
                return latest_move;
            }
        }



    }
}
