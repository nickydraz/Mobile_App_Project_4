package edu.noctrl.craig.generic;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

/**
 * Created by Debra, Nick, And Emily on 5/15/16.
 */
public class EnemyS2 extends GameSprite {
    static final Rect greenRect = new Rect(66, 70, 142, 126); //green attack snake
    static final Rect blueRect = new Rect(144, 70, 225, 140); //blue attack snake

    private Point3F scalePt = new Point3F(1, 1, 1);

    StageTwo w;

    int which;
    boolean timeToAttack = false;
    private double birthday = 0;
    private int randomInterval = 3;


    public EnemyS2(StageTwo theWorld, Point3F pos) {
        super(theWorld);

        w = theWorld;
        position = pos;
        this.collidesWith = Collision.SolidPlayer;
        this.substance = Collision.SolidAI;
        which = (int) (Math.random() + 0.5);
        birthday = w.totalElapsedTime;
        randomInterval = (int)(Math.random() * 15) + 3;
        Log.i("Random", String.valueOf(randomInterval));
    }

    @Override
    public Rect getSource() {

        switch (which){
            case 0:{
                return greenRect;
            }
            case 1:{
                return blueRect;
            }
        }

        return null;
    }

    @Override
    public Point3F getScale() {
        return scalePt;
    }

    @Override
    public void cull() {

    }

    @Override
    public void collision(GameObject other) {
        other.kill();
        w.killCount++; //increment num kills
        w.timeLeft += 5; //give player more time
    }

    @Override
    public void update(float interval){
        position.add(velocity.clone().mult(interval));
        if (((int)(w.totalElapsedTime - birthday)) % randomInterval == 0) {
            if(timeToAttack) {
                shootVenom();
            }
            timeToAttack = false;
        }
        else
            timeToAttack = true;
    }

    //Method for attacking the camel.
    public void shootVenom() {
        Point camelPoint = new Point((int) w.cam2.position.X, (int) w.cam2.position.Y);

        double centerMinusY = (w.height / 2 - camelPoint.y);

        double angle = 0;

        angle = Math.atan2(camelPoint.x, centerMinusY);

        //calculate the venom velocity's X component
        float velocityX = (float) (StageOne.SPIT_SPEED_PERCENT * -Math.sin(angle));

        //Calculate the venom velocity's Y component
        float velocityY = (float) (StageOne.SPIT_SPEED_PERCENT * -Math.cos(angle));

        //Make a venom object and position it
        Venom venom = new Venom(w, velocityX, velocityY, this, (float) angle);
        w.addObject(venom);

    }
}
