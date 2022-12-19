package GUI;

import javax.swing.*;
import java.awt.*;

public class ImageButton extends JButton {

    Image imageIcon=null;

    ImageButton(String imagePath)
    {
        super();
        this.imageIcon= new ImageIcon(imagePath).getImage();
    }

    ImageButton()
    {
        super();
    }

    public ImageButton(Image icon) {
        this.imageIcon=icon;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        if(imageIcon!=null)
            g.drawImage(imageIcon,this.getX(),this.getY(),null);
    }
}
