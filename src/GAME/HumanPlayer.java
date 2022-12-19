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

      /* while (true) {
           Semaphore semaphore = new Semaphore(0);
           gameWindow.setCurrentSymbol(this.getSymbol());
           System.out.println(this.getSymbol());
           gameWindow.setSemaphore(semaphore);
           try {
               semaphore.acquire();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           MoveDetails last = gameWindow.getLastMove();
           if(gameBoard.isValidInput(last))
               return last;
           else
               gameWindow.showInvalidMoveError();
       }

       */

    }
    public void setLock(Semaphore lock) {

    }

}
