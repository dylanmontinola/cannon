package com.example.montinol19.cannonanimation;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

/**
 * Created by montinol19 on 4/2/2017.
 * Help from CS FELLOWS.
 *
 * This new 3B has the following:
 * Sound, target explosions, and cannon explosions!
 */

public class CannonAnimator implements Animator
{
    private int screenWidth, screenHeight;
    private int touchX, touchY;
    private int ballX, ballY;
    private int canStartX, canStartY, canBottomX, canBottomY;
    public boolean target1Hit = false;
    public boolean target2Hit = false;
    public boolean target3Hit = false;
    private double time = 0;
    private boolean shot = false;
    // instance variables

    /**
     * Interval between animation frames: .02 seconds (i.e., about 20 times
     * per second).
     *
     * @return the time interval between frames, in milliseconds.
     */
    public int interval()
    {
        return 20; // From TestAnimator starter code
    }

    /**
     *Purple background color
     *
     * @return the background color onto which we will draw the image.
     */
    public int backgroundColor()
    {
        return Color.rgb(221,160,221); //purple background color
    }

    /**
     * Action to perform on clock tick
     *
     * @param g the graphics object on which to draw
     */
    public void tick(Canvas g)
    {
        screenWidth = g.getWidth(); //gets width of screen
        screenHeight = g.getHeight(); //gets height of screen

        Paint white = new Paint();
        white.setColor(Color.WHITE);
        Paint black = new Paint();
        black.setColor(Color.BLACK);
        Paint gray = new Paint();
        gray.setColor(Color.GRAY);
        Paint darkGray = new Paint();
        darkGray.setColor(Color.DKGRAY);
        Paint purple = new Paint();
        purple.setColor(Color.rgb(221, 160, 221));
        Paint red = new Paint();
        red.setColor(Color.RED);
        //All colors used

        white.setTextSize(50);
        g.drawText("TAP TO FIRE CANNON!",g.getWidth()/3,g.getHeight()/8,white);
        //Displays instructions for the user

        int target1X = g.getWidth() / 2 + 200; //x pos for target 1
        int target1Y =  g.getHeight() - 650; //y pos for target 1
        int radius = 75; //radius for target 1
        //target 1

        int target2X = g.getWidth()- 300; //x pos for target 2
        int target2Y =  300; //y pos for target 2
        //target 2

        int target3X = g.getWidth()- 800; //x pos for target 3
        int target3Y =  500; //y pos for target 3
        //target 3


        if( target1Hit == false) //If it's not hit, draw target 1
        {
            g.drawCircle(target1X, target1Y, radius, darkGray);
            g.drawCircle(g.getWidth() / 2 + 200, g.getHeight() - 650, 60, white);
            g.drawCircle(g.getWidth() / 2 + 200, g.getHeight() - 650, 30, darkGray);
            //draws target 1
        }

        if( target2Hit == false) //If it's not hit, draw target 2
        {
            g.drawCircle(target2X, target2Y, radius, darkGray);
            g.drawCircle(g.getWidth() - 300, 300, 60, white);
            g.drawCircle(g.getWidth() - 300, 300, 30, darkGray);
            //draws target 2
        }
        if( target3Hit == false) //If it's not hit, draw target 3
        {
            g.drawCircle(target3X, target3Y, radius, darkGray);
            g.drawCircle(g.getWidth() - 800, 500, 60, white);
            g.drawCircle(g.getWidth() - 800, 500, 30, darkGray);
            //draws target 3
        }

        canBottomX = 100;
        canBottomY = g.getHeight() - 100;
        canStartX = 200;
        canStartY = g.getHeight() - 200;
        g.drawCircle(canBottomX, canBottomY, 300, darkGray);
        g.drawCircle(canStartX, canStartY, 100, gray);
        g.drawCircle(canStartX, canStartY, 40, black);
        //Draws and creates the cannon in the lower left corner


        ballX = (int)(canStartX + time*(touchX-canStartX));
        ballY = (int)(canStartY + time*(touchY-canStartY));
        //direction for the cannonball

        if(shot) //if tapped
        {
            g.drawCircle(ballX, ballY, 40, black); //draws the cannon ball
            time+=.05; //time
            touchY+=20; //gravity

            if(ballX>g.getWidth() || ballY>g.getHeight()  || ballX<0) //If ball leaves screen
            {
                shot = false; //removes shot, doesn't count


            }
            if((ballX+25 - target1X <= 100 && ballX+25 - target1X >= 0) && (ballY+25 - target1Y <= 100 && ballY+25 - target1Y >= 0)) //If target 1 is hit
            {
                target1Hit = false;
                g.drawPaint(red); //makes explosion
            }

            if((ballX+25 - target2X <= 100 && ballX+25 - target2X >= 0) && (ballY+25 - target2Y <= 100 && ballY+25 - target2Y >= 0)) //If target 2 is hit
            {
                target2Hit = false;
                g.drawPaint(red); //makes explosion
            }

            if((ballX+25 - target3X <= 100 && ballX+25 - target3X >= 0) && (ballY+25 - target3Y <= 100 && ballY+25 - target3Y >= 0)) //If target 3 is hit
            {
                target3Hit = false;
                g.drawPaint(red); //makes explosion
            }
            else if (ballX <= canStartX + 40 && ballY >= canStartY + 10) //If the cannon is hit directly
            {
                g.drawCircle(canStartX + 50, canStartY - 50, 200, purple); //Then remove or "explode" a part of the cannon
                shot = true; //can no longer shoot the cannon
            }
        }
    }

    /**
     * Tells that we never pause.
     *
     * @return indication of whether to pause
     */
    public boolean doPause()
    {
        return false;
    }

    /**
     * Tells that we never stop the animation.
     *
     * @return indication of whether to quit.
     */
    public boolean doQuit()
    {
        return false;
    }

    /**
     * When the screen is tapped, the cannon is shot
     */
    public void onTouch(MotionEvent event)
    {
        if (event.getAction() == MotionEvent.ACTION_UP && !shot)
        {
            shot = true;
            time = 0;
            touchX = (int)event.getX();

            if(touchX > screenWidth/2)
            {
                touchX = screenWidth/2;
            }

            touchY = (int)event.getY();


        }

    }





}
