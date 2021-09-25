package GAME;

public abstract class Player {

    private Character symbol;
    private boolean isHuman;

    Player(Character character, boolean b) {
        this.symbol = character;
        this.isHuman = b;
    }

    Character getSymbol() {
        return symbol;
    }

    boolean isHuman() {
        return isHuman;
    }

    abstract MoveDetails getMove();

}
