package edu.noctrl.craig.generic;

import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Debra, Emily, and Nick on 5/15/16.
 */
public class StageTwo extends World {
    public SpaceCamel cam2;

    private final int ENEMIES_NEEDED = 20;

    //Variable for the touch event
    private float mLastTouchX;
    private float mLastTouchY;
    private float mPosX;
    private float mPosY;

    public StageTwo(StateListener listener, SoundManager sounds) {
        super(listener, sounds);
        cam2 = new SpaceCamel(this);
        cam2.position =  new Point3F(180, 100, 0);
        this.addObject(cam2);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {

        int action = event.getAction();


        //If touching to the right of the camel, then spit at the enemies
        if ((action == MotionEvent.ACTION_DOWN) && (event.getX() > (cam2.getWidth() * 2.5)))
        {
            fireSpit(event);
            return true;
        }

        //If touching in the camel's area, move the camel
        int actionOnly = action & MotionEvent.ACTION_MASK; //0 x FF

        switch (actionOnly) {
            case (MotionEvent.ACTION_MOVE):
                final float x = event.getX();
                final float y = event.getY();

                //Don't let the player move past the boundary line
                if (x > cam2.getWidth() * 2.5)
                {
                    return false;
                }

                // Calculate the distance moved
                final float dx = x - mLastTouchX;
                final float dy = y - mLastTouchY;

                // Move the object
                mPosX += dx;
                mPosY += dy;

                // Remember this touch position for the next move event
                mLastTouchX = x;
                mLastTouchY = y;

                cam2.position = new Point3F(mPosX, mPosY, 0F);
                return true;
            case (MotionEvent.ACTION_UP):
                return false;
            default:
                return super.onTouch(v, event);
        }
    }

    @Override
    public void initializeGame()
    {
        cam2.setStartPt();
        spawnEnemy();
        spawnEnemy();
    }

    @Override
    public void spawnEnemy()
    {
        float camBuffer = (float) Math.max((cam2.getWidth() * 2.5), 600.0);
        float yBuffer = (float) (height - (height * .80));
        int randomX = Math.min(((int)(Math.random() * (width) + camBuffer)), width);
        int randomY = (int) Math.max(((Math.random() * (height - yBuffer))), yBuffer);
        Point3F enemyPos = new Point3F(randomX, randomY, 0);
        EnemyS2 snake = new EnemyS2(this, enemyPos);
        this.addObject(snake);

        spawnInterval = (int) (Math.random() * 6 + 2); //set a new interval for next enemy
    }

    public void fireSpit(MotionEvent event) {
        Point touchPoint = new Point((int) event.getX(), (int) event.getY());

        double angle = Math.PI/2;

        //Fire the spit
        cam2.shootSpit(angle);

        //Increment spit counter
        spitCount++;
    }

    @Override
    public void isGameOver()
    {
        if (timeLeft == 0 && killCount < ENEMIES_NEEDED)
        {
            // !! this isn't getting called for some reason !!
            this.enemies_left = ENEMIES_NEEDED - killCount;
            System.out.println("Enemies left: " + enemies_left);
            this.listener.onGameOver(true);
        }
        else if(killCount >= ENEMIES_NEEDED)
        {
            this.listener.onNextStage(true); //do we want an infinite game?
        }
    }
}
