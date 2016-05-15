package edu.noctrl.craig.generic;

import android.graphics.Rect;
import android.util.Log;

/**
 * Created by Debra, Emily, and Nick on 5/14/16.
 */
public class SpaceCamel extends GameSprite{

    static final Rect camelRect = new Rect(0, 0, 77, 52);
    private Point3F scalePt = new Point3F(1, 1, 1);
    private World w;

    public SpaceCamel(World theWorld) {
        super(theWorld);
        w = theWorld;
        this.collidesWith = Collision.SolidAI;
        this.substance = Collision.SolidPlayer;
    }

    @Override
    public Rect getSource() {
        return camelRect;
    }

    @Override
    public Point3F getScale() {
        return scalePt;
    }

    @Override
    public void cull() {

    }

    //Method for spitting at the enemies.
    public void shootSpit(double angle) {
        //calculate the spit velocity's X component
        float velocityX = (float) (StageOne.SPIT_SPEED_PERCENT *  Math.sin(angle));

        //Calculate the spit velocity's Y component
        float velocityY = (float)(StageOne.SPIT_SPEED_PERCENT * -Math.cos(angle));

        //Make a spit and position it
        Spit spit = new Spit(w, velocityX, velocityY, this, (float)angle);
        Log.i("Rotation", String.valueOf(spit.rotationAngle));
        w.addObject(spit);

    }

    public void setStartPt()
    {
        position = new Point3F(180, w.height/2, 0);
    }
}
