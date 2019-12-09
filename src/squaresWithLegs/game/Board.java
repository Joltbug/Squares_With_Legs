package squaresWithLegs.game;

import java.awt.*; // Event listener
import java.awt.desktop.SystemSleepEvent;
import java.awt.image.BufferedImage;
import java.io.*; // Allows file i/o
import javax.imageio.ImageIO;
import javax.swing.JPanel; //Panel to group images and objs together on screen
import javax.swing.Timer; //Timer to update animations and key input
import java.awt.event.*;
import java.util.*; //
import java.util.Scanner; // scanner for cfg
import java.awt.color.*;

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private sprite sprite;
    private final int DELAY = 100;

    private sprite P1;
    private sprite P2;

    //constants for damage taken
    private int KICK_DAMAGE = 16;
    private int SLOWKICK_DAMAGE = 32;
    private int JUMPKICK_DAMAGE = 16;
    private int GROUND_DAMAGE = 40;
    private int SHIFTBLAST_DAMAGE = 80;
    private int SLIDE_DAMAGE = 32;
    private int KONAMI_DAMAGE = 32;

    private int HitCool = 0;
    private int gameOverCool = 0;

    private int boardWidth = 1920;
    Rectangle L_HealthRect;
    Rectangle R_HealthRect;
    Color leftColor = new Color(14,157,43); // #0e9d2b L
    Color rightColor = new Color(171,4,4); // #ab0404 R

    private costume stage = new costume("Images/Background/SWL_Background.jpg");
    private costume L_Health = new costume("Images/Health/Health0.png");
    private costume R_Health = new costume("Images/Health/Health1.png");
    private costume GameOver = new costume("Images/GameOver/gameover.png");

    public Board(){
        initBoard(); //initialize the board
    }

    //initialize the canvas for elements to be drawn onto
    private void initBoard(){
        //TODO: this is where all initializations on application start go

        int p1Up = KeyEvent.VK_W;
        int p1Down = KeyEvent.VK_S;
        int p1Left = KeyEvent.VK_A;
        int p1Right = KeyEvent.VK_D;
        int p1Slow = KeyEvent.VK_Q;
        int p1Fast = KeyEvent.VK_E;

        int p2Up = KeyEvent.VK_I;
        int p2Down = KeyEvent.VK_K;
        int p2Left = KeyEvent.VK_J;
        int p2Right = KeyEvent.VK_L;
        int p2Slow = KeyEvent.VK_U;
        int p2Fast = KeyEvent.VK_O;


        try {
            Scanner scan = new Scanner(new File("CFG.txt"));
            if(scan.hasNext()){
                scan.next();
            }
            if(scan.hasNextInt()){
                boardWidth = scan.nextInt();
                System.out.println(boardWidth);
            }
            if(scan.hasNext()) scan.nextLine();
            if(scan.hasNext()){
                String temp = scan.nextLine();
                System.out.println(temp);
                char[] ch = temp.toCharArray();
                p1Up = java.awt.event.KeyEvent.getExtendedKeyCodeForChar(ch[0]);
                p1Left = java.awt.event.KeyEvent.getExtendedKeyCodeForChar(ch[1]);
                p1Down = java.awt.event.KeyEvent.getExtendedKeyCodeForChar(ch[2]);
                p1Right = java.awt.event.KeyEvent.getExtendedKeyCodeForChar(ch[3]);
                p1Fast = java.awt.event.KeyEvent.getExtendedKeyCodeForChar(ch[4]);
                p1Slow = java.awt.event.KeyEvent.getExtendedKeyCodeForChar(ch[5]);
            }
            if(scan.hasNext()){
                String temp = scan.nextLine();
                char[] ch = temp.toCharArray();
                p2Up = java.awt.event.KeyEvent.getExtendedKeyCodeForChar(ch[0]);
                p2Left = java.awt.event.KeyEvent.getExtendedKeyCodeForChar(ch[1]);
                p2Down = java.awt.event.KeyEvent.getExtendedKeyCodeForChar(ch[2]);
                p2Right = java.awt.event.KeyEvent.getExtendedKeyCodeForChar(ch[3]);
                p2Fast = java.awt.event.KeyEvent.getExtendedKeyCodeForChar(ch[4]);
                p2Slow = java.awt.event.KeyEvent.getExtendedKeyCodeForChar(ch[5]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        //Create P1 default costume
        costume DefaultRight = new costume("Images/Duck/P1Duck0.png");


        //Create P1 costumes for the duck facing right
        costume Duck0 = new costume("Images/Duck/P1Duck0.png");
        costume Duck1 = new costume("Images/Duck/P1Duck1.png");
        costume Duck2 = new costume("Images/Duck/P1Duck2.png");
        costume Duck3 = new costume("Images/Duck/P1Duck3.png");
        costume Duck4 = new costume("Images/Duck/P1Duck4.png");

        //Create P1 costumes for the kick facing right
        costume Kick0= new costume("Images/Kick/P1Fast0.png");
        costume Kick1= new costume("Images/Kick/P1Fast1.png");
        costume Kick2 = new costume("Images/Kick/P1Fast2.png");
        costume Kick3 = new costume("Images/Kick/P1Fast3.png");
        costume Kick4= new costume("Images/Kick/P1Fast4.png");

        //Create P1 costumes for jump facing right
        costume Jump0= new costume("Images/Jump/P1Jump0.png");
        costume Jump1= new costume("Images/Jump/P1Jump1.png");
        costume Jump2 = new costume("Images/Jump/P1Jump2.png");
        costume Jump3 = new costume("Images/Jump/P1Jump3.png");
        costume Jump4 = new costume("Images/Jump/P1Jump4.png");
        costume Jump5= new costume("Images/Jump/P1Jump5.png");

        //Create P1 costumes for movement facing right
        costume Move0= new costume("Images/Move/P1Move0.png");
        costume Move1= new costume("Images/Move/P1Move1.png");
        costume Move2 = new costume("Images/Move/P1Move2.png");
        costume Move3 = new costume("Images/Move/P1Move3.png");
        costume Move4 = new costume("Images/Move/P1Move4.png");
        costume Move5= new costume("Images/Move/P1Move5.png");

        //Create P1 costumes for slow kicks facing right
        costume Slow0= new costume("Images/SlowKick/P1Slow0.png");
        costume Slow1= new costume("Images/SlowKick/P1Slow1.png");
        costume Slow2 = new costume("Images/SlowKick/P1Slow2.png");
        costume Slow3 = new costume("Images/SlowKick/P1Slow3.png");
        costume Slow4 = new costume("Images/SlowKick/P1Slow4.png");
        costume Slow5 = new costume("Images/SlowKick/P1Slow5.png");
        costume Slow6 = new costume("Images/SlowKick/P1Slow6.png");
        costume Slow7 = new costume("Images/SlowKick/P1Slow7.png");
        
        //Create P1 costumes for the duck facing left
        costume DuckLeft0 = new costume("Images/DuckLeft/P1DuckLeft0.png");
        costume DuckLeft1 = new costume("Images/DuckLeft/P1DuckLeft1.png");
        costume DuckLeft2 = new costume("Images/DuckLeft/P1DuckLeft2.png");
        costume DuckLeft3 = new costume("Images/DuckLeft/P1DuckLeft3.png");
        costume DuckLeft4 = new costume("Images/DuckLeft/P1DuckLeft4.png");

        //Create P1 costumes for the kick facing left
        costume KickLeft0= new costume("Images/KickLeft/P1FastLeft0.png");
        costume KickLeft1= new costume("Images/KickLeft/P1FastLeft1.png");
        costume KickLeft2 = new costume("Images/KickLeft/P1FastLeft2.png");
        costume KickLeft3 = new costume("Images/KickLeft/P1FastLeft3.png");
        costume KickLeft4= new costume("Images/KickLeft/P1FastLeft4.png");

        //Create P1 costumes for jump facing left
        costume JumpLeft0= new costume("Images/JumpLeft/P1JumpLeft0.png");
        costume JumpLeft1= new costume("Images/JumpLeft/P1JumpLeft1.png");
        costume JumpLeft2 = new costume("Images/JumpLeft/P1JumpLeft2.png");
        costume JumpLeft3 = new costume("Images/JumpLeft/P1JumpLeft3.png");
        costume JumpLeft4 = new costume("Images/JumpLeft/P1JumpLeft4.png");
        costume JumpLeft5= new costume("Images/JumpLeft/P1JumpLeft5.png");

        //Create P1 costumes for Movement facing left
        costume MoveLeft0= new costume("Images/MoveLeft/P1MoveLeft0.png");
        costume MoveLeft1= new costume("Images/MoveLeft/P1MoveLeft1.png");
        costume MoveLeft2 = new costume("Images/MoveLeft/P1MoveLeft2.png");
        costume MoveLeft3 = new costume("Images/MoveLeft/P1MoveLeft3.png");
        costume MoveLeft4 = new costume("Images/MoveLeft/P1MoveLeft4.png");
        costume MoveLeft5= new costume("Images/MoveLeft/P1MoveLeft5.png");

        //Create P1 costumes for Slow kicks facing left
        costume SlowLeft0= new costume("Images/SlowKickLeft/P1SlowLeft0.png");
        costume SlowLeft1= new costume("Images/SlowKickLeft/P1SlowLeft1.png");
        costume SlowLeft2 = new costume("Images/SlowKickLeft/P1SlowLeft2.png");
        costume SlowLeft3 = new costume("Images/SlowKickLeft/P1SlowLeft3.png");
        costume SlowLeft4 = new costume("Images/SlowKickLeft/P1SlowLeft4.png");
        costume SlowLeft5 = new costume("Images/SlowKickLeft/P1SlowLeft5.png");
        costume SlowLeft6 = new costume("Images/SlowKickLeft/P1SlowLeft6.png");
        costume SlowLeft7 = new costume("Images/SlowKickLeft/P1SlowLeft7.png");

        //Create P1 costumes for Jump kicks
        costume JumpKick0= new costume("Images/JumpKick/P1JumpKick0.png");
        costume JumpKick1= new costume("Images/JumpKick/P1JumpKick1.png");
        costume JumpKick2= new costume("Images/JumpKick/P1JumpKick2.png");
        costume JumpKick3= new costume("Images/JumpKick/P1JumpKick3.png");
        costume JumpKick4= new costume("Images/JumpKick/P1JumpKick4.png");
        costume JumpKick5= new costume("Images/JumpKick/P1JumpKick5.png");
        costume JumpKick6= new costume("Images/JumpKick/P1JumpKick6.png");

        //Create P1 costumes for left Jump kicks
        costume JumpKickLeft0= new costume("Images/JumpKickLeft/P1JumpKickLeft0.png");
        costume JumpKickLeft1= new costume("Images/JumpKickLeft/P1JumpKickLeft1.png");
        costume JumpKickLeft2= new costume("Images/JumpKickLeft/P1JumpKickLeft2.png");
        costume JumpKickLeft3= new costume("Images/JumpKickLeft/P1JumpKickLeft3.png");
        costume JumpKickLeft4= new costume("Images/JumpKickLeft/P1JumpKickLeft4.png");
        costume JumpKickLeft5= new costume("Images/JumpKickLeft/P1JumpKickLeft5.png");
        costume JumpKickLeft6= new costume("Images/JumpKickLeft/P1JumpKickLeft6.png");

        //Create P1 costumes for Shift Blasts
        costume ShiftBlast0= new costume("Images/ShiftBlast/P1ShiftBlast00.png");
        costume ShiftBlast1= new costume("Images/ShiftBlast/P1ShiftBlast01.png");
        costume ShiftBlast2= new costume("Images/ShiftBlast/P1ShiftBlast02.png");
        costume ShiftBlast3= new costume("Images/ShiftBlast/P1ShiftBlast03.png");
        costume ShiftBlast4= new costume("Images/ShiftBlast/P1ShiftBlast04.png");
        costume ShiftBlast5= new costume("Images/ShiftBlast/P1ShiftBlast05.png");
        costume ShiftBlast6= new costume("Images/ShiftBlast/P1ShiftBlast06.png");
        costume ShiftBlast7= new costume("Images/ShiftBlast/P1ShiftBlast07.png");
        costume ShiftBlast8= new costume("Images/ShiftBlast/P1ShiftBlast08.png");
        costume ShiftBlast9= new costume("Images/ShiftBlast/P1ShiftBlast09.png");
        costume ShiftBlast10= new costume("Images/ShiftBlast/P1ShiftBlast10.png");
        costume ShiftBlast11= new costume("Images/ShiftBlast/P1ShiftBlast11.png");
        costume ShiftBlast12= new costume("Images/ShiftBlast/P1ShiftBlast12.png");

        //Create P1 costumes for Left Shift Blasts
        costume ShiftBlastLeft0= new costume("Images/ShiftBlastLeft/P1ShiftBlastLeft00.png");
        costume ShiftBlastLeft1= new costume("Images/ShiftBlastLeft/P1ShiftBlastLeft01.png");
        costume ShiftBlastLeft2= new costume("Images/ShiftBlastLeft/P1ShiftBlastLeft02.png");
        costume ShiftBlastLeft3= new costume("Images/ShiftBlastLeft/P1ShiftBlastLeft03.png");
        costume ShiftBlastLeft4= new costume("Images/ShiftBlastLeft/P1ShiftBlastLeft04.png");
        costume ShiftBlastLeft5= new costume("Images/ShiftBlastLeft/P1ShiftBlastLeft05.png");
        costume ShiftBlastLeft6= new costume("Images/ShiftBlastLeft/P1ShiftBlastLeft06.png");
        costume ShiftBlastLeft7= new costume("Images/ShiftBlastLeft/P1ShiftBlastLeft07.png");
        costume ShiftBlastLeft8= new costume("Images/ShiftBlastLeft/P1ShiftBlastLeft08.png");
        costume ShiftBlastLeft9= new costume("Images/ShiftBlastLeft/P1ShiftBlastLeft09.png");
        costume ShiftBlastLeft10= new costume("Images/ShiftBlastLeft/P1ShiftBlastLeft10.png");
        costume ShiftBlastLeft11= new costume("Images/ShiftBlastLeft/P1ShiftBlastLeft11.png");
        costume ShiftBlastLeft12= new costume("Images/ShiftBlastLeft/P1ShiftBlastLeft12.png");

        //Create P1 costumes for Ground Pounds
        costume GroundPound0= new costume("Images/GroundPound/P1GroundPound00.png");
        costume GroundPound1= new costume("Images/GroundPound/P1GroundPound01.png");
        costume GroundPound2= new costume("Images/GroundPound/P1GroundPound02.png");
        costume GroundPound3= new costume("Images/GroundPound/P1GroundPound03.png");
        costume GroundPound4= new costume("Images/GroundPound/P1GroundPound04.png");
        costume GroundPound5= new costume("Images/GroundPound/P1GroundPound05.png");
        costume GroundPound6= new costume("Images/GroundPound/P1GroundPound06.png");
        costume GroundPound7= new costume("Images/GroundPound/P1GroundPound07.png");
        costume GroundPound8= new costume("Images/GroundPound/P1GroundPound08.png");
        costume GroundPound9= new costume("Images/GroundPound/P1GroundPound09.png");
        costume GroundPound10= new costume("Images/GroundPound/P1GroundPound10.png");
        costume GroundPound11= new costume("Images/GroundPound/P1GroundPound11.png");

        //Create P1 costumes for Left Ground Pounds
        costume GroundPoundLeft0= new costume("Images/GroundPoundLeft/P1GroundPoundLeft00.png");
        costume GroundPoundLeft1= new costume("Images/GroundPoundLeft/P1GroundPoundLeft01.png");
        costume GroundPoundLeft2= new costume("Images/GroundPoundLeft/P1GroundPoundLeft02.png");
        costume GroundPoundLeft3= new costume("Images/GroundPoundLeft/P1GroundPoundLeft03.png");
        costume GroundPoundLeft4= new costume("Images/GroundPoundLeft/P1GroundPoundLeft04.png");
        costume GroundPoundLeft5= new costume("Images/GroundPoundLeft/P1GroundPoundLeft05.png");
        costume GroundPoundLeft6= new costume("Images/GroundPoundLeft/P1GroundPoundLeft06.png");
        costume GroundPoundLeft7= new costume("Images/GroundPoundLeft/P1GroundPoundLeft07.png");
        costume GroundPoundLeft8= new costume("Images/GroundPoundLeft/P1GroundPoundLeft08.png");
        costume GroundPoundLeft9= new costume("Images/GroundPoundLeft/P1GroundPoundLeft09.png");
        costume GroundPoundLeft10= new costume("Images/GroundPoundLeft/P1GroundPoundLeft10.png");
        costume GroundPoundLeft11= new costume("Images/GroundPoundLeft/P1GroundPoundLeft11.png");

        //Create P1 costumes for Slides
        costume Slide0= new costume("Images/Slide/P1Slide00.png");
        costume Slide1= new costume("Images/Slide/P1Slide01.png");
        costume Slide2= new costume("Images/Slide/P1Slide02.png");
        costume Slide3= new costume("Images/Slide/P1Slide03.png");
        costume Slide4= new costume("Images/Slide/P1Slide04.png");
        costume Slide5= new costume("Images/Slide/P1Slide05.png");
        costume Slide6= new costume("Images/Slide/P1Slide06.png");
        costume Slide7= new costume("Images/Slide/P1Slide07.png");
        costume Slide8= new costume("Images/Slide/P1Slide08.png");
        

        //Create P1 costumes for Left Slides
        costume SlideLeft0= new costume("Images/SlideLeft/P1SlideLeft00.png");
        costume SlideLeft1= new costume("Images/SlideLeft/P1SlideLeft01.png");
        costume SlideLeft2= new costume("Images/SlideLeft/P1SlideLeft02.png");
        costume SlideLeft3= new costume("Images/SlideLeft/P1SlideLeft03.png");
        costume SlideLeft4= new costume("Images/SlideLeft/P1SlideLeft04.png");
        costume SlideLeft5= new costume("Images/SlideLeft/P1SlideLeft05.png");
        costume SlideLeft6= new costume("Images/SlideLeft/P1SlideLeft06.png");
        costume SlideLeft7= new costume("Images/SlideLeft/P1SlideLeft07.png");
        costume SlideLeft8= new costume("Images/SlideLeft/P1SlideLeft08.png");

        //Create P1 costumes for AirDashes
        costume AirDash0= new costume("Images/AirDash/P1AirDash00.png");
        costume AirDash1= new costume("Images/AirDash/P1AirDash01.png");
        costume AirDash2= new costume("Images/AirDash/P1AirDash02.png");
        costume AirDash3= new costume("Images/AirDash/P1AirDash03.png");
        costume AirDash4= new costume("Images/AirDash/P1AirDash04.png");
        costume AirDash5= new costume("Images/AirDash/P1AirDash05.png");
        costume AirDash6= new costume("Images/AirDash/P1AirDash06.png");
        costume AirDash7= new costume("Images/AirDash/P1AirDash07.png");
        costume AirDash8= new costume("Images/AirDash/P1AirDash08.png");
        costume AirDash9= new costume("Images/AirDash/P1AirDash09.png");


        //Create P1 costumes for Left AirDashes
        costume AirDashLeft0= new costume("Images/AirDashLeft/P1AirDashLeft00.png");
        costume AirDashLeft1= new costume("Images/AirDashLeft/P1AirDashLeft01.png");
        costume AirDashLeft2= new costume("Images/AirDashLeft/P1AirDashLeft02.png");
        costume AirDashLeft3= new costume("Images/AirDashLeft/P1AirDashLeft03.png");
        costume AirDashLeft4= new costume("Images/AirDashLeft/P1AirDashLeft04.png");
        costume AirDashLeft5= new costume("Images/AirDashLeft/P1AirDashLeft05.png");
        costume AirDashLeft6= new costume("Images/AirDashLeft/P1AirDashLeft06.png");
        costume AirDashLeft7= new costume("Images/AirDashLeft/P1AirDashLeft07.png");
        costume AirDashLeft8= new costume("Images/AirDashLeft/P1AirDashLeft08.png");
        costume AirDashLeft9= new costume("Images/AirDashLeft/P1AirDashLeft09.png");

        //Create P1 costumes for Konamis
        costume Konami0= new costume("Images/Konami/P1Konami00.png");
        costume Konami1= new costume("Images/Konami/P1Konami01.png");
        costume Konami2= new costume("Images/Konami/P1Konami02.png");
        costume Konami3= new costume("Images/Konami/P1Konami03.png");
        costume Konami4= new costume("Images/Konami/P1Konami04.png");
        costume Konami5= new costume("Images/Konami/P1Konami05.png");
        costume Konami6= new costume("Images/Konami/P1Konami06.png");
        costume Konami7= new costume("Images/Konami/P1Konami07.png");
        costume Konami8= new costume("Images/Konami/P1Konami08.png");
        costume Konami9= new costume("Images/Konami/P1Konami09.png");
        costume Konami10= new costume("Images/Konami/P1Konami10.png");


        //Create P1 costumes for Left Konamis
        costume KonamiLeft0= new costume("Images/KonamiLeft/P1KonamiLeft00.png");
        costume KonamiLeft1= new costume("Images/KonamiLeft/P1KonamiLeft01.png");
        costume KonamiLeft2= new costume("Images/KonamiLeft/P1KonamiLeft02.png");
        costume KonamiLeft3= new costume("Images/KonamiLeft/P1KonamiLeft03.png");
        costume KonamiLeft4= new costume("Images/KonamiLeft/P1KonamiLeft04.png");
        costume KonamiLeft5= new costume("Images/KonamiLeft/P1KonamiLeft05.png");
        costume KonamiLeft6= new costume("Images/KonamiLeft/P1KonamiLeft06.png");
        costume KonamiLeft7= new costume("Images/KonamiLeft/P1KonamiLeft07.png");
        costume KonamiLeft8= new costume("Images/KonamiLeft/P1KonamiLeft08.png");
        costume KonamiLeft9= new costume("Images/KonamiLeft/P1KonamiLeft09.png");
        costume KonamiLeft10= new costume("Images/KonamiLeft/P1KonamiLeft10.png");


        



//initialize P1
        P1 = new sprite(DefaultRight, 96,336,p1Up,p1Down,p1Left, p1Right,p1Fast,p1Slow, 1, boardWidth);

        //add right Duck costumes to P1 Starting at position 1 ending at position 5
        P1.addCostume(Duck0);
        P1.addCostume(Duck1);
        P1.addCostume(Duck2);
        P1.addCostume(Duck3);
        P1.addCostume(Duck4);

        //add right kick costumes to P1 Starting at position 6 ending at position 10
        P1.addCostume(Kick0);
        P1.addCostume(Kick1);
        P1.addCostume(Kick2);
        P1.addCostume(Kick3);
        P1.addCostume(Kick4);

        //add right Jump costumes to P1 starting at position 11 ending at position 16
        P1.addCostume(Jump0);
        P1.addCostume(Jump1);
        P1.addCostume(Jump2);
        P1.addCostume(Jump3);
        P1.addCostume(Jump4);
        P1.addCostume(Jump5);

        //add right Movement costumes to P1 starting at position 17-19 ending at position 20-22
        P1.addCostume(Move0);
        P1.addCostume(Move1);
        P1.addCostume(Move2);
        P1.addCostume(Move3);
        P1.addCostume(Move4);
        P1.addCostume(Move5);

        //add right Slow Kick costumes to P1 starting at position 23 ending at position 30
        P1.addCostume(Slow0);
        P1.addCostume(Slow1);
        P1.addCostume(Slow2);
        P1.addCostume(Slow3);
        P1.addCostume(Slow4);
        P1.addCostume(Slow5);
        P1.addCostume(Slow6);
        P1.addCostume(Slow7);

        //add base left costume at 31
        P1.addCostume(DuckLeft0);

        //add right Duck costumes to P1 Starting at position 32 ending at position 36
        P1.addCostume(DuckLeft0);
        P1.addCostume(DuckLeft1);
        P1.addCostume(DuckLeft2);
        P1.addCostume(DuckLeft3);
        P1.addCostume(DuckLeft4);

        //add right kick costumes to P1 Starting at position 37 ending at position 41
        P1.addCostume(KickLeft0);
        P1.addCostume(KickLeft1);
        P1.addCostume(KickLeft2);
        P1.addCostume(KickLeft3);
        P1.addCostume(KickLeft4);

        //add right Jump costumes to P1 starting at position 42 ending at position 47
        P1.addCostume(JumpLeft0);
        P1.addCostume(JumpLeft1);
        P1.addCostume(JumpLeft2);
        P1.addCostume(JumpLeft3);
        P1.addCostume(JumpLeft4);
        P1.addCostume(JumpLeft5);

        //add right Movement costumes to P1 starting at position 48-50 ending at position 51-53
        P1.addCostume(MoveLeft0);
        P1.addCostume(MoveLeft1);
        P1.addCostume(MoveLeft2);
        P1.addCostume(MoveLeft3);
        P1.addCostume(MoveLeft4);
        P1.addCostume(MoveLeft5);

        //add right Slow Kick costumes to P1 starting at position 54 ending at position 61
        P1.addCostume(SlowLeft0);
        P1.addCostume(SlowLeft1);
        P1.addCostume(SlowLeft2);
        P1.addCostume(SlowLeft3);
        P1.addCostume(SlowLeft4);
        P1.addCostume(SlowLeft5);
        P1.addCostume(SlowLeft6);
        P1.addCostume(SlowLeft7);

        //add right Jump Kick costumes to P2 starting at position 62 ending at 69
        P1.addCostume(JumpKick0);
        P1.addCostume(JumpKick1);
        P1.addCostume(JumpKick2);
        P1.addCostume(JumpKick3);
        P1.addCostume(JumpKick4);
        P1.addCostume(JumpKick5);
        P1.addCostume(JumpKick6);
        P1.addCostume(JumpKick6);

        //add Left Jump Kick costumes to P1 starting at position 70 ending at 77
        P1.addCostume(JumpKickLeft0);
        P1.addCostume(JumpKickLeft1);
        P1.addCostume(JumpKickLeft2);
        P1.addCostume(JumpKickLeft3);
        P1.addCostume(JumpKickLeft4);
        P1.addCostume(JumpKickLeft5);
        P1.addCostume(JumpKickLeft6);
        P1.addCostume(JumpKickLeft6);

        //add right ShiftBlast costumes to P2 starting at position 78 ending at 91
        P1.addCostume(ShiftBlast0);
        P1.addCostume(ShiftBlast1);
        P1.addCostume(ShiftBlast2);
        P1.addCostume(ShiftBlast3);
        P1.addCostume(ShiftBlast4);
        P1.addCostume(ShiftBlast5);
        P1.addCostume(ShiftBlast6);
        P1.addCostume(ShiftBlast7);
        P1.addCostume(ShiftBlast8);
        P1.addCostume(ShiftBlast9);
        P1.addCostume(ShiftBlast10);
        P1.addCostume(ShiftBlast11);
        P1.addCostume(ShiftBlast12);
        P1.addCostume(ShiftBlast12);

        //add left ShiftBlast costumes to P2 starting at position 92 ending at 105
        P1.addCostume(ShiftBlastLeft0);
        P1.addCostume(ShiftBlastLeft1);
        P1.addCostume(ShiftBlastLeft2);
        P1.addCostume(ShiftBlastLeft3);
        P1.addCostume(ShiftBlastLeft4);
        P1.addCostume(ShiftBlastLeft5);
        P1.addCostume(ShiftBlastLeft6);
        P1.addCostume(ShiftBlastLeft7);
        P1.addCostume(ShiftBlastLeft8);
        P1.addCostume(ShiftBlastLeft9);
        P1.addCostume(ShiftBlastLeft10);
        P1.addCostume(ShiftBlastLeft11);
        P1.addCostume(ShiftBlastLeft12);
        P1.addCostume(ShiftBlastLeft12);

        //add right GroundPound costumes to P2 starting at position 106 ending at 117
        P1.addCostume(GroundPound0);
        P1.addCostume(GroundPound1);
        P1.addCostume(GroundPound2);
        P1.addCostume(GroundPound3);
        P1.addCostume(GroundPound4);
        P1.addCostume(GroundPound5);
        P1.addCostume(GroundPound6);
        P1.addCostume(GroundPound7);
        P1.addCostume(GroundPound8);
        P1.addCostume(GroundPound9);
        P1.addCostume(GroundPound10);
        P1.addCostume(GroundPound11);

        //add left GroundPound costumes to P2 starting at position 118 ending at 129
        P1.addCostume(GroundPoundLeft0);
        P1.addCostume(GroundPoundLeft1);
        P1.addCostume(GroundPoundLeft2);
        P1.addCostume(GroundPoundLeft3);
        P1.addCostume(GroundPoundLeft4);
        P1.addCostume(GroundPoundLeft5);
        P1.addCostume(GroundPoundLeft6);
        P1.addCostume(GroundPoundLeft7);
        P1.addCostume(GroundPoundLeft8);
        P1.addCostume(GroundPoundLeft9);
        P1.addCostume(GroundPoundLeft10);
        P1.addCostume(GroundPoundLeft11);

        //add right Slide costumes to P1 starting at position 130 ending at 139
        P1.addCostume(Slide0);
        P1.addCostume(Slide1);
        P1.addCostume(Slide2);
        P1.addCostume(Slide3);
        P1.addCostume(Slide4);
        P1.addCostume(Slide5);
        P1.addCostume(Slide6);
        P1.addCostume(Slide7);
        P1.addCostume(Slide8);
        P1.addCostume(Slide8);

        //add left Slide costumes to P1 starting at position 140 ending at 149
        P1.addCostume(SlideLeft0);
        P1.addCostume(SlideLeft1);
        P1.addCostume(SlideLeft2);
        P1.addCostume(SlideLeft3);
        P1.addCostume(SlideLeft4);
        P1.addCostume(SlideLeft5);
        P1.addCostume(SlideLeft6);
        P1.addCostume(SlideLeft7);
        P1.addCostume(SlideLeft8);
        P1.addCostume(SlideLeft8);

        //add right AirDash costumes to P1 starting at position 150 ending at 159
        P1.addCostume(AirDash0);
        P1.addCostume(AirDash1);
        P1.addCostume(AirDash2);
        P1.addCostume(AirDash3);
        P1.addCostume(AirDash4);
        P1.addCostume(AirDash5);
        P1.addCostume(AirDash6);
        P1.addCostume(AirDash7);
        P1.addCostume(AirDash0);
        P1.addCostume(AirDash0);


        //add left AirDash costumes to P1 starting at position 160 ending at 169
        P1.addCostume(AirDashLeft0);
        P1.addCostume(AirDashLeft1);
        P1.addCostume(AirDashLeft2);
        P1.addCostume(AirDashLeft3);
        P1.addCostume(AirDashLeft4);
        P1.addCostume(AirDashLeft5);
        P1.addCostume(AirDashLeft6);
        P1.addCostume(AirDashLeft7);
        P1.addCostume(AirDashLeft0);
        P1.addCostume(AirDashLeft0);

        //add right Konami costumes to P1 starting at position 170 ending at 180
        P1.addCostume(Konami0);
        P1.addCostume(Konami1);
        P1.addCostume(Konami2);
        P1.addCostume(Konami3);
        P1.addCostume(Konami4);
        P1.addCostume(Konami5);
        P1.addCostume(Konami6);
        P1.addCostume(Konami7);
        P1.addCostume(Konami8);
        P1.addCostume(Konami9);
        P1.addCostume(Konami10);


        //add left Konami costumes to P1 starting at position 181 ending at 191
        P1.addCostume(KonamiLeft0);
        P1.addCostume(KonamiLeft1);
        P1.addCostume(KonamiLeft2);
        P1.addCostume(KonamiLeft3);
        P1.addCostume(KonamiLeft4);
        P1.addCostume(KonamiLeft5);
        P1.addCostume(KonamiLeft6);
        P1.addCostume(KonamiLeft7);
        P1.addCostume(KonamiLeft8);
        P1.addCostume(KonamiLeft9);
        P1.addCostume(KonamiLeft10);

        

        //Create P2 default costume
        costume P2DefaultRight = new costume("Images/Duck/P2Duck0.png");

        //Create P2 costumes for the duck facing right
        costume P2Duck0 = new costume("Images/Duck/P2Duck0.png");
        costume P2Duck1 = new costume("Images/Duck/P2Duck1.png");
        costume P2Duck2 = new costume("Images/Duck/P2Duck2.png");
        costume P2Duck3 = new costume("Images/Duck/P2Duck3.png");
        costume P2Duck4 = new costume("Images/Duck/P2Duck4.png");

        //Create P2 costumes for the kick facing right
        costume P2Kick0= new costume("Images/Kick/P2Fast0.png");
        costume P2Kick1= new costume("Images/Kick/P2Fast1.png");
        costume P2Kick2 = new costume("Images/Kick/P2Fast2.png");
        costume P2Kick3 = new costume("Images/Kick/P2Fast3.png");
        costume P2Kick4= new costume("Images/Kick/P2Fast4.png");

        //Create P2 costumes for jump facing right
        costume P2Jump0= new costume("Images/Jump/P2Jump0.png");
        costume P2Jump1= new costume("Images/Jump/P2Jump1.png");
        costume P2Jump2 = new costume("Images/Jump/P2Jump2.png");
        costume P2Jump3 = new costume("Images/Jump/P2Jump3.png");
        costume P2Jump4 = new costume("Images/Jump/P2Jump4.png");
        costume P2Jump5= new costume("Images/Jump/P2Jump5.png");

        //Create P2 costumes for movement facing right
        costume P2Move0= new costume("Images/Move/P2Move0.png");
        costume P2Move1= new costume("Images/Move/P2Move1.png");
        costume P2Move2 = new costume("Images/Move/P2Move2.png");
        costume P2Move3 = new costume("Images/Move/P2Move3.png");
        costume P2Move4 = new costume("Images/Move/P2Move4.png");
        costume P2Move5= new costume("Images/Move/P2Move5.png");

        //Create P2 costumes for slow kicks facing right
        costume P2Slow0= new costume("Images/SlowKick/P2Slow0.png");
        costume P2Slow1= new costume("Images/SlowKick/P2Slow1.png");
        costume P2Slow2 = new costume("Images/SlowKick/P2Slow2.png");
        costume P2Slow3 = new costume("Images/SlowKick/P2Slow3.png");
        costume P2Slow4 = new costume("Images/SlowKick/P2Slow4.png");
        costume P2Slow5 = new costume("Images/SlowKick/P2Slow5.png");
        costume P2Slow6 = new costume("Images/SlowKick/P2Slow6.png");
        costume P2Slow7 = new costume("Images/SlowKick/P2Slow7.png");

        //Create P2 costumes for the duck facing left
        costume P2DuckLeft0 = new costume("Images/DuckLeft/P2DuckLeft0.png");
        costume P2DuckLeft1 = new costume("Images/DuckLeft/P2DuckLeft1.png");
        costume P2DuckLeft2 = new costume("Images/DuckLeft/P2DuckLeft2.png");
        costume P2DuckLeft3 = new costume("Images/DuckLeft/P2DuckLeft3.png");
        costume P2DuckLeft4 = new costume("Images/DuckLeft/P2DuckLeft4.png");

        //Create P2 costumes for the kick facing left
        costume P2KickLeft0= new costume("Images/KickLeft/P2FastLeft0.png");
        costume P2KickLeft1= new costume("Images/KickLeft/P2FastLeft1.png");
        costume P2KickLeft2 = new costume("Images/KickLeft/P2FastLeft2.png");
        costume P2KickLeft3 = new costume("Images/KickLeft/P2FastLeft3.png");
        costume P2KickLeft4= new costume("Images/KickLeft/P2FastLeft4.png");

        //Create P2 costumes for jump facing left
        costume P2JumpLeft0= new costume("Images/JumpLeft/P2JumpLeft0.png");
        costume P2JumpLeft1= new costume("Images/JumpLeft/P2JumpLeft1.png");
        costume P2JumpLeft2 = new costume("Images/JumpLeft/P2JumpLeft2.png");
        costume P2JumpLeft3 = new costume("Images/JumpLeft/P2JumpLeft3.png");
        costume P2JumpLeft4 = new costume("Images/JumpLeft/P2JumpLeft4.png");
        costume P2JumpLeft5= new costume("Images/JumpLeft/P2JumpLeft5.png");

        //Create P2 costumes for Movement facing left
        costume P2MoveLeft0= new costume("Images/MoveLeft/P2MoveLeft0.png");
        costume P2MoveLeft1= new costume("Images/MoveLeft/P2MoveLeft1.png");
        costume P2MoveLeft2 = new costume("Images/MoveLeft/P2MoveLeft2.png");
        costume P2MoveLeft3 = new costume("Images/MoveLeft/P2MoveLeft3.png");
        costume P2MoveLeft4 = new costume("Images/MoveLeft/P2MoveLeft4.png");
        costume P2MoveLeft5= new costume("Images/MoveLeft/P2MoveLeft5.png");

        //Create P2 costumes for Slow kicks facing left
        costume P2SlowLeft0= new costume("Images/SlowKickLeft/P2SlowLeft0.png");
        costume P2SlowLeft1= new costume("Images/SlowKickLeft/P2SlowLeft1.png");
        costume P2SlowLeft2 = new costume("Images/SlowKickLeft/P2SlowLeft2.png");
        costume P2SlowLeft3 = new costume("Images/SlowKickLeft/P2SlowLeft3.png");
        costume P2SlowLeft4 = new costume("Images/SlowKickLeft/P2SlowLeft4.png");
        costume P2SlowLeft5 = new costume("Images/SlowKickLeft/P2SlowLeft5.png");
        costume P2SlowLeft6 = new costume("Images/SlowKickLeft/P2SlowLeft6.png");
        costume P2SlowLeft7 = new costume("Images/SlowKickLeft/P2SlowLeft7.png");

        //Create P2 costumes for Jump Kicks
        costume P2JumpKick0 = new costume("Images/JumpKick/P2JumpKick0.png");
        costume P2JumpKick1 = new costume("Images/JumpKick/P2JumpKick1.png");
        costume P2JumpKick2 = new costume("Images/JumpKick/P2JumpKick2.png");
        costume P2JumpKick3 = new costume("Images/JumpKick/P2JumpKick3.png");
        costume P2JumpKick4 = new costume("Images/JumpKick/P2JumpKick4.png");
        costume P2JumpKick5 = new costume("Images/JumpKick/P2JumpKick5.png");
        costume P2JumpKick6 = new costume("Images/JumpKick/P2JumpKick6.png");

        //Create P2 costumes for left Jump Kicks
        costume P2JumpKickLeft0= new costume("Images/JumpKickLeft/P2JumpKickRedLeft0.png");
        costume P2JumpKickLeft1= new costume("Images/JumpKickLeft/P2JumpKickRedLeft1.png");
        costume P2JumpKickLeft2= new costume("Images/JumpKickLeft/P2JumpKickRedLeft2.png");
        costume P2JumpKickLeft3= new costume("Images/JumpKickLeft/P2JumpKickRedLeft3.png");
        costume P2JumpKickLeft4= new costume("Images/JumpKickLeft/P2JumpKickRedLeft4.png");
        costume P2JumpKickLeft5= new costume("Images/JumpKickLeft/P2JumpKickRedLeft5.png");
        costume P2JumpKickLeft6= new costume("Images/JumpKickLeft/P2JumpKickRedLeft6.png");

        //Create P2 costumes for Shift Blasts
        costume P2ShiftBlast0= new costume("Images/ShiftBlast/P2ShiftBlast00.png");
        costume P2ShiftBlast1= new costume("Images/ShiftBlast/P2ShiftBlast01.png");
        costume P2ShiftBlast2= new costume("Images/ShiftBlast/P2ShiftBlast02.png");
        costume P2ShiftBlast3= new costume("Images/ShiftBlast/P2ShiftBlast03.png");
        costume P2ShiftBlast4= new costume("Images/ShiftBlast/P2ShiftBlast04.png");
        costume P2ShiftBlast5= new costume("Images/ShiftBlast/P2ShiftBlast05.png");
        costume P2ShiftBlast6= new costume("Images/ShiftBlast/P2ShiftBlast06.png");
        costume P2ShiftBlast7= new costume("Images/ShiftBlast/P2ShiftBlast07.png");
        costume P2ShiftBlast8= new costume("Images/ShiftBlast/P2ShiftBlast08.png");
        costume P2ShiftBlast9= new costume("Images/ShiftBlast/P2ShiftBlast09.png");
        costume P2ShiftBlast10= new costume("Images/ShiftBlast/P2ShiftBlast10.png");
        costume P2ShiftBlast11= new costume("Images/ShiftBlast/P2ShiftBlast11.png");
        costume P2ShiftBlast12= new costume("Images/ShiftBlast/P2ShiftBlast12.png");

        //Create P2 costumes for Left Shift Blasts
        costume P2ShiftBlastLeft0= new costume("Images/ShiftBlastLeft/P2ShiftBlastLeft00.png");
        costume P2ShiftBlastLeft1= new costume("Images/ShiftBlastLeft/P2ShiftBlastLeft01.png");
        costume P2ShiftBlastLeft2= new costume("Images/ShiftBlastLeft/P2ShiftBlastLeft02.png");
        costume P2ShiftBlastLeft3= new costume("Images/ShiftBlastLeft/P2ShiftBlastLeft03.png");
        costume P2ShiftBlastLeft4= new costume("Images/ShiftBlastLeft/P2ShiftBlastLeft04.png");
        costume P2ShiftBlastLeft5= new costume("Images/ShiftBlastLeft/P2ShiftBlastLeft05.png");
        costume P2ShiftBlastLeft6= new costume("Images/ShiftBlastLeft/P2ShiftBlastLeft06.png");
        costume P2ShiftBlastLeft7= new costume("Images/ShiftBlastLeft/P2ShiftBlastLeft07.png");
        costume P2ShiftBlastLeft8= new costume("Images/ShiftBlastLeft/P2ShiftBlastLeft08.png");
        costume P2ShiftBlastLeft9= new costume("Images/ShiftBlastLeft/P2ShiftBlastLeft09.png");
        costume P2ShiftBlastLeft10= new costume("Images/ShiftBlastLeft/P2ShiftBlastLeft10.png");
        costume P2ShiftBlastLeft11= new costume("Images/ShiftBlastLeft/P2ShiftBlastLeft11.png");
        costume P2ShiftBlastLeft12= new costume("Images/ShiftBlastLeft/P2ShiftBlastLeft12.png");

        //Create P2 costumes for Ground Pounds
        costume P2GroundPound0= new costume("Images/GroundPound/P2GroundPound00.png");
        costume P2GroundPound1= new costume("Images/GroundPound/P2GroundPound01.png");
        costume P2GroundPound2= new costume("Images/GroundPound/P2GroundPound02.png");
        costume P2GroundPound3= new costume("Images/GroundPound/P2GroundPound03.png");
        costume P2GroundPound4= new costume("Images/GroundPound/P2GroundPound04.png");
        costume P2GroundPound5= new costume("Images/GroundPound/P2GroundPound05.png");
        costume P2GroundPound6= new costume("Images/GroundPound/P2GroundPound06.png");
        costume P2GroundPound7= new costume("Images/GroundPound/P2GroundPound07.png");
        costume P2GroundPound8= new costume("Images/GroundPound/P2GroundPound08.png");
        costume P2GroundPound9= new costume("Images/GroundPound/P2GroundPound09.png");
        costume P2GroundPound10= new costume("Images/GroundPound/P2GroundPound10.png");
        costume P2GroundPound11= new costume("Images/GroundPound/P2GroundPound11.png");

        //Create P2 costumes for Left Ground Pounds
        costume P2GroundPoundLeft0= new costume("Images/GroundPoundLeft/P2GroundPoundLeft00.png");
        costume P2GroundPoundLeft1= new costume("Images/GroundPoundLeft/P2GroundPoundLeft01.png");
        costume P2GroundPoundLeft2= new costume("Images/GroundPoundLeft/P2GroundPoundLeft02.png");
        costume P2GroundPoundLeft3= new costume("Images/GroundPoundLeft/P2GroundPoundLeft03.png");
        costume P2GroundPoundLeft4= new costume("Images/GroundPoundLeft/P2GroundPoundLeft04.png");
        costume P2GroundPoundLeft5= new costume("Images/GroundPoundLeft/P2GroundPoundLeft05.png");
        costume P2GroundPoundLeft6= new costume("Images/GroundPoundLeft/P2GroundPoundLeft06.png");
        costume P2GroundPoundLeft7= new costume("Images/GroundPoundLeft/P2GroundPoundLeft07.png");
        costume P2GroundPoundLeft8= new costume("Images/GroundPoundLeft/P2GroundPoundLeft08.png");
        costume P2GroundPoundLeft9= new costume("Images/GroundPoundLeft/P2GroundPoundLeft09.png");
        costume P2GroundPoundLeft10= new costume("Images/GroundPoundLeft/P2GroundPoundLeft10.png");
        costume P2GroundPoundLeft11= new costume("Images/GroundPoundLeft/P2GroundPoundLeft11.png");

        //Create P2 costumes for Slides
        costume P2Slide0= new costume("Images/Slide/P2Slide00.png");
        costume P2Slide1= new costume("Images/Slide/P2Slide01.png");
        costume P2Slide2= new costume("Images/Slide/P2Slide02.png");
        costume P2Slide3= new costume("Images/Slide/P2Slide03.png");
        costume P2Slide4= new costume("Images/Slide/P2Slide04.png");
        costume P2Slide5= new costume("Images/Slide/P2Slide05.png");
        costume P2Slide6= new costume("Images/Slide/P2Slide06.png");
        costume P2Slide7= new costume("Images/Slide/P2Slide07.png");
        costume P2Slide8= new costume("Images/Slide/P2Slide08.png");


        //Create P2 costumes for Left Slides
        costume P2SlideLeft0= new costume("Images/SlideLeft/P2SlideLeft00.png");
        costume P2SlideLeft1= new costume("Images/SlideLeft/P2SlideLeft01.png");
        costume P2SlideLeft2= new costume("Images/SlideLeft/P2SlideLeft02.png");
        costume P2SlideLeft3= new costume("Images/SlideLeft/P2SlideLeft03.png");
        costume P2SlideLeft4= new costume("Images/SlideLeft/P2SlideLeft04.png");
        costume P2SlideLeft5= new costume("Images/SlideLeft/P2SlideLeft05.png");
        costume P2SlideLeft6= new costume("Images/SlideLeft/P2SlideLeft06.png");
        costume P2SlideLeft7= new costume("Images/SlideLeft/P2SlideLeft07.png");
        costume P2SlideLeft8= new costume("Images/SlideLeft/P2SlideLeft08.png");

        //Create P2 costumes for AirDashes
        costume P2AirDash0= new costume("Images/AirDash/P2AirDash00.png");
        costume P2AirDash1= new costume("Images/AirDash/P2AirDash01.png");
        costume P2AirDash2= new costume("Images/AirDash/P2AirDash02.png");
        costume P2AirDash3= new costume("Images/AirDash/P2AirDash03.png");
        costume P2AirDash4= new costume("Images/AirDash/P2AirDash04.png");
        costume P2AirDash5= new costume("Images/AirDash/P2AirDash05.png");
        costume P2AirDash6= new costume("Images/AirDash/P2AirDash06.png");
        costume P2AirDash7= new costume("Images/AirDash/P2AirDash07.png");
        costume P2AirDash8= new costume("Images/AirDash/P2AirDash08.png");
        costume P2AirDash9= new costume("Images/AirDash/P2AirDash09.png");

        //Create P2 costumes for Left AirDashes
        costume P2AirDashLeft0= new costume("Images/AirDashLeft/P2AirDashLeft00.png");
        costume P2AirDashLeft1= new costume("Images/AirDashLeft/P2AirDashLeft01.png");
        costume P2AirDashLeft2= new costume("Images/AirDashLeft/P2AirDashLeft02.png");
        costume P2AirDashLeft3= new costume("Images/AirDashLeft/P2AirDashLeft03.png");
        costume P2AirDashLeft4= new costume("Images/AirDashLeft/P2AirDashLeft04.png");
        costume P2AirDashLeft5= new costume("Images/AirDashLeft/P2AirDashLeft05.png");
        costume P2AirDashLeft6= new costume("Images/AirDashLeft/P2AirDashLeft06.png");
        costume P2AirDashLeft7= new costume("Images/AirDashLeft/P2AirDashLeft07.png");
        costume P2AirDashLeft8= new costume("Images/AirDashLeft/P2AirDashLeft08.png");
        costume P2AirDashLeft9= new costume("Images/AirDashLeft/P2AirDashLeft09.png");

        //Create P2 costumes for Konamis
        costume P2Konami0= new costume("Images/Konami/P2Konami00.png");
        costume P2Konami1= new costume("Images/Konami/P2Konami01.png");
        costume P2Konami2= new costume("Images/Konami/P2Konami02.png");
        costume P2Konami3= new costume("Images/Konami/P2Konami03.png");
        costume P2Konami4= new costume("Images/Konami/P2Konami04.png");
        costume P2Konami5= new costume("Images/Konami/P2Konami05.png");
        costume P2Konami6= new costume("Images/Konami/P2Konami06.png");
        costume P2Konami7= new costume("Images/Konami/P2Konami07.png");
        costume P2Konami8= new costume("Images/Konami/P2Konami08.png");
        costume P2Konami9= new costume("Images/Konami/P2Konami09.png");
        costume P2Konami10= new costume("Images/Konami/P2Konami10.png");

        //Create P2 costumes for Left Konamis
        costume P2KonamiLeft0= new costume("Images/KonamiLeft/P2KonamiLeft00.png");
        costume P2KonamiLeft1= new costume("Images/KonamiLeft/P2KonamiLeft01.png");
        costume P2KonamiLeft2= new costume("Images/KonamiLeft/P2KonamiLeft02.png");
        costume P2KonamiLeft3= new costume("Images/KonamiLeft/P2KonamiLeft03.png");
        costume P2KonamiLeft4= new costume("Images/KonamiLeft/P2KonamiLeft04.png");
        costume P2KonamiLeft5= new costume("Images/KonamiLeft/P2KonamiLeft05.png");
        costume P2KonamiLeft6= new costume("Images/KonamiLeft/P2KonamiLeft06.png");
        costume P2KonamiLeft7= new costume("Images/KonamiLeft/P2KonamiLeft07.png");
        costume P2KonamiLeft8= new costume("Images/KonamiLeft/P2KonamiLeft08.png");
        costume P2KonamiLeft9= new costume("Images/KonamiLeft/P2KonamiLeft09.png");
        costume P2KonamiLeft10= new costume("Images/KonamiLeft/P2KonamiLeft10.png");


        //initialize P2
        P2 = new sprite(P2DefaultRight,boardWidth - 576,336, p2Up ,p2Down , p2Left, p2Right, p2Fast,p2Slow, 2, boardWidth);

        //add right Duck costumes to P2 Starting at position 1 ending at position 5
        P2.addCostume(P2Duck0);
        P2.addCostume(P2Duck1);
        P2.addCostume(P2Duck2);
        P2.addCostume(P2Duck3);
        P2.addCostume(P2Duck4);

        //add right kick costumes to P2 Starting at position 6 ending at position 10
        P2.addCostume(P2Kick0);
        P2.addCostume(P2Kick1);
        P2.addCostume(P2Kick2);
        P2.addCostume(P2Kick3);
        P2.addCostume(P2Kick4);

        //add right Jump costumes to P2 starting at position 11 ending at position 16
        P2.addCostume(P2Jump0);
        P2.addCostume(P2Jump1);
        P2.addCostume(P2Jump2);
        P2.addCostume(P2Jump3);
        P2.addCostume(P2Jump4);
        P2.addCostume(P2Jump5);

        //add right Movement costumes to P2 starting at position 17-19 ending at position 20-22
        P2.addCostume(P2Move0);
        P2.addCostume(P2Move1);
        P2.addCostume(P2Move2);
        P2.addCostume(P2Move3);
        P2.addCostume(P2Move4);
        P2.addCostume(P2Move5);

        //add right Slow Kick costumes to P2 starting at position 23 ending at position 30
        P2.addCostume(P2Slow0);
        P2.addCostume(P2Slow1);
        P2.addCostume(P2Slow2);
        P2.addCostume(P2Slow3);
        P2.addCostume(P2Slow4);
        P2.addCostume(P2Slow5);
        P2.addCostume(P2Slow6);
        P2.addCostume(P2Slow7);

        //add base left costume at 31
        P2.addCostume(P2DuckLeft0);

        //add right Duck costumes to P2 Starting at position 32 ending at position 36
        P2.addCostume(P2DuckLeft0);
        P2.addCostume(P2DuckLeft1);
        P2.addCostume(P2DuckLeft2);
        P2.addCostume(P2DuckLeft3);
        P2.addCostume(P2DuckLeft4);

        //add right kick costumes to P2 Starting at position 37 ending at position 41
        P2.addCostume(P2KickLeft0);
        P2.addCostume(P2KickLeft1);
        P2.addCostume(P2KickLeft2);
        P2.addCostume(P2KickLeft3);
        P2.addCostume(P2KickLeft4);

        //add right Jump costumes to P2 starting at position 42 ending at position 47
        P2.addCostume(P2JumpLeft0);
        P2.addCostume(P2JumpLeft1);
        P2.addCostume(P2JumpLeft2);
        P2.addCostume(P2JumpLeft3);
        P2.addCostume(P2JumpLeft4);
        P2.addCostume(P2JumpLeft5);

        //add right Movement costumes to P2 starting at position 48-50 ending at position 51-53
        P2.addCostume(P2MoveLeft0);
        P2.addCostume(P2MoveLeft1);
        P2.addCostume(P2MoveLeft2);
        P2.addCostume(P2MoveLeft3);
        P2.addCostume(P2MoveLeft4);
        P2.addCostume(P2MoveLeft5);

        //add right Slow Kick costumes to P2 starting at position 54 ending at position 61
        P2.addCostume(P2SlowLeft0);
        P2.addCostume(P2SlowLeft1);
        P2.addCostume(P2SlowLeft2);
        P2.addCostume(P2SlowLeft3);
        P2.addCostume(P2SlowLeft4);
        P2.addCostume(P2SlowLeft5);
        P2.addCostume(P2SlowLeft6);
        P2.addCostume(P2SlowLeft7);

        //add right Jump Kick costumes to P2 starting at position 62 ending at 69
        P2.addCostume(P2JumpKick0);
        P2.addCostume(P2JumpKick1);
        P2.addCostume(P2JumpKick2);
        P2.addCostume(P2JumpKick3);
        P2.addCostume(P2JumpKick4);
        P2.addCostume(P2JumpKick5);
        P2.addCostume(P2JumpKick6);
        P2.addCostume(P2JumpKick6);

        //add right Jump Kick costumes to P2 starting at position 70 ending at 77
        P2.addCostume(P2JumpKickLeft0);
        P2.addCostume(P2JumpKickLeft1);
        P2.addCostume(P2JumpKickLeft2);
        P2.addCostume(P2JumpKickLeft3);
        P2.addCostume(P2JumpKickLeft4);
        P2.addCostume(P2JumpKickLeft5);
        P2.addCostume(P2JumpKickLeft6);
        P2.addCostume(P2JumpKickLeft6);

        //add right ShiftBlast costumes to P2 starting at position 78 ending at 91
        P2.addCostume(P2ShiftBlast0);
        P2.addCostume(P2ShiftBlast1);
        P2.addCostume(P2ShiftBlast2);
        P2.addCostume(P2ShiftBlast3);
        P2.addCostume(P2ShiftBlast4);
        P2.addCostume(P2ShiftBlast5);
        P2.addCostume(P2ShiftBlast6);
        P2.addCostume(P2ShiftBlast7);
        P2.addCostume(P2ShiftBlast8);
        P2.addCostume(P2ShiftBlast9);
        P2.addCostume(P2ShiftBlast10);
        P2.addCostume(P2ShiftBlast11);
        P2.addCostume(P2ShiftBlast12);
        P2.addCostume(P2ShiftBlast12);

        //add left P2ShiftBlast costumes to P2 starting at position 92 ending at 105
        P2.addCostume(P2ShiftBlastLeft0);
        P2.addCostume(P2ShiftBlastLeft1);
        P2.addCostume(P2ShiftBlastLeft2);
        P2.addCostume(P2ShiftBlastLeft3);
        P2.addCostume(P2ShiftBlastLeft4);
        P2.addCostume(P2ShiftBlastLeft5);
        P2.addCostume(P2ShiftBlastLeft6);
        P2.addCostume(P2ShiftBlastLeft7);
        P2.addCostume(P2ShiftBlastLeft8);
        P2.addCostume(P2ShiftBlastLeft9);
        P2.addCostume(P2ShiftBlastLeft10);
        P2.addCostume(P2ShiftBlastLeft11);
        P2.addCostume(P2ShiftBlastLeft12);
        P2.addCostume(P2ShiftBlastLeft12);

        //add right GroundPound costumes to P2 starting at position 106 ending at 117
        P2.addCostume(P2GroundPound0);
        P2.addCostume(P2GroundPound1);
        P2.addCostume(P2GroundPound2);
        P2.addCostume(P2GroundPound3);
        P2.addCostume(P2GroundPound4);
        P2.addCostume(P2GroundPound5);
        P2.addCostume(P2GroundPound6);
        P2.addCostume(P2GroundPound7);
        P2.addCostume(P2GroundPound8);
        P2.addCostume(P2GroundPound9);
        P2.addCostume(P2GroundPound10);
        P2.addCostume(P2GroundPound11);


        //add left P2GroundPound costumes to P2 starting at position 118 ending at 129
        P2.addCostume(P2GroundPoundLeft0);
        P2.addCostume(P2GroundPoundLeft1);
        P2.addCostume(P2GroundPoundLeft2);
        P2.addCostume(P2GroundPoundLeft3);
        P2.addCostume(P2GroundPoundLeft4);
        P2.addCostume(P2GroundPoundLeft5);
        P2.addCostume(P2GroundPoundLeft6);
        P2.addCostume(P2GroundPoundLeft7);
        P2.addCostume(P2GroundPoundLeft8);
        P2.addCostume(P2GroundPoundLeft9);
        P2.addCostume(P2GroundPoundLeft10);
        P2.addCostume(P2GroundPoundLeft11);

        //add right Slide costumes to P2 starting at position 130 ending at 139
        P2.addCostume(P2Slide0);
        P2.addCostume(P2Slide1);
        P2.addCostume(P2Slide2);
        P2.addCostume(P2Slide3);
        P2.addCostume(P2Slide4);
        P2.addCostume(P2Slide5);
        P2.addCostume(P2Slide6);
        P2.addCostume(P2Slide7);
        P2.addCostume(P2Slide8);
        P2.addCostume(P2Slide8);

        //add left P2Slide costumes to P2 starting at position 140 ending at 149
        P2.addCostume(P2SlideLeft0);
        P2.addCostume(P2SlideLeft1);
        P2.addCostume(P2SlideLeft2);
        P2.addCostume(P2SlideLeft3);
        P2.addCostume(P2SlideLeft4);
        P2.addCostume(P2SlideLeft5);
        P2.addCostume(P2SlideLeft6);
        P2.addCostume(P2SlideLeft7);
        P2.addCostume(P2SlideLeft8);
        P2.addCostume(P2SlideLeft8);

        //add right AirDash costumes to P2 starting at position 150 ending at 159
        P2.addCostume(P2AirDash0);
        P2.addCostume(P2AirDash1);
        P2.addCostume(P2AirDash2);
        P2.addCostume(P2AirDash3);
        P2.addCostume(P2AirDash4);
        P2.addCostume(P2AirDash5);
        P2.addCostume(P2AirDash6);
        P2.addCostume(P2AirDash7);
        P2.addCostume(P2AirDash0);
        P2.addCostume(P2AirDash0);



        //add left P2AirDash costumes to P2 starting at position 160 ending at 169
        P2.addCostume(P2AirDashLeft0);
        P2.addCostume(P2AirDashLeft1);
        P2.addCostume(P2AirDashLeft2);
        P2.addCostume(P2AirDashLeft3);
        P2.addCostume(P2AirDashLeft4);
        P2.addCostume(P2AirDashLeft5);
        P2.addCostume(P2AirDashLeft6);
        P2.addCostume(P2AirDashLeft7);
        P2.addCostume(P2AirDashLeft0);
        P2.addCostume(P2AirDashLeft0);

        //add right Konami costumes to P2 starting at position 170 ending at 180
        P2.addCostume(P2Konami0);
        P2.addCostume(P2Konami1);
        P2.addCostume(P2Konami2);
        P2.addCostume(P2Konami3);
        P2.addCostume(P2Konami4);
        P2.addCostume(P2Konami5);
        P2.addCostume(P2Konami6);
        P2.addCostume(P2Konami7);
        P2.addCostume(P2Konami8);
        P2.addCostume(P2Konami9);
        P2.addCostume(P2Konami10);



        //add left Konami costumes to P2 starting at position 181 ending at 191
        P2.addCostume(P2KonamiLeft0);
        P2.addCostume(P2KonamiLeft1);
        P2.addCostume(P2KonamiLeft2);
        P2.addCostume(P2KonamiLeft3);
        P2.addCostume(P2KonamiLeft4);
        P2.addCostume(P2KonamiLeft5);
        P2.addCostume(P2KonamiLeft6);
        P2.addCostume(P2KonamiLeft7);
        P2.addCostume(P2KonamiLeft8);
        P2.addCostume(P2KonamiLeft9);
        P2.addCostume(P2KonamiLeft10);

        //make P2 look left first
        P2.setLookingLeft(true);

        addKeyListener(new TAdapter());
        setFocusable(true);

        //Set positions of health bars
        L_HealthRect = new Rectangle(0,16,64,480);
        R_HealthRect= new Rectangle(boardWidth-64,16,64,480);

        //start the timer to activate ticks
        timer = new Timer(DELAY, this);
        timer.start();

    }

    //reset the canvas on game over
    private void reset(){
        P1.setHealth(160);
        P2.setHealth(160);
        P1.setX(96);
        P2.setX(boardWidth - 576);
        P1.setLookingLeft(false);
        P2.setLookingLeft(true);
        P1.reset();
        P2.reset();
        HitCool = 0;
    }

    //paint a specific component
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //clear JPanel
        doDraw(g);
        Toolkit.getDefaultToolkit().sync();
    }

    //draw images to the canvas
    private void doDraw(Graphics g){
        Graphics2D G2D = (Graphics2D) g;
        //draw stage
        G2D.drawImage(stage.getImage(),0,0, this);

        //draw the health bar for p1
        G2D.setColor(leftColor);
        G2D.fillRect((int) L_HealthRect.getX(), (int) L_HealthRect.getY()+((160-P1.getHealth())*3),(int) L_HealthRect.getWidth(), P1.getHealth()*3);

        //draw the health bar for p2
        G2D.setColor(rightColor);
        G2D.fillRect((int) R_HealthRect.getX(), (int) R_HealthRect.getY()+((160-P2.getHealth())*3),(int) R_HealthRect.getWidth(), P2.getHealth()*3);

        //draw character sprites
        G2D.drawImage(P1.getImage(), P1.getX(), P1.getY(), this);
        G2D.drawImage(P2.getImage(), P2.getX(), P2.getY(), this);

        //draw the rectangles containing the health bars
        G2D.drawImage(L_Health.getImage(),0,0, this);
        G2D.drawImage(R_Health.getImage(),boardWidth - 64,0, this);

        //draw the game over image if applicable
        if(gameOverCool>0){
            G2D.drawImage(GameOver.getImage(),  boardWidth/2 - 96, 264, this);
        }
    }

    // detect a collision between a hitbox and a hurtbox
    private void detectCollisions() {
        //check when a p1 hitbox collides with p2
        Rectangle p1hitbox = P1.getActiveHitbox();
        if (p1hitbox != null) {
            boolean collision = P2.collidesWith(p1hitbox);
            if (collision) {
                handleCollision(P2);
            }
        }

        //check when a p2 hitbox collides with p1
        Rectangle p2hitbox = P2.getActiveHitbox();
        if (p2hitbox != null) {
          boolean collision = P1.collidesWith(p2hitbox);
          if (collision) {
              handleCollision(P1);
          }
        }
    }

    // handle collisions and deal damage
    private void handleCollision(sprite s) {
        System.out.println("====Collision detected====");
        //TODO: deal damage
        if(s.getPlayerNum() == 1) {
            //if P1 takes a hit
            HitCool = 6;
            boolean isDucking = false;
            if(P1.getCurrentAction() == "Ducking")isDucking = true;
            if (P2.getCurrentAction() == "Kicking") {

                //P1 takes kick damage
                if(isDucking){
                    P1.setHealth(P1.getHealth()-(KICK_DAMAGE/2));
                }
                else {
                    P1.setHealth(P1.getHealth() - KICK_DAMAGE);
                }
                System.out.println(P1.getHealth());
            }
            if (P2.getCurrentAction() == "SlowKicking") { //P1 takes slowkick damage
                if(isDucking){
                    P1.setHealth(P1.getHealth()-(SLOWKICK_DAMAGE/2));
                }
                else {
                    P1.setHealth(P1.getHealth() - SLOWKICK_DAMAGE);
                }
                System.out.println(P1.getHealth());

            }
            if (P2.getCurrentAction() == "JumpKicking") { //P1 takes jumpkick damage
                if(isDucking){
                    P1.setHealth(P1.getHealth()-(JUMPKICK_DAMAGE/2));
                }
                else {
                    P1.setHealth(P1.getHealth() - JUMPKICK_DAMAGE);
                }

                System.out.println(P1.getHealth());

            }
            if(P2.getCurrentAction() == "GroundPounding"){
                if(isDucking){
                    P1.setHealth(P1.getHealth()-(GROUND_DAMAGE/2));
                }
                else {
                    P1.setHealth(P1.getHealth() - GROUND_DAMAGE);
                }
                System.out.println(P1.getHealth());

            }
            if(P2.getCurrentAction() == "ShiftBlasting"){
                if(isDucking){
                    P1.setHealth(P1.getHealth()-(SHIFTBLAST_DAMAGE/2));
                }
                else {
                    P1.setHealth(P1.getHealth() - SHIFTBLAST_DAMAGE);
                }
                System.out.println(P1.getHealth());

            }
            if (P2.getCurrentAction() == "Sliding"){
                if(isDucking){
                    P1.setHealth(P1.getHealth()-(SLIDE_DAMAGE/2));
                }
                else {
                    P1.setHealth(P1.getHealth() - SLIDE_DAMAGE);
                }
                System.out.println(P1.getHealth());

            }
            if(P2.getCurrentAction() == "konamiForm"){
                if(isDucking){
                    P1.setHealth(P1.getHealth()-(KONAMI_DAMAGE/2));
                }
                else {
                    P1.setHealth(P1.getHealth() - KONAMI_DAMAGE);
                }
                System.out.println(P1.getHealth());

            }
        }
        if(s.getPlayerNum() == 2) { //if P2 takes a hit
            HitCool = 6;
            boolean isDucking = false;
            if(P2.getCurrentAction() == "Ducking")isDucking = true;
            if (P1.getCurrentAction() == "Kicking") { //P2 takes kick damage
                if(isDucking){
                    P2.setHealth(P2.getHealth()-(KICK_DAMAGE/2));
                }
                else {
                    P2.setHealth(P2.getHealth() - KICK_DAMAGE);
                }
                System.out.println(P2.getHealth());

            }
            if (P1.getCurrentAction() == "SlowKicking") { //P2 takes slowkick damage
                if(isDucking){
                    P2.setHealth(P2.getHealth()-(SLOWKICK_DAMAGE/2));
                }
                else {
                    P2.setHealth(P2.getHealth() - SLOWKICK_DAMAGE);
                }
                System.out.println(P2.getHealth());

            }
            if (P1.getCurrentAction() == "JumpKicking") { //P2 takes jumpkick damage
                if(isDucking){
                    P2.setHealth(P2.getHealth()-(JUMPKICK_DAMAGE/2));
                }
                else {
                    P2.setHealth(P2.getHealth() - JUMPKICK_DAMAGE);
                }
                System.out.println(P2.getHealth());

            }
            if(P1.getCurrentAction() == "GroundPounding"){
                if(isDucking){
                    P2.setHealth(P2.getHealth()-(GROUND_DAMAGE/2));
                }
                else {
                    P2.setHealth(P2.getHealth() - GROUND_DAMAGE);
                }
                System.out.println(P2.getHealth());

            }
            if(P1.getCurrentAction() == "ShiftBlasting"){
                if(isDucking){
                    P2.setHealth(P2.getHealth()-(SHIFTBLAST_DAMAGE/2));
                }
                else {
                    P2.setHealth(P2.getHealth() - SHIFTBLAST_DAMAGE);
                }
                System.out.println(P2.getHealth());

            }
            if (P1.getCurrentAction() == "Sliding"){
                if(isDucking){
                    P2.setHealth(P2.getHealth()-(SLIDE_DAMAGE/2));
                }
                else {
                    P2.setHealth(P2.getHealth() - SLIDE_DAMAGE);
                }
                System.out.println(P2.getHealth());

            }
            if(P1.getCurrentAction() == "konamiForm"){
                if(isDucking){
                    P2.setHealth(P2.getHealth()-(KONAMI_DAMAGE/2));
                }
                else {
                    P2.setHealth(P2.getHealth() - KONAMI_DAMAGE);
                }
                System.out.println(P2.getHealth());

            }
        }
    }

    // per-tick event handling
    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: this is where all actions performed per tick are implemented
        if(gameOverCool >0){
            gameOverCool--;
        }
        else {
            P1.update();
            P2.update();
            if (P1.getHealth() <= 0 || P2.getHealth() <= 0) { //end game if health drops below 0
                System.out.println("Game Over");
                System.out.println(P1.getHealth());
                System.out.println(P2.getHealth());
                gameOverCool = 20;
                reset();
            }
            repaint(); //update images on each frame
            if (HitCool > 0) {
                HitCool--;
            } else {
                detectCollisions(); //detect when a collision between a hitbox and a hurtbox occurs
            }
        }
    }

    //class for reading in keyboard information
    private class TAdapter extends KeyAdapter{
        @Override
        public void keyReleased(KeyEvent e){
            P1.keyReleased(e);
            P2.keyReleased(e);
        }
        @Override
        public void keyPressed(KeyEvent e) {
            P1.keyPressed(e);
            P2.keyPressed(e);
        }
    }
}

