package GAME;

public class MoveDetails {

   private int row;
   private int  col;
    Character symbol;

    MoveDetails() {
    }

    public MoveDetails(int i, int j, Character symbol) {
       this.row = i;
       this.col = j;
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

    @Override
    public String toString()
    {
        return (this.row+" "+this.col);
    }

}
