package squaresWithLegs.game;

import java.awt.*;
import java.awt.desktop.SystemSleepEvent;
import java.awt.event.KeyEvent;
import java.lang.reflect.GenericDeclaration;
import java.util.ArrayList;
import java.util.PrimitiveIterator;
import javax.swing.ImageIcon;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

public class sprite {
    //The dimensions of the characters' hurtboxes
    private static int HURTBOX_WIDTH = 113;
    private static int HURTBOX_HEIGHT = 120;
    //The positions of the characters' hurtboxes relative to the sprite location
    private static int HURTBOX_OFFSET_X_L = 208;
    private static int HURTBOX_OFFSET_X_R = 192;
    private static int HURTBOX_OFFSET_Y = 176;

    //The left and right bounds of the screen
    private int SCREEN_BOUND_L = -HURTBOX_OFFSET_X_R;
    private int SCREEN_BOUND_R = 1920 + 512 - HURTBOX_OFFSET_X_R - HURTBOX_WIDTH;

    //Positions of the hurtbox during various actions relative to its position in the regular standing animation
    //Positive xs are in the direction facing
    //Positive ys are down
    private static int[] DUCK_OFFSETS = {0, 80, 48, 16}; // y values
    private static int[] JUMP_OFFSETS = {0, 32, -32, -80, -48}; // y values
    private static int[] FAST_KICK_OFFSETS = {0, 16, 32, 16}; // x values
    private static int[][] SLOW_KICK_OFFSETS = {{0, 0}, {0, 32}, {32, 32}, {64, 32}, {96, 32}, {64, 32}, {32, 32}};
    //x and y values
    private static int[][] JUMP_KICK_OFFSETS = {{0,0},{0,32},{0,-48},{32,-96},{80,-64},{112,-16},{0,0}};
    //x and y values
    private static int[][] SHIFT_BLAST_OFFSETS = {{0,0},{0,0},{-176,0},{-176,0},{-176,0},{-176,0},{-176,0},{-176,0},{-176,0},{160,160},{160,0},{160,0},{0,0},{0,0}};
    //x and y values
    private static int[] GROUND_POUND_OFFSETS = {0, 32, -32, -80, -48, 144, 128, 96, 80, 64, 32,}; // y values
    private static int[][] SLIDE_OFFSETS = {{0,0},{0,32},{48,48},{96,64},{128,64},{176,64},{208,48},{208,16},{0,0},{0,0}};
    //x and y values
    private static int[][] AIR_DASH_OFFSETS = {{0,0},{0,32},{0,-32},{-32,-80},{-64,-80},{-112,0},{-112,64},{-96,32},{0,0}, {0,0}, {0,0}};
    //x and y values
    private static int[] KONAMI_OFFSETS = {0, 32, -32, -80, -48, 0,0,0,0,0,0,0,0,0,0}; // y values

    //Positions of the fast kick hitbox relative to the hurtbox's position
    private static int FAST_KICK_OFFSET_X_L = 80;
    private static int FAST_KICK_OFFSET_X_R = 145;
    private static int FAST_KICK_OFFSET_Y = 112;
    private static int FAST_KICK_WIDTH = 63;
    private static int FAST_KICK_LENGTH = 32;

    //Positions of the slow kick hitbox relative to the hurtbox's position
    private static int SLOW_KICK_OFFSET_X_L = 112;
    private static int SLOW_KICK_OFFSET_X_R = 154;
    private static int SLOW_KICK_OFFSET_Y = 64;
    private static int SLOW_KICK_WIDTH = 80;
    private static int SLOW_KICK_LENGTH = 32;

    //Positions of the jump kick hitbox relative to the hurtbox's position
    private static int JUMP_KICK_OFFSET_X_L = SLOW_KICK_OFFSET_X_L + 32;
    private static int JUMP_KICK_OFFSET_X_R = SLOW_KICK_OFFSET_X_R + 32;
    private static int JUMP_KICK_OFFSET_Y = SLOW_KICK_OFFSET_Y + 32;
    private static int JUMP_KICK_WIDTH = SLOW_KICK_WIDTH;
    private static int JUMP_KICK_LENGTH = SLOW_KICK_LENGTH;

    //Positions of the shift blast hitbox relative to the hurtbox's position
    private static int SHIFT_BLAST_OFFSET_X_L = 362;
    private static int SHIFT_BLAST_OFFSET_X_R = 320;
    private static int SHIFT_BLAST_OFFSET_Y = SLOW_KICK_OFFSET_Y - 80;
    private static int SHIFT_BLAST_WIDTH = 160;
    private static int SHIFT_BLAST_LENGTH = 160;

    //Positions of the ground pound hitbox relative to the hurtbox's position
    private static int GROUND_POUND_OFFSET_X_L = 96;
    private static int GROUND_POUND_OFFSET_X_R = -96;
    private static int GROUND_POUND_OFFSET_Y = -32;
    private static int GROUND_POUND_WIDTH = 304;
    private static int GROUND_POUND_LENGTH = 112;

    //Positions of the slide kick hitbox relative to the hurtbox's position
    private static int SLIDE_OFFSET_X_L = 112;
    private static int SLIDE_OFFSET_X_R = 154;
    private static int SLIDE_OFFSET_Y = 64;
    private static int SLIDE_WIDTH = 80;
    private static int SLIDE_LENGTH = 32;

    private static int[] SLOW_KICK_FRAME_DATA = {4, 6};
    //frame count for when the hitbox is active and when the hitbox is inactive
    private static int[] FAST_KICK_FRAME_DATA = {3, 4}; //hitbox active, hitbox inactive
    private static int[] JUMP_KICK_FRAME_DATA = {5, 6};
    private static int[] SHIFT_BLAST_FRAME_DATA = {9, 11};
    private static int[] GROUND_POUND_FRAME_DATA = {8, 10};
    private static int[] SLIDE_FRAME_DATA = {5, 6};

    //start costumes for each animation
    private static int FAST_KICK_START_L = 37;
    private static int FAST_KICK_START_R = 6;
    private static int SLOW_KICK_START_L = 54;
    private static int SLOW_KICK_START_R = 23;
    private static int DUCK_START_L = 32;
    private static int DUCK_START_R = 1;
    private static int JUMP_START_L = 42;
    private static int JUMP_START_R = 11;
    private static int LEFT_MOVE_START_L = 48;
    private static int RIGHT_MOVE_START_R = 17;
    private static int JUMP_KICK_START_L = 70;
    private static int JUMP_KICK_START_R = 62;
    private static int SHIFT_BLAST_START_L = 92;
    private static int SHIFT_BLAST_START_R = 78;
    private static int GROUND_POUND_START_L = 118;
    private static int GROUND_POUND_START_R = 106;
    private static int SLIDE_START_L = 140;
    private static int SLIDE_START_R = 130;
    private static int AIR_DASH_START_L = 160;
    private static int AIR_DASH_START_R = 150;
    private static int KONAMI_START_L = 181;
    private static int KONAMI_START_R = 170;

    //end costumes for each animation
    private static int FAST_KICK_END_L = 41;
    private static int FAST_KICK_END_R = 10;
    private static int SLOW_KICK_END_L = 61;
    private static int SLOW_KICK_END_R = 30;
    private static int DUCK_END_L = 36;
    private static int DUCK_END_R = 5;
    private static int JUMP_END_L = 47;
    private static int JUMP_END_R = 16;
    private static int LEFT_MOVE_END_L = 50;
    private static int RIGHT_MOVE_END_R = 19;
    private static int JUMP_KICK_END_L = 77;
    private static int JUMP_KICK_END_R = 69;
    private static int SHIFT_BLAST_END_L = 105;
    private static int SHIFT_BLAST_END_R = 91;
    private static int GROUND_POUND_END_L = 129;
    private static int GROUND_POUND_END_R = 117;
    private static int SLIDE_END_L = 149;
    private static int SLIDE_END_R = 139;
    private static int AIR_DASH_END_L = 169;
    private static int AIR_DASH_END_R = 159;
    private static int KONAMI_END_L = 191;
    private static int KONAMI_END_R = 180;

    //default costumes
    private static int RIGHT_DEFAULT = 0;
    private static int LEFT_DEFAULT = 31;

    //position of the sprite
    private int x;
    private int y;
    private ArrayList<costume> costumes = new ArrayList<costume>();
    private Image image;
    private int costumeNum = 0;
    private int MOVE_SPEED = 16; //how far a character moves on each move action
    // also affects how far a character moves when sliding, jumpkicking, airdashing, or shiftblasting

    //for each action, there is a boolean checking whether the character is doing that action
    private boolean Ducking = false;
    private boolean Jumping = false;
    private boolean Kicking = false;
    private boolean MovingLeft = false;
    private boolean MovingRight = false;
    private boolean SlowKicking = false;
    private boolean JumpKicking = false;
    private boolean ShiftBlasting = false;
    private boolean GroundPounding = false;
    private boolean Sliding = false;
    private boolean AirDashing = false;
    private boolean Konaming = false;


    private boolean ActionLocked = false;
    private Boolean LookingLeft = false;

    private int JumpKey;
    private int DuckKey;
    private int LeftKey;
    private int RightKey;
    private int FastKey;
    private int SlowKey;

    private int[] konamiSeq;
    private boolean possibleKonami = false;
    private int[] afterJump = new int[10];
    private int konamiIter = 0;
    private boolean konamiForm = false;
    private int konamiFormCounter = 40;
    private int konamiCooldown = 0;

    private Rectangle activeHitbox;
    private Rectangle hurtbox;

    private int playerNum;
    private int health = 160;

    //constuctor for sprite given costume and position data
    public sprite(costume c, int initx, int inity, int Jump, int Duck, int Left, int Right, int Fast, int Slow, int pNum, int ScreenLen) {
        addCostume(c);
        setCostume(0);
        initSprite(initx,inity,Jump,Duck,Left,Right,Fast,Slow, pNum, ScreenLen);
    }

    //initializer
    private void initSprite(int bx, int by, int Jump, int Duck, int Left, int Right, int Fast, int Slow, int pNum, int ScreenL) {
        ImageIcon ii = new ImageIcon(costumes.get(0).Location);
        image = ii.getImage();
        x = bx;
        y = by;

        SCREEN_BOUND_R = ScreenL - HURTBOX_OFFSET_X_R - HURTBOX_WIDTH;

        int x_offset;
        if(LookingLeft) {
            x_offset = HURTBOX_OFFSET_X_L;
        } else {
            x_offset = HURTBOX_OFFSET_X_R;
        }
        hurtbox = new Rectangle(x + x_offset, y + HURTBOX_OFFSET_Y, HURTBOX_WIDTH, HURTBOX_HEIGHT);

        JumpKey = Jump;
        DuckKey = Duck;
        LeftKey = Left;
        RightKey = Right;
        FastKey = Fast;
        SlowKey = Slow;

        konamiSeq = new int[]{ JumpKey, DuckKey, DuckKey, LeftKey, RightKey, LeftKey, RightKey, SlowKey, FastKey};


        playerNum = pNum;
    }

    //adds a costume to the array of costumes available to a sprite at the end
    public void addCostume(costume c){
        costumes.add(c);
        System.out.println("Costume " + c.toString() + " added");
    }

    //changes to the next costume in the array, loops over if end is reached
    public void nextCostume() {
        image = costumes.get(costumeNum).image;
        if (costumeNum < costumes.size() - 1) costumeNum++;
        else costumeNum = 0;
    }

    //set a costume for a given position in the array
    public void setCostume(int a){
        image = costumes.get(a).image;
    }

    //returns the costume at the given index
    public costume getCostume(){
        return costumes.get(costumeNum);
    }

    //returns the current image
    public Image getImage() {
        return image;
    }

    //get X value from sprite
    public int getX() {
        return x;
    }

    //get Y value from sprite
    public int getY() {
        return y;
    }

    //turns off all actions and centers hurtboxes, nulls hitboxes
    public void reset(){
        Ducking = false;
        Jumping = false;
        Kicking = false;
        MovingLeft = false;
        MovingRight = false;
        SlowKicking = false;
        JumpKicking = false;
        ShiftBlasting = false;
        GroundPounding = false;
        Sliding = false;
        AirDashing = false;
        Konaming = false;
        ActionLocked = false;

        int hurtbox_x_offset = 0;

        if (LookingLeft) {
            //hurtbox_x_offset *= -1; //reverse the direction if sprite is looking left
            hurtbox_x_offset += HURTBOX_OFFSET_X_L;
        } else {
            hurtbox_x_offset += HURTBOX_OFFSET_X_R;
        }

        hurtbox.setLocation(x +hurtbox_x_offset,y);
        activeHitbox = null;
    }

    //returns the current action of a sprite in string format
    public String getCurrentAction(){ //return current action as a string
        if(Kicking){
            return "Kicking";
        }
        if(JumpKicking){
            return "JumpKicking";
        }
        if(SlowKicking){
            return "SlowKicking";
        }
        if(Ducking){
            return "Ducking";
        }
        if(MovingLeft){
            return "MovingLeft";
        }
        if(MovingRight){
            return "MovingRight";
        }
        if(ShiftBlasting){
            return "ShiftBlasting";
        }
        if(GroundPounding){
            return "GroundPounding";
        }
        if(Sliding){
            return "Sliding";
        }
        if(Konaming){
            return "Konaming";
        }
        if(konamiForm){
            return "konamiForm";
        }
        return "Idle";
    }

    //set a sprite's X position
    public void setX( int newx) {
        x = newx;
    }

    //set a sprite's Y position
    public void setY( int newy) {
        y = newy;
    }

    //change move speed
    public void setMoveSpeed(int speed){
        MOVE_SPEED = speed;
    }

    //check if looking left
    public boolean getLookingLeft(){
        return LookingLeft;
    }

    //set if sprite should look left
    public void setLookingLeft(Boolean look){
        LookingLeft = look;
    }

    //performs all per-tick actions for a sprite
    public void update(){
        int hurtbox_x_offset = 0;
        int hurtbox_y_offset = HURTBOX_OFFSET_Y;

        if(konamiForm){ //perform actions for konami attack

            nextCostume();

            //move twice as fast in konami form
            if(MovingLeft){
                if (x - 2*MOVE_SPEED >= SCREEN_BOUND_L){
                    x -=  2* MOVE_SPEED;
                }
                MovingLeft = false;
                costumeNum = KONAMI_END_L -3;
            }
            if(MovingRight){
                if (x + 2*MOVE_SPEED <= SCREEN_BOUND_R) {
                    x += 2 * MOVE_SPEED;
                }
                MovingRight = false;
                costumeNum = KONAMI_END_R -3;
            }

            //increase the size of the hurtbox as the character size increases
            hurtbox = new Rectangle(x, y, HURTBOX_WIDTH * 3, HURTBOX_HEIGHT *3);
            if (LookingLeft) {
                hurtbox_x_offset *= -1; //reverse the direction if sprite is looking left
                hurtbox_x_offset += 96;
            } else {
                hurtbox_x_offset += 80;
            }

            hurtbox.setLocation(x + hurtbox_x_offset, y + 32);

            //set the hitbox konami form
            activeHitbox = new Rectangle(x, y, HURTBOX_WIDTH * 3, HURTBOX_HEIGHT *3);
            activeHitbox.setLocation(x + hurtbox_x_offset, y + 32);

            //set the end animation for konami
            if(konamiFormCounter == 3){
                costumeNum = KONAMI_END_R - 2;
                if(LookingLeft)costumeNum = KONAMI_END_L - 2;
            }
            if(konamiFormCounter == 2){
                costumeNum = KONAMI_END_R - 1;
                if(LookingLeft)costumeNum = KONAMI_END_L - 1;
            }
            if(konamiFormCounter == 1 ){
                costumeNum = KONAMI_END_R;
                if(LookingLeft)costumeNum = KONAMI_END_L;
                konamiForm = false;
                ActionLocked = false;
                konamiFormCounter = 40;
                HURTBOX_WIDTH = 113;
                HURTBOX_HEIGHT = 120;
                konamiCooldown = 100;

                int x_offset;
                if(LookingLeft) {
                    x_offset = HURTBOX_OFFSET_X_L;
                } else {
                    x_offset = HURTBOX_OFFSET_X_R;
                }
                activeHitbox = null;

                hurtbox = new Rectangle(x + x_offset, y + HURTBOX_OFFSET_Y, HURTBOX_WIDTH, HURTBOX_HEIGHT);
            }
            konamiFormCounter--;

            if(LookingLeft){
                costumeNum = KONAMI_END_L -3;
            }
            else{
                costumeNum = KONAMI_END_R -3;
            }

        }
        //the player can do normal actions if it is not in konami form
        else {
            konamiCooldown--;
            if (Ducking) { //perform a duck
                nextCostume();
                if (costumeNum == DUCK_END_L || costumeNum == DUCK_END_R) {
                    Ducking = false;
                    ActionLocked = false;
                }

                int start_costume;
                if (LookingLeft) {
                    start_costume = DUCK_START_L;
                } else {
                    start_costume = DUCK_START_R;
                }
                //set hurtbox for ducking
                int duck_offset_index = costumeNum - start_costume - 1;
                hurtbox_y_offset += DUCK_OFFSETS[duck_offset_index];
            } else if (Jumping) { //perform a jump
                nextCostume();
                if (costumeNum == JUMP_END_L || costumeNum == JUMP_END_R) {
                    Jumping = false;
                    ActionLocked = false;
                }

                int start_costume;
                if (LookingLeft) {
                    start_costume = JUMP_START_L;
                } else {
                    start_costume = JUMP_START_R;
                }

                //set hurtbox for jump
                int jump_offset_index = costumeNum - start_costume - 1;
                hurtbox_y_offset += JUMP_OFFSETS[jump_offset_index];
            } else if (Kicking) { //perform a fast kick
                nextCostume();
                int start_costume;
                int end_costume;

                if (LookingLeft) {
                    start_costume = FAST_KICK_START_L;
                    end_costume = FAST_KICK_END_L;
                } else {
                    start_costume = FAST_KICK_START_R;
                    end_costume = FAST_KICK_END_R;
                }

                if (costumeNum == end_costume) {
                    Kicking = false;
                    ActionLocked = false;
                }

                //activate the hitbox for the fast kick
                if (costumeNum >= start_costume + FAST_KICK_FRAME_DATA[0]
                        && costumeNum < start_costume + FAST_KICK_FRAME_DATA[1]) {

                    int x_offset;

                    if (LookingLeft) {
                        x_offset = -1 * FAST_KICK_OFFSET_X_L;
                    } else {
                        x_offset = FAST_KICK_OFFSET_X_R;
                    }
                    activeHitbox = new Rectangle((int) (hurtbox.getX() + x_offset),
                            (int) (hurtbox.getY() + FAST_KICK_OFFSET_Y), FAST_KICK_WIDTH, FAST_KICK_LENGTH);

                } //reactivate the hitbox on every frame to move as the character moves

                if (costumeNum == start_costume + FAST_KICK_FRAME_DATA[1]) {
                    activeHitbox = null;
                }

                //reset the hurtbox for a fast kick
                int kick_offset_index = costumeNum - start_costume - 1;
                hurtbox_x_offset += FAST_KICK_OFFSETS[kick_offset_index];
            } else if (SlowKicking) { //perform a slowkick
                nextCostume();
                int start_costume;
                int end_costume;

                if (LookingLeft) {
                    start_costume = SLOW_KICK_START_L;
                    end_costume = SLOW_KICK_END_L;
                } else {
                    start_costume = SLOW_KICK_START_R;
                    end_costume = SLOW_KICK_END_R;
                }

                if (costumeNum == end_costume) {
                    SlowKicking = false;
                    ActionLocked = false;
                }

                //activate the hitbox for the slow kick
                if (costumeNum >= start_costume + SLOW_KICK_FRAME_DATA[0]
                        && costumeNum < start_costume + SLOW_KICK_FRAME_DATA[1]) {
                    int x_offset;

                    if (LookingLeft) {
                        x_offset = -1 * SLOW_KICK_OFFSET_X_L;
                    } else {
                        x_offset = SLOW_KICK_OFFSET_X_R;
                    }

                    activeHitbox = new Rectangle((int) (hurtbox.getX() + x_offset),
                            (int) (hurtbox.getY() + SLOW_KICK_OFFSET_Y), SLOW_KICK_WIDTH, SLOW_KICK_LENGTH);
                } //reactivate the hitbox on every frame to move as the character moves

                if (costumeNum == start_costume + SLOW_KICK_FRAME_DATA[1]) {
                    activeHitbox = null;
                }

                //reactivate the hurtbox for the slow kick
                int slow_kick_offset_index = costumeNum - start_costume - 1;
                hurtbox_x_offset += SLOW_KICK_OFFSETS[slow_kick_offset_index][0];
                hurtbox_y_offset += SLOW_KICK_OFFSETS[slow_kick_offset_index][1];
            } else if (MovingRight) { //move right
                nextCostume();
                if (costumeNum == RIGHT_MOVE_END_R) {
                    MovingRight = false;
                    ActionLocked = false;
                }

                if (x + MOVE_SPEED <= SCREEN_BOUND_R) {
                    x += MOVE_SPEED;
                }

            } else if (MovingLeft) { //move left
                nextCostume();
                if (costumeNum == LEFT_MOVE_END_L) {
                    MovingLeft = false;
                    ActionLocked = false;
                }

                if (x - MOVE_SPEED >= SCREEN_BOUND_L) {
                    x -= MOVE_SPEED;
                }
            } else if (JumpKicking) { //perform a jumpkick
                nextCostume();

                int start_costume;
                int end_costume;

                if (LookingLeft) {
                    start_costume = JUMP_KICK_START_L;
                    end_costume = JUMP_KICK_END_L;
                } else {
                    start_costume = JUMP_KICK_START_R;
                    end_costume = JUMP_KICK_END_R;
                }

                //move character at the end of the jump kick to where the character lands
                if (costumeNum == end_costume) {
                    if (!LookingLeft) {
                        x += (MOVE_SPEED * 7);
                    }
                    if (LookingLeft) x -= (MOVE_SPEED * 7);
                    setCostume(0);
                    if (LookingLeft) setCostume(31);
                    JumpKicking = false;
                    ActionLocked = false;
                }

                //activate the hitbox for the jump kick
                if (costumeNum >= start_costume + JUMP_KICK_FRAME_DATA[0]
                        && costumeNum < start_costume + JUMP_KICK_FRAME_DATA[1]) {
                    int x_offset;

                    if (LookingLeft) {
                        x_offset = -1 * JUMP_KICK_OFFSET_X_L;
                    } else {
                        x_offset = JUMP_KICK_OFFSET_X_R;
                    }

                    activeHitbox = new Rectangle((int) (hurtbox.getX() + x_offset),
                            (int) (hurtbox.getY() + JUMP_KICK_OFFSET_Y), JUMP_KICK_WIDTH, JUMP_KICK_LENGTH);
                } //reactivate the hitbox on every frame to move as the character moves

                //deactivate the hitbox after it's supposed to end
                if (costumeNum == start_costume + JUMP_KICK_FRAME_DATA[1]) {
                    activeHitbox = null;
                }

                //
                int jump_kick_offset_index = costumeNum - start_costume - 1;
                hurtbox_x_offset += JUMP_KICK_OFFSETS[jump_kick_offset_index][0];
                hurtbox_y_offset += JUMP_KICK_OFFSETS[jump_kick_offset_index][1];
            } else if (ShiftBlasting) {
                nextCostume();

                int start_costume;
                int end_costume;

                if (LookingLeft) {
                    start_costume = SHIFT_BLAST_START_L;
                    end_costume = SHIFT_BLAST_END_L;
                } else {
                    start_costume = SHIFT_BLAST_START_R;
                    end_costume = SHIFT_BLAST_END_R;
                }

                if (costumeNum == end_costume) {
                    if (!LookingLeft) {
                        x += (MOVE_SPEED * 10);
                    }
                    if (LookingLeft) {
                        x -= (MOVE_SPEED * 10);
                    }
                    setCostume(0);
                    if (LookingLeft) setCostume(31);
                    ShiftBlasting = false;
                    ActionLocked = false;
                }

                if (costumeNum >= start_costume + SHIFT_BLAST_FRAME_DATA[0]
                        && costumeNum < start_costume + SHIFT_BLAST_FRAME_DATA[1]) {
                    int x_offset;

                    if (LookingLeft) {
                        x_offset = -1 * SHIFT_BLAST_OFFSET_X_L;
                    } else {
                        x_offset = SHIFT_BLAST_OFFSET_X_R;
                    }

                    activeHitbox = new Rectangle((int) (hurtbox.getX() + x_offset),
                            (int) (hurtbox.getY() + SHIFT_BLAST_OFFSET_Y), SHIFT_BLAST_WIDTH, SHIFT_BLAST_LENGTH);
                } //reactivate the hitbox on every frame to move as the character moves

                if (costumeNum == start_costume + SHIFT_BLAST_FRAME_DATA[1]) {
                    activeHitbox = null;
                }

                int shift_blast_offset_index = costumeNum - start_costume - 1;
                hurtbox_x_offset += SHIFT_BLAST_OFFSETS[shift_blast_offset_index][0];
                hurtbox_y_offset += SHIFT_BLAST_OFFSETS[shift_blast_offset_index][1];
            } else if (GroundPounding) {
                nextCostume();

                int start_costume;
                int end_costume;

                if (LookingLeft) {
                    start_costume = GROUND_POUND_START_L;
                    end_costume = GROUND_POUND_END_L;
                } else {
                    start_costume = GROUND_POUND_START_R;
                    end_costume = GROUND_POUND_END_R;
                }

                if (costumeNum == end_costume) {
                    GroundPounding = false;
                    ActionLocked = false;
                }

                if (costumeNum >= start_costume + GROUND_POUND_FRAME_DATA[0]
                        && costumeNum < start_costume + GROUND_POUND_FRAME_DATA[1]) {
                    int x_offset;

                    if (LookingLeft) {
                        x_offset = -1 * GROUND_POUND_OFFSET_X_L;
                    } else {
                        x_offset = GROUND_POUND_OFFSET_X_R;
                    }

                    activeHitbox = new Rectangle((int) (hurtbox.getX() + x_offset),
                            (int) (hurtbox.getY() + GROUND_POUND_OFFSET_Y), GROUND_POUND_WIDTH, GROUND_POUND_LENGTH);
                } //reactivate the hitbox on every frame to move as the character moves


                if (costumeNum == start_costume + GROUND_POUND_FRAME_DATA[1]) {
                    activeHitbox = null;
                }

                int ground_pound_offset_index = costumeNum - start_costume - 1;
                hurtbox_y_offset += GROUND_POUND_OFFSETS[ground_pound_offset_index];
            } else if (Sliding) {
                nextCostume();

                int start_costume;
                int end_costume;

                if (LookingLeft) {
                    start_costume = SLIDE_START_L;
                    end_costume = SLIDE_END_L;
                } else {
                    start_costume = SLIDE_START_R;
                    end_costume = SLIDE_END_R;
                }

                if (costumeNum == end_costume) {
                    if (!LookingLeft) x += (MOVE_SPEED * 13);
                    if (LookingLeft) x -= (MOVE_SPEED * 13);
                    setCostume(0);
                    if (LookingLeft) setCostume(31);
                    Sliding = false;
                    ActionLocked = false;
                }

                if (costumeNum >= start_costume + SLIDE_FRAME_DATA[0]
                        && costumeNum < start_costume + SLIDE_FRAME_DATA[1]) {
                    int x_offset;

                    if (LookingLeft) {
                        x_offset = -1 * SLIDE_OFFSET_X_L;
                    } else {
                        x_offset = SLIDE_OFFSET_X_R;
                    }

                    activeHitbox = new Rectangle((int) (hurtbox.getX() + x_offset),
                            (int) (hurtbox.getY() + SLIDE_OFFSET_Y), SLIDE_WIDTH, SLIDE_LENGTH);
                } //reactivate the hitbox on every frame to move as the character moves

                if (costumeNum == start_costume + SLIDE_FRAME_DATA[1]) {
                    activeHitbox = null;
                }

                int jump_kick_offset_index = costumeNum - start_costume - 1;
                hurtbox_x_offset += SLIDE_OFFSETS[jump_kick_offset_index][0];
                hurtbox_y_offset += SLIDE_OFFSETS[jump_kick_offset_index][1];
            } else if (AirDashing) {
                nextCostume();

                int start_costume;
                int end_costume;

                if (LookingLeft) {
                    start_costume = AIR_DASH_START_L;
                    end_costume = AIR_DASH_END_L;
                } else {
                    start_costume = AIR_DASH_START_R;
                    end_costume = AIR_DASH_END_R;
                }

                if (costumeNum == end_costume) {
                    if (!LookingLeft) x = x - (MOVE_SPEED * 5);
                    if (LookingLeft) x = x + (MOVE_SPEED * 5);
                    setCostume(0);
                    if (LookingLeft) setCostume(31);
                    AirDashing = false;
                    ActionLocked = false;
                }


                int jump_kick_offset_index = costumeNum - start_costume - 1;
                hurtbox_x_offset += AIR_DASH_OFFSETS[jump_kick_offset_index][0];
                hurtbox_y_offset += AIR_DASH_OFFSETS[jump_kick_offset_index][1];
            } else if (Konaming) {
                nextCostume();
                if (costumeNum == KONAMI_END_L - 3 || costumeNum == KONAMI_END_R - 3) {
                    Konaming = false;
                    konamiForm = true;
                }

                int start_costume;
                int end_costume;

                if (LookingLeft) {
                    start_costume = KONAMI_START_L;
                    end_costume = KONAMI_END_L;
                } else {
                    start_costume = KONAMI_START_R;
                    end_costume = KONAMI_END_R;
                }

                int jump_offset_index = costumeNum - start_costume - 1;
                hurtbox_y_offset += KONAMI_OFFSETS[jump_offset_index];
            } else {
                if (LookingLeft) {
                    setCostume(LEFT_DEFAULT);
                } else {
                    setCostume(RIGHT_DEFAULT);
                }
            }

            if (LookingLeft) {
                hurtbox_x_offset *= -1; //reverse the direction if sprite is looking left
                hurtbox_x_offset += HURTBOX_OFFSET_X_L;
            } else {
                hurtbox_x_offset += HURTBOX_OFFSET_X_R;
            }

            hurtbox.setLocation(x + hurtbox_x_offset, y + hurtbox_y_offset);

            if (possibleKonami && konamiIter == 8) { //detect beginning of a konimi code enter chain
                boolean maybeKonami = true;
                for (int i = 0; i < konamiIter; i++) {
                    if (afterJump[i] != konamiSeq[i]) maybeKonami = false;
                }
                if (maybeKonami) {
                    Ducking = false;
                    Jumping = false;
                    Kicking = false;
                    MovingLeft = false;
                    MovingRight = false;
                    SlowKicking = false;
                    JumpKicking = false;
                    ShiftBlasting = false;
                    GroundPounding = false;
                    Sliding = false;
                    AirDashing = false;
                    Konaming = true;
                    ActionLocked = true;
                    possibleKonami = false;
                    System.out.println("Activated Konami code");
                    for (int j = 0; j < konamiIter; j++) {
                        afterJump[j] = 0;
                    }

                    costumeNum = KONAMI_START_R;
                    if (LookingLeft) costumeNum = KONAMI_START_L;
                }
                konamiIter = 0;
            } else if (possibleKonami) {
                for (int i = 0; i < konamiIter; i++) {
                    if (afterJump[i] != konamiSeq[i]) {
                        possibleKonami = false;
                        konamiIter = 0;
                    }
                }
            }
        }

    }

    //check if the hurtbox of the sprite is touching the hurt box of the given sprite
    public boolean isTouching(sprite s){
        //TODO: This is where we can check overlap of sprites, given another sprite
        return hurtbox.getX() + hurtbox.getWidth() > s.hurtbox.getX();
    }

    //checks if a given hitbox collides with this sprite's hurtbox
    public boolean collidesWith(Rectangle hitbox) {
        int hitbox_bottom = (int) (hitbox.getY() + hitbox.getHeight());
        int sprite_bottom = (int) (hurtbox.getY() + hurtbox.getHeight());
        boolean vertical = hitbox_bottom >= hurtbox.getY() && hitbox.getY() <= sprite_bottom;

        int hitbox_right = (int) (hitbox.getX() + hitbox.getWidth());
        int sprite_right = (int) (hurtbox.getX() + hurtbox.getWidth());
        boolean horizontal = hitbox_right >= hurtbox.getX() && hitbox.getX() <= sprite_right;

        return vertical && horizontal;
    }

    //IGNORE: check update
    public void keyPressed(KeyEvent e) {
      //if we wanted to activate things on the press of a key code would go here
    }
    
    //tracks hurtbox
    public void updateHurtbox(int x_offset, int y_offset) {
        //System.out.printf("Hurtbox moving down %d pixels\n", y_offset);
        int xpos = (int) hurtbox.getX();
        int ypos = (int) hurtbox.getY();
        hurtbox.setLocation(xpos + x_offset, ypos + y_offset);
    }

    //track key release
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == DuckKey){
            System.out.println("Down Key Pressed");
            if(!ActionLocked){
                costumeNum = DUCK_START_R;
                if(LookingLeft) costumeNum = DUCK_START_L;
                Ducking = true;
                ActionLocked = true;
            }
            if(Jumping){
                if(costumeNum <= JUMP_START_R +4 && !LookingLeft){
                    costumeNum = GROUND_POUND_START_R + costumeNum - JUMP_START_R;
                    Jumping = false;
                    GroundPounding = true;
                    ActionLocked = true;
                }
                if(costumeNum <= JUMP_START_L +4 && LookingLeft){
                    costumeNum = GROUND_POUND_START_L + costumeNum - JUMP_START_L;
                    Jumping = false;
                    GroundPounding = true;
                    ActionLocked = true;
                }
            }
            if(SlowKicking){
                if(costumeNum <= SLOW_KICK_START_R +4 && !LookingLeft){
                    if (x + MOVE_SPEED*13 <= SCREEN_BOUND_R) {
                        costumeNum = SLIDE_START_R + costumeNum - SLOW_KICK_START_R;
                        SlowKicking = false;
                        Sliding = true;
                        ActionLocked = true;
                    }
                }
                if(costumeNum <= SLOW_KICK_START_L +4 && LookingLeft){
                    if (x - MOVE_SPEED *13 >= SCREEN_BOUND_L) {
                        costumeNum = SLIDE_START_L + costumeNum - SLOW_KICK_START_L;
                        SlowKicking = false;
                        Sliding = true;
                        ActionLocked = true;
                    }
                }
            }
            if(possibleKonami && konamiIter <8){
                afterJump[konamiIter] = DuckKey;
                konamiIter++;
            }
        }
        if(key == JumpKey){
            System.out.println("Up Key Pressed");
            if(!ActionLocked) {
                costumeNum = JUMP_START_R;
                if (LookingLeft) costumeNum = JUMP_START_L;
                Jumping = true;
                ActionLocked = true;
            }
            if(!possibleKonami && !Konaming && konamiIter <8 && konamiCooldown <0) {
                possibleKonami = true;
                afterJump[konamiIter] = JumpKey;
                System.out.println("possibleKonami");
                konamiIter++;
            }
        }
        if(key == FastKey){
            System.out.println("Fast Attack Key Pressed");
            if(!ActionLocked){
                costumeNum = FAST_KICK_START_R;
                if(LookingLeft) costumeNum = FAST_KICK_START_L;
                Kicking = true;
                ActionLocked = true;
            }
            if(Jumping){
                if(costumeNum <= JUMP_START_R +4 && !LookingLeft){
                    if (x + MOVE_SPEED * 7 <= SCREEN_BOUND_R) {
                        costumeNum = JUMP_KICK_START_R + costumeNum - JUMP_START_R;
                        Jumping = false;
                        JumpKicking = true;
                        ActionLocked = true;
                    }
                }
                if(costumeNum <= JUMP_START_L +4 && LookingLeft){
                    if (x - MOVE_SPEED * 7 >= SCREEN_BOUND_L) {
                        costumeNum = JUMP_KICK_START_L + costumeNum - JUMP_START_L;
                        Jumping = false;
                        JumpKicking = true;
                        ActionLocked = true;
                    }
                }
            }
            if(possibleKonami && konamiIter <8){
                afterJump[konamiIter] = FastKey;
                konamiIter++;
            }
        }
        if(key == SlowKey){
            System.out.println("Slow Attack Key Pressed");
            if(!ActionLocked){
                costumeNum = SLOW_KICK_START_R;
                if(LookingLeft) costumeNum = SLOW_KICK_START_L;
                SlowKicking = true;
                ActionLocked = true;
            }
            if(MovingLeft){
                if(costumeNum <= LEFT_MOVE_START_L +2){
                    if (x - MOVE_SPEED*10 >= SCREEN_BOUND_L) {
                        costumeNum = SHIFT_BLAST_START_L + costumeNum - LEFT_MOVE_START_L;
                        MovingLeft = false;
                        ShiftBlasting = true;
                        ActionLocked = true;
                    }
                }
            }
            if(MovingRight){
                if(costumeNum <= RIGHT_MOVE_START_R +2){
                    if (x + MOVE_SPEED*10 <= SCREEN_BOUND_R) {
                        costumeNum = SHIFT_BLAST_START_R + costumeNum - RIGHT_MOVE_START_R;
                        MovingRight = false;
                        ShiftBlasting = true;
                        ActionLocked = true;
                    }
                }
            }
            if(possibleKonami && konamiIter <8){
                afterJump[konamiIter] = SlowKey;
                konamiIter++;
            }
        }
        if(key == RightKey) {
            System.out.println("Right Key Pressed");
            if(!ActionLocked){
                LookingLeft = false;
                costumeNum = RIGHT_MOVE_START_R;
                MovingRight = true;
                ActionLocked = true;
            }
            if( Jumping && LookingLeft){
                if(costumeNum <= JUMP_START_L +4){
                    if (x + MOVE_SPEED * 5 <= SCREEN_BOUND_R) {
                        costumeNum = AIR_DASH_START_L + costumeNum - JUMP_START_L;
                        // System.out.println(costumeNum);
                        Jumping = false;
                        AirDashing = true;
                        ActionLocked = true;
                    }
                }
            }
            if(possibleKonami && konamiIter <8){
                afterJump[konamiIter] = RightKey;
                konamiIter++;
            }
            if(konamiForm){
                costumeNum = KONAMI_START_R + 5;
                LookingLeft = false;
                MovingRight = true;
            }
        }
        if(key == LeftKey){
            System.out.println("Left Key Pressed");
            if(!ActionLocked) {
                LookingLeft = true;
                costumeNum = LEFT_MOVE_START_L;
                MovingLeft = true;
                ActionLocked = true;
            }
            if( Jumping && !LookingLeft){
                if(costumeNum <= JUMP_START_R +4){
                    if (x - MOVE_SPEED * 5 >= SCREEN_BOUND_L) {
                        costumeNum = AIR_DASH_START_R + costumeNum - JUMP_START_R;
                        Jumping = false;
                        AirDashing = true;
                        ActionLocked = true;
                    }
                }
            }
            if(possibleKonami && konamiIter <8){
                afterJump[konamiIter] = LeftKey;
                konamiIter++;
            }
            if(konamiForm){
                costumeNum = KONAMI_START_L + 5;
                LookingLeft = true;
                MovingLeft = true;
            }
        }
    }

    //get active hitbox
    public Rectangle getActiveHitbox() {
        return activeHitbox;
    }

    //deactivate hitbox
    public void deactivateHitbox() {
        activeHitbox = null;
    }

    //get active hurtbox
    public Rectangle getHurtbox() {
        return hurtbox;
    }

    //get player number
    public int getPlayerNum() {
        return playerNum;
    }

    //set player health
    public int setHealth(int new_health){
        health = new_health;
        return health;
    }

    //get player health
    public int getHealth(){
        return health;
    }
}
