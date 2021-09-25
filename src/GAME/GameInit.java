package GAME;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GameInit {


    int playingAgainst;  //computer or another human
    int gamesize;        // always >2


    public void getGameDetails() {

        System.out.print("Welcome" + "\r\n");
        System.out.print("Tic-Tac-Toe is played on a NxN grid" + "\r\n");
        System.out.print("First Player to get N symbols in any row,column,diagonal wins" + "\r\n");
        Scanner scan = new Scanner(System.in);


        while (true) {


            try {
                System.out.print("Press 0 to play against another computer or 1 to play against human." + "\r\n");
                playingAgainst = scan.nextInt();
                System.out.print("Enter the Size of GameBoard and it should be greater than 2" + "\r\n");
                gamesize = scan.nextInt();

            } catch (InputMismatchException ex) {
                scan.next();

            }

            if (!(playingAgainst == 0 || playingAgainst == 1) || gamesize < 3) {
                System.out.print("Wrong Input." + "\r\n");

            } else
                break;

        }


    }

    public void startGame() {

        GameController gameController = new GameController(gamesize, playingAgainst);
        gameController.init_Game();
    }


}
