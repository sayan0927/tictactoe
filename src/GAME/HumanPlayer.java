package GAME;

import GUI.GameWindow;

import javax.swing.*;
import java.util.concurrent.Semaphore;

public class HumanPlayer extends Player {




    MoveDetails last;


    private final MoveDetails move;
    private int game_size;
    GameWindow gameWindow;


    HumanPlayer(Character symbol, boolean isHuman, int game_size, GameWindow gameWindow) {
        super(symbol, isHuman);
        move = new MoveDetails();
        this.game_size = game_size;
        this.gameWindow=gameWindow;
    }

    @Override
    public MoveDetails newMove(GameBoard gameBoard) {
        return gameWindow.getLastMove();
    }
   

}
