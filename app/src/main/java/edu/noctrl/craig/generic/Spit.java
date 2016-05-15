package edu.noctrl.craig.generic;

import android.graphics.Rect;

/**
 * Created by Debra, Emily, and Nick on 5/14/16.
 * Class for the spit-bullet used by the player
 */
public class Spit extends GameSprite {

    private Rect spitBullet = new Rect(0, 150, 45, 167);
    private Point3F spitPt = new Point3F(1F,1F,1F);

    public Spit(World theWorld, float velocityX, float velocityY, SpaceCamel cam, float rotationAngle) {
        super(theWorld);
        this.collidesWith = Collision.SolidAI;
        this.substance = Collision.SolidPlayer;

        float relativeWidth = (float) (cam.getWidth() + (cam.getWidth() * 0.50));
        this.position = new Point3F(relativeWidth, (theWorld.height / 2) - 50 , 0);
        this.speed = 200;
        this.baseVelocity = new Point3F(velocityX, velocityY, 0F);
        this.rotationAngle = (float) (rotationAngle * (180 / Math.PI));
        this.updateVelocity();
    }

    @Override
    public Rect getSource() {
        return spitBullet;
    }

    @Override
    public Point3F getScale() {
        return spitPt;
    }

    @Override
    public void cull() {
        //give a spit back
        world.spitLimit--;
    }

    @Override
    public void collision(GameObject other) {
        other.kill();
        world.hitCount++;
    }
}
