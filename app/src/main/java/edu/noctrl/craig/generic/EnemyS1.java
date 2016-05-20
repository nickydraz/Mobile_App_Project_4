package edu.noctrl.craig.generic;

import android.graphics.Rect;

/**
 * Created by Debra, Emily, and Nick on 5/14/16.
 */
public class EnemyS1 extends GameSprite {
    static final Rect snakeRect = new Rect(0, 70, 56, 135);

    private Point3F scalePt = new Point3F(1, 1, 1);
    StageOne w;
    public EnemyS1(StageOne theWorld, Point3F pos) {
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
        w.score += 100;
    }

    @Override
    public void collision(GameObject other) {
        other.kill();
        w.killCount++; //increment num kills
        w.timeLeft += 5; //give player more time

    }
}
