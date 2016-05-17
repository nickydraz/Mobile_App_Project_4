package edu.noctrl.craig.generic;

import android.graphics.Rect;

/**
 * Created by Emily, Nick, and Debra on 5/16/16.
 * Venom class for enemies to fire at camel
 */
public class Venom extends GameSprite {
    private Rect venomBullet = new Rect(63, 154, 102, 163);
    private Point3F venomPt = new Point3F(1F,1F,1F);

    public Venom(World theWorld, float velocityX, float velocityY, EnemyS2 enm, float rotationAngle) {
        super(theWorld);
        this.collidesWith = Collision.SolidPlayer;
        this.substance = Collision.SolidAI;
        this.position = enm.position.clone();
        this.speed = 200;
        this.baseVelocity = new Point3F(velocityX, velocityY, 0F);
        this.rotationAngle = (float) (rotationAngle * (180 / Math.PI));
        this.updateVelocity();
    }

    @Override
    public Rect getSource() {
        return venomBullet;
    }

    @Override
    public Point3F getScale() {
        return venomPt;
    }

    @Override
    public void cull() {

    }

    @Override
    public void collision(GameObject other) {
        other.kill();
    }
}
