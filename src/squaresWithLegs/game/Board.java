package squaresWithLegs.game;

import java.awt.*; // Event listener
import java.io.*; // Allows file i/o
import javax.swing.JPanel; //Panel to group images and objs together on screen
import javax.swing.Timer; //Timer to update animations and key input
import java.awt.event.*;
import java.util.*; //

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private sprite sprite;
    private final int DELAY = 10;

    public Board(){
        initBoard(); //intialize the board
    }

    private void initBoard(){
        //TODO: this is where all initializations on application start go
        timer = new Timer(DELAY, this);
        timer.start();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //clear Janel
        doDraw(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void doDraw(Graphics g){
        Graphics2D G2D = (Graphics2D) g;
        // TODO: this is where we put all

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: this is where all actions performed per tick are implemented
    }
    private class TAdapter extends KeyAdapter{
        @Override
        public void keyReleased(KeyEvent e){
            sprite.keyReleased(e);
        }
        @Override
        public void keyPressed(KeyEvent e) {
            sprite.keyPressed(e);
        }
    }
}

