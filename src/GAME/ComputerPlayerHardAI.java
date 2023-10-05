package GAME;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.Semaphore;

public class ComputerPlayerHardAI extends Player {

    protected final Character computerPlayer ;
    protected final Character humanPlayer ;
    protected final GameBoard mainGameBoard;
    protected GameBoard gameBoardForMoves; // this board is used to make moves by minimax, so that original board doesnt change


    protected final int gamesize;


    ComputerPlayerHardAI(Character computerPlayer, Character humanPlayer, boolean b, int gamesize, GameBoard gameBoard) {
        super(computerPlayer, b);
        this.mainGameBoard = gameBoard;
        this.gamesize=gamesize;
        this.computerPlayer=computerPlayer;
        this.humanPlayer=humanPlayer;


    }

    @Override
    public MoveDetails newMove(GameBoard gameBoard) {
       this.gameBoardForMoves=new GameBoard(mainGameBoard);
       MoveDetails latestMove= findBestMove(gameBoardForMoves);
       return latestMove;
    }


   protected int evaluate(GameBoard boardForMoves)  {



        Status status=boardForMoves.rowCheck();
        if(status.hasSomeoneWon())
        {
            if(status.getWinner().equals(computerPlayer))
                return 10;
            else
                return -10;
        }

        status=boardForMoves.columnCheck();
        if(status.hasSomeoneWon())
        {
            if(status.getWinner().equals(computerPlayer))
                return 10;
            else
                return -10;
        }

        status=boardForMoves.rightDiagonalCheck();
        if(status.hasSomeoneWon())
        {
            if(status.getWinner().equals(computerPlayer))
                return 10;
            else
                return -10;
        }

        status=boardForMoves.leftDiagonalCheck();
        if(status.hasSomeoneWon())
        {
            if(status.getWinner().equals(computerPlayer))
                return 10;
            else
                return -10;
        }

        //no one won
        return 0;

    }








    protected int minimax(GameBoard  boardForMoves, int depth, boolean isMax)  {
        System.out.println("at depth "+depth);
        int score = evaluate(boardForMoves);

        if (score == 10)  // If Maximizer has won the game return his/her evaluated score
            return score;

        if (score == -10) // If Minimizer has won the game return his/her evaluated score
            return score;

        if (!boardForMoves.movesLeft())   // If there are no more moves and no winner then it is a tie
            return 0;

        if (isMax) // If this maximizer's move
        {
            int best = -1000;

            for (int i = 0; i < gamesize; i++)
            {
                for (int j = 0; j < gamesize; j++)
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

   protected MoveDetails findBestMove(GameBoard boardForMoves)  {
        int bestVal = -1000;


        TreeMap<Integer, List<MoveDetails>> moveScores = new TreeMap<>();

        for (int i = 0; i < gamesize; i++)
        {
            for (int j = 0; j < gamesize; j++)
            {

                if (boardForMoves.getCellValue(i,j) ==boardForMoves.empty_charecter)
                {
                    boardForMoves.setCellValue(i,j,computerPlayer);  // Make the move

                    int moveVal = minimax(boardForMoves, 0, false);   // compute evaluation function for this move



                    if(moveScores.containsKey(moveVal))
                        moveScores.get(moveVal).add(new MoveDetails(i,j,computerPlayer));
                    else
                    {
                        List<MoveDetails> set = new ArrayList<>();
                        set.add(new MoveDetails(i,j,computerPlayer));
                        moveScores.put(moveVal,set);
                    }

                    boardForMoves.setCellValue(i,j,boardForMoves.empty_charecter);  // Undo the move

                }
            }
        }

        //returning a random move from the moves with top scores

        List<MoveDetails> bestMoves = moveScores.lastEntry().getValue();
       System.out.println("Moves "+bestMoves +"  "+moveScores);
        int randIdx = new Random().nextInt(bestMoves.size());
        return bestMoves.get(randIdx);

    }


}
