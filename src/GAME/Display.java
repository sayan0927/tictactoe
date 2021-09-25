package GAME;

public class Display {

    public void consoleDisplay(Character[][] board_state) {


        for (Character[] characters : board_state) {            //traverse rows
            for (Character character : characters) {      //traverse columns
                System.out.print("   " + character + "   ");

            }
            System.out.print("\n");
        }

        System.gc();


    }
}
