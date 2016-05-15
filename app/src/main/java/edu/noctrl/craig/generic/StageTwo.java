package edu.noctrl.craig.generic;

import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Debra, Emily, and Nick on 5/15/16.
 */
public class StageTwo extends World {
    public SpaceCamel cam2;

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

        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE)
        {
            fireSpit(event);
        }
        return true;
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
        //int camBuffer = 600;
        float camBuffer = (float) (cam2.getWidth() * 2.5);
        //(int)((GameSprite) objects.get(0)).getWidth()+80;
        float yBuffer = (float) (height - (height * .80));
        int randomX = Math.min(((int)(Math.random() * (width) + camBuffer)), width);
        int randomY = (int) Math.max(((Math.random() * (height - yBuffer))), yBuffer);
        Point3F enemyPos = new Point3F(randomX, randomY, 0);
        EnemyS1 snake = new EnemyS1(this, enemyPos);
        this.addObject(snake);
    }

    public void fireSpit(MotionEvent event) {
        Point touchPoint = new Point((int) event.getX(), (int) event.getY());

        double centerMinusY = (this.height / 2 - touchPoint.y);

        double angle = 0;

        angle = Math.atan2(touchPoint.x, centerMinusY);

        //Fire the spit
        cam2.shootSpit(angle);

        //Increment spit counter
        spitCount++;
    }
}
