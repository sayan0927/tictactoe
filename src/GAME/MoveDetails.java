package GAME;

public class MoveDetails {

    int row;
    int col;
    Character symbol;

    MoveDetails() {
    }

    MoveDetails(int i, int j, Character symbol) {
        row = i;
        col = j;
        this.symbol = symbol;
    }

    void setRow(int x) {
        this.row = x;
    }

    void setCol(int x) {
        this.col = x;
    }

    void setSymbol(Character c) {
        this.symbol = c;
    }

    int getRow() {
        return row;
    }

    int getCol() {
        return col;
    }

    Character getSymbol() {
        return symbol;
    }


}
