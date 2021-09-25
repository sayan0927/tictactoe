package GAME;

public class GameController {

    boolean against_human;
    int game_size;
    Display display;
    HumanGetMove humanGetMove;
    ComputerGetMove computerGetMove;
    private final GameBoard gameBoard;
    MoveDetails latest_move;
    int moves_made;
    boolean game_ended;


    GameController(int game_size, int playing_against) {
        this.game_size = game_size;
        this.against_human = (playing_against > 0);
        display = new Display();
        gameBoard = new GameBoard(game_size);
        humanGetMove = new HumanGetMove('X', true, game_size);
        computerGetMove = new ComputerGetMove('O', false, game_size);

        moves_made = 0;
        game_ended = false;
    }


    void init_Game() {


        Object classname = humanGetMove.getClass();

        System.out.println(classname);


        while (true)      //maximum moves possible in NxN grid
        {
            display.consoleDisplay(gameBoard.getBoardState());

            latest_move = getInput(humanGetMove);                                  //human making move
            gameBoard.set_Player_Mark(humanGetMove.getSymbol(), latest_move);
            moves_made++;

            if (maximum_moves_reached(moves_made)) {
                System.out.println("Winner is  " + gameBoard.getWinner());
                System.out.println("Game has Ended");
                break;
            }

            if (gameBoard.check_if_game_ended(moves_made)) {
                display.consoleDisplay(gameBoard.getBoardState());
                System.out.println("Winner is  " + gameBoard.getWinner());
                System.out.println("Game has Ended");
                break;
            }


            latest_move = getInput(computerGetMove);
            gameBoard.set_Player_Mark(computerGetMove.getSymbol(), latest_move);        //computer making move
            moves_made++;

            if (gameBoard.check_if_game_ended(moves_made)) {
                display.consoleDisplay(gameBoard.getBoardState());
                System.out.println("Winner is  " + gameBoard.getWinner());
                System.out.println("Game has Ended");
                break;
            }

            if (maximum_moves_reached(moves_made)) {
                System.out.println("Winner is  " + gameBoard.getWinner());
                System.out.println("Game has Ended");
                break;
            }


        }


    }

    boolean maximum_moves_reached(int moves_made) {
        return moves_made >= game_size * game_size;
    }

    MoveDetails getInput(Player player) {

        MoveDetails latest_move;
        while (true) {
            latest_move = player.getMove();
            if (gameBoard.isValidInput(latest_move))
                return latest_move;

            if (player instanceof HumanGetMove) {
                System.out.println("Wrong Input!!");
                display.consoleDisplay(this.gameBoard.getBoardState());
            }
        }


    }


}
