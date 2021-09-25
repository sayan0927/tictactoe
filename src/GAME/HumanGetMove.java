package GAME;

import java.util.Scanner;

public class HumanGetMove extends Player implements GetMove {


    private final MoveDetails move;
    private int game_size;


    HumanGetMove(Character symbol, boolean isHuman, int game_size) {
        super(symbol, isHuman);
        move = new MoveDetails();
        this.game_size = game_size;
    }

    @Override
    public MoveDetails getMove() {
        System.out.print("Playing as :" + super.getSymbol() + "\r\n");
        System.out.print("Please Enter co-ordinates of cell where you want to play " + "\r\n");
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter row and column: " + "\r\n");

        move.setRow(scan.nextInt() - 1);    //get coordinates of cell where player will enter their symbol
        move.setCol(scan.nextInt() - 1);

        move.setSymbol(super.getSymbol());
        return move;
    }
}
