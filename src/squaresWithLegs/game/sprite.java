package squaresWithLegs.game;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class sprite {

    private int x;
    private int y;
    private ArrayList<costume> costumes = new ArrayList<costume>();
    private Image image;
    private int costumeNum = 0;


    public sprite(costume c, int initx, int inity) {
        addCostume(c);
        setCostume(0);
        initSprite(initx,inity);
    }

    private void initSprite(int bx, int by) {
        ImageIcon ii = new ImageIcon(costumes.get(0).Location);
        image = ii.getImage();
        x = bx;
        y = by;
        System.out.println(x + " " + y + costumes.get(costumeNum).toString());
    }

    public void addCostume(costume c){
        costumes.add(c);
        System.out.println("Costume " + c.toString()+" added");
    }

    public void nextCostume() {
        image = costumes.get(costumeNum).image;
        if (costumeNum < costumes.size() - 1) costumeNum++;
        else costumeNum = 0;
    }

    public void setCostume(int a){
        image = costumes.get(a).image;
    }

    public costume getCostume(){
        return costumes.get(costumeNum);
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isTouching(sprite s){
        //TODO: This is where we can check overlap of sprites, given another sprite
        return false;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_A){
            //TODO: this is where the code for pressing a goes
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();
        if(key == KeyEvent.VK_A) {
            //TODO: this is where the code for releasing a goes
        }
    }


}
