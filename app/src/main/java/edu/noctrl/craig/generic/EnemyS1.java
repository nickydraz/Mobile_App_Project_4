package edu.noctrl.craig.generic;

import android.graphics.Rect;
import android.util.Log;

/**
 * Created by Debra, Emily, and Nick on 5/14/16.
 */
public class EnemyS1 extends GameSprite {
    static final Rect snakeRect = new Rect(0, 70, 56, 126);

    private Point3F scalePt = new Point3F(1, 1, 1);
    World w;
    public EnemyS1(World theWorld, Point3F pos) {
        super(theWorld);
        w = theWorld;
        position = pos;
        this.collidesWith = Collision.SolidPlayer;
        this.substance = Collision.SolidAI;
    }

    @Override
    public Rect getSource() {
        return snakeRect;
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
        Log.i("Counter", "Kill: " + w.killCount);
    }
}
