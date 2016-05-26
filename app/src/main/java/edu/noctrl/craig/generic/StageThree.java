package edu.noctrl.craig.generic;

import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by debra on 5/24/16.
 */
public class StageThree extends World{

    public SpaceCamel cam3;

    //Variable for the touch event
    private float mLastTouchX;
    private float mLastTouchY;
    private float mPosX;
    private float mPosY;

    public StageThree(StateListener listener, SoundManager sounds) {
        super(listener, sounds);
        this.suddenDeath=true;
        cam3 = new SpaceCamel(this);
        cam3.position =  new Point3F(180, 100, 0);
        this.addObject(cam3);

    }
    @Override
    public boolean onTouch(View v, MotionEvent event)
    {

        int action = event.getAction();


        //If touching to the right of the camel, then spit at the enemies
        if ((action == MotionEvent.ACTION_DOWN) && (event.getX() > (cam3.getWidth() * 2.5)))
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
                if (x > cam3.getWidth() * 2.5)
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

                cam3.position = new Point3F(mPosX, mPosY, 0F);
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
        cam3.setStartPt();
        spawnEnemy();
        spawnEnemy();
    }

    @Override
    public void spawnEnemy()
    {
        float camBuffer = (float) Math.max((cam3.getWidth() * 2.5), 600.0);
        float yBuffer = (float) (height - (height * .80));
        float range = (width - camBuffer) + 1;
        int randomX = (int) ((Math.random() * range) + camBuffer );
        int randomY = (int) Math.max(((Math.random() * (height - yBuffer))), yBuffer);
        Point3F enemyPos = new Point3F(randomX, randomY, 0);
        EnemyS3 snake = new EnemyS3(this, enemyPos);
        this.addObject(snake);
        spawnInterval = (int) ((Math.random() * 6) + 2); //set a new interval for next enemy
    }

    public void fireSpit(MotionEvent event) {
        Point touchPoint = new Point((int) event.getX(), (int) event.getY());

        double angle = Math.PI/2;

        //Fire the spit
        cam3.shootSpit(angle);
    }

    @Override
    public void isGameOver()
    {


    }
}
