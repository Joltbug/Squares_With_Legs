package squaresWithLegs.game;

//Squares with Legs
//A. Wiley Lippke, J. Turner, M. Kramer, S. Harris
//CSCI 241 - Systems Programming FINAL PROJECT

import java.awt.EventQueue; //event handler
import javax.swing.JFrame; //graphics library (window)


public class squaresWithLegs extends JFrame {
    public squaresWithLegs() { //instantiate a squaresWithLegs object
        initUI(); //initialize the user interface
    }

    private void initUI(){ //override JFrame initUI method
        add(new Board()); //create a canvas on which to paint assets
        setSize(1920, 1080); //set window size as monitor size
        setTitle("Squares With Legs"); //window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //if window closed, exit program
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            public void run() {
                squaresWithLegs squares = new squaresWithLegs();
                squares.setVisible(true);
            }
        });
    }
}





