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
        this.position = cam.position.clone();
        this.speed = 500;
        this.baseVelocity = new Point3F(velocityX, velocityY, 0F);
        this.rotationAngle = (float) (rotationAngle * (180 / Math.PI)) - 90f; //in degrees
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

   /* @Override
    public void draw(Canvas canvas)
    {
        //Log.e("Spit", "is being drawn! the angle is " + rotationAngle);
        canvas.save();

        if(canvas == null) {
            Log.i("null", "Canvas is null");
            return;
        }
        Rect src = getSource();
        Point3F scale = getScale();
        float width = ((src.width() * scale.X) * scale.Z * this.world.worldScale.X)/2F;
        float height = ((src.height() * scale.Y) * scale.Z * this.world.worldScale.Y)/2F;
        //Rect(int left, int top, int right, int bottom)
        RectF dest = new RectF(0,0,width,height);
        bounds = dest;
        if(dest.right < 0 || dest.left > canvas.getWidth()
                || dest.bottom < 0 || dest.top > canvas.getHeight()){
            Log.i("Offscreen", "Offscreen");
            offScreen = true;}
        else {
            canvas.translate(position.X, position.Y);
            canvas.rotate(rotationAngle);
            canvas.drawBitmap(SPRITE_SOURCE, src, dest, EIGHT_BIT_GOODNESS);
        }
        canvas.restore();

    }*/
}
