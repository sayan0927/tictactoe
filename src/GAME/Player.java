package GAME;

import javax.swing.*;
import java.util.concurrent.Semaphore;

public abstract class Player {

    private final Character symbol;
    private final boolean isHuman;



    Player(Character character, boolean b) {
        this.symbol = character;
        this.isHuman = b;

    }

  public Character getSymbol() {
        return symbol;
    }

    boolean isHuman() {
        return isHuman;
    }

    abstract MoveDetails newMove(GameBoard gameBoard);


}
