package edu.noctrl.craig.generic;

import android.graphics.Rect;

/**
 * Created by Debra, Nick, And Emily on 5/15/16.
 */
public class EnemyS2 extends GameSprite {
    static final Rect greenRect = new Rect(0, 70, 56, 135);
    static final Rect blueRect = new Rect(144, 70, 225, 140);

    private Point3F scalePt = new Point3F(1, 1, 1);
    World w;

    int which;


    public EnemyS2(World theWorld, Point3F pos) {
        super(theWorld);

        w = theWorld;
        position = pos;
        this.collidesWith = Collision.SolidPlayer;
        this.substance = Collision.SolidAI;
        which = (int) (Math.random()+0.5);
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
        w.killCount++;
    }
}
