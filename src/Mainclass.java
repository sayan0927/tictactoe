import GAME.GameController;
import GUI.WelcomePage;

import javax.swing.*;

import java.util.concurrent.Semaphore;

import static javax.swing.SwingUtilities.updateComponentTreeUI;

public class Mainclass {
    public static void main(String[] args) throws ClassNotFoundException {



        WelcomePage welcome = new WelcomePage();


        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

            updateComponentTreeUI(welcome);

            updateComponentTreeUI(welcome);

        } catch (Exception exception )
        {
            exception.printStackTrace();
        }


    }
}
