package edu.noctrl.craig.generic;

import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by Debra, Nick, And Emily on 5/24/16.
 */
public class EnemyS3 extends GameSprite {
    static final Rect greenRect = new Rect(66, 70, 142, 126); //green attack snake
    static final Rect blueRect = new Rect(144, 70, 225, 140); //blue attack snake

    private Point3F scalePt = new Point3F(1, 1, 1);

    StageThree w;

    int which;
    private int randomX;
    private int randomY;

    private int numUpdates = 30;


    public EnemyS3(StageThree theWorld, Point3F pos) {
        super(theWorld);

        w = theWorld;
        position = pos;
        this.collidesWith = Collision.SolidPlayer;
        this.substance = Collision.SolidAI;
        which = (int) (Math.random() + 0.5);
        randomX =  (int)this.position.X;
        randomY =  (int)this.position.Y;
        speed = 100;

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
        w.soundManager.playSound(2);
        //increment the score if the player hits a snake
        switch (which){
            case 0:{
                w.score += 150;
                break;
            }
            case 1:{
                w.score += 300;
                break;
            }
        }
    }

    @Override
    public void collision(GameObject other) {
        other.kill();
        w.killCount++; //increment num kills
    }

    @Override
    public void update(float interval){
        position.add(velocity.clone().mult(interval));
       //Move toward the camel
        Point camelPoint = new Point((int) w.cam3.position.X, (int) w.cam3.position.Y);
        double angle = 0;

        angle = Math.atan2(this.position.X - camelPoint.x, camelPoint.y - this.position.Y);
        //calculate the snake velocity's X component
        float velocityX = (float) (StageOne.SPIT_SPEED_PERCENT * -Math.sin(angle));

        //Calculate the snake velocity's Y component
        float velocityY = (float) (StageOne.SPIT_SPEED_PERCENT * Math.cos(angle));

        this.baseVelocity.X = velocityX;
        this.baseVelocity.Y = velocityY;
        this.rotationAngle = (float) angle;

        this.updateVelocity();
    }

    //Method for attacking the camel.
   /* public void shootVenom() {
        //Snake hissing sound
        w.soundManager.playSound(3);

        Point camelPoint = new Point((int) w.cam3.position.X, (int) w.cam3.position.Y);
        double angle = 0;

        angle = Math.atan2(this.position.X - camelPoint.x, camelPoint.y - this.position.Y);
        //calculate the venom velocity's X component
        float velocityX = (float) (StageOne.SPIT_SPEED_PERCENT * -Math.sin(angle));

        //Calculate the venom velocity's Y component
        float velocityY = (float) (StageOne.SPIT_SPEED_PERCENT * Math.cos(angle));

        //Make a venom object and position it
        Venom venom = new Venom(w, velocityX, velocityY, this, (float) angle);
        w.addObject(venom);

    }*/

    public void setVelocity()
    {
        float camBuffer = (float) Math.max((w.cam3.getWidth() * 2.5), 600.0);
        float yBuffer = (float) (w.height - (w.height * .80));
        float range = (w.width - camBuffer) + 1;
        randomX = (int) ((Math.random() * range) + camBuffer );
        randomY = (int) Math.max(((Math.random() * (w.height - yBuffer))), yBuffer);

        Point randPoint = new Point( randomX, randomY);

        double centerMinusY = (w.height / 2 - randPoint.y);

        double angle = 0;

        angle = Math.atan2(this.position.X - randPoint.x, randPoint.y - this.position.Y);
        //calculate the venom velocity's X component
        float velocityX = (float) (StageOne.SPIT_SPEED_PERCENT * -Math.sin(angle));

        //Calculate the venom velocity's Y component
        float velocityY = (float) (StageOne.SPIT_SPEED_PERCENT * Math.cos(angle));

        this.baseVelocity.X = velocityX;
        this.baseVelocity.Y = velocityY;
        this.rotationAngle = (float) angle;

        this.updateVelocity();
    }
}
