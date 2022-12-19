package GUI;

import GAME.GameBoard;
import GAME.MoveDetails;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class GameWindow extends BaseFrame{


    JPanel midPanel,leftPanel,lowerPanel;
    JButton[][] buttons;

    MoveDetails lastMove;

    GameBoard gameBoard;

    Semaphore semaphore;


    public void setCurrentSymbol(Character currentSymbol) {
        this.currentSymbol = currentSymbol;

        System.out.println("yo "+this.currentSymbol);
    }

    public void showInvalidMoveError()
    {
        JOptionPane.showMessageDialog(this,"Error this move is Invalid");
    }

    public void showWinner(String text)
    {
        JOptionPane.showMessageDialog(this,text);
    }
    public void showDraw()
    {
        JOptionPane.showMessageDialog(this,"Draw");
    }

    Character currentSymbol;

    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }
    Map<Character,String> iconMap;

    JLabel label;

    JTextArea l;

    public GameWindow(GameBoard gameBoard)
    {
        iconMap=new HashMap<>();
        iconMap.put('X',"assets\\x.gif");
        iconMap.put('O',"assets\\o.gif");
        iconMap.put('-',"assets\\button.gif");

         this.gameBoard=gameBoard;


         lowerPanel=new ImagePanel(BaseFrame.yellowBackGroundImage,BaseFrame.windowSize);
         lowerPanel.setLayout(new BorderLayout());

         midPanel = new ImagePanel(BaseFrame.yellowBackGroundImage,BaseFrame.windowSize);
         midPanel.setLayout(new GridLayout(3,3,50,80));

         leftPanel = new ImagePanel(BaseFrame.blackBackGroundImage,new Dimension(midPanel.getWidth()/4,midPanel.getHeight()));
         leftPanel.setLayout(new FlowLayout());

        lowerPanel.add(midPanel,BorderLayout.CENTER);
        lowerPanel.add(leftPanel,BorderLayout.WEST);


        basePanel.add(lowerPanel);
        buttons=new JButton[3][3];


         currentSymbol='X';
         label =new JLabel("Turn of "+currentSymbol);
         l =new JTextArea("Turn of "+currentSymbol);
         label.setOpaque(true);
         l.setEditable(false);
         leftPanel.add(l);





         placeButtons();
         refresh();


    }

    void placeButtons()
    {
        for(int i=0;i<3;i++)
        {

            for(int j=0;j<3;j++)
            {
                buttons[i][j]=new JButton(new ImageIcon(iconMap.get(gameBoard.getBoardState()[i][j])));
                //buttons[i][j]=new JButton();
                buttons[i][j].addActionListener(this);
                midPanel.add(buttons[i][j]);
            }
        }

    }

   public void updateWindow()
    {

        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                JButton currentButton=buttons[i][j];
                Character currentState=gameBoard.getBoardState()[i][j];
                try {
                    Image image= ImageIO.read(new File(iconMap.get(currentState))).getScaledInstance(currentButton.getWidth(),currentButton.getHeight(),Image.SCALE_DEFAULT);
                    currentButton.setIcon(new ImageIcon(image));
                }catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        }
        System.out.println("Turn of "+String.valueOf(this.currentSymbol));
        this.l.setText("Turn of "+String.valueOf(this.currentSymbol));
        refresh();
    }

    void refresh()
    {
        this.setVisible(false);
        this.repaint();
        this.setVisible(true);
    }

   public MoveDetails getLastMove()  {
        return lastMove;
    }

    @Override
   public void actionPerformed(ActionEvent e)
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(e.getSource().equals(buttons[i][j])) {
                    {
                        lastMove = new MoveDetails(i, j, currentSymbol);

                        semaphore.release();
                        break;
                    }

                }
            }
        }
    }
}
