package edu.noctrl.craig.generic;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by debra on 5/14/16.
 */
public class StageOne extends World{
    private SpaceCamel cam;

    public StageOne(StateListener listener, SoundManager sounds) {
        super(listener, sounds);
        cam = new SpaceCamel(this);
        cam.position =  new Point3F(180, 100, 0);
        System.out.println("In Stage One width is " + width);
        this.addObject(cam);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        return true;
    }

@Override
    public void initializeGame()
    {
        cam.setStartPt();
        spawnEnemy();
        spawnEnemy();
    }

    @Override
    public void spawnEnemy()
    {
        int camBuffer = 500;
                //(int)((GameSprite) objects.get(0)).getWidth()+80;
        int randomX = Math.min(((int)(Math.random() * (width) + camBuffer)), width);
        int randomY = (int)(Math.random() * (height-20) + 20);
        Point3F enemyPos = new Point3F(randomX, randomY, 0);
        EnemyS1 snake = new EnemyS1(this, enemyPos);
        this.addObject(snake);
    }
}
