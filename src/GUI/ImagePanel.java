package GUI;


import javax.swing.*;
import java.awt.*;

class ImagePanel extends JPanel {

    private Image img;


    public ImagePanel(String img,Dimension dimension) {

        this(new ImageIcon(img).getImage(),dimension);
    }



    public ImagePanel(Image img,Dimension size) {
        this.img = img;

        if(size!=null)
        {
            setPreferredSize(size);
            setMinimumSize(size);
           // setMaximumSize(size);
            setSize(size);
        }

    }



    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

}
