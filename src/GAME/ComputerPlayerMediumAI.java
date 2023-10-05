package GAME;

import javax.swing.*;
import java.util.concurrent.Semaphore;

public class ComputerPlayerMediumAI extends ComputerPlayerHardAI {


    private int depthLimit=5;


    ComputerPlayerMediumAI(Character computerPlayer, Character humanPlayer, boolean b, int gamesize, GameBoard gameBoard) {
        super(computerPlayer, humanPlayer,b,gamesize,gameBoard);
    }



    @Override
    public MoveDetails newMove(GameBoard gameBoard) {
        this.gameBoardForMoves=new GameBoard(mainGameBoard);
        MoveDetails latestMove= super.findBestMove(gameBoardForMoves);
        depthLimit--;
        return latestMove;
    }



    @Override
    protected int minimax(GameBoard  boardForMoves, int depth, boolean isMax)  {
        System.out.println("depth "+depth);

        //depth limiting for medium AI
        if(depth==depthLimit)
            return 10;

        int score = super.evaluate(boardForMoves);

        if (score == 10)  // If Maximizer has won the game return his/her evaluated score
            return score;

        if (score == -10) // If Minimizer has won the game return his/her evaluated score
            return score;

        if (!boardForMoves.movesLeft())   // If there are no more moves and no winner then it is a tie
            return 0;

        if (isMax) // If this maximizer's move
        {
            int best = -1000;

            for (int i = 0; i < super.gamesize; i++)
            {
                for (int j = 0; j < super.gamesize; j++)
                {

                    if (boardForMoves.getCellValue(i,j).equals(boardForMoves.empty_charecter) )
                    {
                        boardForMoves.setCellValue(i,j,computerPlayer);  // Make the move

                        best = Math.max(best, minimax(boardForMoves, depth + 1, false));  // Call minimax recursively and choose  the maximum value

                        boardForMoves.setCellValue(i,j,boardForMoves.empty_charecter); // Undo the move
                    }
                }
            }
            return best;
        }

        else { // If this minimizer's move
            int best = 1000;

            for (int i = 0; i < gamesize; i++)
            {
                for (int j = 0; j < gamesize; j++)
                {

                    if (boardForMoves.getCellValue(i,j).equals(boardForMoves.empty_charecter) )
                    {
                        boardForMoves.setCellValue(i,j,humanPlayer);   // Make the move

                        best = Math.min(best, minimax(boardForMoves, depth + 1, true));    // Call minimax recursively and choose the minimum value

                        boardForMoves.setCellValue(i,j,boardForMoves.empty_charecter);  // Undo the move
                    }
                }
            }

            return best;
        }
    }




}
