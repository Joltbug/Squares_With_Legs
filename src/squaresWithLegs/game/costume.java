package squaresWithLegs.game;

import java.awt.Image;
import javax.swing.ImageIcon;


public class costume{ //costume class for painting to canvas
    Image image;
    String Location;
    public costume(String loc){
        Location = loc;
        ImageIcon ii = new ImageIcon(Location);
        image = ii.getImage();
    }
    public Image getImage(){
        return image;
    }
    public String toString(){
        return Location;
    }
    public String getDimensions(){
        return image.getHeight(null) + " " + image.getWidth(null);
    }

}