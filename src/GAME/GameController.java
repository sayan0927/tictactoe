package GAME;

import GUI.GameWindow;
import GUI.WelcomePage;
import com.sun.tools.javac.Main;


import javax.swing.*;
import java.util.concurrent.Semaphore;

import static javax.swing.SwingUtilities.updateComponentTreeUI;

public class GameController  {

    boolean against_human;
    int game_size;


    Player firstPlayer;
    Player secondPlayer;

    private final GameBoard gameBoard;
    MoveDetails latest_move;
    int moves_made;
    boolean game_ended;

    GameWindow gameWindow;




    public GameController(int playing_against) {

        this.game_size = 3;
        this.against_human = (playing_against > 0);

        gameBoard = new GameBoard(this.game_size);




        gameWindow=new GameWindow(gameBoard);


        firstPlayer=new HumanPlayer('X', true, game_size,gameWindow);

        if(playing_against==1)
            secondPlayer=new ComputerPlayerEasyAI('O', false, game_size);
        else if(playing_against==2)
            secondPlayer=new ComputerPlayerHardAI('O','X',false,game_size,this.gameBoard);
        else if(playing_against==3)
            secondPlayer=new HumanPlayer('O', true, game_size,gameWindow);

        moves_made = 0;
        game_ended = false;



        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

            updateComponentTreeUI(gameWindow);

            updateComponentTreeUI(gameWindow);

        } catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException |
                 ClassNotFoundException | NullPointerException ignored) {

        }


        init_Game();



    }


    void init_Game()  {

        gameWindow.setCurrentSymbol(firstPlayer.getSymbol());
        Semaphore lock=new Semaphore(0);
        while (true)
        {


            //human making move
            while (true)
            {

                gameWindow.setSemaphore(lock);

                getLock(lock);


                latest_move = firstPlayer.newMove(gameBoard);
                if(gameBoard.isValidInput(latest_move)) {
                    gameBoard.set_Player_Mark(firstPlayer.getSymbol(), latest_move);
                    break;
                }
                else
                    gameWindow.showInvalidMoveError();

            }

            moves_made++;
            gameWindow.setCurrentSymbol(secondPlayer.getSymbol());
            gameWindow.updateWindow();





            if(winCheck(moves_made) || gameEndCheck(moves_made))
                break;




            //2nd player make move
            while (true)
            {
               // Semaphore lock=new Semaphore(0);
                gameWindow.setSemaphore(lock);



                //only if 2nd player is human take the lock
                if(secondPlayer instanceof HumanPlayer)
                    getLock(lock);

                latest_move = secondPlayer.newMove(gameBoard);
                if(gameBoard.isValidInput(latest_move)) {
                    gameBoard.set_Player_Mark(secondPlayer.getSymbol(), latest_move);
                    break;
                }
                else
                    gameWindow.showInvalidMoveError();


            }





            moves_made++;
            gameWindow.setCurrentSymbol(firstPlayer.getSymbol());
            gameWindow.updateWindow();

            if(winCheck(moves_made) || gameEndCheck(moves_made))
                break;



        }

        restart();

    }

    private void getLock(Semaphore lock)
    {
        try {
            lock.acquire();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    boolean winCheck(int moves_made)
    {
        if (gameBoard.check_if_game_ended(moves_made))
        {
            gameWindow.showWinner("Winner is  " + gameBoard.getWinner());
            return true;
        }
        return false;
    }

    boolean gameEndCheck(int moves_made)
    {
        if(maximum_moves_reached(moves_made))
        {
            gameWindow.showDraw();
            return true;
        }
        return false;
    }

    void restart()
    {
        gameWindow.dispose();
        new WelcomePage();
    }
    boolean maximum_moves_reached(int moves_made) {
        return moves_made >= game_size * game_size;
    }



}
