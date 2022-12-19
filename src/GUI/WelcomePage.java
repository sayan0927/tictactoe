package GUI;

import GAME.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.Semaphore;

public class WelcomePage extends BaseFrame{

    JButton playAgainstHuman,playAgainstHardAI, playAgainstEasyAi;
    int playingAgainst=-1;

    Semaphore semaphore;

    JPanel rightPanel,leftPanel,lowerPanel,innerLeftPanel;

    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }



    public WelcomePage()
    {

        this.setTitle("Welcome");

        playAgainstHuman=new JButton("Play against another Human");
        playAgainstHuman.setBounds(200,50,200,100);


        playAgainstHardAI=new JButton("Play against AI (Hard)");
        playAgainstHardAI.setBounds(200,300,200,100);


        playAgainstEasyAi =new JButton("Play against AI (Easy)");
        playAgainstEasyAi.setBounds(200,400,200,100);

        playAgainstHuman.addActionListener(this);
        playAgainstHardAI.addActionListener(this);
        playAgainstEasyAi.addActionListener(this);

        lowerPanel=new ImagePanel(BaseFrame.yellowBackGroundImage,BaseFrame.windowSize);
        lowerPanel.setLayout(new BorderLayout());

       rightPanel = new ImagePanel(BaseFrame.yellowBackGroundImage,BaseFrame.windowSize);
       rightPanel.setLayout(new GridLayout(3,3,50,50));

        leftPanel = new ImagePanel(BaseFrame.blackBackGroundImage,new Dimension(rightPanel.getWidth()/3,rightPanel.getHeight()));
        leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.Y_AXIS));

        //innerLeftPanel = new ImagePanel(BaseFrame.yellowBackGroundImage,new Dimension(leftPanel.getWidth(),rightPanel.getHeight()/3));
       // leftPanel.add(innerLeftPanel,BorderLayout.SOUTH);

        lowerPanel.add(rightPanel,BorderLayout.CENTER);
        lowerPanel.add(leftPanel,BorderLayout.WEST);

        basePanel.add(lowerPanel);



       leftPanel.add(playAgainstHuman,BorderLayout.NORTH);
       leftPanel.add(playAgainstEasyAi,BorderLayout.CENTER);
       leftPanel.add(playAgainstHardAI,BorderLayout.SOUTH);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        if(e.getSource().equals(playAgainstHuman))
            playingAgainst=3;
        else if (e.getSource().equals(playAgainstHardAI))
            playingAgainst=2;
        else if(e.getSource().equals(playAgainstEasyAi))
            playingAgainst=1;

       // semaphore.release();
        this.setVisible(false);

        Thread gameThread = new Thread(new Runnable() { public void run()
        {

            // current thread is AWT Thread
            //running the controller in a main thread
            GameController gameController=new GameController(playingAgainst);

        }});

        gameThread.start();


    }

   public int getPlayingAgainst()
    {
        return playingAgainst;
    }




}
