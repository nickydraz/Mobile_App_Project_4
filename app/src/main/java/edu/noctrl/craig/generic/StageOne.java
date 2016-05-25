package edu.noctrl.craig.generic;

import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Debra, Emily, Nick on 5/14/16.
 */
public class StageOne extends World{
    public SpaceCamel cam;

    //Constants
    public static final double SPIT_SPEED_PERCENT = 3.0 / 2;

    public StageOne(StateListener listener, SoundManager sounds) {
        super(listener, sounds);
        cam = new SpaceCamel(this);
        cam.position =  new Point3F(180, 100, 0);
        this.addObject(cam);
        ENEMIES_NEEDED = 10;
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
        cam.setStartPt();
        spawnEnemy();
    }

    @Override
    public void spawnEnemy()
    {
        float camBuffer = (float) Math.max((cam.getWidth() * 2.5), 600.0);
        float yBuffer = (float) (height - (height * .80));
        float range = (width - camBuffer) + 1;
        int randomX = (int) ((Math.random() * range) + camBuffer );
        int randomY = (int) Math.max(((Math.random() * (height - yBuffer))), yBuffer);
        Point3F enemyPos = new Point3F(randomX, randomY, 0);
        EnemyS1 snake = new EnemyS1(this, enemyPos);
        this.addObject(snake);

        spawnInterval = (int) (Math.random() * 6 + 2);
    }

    public void fireSpit(MotionEvent event) {
        Point touchPoint = new Point((int) event.getX(), (int) event.getY());

        double centerMinusY = (this.height / 2 - touchPoint.y);

        double angle = 0;

        angle = Math.atan2(touchPoint.x, centerMinusY);

        //Fire the spit
        cam.shootSpit(angle);
    }

    @Override
    public void isGameOver()
    {
        if (timeLeft == 0 && killCount < ENEMIES_NEEDED)
        {
            this.enemies_left = ENEMIES_NEEDED - killCount; //how close was the player?
            this.listener.onGameOver(true);
        }
        else if(killCount >= ENEMIES_NEEDED)
        {
            this.listener.onNextStage(true, 2);
        }
    }
}